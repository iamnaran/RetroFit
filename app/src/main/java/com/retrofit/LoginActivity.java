package com.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.retrofit.Models.Login;
import com.retrofit.Service.APIService;
import com.retrofit.URLConstants.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView username;
    private EditText password;
    private Button btnSignIn;

    private Button btnGoto, btnStack, btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (AutoCompleteTextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnGoto = (Button) findViewById(R.id.btnGoto);
        btnStack = (Button) findViewById(R.id.btnStack);
        btnPost = (Button) findViewById(R.id.btnPost);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogin();

            }
        });

        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });

        btnStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, StackActivity.class));

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PostActivity.class));
            }
        });




    }


    private void userLogin() {

        String user = username.getText().toString();
        String pass = password.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIService apiService = retrofit.create(APIService.class);


        Call<Login> call = apiService.userLogin(user, pass);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                Toast.makeText(LoginActivity.this, "Response" + response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Response" + t, Toast.LENGTH_SHORT).show();


            }
        });


    }
}


