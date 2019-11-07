package com.nvk.loader2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import java.util.List;

public class NguoiChoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_LOADING = 0;
    private static final int TYPE_NGUOICHOI =1;
    public static final String KEY_NGUOI_CHOI = "nguoi_choi";
    private Context context;
    private List<NguoiChoi> nguoiChois;


    public NguoiChoiAdapter(Context context, List<NguoiChoi> nguoiChois) {
        this.context = context;
        this.nguoiChois = nguoiChois;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NGUOICHOI){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_nguoi_choi,parent,false);
            return new NguoiChoiHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NguoiChoi nguoiChoi = nguoiChois.get(position);
        if (holder instanceof NguoiChoiHolder){
            NguoiChoiHolder nguoiChoiHolder = (NguoiChoiHolder) holder;
            nguoiChoiHolder.tvTenDangNhap.setText(nguoiChoi.getTenDangNhap());
            nguoiChoiHolder.tvDiemCaoNhat.setText(nguoiChoi.getDiemCaoNhat()+"");
        }
    }

    @Override
    public int getItemCount() {
        return nguoiChois.size();
    }

    public NguoiChoi getNguoiChoi(int position){
        return nguoiChois.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return nguoiChois.get(position) ==null ? TYPE_LOADING : TYPE_NGUOICHOI;
    }

    class NguoiChoiHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTenDangNhap,tvDiemCaoNhat;
        private CardView cvItemNguoiChoi;
        public NguoiChoiHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDangNhap = itemView.findViewById(R.id.tvTenDangNhap);
            tvDiemCaoNhat = itemView.findViewById(R.id.tvDiemCaoNhat);
            cvItemNguoiChoi = itemView.findViewById(R.id.cvItemNguoiChoi);
            cvItemNguoiChoi.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,ThongTinNguoiChoiActivity.class);
            intent.putExtra(KEY_NGUOI_CHOI,getNguoiChoi(getLayoutPosition()));
            context.startActivity(intent);
        }
    }
    class LoadHolder extends RecyclerView.ViewHolder{

        public LoadHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
