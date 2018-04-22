package com.example.wlaza.smart_przodek.FirebaseManagement;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.wlaza.smart_przodek.FileManagement.FileDownloader;
import com.example.wlaza.smart_przodek.onUploadReady;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.MalformedURLException;
import java.net.URL;
import com.example.wlaza.smart_przodek.FileManagement.FileDownloader;

public class FirebaseStorageHandler {
    private static final FirebaseStorageHandler instance = new FirebaseStorageHandler();
    StorageReference firebaseRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://smartprzodek-83281.appspot.com");

    public void upload(Uri uri, String ext, onUploadReady Cb){
        StorageReference materialyRef = firebaseRef.child("materialy/" + uri.getLastPathSegment() + "." + ext);
        UploadTask uploadTask = materialyRef.putFile(uri);
        //Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                exception.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Cb.action();
            }
        });
    }

    public void download(String directory, String filename) {
        firebaseRef.child("materialy/" + filename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    new FileDownloader(directory, filename).execute(new URL(uri.toString()));
                }
                catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    public static FirebaseStorageHandler getInstance() {
        return instance;
    }
}
