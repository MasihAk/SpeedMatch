package com.example.masih.speedmatch;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MySharePrefrences {

    private static MySharePrefrences instance = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null ;

    public static MySharePrefrences getInstance(Context context){
        if(instance == null){
            instance = new MySharePrefrences(context);
        }
        return instance;
    }

    private MySharePrefrences(Context context) {
        sharedPreferences = context.getSharedPreferences("my_prefrences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public int getHighscore(){
        return sharedPreferences.getInt("high_score", 0);
    }

    public void putHighscore(int highscore){
        editor.putInt("high_score", highscore);
        editor.apply();

    }

    public User getBestUser(){
        Gson gson = new Gson();
        String bestUser = sharedPreferences.getString("best_user",null);

        if(bestUser == null){
            return null;
        }

        return gson.fromJson(bestUser,User.class);
    }

    public void putBestUser(User user){
        Gson gson = new Gson();
        String bestUser = gson.toJson(user,User.class);
        editor.putString("best_user",bestUser);
        editor.apply();
    }





}
