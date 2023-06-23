package com.backend.moviebooking.Config;

import com.backend.moviebooking.Model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class CustomIdGenerationEventListener extends AbstractMongoEventListener<Object> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent entity) {
        Object source = entity.getSource();
        if((source instanceof Movie) && (((Movie) source).getId() == null)) {
            ((Movie) source).setId(UUID.randomUUID().toString() + entity);
        }
    }
}
