package de.tud.cs.soot.callgraph.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethods;

import soot.tagkit.AnnotationAnnotationElem;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationTag;

public class InvokedMethodsCreator {

	private InvokedMethodCreator imc;

	public InvokedMethodsCreator(InvokedMethodCreator imc) {
		this.imc = imc;
	}

	public InvokedMethods create(AnnotationTag at) {
		for (AnnotationElem val : at.getElems()) {
			if ((val instanceof AnnotationArrayElem)) {
				List<InvokedMethod> methods = new ArrayList<>();

				for (AnnotationElem elem : ((AnnotationArrayElem) val).getValues()) {
					if ((elem instanceof AnnotationAnnotationElem)) {
						methods.add(imc.create(((AnnotationAnnotationElem) elem).getValue()));
					} else {
						throw new RuntimeException();
					}
				}

				InvokedMethod[] mths = methods.toArray(new InvokedMethod[methods.size()]);
				return newInvokedMethods(mths);
			}
		}
		throw new RuntimeException();
	}
	
	private InvokedMethods newInvokedMethods(final InvokedMethod[] invokedMethods){
		return new InvokedMethods() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return InvokedMethods.class;
			}
			
			@Override
			public InvokedMethod[] value() {
				return invokedMethods;
			}
		};
	}
}
