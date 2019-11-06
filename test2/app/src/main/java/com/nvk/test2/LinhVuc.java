package com.nvk.test2;

import java.io.Serializable;

public class LinhVuc implements Serializable {
    private int id;
    private String tenLinhVuc;

    public LinhVuc(){
    }

    public LinhVuc(int id, String tenLinhVuc){
        this.id = id;
        this.tenLinhVuc = tenLinhVuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }
}
