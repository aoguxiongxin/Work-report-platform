package atest.test.com.app.view.fragment.punchFragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

import atest.test.com.app.R;
import atest.test.com.app.utils.SettingUtils;
import atest.test.com.app.view.activity.ConsultActivity;


/**
 * 作者: 赵虔
 * 时间: 2017/10/21
 * 类作用:打卡页面
 */

public class PunchFragment extends Fragment {

    private View view;
    private TabLayout punchTablayout;
    private ViewPager viewPager;
    private String[] title = {"考勤打卡", "下级打卡", "我的打卡"};
    private ArrayList<Fragment> list;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.punch_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        punchTablayout.setupWithViewPager(viewPager);
        //滑动的时候把输入法关闭
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    SettingUtils.closeInputMethod(getActivity(), v);
                }
            });
        }
    }

    private void initView() {
        //获取头部的include
        View inclutView = view.findViewById(R.id.PunchTitleLayout);
        punchTablayout = (TabLayout) inclutView.findViewById(R.id.TitleTabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.punchViewPager);
        list = new ArrayList<>();
        list.add(new PunchingFragment());
        list.add(new PunchSubordinateFragment());
        list.add(new PunchMyFragment());
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //不让系统自动销毁item,方式滑动回来以后页面显示空白
//            super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
