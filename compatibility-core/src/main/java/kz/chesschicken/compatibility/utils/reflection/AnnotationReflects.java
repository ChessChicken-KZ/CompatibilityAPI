package kz.chesschicken.compatibility.utils.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Great thing, but works only on java 1.8.
 */
public class AnnotationReflects {

    private static final Constructor<?> AnnotationInvocationHandler_constructor;
    private static final Constructor<?> AnnotationData_constructor;
    private static final Method Class_annotationData;
    private static final Field Class_classRedefinedCount;
    private static final Field AnnotationData_annotations;
    private static final Field AnnotationData_declaredAnnotations;
    private static final Method Atomic_casAnnotationData;
    private static final Class<?> Atomic_class;

    static{
        try {
            Class<?> AnnotationInvocationHandler_class = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            AnnotationInvocationHandler_constructor = AnnotationInvocationHandler_class.getDeclaredConstructor(Class.class, Map.class);
            AnnotationInvocationHandler_constructor.setAccessible(true);

            Atomic_class = Class.forName("java.lang.Class$Atomic");
            Class<?> AnnotationData_class = Class.forName("java.lang.Class$AnnotationData");

            AnnotationData_constructor = AnnotationData_class.getDeclaredConstructor(Map.class, Map.class, int.class);
            AnnotationData_constructor.setAccessible(true);
            Class_annotationData = Class.class.getDeclaredMethod("annotationData");
            Class_annotationData.setAccessible(true);

            Class_classRedefinedCount= Class.class.getDeclaredField("classRedefinedCount");
            Class_classRedefinedCount.setAccessible(true);

            AnnotationData_annotations = AnnotationData_class.getDeclaredField("annotations");
            AnnotationData_annotations.setAccessible(true);
            AnnotationData_declaredAnnotations = AnnotationData_class.getDeclaredField("declaredAnnotations");
            AnnotationData_declaredAnnotations.setAccessible(true);

            Atomic_casAnnotationData = Atomic_class.getDeclaredMethod("casAnnotationData", Class.class, AnnotationData_class, AnnotationData_class);
            Atomic_casAnnotationData.setAccessible(true);

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
                int classRedefinedCount = Class_classRedefinedCount.getInt(c);
                Object annotationData = Class_annotationData.invoke(c);
                Object newAnnotationData = createAnnotationData(c, annotationData, annotationClass, annotation, classRedefinedCount);
                if ((boolean) Atomic_casAnnotationData.invoke(Atomic_class, c, annotationData, newAnnotationData)) {
                    break;
                }
            }
        } catch(IllegalArgumentException | IllegalAccessException | InvocationTargetException | InstantiationException e){
            throw new IllegalStateException(e);
        }

    }

    @SuppressWarnings("unchecked")
    private static <T extends Annotation> Object createAnnotationData(Class<?> c, Object annotationData, Class<T> annotationClass, T annotation, int classRedefinedCount) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) AnnotationData_annotations.get(annotationData);
        Map<Class<? extends Annotation>, Annotation> declaredAnnotations= (Map<Class<? extends Annotation>, Annotation>) AnnotationData_declaredAnnotations.get(annotationData);

        Map<Class<? extends Annotation>, Annotation> newDeclaredAnnotations = new LinkedHashMap<>(annotations);
        newDeclaredAnnotations.put(annotationClass, annotation);
        Map<Class<? extends Annotation>, Annotation> newAnnotations ;
        if (declaredAnnotations == annotations) {
            newAnnotations = newDeclaredAnnotations;
        } else{
            newAnnotations = new LinkedHashMap<>(annotations);
            newAnnotations.put(annotationClass, annotation);
        }
        return AnnotationData_constructor.newInstance(newAnnotations, newDeclaredAnnotations, classRedefinedCount);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T annotationForMap(final Class<T> annotationClass, final Map<String, Object> valuesMap){
        return (T) AccessController.doPrivileged((PrivilegedAction<Annotation>) () -> {
            try {
                return (Annotation)Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[] { annotationClass }, (InvocationHandler) AnnotationInvocationHandler_constructor.newInstance(annotationClass,new HashMap<>(valuesMap)));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}