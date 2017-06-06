/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sensor;

import java.sql.Timestamp;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author P
 */
public class Main {

    private static double temperatureFinal;
    private static String temperatureFinalString;

    private static boolean userExists;
    private String userID;
    private static String userName;
    private static User deviceUser = new User();
    private static Data deviceData = new Data();
    private static UserDevice userDevice = new UserDevice();
    private static transient final List<User> usersList = new CopyOnWriteArrayList<User>();

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new SimpleThread().start();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder service = retrofit.create(JSONPlaceholder.class);

        Call<List<User>> users = service.getUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> rspns) {
                List<User> users = rspns.body();
                if (!users.isEmpty()) {
                    for (User user : users) {
                        usersList.add(user);
                        System.out.println(user.getUserName() + " " + user.getUserId());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable thrwbl) {
                Logger.getLogger("").log(Level.SEVERE, "Chyba", thrwbl);
            }
        });
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);

        while (true) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Iterator<User> iter = usersList.iterator();
            if (!iter.hasNext() && loginForm.getClicked()) {
                userExists = false;

            } else {

                while (iter.hasNext()) {
                    User blah = iter.next();
                    if (Objects.equals(blah.getUserName(), loginForm.getUserName())) {
                        userExists = true;
                        break;
                    } else {
                        userExists = false;
                    }
                }

            }

            if (userExists == false && loginForm.getClicked() == true) {
                userName = loginForm.getUserName();
                deviceUser.setUserName(userName);
                System.out.println(userName);
                deviceUser.setUserId(6);
                deviceData.setDate(timestamp.toString());
                deviceData.setTemperature("none");
                userDevice.setData(deviceData);
                Call<User> newUserP = service.sendUser(deviceUser);
                Response<User> execNewPost = newUserP.execute();
                break;

            }
            if (userExists == true && loginForm.getClicked() == true) {
                userName = loginForm.getUserName();
                deviceUser.setUserName(userName);
              
                break;

            }
        }
        TemperatureForm tForm = new TemperatureForm();
        tForm.setVisible(true);
        loginForm.setVisible(false);
  
        Call<Integer> newDev = service.createDevice(deviceUser.getUserName(), userDevice);
        newDev.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> rspns) {
                Integer dev = rspns.body();
                userDevice.setDeviceId(dev);
                System.out.println(dev);
                while (true) {

                    Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
                    temperatureFinalString = String.valueOf(temperatureFinal);
                    tForm.setTextLabel("Device ID: " + userDevice.getDeviceId()+"  User: "+deviceUser.getUserName(), "Temperature:  " + temperatureFinalString + " °C");
                    deviceData.setTemperature(temperatureFinalString);
                    deviceData.setDate(timestamp2.toString());
                    userDevice.setData(deviceData);
                    Call<Void> newUserP2 = service.updateDevice(deviceUser.getUserName(), userDevice.getDeviceId(), userDevice);
                    try {
                        Response<Void> execNewPost2 = newUserP2.execute();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(deviceData.getTemperature() + "   " + deviceData.getDate());
                    System.out.println(tempM());
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable thrwbl) {
                Logger.getLogger("").log(Level.SEVERE, "Chyba", thrwbl);
            }
        });

    }

    /**
     *
     * @return
     */
    static public double tempM() {
        int min = 0;
        int max = 350;
        boolean change = false;

        double temperature;
        int[] randomF = new int[2];
        if (temperatureFinal != 0) {
            temperature = temperatureFinal * 10;
        } else {
            temperature = randomF[0] = ThreadLocalRandom.current().nextInt(min, max);
        }

        while (true) {

            randomF[0] = ThreadLocalRandom.current().nextInt(min, max);
            if ((temperature > randomF[0]) && (temperature == randomF[0] + 1)) {

                temperature = randomF[0];
                change = true;
            } else {
                change = false;
            }
            if ((temperature < randomF[0]) && (temperature == randomF[0] - 1)) {
                temperature = randomF[0];
                change = true;
            } else {
                change = false;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (change) {
                temperatureFinal = temperature / 10;
                return temperature / 10;
            }

        }
    }

}
