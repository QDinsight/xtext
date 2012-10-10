/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.typesystem.util;

import java.util.Collection;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.xbase.typesystem.references.ArrayTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightBoundTypeArgument;
import org.eclipse.xtext.xbase.typesystem.references.LightweightMergedBoundTypeArgument;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.ParameterizedTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.UnboundTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.WildcardTypeReference;

import com.google.common.collect.ListMultimap;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@NonNullByDefault
public class TypeArgumentFromComputedTypeCollector extends UnboundTypeParameterAwareTypeArgumentCollector {

	public static void resolveAgainstActualType(final LightweightTypeReference declaredType, LightweightTypeReference actualType,
			Collection<JvmTypeParameter> typeParameters, Map<JvmTypeParameter, LightweightMergedBoundTypeArgument> typeParameterMapping,
			BoundTypeArgumentSource source,
			ITypeReferenceOwner owner) {
		if (declaredType.isRawType() || actualType.isRawType())
			return;
		TypeArgumentFromComputedTypeCollector implementation = new TypeArgumentFromComputedTypeCollector(typeParameters, source, owner);
		implementation.populateTypeParameterMapping(declaredType, actualType);
		ListMultimap<JvmTypeParameter, LightweightBoundTypeArgument> parameterMapping = implementation.rawGetTypeParameterMapping();
		for(Map.Entry<JvmTypeParameter, LightweightBoundTypeArgument> entry: parameterMapping.entries()) {
			LightweightMergedBoundTypeArgument boundTypeArgument = typeParameterMapping.get(entry.getKey());
			if (boundTypeArgument != null ) {
				LightweightBoundTypeArgument computedBoundTypeArgument = entry.getValue();
				if (computedBoundTypeArgument.getSource() == BoundTypeArgumentSource.RESOLVED) {
					VarianceInfo varianceInfo = computedBoundTypeArgument.getDeclaredVariance().mergeDeclaredWithActual(computedBoundTypeArgument.getActualVariance());
					typeParameterMapping.put(entry.getKey(), new LightweightMergedBoundTypeArgument(computedBoundTypeArgument.getTypeReference(), varianceInfo));
				} else if (boundTypeArgument.getTypeReference() instanceof UnboundTypeReference) {
					UnboundTypeReference typeReference = (UnboundTypeReference) boundTypeArgument.getTypeReference();
					if (!typeReference.internalIsResolved()) {
						LightweightBoundTypeArgument value = entry.getValue();
						if (!(value.getTypeReference() instanceof UnboundTypeReference) || 
								((UnboundTypeReference) value.getTypeReference()).getHandle() != typeReference.getHandle())
							typeReference.acceptHint(value);
					}
				}
			}
		}
	}
	
	public TypeArgumentFromComputedTypeCollector(Collection<JvmTypeParameter> parametersToBeMapped,
			BoundTypeArgumentSource defaultSource, ITypeReferenceOwner owner) {
		super(parametersToBeMapped, defaultSource, owner);
	}

	@Override
	protected void acceptHint(UnboundTypeReference reference, LightweightTypeReference param) {
		if (!shouldProcess(reference.getTypeParameter())) {
			reference.tryResolve();
			if (reference.internalIsResolved()) {
				outerVisit(reference, param);
			} else {
				super.acceptHint(reference, param);
			}
		} else {
			reference.acceptHint(boundByInference(param));
		}
	}
	
	@Override
	protected ArrayTypeReferenceTraverser createArrayTypeReferenceTraverser() {
		return new ArrayTypeReferenceTraverser() {
			@Override
			protected void doVisitParameterizedTypeReference(ParameterizedTypeReference reference, ArrayTypeReference declaration) {
				JvmType type = reference.getType();
				if (type instanceof JvmTypeParameter) {
					if (shouldProcess((JvmTypeParameter) type)) {
						JvmTypeParameter typeParameter = (JvmTypeParameter) type;
						processTypeParameter(typeParameter, declaration);
					}
				} else if (reference.isSubtypeOf(Iterable.class)) {
					ArrayTypeReference array = reference.toArrayIfIterable();
					if (array != null) {
						outerVisit(declaration, array);
					}
				}
			}
		};
	}
	
	@Override
	protected ParameterizedTypeReferenceTraverser createParameterizedTypeReferenceTraverser() {
		return new UnboundTypeParameterAwareParameterizedTypeReferenceTraverser() {
			@Override
			protected void doVisitArrayTypeReference(ArrayTypeReference reference, ParameterizedTypeReference declaration) {
				JvmType type = declaration.getType();
				if (type instanceof JvmTypeParameter) {
					if (shouldProcess((JvmTypeParameter) type)) {
						JvmTypeParameter typeParameter = (JvmTypeParameter) type;
						processTypeParameter(typeParameter, reference);
					}
				} else if (declaration.isSubtypeOf(Iterable.class)) {
					ArrayTypeReference array = declaration.toArrayIfIterable();
					if (array != null) {
						outerVisit(array, reference);
					}
				}
			}
		};
	}
	
	@Override
	protected UnboundTypeReferenceTraverser createUnboundTypeReferenceTraverser() {
		return new UnboundTypeParameterAwareUnboundTypeReferenceTraverser() {
			@Override
			protected void doVisitWildcardTypeReference(WildcardTypeReference reference,
					UnboundTypeReference declaration) {
				if (shouldProcess(declaration.getTypeParameter())) {
					declaration.acceptHint(boundByInference(reference));
				} else {
					super.doVisitWildcardTypeReference(reference, declaration);
				}
			}
		};
	}
	private LightweightBoundTypeArgument boundByInference(LightweightTypeReference reference) {
		return new LightweightBoundTypeArgument(reference.getWrapperTypeIfPrimitive(), BoundTypeArgumentSource.INFERRED, getOrigin(), getExpectedVariance(), getActualVariance());
	}
}

