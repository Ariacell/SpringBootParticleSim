package com.particlesim.particles;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

import com.particlesim.model.Particles;

import lombok.Getter;

public class ParticleFlock {

    @Getter
    private Particles particleList;
    
    /* Hardcoded values here for now for particle attributes, will change from hardcoded once 
    tests are written to force me to do so, getting poc off the ground first*/
    public ParticleFlock(int numParticles){
        List<BaseParticle> pList = new ArrayList<BaseParticle>();
        for(int i = 0; i < numParticles; i++) {
            /* TODO: finish this initialisation (Particles should probably
             be the ones to control the random based on single parametre passed to flock)*/
            pList.add(new BaseParticle(
                (int)(20 + Math.random() * (500)),
                (int)(20 + Math.random() * (500)),
                2.0,
                Math.random() * 360,
                1.0));
        }
        particleList = Particles.builder().
        particles(pList)
        .build();
    }

    /*Constructor accepting a list of particles in case we want to 
    create a flock from an existing list of particles*/
    public ParticleFlock(List<BaseParticle> particleList){
        this.particleList = Particles.builder()
        .particles(particleList)
        .build();
    }

    public void draw(Graphics g){
        for(BaseParticle p : particleList.getParticles()){
            g.setColor(Color.ORANGE);
            p.draw(g);
        }
    }

    public void tick(){
        particleList.getParticles().stream().parallel().forEach(p -> p.tick());
    }
}