package com.objective.objectivetest.view;

import com.objective.objectivetest.utils.BeanUtils;

import javax.swing.*;
import java.awt.event.*;

public class SuccessFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;


    public SuccessFrame() {
        init();
    }

    private void init() {
        initUI();
        pack();
        setVisible(true);
    }

    public void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        BeanUtils.getBean(MainFrame.class).setVisible(true);
        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
