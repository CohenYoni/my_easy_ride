package com.yonicohen.myeasyride.tools;

import com.yonicohen.myeasyride.R;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Get the resources of the app.
 * singleton class.
 */
public class AppResources {

    private static ArrayList<String> cities;

    /**
     * private constructor to ensure creating just one instance of the class
     */
    private AppResources() {}

    /**
     * Get ArrayList of the cities
     * @return string array list of cities
     */
    public static ArrayList<String> getCitiesList() {
        if (cities == null) {
            synchronized(AppResources.class) {
                if (cities == null) {
                    cities = new ArrayList<>(Arrays.asList(App.getContext().getResources().getStringArray(R.array.cities_array)));
                }
            }
        }
        return cities;
    }

    //TODO: add getStreetList and getPhonesList methods (from DB)
}
