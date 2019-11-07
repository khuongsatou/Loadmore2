package com.nvk.loader2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

import static com.nvk.loader2.MainActivity.KEY_LIMIT;
import static com.nvk.loader2.MainActivity.KEY_PAGE;

public class NguoiChoiLoader extends AsyncTaskLoader<String> {

    private  int page;
    private  int limit;

    public NguoiChoiLoader(@NonNull Context context,int page,int limit) {
        super(context);
        this.page=page;
        this.limit = limit;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        HashMap<String,String> params = new HashMap<>();
        params.put(KEY_PAGE,String.valueOf(page));
        params.put(KEY_LIMIT,String.valueOf(limit));
        return NetWork.connect("nguoi_choi","GET",params);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
