package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.retrofit.Model.Comments
import com.example.retrofit.Model.Post
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        retrofit = RetrofitInstance.getInstance()
        jsonPlaceHolderApi = retrofit?.create(JsonPlaceHolderApi::class.java)

        //getPosts()
        //getPostsWithQuery()
        //getComments()
        //getCommentsUsingUrl(2)
        //createPost()
        //createPostUsingUrlEncoded()
        //createPostUsingHashMap()
        //updatePostUsingPut()
        //updatePostUsingPatch()
        //deletePost()
    }

    fun getPosts() {
        val call = jsonPlaceHolderApi?.getPosts()

        call?.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val posts = response.body()
                posts?.forEach {
                    binding.retrofitContent.append("Id:${it.id}\nUserId: ${it.userId}\nTilte: ${it.title}\n\n")
                }
            }

        })
    }

    fun getPostsWithQuery() {
//        val map = LinkedHashMap<String,String>() // use linkedhashmap bcz hashmap doesnt gurantee order
//        map["userId"] = "1"
//        map["_sort"] = "id"
//        map["_order"] = "desc"

        val call = jsonPlaceHolderApi?.getPosts(2)

        call?.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val posts = response.body()
                posts?.forEach {
                    binding.retrofitContent.append("Id:${it.id}\nUserId: ${it.userId}\nTilte: ${it.title}\n\n")
                }
            }

        })
    }

    fun getComments() {
        val call = jsonPlaceHolderApi?.getComments(2)

        call?.enqueue(object :Callback<List<Comments>>{
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val posts = response.body()
                posts?.forEach {
                    binding.retrofitContent.append("PostId:${it.postId}\nEmail: ${it.email}\nBody: ${it.body}\n\n")
                }
            }

        })
    }


    fun getCommentsUsingUrl(postId:Int) {
        val call = jsonPlaceHolderApi?.getComments("posts/${postId}/comments")

        call?.enqueue(object :Callback<List<Comments>>{
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val posts = response.body()
                posts?.forEach {
                    binding.retrofitContent.append("PostId:${it.postId}\nEmail: ${it.email}\nBody: ${it.body}\n\n")
                }
            }

        })
    }

    fun createPost(){
        val post = Post(null,23,"Create Post","A New Post is created")
        val call = jsonPlaceHolderApi?.createPost(post)

        call?.enqueue(object :Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val createdPost = response.body()
                binding.retrofitContent.text = "Id : ${createdPost?.id}\nUserId : ${createdPost?.userId}\nTitle : ${createdPost?.title}\nBody : ${createdPost?.body}"
            }

        })
    }

    fun createPostUsingUrlEncoded(){
        val post = jsonPlaceHolderApi?.createPost(25,"New Post","How is life?")

        post?.enqueue(object :Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val createdPost = response.body()
                binding.retrofitContent.text = "Id : ${createdPost?.id}\nUserId : ${createdPost?.userId}\nTitle : ${createdPost?.title}\nBody : ${createdPost?.body}"
            }

        })
    }

    fun createPostUsingHashMap(){
        val map = HashMap<String,String>()
        map["userId"] = "28"
        map["title"] = "Hash Map"
        map["body"] = "Better way of doing it"

        val post = jsonPlaceHolderApi?.createPost(map)

        post?.enqueue(object :Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val createdPost = response.body()
                binding.retrofitContent.text = "Id : ${createdPost?.id}\nUserId : ${createdPost?.userId}\nTitle : ${createdPost?.title}\nBody : ${createdPost?.body}"
            }

        })
    }

    fun updatePostUsingPut(){
        val post = Post(null,12,null,"Updated Body")
        val call = jsonPlaceHolderApi?.putPost(5,post)

        call?.enqueue(object :Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val createdPost = response.body()
                binding.retrofitContent.append("Id : ${createdPost?.id}\nUserId : ${createdPost?.userId}\nTitle : ${createdPost?.title}\nBody : ${createdPost?.body}\n\n")
            }

        })
    }

    fun updatePostUsingPatch(){
        val post = Post(null,12,null,"Updated Body")
        val call = jsonPlaceHolderApi?.putPatch(5,post)

        call?.enqueue(object :Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    Log.d("onFailure:", response.code().toString())
                }

                val createdPost = response.body()
                binding.retrofitContent.append("Id : ${createdPost?.id}\nUserId : ${createdPost?.userId}\nTitle : ${createdPost?.title}\nBody : ${createdPost?.body}")
            }

        })
    }

    fun deletePost(){
        val call = jsonPlaceHolderApi?.delete(10)
        call?.enqueue(object :Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("onError:", t.message.toString())
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val deletedBody = response.body()
                binding.retrofitContent.text = "${response.code()} ${deletedBody}"
            }

        })
    }
}
