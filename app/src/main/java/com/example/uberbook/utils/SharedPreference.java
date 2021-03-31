package com.example.uberbook.schemas;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    static private SharedPreferences settings;
    static private SharedPreferences.Editor editor;

    public SharedPreference(Context context){
//       Object creation to get stored data of shared preference
        settings = context.getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
        editor = settings.edit();
    };

    public SharedPreference(Context context, User user){

//       Object creation to get stored data of shared preference
        settings = context.getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
         editor = settings.edit();

         this.removeAll();

         editor.putString("jwt", user.jwt);
         editor.putString("user", user.user.username);
         editor.putString("email", user.user.email);
         editor.putInt("id", user.user.id);

//      Push the modification
        editor.apply();

    }

    static boolean isLogged(){

        if(settings.contains("user")){
            return true;
        }else{
            return false;
        }
    }
    static void getUserData(User user){
//      Fill user object from data stored locally
        user.jwt = settings.getString("jwt", "");
        user.user.id = settings.getInt("id", 0);
        user.user.username = settings.getString("user", "");
        user.user.email = settings.getString("email", "");

    }

    static int getUserId(){  return(settings.getInt("id", 0)); }
    static String getUserName(){  return(settings.getString("user", "")); }
    static String getUserEmail(){  return(settings.getString("email", "")); }
    static String getJwt(){  return(settings.getString("jwt", "")); }


    static void setUserId(int id){
        if(settings.contains("id")){
            editor.remove("id");
        }

        editor.putInt("id", id);
        editor.apply();
    }

    static void setUserName(String user){
        if(settings.contains("user")){
            editor.remove("user");
        }

        editor.putString("user", user);
        editor.apply();
    }

    static void setJwt(String jwt){

        if(settings.contains("jwt")){
            editor.remove("jwt");
        }
        editor.putString("jwt", jwt);
        editor.apply();
    }

    static void setUserEmail(String email){

        if(settings.contains("email")){
            editor.remove("email");
        }

        editor.putString("email", email);
        editor.apply();
    }

    static void removeAll(){

        editor.remove("jwt");
        editor.remove("id");
        editor.remove("user");
        editor.remove("email");
        editor.apply();
    }
}
