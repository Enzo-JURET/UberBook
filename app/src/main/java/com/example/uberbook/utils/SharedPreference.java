package com.example.uberbook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.uberbook.schemas.User;
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

    public static void buildSharedPreference(Context context, User user) throws JSONException {

//       Object creation to get stored data of shared preference
        settings = context.getSharedPreferences("user", 0);
//       Create the Editor object to modify the shared preference
         editor = settings.edit();

//       Delete all existing shared preference  key/data
         removeAll();

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

    public static boolean isLogged(){

        if(settings.contains("jwt")){
            return true;
        }else{
            return false;
        }
    }

    public static User getUserData() throws JSONException {
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
