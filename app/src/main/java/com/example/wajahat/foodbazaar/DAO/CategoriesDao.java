package com.example.wajahat.foodbazaar.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.wajahat.foodbazaar.Data.Categories;

import java.util.List;

@Dao
public interface CategoriesDao {

    @Insert
    public void insert(Categories categories);

    @Query("Select * from Categories")
    public List<Categories> getAllCategories();

    @Query("SELECT * FROM Categories WHERE id like :id")
    public Categories getCategoryById(int id);

    @Query("SELECT * FROM Categories WHERE name like :name")
    public Categories getCategoryByName(String name);

}
