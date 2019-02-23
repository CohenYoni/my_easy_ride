package com.yonicohen.myeasyride.model;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * Passenger model
 */
public class Passenger implements Serializable {

    private String phone_number;
    private String first_name;
    private String last_name;
    private String city;
    private String street;
    private int apt_number;
    private float rating;

    /**
     * Constructor
     * @param phone_number the phone number of the passenger (primary key in DB)
     * @param first_name the first name of the passenger
     * @param last_name the last name of the passenger
     * @param city the city of the passenger
     * @param street the street of the passenger
     * @param apt_number the apartment number of the passenger
     * @param rating the rating of the passenger
     */
    public Passenger(String phone_number, String first_name, String last_name, String city, String street, int apt_number, int rating) {
        this.phone_number = phone_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.street = street;
        this.apt_number = apt_number;
        this.rating = rating;
    }

    /**
     * get the phone number
     * @return string of phone number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * set the phone number of the passenger
     * @param phone_number the phone number of the passenger
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * get the first name of the passenger
     * @return the first name of the passenger
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * set the first name of the passenger
     * @param first_name the first name of the passenger
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * get the last name of the passenger
     * @return the last name of the passenger
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * set the last name of the passenger
     * @param last_name the last name of the passenger
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * get the city of the passenger
     * @return the city of the passenger
     */
    public String getCity() {
        return city;
    }

    /**
     * set the city of the passenger
     * @param city the city of the passenger
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * get the street of the passenger
     * @return the street of the passenger
     */
    public String getStreet() {
        return street;
    }

    /**
     * set the street of the passenger
     * @param street the street of the passenger
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * get the apartment number of the passenger
     * @return the apartment number of the passenger
     */
    public int getApt_number() {
        return apt_number;
    }

    /**
     * set the apartment number of the passenger
     * @param apt_number the apartment number of the passenger
     */
    public void setApt_number(int apt_number) {
        this.apt_number = apt_number;
    }

    /**
     * get the rating of the passenger
     * @return the rating of the passenger
     */
    public float getRating() {
        return rating;
    }

    /**
     * set the rating of the passenger
     * @param rating the rating of the passenger
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * string format of passenger
     * @return string format of the object
     */
    @NonNull
    @Override
    public String toString() {
        return "Passenger{" +
                "phone_number='" + phone_number + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", apt_number=" + apt_number +
                ", rating=" + rating +
                '}';
    }
}