package com.particlesim.ParticleSim;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParticleSimApplicationTests {

	@BeforeAll
	public static void setup() {
		/*Since we run the main app in non-headless mode we need to
		inform the SpringBootTest loader to also do the same, otherwise
		the context will fail to load correctly.*/
		System.setProperty("java.awt.headless", "false");
	}

	@Test
	void contextLoads() {
	}

}
