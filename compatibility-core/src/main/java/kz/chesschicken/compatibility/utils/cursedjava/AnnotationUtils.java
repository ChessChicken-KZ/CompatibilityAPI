package kz.chesschicken.compatibility.utils.cursedjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnotationUtils {

    private static final Constructor<?> annotationInvocationHandlerConstructor;
    private static final Constructor<?> annotationDataConstructor;
    private static final Method annotationData;
    private static final Field classRedefinedCount;
    private static final Field annotations;
    private static final Field declaredAnnotations;
    private static final Method casAnnotationData;
    private static final Class<?> javaLangClassAtomic;

    static {
        try {
            annotationInvocationHandlerConstructor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructor(Class.class, Map.class);
            annotationInvocationHandlerConstructor.setAccessible(true);

            javaLangClassAtomic = Class.forName("java.lang.Class$Atomic");
            Class<?> annotationDataClass = Class.forName("java.lang.Class$AnnotationData");

            annotationDataConstructor = annotationDataClass.getDeclaredConstructor(Map.class, Map.class, int.class);
            annotationDataConstructor.setAccessible(true);
            annotationData = Class.class.getDeclaredMethod("annotationData");
            annotationData.setAccessible(true);

            classRedefinedCount = Class.class.getDeclaredField("classRedefinedCount");
            classRedefinedCount.setAccessible(true);

            annotations = annotationDataClass.getDeclaredField("annotations");
            annotations.setAccessible(true);
            declaredAnnotations = annotationDataClass.getDeclaredField("declaredAnnotations");
            declaredAnnotations.setAccessible(true);

            casAnnotationData = javaLangClassAtomic.getDeclaredMethod("casAnnotationData", Class.class, annotationDataClass, annotationDataClass);
            casAnnotationData.setAccessible(true);

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T extends Annotation> void putAnnotation(Class<?> c, Class<T> annotationClass, Map<String, Object> valuesMap){
        putAnnotation(c, annotationClass, annotationForMap(annotationClass, valuesMap));
    }

    public static <T extends Annotation> void putAnnotation(Class<?> c, Class<T> annotationClass, T annotation){
        try {
            while (true) {
                Object annotationData = AnnotationUtils.annotationData.invoke(c);
                if ((boolean) casAnnotationData.invoke(javaLangClassAtomic, c, annotationData, createAnnotationData(c, annotationData, annotationClass, annotation, AnnotationUtils.classRedefinedCount.getInt(c)))) {
                    break;
                }
            }
        } catch(IllegalArgumentException | IllegalAccessException | InvocationTargetException | InstantiationException e){
            throw new IllegalStateException(e);
        }

    }

    @SuppressWarnings("unchecked")
    private static <T extends Annotation> Object createAnnotationData(Class<?> c, Object annotationData, Class<T> annotationClass, T annotation, int classRedefinedCount) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) AnnotationUtils.annotations.get(annotationData);
        Map<Class<? extends Annotation>, Annotation> declaredAnnotations= (Map<Class<? extends Annotation>, Annotation>) AnnotationUtils.declaredAnnotations.get(annotationData);

        Map<Class<? extends Annotation>, Annotation> newDeclaredAnnotations = InitAndApply.apply(new LinkedHashMap<>(annotations), instance -> instance.put(annotationClass, annotation));
        Map<Class<? extends Annotation>, Annotation> newAnnotations;
        if (declaredAnnotations == annotations) {
            newAnnotations = newDeclaredAnnotations;
        } else {
            newAnnotations = InitAndApply.apply(new LinkedHashMap<>(annotations), instance -> instance.put(annotationClass, annotation));
        }
        return annotationDataConstructor.newInstance(newAnnotations, newDeclaredAnnotations, classRedefinedCount);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T annotationForMap(final Class<T> annotationClass, final Map<String, Object> valuesMap){
        return (T) AccessController.doPrivileged((PrivilegedAction<Annotation>) () -> {
            try {
                return (Annotation)Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[] { annotationClass }, (InvocationHandler) annotationInvocationHandlerConstructor.newInstance(annotationClass,new HashMap<>(valuesMap)));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}