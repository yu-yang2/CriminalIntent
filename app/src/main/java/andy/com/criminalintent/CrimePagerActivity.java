package andy.com.criminalintent;

//import android.app.FragmentManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by andy on 2016/3/27.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getmId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
