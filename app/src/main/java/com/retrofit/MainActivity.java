package com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.retrofit.Adapter.EventRecyclerViewAdapter;
import com.retrofit.Models.Event;
import com.retrofit.Service.APIService;
import com.retrofit.URLConstants.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    List<Event> eventList = new ArrayList<>();
    RecyclerView recyclerView;

    EventRecyclerViewAdapter eventRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventRecyclerViewAdapter = new EventRecyclerViewAdapter(getApplicationContext(),eventList);
        recyclerView.setAdapter(eventRecyclerViewAdapter);

        getEventData();


    }



    private void initialize() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



    }


    @Override
    public void onClick(View view) {

    }

    private void getEventData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.EVENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<List<Event>> call = apiService.getEventsData();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                eventList.addAll(response.body());
                eventRecyclerViewAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

    }


}
