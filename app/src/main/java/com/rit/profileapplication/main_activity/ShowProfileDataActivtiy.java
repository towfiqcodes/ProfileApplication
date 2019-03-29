package com.rit.profileapplication.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rit.profileapplication.R;
import com.squareup.picasso.Picasso;

public class ShowProfileDataActivtiy extends AppCompatActivity {
    public static final String TAG = ShowProfileDataActivtiy.class.getSimpleName();
    private TextView nameTv, idTv, jobTv, phoneTv;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_data_activtiy);
        setTitle("Show Profile");
        initializaation();
    }

    private void initializaation() {
        nameTv = findViewById(R.id.NameTV);
        idTv = findViewById(R.id.idNumberTV);
        jobTv = findViewById(R.id.jobTV);
        phoneTv = findViewById(R.id.phoneTV);
        imageView = findViewById(R.id.image);


        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        String job = getIntent().getStringExtra("job");
        String phone = getIntent().getStringExtra("phone");
        String image = getIntent().getStringExtra("image");
        Picasso.get().load(image).into(imageView);

        nameTv.setText(name);
        idTv.setText(id);
        jobTv.setText(job);
        phoneTv.setText(phone);

    }
}
