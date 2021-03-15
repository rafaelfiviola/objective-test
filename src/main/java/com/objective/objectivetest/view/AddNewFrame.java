package com.objective.objectivetest.view;

import com.objective.objectivetest.utils.BeanUtils;
import com.objective.objectivetest.service.QuestionService;
import com.objective.objectivetest.entities.QuestionEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JFormattedTextField newOptionTextField;
    private JLabel label;


    public AddNewFrame(String newOptionText, QuestionEntity questionEntity) {
        init(questionEntity, newOptionText);
    }

    private void init(QuestionEntity questionEntity, String newOptionText) {
        initUI(questionEntity, newOptionText);
        setText(newOptionText, questionEntity);
        pack();
        setVisible(true);
    }

    private void initUI(QuestionEntity questionEntity, String optionText) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(questionEntity, optionText);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void setText(String chosenOption, QuestionEntity questionEntity) {
        this.label.setText(
                chosenOption == null
                ? "Qual é o prato em que você pensou?"
                : "Complete: "
                        .concat(chosenOption)
                        .concat(" é ________,  mas ")
                        .concat(questionEntity.getOption())
                        .concat(" não"));
    }

    private void onOK(QuestionEntity questionEntity, String newOptionText) {
        var fieldValue = newOptionTextField.getText();
        if (newOptionText == null) {
            new AddNewFrame(fieldValue, questionEntity);
        } else {
            insert(questionEntity, fieldValue, newOptionText);
            new QuestionFrame(getQuestionService().findFirst());
        }
        dispose();
    }

    private void insert(
            QuestionEntity questionEntity, String fieldValue,
            String newOptionText) {
        getQuestionService().insert(questionEntity, fieldValue, newOptionText);
    }

    private QuestionService getQuestionService() {
        return BeanUtils.getBean(QuestionService.class);
    }

}
