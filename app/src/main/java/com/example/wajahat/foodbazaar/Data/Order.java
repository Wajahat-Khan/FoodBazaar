package com.example.wajahat.foodbazaar.Data;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {

    private HashMap<Integer, Integer> order_list=new HashMap<>();
    public Order(){

    }

    public HashMap<Integer, Integer> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(HashMap<Integer, Integer> order_list) {
        this.order_list = order_list;
    }

}
