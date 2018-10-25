package com.example.wajahat.foodbazaar.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.wajahat.foodbazaar.DAO.CategoriesDao;
import com.example.wajahat.foodbazaar.DAO.ItemsDao;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;

import java.util.List;

public class FoodbazaarRepository {
    private ItemsDao itemsDao;
    private CategoriesDao categoriesDao;
    private LiveData<List<Items>> allItems;
    private LiveData<List<Categories>> allCategories;
    public FoodbazaarRepository(Application application){
        FoodbazaarDatabase db=FoodbazaarDatabase.getInstance(application);
        itemsDao=db.itemsDao();
        categoriesDao=db.categoriesDao();
        allItems=itemsDao.getAllItems();
        allCategories=categoriesDao.getAllCategories();

    }
    public LiveData<List<Items>> getAllItems() {
        return allItems;
    }

    public LiveData<List<Categories>> getAllCategories() {
        return allCategories;
    }

    public Items getItemById(int i) {return itemsDao.getItemById(i); }

    public void insertItem (Items item) {
        new insertAsyncTask_item(itemsDao).execute(item);
    }
    public void insertCategory(Categories categories){new insertAsyncTask_cat(categoriesDao).execute(categories);}




    private static class insertAsyncTask_item extends AsyncTask<Items, Void, Void> {

        private ItemsDao mAsyncTaskDao;

        insertAsyncTask_item(ItemsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Items... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask_cat extends AsyncTask<Categories, Void, Void> {

        private CategoriesDao mAsyncTaskDao;

        insertAsyncTask_cat(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Categories... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
