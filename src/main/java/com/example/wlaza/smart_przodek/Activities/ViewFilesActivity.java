package com.example.wlaza.smart_przodek.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wlaza.smart_przodek.FileManagement.FileDatabase;
import com.example.wlaza.smart_przodek.FileManagement.FileSelector;
import com.example.wlaza.smart_przodek.FileManagement.FileStorage;
import com.example.wlaza.smart_przodek.R;

import java.util.ArrayList;
import java.util.List;

public class ViewFilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileSelector.getInstance().selectFile(ViewFilesActivity.this);
            }
        });

        addRows();
    }

    private void addRows() {
        ListView list = (ListView) findViewById(R.id.Listing);

        FileDatabase.getInstance().getFileList("materialy", fileList -> {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, fileList);
            list.setAdapter(arrayAdapter);
        });
    }

    private void updateRows() {
        ListView list = (ListView) findViewById(R.id.Listing);

        ArrayList<String> emptyArray = new ArrayList<>();
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyArray));

        addRows();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do
                    FileStorage.getInstance().download(Environment.getExternalStorageDirectory().getPath(), "3227.pdf" );
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getExtensionFromMimeType(cR.getType(resultData.getData()));
        FileStorage.getInstance().upload(resultData.getData(), type, () -> {updateRows();});
    }
}
