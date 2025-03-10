package com.example.dbgroupwork.Presentation

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class FitApp : Application() {
}

fun FitApp.getFirestore(): FirebaseFirestore {
    return Firebase.firestore
}
