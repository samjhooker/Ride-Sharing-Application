package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Car;

import javax.xml.crypto.Data;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static controllers.DataStore.cars;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class RegisterCarController implements Initializable {

    @FXML
    private TextField makeTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField colorTextField;
    @FXML
    private TextField licenceTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField seatsTextField;
    @FXML
    private DatePicker registrationExpiryDatePicker;
    @FXML
    private DatePicker wofExpiryDatePicker;
    @FXML
    private TextField litresPer100kmTextField;


    @FXML
    private Label statusLabel;


    @FXML
    private Label updateStatusLabel;
    @FXML
    private DatePicker updateRegistrationExpiryDatePicker;
    @FXML
    private DatePicker updateWofExpiryDatePicker;
    @FXML
    private TextField updateLitresPer100kmTextField;
    @FXML
    private ListView carsListView;
    @FXML
    private Label makeAndModelLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label licenceLabel;
    @FXML
    private Label yearLabel;

    private Car selectedCar = null;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateCarsList();

        carsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedCar = (Car)newValue;
                makeAndModelLabel.setText(selectedCar.toString());
                colorLabel.setText(selectedCar.getColor());
                licenceLabel.setText(selectedCar.getLicence());
                yearLabel.setText("" + selectedCar.getYear());
                updateLitresPer100kmTextField.setText("" + selectedCar.getLitresPer100km());


            }
        });

    }

    private void updateCarsList(){
        carsListView.setItems(DataStore.getCars());
    }



    @FXML
    public void submitButtonPressed(){

        if(!makeTextField.getText().isEmpty() &&
            !modelTextField.getText().isEmpty() &&
                !colorTextField.getText().isEmpty() &&
                !licenceTextField.getText().isEmpty() &&
                !yearTextField.getText().isEmpty() &&
                !seatsTextField.getText().isEmpty() &&
                registrationExpiryDatePicker.getValue() != null &&
                wofExpiryDatePicker.getValue() != null &&
                DataStore.currentUser != null &&
                !litresPer100kmTextField.getText().isEmpty()
                )
        {

            try{
                Car car = new Car(
                        makeTextField.getText(),
                        modelTextField.getText(),
                        colorTextField.getText(),
                        licenceTextField.getText(),
                        Integer.parseInt(yearTextField.getText()),
                        Integer.parseInt(seatsTextField.getText()),
                        wofExpiryDatePicker.getValue(),
                        registrationExpiryDatePicker.getValue(),
                        DataStore.currentUser.getUserId(),
                        Double.parseDouble(litresPer100kmTextField.getText())

                );
                cars.add(car);

                System.out.println(cars);


                makeTextField.setText("");
                modelTextField.setText("");
                colorTextField.setText("");
                licenceTextField.setText("");
                yearTextField.setText("");
                seatsTextField.setText("");

                statusLabel.setText("Successful ");

                DataStore.saveData();
                updateCarsList();



            }catch (Exception e){
                statusLabel.setText("Input Invalid");
            }


        }else{
            statusLabel.setText("All Input Required");
        }





    }


    @FXML
    private void updateSubmitButtonPressed(){

    }
}
