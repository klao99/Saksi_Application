package com.chutikarn.saksi_application.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseManager {
    private DatabaseReference databaseReference;
    public FirebaseManager(){databaseReference = FirebaseDatabase.getInstance().getReference();}

    private ValueEventListener setListener(final FirebaseCallbacks listener){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFail();
            }
        };
    }

    public synchronized void getTattoosImg(final FirebaseCallbacks listener){
        listener.onLoading();
        databaseReference.child("tattooPic").addValueEventListener(setListener(listener));
    }
    public synchronized void getTattooImgByID(String id , final FirebaseCallbacks listener){
        listener.onLoading();
        databaseReference.child("tattooPic").child(id).addValueEventListener(setListener(listener));
    }



}
