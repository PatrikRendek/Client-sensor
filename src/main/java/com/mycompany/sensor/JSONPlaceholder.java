/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sensor;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author hudik1
 */
public interface JSONPlaceholder {

    /**
     *
     * @return
     */
    @GET("/users")
    Call<List<User>> getUsers();

    /**
     *
     * @param id
     * @return
     */
    @GET("/users/{id}")
    Call<User> getUser(@Path("id") Integer id);

    /**
     *
     * @param user
     * @return
     */
    @POST("/users/create")
    Call<User> sendUser(@Body User user);

    /**
     *
     * @param id
     * @param userName
     * @return
     */
    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User userName);

    /**
     *
     * @param name
     * @param device
     * @return
     */
    @POST("/users/{name}/device/")
    Call<Integer> createDevice(@Path("name") String name, @Body UserDevice device);

    /**
     *
     * @param name
     * @param id
     * @return
     */
    @DELETE("/users/{name}/device/{id}")
    Call<Void> deleteDevice(@Path("name") String name, @Path("id") Integer id);
    
    /**
     *
     * @param name
     * @param id
     * @return
     */
    @GET("/users/{name}/device/{id}")
    Call<UserDevice> getDevice(@Path("name") String name, @Path("id") Integer id);

    /**
     *
     * @param name
     * @param id
     * @param device
     * @return
     */
    @PUT("/users/{name}/device/{id}")
    Call<Void> updateDevice(@Path("name") String name, @Path("id") Integer id, @Body UserDevice device);

}
