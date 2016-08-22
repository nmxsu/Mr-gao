
package com.fat246.orders.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fat246.orders.R;
import com.fat246.orders.application.MyApplication;
import com.fat246.orders.bean.TimeInfo;
import com.fat246.orders.bean.TimeStandInfo;
import com.fat246.orders.parser.TimeInfoParser;
/*import com.fat246.orders.bean.OrderInfo;
import com.fat246.orders.bean.OrderStandInfo;
import com.fat246.orders.parser.OrderInfoParser;*/

public class TimeStandInfoActivity extends AppCompatActivity {

    private String Order_Id;
    private Boolean Is_Passed;

    private TextView tCreate;
    private TextView tSerive;
    private TextView tCommit;
    private TextView tApprove;
    private TextView tFinish;

    private LinearLayout linearLayout;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_stand_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        Intent tIntent = getIntent();

        Bundle bundle = tIntent.getExtras();

        Order_Id = bundle.getString(TimeInfo.prhsord_id);
        Is_Passed = bundle.getBoolean(TimeInfo.is_passed);

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

        tCreate   = (TextView) findViewById(R.id.tCreate);
        tSerive = (TextView) findViewById(R.id.tSerive);
        tCommit = (TextView) findViewById(R.id.tCommit);
        tApprove = (TextView) findViewById(R.id.tApprove);
        tFinish = (TextView) findViewById(R.id.tFinish);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
    }

    class MyAsyncTask extends AsyncTask<String, Void, TimeStandInfo> {

        @Override
        protected TimeStandInfo doInBackground(String... params) {

            return new TimeInfoParser(MyApplication.getTimeStandInfoUrl(), params[0]).getTimeInfo();
        }

        @Override
        protected void onPostExecute(TimeStandInfo timeStandInfo) {

            tCreate.append(": " + timeStandInfo.gettCreate());
            tSerive.append(": " + timeStandInfo.gettSerive());
            tCommit.append(": " + timeStandInfo.gettCommit());
            tApprove.append(": " + timeStandInfo.gettApprove());
            tFinish.append(": " + timeStandInfo.gettFinish());


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