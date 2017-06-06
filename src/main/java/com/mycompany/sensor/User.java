
package com.mycompany.sensor;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author P
 */
public class User {

@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("userName")
@Expose
private String userName;
@SerializedName("userDevices")
@Expose
private List<UserDevice> userDevices = null;

/**
* No args constructor for use in serialization
* 
*/
public User() {
}

/**
* 
* @param userDevices
* @param userId
* @param userName
*/
public User(Integer userId, String userName, List<UserDevice> userDevices) {
super();
this.userId = userId;
this.userName = userName;
this.userDevices = userDevices;
}

    /**
     *
     * @return
     */
    public Integer getUserId() {
return userId;
}

    /**
     *
     * @param userId
     */
    public void setUserId(Integer userId) {
this.userId = userId;
}

    /**
     *
     * @return
     */
    public String getUserName() {
return userName;
}

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
this.userName = userName;
}

    /**
     *
     * @return
     */
    public List<UserDevice> getUserDevices() {
return userDevices;
}

    /**
     *
     * @param userDevices
     */
    public void setUserDevices(List<UserDevice> userDevices) {
this.userDevices = userDevices;
}

}