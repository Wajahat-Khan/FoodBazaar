package com.example.wajahat.foodbazaar.Data;

import java.util.List;

public class Orders {
    private int table_no;
    private List<Integer> order_list;

    public Orders(int table_no, List<Integer> order_list){
        this.table_no=table_no;
        this.order_list=order_list;
    }

    public int getTable_no() {
        return table_no;
    }

    public void setTable_no(int table_no) {
        this.table_no = table_no;
    }

    public List<Integer> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Integer> order_list) {
        this.order_list = order_list;
    }
}
