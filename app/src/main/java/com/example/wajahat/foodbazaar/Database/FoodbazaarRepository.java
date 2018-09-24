package com.example.wajahat.foodbazaar.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.wajahat.foodbazaar.DAO.ItemsDao;
import com.example.wajahat.foodbazaar.Data.Items;

import java.util.List;

public class FoodbazaarRepository {
    private ItemsDao itemsDao;
    private LiveData<List<Items>> allItems;
    public FoodbazaarRepository(Application application){
        FoodbazaarDatabase db=FoodbazaarDatabase.getInstance(application);
        itemsDao=db.itemsDao();
        allItems=itemsDao.getAllItems();

    }
    public LiveData<List<Items>> getAllItems() {
        return allItems;
    }
    public void insert (Items item) {
        new insertAsyncTask(itemsDao).execute(item);
    }
    private static class insertAsyncTask extends AsyncTask<Items, Void, Void> {

        private ItemsDao mAsyncTaskDao;

        insertAsyncTask(ItemsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Items... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
