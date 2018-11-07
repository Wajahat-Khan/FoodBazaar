package com.example.wajahat.foodbazaar_fb.Data;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private List<Items> order_list;
    int table_no;
    int order_no;
    int total;
    public Order(){

    }

    public List<Items> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Items> order_list) {
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

