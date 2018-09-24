package com.example.wajahat.foodbazaar.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.wajahat.foodbazaar.DAO.ItemsDao;
import com.example.wajahat.foodbazaar.Data.Items;

@Database(entities = {Items.class}, version = 1)
public abstract class FoodbazaarDatabase extends RoomDatabase {
    public abstract ItemsDao itemsDao();

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

        private final ItemsDao mDao;

        PopulateDbAsync(FoodbazaarDatabase db) {
            mDao = db.itemsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.delete_all();
            Items item = new Items(3,"ChickenBurger","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","burgers",4,"chicken,bun,salad");
            mDao.insert(item);
            item = new Items(4,"Beef Burger","a good burger","A very good burger. good burger",250,true,null
                    ,null,"chicken","burgers",4,"chicken,bun,salad");
            mDao.insert(item);
            return null;
        }
    }
}
