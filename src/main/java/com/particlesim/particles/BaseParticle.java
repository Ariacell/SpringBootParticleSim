package com.particlesim.particles;

import java.awt.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseParticle {
    private int x,y;
    private double speedX,speedY;
    private double angle;
    private double mass;

    public BaseParticle(int x, int y, double speed, double angle, double mass) {
        this.x = x;
        this.y = y;
        this.speedX = (speed * Math.cos(Math.toRadians(angle)));
        this.speedY = (-speed * Math.sin(Math.toRadians(angle)));
        this.angle = angle;
        this.mass = mass;
    }

    public void draw(Graphics g){
        g.drawOval(x, y, 1, 1);
    }

    public void tick(){
        x = (int)(x + speedX);
        y = (int)(y + speedY);
    }


}