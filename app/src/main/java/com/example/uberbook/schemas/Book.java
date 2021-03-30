package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Book {
    @Expose
    @SerializedName("id")
    Integer id;

    @Expose
    @SerializedName("Title")
    String title;

    @Expose
    @SerializedName("Auteur")
    String auteur;

    @Expose
    @SerializedName("Description")
    String description;

    @Expose
    @SerializedName("ISBN")
    BigInteger isbn;
}
