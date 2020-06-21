package com.particlesim.particles;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

import com.particlesim.container.BoundingBox;
import com.particlesim.model.Particles;

import lombok.Getter;

/* It is the responsibility of the ParticleFlock to store the context for
each individual particle it is shepherding to access as needed.*/
public class ParticleFlock {

    @Getter
    private Particles particleList;

    @Getter
    private ParticleContext context;
    
    /* Hardcoded values here for now for particle attributes, will change from hardcoded once 
    tests are written to force me to do so, getting poc off the ground first*/
    public ParticleFlock(int numParticles, BoundingBox boundingContainer){
        List<BaseParticle> pList = new ArrayList<BaseParticle>();
        for(int i = 0; i < numParticles; i++) {
            /* TODO: finish this initialisation (Particles should probably
             be the ones to control the random based on single parametre passed to flock)*/
            pList.add(new BaseParticle(
                (int)(200 + Math.random() * (100)),
                (int)(200 + Math.random() * (100)),
                8.0,
                Math.random() * 360,
                1.0));
        }
        particleList = Particles.builder().
        particles(pList)
        .build();

        context = ParticleContext.builder().particleBounds(boundingContainer).build();
    }

    /*Constructor accepting a list of particles in case we want to 
    create a flock from an existing list of particles*/
    public ParticleFlock(List<BaseParticle> particleList, BoundingBox boundingContainer){
        this.particleList = Particles.builder()
        .particles(particleList)
        .build();

        context = ParticleContext.builder().particleBounds(boundingContainer).build();
    }

    public void draw(Graphics g){
        for(BaseParticle p : particleList.getParticles()){
            g.setColor(Color.ORANGE);
            p.draw(g);
        }
    }

    public void tick(){
        particleList.getParticles().stream().parallel().forEach(p -> p.tick(this.context));
    }
}