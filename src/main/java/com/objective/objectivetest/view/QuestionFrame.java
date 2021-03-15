package com.objective.objectivetest.view;

import com.objective.objectivetest.entities.QuestionEntity;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuestionFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel optionLabel;

    public QuestionFrame(QuestionEntity questionEntity) {
        init(questionEntity);
    }


    private void init(
            QuestionEntity questionEntity) {
        initUI(questionEntity);
        setText(this.optionLabel, questionEntity);
        pack();
        setVisible(true);

    }

    private void initUI(QuestionEntity questionEntity) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK(questionEntity));
        buttonCancel.addActionListener(e -> onCancel(questionEntity));

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel(questionEntity);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(questionEntity),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    private void onOK(QuestionEntity questionEntity) {
        if (questionEntity.isLeaf()) {
            goToSuccessFrame();
        } else {
            goToNextFrame(questionEntity.getIfTrueNext());
        }
    }


    private void onCancel(QuestionEntity questionEntity) {
        if (questionEntity.isLeaf()) {
            goToAddNewFrame(questionEntity);
        } else {
            goToNextFrame(questionEntity.getIfFalseNext());
        }
    }

    private void goToNextFrame(QuestionEntity questionEntity) {
        new QuestionFrame(questionEntity);
        dispose();
    }

    private void goToSuccessFrame() {
        new SuccessFrame();
        dispose();
    }

    private void goToAddNewFrame(QuestionEntity questionEntity) {
        new AddNewFrame(null, questionEntity);
        dispose();
    }

    private void setText(JLabel label, QuestionEntity questionEntity) {
        label
                .setText((questionEntity.isLeaf()
                          ? "Você pensou em "
                          : "O prato em que você pensou é ")
                        .concat(questionEntity.getOption())
                        .concat("?"));
    }


}
