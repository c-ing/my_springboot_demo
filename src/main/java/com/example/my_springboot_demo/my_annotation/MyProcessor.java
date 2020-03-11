package com.example.my_springboot_demo.my_annotation;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @Author: cdc
 * @Date: 2019/12/20 10:54
 */
@SupportedAnnotationTypes("com.example.my_springboot_demo.my_annotation.DataTest")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Process.class)
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER,"Hello World");
        return false;
    }
}
