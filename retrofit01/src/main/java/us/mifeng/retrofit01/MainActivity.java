package us.mifeng.retrofit01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import us.mifeng.retrofit01.bean.ContentData;
import us.mifeng.retrofit01.bean.MktBean;
import us.mifeng.retrofit01.bean.UserBean;
import us.mifeng.retrofit01.http.RequestServers;
import us.mifeng.retrofit01.manager.RetrofitManager;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setImageBitmap(bitmap);
        }
    };
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        //使用get请求下载一张图片
        //requestLoadImg();

        //requestLoadData();
        //requestLoadUserData();
        //requestLoadStrData();
        //requestPostLoadMktData();
        //requestHttpLoadMktData();
        //requestHttpLoadMktData2();
        //requestUpLoadImg();
        requestUpLoadImg2();
    }

    //模拟上传多个文件
    private void requestUpLoadImg2() {
        Retrofit retrofit = getRetrofit("http://api.dameiketang.com/");
        RequestServers rs = retrofit.create(RequestServers.class);

        File[] file = new File[4];
        Map<String,MultipartBody.Part> partMap = new HashMap<>();
        for(int i=0;i<file.length;i++){
            RequestBody body = RequestBody.create(MediaType.parse(getMediaType(file[i])),file[i]);
            MultipartBody.Part part = MultipartBody.Part.createFormData(file[0].getName(),file[0].getName(),body);
            partMap.put(file[i].getName(),part);
        }

        Call<ResponseBody> call = rs.upLoadBitmaps(partMap);
    }
    //模拟上传单个文件
    private void requestUpLoadImg() {
        Retrofit retrofit = getRetrofit("http://api.dameiketang.com/");
        RequestServers rs = retrofit.create(RequestServers.class);
        Map<String, RequestBody>map = new HashMap<>();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id","6894681b-ad8b-47e4-9f17-1cf07324464c");
        map.put("str",builder.build());

        File file = new File("");
        RequestBody body = RequestBody.create(MediaType.parse(getMediaType(file)),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("body","",body);
        rs.upLoadBitmap(map,part);
    }
    //使用map集合上传参数
    private void requestHttpLoadMktData2() {
        Retrofit retrofit = getRetrofit("http://api.dameiketang.com/");
        RequestServers rs = retrofit.create(RequestServers.class);
        Map<String,String>map = new HashMap<>();
        map.put("id","6894681b-ad8b-47e4-9f17-1cf07324464c");
        Call<MktBean> call = rs.getHttpMktData2(map);
        call.enqueue(new Callback<MktBean>() {
            @Override
            public void onResponse(Call<MktBean> call, Response<MktBean> response) {
                MktBean body = response.body();
                Log.i("tag", "onResponse: ==dd=44=="+body.getM());
            }

            @Override
            public void onFailure(Call<MktBean> call, Throwable t) {

            }
        });
    }
    //使用@Filed提交参数
    private void requestHttpLoadMktData() {
        Retrofit retrofit = getRetrofit("http://api.dameiketang.com/");
        RequestServers rs = retrofit.create(RequestServers.class);
        Call<MktBean> call = rs.getHttpMktData("6894681b-ad8b-47e4-9f17-1cf07324464c");
        call.enqueue(new Callback<MktBean>() {
            @Override
            public void onResponse(Call<MktBean> call, Response<MktBean> response) {
                MktBean body = response.body();
                Log.i("tag", "onResponse: ==dd==="+body.getM());
            }

            @Override
            public void onFailure(Call<MktBean> call, Throwable t) {

            }
        });

    }
    //获取Post请求
    private void requestPostLoadMktData() {
        Retrofit retrofit = getRetrofit("http://api.dameiketang.com/");
        RequestServers rs = retrofit.create(RequestServers.class);
        Call<MktBean> call = rs.getMktData("6894681b-ad8b-47e4-9f17-1cf07324464c");
        call.enqueue(new Callback<MktBean>() {
            @Override
            public void onResponse(Call<MktBean> call, Response<MktBean> response) {
                MktBean body = response.body();
                Log.i("tag", "onResponse: ==dd==="+body.getM());
            }

            @Override
            public void onFailure(Call<MktBean> call, Throwable t) {
                Log.i("tag", "onFailure: ==="+t.getMessage());
            }
        });
    }

    private void requestLoadStrData() {
        //获取对象
        Retrofit retrofit = getRetrofit("http://v.juhe.cn/");
        RequestServers rs = retrofit.create(RequestServers.class);
        //具有返回String字符串的get请求
        Call<String> call = rs.getStrData("社会", "e555890e7dd2500c97b7bf2a9cceae06");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String str = response.body();
                Log.i("tag","=="+str);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    //具有参数的get请求案例
    private void requestLoadUserData() {
        //获取Retrofit对象
        Retrofit retrofit = getRetrofit("http://v.juhe.cn/");
        //获取接口对象
        RequestServers rs = retrofit.create(RequestServers.class);
        //获取Call对象
        Call<UserBean> call = rs.getUserData("社会", "e555890e7dd2500c97b7bf2a9cceae06");
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean body = response.body();
                Log.i("tag","====="+body.getReason()+body.getResult().getData().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        });
    }

    //使用get请求网络数据，并返回JavaBean对象
    private void requestLoadData() {
        //创建Retrofit对象
        Retrofit retrofit = getRetrofit("http://fy.iciba.com/");
        //创建接口对象
        RequestServers requestServers = retrofit.create(RequestServers.class);
        //获取Call对象
        Call<ContentData> call = requestServers.getContentData();
        call.enqueue(new Callback<ContentData>() {
            @Override
            public void onResponse(Call<ContentData> call, Response<ContentData> response) {
                ContentData body = response.body();
                Log.i("tag","==sss====="+body.getContent().toString());
            }

            @Override
            public void onFailure(Call<ContentData> call, Throwable t) {

            }
        });
    }

    //使用get请求下载一行行图片案例
    private void requestLoadImg() {
        //第一步创建Retrofit对象
        Retrofit retrofit = getRetrofit("http://pic2.nipic.com/");
        //通过Retrofit调用create方法获取了接口对象
        RequestServers rs = retrofit.create(RequestServers.class);
        //接口对象调用接口中的方法,请求一张图片
        Call<ResponseBody> call = rs.getRequestImg();
        //执行异步请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                try {
                    byte[]bytes = body.bytes();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    Message message = handler.obtainMessage();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
    //获取Retrofit的对象
    private Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = RetrofitManager.getRetrofit(baseUrl);
        return retrofit;
    }
    public String getMediaType(File file){
        //获取文件名称对象类
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        //根据文件的名字去获取文件的类型
        String contentType = fileNameMap.getContentTypeFor(file.getName());
        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}
