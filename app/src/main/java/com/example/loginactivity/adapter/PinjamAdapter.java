package com.example.loginactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.loginactivity.model.Pinjaman;

import java.util.List;

public class PinjamAdapter extends RecyclerView.Adapter<PinjamAdapter.ViewHolder>{

    private static final String TAG = "PinjamAdapter";
    View mView;

    private Context context;
    List<Pinjaman> listPinjaman;

    public PinjamAdapter(Context context, List<Pinjaman> listPinjaman) {
        this.context = context;
        this.listPinjaman = listPinjaman;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_pinjaman, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cv_id.setText(listPinjaman.get(position).getId());
        holder.cv_nama.setText(listPinjaman.get(position).getEmail());
        holder.cv_tanggal.setText(listPinjaman.get(position).getTglPinjam());
        holder.cv_besar.setText(listPinjaman.get(position).getBesarPinjam().toString());

    }

    @Override
    public int getItemCount() {
        return listPinjaman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout root;

        public TextView cv_id;
        public TextView cv_nama;
        public TextView cv_tanggal;
        public TextView cv_besar;
        public TextView cv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_id = itemView.findViewById(R.id.cv_pinjam_id);
            cv_nama = itemView.findViewById(R.id.cv_pinjam_nama);
            cv_tanggal = itemView.findViewById(R.id.cv_pinjam_tanggal);
            cv_besar = itemView.findViewById(R.id.cv_pinjam_besar);
            cv_status= itemView.findViewById(R.id.cv_status);

            mView = itemView;

        }
    }
}
