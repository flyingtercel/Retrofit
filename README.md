# Retrofit
Retrofit案例学习

###### RxJava依赖
```
    compile 'io.reactivex:rxjava:1.1.0'
    compile 'io.reactivex:rxandroid:1.1.0'
```
###### Retrofit依赖
```
    compile 'com.squareup.retrofit2:retrofit:2.0.2'  //Retrofit2所需要的包
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'  //ConverterFactory的Gson依赖包
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'    //ConverterFactory的Observer依赖包
    compile 'com.squareup.retrofit2:converter-scalars:2.0.2' //ConverterFactory的String依赖包
    compile 'com.google.code.gson:gson:2.6.2'
```
在这里遇到一些坑，如果下面三个少一个，返回他不支持的结果时，就会崩溃，这也是上面为什么会导那么多包的原因。

Retrofit提供的请求方式注解有两种@GET和@POST，参数注解有@Path和@Query。
@PATH通过参数填充完整的路径
```
@GET("{name}")
    Call<User>getUser(@Path("name")String name);
```
@Query就是我们请求键值对的设置
```
@POST("mobileLogin/submit.html")
    Call<String>getString(@Query("longinname")String name,@Query("longinpass")String pass);
```
###### 接口列表：
```

地址	         请求方法	       参数	                                    说明
/blog	     GET	    page={page},sort=asc或desc	            分页获取Blog列表,每页10条
/blog/{id}	 GET	    id	                                    获取指定ID的Blog
/blog	     POST       {"author":"","title":"","content":""}	创建一个新Blog
/blog/{id}	 PUT	    {"author":"","title":"","content":""}   中至少一个	修改Blog
/blog/{id}	 DELETE	    id	                                    删除一个Blog
/form	     POST       任意,最终以Json Object形式返回	        用于测试Form表单，支持文件上传
/headers	 GET	    showAll=true或false,默认false	        返回自定义请求头，all=true是显示全部
注：以上的接口的{id}和{page}均代表一个纯数字，/blog/{id} 可以用 /blog?id=XXX 代替，page同理。

```
###### 参数一
![参数一](https://github.com/flyingtercel/Retrofit/blob/master/retrofit01/src/main/res/drawable/s0.png)  </br>
###### 参数二
![参数二](https://github.com/flyingtercel/Retrofit/blob/master/retrofit01/src/main/res/drawable/s1.png)

