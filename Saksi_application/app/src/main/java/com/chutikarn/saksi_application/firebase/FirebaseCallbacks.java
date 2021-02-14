package com.chutikarn.saksi_application.firebase;

import com.google.firebase.database.DataSnapshot;

public interface  FirebaseCallbacks {
    void onSuccess(DataSnapshot dataSnapshot);
    void onLoading();
    void onFail();
}
