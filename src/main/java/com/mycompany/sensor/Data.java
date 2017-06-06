
package com.mycompany.sensor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author P
 */
public class Data {

@SerializedName("temperature")
@Expose
private String temperature;
@SerializedName("date")
@Expose
private String date;

/**
* No args constructor for use in serialization
* 
*/
public Data() {
}

/**
* 
* @param date
* @param temperature
*/
public Data(String temperature, String date) {
super();
this.temperature = temperature;
this.date = date;
}

    /**
     *
     * @return
     */
    public String getTemperature() {
return temperature;
}

    /**
     *
     * @param temperature
     */
    public void setTemperature(String temperature) {
this.temperature = temperature;
}

    /**
     *
     * @return
     */
    public String getDate() {
return date;
}

    /**
     *
     * @param date
     */
    public void setDate(String date) {
this.date = date;
}

}