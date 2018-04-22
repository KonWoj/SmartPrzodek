package com.example.wlaza.smart_przodek.FileManagement;

import com.example.wlaza.smart_przodek.FirebaseManagement.FirebaseDatabaseHandler;
import com.example.wlaza.smart_przodek.onFileListReady;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FileDatabase extends FirebaseDatabaseHandler {
    private static final FileDatabase instance = new FileDatabase();

    public static FileDatabase getInstance() {
        return instance;
    }

    public void updateFileList(String name, String ext) {
        database.child("materialy").child(name).setValue(ext);
    }

    public void getFileList(String directory, onFileListReady cb) {
        database.child(directory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> fileList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    fileList.add(snapshot.getKey().toString() + "." + snapshot.getValue().toString());
                }

                cb.result(fileList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
