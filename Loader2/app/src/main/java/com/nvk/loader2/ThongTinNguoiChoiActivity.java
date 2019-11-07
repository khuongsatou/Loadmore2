package com.nvk.loader2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.nvk.loader2.NguoiChoiAdapter.KEY_NGUOI_CHOI;

public class ThongTinNguoiChoiActivity extends AppCompatActivity {
    private ImageView ivHinhDaiDien;
    private TextView tvEmail,tvCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_choi);

        radiation();
        setData();


    }

    private void setData() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_NGUOI_CHOI);
        tvEmail.setText(nguoiChoi.getEmail());
        tvCredit.setText(nguoiChoi.getCredit()+"");
        getSupportActionBar().setTitle(nguoiChoi.getId()+"");
        getSupportActionBar().setSubtitle(nguoiChoi.getHinhDaiDien()+"");


    }

    private void radiation() {
        tvEmail = findViewById(R.id.tvEmail);
        tvCredit = findViewById(R.id.tvCredit);
    }
}
