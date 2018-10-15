package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;

public class WeatherGetter {
    private String cityTakenFromUser;
    private String cityURL;
    private String cityRegionFromChecker;
    private String[] regionsForChecker = new String[16];

    public void tekst(){
        System.out.println(cityRegionFromChecker);
    }

    //contructors
    public WeatherGetter() {
        cityTakenFromUser = "Katowice";
    }

    public WeatherGetter(String cityTakenFromUser) {
        this.cityTakenFromUser = cityTakenFromUser;
    }



    //*****************method checked if the city is real and in which region it is*****************//
    public boolean getCityFromUserCheck() throws Exception {

        //change to lower signs
        cityTakenFromUser = cityTakenFromUser.toLowerCase();

        //load file with regions
        FileReader file = new FileReader("C:/Users/Dawid/IdeaProjects/WAPP1.01/src/sample/Regions/cityFromRegions.txt");
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine().toLowerCase();

        int i = 1;                                  //init var count cities in choicebox

        //give results in which region city is
        while (line != null) {
            if (line.equals(cityTakenFromUser)) {
                if (i > 0 && i < 84) {
                    cityRegionFromChecker = "mazowieckie";
                    return true;
                }
                if (i > 83 && i < 153) {
                    cityRegionFromChecker = "śląskie";
                    return true;
                }
                if (i > 152 && i < 241) {
                    cityRegionFromChecker = "dolnośląskie";
                    return true;
                }
                if (i > 240 && i < 293) {
                    cityRegionFromChecker = "kujawsko-pomorskie";
                    return true;
                }
                if (i > 292 && i < 337) {
                    cityRegionFromChecker = "łódzkie";
                    return true;
                }
                if (i > 336 && i < 384) {
                    cityRegionFromChecker = "lubelskie";
                    return true;
                }
                if (i > 383 && i < 427) {
                    cityRegionFromChecker = "lubuskie";
                    return true;
                }
                if (i > 426 && i < 487) {
                    cityRegionFromChecker = "małopolskie";
                    return true;
                }
                if (i > 486 && i < 522) {
                    cityRegionFromChecker = "opolskie";
                    return true;
                }
                if (i > 521 && i < 573) {
                    cityRegionFromChecker = "podkarpackie";
                    return true;
                }
                if (i > 574 && i < 615) {
                    cityRegionFromChecker = "pomorskie";
                    return true;
                }
                if (i > 614 && i < 649) {
                    cityRegionFromChecker = "świętokrzyskie";
                    return true;
                }
                if (i > 648 && i < 698) {
                    cityRegionFromChecker = "warmińsko-mazurskie";
                    return true;
                }
                if (i > 697 && i < 806) {
                    cityRegionFromChecker = "wielkipolskie";
                    return true;
                }
                if (i > 805 && i < 871) {
                    cityRegionFromChecker = "zachodniopomorskie";
                    return true;
                }
                if (i > 870 && i < 908) {
                    cityRegionFromChecker = "kujawsko-pomorskie";
                    return true;
                }
            }
            i++;
            line = reader.readLine().toLowerCase();
        }
        return false;
    }

    public String getCityTakenFromUser()
    {
        return cityTakenFromUser;
    }

    public String getCityRegionFromChecker(){
        return cityRegionFromChecker;
    }

    public void setCityURL() {
        String city = ("http://www.pogodynka.pl/polska/" + cityTakenFromUser + "_" + cityTakenFromUser);
        cityURL = city;
    }

    public String cityNameFromUser() throws Exception {
        //miasto
        Document document = Jsoup.connect(cityURL).get();
        Elements html = document.select("span.sub-label-title");
        return (html.text());
    }

    //-JSOUP METHODS-//

    //temperature
    public String cityTemp() throws Exception {
        Document document = Jsoup.connect(cityURL).get();
        Elements html = document.select("div.teraz");
        return ("Temperatura " + html.text().substring(31, 34).replace(" ", "") +"°C");
    }

    //pressure
    public String cityPressure() throws Exception {
        Document document = Jsoup.connect(cityURL).get();
        Elements html = document.select("div.teraz");
        return ("Ciśnienie " + html.text().substring(63, 68).replace(" ", "") + " hPa");
    }

    //wind
    public String cityWind() throws Exception {
        Document document = Jsoup.connect(cityURL).get();
        Elements html = document.select("div.teraz");
        return ("Siła wiatru " + html.text().substring(88,90).replace(" ","")+" m/s");
    }

    //wind direction
    public String GetCityWindDirectionURL() throws Exception
    {
        Document document = Jsoup.connect(cityURL).get();
        Elements html = document.select("div.autodin");
        if (html.toString().substring(120, 121).equals("0"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\S.png");
        else if (html.toString().substring(120, 121).equals("1"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\SW.png");
        else if (html.toString().substring(120, 121).equals("2"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\W.png");
        else if (html.toString().substring(120, 121).equals("3"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\NW.png");
        else if (html.toString().substring(120, 121).equals("4"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\N.png");
        else if (html.toString().substring(120, 121).equals("5"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\NE.png");
        else if (html.toString().substring(120, 121).equals("6"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\E.png");
        else if (html.toString().substring(120, 121).equals("7"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\SE.png");
        else if (html.toString().substring(120, 121).equals("8"))
            return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WindArrows\\All.png");
    return ("error");
    }

    //image of current weather
    public String setWeatherConditionImage() throws Exception
    {

        int indexOfStarting;        //var of number of starting sings in text from JSOUP
        int indexOfEnding;          //var of number of endings sings in text from JSOUP
        String htmlToString;        //var to toString method

        Document document = Jsoup.connect("https://www.google.pl/search?safe=off&ei=iV5fW9zqIpLLsAHFtp2wCQ&q=pogoda+" + cityTakenFromUser + "&oq=pogoda+" + cityTakenFromUser + "&gs_l=psy-ab.3..35i39k1l2j0i131k1j0l7.7976.9239.0.9364.9.8.0.0.0.0.215.959.2j5j1.8.0....0...1c.1.64.psy-ab..1.8.950...0i67k1.0.zIBUModZISw").get();
        Elements html = document.select("img#wob_tci");

        htmlToString = html.toString();

        indexOfStarting = htmlToString.indexOf("/64/") + 4;
        indexOfEnding = htmlToString.indexOf(" id=" ) - 1;
        return ("C:\\Users\\Dawid\\IdeaProjects\\WAPP1.01\\src\\sample\\Images\\WeatherConditionImages\\" + html.toString().substring(indexOfStarting, indexOfEnding));
    }
}


