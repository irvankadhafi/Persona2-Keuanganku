package com.kdhf.keuanganku.activity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kdhf.keuanganku.R;
import com.kdhf.keuanganku.helper.DBHandler;

public class MainActivity extends AppCompatActivity {
    private Button button_tambahdata;
    private Button button_lihatdata;
    private Button button_hapusdata;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
        initComponents();
    }
    private void initComponents(){
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);
        button_lihatdata = (Button) findViewById(R.id.button_lihatdata);
        button_hapusdata = (Button) findViewById(R.id.button_hapusdata);

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahDataActivity.class));
            }
        });

        button_lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LihatUangActivity.class));
            }
        });


        button_hapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.hapusSemuaDataMahasiswa();
                Toast.makeText(MainActivity.this, "Berhasil Menghapus Semua Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
