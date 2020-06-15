package com.particlesim.ParticleSim;


import com.particlesim.container.MainPanel;

import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ParticleSimApplication implements CommandLineRunner {

	public static void main(String[] args) {
		// SpringApplication.run(ParticleSimApplication.class, args);
		new SpringApplicationBuilder(ParticleSimApplication.class).headless(false).run(args);
	}

	@Override
	public void run(String... args) {
		// JFrame jFrame = new JFrame("Frame");
		// jFrame.setSize(600, 400);
		// jFrame.setTitle("Something Simulator");

		MainPanel mainPanel = new MainPanel(500, 550);
		mainPanel.setVisible(true);
	}

}
