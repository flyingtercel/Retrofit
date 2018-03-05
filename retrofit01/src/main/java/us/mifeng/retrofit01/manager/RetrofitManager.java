package us.mifeng.retrofit01.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 黑夜之火 on 2018/3/5.
 */

public class RetrofitManager {
    private static OkHttpClient client;
    private static RetrofitManager manager;
    public static RetrofitManager getInstance(){
        if (manager == null){
            synchronized (RetrofitManager.class){
                if (manager == null){
                    manager = new RetrofitManager();
                }
            }
        }
        return manager;
    }
    private RetrofitManager(){
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpClient getClient(){
        if (manager == null){
            manager = getInstance();
        }
        return client;
    }

    public static Retrofit getRetrofit(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }
}
