package com.particlesim.particles;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.awt.Graphics;


public class BaseParticleTest {
    
    @Mock
    Graphics g = Mockito.mock(Graphics.class);

    private static final Double TOLERANCE = 0.00000000001;
    private final int x = 10;
    private final int y = x;

    @Test
    void baseParticle_shouldInstantiateWithCorrectSpeeds(){
        final BaseParticle baseParticle = new BaseParticle(x,y,20.0,45,1.0);
        assertThat(baseParticle.getSpeedX()-20*(1/Math.sqrt(2))).isLessThan(TOLERANCE);
        assertThat(baseParticle.getSpeedY()-20*(1/Math.sqrt(2))).isLessThan(TOLERANCE);
    }

    @Test
    void baseParticle_ShouldDrawItselfAtDiscretePoint() {
        final BaseParticle baseParticle = new BaseParticle(x,y,20.0,90,1.0);
        baseParticle.draw(g);
        verify(g).drawOval(x, y, 1, 1);
    }

    @Test
    void baseParticle_shouldUpdateWithSpeedOnTick() {
        final BaseParticle baseParticle = new BaseParticle(x,y,20.0,45,1.0);
        baseParticle.tick();
        assertThat(baseParticle.getX()).isEqualTo((int)(x+baseParticle.getSpeedX()));
        assertThat(baseParticle.getY()).isEqualTo((int)(x+baseParticle.getSpeedY()));
    }
}