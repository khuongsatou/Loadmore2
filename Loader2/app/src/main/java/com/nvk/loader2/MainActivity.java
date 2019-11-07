package com.nvk.loader2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public static final String KEY_PAGE ="page" ;
    public static final String KEY_LIMIT = "limit";
    private static final int PAGE_SIZE = 15;
    private boolean checkLoading =false;
    private boolean checkLastPage =false;
    private int currentPage = 1;
    private int totalPage = 0;

    private RecyclerView rcvNguoiChoi;
    private NguoiChoiAdapter adapter;
    private List<NguoiChoi> nguoiChois = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiation();
        createAdapter();
        loadData(null);
        checkScroll();

    }

    private void checkScroll() {
        rcvNguoiChoi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) rcvNguoiChoi.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int itemChild = layoutManager.getChildCount();
                int findNextChild =layoutManager.findFirstVisibleItemPosition();
                if (!checkLoading && !checkLastPage){
                    if ((itemChild+findNextChild) >= itemCount && findNextChild >=0 && itemCount >= PAGE_SIZE){
                        checkLoading = true;
                        currentPage ++;
                        //hiển thị loading
                        nguoiChois.add(null);
                        adapter.notifyItemInserted(nguoiChois.size() -1);

                        Bundle data = new Bundle();
                        data.putInt(KEY_PAGE,currentPage);
                        data.putInt(KEY_LIMIT,PAGE_SIZE);
                        loadData(data);
                    }
                }


            }
        });
    }

    private void loadData(Bundle data) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()){
            startLoader(data);
        }else{
            showDialogConnect("Không có Kết nối Internet").show();
        }
    }

    private void startLoader(Bundle data) {
        if (getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().restartLoader(0,data,this);
        }
        getSupportLoaderManager().initLoader(0,data,this);
    }

    private AlertDialog showDialogConnect(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lỗi").setMessage(message).setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    private void createAdapter() {
        adapter = new NguoiChoiAdapter(this,nguoiChois);
        rcvNguoiChoi.setLayoutManager(new LinearLayoutManager(this));
        rcvNguoiChoi.setAdapter(adapter);
    }

    private void radiation() {
        rcvNguoiChoi = findViewById(R.id.rcvNguoiChoi);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        int page = 1;
        int limit = 25;
        if (args != null){
            page = args.getInt(KEY_PAGE);
            limit = args.getInt(KEY_LIMIT);
        }
        return new NguoiChoiLoader(this,page,limit);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        //JSON
        try {
            JSONObject objNguoiChoi = new JSONObject(data);

            int total = objNguoiChoi.getInt("total");
            totalPage = total / PAGE_SIZE;

            if (nguoiChois.size() > 0){
                nguoiChois.remove(nguoiChois.size()-1);
                adapter.notifyItemRemoved(nguoiChois.size());
            }

            JSONArray arrNguoiChoi = objNguoiChoi.getJSONArray("danh_sach");
            for (int i = 0; i < arrNguoiChoi.length(); i++) {
                JSONObject objItemNguoiChoi = arrNguoiChoi.getJSONObject(i);
                int id = objItemNguoiChoi.getInt("id");
                String tenDangNhap = objItemNguoiChoi.getString("ten_dang_nhap");
                String matKhau = objItemNguoiChoi.getString("mat_khau");
                int diemCaoNhat = objItemNguoiChoi.getInt("diem_cao_nhat");
                String hinhDaiDien = objItemNguoiChoi.getString("hinh_dai_dien");
                int credit = objItemNguoiChoi.getInt("credit");
                String email = objItemNguoiChoi.getString("email");

                NguoiChoi nguoiChoi = new NguoiChoi();
                nguoiChoi.setId(id);
                nguoiChoi.setMatKhau(matKhau);
                nguoiChoi.setTenDangNhap(tenDangNhap);
                nguoiChoi.setDiemCaoNhat(diemCaoNhat);
                nguoiChoi.setHinhDaiDien(hinhDaiDien);
                nguoiChoi.setEmail(email);
                nguoiChoi.setCredit(credit);
                nguoiChois.add(nguoiChoi);
            }

            adapter.notifyDataSetChanged();
            checkLoading = false;
            checkLastPage = (currentPage == totalPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
