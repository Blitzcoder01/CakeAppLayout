package com.hello.cakeapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmObject;

public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
    }

}
