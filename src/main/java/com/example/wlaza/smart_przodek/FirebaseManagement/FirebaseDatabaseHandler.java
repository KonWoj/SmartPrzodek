package com.example.wlaza.smart_przodek.FirebaseManagement;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHandler {
    private static final FirebaseDatabaseHandler instance = new FirebaseDatabaseHandler();
    protected DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public static FirebaseDatabaseHandler getInstance() {
        return instance;
    }
}
