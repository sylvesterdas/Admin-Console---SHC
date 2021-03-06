package com.SacredHeartColaba.admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.SacredHeartColaba.admin.fragment.EventsFragment;
import com.SacredHeartColaba.admin.fragment.ModelFragment;
import com.SacredHeartColaba.admin.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
            The {@link android.support.v4.view.PagerAdapter} that will provide
            fragments for each of the sections. We use a
            {@link FragmentPagerAdapter} derivative, which will keep every
            loaded fragment in memory. If this becomes too memory intensive, it
            may be best to switch to a
            {@link android.support.v4.app.FragmentStatePagerAdapter}.
        */
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //  Set up the ViewPager with the sections adapter.
        /*
            The {@link ViewPager} that will host the section contents.
        */
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ModelFragment modelFragment = null;
            switch (position) {
                case 0:
                    modelFragment = new NewsFragment();
                    break;
                case 1:
                    modelFragment = new EventsFragment();
                    break;
                default:
            }
            return modelFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NEWS";
                case 1:
                    return "EVENTS";
            }
            return null;
        }
    }
}
