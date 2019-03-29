package com.rit.profileapplication.my_interface;

import com.rit.profileapplication.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetProfileDataService {

    @GET("bins/15baeq/")
    Call<Profile> getProfileData();
}
