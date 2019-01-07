package com.example.mhzhaog.myandroidsummary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titleList = null;
    private List<Fragment> fragmentList = null;

    public MainFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.titleList = new String[fragmentList.size()];
        this.fragmentList = fragmentList;
    }

    public MainFragmentPagerAdapter(FragmentManager fragmentManager, String[] titleList, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    public int getCount() {

        return this.fragmentList.size();

    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position < this.fragmentList.size()) {
            fragment = (Fragment)this.fragmentList.get(position);
        } else {
            fragment = (Fragment)this.fragmentList.get(0);
        }

        return fragment;
    }

    public CharSequence getPageTitle(int position) {
        return this.titleList[position % this.titleList.length];
    }
}
