package com.llkj.creditchecking.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.llkj.creditchecking.R;
import com.llkj.creditchecking.base.BaseActivity;
import com.llkj.creditchecking.listener.CustomTabEntity;
import com.llkj.creditchecking.listener.OnTabSelectListener;
import com.llkj.creditchecking.model.TabEntity;
import com.llkj.creditchecking.module.home.NewsTabFragment;
import com.llkj.creditchecking.module.im.MsgFragment;
import com.llkj.creditchecking.utils.ToastUtils;
import com.llkj.creditchecking.utils.UnreadMsgUtils;
import com.llkj.creditchecking.widget.CommonTabLayout;
import com.llkj.creditchecking.widget.MsgView;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

import static com.llkj.creditchecking.utils.ConvertUtils.dp2px;

public class CacheDemoActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tl_1)
    CommonTabLayout mTabLayout_1;

    private String[] mTitles = {"首页", "消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_demo);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        NewsTabFragment fragment1 = NewsTabFragment.newInstance();
        fragment1.setTitle("国内最新");
        fragments.add(fragment1);
        MsgFragment fragment2 = MsgFragment.newInstance();
        fragment2.setTitle("游戏焦点");
        fragments.add(fragment2);
        NewsTabFragment fragment3 = NewsTabFragment.newInstance();
        fragment3.setTitle("娱乐焦点");
        fragments.add(fragment3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.addOnPageChangeListener(this);

        mTabLayout_1.setTabData(mTabEntities);

        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_1.showMsg(0, position);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        mTabLayout_1.setCurrentTab(0);

        //两位数
        mTabLayout_1.showMsg(0, 55);
        mTabLayout_1.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout_1.showMsg(1, 100);
        mTabLayout_1.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout_1.showDot(2);
        MsgView rtv_2_2 = mTabLayout_1.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout_1.showMsg(3, 5);
        mTabLayout_1.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout_1.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        ToastUtils.showLongToast("position = "+position);
        mTabLayout_1.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}