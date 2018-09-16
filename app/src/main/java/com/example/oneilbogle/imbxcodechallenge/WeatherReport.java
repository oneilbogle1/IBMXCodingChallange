package com.example.oneilbogle.imbxcodechallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oneilbogle.imbxcodechallenge.data.model.Get;

import java.util.List;

public class WeatherReport extends RecyclerView.Adapter<WeatherReport.ViewHolder> {


    private List<Get> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView wTitle;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);

            wTitle = (TextView) itemView.findViewById(android.R.id.text1);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Get witem = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(witem.getWoeid());

            notifyDataSetChanged();
        }
    }

    public WeatherReport(Context context, List<Get> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public WeatherReport.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherReport.ViewHolder holder, int position) {

        Get item = mItems.get(position);
        TextView textView = holder.wTitle;
        textView.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Get> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Get getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }


}