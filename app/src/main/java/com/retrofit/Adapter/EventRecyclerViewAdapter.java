package com.retrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.retrofit.Models.Event;
import com.retrofit.R;

import java.util.List;

/**
 * Created by NaRan on 8/13/17.
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Event> eventList;

    public EventRecyclerViewAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }



    @Override
    public EventRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_row,parent,false);

        return new EventRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewAdapter.ViewHolder holder, int position) {

        final Event event = eventList.get(position);

        holder.name.setText(event.getName());
        holder.address.setText(event.getAddress());
        holder.date.setText(event.getDate());
        holder.phone.setText(event.getPhone());
        holder.eventDescription.setText(event.getEvent_description());
        Glide.with(context).load(event.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name, address, date, phone;
        private TextView eventDescription;


        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            phone = (TextView) itemView.findViewById(R.id.phone);
            date = (TextView) itemView.findViewById(R.id.date);
            eventDescription = (TextView) itemView.findViewById(R.id.event_description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
