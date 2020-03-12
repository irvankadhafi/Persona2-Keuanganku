package com.kdhf.keuanganku.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kdhf.keuanganku.R;
import com.kdhf.keuanganku.model.Uang;

import java.util.ArrayList;
import java.util.List;

public class UangAdapter extends RecyclerView.Adapter<UangAdapter.UangViewHolder> {

    private List<Uang> uangList = new ArrayList<>();

    public UangAdapter(List<Uang> mahasiswaList){
        this.uangList =mahasiswaList;
    }

    @NonNull
    @Override
    public UangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_uang, parent, false);
        UangViewHolder uangViewHolder = new UangViewHolder(view);
        return uangViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UangViewHolder holder, int position) {
        holder.txt_income.setText(uangList.get(position).getPemasukan());
        holder.txt_outcome.setText(uangList.get(position).getPengeluaran());

    }

    @Override
    public int getItemCount() {
        return uangList.size();
    }

    public class UangViewHolder extends RecyclerView.ViewHolder{

        TextView txt_income;
        TextView txt_outcome;

        public UangViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_income = (TextView) itemView.findViewById(R.id.txt_income);
            txt_outcome = (TextView) itemView.findViewById(R.id.txt_outcome);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
