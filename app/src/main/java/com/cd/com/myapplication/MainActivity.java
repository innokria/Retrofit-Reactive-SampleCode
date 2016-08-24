package com.cd.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cd.com.myapplication.adapter.MoviesAdapter;
import com.cd.com.myapplication.model.Deal;
import com.cd.com.myapplication.model.POJO;
import com.cd.com.myapplication.rest.ApiClient;
import com.cd.com.myapplication.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    //used this class to test zip feature of reactive
    public class MyResult {
        public MyResult(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String a;
        public String b;
    }


    Observer<String> myObserver = new Observer<String>() {
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
            System.out.println("photo is====" + s);
            Log.d("MY OBSERVER===========", s);
            System.out.println("test=react=" + s);
            Log.d("RAK", s + "==");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        // ***********************reactive way handling ************************

        //This will execute the Observable on a new thread, and emit results through onNext on the main thread.


        //Observer can sit anywhere for example below
        /*POJO p = new POJO();

        Observable.just("one1", "two2", "three3", "four4", "five5")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(p.myObserver);
        */


        /*Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);*/


        Observable<String> myObservable1 = Observable.just("world");

        Observable<String> myObservable = Observable.just("Hello");

           myObservable.subscribe(myObserver);//open this to listen the output in observer we defined above
        // myObservable1.subscribe(myObserver);

        // ************************sample shows how to subscribe and  unsubscribe ************************
        // Subscription mySubscription = myObservable.subscribe(myObserver);
        // mySubscription.unsubscribe();

        //testing zip
        // ************************sample shows how to combine 2 operations and get the result together ************************
        Observable.zip(myObservable1, myObservable,
                new Func2<String, String, MyResult>() {

                    @Override
                    public MyResult call(String obs1Response,
                                         String obs2Response) {
                        MyResult a = new MyResult(obs1Response, obs2Response);
                        Log.d("RAK", a.a + "==" + a.b + "==combined outout");
                        return a;
                    }
                }).subscribe(new Action1<MyResult>() {
            @Override
            public void call(MyResult combined) {

            }
        });

        //ends




        // ***********************reactive way network operation handling ************************

        // Below are 2 Network operations use either of them to show the result as per requirement

        Observable<List<Deal>> responseObservable = apiService.getSubtitles();
        responseObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Deal>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Deal> titlesList) {

                        System.out.println("photo is== " + titlesList.get(0).getTitle() + titlesList.size());
                    }

                });


        // ***********************reactive way handling ************************

//__________________________________________________________________________________________________________//


        // ***********************retrofit way network handling ************************

        Call<List<Deal>> call = apiService.getSubtitle();
        call.enqueue(new Callback<List<Deal>>() {

            @Override
            public void onResponse(Call<List<Deal>> call, Response<List<Deal>> response) {

                List<Deal> movies = response.body();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Deal>> call, Throwable t) {
                Log.e("MainActivity", t.toString());
            }
        });

        // ***********************retrofit way handling ends ************************

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
