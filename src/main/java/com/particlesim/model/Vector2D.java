package com.particlesim.model;

import lombok.Data;

@Data
public class Vector2D<T> {
    
    private T a1;
    private T a2;

    public Vector2D (T a1, T a2){
        this.a1 = a1;
        this.a2 = a2;
    }

}