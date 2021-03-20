package com.objective.objectivetest.view;

import com.objective.objectivetest.service.QuestionService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class MainFrame extends JFrame {

    private final QuestionService questionService;
    private JPanel contentPane;
    private JButton buttonOK;

    public MainFrame(QuestionService questionService) {
        this.questionService = questionService;
        init();

    }

    private void init() {
        initUI();
        pack();
        setVisible(true);
    }

    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        new QuestionFrame(this.questionService.findFirst());
    }
}
