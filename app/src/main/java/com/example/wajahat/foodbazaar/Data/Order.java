package com.example.wajahat.foodbazaar.Data;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {

    private HashMap<Integer, Integer> order_list=new HashMap<>();
    int table_no;
    int order_no;
    public Order(){

    }

    public HashMap<Integer, Integer> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(HashMap<Integer, Integer> order_list) {
        this.order_list = order_list;
    }

    public void setTable_no(int table_no) {
        this.table_no = table_no;
    }

    public int getTable_no() {
        return table_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public int getOrder_no() {
        return order_no;
    }
}
