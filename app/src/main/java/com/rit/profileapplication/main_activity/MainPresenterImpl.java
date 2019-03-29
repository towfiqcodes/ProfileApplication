package com.rit.profileapplication.main_activity;

import com.rit.profileapplication.model.SearchResult;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetProfileIntractor.OnFinishedListener {
    private MainContract.MainView mainView;
    private MainContract.GetProfileIntractor getProfileIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetProfileIntractor getProfileIntractor) {
        this.mainView = mainView;
        this.getProfileIntractor = getProfileIntractor;
    }


    @Override
    public void onDestroy() {
        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {
        if (mainView != null) {
            mainView.showProgress();
        }
        getProfileIntractor.getProfileArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getProfileIntractor.getProfileArrayList(this);

    }

    @Override
    public void onFinished(ArrayList<SearchResult> resultArrayList) {
        if (mainView != null) {
            mainView.setDataToRecyclerView(resultArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
