package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //FXML controllers
    @FXML
    private Label cityLabel;                    //label with city text
    @FXML
    private ChoiceBox<String> cityChoiceBox;    //choicebox with ciies
    @FXML
    private Button cityChoiceButton;
    @FXML
    private Button cityAdd;                     //button to add city to choice box
    @FXML
    private TextField cityAddTextField;         //text field where user write city name
    @FXML
    private Label cityTemperatureNow;           //label with temperature info text
    @FXML
    private Label cityPressureNow;              //label with pressure info text
    @FXML
    private Label cityWindNow;                  //label with wind speed info text
    @FXML
    private ImageView cityWeatherConditionImageNow; //image with weather condition
    @FXML
    private ImageView imageWindDirection;       //image with wind direction

    //variables
    private String cityFromTextField;           //var to contain city name from textfield
    private String cityNameObject = "katowice"; //first default city in weather app
    int numbersOfAddedCities = 2;               //counter of added cities, at the init in choicebox are 3 cities by default
    TabOfCitiesInChoiceBox tabOfCitiesInChoiceBox = new TabOfCitiesInChoiceBox();


    public void setCityNameObject(String cityNameObject) {
        this.cityNameObject = cityNameObject;
    }

    public String getCityNameObject() {
        return cityNameObject;
    }


    //*****************init*****************//
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityChoiceBox.getItems().addAll("Katowice", "Wrocław", "Warszawa");
        cityChoiceBox.setValue(getCityNameObject());
        cityChoiceBox.setValue("Katowice");

        //init object contain tab of cities in choicebox
        TabOfCitiesInChoiceBox tabOfCitiesInChoiceBox = new TabOfCitiesInChoiceBox();

        //init of default city (Katowice)
        init();
    }

    //*****************init new city object*****************//
    public void init() {
        WeatherGetter katowice = new WeatherGetter(getCityNameObject());
        CitiesChanger.cityChangeAction(katowice);

        try {
            cityLabel.setText(katowice.getCityTakenFromUser() + " " + katowice.getCityRegionFromChecker());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //labelsNow
        try {
            cityTemperatureNow.setText(katowice.cityTemp());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cityPressureNow.setText(katowice.cityPressure());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cityWindNow.setText(katowice.cityWind());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //images
        FileInputStream input = null;
        try {
            input = new FileInputStream(katowice.setWeatherConditionImage());
            Image imageWeatherCondition = new Image(input);
            cityWeatherConditionImageNow.setImage(imageWeatherCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        input = null;
        try {
            input = new FileInputStream(katowice.GetCityWindDirectionURL());
            Image imageWindDirectionNow = new Image(input);
            imageWindDirection.setImage(imageWindDirectionNow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonSwitchCity(ActionEvent event) {
        String cityAction = cityChoiceBox.getValue();
        setCityNameObject(cityAction);
        init();
    }

    @FXML
    private void buttonAddCity(ActionEvent event) throws Exception {
        cityFromTextField = cityAddTextField.getText();
        boolean checker = tabOfCitiesInChoiceBox.compareCityWithTab(cityFromTextField);

        if (checker==true)
        {
            WeatherGetter wg = new WeatherGetter(cityFromTextField);
            boolean checkerTabCitiesInChoiceBox = CitiesChanger.cityChangeAction(wg);
            if (checkerTabCitiesInChoiceBox == true) {
                setCityNameObject(cityFromTextField);
                String name = CitiesChanger.makeCityGreatLookName(cityFromTextField);
                cityChoiceBox.getItems().add(name);
                cityChoiceBox.setValue(name);
                init();
            }
            else {
                ControllerCityNotFoundWindow controllerCityNotFoundWindow = new ControllerCityNotFoundWindow();
                controllerCityNotFoundWindow.WindowInfoStart();
            }
        }
        else
            {
                ControllerCityNotFoundWindow controllerCityNotFoundWindow = new ControllerCityNotFoundWindow();
                controllerCityNotFoundWindow.WindowInfoStart();
        }

        //clean textfield after adding city
        cityAddTextField.setText("");


    }
}

