package com.example.loginactivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginactivity.R;

import java.util.Date;

public class pinjamanActivity extends AppCompatActivity {
    EditText nominal,bulan,total,jumlah;
 EditText date;
    String namas="EMAIL",email;
    Button kembali,riwayat,pengajuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman);
        nominal=findViewById(R.id.txtnominal);
        bulan=findViewById(R.id.detail_bulan);
        total=findViewById(R.id.detail_total);
        jumlah=findViewById(R.id.detail_jumlahygdibayar);
       date=findViewById(R.id.detail_tanggal);




        
    }
}
