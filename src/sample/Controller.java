package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Double temp = 0.0, sum = 0.0;
    boolean isOperatorPressed;
    String operatorPressed = "";

    @FXML
    TextField outputTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outputTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d*)?")) {
                    outputTF.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void onNumberClick(ActionEvent event) {

            Button btn = (Button)event.getSource();
            outputTF.setText(outputTF.getText().trim() + btn.getText().trim());
            isOperatorPressed = false;
        }



    public void onOperatorClick(ActionEvent event)
    {

            Button btn = (Button)event.getSource();
            if (!outputTF.getText().isEmpty()) {
                temp = Double.valueOf(outputTF.getText());
                if (btn.getText().equals("%")) {
                    temp = sum * temp / 100;
                }
                if (btn.getText().equals("sqrt")) {
                    temp = Math.sqrt ( temp );
                }
                 switch (operatorPressed) {
                    case "/":
                        sum /= temp;
                        break;
                    case "*":
                        sum *= temp;
                        break;
                    case "+":
                        sum += temp;
                        break;
                    case "-":
                        sum -= temp;
                        break;
                    case "^":
                        sum = Math.pow ( sum,temp );
                        break;
                    default:
                        sum = temp;
                }
            }

            if (btn.getText().equals("=") || btn.getText().equals("%")||btn.getText().equals("sqrt")) {
                outputTF.setText(String.valueOf(sum));
                operatorPressed = "";
            } else {
                outputTF.setText("");
                operatorPressed = btn.getText().trim();
            }
            isOperatorPressed = true;
        }

    public void onDELClick(ActionEvent event) {
        if(outputTF.getText().length() > 0) {
            outputTF.setText(outputTF.getText(0, outputTF.getText().length() - 1));
        }
    }

    public void onCEClick(ActionEvent event) {
        outputTF.setText("");
        temp = 0.0;
        sum = 0.0;
        isOperatorPressed = false;
        operatorPressed = "";
    }

   
}
