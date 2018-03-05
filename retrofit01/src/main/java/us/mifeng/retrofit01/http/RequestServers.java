package us.mifeng.retrofit01.http;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import us.mifeng.retrofit01.bean.ContentData;
import us.mifeng.retrofit01.bean.MktBean;
import us.mifeng.retrofit01.bean.UserBean;

/**
 * Created by 黑夜之火 on 2018/3/2.
 */

public interface RequestServers {
    //http://pic2.nipic.com/20090424/1242397_110033072_2.jpg
    //使用GET请求
    @GET("20090424/1242397_110033072_2.jpg")
    Call<ResponseBody>getRequestImg();

    //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<ContentData>getContentData();

    /**
     * http://v.juhe.cn/:BaseUrl
     * toutiao/index?   路径字段
     * type=社会&key=e555890e7dd2500c97b7bf2a9cceae06 提交的参数
     * @return
     * @Query通过键值对的方式向服务器传递数据，
     */
    //http://v.juhe.cn/toutiao/index?type=社会&key=e555890e7dd2500c97b7bf2a9cceae06
    @GET("toutiao/index?")
    Call<UserBean>getUserData(@Query("type")String type,@Query("key")String key);

    //获取一个String类型的字符串
    @GET("toutiao/index?")
    Call<String>getStrData(@Query("type")String type,@Query("key")String key);

    //http://api.dameiketang.com/manager.php?m=Admin&c=Threevesion&a=IndexPageData
    @POST("manager.php?m=Admin&c=Threevesion&a=IndexPageData")
    @FormUrlEncoded
    Call<MktBean>getMktData(@Field("id") String uuid);

    @HTTP(method = "POST",path = "manager.php?m=Admin&c=Threevesion&a=IndexPageData",hasBody = true)
    @FormUrlEncoded
    Call<MktBean>getHttpMktData(@Field("id")String id);

    @HTTP(method = "POST",path = "manager.php?m=Admin&c=Threevesion&a=IndexPageData",hasBody = true)
    @FormUrlEncoded
    Call<MktBean>getHttpMktData2(@FieldMap()Map<String,String>map);
    //上传一张图pain

    @POST("")
    Call<ResponseBody>upLoadBitmap(@FieldMap() Map<String, RequestBody>map, @Part MultipartBody.Part part);

    @POST("")
    Call<ResponseBody>upLoadBitmaps(@PartMap Map<String,MultipartBody.Part>map);
}
