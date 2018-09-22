package com.example.wajahat.foodbazaar.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.wajahat.foodbazaar.Data.Items;

import java.util.List;

@Dao
public interface ItemsDao {
@Insert
    public void insert(Items item);

@Query("Select * from Items")
    public List<Items> getAllItems();

@Query("SELECT * FROM Items WHERE id like :id")
    public Items getItemById(int id);
}
