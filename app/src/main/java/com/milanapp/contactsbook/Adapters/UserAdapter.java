package com.milanapp.contactsbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.milanapp.contactsbook.Activities.DetailsActivity;
import com.milanapp.contactsbook.Model.Datum;
import com.milanapp.contactsbook.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<Datum> datumList = new ArrayList<>();



    public UserAdapter(Context context, List<Datum> datumList) {
        this.context = context;
        this.datumList = datumList;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.user_items,parent,false);
        final UserViewHolder holder =new UserViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.user_name.setText(datumList.get(position).getFirstName() + " " + datumList.get(position).getLastName());
        Glide.with(context).load(datumList.get(position).getAvatar()).into(holder.user_profile);


    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView user_profile;
        private TextView user_name;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            user_profile = itemView.findViewById(R.id.img_user_profile);
            user_name = itemView.findViewById(R.id.user_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, DetailsActivity.class);

                    intent.putExtra("firstName", datumList.get(getAdapterPosition()).getFirstName());
                    intent.putExtra("lastName", datumList.get(getAdapterPosition()).getLastName());
                    intent.putExtra("emailAdd", datumList.get(getAdapterPosition()).getEmail());
                    intent.putExtra("profilePhoto", datumList.get(getAdapterPosition()).getAvatar());

                    context.startActivity(intent);
                }
            });

        }


    }
}

