package com.objective.objectivetest;

import com.objective.objectivetest.view.MainFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class ObjectiveTestApplication extends JFrame {

    public ObjectiveTestApplication() {
    }

    public static void main(String[] args) {
        var ctx = new SpringApplicationBuilder(ObjectiveTestApplication.class)
                .headless(false).run(args);
        EventQueue.invokeLater(() -> {
            MainFrame appFrame = ctx.getBean(MainFrame.class);
        });
    }


}
