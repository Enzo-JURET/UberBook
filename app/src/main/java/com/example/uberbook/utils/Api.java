package com.example.uberbook.utils;

import com.example.uberbook.interfaces.ApiService;
import com.example.uberbook.schemas.Book;
import com.example.uberbook.schemas.Login;
import com.example.uberbook.schemas.Register;
import com.example.uberbook.schemas.User;
import com.example.uberbook.schemas.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://telougat.space:4030/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void login(String username, String password, Callback<User> callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.login(new Login(username, password));
        call.enqueue(callback);
    }

    public void register(Register registerSchema, Callback<User> callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.register(registerSchema);
        call.enqueue(callback);
    }

    public void getBooks(User user, Callback<List<Book>> callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Book>> call = apiService.listBooks(user.getFormattedJwt());
        call.enqueue(callback);
    }

    public void addBooks(User user, Book book, Callback<Book> callback) {
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Book> call = apiService.addBook(user.getFormattedJwt(), book);
        call.enqueue(callback);
    }
}
