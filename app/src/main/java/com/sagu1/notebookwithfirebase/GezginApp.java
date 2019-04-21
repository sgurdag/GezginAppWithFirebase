package com.sagu1.notebookwithfirebase;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class GezginApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
