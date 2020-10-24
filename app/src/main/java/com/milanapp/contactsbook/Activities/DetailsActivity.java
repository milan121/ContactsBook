package com.milanapp.contactsbook.Activities;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.milanapp.contactsbook.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView tv_first_name,tv_last_name,tv_email_add;
    private CircleImageView img_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_first_name = findViewById(R.id.tv_first_name);
        tv_last_name = findViewById(R.id.tv_last_name);
        tv_email_add = findViewById(R.id.tv_email_add);
        img_profile = findViewById(R.id.img_user_profile);


        String FirstName = (getIntent().getExtras().getString("firstName"));
        String LastName = (getIntent().getExtras().getString("lastName"));
        String Email = (getIntent().getExtras().getString("emailAdd"));
        String ProfilePhoto = (getIntent().getExtras().getString("profilePhoto"));



        tv_first_name.setText(FirstName);
        tv_last_name.setText(LastName);
        tv_email_add.setText(Email);

        Glide.with(DetailsActivity.this).load(ProfilePhoto).into(img_profile);

    }
}

