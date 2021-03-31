package com.example.uberbook.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.uberbook.schemas.Book;

public class TableControllerBook extends DatabaseHandler {

    public TableControllerBook(Context context){
        super(context);
    }

    public boolean create(Book book){

        ContentValues values = new ContentValues();
        values.put("Title", book.getTitle());
        values.put("Author", book.getAuthor());
        values.put("description", book.getDescription());
        values.put("Isbn", book.getIsbn());
//        values.put("Status", book.getStatus);


        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessFul = db.insert("books", null, values)>0;
        db.close();
        return createSuccessFul;
    }
}
