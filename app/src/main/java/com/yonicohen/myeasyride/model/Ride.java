package com.yonicohen.myeasyride.model;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * Ride model
 */
public class Ride implements Serializable {

    private String from_city;
    private String to_city;
    private String ride_date;
    private String ride_time;

    /**
     * Constructor
     * @param from_city ride from city
     * @param to_city ride to city
     * @param ride_date rife date
     * @param ride_time ride time (hh:mm)
     */
    public Ride(String from_city, String to_city, String ride_date, String ride_time) {
        this.from_city = from_city;
        this.to_city = to_city;
        this.ride_date = ride_date;
        this.ride_time = ride_time;
    }

    /**
     * get the source city of the ride
     * @return the source city of the ride
     */
    public String getFrom_city() {
        return from_city;
    }

    /**
     * set the source city of the ride
     * @param from_city the source city of the ride
     */
    public void setFrom_city(String from_city) { this.from_city = from_city; }

    /**
     * get the destination city of the ride
     * @return the destination city of the ride
     */
    public String getTo_city() { return to_city; }

    /**
     * set the destination city of the ride
     * @param to_city the destination city of the ride
     */
    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    /**
     * get the date of the ride
     * @return the date of the ride (String)
     */
    public String getRide_date() {
        return ride_date;
    }

    /**
     * set the date of the ride
     * @param ride_date the date of the ride (String)
     */
    public void setRide_date(String ride_date) {
        this.ride_date = ride_date;
    }

    /**
     * get the time of the ride
     * @return the time of the ride
     */
    public String getRide_time() {
        return ride_time;
    }

    /**
     * set the time of the ride
     * @param ride_time the time of the ride
     */
    public void setRide_time(String ride_time) {
        this.ride_time = ride_time;
    }

    /**
     * string format of ride
     * @return string format of the object
     */
    @NonNull
    @Override
    public String toString() {
        return "Ride{" +
                "from_city='" + from_city + '\'' +
                ", to_city='" + to_city + '\'' +
                ", ride_date='" + ride_date + '\'' +
                ", ride_time='" + ride_time + '\'' +
                '}';
    }
}