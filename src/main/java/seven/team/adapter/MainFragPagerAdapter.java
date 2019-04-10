package seven.team.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import seven.team.fragment.Fragment_1;
import seven.team.fragment.Fragment_2;
import seven.team.fragment.Fragment_3;
import seven.team.fragment.Fragment_4;
import seven.team.activity.MainActivity;

/**
 * Created by Administrator on 2019/3/12 0012.
 */
public class MainFragPagerAdapter extends FragmentPagerAdapter {
    public final int PAGE_COUNT = 4;
    private Fragment_1 myFragment1 = null;
    private Fragment_2 myFragment2 = null;
    private Fragment_3 myFragment3 = null;
    private Fragment_4 myFragment4 = null;

    public MainFragPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Fragment_1();
        myFragment2 = new Fragment_2();
        myFragment3 = new Fragment_3();
        myFragment4 = new Fragment_4();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
