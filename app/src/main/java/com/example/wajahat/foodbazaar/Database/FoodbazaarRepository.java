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

FoodbazaarRepository(Application application){
    FoodbazaarDatabase db=FoodbazaarDatabase.getDatabase(application);
    itemsDao=db.itemsDao();
    categoriesDao=db.categoriesDao();
}
LiveData<List<Items>> getAllItems(){
    return  allItems;
}
LiveData<List<Categories>> getAllCategories(){
    return allCategories;
}
public void insert_item(Items item){
    new insertAsyncTaskItem(itemsDao).execute(item);
}
    private static class insertAsyncTaskItem extends AsyncTask<Items, Void, Void> {

        private ItemsDao mAsyncTaskDao;

        insertAsyncTaskItem(ItemsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Items... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert_category(Categories categories){
        new insertAsyncTaskCategory(categoriesDao).execute(categories);
    }

    private static class insertAsyncTaskCategory extends AsyncTask<Categories, Void, Void> {

        private CategoriesDao mAsyncTaskDao;

        insertAsyncTaskCategory(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Categories... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
