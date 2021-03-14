package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;

public class CalculatorController {

    static int bracketCounter;
    public Label label;
    public Button openBrackets;
    public Button closedBrackets;
    private ArrayList<String> numbersArrayList = new ArrayList<>();
    private int enteredNumbersArrayListCounter = 0;


    @FXML
    //Initializes arraylist so i can start adding things right away (so that the first position isn't null)
    public void initialize() {
        numbersArrayList.add("");
    }

    //returns the part that needs to be put in the upper part of the label
    //aka. everything that was previously entered upto and excluding the last
    //thing that was entered as that belongs in the next line
    public String getUpperLabel(){
        String substring = "";
        for (int i = 0; i < numbersArrayList.size() - 1; i++){
            substring += numbersArrayList.get(i);
        }
        return substring;
    }

    //Prints out and calculates result with mxparser
    //also ignores empty strings ("")
    public String printArray(){
        int i = 0;
        String substring = "";
        for (String number : numbersArrayList) {
            if(number != ""){
                substring += number;
            }
        }
        Expression e1 = new Expression(substring);
        substring = String.valueOf(e1.calculate());
        return substring;
    }

    //Handles clicks on digits (including period and +-)
    public void digitClickHandler(ActionEvent e) {
        Button clickedButton = (Button) e.getTarget();
        String buttonLabel = clickedButton.getText();
        numbersArrayList.set(enteredNumbersArrayListCounter, numbersArrayList.get(enteredNumbersArrayListCounter) + buttonLabel);
        label.setText(getUpperLabel() + "\n" + numbersArrayList.get(enteredNumbersArrayListCounter));
    }

    //Handles clicks on operations (+ - * /) as well as brackets and counting thereof
    public void operationClickHandler(ActionEvent e) {
            Button clickedButton = (Button) e.getTarget();
            String buttonLabel = clickedButton.getText();
        String id = clickedButton.getId() != null ? clickedButton.getId() : "null";
        switch (id) {
            case ("openBrackets") -> {
                bracketCounter++;
                clickedButton.setText("(" + bracketCounter);
                numbersArrayList.add(("("));
            }
            case ("closedBrackets") -> {
                if (bracketCounter > 0) {
                    bracketCounter--;
                    openBrackets.setText("(" + bracketCounter);
                    numbersArrayList.add((")"));
                }
                if (bracketCounter <= 0) {
                    openBrackets.setText("(");
                }
            }
            case ("π") -> {
                numbersArrayList.add("3.14159265359");
            }
            default -> numbersArrayList.add(buttonLabel);
        }
        enteredNumbersArrayListCounter++;
        enteredNumbersArrayListCounter++;
        numbersArrayList.add("");
        label.setText(getUpperLabel() + "\n" + "");
    }

    //Handles clicks on functional keys
    public void functionClickHandler(ActionEvent e) {
        Button clickedButton = (Button) e.getTarget();
        String buttonLabel = clickedButton.getText();
        switch(buttonLabel){
            case ("="):
                    label.setText(printArray()); return;
            case ("<-"):
                if (enteredNumbersArrayListCounter == 0) {
                    numbersArrayList.set(enteredNumbersArrayListCounter, "");
                } else {
                    numbersArrayList.remove(enteredNumbersArrayListCounter);
                    enteredNumbersArrayListCounter--;
                }
                label.setText(getUpperLabel() + "\n" + numbersArrayList.get(enteredNumbersArrayListCounter));
                return;
            case ("CE"):
                numbersArrayList.clear();
                enteredNumbersArrayListCounter = 0;
                numbersArrayList.add("");
            case ("C"):
                numbersArrayList.set(enteredNumbersArrayListCounter, "");

        }
        label.setText(getUpperLabel() + "\n");
    }

    //Changes background color for = key
    public void changeBackgroundColorEquals(MouseEvent e){
        Button button = (Button) e.getTarget();
        if(e.getEventType().toString() == "MOUSE_ENTERED"){
            button.setStyle("-fx-background-color: #4D5358");
        }
        else{
            button.setStyle("-fx-background-color: #343332");
        }
    }

    //Changes background color for number keys
    public void changeBackgroundColorNumbers(MouseEvent e){
        Button button = (Button) e.getTarget();
        if(e.getEventType().toString() == "MOUSE_ENTERED"){
            button.setStyle("-fx-background-color: #4F4F4F");
        }
        else{
            button.setStyle("-fx-background-color: #070707");
        }
    }
    //Changes background color for functional keys
    public void changeBackgroundColorFuncKeys(MouseEvent e){
        Button button = (Button) e.getTarget();
        if(e.getEventType().toString() == "MOUSE_ENTERED") {
            button.setStyle("-fx-background-color: #4F4F4F");
        } else {
            button.setStyle("-fx-background-color: #161616");
        }
    }
}
