package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCityNotFoundWindow
{

    @FXML
    private Label labelInfo;

    public Stage stage;

    public void WindowInfoStart() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cityNotFoundWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        stage.setAlwaysOnTop(true);
    }


    public void CityNotFound()
    {
        labelInfo.setText("Nie znaleziono takiego miasta w bazie.");
    }
}
