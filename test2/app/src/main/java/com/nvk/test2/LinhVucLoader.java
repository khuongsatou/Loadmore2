package com.nvk.test2;

import android.content.Context;
import android.net.Network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

public class LinhVucLoader extends AsyncTaskLoader<String> {

    private int page;
    private int limit;
    public LinhVucLoader(@NonNull Context context,int page,int limit) {
        super(context);
        this.page = page;
        this.limit = limit;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("page",Integer.toString(this.page));
        queryParams.put("limit",Integer.toString(this.limit));
        return NewWork.connect("linh_vuc", "GET",queryParams);
    }
    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }


}
