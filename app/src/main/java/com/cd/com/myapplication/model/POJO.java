package com.cd.com.myapplication.model;

/**
 * Created by kumar0044q on 8/11/2016.
 */

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rx.Observer;


public class POJO {

  public   Observer<String> myObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            // Called when the observable has no more data to emit
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
        }

        @Override
        public void onNext(String s) {
            // Called each time the observable emits data
            System.out.println("photo is===="+s);
            Log.d("MY OBSERVER===========", s);
            System.out.println("test=react="+s);
            Log.d("RAKS",s+"printed");
        }
    };

}