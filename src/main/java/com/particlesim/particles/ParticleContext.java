package com.particlesim.particles;

import com.particlesim.container.BoundingBox;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticleContext {
    private BoundingBox particleBounds;
}