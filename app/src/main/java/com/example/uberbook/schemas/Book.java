package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @Expose
    @SerializedName("id")
    Integer id;

    @Expose
    @SerializedName("Title")
    String title;

    @Expose
    @SerializedName("Author")
    String author;

    @Expose
    @SerializedName("Description")
    String description;

    @Expose
    @SerializedName("ISBN")
    int isbn;

    @Expose
    @SerializedName("Borrower")
    UserData borrower;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public UserData getBorrower() {
        return borrower;
    }

    public void setBorrower(UserData borrower) {
        this.borrower = borrower;
    }
}
