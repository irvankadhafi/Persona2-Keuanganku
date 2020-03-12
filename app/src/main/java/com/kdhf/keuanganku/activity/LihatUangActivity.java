package com.kdhf.keuanganku.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kdhf.keuanganku.R;
import com.kdhf.keuanganku.adapter.UangAdapter;
import com.kdhf.keuanganku.helper.DBHandler;
import com.kdhf.keuanganku.helper.RecyclerItemClickListener;
import com.kdhf.keuanganku.model.Uang;

import java.util.ArrayList;
import java.util.List;

public class LihatUangActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private UangAdapter adapter;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;
    private List<Uang> uangList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
    }

    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_uang);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(LihatUangActivity.this);
        uangList = dbHandler.getSemuaUang();
        adapter = new UangAdapter(uangList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Uang uang = uangList.get(position);
//                            int id = uang.getId();
                            String detail = "pemasukan";
                            Toast.makeText(LihatUangActivity.this, "Klik di " + detail, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}
