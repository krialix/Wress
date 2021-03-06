package com.aurora.wress.intro;

import com.aurora.wress.R;
import com.aurora.wress.login.LoginActivity;
import com.aurora.wress.utils.PrefUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.relex.circleindicator.CircleIndicator;

/**
 * The type Intro activity.
 */
public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ViewPager viewPager = (ViewPager) findViewById(R.id.intro_viewPager);
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.intro_indicator);

        IntroPagerAdapter adapter = new IntroPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);

        final Button getStartedBtn = (Button) findViewById(R.id.intro_get_started_Button);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    getStartedBtn.setVisibility(View.VISIBLE);
                    getStartedBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            PrefUtil.markTosAccepted(getApplicationContext());
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Inner static class for Viewpager
     * It's a best practise to make inner classes static
     */
    private static class IntroPagerAdapter extends FragmentStatePagerAdapter {

        /**
         * Instantiates a new Intro pager adapter.
         *
         * @param fm the fm
         */
        public IntroPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IntroFragment.newInstance(R.string.slide_1, R.drawable.slide1);
                case 1:
                    return IntroFragment.newInstance(R.string.slide_2, R.drawable.slide2);
                case 2:
                    return IntroFragment.newInstance(R.string.slide_3, R.drawable.slide3);
                case 3:
                    return IntroFragment.newInstance(R.string.slide_4, R.drawable.slide4);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Returns total number of fragments
            return 4;
        }
    }
}
