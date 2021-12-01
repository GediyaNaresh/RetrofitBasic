package com.nareshgediya.retrofitbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nareshgediya.retrofitbasic.Models.Comment;
import com.nareshgediya.retrofitbasic.Models.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;
ProgressBar progressBar;
    MyWebService myWebService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv1);
        progressBar = findViewById(R.id.p1);

        progressBar.setVisibility(View.VISIBLE);
        myWebService = MyWebService.retrofit.create(MyWebService.class);

       getPosts();
      //  getComments();

    // createPost();
//    updatePost();

    //    deletePost();




    }

    private void deletePost() {
Call<Void> deletCall = myWebService.deletePost(12);

deletCall.enqueue(new Callback<Void>() {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
            progressBar.setVisibility(View.INVISIBLE);
            textView.append(String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
});

    }

    private void updatePost() {
        Post post1 = new Post(20,"New Title ", null);

        Call<Post> putPost = myWebService.patchPost(2,post1);

        putPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.append(String.valueOf(response.code()));
                    Post post = response.body();

                    textView.append("\n"+"Id :" +post.getId()+"\n"+"UID :" +post.getUserId()+"\n"+"Title :" +post.getTitle()+"\n"+"body :" +post.getBody()+"\n\n");

                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText("Error : "+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createPost() {

        myWebService = MyWebService.retrofit.create(MyWebService.class);


        Map<String , Object> map = new HashMap<>();
        map.put("userId", 12);
        map.put("title", "This is map Title");
        map.put("body", "This is MapBody");

       // Call<Post> call = myWebService.createPost(100,"This New", "New Body");

        Call<Post> call = myWebService.createPost(map);




        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.append(String.valueOf(response.code()));
                   Post post = response.body();
                       textView.append("\n"+"Id :" +post.getId()+"\n"+"UID :" +post.getUserId()+"\n"+"Title :" +post.getTitle()+"\n"+"body :" +post.getBody()+"\n\n");

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText("Error : "+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPosts() {
        myWebService = MyWebService.retrofit.create(MyWebService.class);

        Call<List<Post>> call = myWebService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    for (Post post : response.body()){
                        textView.append("Id :" +post.getId()+"\n"+"UID :" +post.getUserId()+"\n"+"Title :" +post.getTitle()+"\n"+"body :" +post.getBody()+"\n\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getComments() {
        myWebService = MyWebService.retrofit.create(MyWebService.class);

      //  Call<List<Comment>> call = myWebService.getComments(8,"id", "desc");
      //  Call<List<Comment>> call1 = myWebService.getComments(null,"id", "desc");

        Map<String , String> map = new HashMap<>();
        map.put("id","1");
        map.put("_sort","id");
        map.put("_order","desc");

//        map.put("_sort","email");
//        map.put("_order","desc");


        Call<List<Comment>> call1 = myWebService.getComments("id","desc",1,2,3,4,100,150,200,250,300);

        call1.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    for (Comment post : response.body()){

                        textView.append("Id : " +post.getId()+"\n"+"UID : " +post.getPostId()+"\n"+"Title : " +post.getEmail()+"\n"+"body : " +post.getBody()+"\n\n");

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText("Error : "+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}