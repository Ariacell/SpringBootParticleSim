package com.particlesim.particles;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

import com.particlesim.model.BaseParticle;
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
                5.0,
                90.0,
                1.0));
        }
        particleList = Particles.builder().
        particles(pList)
        .build();
    }

    public void draw(Graphics g){
        for(BaseParticle p : particleList.getParticles()){
            g.setColor(Color.ORANGE);
            p.draw(g);
        }
    }

    public void tick(){

    }
}