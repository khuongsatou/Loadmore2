package com.nvk.test2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private RecyclerView rcvLinhVuc;
    private LinhVucApdater adapter;
    private List<LinhVuc> linhVucs = new ArrayList<>();

    private boolean isLoading = false;
    private boolean isLastPage = false;

    private  int currentPage = 1;
    private  int totalPage;
    private static  final  int PAGE_SIZE =25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiation();
        createAdapter();
        //startLoader();
        loadData(null);
        checkScroll();


    }

    private void checkScroll() {
        rcvLinhVuc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) rcvLinhVuc.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if(!isLoading && !isLastPage){
                    //điều kiện loading
                    if ((visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >=0 && totalItemCount >= PAGE_SIZE)){
                        isLoading = true;
                        //tăng trang lên
                        currentPage++;
                        //nơi phần tử progress 3 hiển thị
                        linhVucs.add(null);
                        //chạy adapter cập nhật lại
                        adapter.notifyItemInserted(linhVucs.size() -1);

                        //put trong tiếp theo
                        Bundle data = new Bundle();
                        data.putInt("page",currentPage);
                        data.putInt("limit",PAGE_SIZE);
                        loadData(data);
                    }
                }
                //...

            }
        });




    }

    private void loadData(Bundle data) {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (conMgr != null){
            networkInfo = conMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()){
            startLoader(data);
        }
    }

    private void createAdapter() {
        adapter = new LinhVucApdater(this,linhVucs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvLinhVuc.setLayoutManager(layoutManager);
        rcvLinhVuc.setAdapter(adapter);
    }

    private void radiation() {
        rcvLinhVuc = findViewById(R.id.rcvLinhVuc);
    }

    private void startLoader(Bundle data) {
        if (getSupportLoaderManager().getLoader(0) != null){
            //lần 2 nó restart nhưng ko chạy vào loadInBackground mà chỉ gán
            getSupportLoaderManager().restartLoader(0, data, this);
        }
        //khởi tạo nó cũng chạy vào hàm onCreateLoader

        //init lần đầu sẽ chạy cả 2 gán vào loader và chạy LoadInBackground đọc json (do background)
        //lần 2: nó không khởi tạo loader mà chỉ chạy loadInBackround
        getSupportLoaderManager().initLoader(0,data, this);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        int page = 1;
        int limit = 25;
        if (args != null){
            page = args.getInt("page");
            limit = args.getInt("limit");
        }
        return new LinhVucLoader(this,page,limit);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject objLinhVuc = new JSONObject(data);
            int total = objLinhVuc.getInt("total");
            totalPage = total/PAGE_SIZE;
            if (total %PAGE_SIZE != 0){
                totalPage++;
            }

            JSONArray arrLinhVuc = objLinhVuc.getJSONArray("danh_sach");

            if (linhVucs.size() >0){
                //remove progress bar đi
                linhVucs.remove(linhVucs.size()-1);
                int scrollPosition = linhVucs.size();
                adapter.notifyItemRemoved(scrollPosition);
            }


            for (int i = 0; i < arrLinhVuc.length(); i++) {
                JSONObject objItem = arrLinhVuc.getJSONObject(i);
                int id = objItem.getInt("id");
                String tenLinhVuc = objItem.getString("ten_linh_vuc");
                LinhVuc linhVuc = new LinhVuc();
                linhVuc.setId(id);
                linhVuc.setTenLinhVuc(tenLinhVuc);

                linhVucs.add(linhVuc);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        isLoading = false;
        isLastPage = (currentPage == totalPage);


    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
