package com.example.wajahat.foodbazaar.Interfaces;

import com.example.wajahat.foodbazaar.Data.Items;

import java.util.HashMap;

public interface AsyncResponse {
    void processFinish(HashMap<Items,Integer> output);
}
