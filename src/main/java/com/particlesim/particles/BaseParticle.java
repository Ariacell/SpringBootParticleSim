package com.particlesim.particles;

import java.awt.*;

import com.particlesim.container.BoundingBox;
import com.particlesim.model.Vector2D;

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

    private Vector2D<Integer> location;
    private Vector2D<Double> velocity; 
    private double angle;
    private double mass;

    public BaseParticle(int x, int y, double speed, double angle, double mass) {
        this.location = new Vector2D<Integer>(x, y);
        this.velocity = new Vector2D<Double>(
            (speed * Math.cos(Math.toRadians(angle))),
            (-speed * Math.sin(Math.toRadians(angle)))
        );
        this.angle = angle;
        this.mass = mass;
    }

    public void draw(Graphics g){
        g.drawLine(location.getX(), location.getY(), location.getX(), location.getY());
    }

    public void tick(ParticleContext pCtx){
        BoundingBox bounds = pCtx.getParticleBounds();
        int newX = (int)(location.getX() + Math.signum(velocity.getX())*Math.ceil(Math.abs(velocity.getX())));
        if (newX >= bounds.getMaxX()) {
            location.setX(bounds.getMaxX());
            velocity.setX(-velocity.getX());
        } else if (newX <= bounds.getMinX()){
            location.setX(bounds.getMinX());
            velocity.setX(-velocity.getX());
        } else {
            location.setX(newX);
        }

        int newY = (int)(location.getY() + Math.signum(velocity.getY())*Math.ceil(Math.abs(velocity.getY())));
        if (newY >= bounds.getMaxY()) {
            location.setY(bounds.getMaxY());
            velocity.setY(-velocity.getY());
        } else if (newY <= bounds.getMinY()){
            location.setY(bounds.getMinY());
            velocity.setY(-velocity.getY());
        } else {
            location.setY(newY);
        }
    }

}