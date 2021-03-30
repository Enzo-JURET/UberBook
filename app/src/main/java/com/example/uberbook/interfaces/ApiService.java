package com.example.uberbook.interfaces;

import com.example.uberbook.schemas.Book;
import com.example.uberbook.schemas.Login;
import com.example.uberbook.schemas.Register;
import com.example.uberbook.schemas.User;
import com.example.uberbook.schemas.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/local")
    Call<User> login(@Body Login loginSchema);

    @POST("auth/local/register")
    Call<User> register(@Body Register registerSchema);

    @GET("books")
    Call<List<Book>> listBooks(@Header("Authorization") String formattedJwt);

    @POST("books")
    Call<Book> addBook(@Header("Authorization") String formattedJwt, @Body Book book);
}
