package com.particlesim.container;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


public class MainPanelTest {
   
    @Test
    void mainPanel_ShouldInitialiseWithWidthAndHeight() {
        MainPanel mainPanel = new MainPanel(501,502);
        assertThat(mainPanel.getCanvasWidth()).isEqualTo(501);
        assertThat(mainPanel.getCanvasHeight()).isEqualTo(502);
        assertThat(mainPanel.getContainer()).isNotNull();
        assertThat(mainPanel.getAppPanel()).isNotNull();
    }

    @Test
    void mainPanel_ShouldCreateAnInnerPanel() {
        MainPanel mainPanel = new MainPanel(501,502);
        //Not sure how to properly test the assignment of BorderLayout yet
        assertThat(mainPanel.getLayout()).isNotNull();
        
    }

}