package com.particlesim.container;

import java.awt.Color;
import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.particlesim.particles.BaseParticle;
import com.particlesim.particles.ParticleFlock;

import lombok.Getter;

public class MainPanel extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 107301013674641039L;

    private static final int FRAME_RATE = 30;

    private BufferedImage particleImg;
    int[] imgBuffer;
    private BufferedImage baseImg;

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
        // Strange off by 1 bug on the x-axis, why?
        container = new BoundingBox(1, 0, canvasWidth, canvasHeight, Color.BLUE, Color.BLACK);
        particleFlock = new ParticleFlock(500, container);

        /* Set up the base img to quickly clear our buffer back to baseline */
        this.baseImg = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);

        /*
         * Image Buffer code, we only new it up once, and only update the particles x
         * and y locations and their previous locations to save on time
         */
        this.particleImg = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        imgBuffer = ((DataBufferInt) particleImg.getRaster().getDataBuffer()).getData();
        //The belox code doesn't work, will be moving to using the data buffer to update as discussed above.
        // for (int row = 0; row < canvasHeight; row++) {
        //     for (int col = 0; col < canvasWidth; col++) {
        //         particleImg.setRGB(row, col, Color.BLUE.getRGB());
        //     }
        // }

        // Init the drawing panel:
        appPanel = new AppPanel();
        this.setSize(canvasWidth, canvasHeight);
        this.setLayout(new BorderLayout());
        this.add(appPanel, BorderLayout.CENTER);

        simulationStart();
    }

    /*
     * No tests around the update logic yet because I'm not sure what exactly I want
     * to test here. WIP
     */
    public void simulationStart() {
        Thread simThread = new Thread() {
            public void run() {
                while (true) {
                    updateSim();
                    repaint();
                    try {
                        Thread.sleep(1000 / FRAME_RATE);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        simThread.start();
    }

    public void updateSim() {
        particleFlock.tick();
        //particleImg.setData(baseImg.getRaster());
        for(BaseParticle p : this.particleFlock.getParticleList().getParticles()){
            particleImg.setRGB(p.getX(), p.getY(), Color.WHITE.getRGB());
        }
    }

    private class AppPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 4192031073930160758L;

        @Override
        public void paintComponent(Graphics g) {
            // container.draw(g);
            g.drawImage(particleImg,0,0,null);
            // particleFlock.draw(g);
        }

    }

}