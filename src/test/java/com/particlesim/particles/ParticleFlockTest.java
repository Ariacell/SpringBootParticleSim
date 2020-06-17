package com.particlesim.particles;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.particlesim.container.BoundingBox;

import java.awt.*;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParticleFlockTest {

    @Mock
    BoundingBox boxMock;

    @Test
    void particleFlock_ShouldConstructWithWantedNumberOfParticles() {
        ParticleFlock particleFlock = new ParticleFlock(20, boxMock);
        assertThat(particleFlock.getParticleList().getParticles().size()).isEqualTo(20);
    }

    @Test
    void draw_ShouldCallDrawForEachParticleInFlock(){
        Graphics mockGraphics = Mockito.mock(Graphics.class);
        ParticleFlock particleFlock = new ParticleFlock(20, boxMock);
        particleFlock.draw(mockGraphics);
        verify(mockGraphics, times(20)).drawOval(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test 
    void tick_ShouldUpdateParticlePosition(){
        
        BaseParticle p1 = new BaseParticle(20,20,20.0,60.0,1.0);
        BaseParticle p2 = new BaseParticle(30,30,25.0,60.0,1.0);
        List<BaseParticle> pList = new ArrayList<BaseParticle>();
        pList.add(p1);
        pList.add(p2);
        ParticleFlock particleFlock = new ParticleFlock(pList, boxMock);

        particleFlock.tick();
        pList.forEach(p -> p.tick());

        assertThat(particleFlock.getParticleList().getParticles().get(0).getX()).isEqualTo(p1.getX());
        assertThat(particleFlock.getParticleList().getParticles().get(0).getY()).isEqualTo(p1.getY());
        assertThat(particleFlock.getParticleList().getParticles().get(1).getX()).isEqualTo(p2.getX());
        assertThat(particleFlock.getParticleList().getParticles().get(1).getY()).isEqualTo(p2.getY());
    }
}
