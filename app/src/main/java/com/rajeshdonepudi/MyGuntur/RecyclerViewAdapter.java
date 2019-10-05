package com.rajeshdonepudi.MyGuntur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    public ArrayList<String> mImages;
    public ArrayList<String> mNames;
    public ArrayList<String> mLinks;
    public onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;

    }

    public RecyclerViewAdapter(Context context, ArrayList<String> images, ArrayList<String> names,ArrayList<String> links) {

        mImages = images;
        mNames = names;
        mContext = context;
        mLinks = links;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.imageView);
        holder.textView.setText(mNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      if(mListener != null ) {
                          int position = getAdapterPosition();
                          if (position != RecyclerView.NO_POSITION) {
                              mListener.onItemClick(position);
                          }
                      }
                }
            });
        }
    }
}
