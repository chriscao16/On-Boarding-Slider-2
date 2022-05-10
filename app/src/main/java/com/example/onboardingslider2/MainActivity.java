package com.example.onboardingslider2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.onboardingslider2.fragments.BaseFragment;
import com.example.onboardingslider2.fragments.ColumnLayoutFragment;
import com.example.onboardingslider2.fragments.MainLayoutFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity of the application. It provides viewPager to move between fragments.
 */
public class MainActivity extends BaseActivity {

    private List<BaseFragment> fragments = new ArrayList<>();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
                getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(screenSlidePagerAdapter);

        fragments.add(ColumnLayoutFragment
                .newInstance(R.drawable.pizza, getString(R.string.task_intro),
                        getString(R.string.footnote_sample), getString(R.string.timestamp_sample)));
        fragments.add(MainLayoutFragment
                .newInstance(getString(R.string.dough_instructions), getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));
        fragments.add(MainLayoutFragment
                .newInstance(getString(R.string.tomato_instructions), getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));
        fragments.add(MainLayoutFragment
                .newInstance(getString(R.string.combine_instructions), getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));
        fragments.add(MainLayoutFragment
                .newInstance(getString(R.string.bake_instructions), getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));

        screenSlidePagerAdapter.notifyDataSetChanged();

        final TabLayout tabLayout = findViewById(R.id.page_indicator);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public boolean onGesture(GlassGestureDetector.Gesture gesture) {
        switch (gesture) {
            case TAP:
                fragments.get(viewPager.getCurrentItem()).onSingleTapUp();
                return true;
            default:
                return super.onGesture(gesture);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
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
