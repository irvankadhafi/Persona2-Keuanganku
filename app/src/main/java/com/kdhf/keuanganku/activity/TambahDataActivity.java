package com.kdhf.keuanganku.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kdhf.keuanganku.R;
import com.kdhf.keuanganku.adapter.UangAdapter;
import com.kdhf.keuanganku.helper.DBHandler;
import com.kdhf.keuanganku.model.Uang;

import java.util.List;

public class TambahDataActivity extends AppCompatActivity {

    private EditText et_income;
    private EditText et_outcome;
    private Button button_tambahdata;

    private DBHandler dbHandler;
    private UangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        dbHandler = new DBHandler(this);
        initComponents();
    }

    private void initComponents(){
        et_income = (EditText) findViewById(R.id.et_income);
        et_outcome = (EditText) findViewById(R.id.et_outcome);
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiForm();
            }
        });
    }

    // FUNGSI INI UNTUK MEMVALIDASI FORM JIKA ADA YANG KOSONG ATAU TIDAK
    // LALU DILANJUT UNTUK MENJALANKAN PERINTAH SELANJUTNYA
    private void validasiForm(){

//        String temp1=et_income.getText().toString();
//        int form_pemasukan=0;
//        if (!"".equals(temp1)){
//            form_pemasukan=Integer.parseInt(temp1);
//        }
//        String temp2=et_outcome.getText().toString();
//        int form_pengeluaran=0;
//        if (!"".equals(temp2)){
//            form_pengeluaran=Integer.parseInt(temp2);
//        }
        String form_pemasukan = et_income.getText().toString();
        String form_pengeluaran = et_outcome.getText().toString();

        if (form_pemasukan.isEmpty()){
            et_income.setError("Isi pemasukan dulu");
            et_outcome.requestFocus();
        } if (form_pengeluaran.isEmpty()){
            et_outcome.setError("Isi pengeluaran dulu");
            et_outcome.requestFocus();
        } else {
//            dbHandler.tambahUang(new Uang(form_pemasukan,form_pengeluaran));
            dbHandler.tambahUang(new Uang(form_pemasukan, form_pengeluaran));
            List<Uang> uangList = dbHandler.getSemuaUang();
            adapter = new UangAdapter(uangList);
            adapter.notifyDataSetChanged();

            Toast.makeText(TambahDataActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}

