package com.example.semiproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;// Tab 영역
    private ViewPager viewPager; // Tab별 표시할 영역

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Tab 생성
        tabLayout.addTab(tabLayout.newTab().setText("메모"));
        tabLayout.addTab(tabLayout.newTab().setText("회원확인"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabSize; // TAB 수

        public MyPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count; // TAB 수
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Fragment_1();
                case 1:
                    return new Fragment_2();
            }

            return null;
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }
}
