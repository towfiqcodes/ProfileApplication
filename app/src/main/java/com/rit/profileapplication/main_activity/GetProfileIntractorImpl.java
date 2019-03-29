package com.rit.profileapplication.main_activity;

import android.util.Log;

import com.rit.profileapplication.model.Profile;
import com.rit.profileapplication.model.SearchResult;
import com.rit.profileapplication.my_interface.GetProfileDataService;
import com.rit.profileapplication.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileIntractorImpl implements MainContract.GetProfileIntractor {
    @Override
    public void getProfileArrayList(final OnFinishedListener onFinishedListener) {
        GetProfileDataService service = RetrofitInstance.getRetrofitInstance().create(GetProfileDataService.class);
        Call<Profile> profileCall = service.getProfileData();

        Log.wtf("URL Called", profileCall.request().url() + "");
        profileCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                onFinishedListener.onFinished((ArrayList<SearchResult>) response.body().getSearchResult());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }


}
