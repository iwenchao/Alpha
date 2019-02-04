package cn.iwenchaos.alpha.net.retrofit;

/**
 * Created by chaos
 * on 2019/2/2. 17:14
 * 文件描述：
 *
 * retrofit支持http协议中所有的请求方式
 * 1. @GET
 * 2. @POST
 * 3. @PUT
 * 4. @DELETE
 * 5. @HEAD
 * 6. @OPTIONS
 * 7. @PATH
 * 还有
 * 8. @HTTP 用于替换上面7个注解的作用以及更多的功能扩展
 *
 * 标记类
 * 1. @FormURLEncoded:
 *      表示请求体是一个form表单；Content-Type：application/x-www-form-urlencoded
 * 2. @Multipart
 *      表示请求体是一个支持文件上传的form表单；Content-Type：multipart/form-data
 * 3. @Streaming
 *      表示响应体的数据是以流的方式返回。
 *      如果没有使用该注解，则默认把数据全部载入内容。之后你通过流获取数据也不过是读取内存中的数据。
 *      如果数据量大，可能引起内存不足。所以应该用该注解，使用流的方式
 *
 * 网络请求参数
 *
 *  作用于方法
 * 1. @Headers：用于添加请求头
 *
 * 作用于方法参数
 * 2. @Header：用于添加不固定值的请求头字段
 * 4. @Body ： 用于非表单请求体
 *
 *  作用于表单字段
 * 6. @Field
 * 7. @FieldMap  Map<String,String> 这两个Fieldx与FormUrlEncoded注解配合
 * 8. @Part
 * 9. @PartMap  Map<String,RequestBody>这两个Partx与Multipart注解配合，适合文件上传的场景
 *
 *
 * 作用于url，字段拼接到url上
 * 3. @URL
 * 5. @Path ： 同{占位符}使用，拼接到url
 * 10. @Query  同{占位符}使用，拼接到url
 * 11. @QueryMap  同Field   同{占位符}使用，拼接到url
 *
 *
 *
 * Query，Field，Part这三者都支持数组和实现了Iterable接口的类型，如List，Set等
 */
public class Doc {



}
