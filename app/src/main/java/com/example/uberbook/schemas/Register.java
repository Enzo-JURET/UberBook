package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {
    @Expose
    @SerializedName("username")
    String username;

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("password")
    String password;
}
