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

    private LiveData<List<Items>> allItems;
    private LiveData<List<Categories>> AllCategories;

    public LiveData<List<Items>> getAllItems(){return allItems;}
    public LiveData<List<Categories>> getAllCategories(){return AllCategories;}
    //public void insert_item(Items item) { mRepository.insertItem(item); }
    //public void insert_category(Categories categories){mRepository.insertCategory(categories);}

    public ItemsViewModel (Application application) {
        super(application);
        mRepository = new FoodbazaarRepository(application);
       allItems=mRepository.getAllItems();
       AllCategories=mRepository.getAllCategories();

    }



}
