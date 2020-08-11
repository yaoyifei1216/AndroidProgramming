package com.example.criminalintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.example.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mToFirstBtn,mToEndBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mViewPager = findViewById(R.id.activity_crime_view_pager);
        mToFirstBtn = findViewById(R.id.to_first);
        mToFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        mToEndBtn = findViewById(R.id.to_end);
        mToEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size()-1);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mToFirstBtn.setVisibility(View.VISIBLE);
                mToEndBtn.setVisibility(View.VISIBLE);
                if (position == 0) {
                    mToFirstBtn.setVisibility(View.INVISIBLE);
                }
                if (position == mCrimes.size()-1){
                    mToEndBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mCrimes = CrimeLab.get(this).getCrimes();
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        //getParentActivityIntent().putExtra(CrimeListFragment.SAVED_SUBTITLE_VISIBLE,getIntent().getSerializableExtra(EXTRA_SUB_VISIABLE));
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(uuid)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

/*    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        super.getSupportParentActivityIntent().putExtra("mSubtitleVisible",(boolean) getIntent().getSerializableExtra("mSubtitleVisible"));
        return super.getSupportParentActivityIntent();
    }*/
}
