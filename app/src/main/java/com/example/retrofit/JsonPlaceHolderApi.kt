package com.example.retrofit

import com.example.retrofit.Model.Comments
import com.example.retrofit.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts")
    fun getPosts(
        @Query("userId") userId: Int
        //@Query("userId") userId:Array<Int>,
        //@Query("_sort") sortBy: String,
        //@Query("_order") orderBy: String
        //@QueryMap parameters: Map<String, String>
    ): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<Comments>>

    @GET
    fun getComments(@Url url: String): Call<List<Comments>>

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @FieldMap fields: HashMap<String, String>
    ): Call<Post>

    @PUT("posts/{id}")
    fun putPost(
        @Path("id") id: Int
        , @Body post: Post
    ): Call<Post>

    @PATCH("posts/{id}")
    fun putPatch(
        @Path("id") id: Int
        , @Body post: Post
    ): Call<Post>

    @DELETE("posts/{id}")
    fun delete(
        @Path("id") id: Int
    ): Call<Unit>
}