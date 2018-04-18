package com.znxk.charge.net.netconfig;


import com.znxk.charge.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/23.
 */
public class NetJsonUtil {

    public static <T> T getRetrofit(String baseUrl, Class<T> service) {
        try {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            if(BuildConfig.DEBUG){
                //日志拦截器
                builder.addNetworkInterceptor(new HttpLoggingInterceptor());
            }
            OkHttpClient client = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return (T) retrofit.create(service);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
