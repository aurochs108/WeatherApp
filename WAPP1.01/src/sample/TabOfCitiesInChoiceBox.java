package sample;

import java.io.IOException;

public class TabOfCitiesInChoiceBox {
    int numberOfCitiesInCityChoiceBox=2;
    CitiesChanger citiesChanger = new CitiesChanger();
    public String[] tabOfCitiesNames = new String[9];

    public TabOfCitiesInChoiceBox() {
        this.tabOfCitiesNames[0] = "Katowice";
        this.tabOfCitiesNames[1] = "Kraków";
        this.tabOfCitiesNames[2] = "Wrocław";
    }



    public void addCity(String city)
    {
        numberOfCitiesInCityChoiceBox++;
        tabOfCitiesNames[numberOfCitiesInCityChoiceBox]=city;
    }
    public void deleteCity(String city)
    {
        //ogarnijokienko
        if(numberOfCitiesInCityChoiceBox<1) {
            System.out.println("ogarnij okienko");
        }
        else {
            tabOfCitiesNames[numberOfCitiesInCityChoiceBox] = null;
            numberOfCitiesInCityChoiceBox--;
        }
    }

    public boolean compareCityWithTab(String city) throws IOException {
        city = citiesChanger.makeCityGreatLookName(city);

        for(int i=0; i<=numberOfCitiesInCityChoiceBox; i++)
        {
            if(city.equals(tabOfCitiesNames[i])==true)
            {
                return false;
            }
        }
        numberOfCitiesInCityChoiceBox++;
        tabOfCitiesNames[numberOfCitiesInCityChoiceBox] = city;
        return true;
    }
}
