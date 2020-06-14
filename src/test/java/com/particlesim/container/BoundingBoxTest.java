package com.particlesim.container;

import java.awt.*;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

public class BoundingBoxTest {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private final int OFFSET = 10;
    private final Color BORDER_COLOUR = Color.GREEN;
    private final Color FILL_COLOUR = Color.ORANGE;

    @Test
    void screen_ShouldConstructItselfWIthoutErrorsInDefaultCase() {
        BoundingBox screen = new BoundingBox(1,1);
        assertThat(screen.minX).isEqualTo(1);
        assertThat(screen.minY).isEqualTo(1);
        assertThat(screen.maxX).isEqualTo(WIDTH);
        assertThat(screen.maxY).isEqualTo(HEIGHT);
        assertThat(screen.getFillColour()).isEqualTo(Color.LIGHT_GRAY);
        assertThat(screen.getBorderColour()).isEqualTo(Color.BLACK);
    }

    @Test
    void screen_ShouldConstructItselfInNonTopLeftCorner() {
        BoundingBox screen = new BoundingBox(OFFSET,OFFSET);
        assertThat(screen.minX).isEqualTo(OFFSET);
        assertThat(screen.minY).isEqualTo(OFFSET);
        assertThat(screen.maxX).isEqualTo(WIDTH+OFFSET-1);
        assertThat(screen.maxY).isEqualTo(HEIGHT+OFFSET-1);
    }

    @Test
    void screen_ShouldConstructItselfWithNonDefaultArguments() {
        int someHeight = 505;
        int someWidth = 909;
        BoundingBox screen = new BoundingBox(OFFSET,OFFSET,someWidth,someHeight, FILL_COLOUR, BORDER_COLOUR);
        assertThat(screen.minX).isEqualTo(OFFSET);
        assertThat(screen.minY).isEqualTo(OFFSET);
        assertThat(screen.maxX).isEqualTo(someWidth + OFFSET - 1);
        assertThat(screen.maxY).isEqualTo(someHeight + OFFSET - 1);
        assertThat(screen.getFillColour()).isEqualTo(FILL_COLOUR);
        assertThat(screen.getBorderColour()).isEqualTo(BORDER_COLOUR);
    }

    @Test
    void draw_ShouldExecuteAsExpectedWIthoutErrors() {

        Graphics mockGraphics = Mockito.mock(Graphics.class);

        BoundingBox screen = new BoundingBox(OFFSET,OFFSET, WIDTH, HEIGHT, FILL_COLOUR, BORDER_COLOUR);
        screen.draw(mockGraphics);

        InOrder orderVerifier = Mockito.inOrder(mockGraphics);

        orderVerifier.verify(mockGraphics).setColor(FILL_COLOUR);
        orderVerifier.verify(mockGraphics).fillRect(OFFSET, OFFSET, screen.maxX - OFFSET - 1, screen.maxY - OFFSET - 1);
        orderVerifier.verify(mockGraphics).setColor(BORDER_COLOUR);
        orderVerifier.verify(mockGraphics).drawRect(OFFSET, OFFSET, screen.maxX - OFFSET - 1, screen.maxY - OFFSET - 1);
    }

}