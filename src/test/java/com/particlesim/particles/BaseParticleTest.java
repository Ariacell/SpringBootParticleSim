package com.particlesim.particles;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;

import com.particlesim.container.BoundingBox;

public class BaseParticleTest {

    @Mock
    Graphics g = Mockito.mock(Graphics.class);

    private static final Double TOLERANCE = 0.00000000001;
    private final int x = 10;
    private final int y = x;

    @Test
    void baseParticle_shouldInstantiateWithCorrectSpeeds() {
        final BaseParticle baseParticle = new BaseParticle(x, y, 20.0, 45, 1.0);
        assertThat(baseParticle.getSpeedX() - 20 * (1 / Math.sqrt(2))).isLessThan(TOLERANCE);
        assertThat(baseParticle.getSpeedY() - 20 * (1 / Math.sqrt(2))).isLessThan(TOLERANCE);
    }

    @Test
    void baseParticle_ShouldDrawItselfAtDiscretePoint() {
        final BaseParticle baseParticle = new BaseParticle(x, y, 20.0, 90, 1.0);
        baseParticle.draw(g);
        verify(g).drawLine(x, y, x, y);
    }

    @Test
    void tickWithoutContext_shouldUpdateWithSpeedOnTick() {
        final BaseParticle baseParticle = new BaseParticle(x, y, 20.0, 315, 1.0);
        final ParticleContext particleContext = ParticleContext.builder()
        .particleBounds(new BoundingBox(x-2, y-2, x+100, y+100, Color.BLACK, Color.BLACK))
        .build();
        
        baseParticle.tick(particleContext);

        assertThat(baseParticle.getX()).isEqualTo((int) (x + baseParticle.getSpeedX()));
        assertThat(baseParticle.getY()).isEqualTo((int) (x + baseParticle.getSpeedY()));
    }

    @Test
    void tickWithContext_shouldUpdateWithSpeedOnTick() {
        final BaseParticle baseParticle = new BaseParticle(x, y, 20.0, 315, 1.0);
        final ParticleContext particleContext = ParticleContext.builder()
        .particleBounds(new BoundingBox(x-2, y-2, x+100, y+100, Color.BLACK, Color.BLACK))
        .build();

        baseParticle.tick(particleContext);
        
        assertThat(baseParticle.getX()).isEqualTo((int) (x + baseParticle.getSpeedX()));
        assertThat(baseParticle.getY()).isEqualTo((int) (y + baseParticle.getSpeedY()));
    }

    @Test
    void tickWithContext_shouldMakeParticleRemainWithinProvidedBounds() {

        int minCorner = 20;
        int maxCorner = 50;
        int maxCornerAdjacentPoint = minCorner + maxCorner -1;
        int minCornerAdjacentPoint = minCorner + 1;
        double downRightAngle = 315;
        double upLeftAngle = 135;
        double speed = 20.0;

        final ParticleContext particleContext = ParticleContext.builder()
        .particleBounds(new BoundingBox(minCorner, minCorner, maxCorner, maxCorner, Color.BLACK, Color.BLACK))
        .build();
        final BaseParticle bottomRightEscapingParticle = new BaseParticle(maxCornerAdjacentPoint, maxCornerAdjacentPoint, speed, downRightAngle, 1.0);
        final BaseParticle topLeftEscapingParticle = new BaseParticle(minCornerAdjacentPoint, minCornerAdjacentPoint, speed, upLeftAngle, 1.0);
        double bottomParticleSpeedX = bottomRightEscapingParticle.getSpeedX();
        double bottomParticleSpeedY = bottomRightEscapingParticle.getSpeedY();
        double topParticleSpeedX = topLeftEscapingParticle.getSpeedX();
        double topParticleSpeedY = topLeftEscapingParticle.getSpeedY();

        bottomRightEscapingParticle.tick(particleContext);
        topLeftEscapingParticle.tick(particleContext);

        assertThat(bottomRightEscapingParticle.getX()).isEqualTo(particleContext.getParticleBounds().getMaxX());
        assertThat(bottomRightEscapingParticle.getY()).isEqualTo(particleContext.getParticleBounds().getMaxY());
        assertThat(bottomRightEscapingParticle.getSpeedX()).isCloseTo(-bottomParticleSpeedX, Offset.offset(0.001));
        assertThat(bottomRightEscapingParticle.getSpeedY()).isCloseTo(-bottomParticleSpeedY, Offset.offset(0.001));
        assertThat(topLeftEscapingParticle.getX()).isEqualTo(particleContext.getParticleBounds().getMinY());
        assertThat(topLeftEscapingParticle.getY()).isEqualTo(particleContext.getParticleBounds().getMinY());
        assertThat(topLeftEscapingParticle.getSpeedX()).isCloseTo(-topParticleSpeedX, Offset.offset(0.001));
        assertThat(topLeftEscapingParticle.getSpeedY()).isCloseTo(-topParticleSpeedY, Offset.offset(0.001));
    }
}