package com.example.wajahat.foodbazaar.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.wajahat.foodbazaar.DAO.CategoriesDao;
import com.example.wajahat.foodbazaar.DAO.ItemsDao;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;

@Database(entities = {Items.class, Categories.class}, version = 1)
public abstract class FoodbazaarDatabase extends RoomDatabase {

    public abstract ItemsDao itemsDao();
    public abstract CategoriesDao categoriesDao();

    private static volatile FoodbazaarDatabase INSTANCE;

    static FoodbazaarDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodbazaarDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodbazaarDatabase.class, "database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}