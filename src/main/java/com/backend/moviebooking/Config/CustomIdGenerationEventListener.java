package com.backend.moviebooking.Config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.lang.reflect.Field;
import java.util.UUID;

public class CustomIdGenerationEventListener extends AbstractMongoEventListener<Object> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent entity) {
        Object source = entity.getSource();
        if(source != null) {
            Class<?> entityClass = source.getClass();
            Field idField = getIdField(entityClass);
            if(idField != null) {
                try {
                    idField.setAccessible(true);
                    Object idValue = idField.get(source);
                    if(idValue == null) {
                        idField.set(source, generateId(entityClass));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Field getIdField(Class<?> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    private String generateId(Class<?> entityClass) {
        return UUID.randomUUID().toString() + entityClass.getSimpleName();
    }
}
