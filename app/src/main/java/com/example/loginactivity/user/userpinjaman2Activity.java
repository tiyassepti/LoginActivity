package com.example.loginactivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginactivity.DetailPeternakActivity;
import com.example.loginactivity.R;
import com.example.loginactivity.model.PeternakRegister;
import com.example.loginactivity.model.Pinjaman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class userpinjaman2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText atasnama;
    EditText norek;
    Spinner guna;
    Button selanjutnya;
    String id;
    String nominal;
    String lama;
    String jumlah;
    String tgl;
    String email;
    String key;
    DatabaseReference database;
    private static final String TAG = "userPinjaman2Activity";
    DatabaseReference refer;

    private List<Pinjaman> listPinjaman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpinjaman2);
        atasnama = findViewById(R.id.txtatasnama);
        norek = findViewById(R.id.tnorek);
        selanjutnya = findViewById(R.id.btnselanjutnya);
        guna = (Spinner) findViewById(R.id.Guna);
        selanjutnya.setOnClickListener(this);
        id = getIntent().getStringExtra("ID");
        nominal = getIntent().getStringExtra("NOMINAL");
        lama = getIntent().getStringExtra("LAMA");
        jumlah = getIntent().getStringExtra("JUMLAH");
        tgl = getIntent().getStringExtra("TGL");
        email = getIntent().getStringExtra("EMAIL");
        refer = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Pinjaman")
                .orderByChild("email")
                .equalTo(email).limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"OnDataChange:JumlahData"+dataSnapshot.getChildrenCount());
                for (DataSnapshot pinjamanSnapshot : dataSnapshot.getChildren()){

                    key=pinjamanSnapshot.getKey();
                    Log.d(TAG,"onDataChange "+key);
                    final Pinjaman pinjam  = new Pinjaman();


                  selanjutnya.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pinjam.setBesarPinjam(Double.parseDouble(nominal));
                            pinjam.setLamaPinjam(Integer.parseInt(lama));
                            pinjam.setTotalPinjam(Double.parseDouble(jumlah));
                            pinjam.setTglPinjam(tgl);
                            pinjam.setEmail(email);
                            pinjam.setNamarek(atasnama.getText().toString());
                            pinjam.setNorek(norek.getText().toString());
                            pinjam.setAlasan(guna.getSelectedItem().toString());
                            pinjam.setId(id);
                            pinjam.setStatus("Proses");

                            editdata(pinjam);

                            System.out.println(key);

                        }
                    });
                }
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void editdata(Pinjaman peternak){

        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
       refer.child("Pinjaman") //akses parent index, ibaratnya seperti nama tabel
                .child(key) //select barang berdasarkan key
                .setValue(peternak) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener
                        <Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Toast.makeText(userpinjaman2Activity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        };
                    }
                });}

    @Override
    public void onClick(View v) {

    }
}