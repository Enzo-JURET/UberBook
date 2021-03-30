package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    public Login(String username, String password) {
        this.identifier = username;
        this.password = password;
    }

    @Expose
    @SerializedName("identifier")
    String identifier;

    @Expose
    @SerializedName("password")
    String password;
}
