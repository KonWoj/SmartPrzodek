package com.example.wlaza.smart_przodek.FileManagement;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class FileSelector {
    private static FileSelector instance = new FileSelector();

    public static FileSelector getInstance() {
        return instance;
    }

    public void selectFile(Activity caller) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse("/"); // a directory
        intent.setDataAndType(uri, "*/*");
        caller.startActivityForResult(intent, 42);
    }
}
