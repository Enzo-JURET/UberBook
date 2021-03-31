package com.example.uberbook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.uberbook.schemas.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import okio.GzipSource;

public class SharedPreference {

    static private SharedPreferences settings;
    static private SharedPreferences.Editor editor;

    public SharedPreference(Context context){
//       Object creation to get stored data of shared preference
        settings = context.getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
        editor = settings.edit();
    };

    public SharedPreference(Context context, User user) throws JSONException {

//       Object creation to get stored data of shared preference
        settings = context.getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
         editor = settings.edit();

//       Delete all existing shared preference  key/data
         this.removeAll();

         Gson gson = new GsonBuilder().setPrettyPrinting().create();
//       Convert to String with json format
         String resultJson = gson.toJson(user);
//       Convert to JSON object
        JSONObject jsonOb = new JSONObject(resultJson);

         editor.putString("jwt", jsonOb.getString("jwt"));
         editor.putString("username", jsonOb.getString("username"));
         editor.putString("email", jsonOb.getString("email"));
         editor.putInt("id", jsonOb.getInt("id"));

//      Push the modification
        editor.apply();

    }

    static boolean isLogged(){

        if(settings.contains("jwt")){
            return true;
        }else{
            return false;
        }
    }

    static User getUserData() throws JSONException {
//        Build string with json format
        String userString = new JSONObject()
                .put("jwt", settings.getString("jwt", ""))
                .put("id", settings.getInt("id", 0))
                .put("username", settings.getString("username", ""))
                .put("email", settings.getString("email", ""))
                .toString();

        Gson gson = new Gson();
        return(gson.fromJson(userString, User.class));
    }

    static int getUserId(){  return(settings.getInt("id", 0)); }
    static String getUserName(){  return(settings.getString("username", "")); }
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
        if(settings.contains("username")){
            editor.remove("username");
        }

        editor.putString("username", user);
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
        editor.remove("username");
        editor.remove("email");
        editor.apply();
    }
}
