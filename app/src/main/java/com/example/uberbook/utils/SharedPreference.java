package com.example.uberbook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.uberbook.schemas.User;
import com.example.uberbook.schemas.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;


public class SharedPreference {

    static private SharedPreferences settings;
    static private SharedPreferences.Editor editor;

    static {
//       Object creation to get stored data of shared preference
        settings = App.getAppContext().getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
        editor = settings.edit();
    };

    public static void buildSharedPreference(User user) {

//       Object creation to get stored data of shared preference
         settings = App.getAppContext().getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
         editor = settings.edit();

//       Delete all existing shared preference  key/data
         removeAll();

         editor.putString("jwt", user.getJwt());
         editor.putString("username", user.getUser().getUsername());
         editor.putString("email", user.getUser().getEmail());
         editor.putInt("id", user.getUser().getId());

//      Push the modification
        editor.apply();

    }

    public static boolean isLogged(){
        return settings.contains("jwt");
    }

    public static User getUser() {
//        Build string with json format
        User user = new User();
        UserData userData = new UserData();

        userData.setEmail(settings.getString("email", null));
        userData.setId(settings.getInt("id", -1));
        userData.setUsername(settings.getString("email", null));

        user.setJwt(settings.getString("jwt", null));
        user.setUser(userData);

        return user;
    }

    public static int getUserId(){  return(settings.getInt("id", 0)); }
    public static String getUserName(){  return(settings.getString("username", "")); }
    public static String getUserEmail(){  return(settings.getString("email", "")); }
    public static String getJwt(){  return(settings.getString("jwt", "")); }


    public static void setUserId(int id){
        if(settings.contains("id")){
            editor.remove("id");
        }

        editor.putInt("id", id);
        editor.apply();
    }

    public static void setUserName(String user){
        if(settings.contains("username")){
            editor.remove("username");
        }

        editor.putString("username", user);
        editor.apply();
    }

    public static void setJwt(String jwt){

        if(settings.contains("jwt")){
            editor.remove("jwt");
        }
        editor.putString("jwt", jwt);
        editor.apply();
    }

    public static void setUserEmail(String email){

        if(settings.contains("email")){
            editor.remove("email");
        }

        editor.putString("email", email);
        editor.apply();
    }

    public static void removeAll(){

        editor.remove("jwt");
        editor.remove("id");
        editor.remove("username");
        editor.remove("email");
        editor.apply();
    }
}
