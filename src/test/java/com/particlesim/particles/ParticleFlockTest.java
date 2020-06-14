package com.particlesim.particles;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

import com.particlesim.model.BaseParticle;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParticleFlockTest {
    
    @Test
    void particleFlock_ShouldConstructWithWantedNumberOfParticles() {
        ParticleFlock particleFlock = new ParticleFlock(20);
        assertThat(particleFlock.getParticleList().getParticles().size()).isEqualTo(20);
    }

    @Test
    void draw_ShouldCallDrawForEachParticleInFlock(){
        Graphics mockGraphics = Mockito.mock(Graphics.class);
        ParticleFlock particleFlock = new ParticleFlock(20);
        particleFlock.draw(mockGraphics);
        verify(mockGraphics, times(20)).drawOval(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test 
    void tick_ShouldUpdateParticlePosition(){
        
        ParticleFlock particleFlock = new ParticleFlock(1);
        // BaseParticle p = BaseParticle.builder()
        // .x(particleFlock.getParticleList().getParticles().get(0).getX())
        // .y(particleFlock.getParticleList().getParticles().get(0).getY())
        // .speed(particleFlock.getParticleList().getParticles().get(0).getSpeed())
        // .angle(particleFlock.getParticleList().getParticles().get(0).getAngle())
        // .mass(particleFlock.getParticleList().getParticles().get(0).getMass())
        // .build();

        particleFlock.tick();
//This test should become a test that the individual mocked particle had tick called on it
//And then the particle controls it's own updating.
//Influences on that update should occur from the flock before the invidual tick is called for particle
        // assertThat(p.getX()).isEqualTo(expected)
    }
}