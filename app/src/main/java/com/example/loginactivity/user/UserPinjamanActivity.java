package com.example.loginactivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.loginactivity.DetailPeternakActivity;
import com.example.loginactivity.ForgotpassActivity;
import com.example.loginactivity.LoginActivity;
import com.example.loginactivity.MainActivity;
import com.example.loginactivity.R;
import com.example.loginactivity.adapter.PeternakAdapter;
import com.example.loginactivity.model.PeternakRegister;
import com.example.loginactivity.model.Pinjaman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserPinjamanActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    private static final String TAG = "userPinjamanPeternak";
    EditText nominal, bulan, total, jumlah;
    EditText date,cicil;
    Button berikutnya, kembali, riwayat;
    private StorageReference msstorage;
    TextView tgl;
    long maxId;
    String idBaru;
    DatabaseReference database;

ProgressDialog dialog;
    String email;
    String data;
    Double jml;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pinjaman);
        database = FirebaseDatabase.getInstance().getReference();
        nominal = findViewById(R.id.txtnominal);
        bulan = findViewById(R.id.detail_bulan);
        total = findViewById(R.id.detail_total);
        jumlah = findViewById(R.id.detail_jumlahygdibayar);
        date = findViewById(R.id.detail_tanggal);
        berikutnya = findViewById(R.id.btnberikut);
        kembali = findViewById(R.id.btnkembali);
        riwayat = findViewById(R.id.btnriwayat);
        cicil=findViewById(R.id.cicilanperbulan);
        tgl=findViewById(R.id.txttgl);
       bulan.setOnKeyListener(this);
       riwayat.setOnClickListener(this);
        email=  getIntent().getStringExtra("EMAIL");





        msstorage = FirebaseStorage.getInstance().getReference("Pinjaman");
        Calendar ci=Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String strdate=dateFormat.format(cal.getTime());
        String strd=dateFormat.format(ci.getTime());
        date.setText("Tanggal Angsuran Pertama :"+strdate);
        tgl.setText(strd);



        //IKI NGGO KODE PETERNAK
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Pinjaman");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId = dataSnapshot.getChildrenCount() + 1;
                idBaru = "PJN-" + maxId;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final Pinjaman pinjaman = new Pinjaman();



        berikutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Query query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("Pinjaman")
                        .orderByChild( "email")
                        .equalTo(email).limitToLast(1);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Log.d(TAG, "onDataChange: Jumlah Data " + dataSnapshot.getChildrenCount());
                            for (DataSnapshot pinjamanSnapshot : dataSnapshot.getChildren()){

                            Pinjaman ka = pinjamanSnapshot.getValue(Pinjaman.class);
                            Log.d(TAG, "onDataChange: " + ka.getEmail());
                           jml=ka.getTotalPinjam();
                           data=ka.getEmail();
                            } if(jml!=0){
                                mode=1;
                                //Toast.makeText(UserPinjamanActivity.this, "Mohon maaf anda tidak bisa melakukan pinjaman lain ", Toast.LENGTH_SHORT).show();

//
                            }else {
                                pinjaman.setId(idBaru);
                                pinjaman.setBesarPinjam(Double.parseDouble(nominal.getText().toString()));
                                pinjaman.setLamaPinjam(Integer.parseInt(bulan.getText().toString()));
                                pinjaman.setTotalPinjam(Double.parseDouble(jumlah.getText().toString()));
                                pinjaman.setTglPinjam((tgl.getText().toString()));
                                pinjaman.setEmail(email);
                                Simpandata(pinjaman);
                                mode=2;
                                //Toast.makeText(UserPinjamanActivity.this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else {
                            mode=3;
                            pinjaman.setId(idBaru);
                            pinjaman.setBesarPinjam(Double.parseDouble(nominal.getText().toString()));
                          pinjaman.setLamaPinjam(Integer.parseInt(bulan.getText().toString()));
                        pinjaman.setTotalPinjam(Double.parseDouble(jumlah.getText().toString()));
                        pinjaman.setTglPinjam((tgl.getText().toString()));
                          pinjaman.setEmail(email);
                          Simpandata(pinjaman);
                            Toast.makeText(UserPinjamanActivity.this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();}

                        if(mode==1) {
                            Toast.makeText(UserPinjamanActivity.this, "Mohon maaf anda tidak bisa melakukan pinjaman lain ", Toast.LENGTH_SHORT).show();
                        }else if(mode==2){
                            Toast.makeText(UserPinjamanActivity.this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();

                    }else if(mode==3){
                        Toast.makeText(UserPinjamanActivity.this, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();
                    }
//
//                        Log.d(TAG,"jjjj"+data);
//
//                        if(data= || jml!=){
//                            Toast.makeText(UserPinjamanActivity.this, "Pinjaman lain belum lunas", Toast.LENGTH_SHORT).show();
//
//                        }else{
                        Intent y=new Intent(UserPinjamanActivity.this, userpinjaman2Activity.class);
                        y.putExtra("ID",idBaru);
                        y.putExtra("NOMINAL",nominal.getText().toString());
                        y.putExtra("LAMA",bulan.getText().toString());
                        y.putExtra("JUMLAH",jumlah.getText().toString());
                        y.putExtra("TGL",tgl.getText().toString());
                        y.putExtra("EMAIL",email);
                        startActivity(y);

//
//
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: "+databaseError);

                    }
                });

            }
        });

    }

    public void Simpandata(Pinjaman pinjam) {

        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("Pinjaman") //akses parent index, ibaratnya seperti nama tabel
                .child(String.valueOf(maxId)) //select barang berdasarkan key
                .setValue(pinjam)//set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener
                        <Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        };
                    }
                });


    }

    public boolean onKey(View view, int keyCode, KeyEvent event) {


        if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                keyCode == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            if (!event.isShiftPressed()) {
                Log.v("AndroidEnterKeyActivity", "Enter Key Pressed!");
                switch (view.getId()) {
                    case R.id.detail_bulan:
                        total.setText(nominal.getText());
                        double b = Double.parseDouble(nominal.getText().toString());
                        double c = (b * 0.001) + b;
                        jumlah.setText(String.valueOf(c));
                        double d=c/Double.parseDouble(bulan.getText().toString());
                        cicil.setText("cicilan pertama "+String.valueOf(d));

                        break;

                }
                return true;
            }

        }
        return false; // pass on to other listeners.

    }


    @Override
    public void onClick(View v) {
       // startActivity(new Intent(UserPinjamanActivity.this,RiwayatPinjaman.class));
        Intent y=new Intent(UserPinjamanActivity.this, RiwayatPinjaman.class);
        y.putExtra("EMAIL",email);
        startActivity(y);
        finish();
    }
}


