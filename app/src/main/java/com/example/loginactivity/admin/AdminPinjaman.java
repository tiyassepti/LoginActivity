package com.example.loginactivity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginactivity.R;
import com.example.loginactivity.adapter.PinjamAdapter;
import com.example.loginactivity.model.Pinjaman;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminPinjaman extends AppCompatActivity {

    private static final String TAG = "AdminPinjaman";

    LinearLayoutManager linearLayoutManager;
    EditText edtSearch;
    Button btnSearch;

    RecyclerView recyclerView;
    PinjamAdapter rvAdapter;

    private List<Pinjaman> listPinjaman;
    FirebaseDatabase database;

    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pinjaman);

        edtSearch = findViewById(R.id.pinjaman_search);
        btnSearch = findViewById(R.id.btn_search_pinjam);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("pinjaman")
                        .orderByChild("id")
                        .equalTo(edtSearch.getText().toString()).limitToLast(2);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listPinjaman.clear();
                        Log.d(TAG, "onDataChange: Jumlah Data " + dataSnapshot.getChildrenCount());
                        for (DataSnapshot pinjamanSnapshot : dataSnapshot.getChildren()){

                            Pinjaman ka = pinjamanSnapshot.getValue(Pinjaman.class);
                            listPinjaman.add(ka);
                        }
                        rvAdapter = new PinjamAdapter(AdminPinjaman.this, listPinjaman);
                        recyclerView.setAdapter(rvAdapter);
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        dialog = new ProgressDialog(AdminPinjaman.this);
        dialog.setMessage("Loading...");
        dialog.show();
        listPinjaman = new ArrayList<Pinjaman>();

        recyclerView = findViewById(R.id.rv_pinjaman);
        linearLayoutManager = new LinearLayoutManager(AdminPinjaman.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //fetch();
    }

    private void fetch() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("pinjaman");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPinjaman.clear();
                Log.d(TAG, "onDataChange: Jumlah Data " + dataSnapshot.getChildrenCount());
                for (DataSnapshot pinjamanSnapshot : dataSnapshot.getChildren()){
                    Pinjaman ka = pinjamanSnapshot.getValue(Pinjaman.class);
                    listPinjaman.add(ka);
                }
                rvAdapter = new PinjamAdapter(AdminPinjaman.this, listPinjaman);
                recyclerView.setAdapter(rvAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
