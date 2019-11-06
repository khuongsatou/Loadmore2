package com.nvk.test2;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinhVucApdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String KEY_LINH_VUC = "123";
    private static final int TYPE_LOADING = 0;
    private static final int TYPE_LINH_VUC =1 ;
    private Context context;
    private List<LinhVuc> linhVucs;

    public LinhVucApdater(Context context, List<LinhVuc> linhVucs) {
        this.context = context;
        this.linhVucs = linhVucs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LINH_VUC){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_linh_vuc,parent,false);
            return new LinhVucHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadingHolder(view);
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LinhVuc linhVuc = linhVucs.get(position);
        if (holder instanceof LinhVucHolder){
            LinhVucHolder linhVucHolder = (LinhVucHolder) holder;
            linhVucHolder.tvLinhVuc.setText(linhVuc.getTenLinhVuc());
        }

    }

    @Override
    public int getItemViewType(int position) {
        return linhVucs.get(position) == null ? TYPE_LOADING : TYPE_LINH_VUC;
    }

    @Override
    public int getItemCount() {
        return linhVucs.size();
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class LinhVucHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvLinhVuc;


        public LinhVucHolder(@NonNull View itemView) {
            super(itemView);

            tvLinhVuc = itemView.findViewById(R.id.tvLinhVuc);
            tvLinhVuc.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,MainSecondActivity.class);
            intent.putExtra(KEY_LINH_VUC,linhVucs.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }
}
