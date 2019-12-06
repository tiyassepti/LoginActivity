package com.example.loginactivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.loginactivity.LoginActivity;
import com.example.loginactivity.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserAngsuranActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserAngsuranActivity";
    private final int CHOOSE_IMAGE=1;

    private Button btnPilihTanggal, btnSimpan, btnBatal, btnPilihGambar;
    private MaterialEditText kodeAmgsuran, jumlah, tanggal, angsuranKe;
    Spinner kodePinjaman;
    private int mYear,mMonth,mDay;
    String bulan = "";
    ProgressDialog dialog;
    String userId;

    private Uri imageURL;
    private StorageReference storageReference;
    private StorageTask storageTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_angsuran);

        kodeAmgsuran = findViewById(R.id.user_angsuran_kode);
        jumlah = findViewById(R.id.user_angsuran_jumlah);
        tanggal = findViewById(R.id.user_angsuran_tanggal);
        angsuranKe = findViewById(R.id.user_angsuran_ke);
        kodePinjaman = findViewById(R.id.user_angsuran_kode_peminjaman);

        btnPilihTanggal = findViewById(R.id.user_angsuran_pilh_tanggal);
        btnSimpan = findViewById(R.id.user_angsuran_simpan);
        btnBatal = findViewById(R.id.user_angsuran_batal);
        btnPilihGambar = findViewById(R.id.user_angsuran_upload_gambar);
        btnPilihTanggal.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);
        btnPilihGambar.setOnClickListener(this);

        storageReference= FirebaseStorage.getInstance().getReference("Peternak/" + userId + "/Angsuran");
        makeCalendar();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_angsuran_pilh_tanggal:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(UserAngsuranActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                Log.d(TAG, "Bulan " + mMonth);

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);
                                mMonth = mMonth+1;
                                if (mMonth == 1){
                                    bulan = "Januari";
                                } else if (mMonth == 2){
                                    bulan = "Februari";
                                } else if (mMonth == 3){
                                    bulan = "Maret";
                                } else if (mMonth == 4){
                                    bulan = "April";
                                } else if (mMonth == 5){
                                    bulan = "Mei";
                                } else if (mMonth == 6){
                                    bulan = "Juni";
                                } else if (mMonth == 7){
                                    bulan = "Juli";
                                } else if (mMonth == 8){
                                    bulan = "Agustus";
                                } else if (mMonth == 9){
                                    bulan = "September";
                                } else if (mMonth == 10){
                                    bulan = "Oktober";
                                } else if (mMonth == 11){
                                    bulan = "November";
                                } else if (mMonth == 12){
                                    bulan = "Desember";
                                }

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                tanggal.setText(dayOfMonth + " "
                                        + (bulan) + " " + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
                break;
            case R.id.user_angsuran_simpan:
                break;
            case R.id.user_angsuran_upload_gambar:
                Showchooseimage();
                break;
            case R.id.user_angsuran_batal:
                startActivity(new Intent(UserAngsuranActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void makeCalendar(){
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tanggal.setText(sdf.format(myCalendar.getTime()));
            }
        };
    }

    private  void Showchooseimage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,CHOOSE_IMAGE);
    }
}
