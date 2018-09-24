package com.example.wajahat.foodbazaar.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.Database.FoodbazaarRepository;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {

    private FoodbazaarRepository mRepository;

    private LiveData<List<Items>> allItems;
    //private List<Categories> mAllCategories;

    public ItemsViewModel (Application application) {
        super(application);
        mRepository = new FoodbazaarRepository(application);
       allItems=mRepository.getAllItems();
       // mAllCategories=mRepository.getAllCategories();

    }

    public LiveData<List<Items>> getAllWords(){return allItems;}
    public void insert(Items item){
        mRepository.insert(item);
    }
    //public void insert_item(Items item) { mRepository.insert_item(item); }
    //public void insert_category(Categories categories){mRepository.insert_category(categories);}

}
