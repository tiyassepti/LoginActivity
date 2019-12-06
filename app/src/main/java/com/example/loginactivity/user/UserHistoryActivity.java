package com.example.loginactivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.loginactivity.LoginActivity;
import com.example.loginactivity.R;

import java.util.List;

public class UserHistoryActivity extends Activity implements View.OnClickListener {
    private EditText phoneNumber;
    private EditText smsBody;
    private Button smsManagerBtn;
    private Button smsSendToBtn;
    private Button smsViewBtn;
    final int send=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        smsBody = (EditText) findViewById(R.id.smsBody);
        smsManagerBtn = (Button) findViewById(R.id.smsManager);
        smsSendToBtn = (Button) findViewById(R.id.smsSIntent);
        smsViewBtn = (Button) findViewById(R.id.smsVIntent);
        smsViewBtn.setOnClickListener( this);
        smsManagerBtn.setOnClickListener( this);
        smsSendToBtn.setOnClickListener( this);




    }public void sendSmsByManager() {
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            SmsManager.getDefault().sendTextMessage("082242571096", null, "yyyy", null, null);
            Toast.makeText(getApplicationContext(), "SMS Berhasil Dikirim!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Pengiriman SMS Gagal...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsBySIntent( ) {
        // add the phone number in the data
        Uri uri = Uri.parse("smsto:082242571096" );

        Intent smsSIntent = new Intent(Intent.ACTION_SEND);
        // add the message at the sms_body extra field
        smsSIntent.putExtra("sms","082242571096");
        smsSIntent.putExtra("sms_body", "wwww");
        try{
            startActivity(smsSIntent);
        } catch (Exception ex) {
            Toast.makeText(UserHistoryActivity.this, "Pengiriman SMS Gagal..."+ex,
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsByVIntent( ) {

        SmsManager smsManager=SmsManager.getDefault();
        try{
            smsManager.sendTextMessage("082242571096","null","maf",null,null);
            Toast.makeText(UserHistoryActivity.this, "Pengiriman SMS berhasil...",
                    Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            Toast.makeText(UserHistoryActivity.this, "Pengiriman SMS Gagal..."+ex,
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }



    public static String getDefaultSmsAppPackageName(@NonNull Context context) {
        String defaultSmsPackageName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context);
            return defaultSmsPackageName;
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .addCategory(Intent.CATEGORY_DEFAULT).setType("vnd.android-dir/mms-sms");
            final List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, 0);
            if (resolveInfos != null && !resolveInfos.isEmpty())
                return resolveInfos.get(0).activityInfo.packageName;

        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.smsManager:
sendSmsByVIntent();

                break;
            case R.id.smsSIntent:

                break;
            case R.id.smsVIntent:

                break;

    }
}}
