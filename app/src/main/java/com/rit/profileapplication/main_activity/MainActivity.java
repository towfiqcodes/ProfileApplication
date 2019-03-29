package com.rit.profileapplication.main_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rit.profileapplication.R;
import com.rit.profileapplication.adapter.ProfileAdapter;
import com.rit.profileapplication.model.SearchResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();



        presenter = new MainPresenterImpl(this, new GetProfileIntractorImpl());
        presenter.requestDataFromServer();

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    private void initializeToolbarAndRecyclerView() {
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_profile_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }



    private RecyclerItemClickListener recyclerItemClickListener=new RecyclerItemClickListener() {
        @Override
        public void onItemClick(SearchResult searchResult) {
            Intent intent=new Intent(MainActivity.this,ShowProfileDataActivtiy.class);
            intent.putExtra("id",searchResult.getId());
            intent.putExtra("name",searchResult.getName());
            intent.putExtra("job",searchResult.getWho());
            intent.putExtra("phone",searchResult.getUser());
            intent.putExtra("image",searchResult.getImage());

            startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setDataToRecyclerView(ArrayList<SearchResult> resultArrayList) {
        ProfileAdapter adapter = new ProfileAdapter(resultArrayList , recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
