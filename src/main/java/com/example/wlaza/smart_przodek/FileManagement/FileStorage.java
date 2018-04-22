package com.example.wlaza.smart_przodek.FileManagement;

import android.net.Uri;

import com.example.wlaza.smart_przodek.FirebaseManagement.FirebaseStorageHandler;
import com.example.wlaza.smart_przodek.onUploadReady;

public class FileStorage extends FirebaseStorageHandler {
    private static final FileStorage instance = new FileStorage();

    public static FileStorage getInstance() {
        return instance;
    }

    @Override
    public void upload(Uri uri, String ext, onUploadReady cb) {
        super.upload(uri, ext, cb);
        FileDatabase.getInstance().updateFileList(uri.getLastPathSegment(), ext);
    }
}
