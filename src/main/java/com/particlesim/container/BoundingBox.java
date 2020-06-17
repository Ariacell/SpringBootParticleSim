package com.particlesim.container;

import java.awt.*;
import lombok.Getter;

public class BoundingBox {

    private final int DEFAULT_WIDTH = 600;
    private final int DEFAULT_HEIGHT = 400;
    private final Color DEFAULT_BORDER_COLOUR = Color.BLACK;
    private final Color DEFAULT_FILL_COLOUR = Color.LIGHT_GRAY;

    @Getter
    int minX,maxX,minY,maxY;

    @Getter
    private Color fillColour;

    @Getter
    private Color borderColour;
 
    public BoundingBox(int x,int y) {
        minX = x;
        minY = y;
        maxX = x+DEFAULT_WIDTH-1;
        maxY = y+DEFAULT_HEIGHT-1;
        fillColour = DEFAULT_FILL_COLOUR;
        borderColour = DEFAULT_BORDER_COLOUR;
    }

    public BoundingBox(int x,int y, int width, int height, Color fillColour, Color borderColour) {
        minX = x;
        minY = y;
        maxX = x+width-1;
        maxY = y+height-1;
        this.fillColour = fillColour;
        this.borderColour = borderColour;
    }

    public void draw(Graphics g) {
        g.setColor(fillColour);
        g.fillRect(minX, minY, maxX - minX -1, maxY - minY -1);
        g.setColor(borderColour);
        g.drawRect(minX, minY, maxX - minX -1, maxY - minY -1);
    }

}