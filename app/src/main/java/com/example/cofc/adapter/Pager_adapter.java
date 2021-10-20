package com.example.cofc.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class Pager_adapter extends FragmentPagerAdapter {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();

    public Pager_adapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void add_fragment(String title , Fragment fragment)
    {
        titles.add(title);
        fragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return titles.size();
    }
}
