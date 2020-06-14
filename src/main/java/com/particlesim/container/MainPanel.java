package com.particlesim.container;

import java.awt.Color;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
    private Container container;

    public MainPanel(int width, int height) {
        canvasWidth = width;
        canvasHeight = height;
        container = new Container(0, 0, canvasWidth, canvasHeight, Color.BLUE, Color.BLACK);

        //Init the drawing panel:
        appPanel = new AppPanel();
        this.setSize(canvasWidth, canvasHeight);
        // this.setLayout(new BorderLayout());
        // this.add(appPanel, BorderLayout.CENTER);
        // repaint();
    }


    private class AppPanel extends JPanel{

        /**
         *
         */
        private static final long serialVersionUID = 4192031073930160758L;

        @Override
        public void paintComponent(Graphics g) {
            container.draw(g);
        }


    }

}