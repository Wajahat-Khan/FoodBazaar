package com.example.wajahat.foodbazaar.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.Database.FoodbazaarRepository;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {

    private FoodbazaarRepository mRepository;

    private LiveData<List<Items>> mAllItems;
    private LiveData<List<Categories>> mAllCategories;

    public ItemsViewModel (Application application) {
        super(application);
        mRepository = new FoodbazaarRepository(application);
        mAllItems=mRepository.getAllItems();
        mAllCategories=mRepository.getAllCategories();

    }

    LiveData<List<Items>> getAllItems() { return mAllItems; }
    LiveData<List<Categories>> getAllCategories() { return mAllCategories; }
    public void insert_item(Items item) { mRepository.insert_item(item); }
    public void insert_category(Categories categories){mRepository.insert_category(categories);}
}