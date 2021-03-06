package com.example.whasapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whasapp.R;
import com.example.whasapp.databinding.ActivityMainBinding;
import com.example.whasapp.menu.CallsFragment;
import com.example.whasapp.menu.ChatsFragment;
import com.example.whasapp.menu.StatusFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setUpWithViewPager(binding.viewPager);
        setSupportActionBar(binding.toolbar);
        binding.viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 changeFabIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
    }

    private void setUpWithViewPager(ViewPager viewPager) {
        MainActivity.SectionsPagerAdapter adapter = new SectionsPagerAdapter( getSupportFragmentManager() );
        adapter.addFragment( new ChatsFragment(), "Chats" );
        adapter.addFragment( new StatusFragment(), "Status" );
        adapter.addFragment( new CallsFragment(), "Calls" );
        viewPager.setAdapter( adapter );
    }

    //Add this code
    private static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get( position );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_search :  Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_more :  Toast.makeText(MainActivity.this, "Action more", Toast.LENGTH_LONG).show(); break;


        }
        return super.onOptionsItemSelected(item);
    }


    private void changeFabIcon(final int index){
        binding.fabAction.hide();
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                switch (index){
                    case 0:binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_chat)); break;
                    case 1:binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_photo_camera)); break;
                    case 2:binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_call)); break;
                }

                binding.fabAction.show();
            }
        },400);

    }
}