package io.sakib.demo.custom_annotation;

import java.lang.reflect.Field;

public class AnnotationProcessor {

    public static Object process(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.println("field.getName() = " + field.getName());
            // Check if the field is annotated with @CustomValue
            if(field.isAnnotationPresent(CustomValue.class)) {
                CustomValue customValue = field.getAnnotation(CustomValue.class);
                System.out.println("customValue.value() = " + customValue.value());
                field.setAccessible(true);
                field.set(obj, customValue.value());
            }
        }
        return obj; // Return the modified original object
    }
}
