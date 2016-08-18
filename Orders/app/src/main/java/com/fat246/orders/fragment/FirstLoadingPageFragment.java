package com.fat246.orders.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fat246.orders.R;
import com.fat246.orders.activity.LoginPage;
import com.fat246.orders.widget.CI.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class FirstLoadingPageFragment extends Fragment {

    //View First
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private Button mNowLogIn;

    private List<View> mViewList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflater View
        View rootView = inflater.inflate(R.layout.fragment_first_loading_page, container, false);

        setFirstView(rootView);

        setFirstData();

        setFirstListener();

        setSomeThing();

        return rootView;
    }

    public void setFirstView(View rootView) {

        mViewPager = (ViewPager) rootView.findViewById(R.id.mViewPager);
        mCircleIndicator = (CircleIndicator) rootView.findViewById(R.id.mCircleIndicator);
        mNowLogIn = (Button) rootView.findViewById(R.id.now_login);
    }

    public void setFirstData() {

        mViewList = new ArrayList<>();

        ImageView[] mImages = new ImageView[5];

        for (int i = 0; i < 5; i++) {

            mImages[i] = new ImageView(getActivity());

            mImages[i].setScaleType(ImageView.ScaleType.FIT_XY);
        }

        mImages[0].setImageResource(R.drawable.pic1);
        mImages[1].setImageResource(R.drawable.pic2);
        mImages[2].setImageResource(R.drawable.pic3);
        mImages[3].setImageResource(R.drawable.pic4);
        mImages[4].setImageResource(R.drawable.pic5);

        for (int i = 0; i < 5; i++) {

            mViewList.add(mImages[i]);
        }
    }

    //监听事件
    public void setFirstListener() {

        mNowLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(getActivity(), LoginPage.class);

                //改为从配置文件里面获取过后就不需要传入配置文件了
//                UserInfo.setData(mIntent);

                startActivity(mIntent);

                //动画

                getActivity().finish();
            }
        });


    }

    public void setSomeThing() {

        //
        mViewPager.setAdapter(pagerAdapter);
        mCircleIndicator.setViewPager(mViewPager);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return mViewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(mViewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "title";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));

            return mViewList.get(position);
        }

    };
}
