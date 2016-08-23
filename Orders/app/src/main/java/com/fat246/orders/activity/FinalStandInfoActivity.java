package com.fat246.orders.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fat246.orders.R;
import com.fat246.orders.application.MyApplication;
import com.fat246.orders.bean.FinalInfo;
import com.fat246.orders.bean.FinalStandInfo;
import com.fat246.orders.parser.FinalInfoParser;

public class FinalStandInfoActivity extends AppCompatActivity {
    private String Order_Id;
    private Boolean Is_Passed;


    private TextView fCode;
    private TextView fName;
    private TextView fOrderq;
    private TextView fReceiveq;
    private TextView fStorageq;
    private TextView fReturnq;

    private ProgressBar progressBar;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_stand_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        Intent mIntent = getIntent();

        Bundle bundle = mIntent.getExtras();

        Order_Id = bundle.getString(FinalInfo.prhsord_id);
        Is_Passed = bundle.getBoolean(FinalInfo.is_passed);

        this.setTitle(Order_Id);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (Is_Passed) {

            fab.setImageResource(R.drawable.ic_action_cancle);
        } else {

            fab.setImageResource(R.drawable.ic_action_ok);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();

        showBar();

        new MyAsyncTask().execute(Order_Id);
    }

    private void initView() {

        fCode = (TextView) findViewById(R.id.fCode);
        fName = (TextView) findViewById(R.id.fName);
        fOrderq = (TextView) findViewById(R.id.fOrderq);
        fReceiveq = (TextView) findViewById(R.id.fReceiveq);
        fStorageq = (TextView) findViewById(R.id.fStorageq);
        fReturnq = (TextView) findViewById(R.id.fReturnq);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
    }

    class MyAsyncTask extends AsyncTask<String, Void, FinalStandInfo> {

        @Override
        protected FinalStandInfo doInBackground(String... params) {

            return new FinalInfoParser(MyApplication.getFinalStandInfoUrl(), params[0]).getFinalInfo();
        }

        @Override
        protected void onPostExecute(FinalStandInfo finalStandInfo) {


            fCode.append(":" + finalStandInfo.getfCode());
            fName.append(":" + finalStandInfo.getfName());
            fOrderq.append(":" + finalStandInfo.getfOrderq());
            fReceiveq.append(":" + finalStandInfo.getfReceiveq());
            fStorageq.append(":" + finalStandInfo.getfStorageq());
            fReturnq.append(":" + finalStandInfo.getfReturnq());


            hideBar();
        }
    }

    private void showBar() {

        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    private void hideBar() {

        progressBar.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }
}