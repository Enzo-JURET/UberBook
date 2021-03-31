package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
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
