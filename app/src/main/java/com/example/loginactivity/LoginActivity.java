package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginactivity.user.UserPinjamanActivity;
import com.example.loginactivity.user.tampilDataActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseUser user;
    FirebaseAuth auth;
    Button btnSignOut, btnPinjaman, btnHistory, btnAngsuran, btnRegister;
    String emailditerima="EMAIL";
    String nama;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        nama=getIntent().getStringExtra("EMAIL");

        btnSignOut = findViewById(R.id.btn_keluar);
        btnAngsuran = findViewById(R.id.btn_angsuran);
        btnHistory = findViewById(R.id.btn_history);
        btnPinjaman = findViewById(R.id.btn_pinjaman);
        btnRegister = findViewById(R.id.btn_register);

        btnSignOut.setOnClickListener(this);
        btnAngsuran.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnPinjaman.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

/*if (auth!=null && user.isEmailVerified()){

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });


    }*/}

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_register:

                break;
            case R.id.btn_pinjaman:
                Intent m=new Intent(LoginActivity.this, UserPinjamanActivity.class);
                m.putExtra("EMAIL",nama);
                startActivity(m);
                finish();
                break;
            case R.id.btn_history:
                Intent y=new Intent(LoginActivity.this, tampilDataActivity.class);
                y.putExtra("EMAIL",nama);
                startActivity(y);
                finish();
                break;
            case R.id.btn_angsuran:
                break;
        }

    }
}
