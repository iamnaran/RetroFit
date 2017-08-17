package com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.Models.Post;
import com.retrofit.Remote.ApiUtils;
import com.retrofit.Service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private TextView responseTv;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText title = (EditText) findViewById(R.id.et_title);
        final EditText body = (EditText) findViewById(R.id.et_body);
        responseTv = (TextView) findViewById(R.id.tv_response);
        final Button btnSubmit = (Button) findViewById(R.id.btn_submit);

        apiService = ApiUtils.getAPIService();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pTitle = title.getText().toString().trim();
                String pBody = body.getText().toString().trim();

                if (pTitle.isEmpty() && pBody.isEmpty()) {

                    Toast.makeText(PostActivity.this, "Empty", Toast.LENGTH_SHORT).show();

                } else {
                    sendPost(pTitle, pBody);
                }

            }
        });

    }

    private void sendPost(String pTitle, String pBody) {

        apiService.savePost(pTitle, pBody, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                }


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {


            }
        });


    }

    private void showResponse(String s) {

        if (responseTv.getVisibility() == View.GONE) {
            responseTv.setVisibility(View.VISIBLE);
        }
        responseTv.setText(s);
    }
}
