package com.rit.profileapplication.main_activity;

import com.rit.profileapplication.model.SearchResult;

import java.util.ArrayList;

public interface MainContract {
    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{
        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();
    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<SearchResult> resultArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetProfileIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<SearchResult> resultArrayList);
            void onFailure(Throwable t);
        }

        void getProfileArrayList(OnFinishedListener onFinishedListener);
    }
}
