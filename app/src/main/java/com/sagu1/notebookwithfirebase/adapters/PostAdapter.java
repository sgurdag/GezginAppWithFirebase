package com.sagu1.notebookwithfirebase.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagu1.notebookwithfirebase.R;
import com.sagu1.notebookwithfirebase.models.PostModel;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<PostModel> postList;
    Context context;
    RecyclerViewItemClikcListener imageClickListener;

    private static int SENT = 0;
    private static int RECEIVED = 1;

    public PostAdapter(ArrayList<PostModel> postList, Context context, RecyclerViewItemClikcListener imageClickListener) {
        this.postList = postList;
        this.context = context;
        this.imageClickListener = imageClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        // View Type kullanımına örnek olması için yaptık.

        if (postList.get(position).fromMe)
            return SENT;
        else{
            return RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = null;
        if (viewType == SENT)
                v = LayoutInflater.from(context).inflate(R.layout.post_list,viewGroup,false);

            else if( viewType == RECEIVED)
                v = LayoutInflater.from(context).inflate(R.layout.post_list,viewGroup,false);

        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        PostHolder holder = (PostHolder) viewHolder;
        final PostModel postModel = postList.get(position);

        holder.name.setText(postModel.getPostName());
        holder.description.setText(postModel.getPostDescription());
        holder.postPicture.setImageResource(postModel.getPostPicture());
        holder.postPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickListener.onPostClicked(postModel);
            }
        });

        holder.postPicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                imageClickListener.onitemDeleted(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class PostHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView description;
        public ImageView postPicture;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.post_title);
            description = itemView.findViewById(R.id.post_description);
            postPicture = itemView.findViewById(R.id.post_picture);

        }
    }

    public interface RecyclerViewItemClikcListener{
        void onPostClicked(PostModel postModel);
        void onitemDeleted (int position);
    }
}
