package com.example.wlaza.smart_przodek.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.wlaza.smart_przodek.FileManagement.FileStorage;
import com.example.wlaza.smart_przodek.R;


public class LoggingActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);

//        FileSelector.getInstance().selectFile(this);
//        FileDatabase.getInstance().getFileList("materialy", result -> {
//            for (String file : result) {
//                Log.d("cb", file);
//            }
//        });

        Intent viewFilesActivity = new Intent(this, ViewFilesActivity.class);
        this.startActivity(viewFilesActivity);

//        ActivityCompat.requestPermissions(this,
//                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                1);
    }

}
