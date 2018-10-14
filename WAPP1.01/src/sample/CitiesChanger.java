package sample;


public class CitiesChanger
{
    static boolean cityChangeAction(WeatherGetter wg) {
        //inicjalizacja URL
        wg.setCityURL();
        //sprawdzenie w jakim regionie jest miasto
        try {
            boolean checker = wg.getCityFromUserCheck();
            if(checker==true){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    static String makeCityGreatLookName(String name)
    {
        String firstLetter;
        String restOfTheLetters;
        String buffor;
        int numbersOfLetters;

        numbersOfLetters = (name.length());
        firstLetter=name.substring(0,1).toUpperCase();
        restOfTheLetters=name.substring(1,numbersOfLetters).toLowerCase();
        buffor=firstLetter+restOfTheLetters;
        return buffor;
    }
}


