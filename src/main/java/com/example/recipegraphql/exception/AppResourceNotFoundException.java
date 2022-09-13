package com.example.recipegraphql.exception;

import java.util.function.Supplier;

public class AppResourceNotFoundException extends RuntimeException{

    public AppResourceNotFoundException(String message) {
        super(message);
    }

    public static Supplier<AppResourceNotFoundException> of(Class<?> clazz, String id){
        return () -> new AppResourceNotFoundException("Couldn't find object of class " + clazz.getSimpleName() + " with id " + id);
    }
}
