package com.particlesim.model;

import com.particlesim.particles.BaseParticle;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Particles {
    private List<BaseParticle> particles;
}