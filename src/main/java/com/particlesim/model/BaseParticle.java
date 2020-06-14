package com.particlesim.model;

import java.awt.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseParticle {
    private int x,y;
    private double speed;
    private double angle;
    private double mass;

    public void draw(Graphics g){
        g.drawOval(x, y, 1, 1);
    }
}