package com.particlesim.container;

import java.awt.Color;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.particlesim.particles.ParticleFlock;

import lombok.Getter;

public class MainPanel extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 107301013674641039L;

    private static final int FRAME_RATE = 30;

    @Getter
    private AppPanel appPanel;

    @Getter
    private int canvasWidth, canvasHeight;

    @Getter
    private BoundingBox container;
    @Getter
    private ParticleFlock particleFlock;

    public MainPanel(int width, int height) {
        canvasWidth = width;
        canvasHeight = height;
        //Strange off by 1 bug on the x-axis, why?
        container = new BoundingBox(1, 0, canvasWidth, canvasHeight, Color.BLUE, Color.BLACK);
        particleFlock = new ParticleFlock(300);

        //Init the drawing panel:
        appPanel = new AppPanel();
        this.setSize(canvasWidth, canvasHeight);
        this.setLayout(new BorderLayout());
        this.add(appPanel, BorderLayout.CENTER);
        
    }


    private class AppPanel extends JPanel{

        /**
         *
         */
        private static final long serialVersionUID = 4192031073930160758L;

        @Override
        public void paintComponent(Graphics g) {
            container.draw(g);
            particleFlock.draw(g);
        }


    }

}