package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    @SerializedName("jwt")
    String jwt;

    @Expose
    @SerializedName("user")
    UserData user;

    public String getFormattedJwt() {
        return "Bearer " + jwt;
    }

}
