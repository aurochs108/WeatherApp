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
    //atrybuty w aplikacji
    @FXML
    private Label cityLabel;
    @FXML
    private ChoiceBox<String> cityChoiceBox;
    @FXML
    private Button cityChoiceButton;
    @FXML
    private Button cityAdd;
    @FXML
    private TextField cityAddTextField;
    @FXML
    private Label cityTemperatureNow;
    @FXML
    private Label cityPressureNow;
    @FXML
    private Label cityWindNow;
    @FXML
    private ImageView cityWeatherConditionImageNow;
    @FXML
    private ImageView imageWindDirection;

    //zmienne
    private String cityFromTextField;
    private String cityNameObject = "katowice";
    //licznik dodanych miast przez pole do wpisywania
    int numbersOfAddedCities = 2;
    TabOfCitiesInChoiceBox tabOfCitiesInChoiceBox = new TabOfCitiesInChoiceBox();

    //funkcje
    public void setCityNameObject(String cityNameObject) {
        this.cityNameObject = cityNameObject;
    }

    public String getCityNameObject() {
        return cityNameObject;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityChoiceBox.getItems().addAll("Katowice", "Wrocław", "Warszawa");
        cityChoiceBox.setValue(getCityNameObject());
        cityChoiceBox.setValue("Katowice");
        //inicjacja obiektu zawierajacego tablice miast w choicebox
        TabOfCitiesInChoiceBox tabOfCitiesInChoiceBox = new TabOfCitiesInChoiceBox();
        //inicjalizacja dla miasta początkowego czyli Katowic
        init();
    }

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

        //imagesNow
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
        cityAddTextField.setText("");


    }
}

