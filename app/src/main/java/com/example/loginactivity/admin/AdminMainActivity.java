package com.example.loginactivity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginactivity.LoginActivity;
import com.example.loginactivity.R;
import com.example.loginactivity.user.UserAngsuranActivity;
import com.example.loginactivity.user.UserPinjamanActivity;
import com.example.loginactivity.user.pinjamanActivity;
import com.google.firebase.auth.FirebaseAuth;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDataPeternak, btnAngsuran, btnPinjaman, btnSimpanan, btnLaporan, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        btnDataPeternak = findViewById(R.id.admin_peternak);
        btnAngsuran = findViewById(R.id.admin_angsuran);
        btnPinjaman = findViewById(R.id.admin_pinjaman);
        btnSimpanan = findViewById(R.id.admin_simpanan);
        btnLaporan = findViewById(R.id.admin_laporan);
        btnKeluar = findViewById(R.id.admin_keluar);
        btnDataPeternak.setOnClickListener(this);
        btnAngsuran.setOnClickListener(this);
        btnPinjaman.setOnClickListener(this);
        btnSimpanan.setOnClickListener(this);
        btnLaporan.setOnClickListener(this);
        btnKeluar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.admin_peternak:
                //WES BERES CUK
                startActivity(new Intent(AdminMainActivity.this, RecyclerViewPeternakActivity.class));
                finish();
                break;
            case R.id.admin_angsuran:
                startActivity(new Intent(AdminMainActivity.this, UserAngsuranActivity.class));
                finish();
                break;
            case R.id.admin_pinjaman:


                try {
                    // Construct data
                    String userkey = "api_key=7cb53d7a" ;
                    String paskey = "api_secret=xa35XwfCcjW3FC0j" ;
                    String num = "to=6282242571096" ;
                    String mess= "from=Admin";
                    String to="Hello from Nexmo";

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://reguler.zenziva.net/apps/smsapi.php?").openConnection();
                    String data = userkey+paskey+ num + mess;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        //stringBuffer.append(line);
                        //JOptionPane.showMessageDialog(null, "pesan anda sdah terkirim");
                    }
                    rd.close();

                    //return stringBuffer.toString();
                } catch (Exception e) {
                    System.out.println("Error SMS "+e);
                    //JOptionPane.showMessageDialog(null, e);
                    //return "Error "+e;
                }
                break;
            case R.id.admin_simpanan:
                startActivity(new Intent(AdminMainActivity.this, AdminSimpanan.class));
                finish();
                break;
            case R.id.admin_laporan:
//                startActivity(new Intent(AdminMainActivity.this, AdminLaporan.class));
//                finish();
                break;
            case R.id.admin_keluar:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
