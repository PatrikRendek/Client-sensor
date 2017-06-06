
package com.mycompany.sensor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author P
 */
public class UserDevice {

@SerializedName("deviceId")
@Expose
private Integer deviceId;
@SerializedName("data")
@Expose
private Data data;

/**
* No args constructor for use in serialization
* 
*/
public UserDevice() {
}

/**
* 
* @param data
* @param deviceId
*/
public UserDevice(Integer deviceId, Data data) {
super();
this.deviceId = deviceId;
this.data = data;
}

    /**
     *
     * @return
     */
    public Integer getDeviceId() {
return deviceId;
}

    /**
     *
     * @param deviceId
     */
    public void setDeviceId(Integer deviceId) {
this.deviceId = deviceId;
}

    /**
     *
     * @return
     */
    public Data getData() {
return data;
}

    /**
     *
     * @param data
     */
    public void setData(Data data) {
this.data = data;
}

}