package com.particlesim.particles;

import java.awt.*;

import com.particlesim.container.BoundingBox;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/* The Base Particle class for storing individual particle attributes, with
knowledge of the outside world only being passed in through context arguments
in update methods */
public class BaseParticle {

    protected static final double PARTICLE_ANGLE_RIGHT = 0;
    protected static final double PARTICLE_ANGLE_LEFT = 180;
    protected static final double PARTICLE_ANGLE_UP = 90;
    protected static final double PARTICLE_ANGLE_DOWN = 270;

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
        g.drawLine(x, y, x, y);
    }

    public void tick(ParticleContext pCtx){
        BoundingBox bounds = pCtx.getParticleBounds();
        int newX = (int)(x + Math.signum(speedX)*Math.ceil(Math.abs(speedX)));
        if (newX >= bounds.getMaxX()) {
            x = bounds.getMaxX();
            speedX = -speedX;
        } else if (newX <= bounds.getMinX()){
            x = bounds.getMinX();
            speedX = -speedX;
        } else {
            x = newX;
        }

        int newY = (int)(y + Math.signum(speedY)*Math.ceil(Math.abs(speedY)));
        if (newY >= bounds.getMaxY()) {
            y = bounds.getMaxY();
            speedY = -speedY;
        } else if (newY <= bounds.getMinY()){
            y = bounds.getMinY();
            speedY = -speedY;
        } else {
            y = newY;
        }
    }

}