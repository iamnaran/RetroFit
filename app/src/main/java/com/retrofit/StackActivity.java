package com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.retrofit.Adapter.AnswerAdapter;
import com.retrofit.Models.Item;
import com.retrofit.Models.SOAnswersResponse;
import com.retrofit.Remote.ApiUtils;
import com.retrofit.Remote.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StackActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnswerAdapter answerAdapter;
    private SOService mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);


        mService = ApiUtils.getSOService();
        recyclerView = (RecyclerView) findViewById(R.id.rRecyclerView);



        answerAdapter = new AnswerAdapter(this, new ArrayList<Item>(0), new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {

                Toast.makeText(StackActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();

            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(answerAdapter);


        getAnswer();




    }

    private void getAnswer() {

        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if (response.isSuccessful()){

                    answerAdapter.updateAnswers(response.body().getItems());


                }else {
                    int status = response.code();
                }

            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

            }
        });



    }
}
