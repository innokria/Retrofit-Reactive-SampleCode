package com.cd.com.myapplication.rest;

import com.cd.com.myapplication.model.Deal;
import com.cd.com.myapplication.model.JSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import rx.Observable;


public interface ApiInterface {


//************retrofit way**************

    @GET("Hotdeal.php")
    Call<List<Deal>> getSubtitle();


//************reactive way*****************

    @GET("Hotdeal.php")
    Observable<List<Deal>> getSubtitles();



   }
