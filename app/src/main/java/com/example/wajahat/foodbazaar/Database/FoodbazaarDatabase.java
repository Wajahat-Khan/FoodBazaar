package com.example.wajahat.foodbazaar.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.wajahat.foodbazaar.DAO.CategoriesDao;
import com.example.wajahat.foodbazaar.DAO.ItemsDao;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;

@Database(entities = {Items.class, Categories.class}, version = 2)
public abstract class FoodbazaarDatabase extends RoomDatabase {
    public abstract ItemsDao itemsDao();
    public abstract CategoriesDao categoriesDao();

    private static FoodbazaarDatabase Instance;
    public static FoodbazaarDatabase getInstance(final Context context){
        if(Instance==null){
            synchronized (FoodbazaarDatabase.class){
                if(Instance==null){
                    Instance= Room.databaseBuilder(context.getApplicationContext(),FoodbazaarDatabase.class,"foodbazaar").build();
                }
            }
        }
        return Instance;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(Instance).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ItemsDao itemsDao;
        private final CategoriesDao categoriesDao;

        PopulateDbAsync(FoodbazaarDatabase db) {
            itemsDao = db.itemsDao();
            categoriesDao=db.categoriesDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            itemsDao.deleteAll();
            categoriesDao.deleteAll();
            Items item = new Items(3,"Chicken Burger","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","burgers",4,"chicken,bun,salad");
            itemsDao.insert(item);
            item = new Items(4,"Beef Burger","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","burgers",4,"chicken,bun,salad");
            itemsDao.insert(item);
            item = new Items(1,"Chicken Sandwich","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","sandwich",4,"chicken,bun,salad");
            itemsDao.insert(item);
            item = new Items(2,"Beef Sandwich","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","sandwich",4,"chicken,bun,salad");
            itemsDao.insert(item);

            Categories categories=new Categories(1,"Burgers","Chicken Burger,Beef Burger,Fish Burger");
            categoriesDao.insert(categories);
            categories=new Categories(2,"Sandwiches","Chicken Sandwich,Beef Sandwich,Fish Sandwich");
            categoriesDao.insert(categories);
            return null;
        }
    }
}
