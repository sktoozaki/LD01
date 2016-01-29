package com.example.kk.ld01.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kk.ld01.R;

import org.joda.time.DateTime;

/**
 * Created by KK on 2016/1/29.
 */
public class DayFragment extends Fragment {
    private ListView mListView;

    private DateTime dateTime;

    public DayFragment(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fagment_day,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initService();
    }

    private void initService() {

    }

    private void initViews() {
        mListView= (ListView) getView().findViewById(R.id.listview_day);
    }
}
