package com.retrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retrofit.Models.Item;
import com.retrofit.R;

import java.util.List;

/**
 * Created by NaRan on 8/15/17.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private Context context;
    private List<Item> mItems;
    private PostItemListener postItemListener;

    public AnswerAdapter(Context context, List<Item> mItems, PostItemListener postItemListener) {
        this.context = context;
        this.mItems = mItems;
        this.postItemListener = postItemListener;
    }

    public AnswerAdapter(Context applicationContext, List<Item> itemList) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.simple_list_row, parent, false);
        ViewHolder viewHolder =new ViewHolder(view ,this.postItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getOwner().getDisplayName());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private TextView titleTv;
        PostItemListener postItemListener;


        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.textView);
            this.postItemListener =  postItemListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            Item item = getItem(getAdapterPosition());
            this.postItemListener.onPostClick(item.getAnswerId());

            notifyDataSetChanged();



        }
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
