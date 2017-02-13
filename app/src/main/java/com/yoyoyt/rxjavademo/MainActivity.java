package com.yoyoyt.rxjavademo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();

        Toast.makeText(MainActivity.this,"你好",Toast.LENGTH_LONG).show();
    }

    //进行网络请求
    private void getMovie(){
        //基本请求网址
        String baseUrl="https://api.douban.com/v2/movie/";
        //得到返回的retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        //得到用户信息
        Users users = retrofit.create(Users.class);

        users.getUser(0,10).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Log.e("onNext", "onCompleted: " );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                Log.e("onNext", "onNext: "+user.toString() );
                resultTV.setText(user.toString());
                Toast.makeText(MainActivity.this,user.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * scheduler的使用
     *
     * @param view
     */
    public void scheduler(View view) {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())//指定subscribe运行在子线程中
                .observeOn(AndroidSchedulers.mainThread())//指定subscribe的回调运行在主线程中
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("tag", "call: " + integer);
                    }
                });
        Observable ob = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

            }
        });
        ob.lift(new Observable.Operator() {
            @Override
            public Object call(Object o) {
                return new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                };
            }
        });
    }

    /**
     * 变换
     *
     * @param view
     */
    public void map(View view) {
        //变换的使用
        /**
         * map(): 事件对象的直接变换，具体功能上面已经介绍过。它是 RxJava 最常用的变换。
         */
        Observable.just("imager/logo.png").map(new Func1<String, Bitmap>() {

            @Override
            public Bitmap call(String s) {
                return null;
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {

            }
        });
    }

    /**
     * doOnSubscribe的使用
     *
     * @param view
     */
    public void doOnSubscribe(View view) {
        String url = "https://api.douban.com/v2/movie/top250?start=0&count=10";
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                ProgressDialog p = new ProgressDialog(MainActivity.this);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

    }
}
