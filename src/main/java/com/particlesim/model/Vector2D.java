package com.particlesim.model;

import lombok.Data;

@Data
public class Vector2D<T> {
    
    private T x;
    private T y;

    public Vector2D (T x, T y){
        this.x = x;
        this.y = y;
    }

}