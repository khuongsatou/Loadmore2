package com.nvk.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.nvk.test2.LinhVucApdater.KEY_LINH_VUC;

public class MainSecondActivity extends AppCompatActivity {
    private TextView tvMoTa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);

        Intent intent = getIntent();
        LinhVuc linhVuc = (LinhVuc) intent.getSerializableExtra(KEY_LINH_VUC);

        tvMoTa = findViewById(R.id.tvMoTa);
        tvMoTa.setText(linhVuc.getTenLinhVuc());
        getSupportActionBar().setTitle(linhVuc.getId() + "");
        getSupportActionBar().setSubtitle(linhVuc.getTenLinhVuc());
        //getActionBar().setTitle("linh"+linhVuc.getTenLinhVuc());
    }
}
