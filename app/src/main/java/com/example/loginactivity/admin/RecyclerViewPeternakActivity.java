package com.example.loginactivity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.loginactivity.R;
import com.example.loginactivity.adapter.PeternakAdapter;
import com.example.loginactivity.fragment.RecyclerViewFragment;
import com.example.loginactivity.model.PeternakRegister;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerViewPeternakActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewPeternakAct";

    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView;
    PeternakAdapter rvAdapter;

    private List<PeternakRegister> listPeternak;
    FirebaseDatabase database;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_peternak);

        startFragment();
    }

    private void startFragment(){
        RecyclerViewFragment myFragment = new RecyclerViewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.rvFragment, myFragment).commit();
    }

    private void fetch() {
        DatabaseReference ref = database.getReference().child("peternak");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPeternak.clear();
                Log.d(TAG, "onDataChange: Jumlah Data " + dataSnapshot.getChildrenCount());
                for (DataSnapshot peternakSnapshot : dataSnapshot.getChildren()){
                    PeternakRegister ka = peternakSnapshot.getValue(PeternakRegister.class);
                    listPeternak.add(ka);
                }
                rvAdapter = new PeternakAdapter(RecyclerViewPeternakActivity.this, listPeternak);
                recyclerView.setAdapter(rvAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
