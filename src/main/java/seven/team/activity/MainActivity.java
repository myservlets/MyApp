package seven.team.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import seven.team.adapter.MainFragPagerAdapter;
import seven.team.util.AppUsedAdapter;
import seven.team.util.BaseActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_deal;
    private RadioButton rb_job;
    private RadioButton rb_chater;
    private RadioButton rb_mine;
    private ViewPager vPager;

    private MainFragPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MainFragPagerAdapter(getSupportFragmentManager());
        bindViews();
        setRadioBtnSize();
        rb_deal.setChecked(true);
    }

    private void bindViews(){
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_deal = (RadioButton)findViewById(R.id.rb_deal);
        rb_job = (RadioButton)findViewById(R.id.rb_job);
        rb_chater = (RadioButton)findViewById(R.id.rb_chater);
        rb_mine = (RadioButton)findViewById(R.id.rb_mine);
        rg_tab_bar.setOnCheckedChangeListener(this);
        vPager = (ViewPager)findViewById(R.id.vpager);
        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(0);
        vPager.addOnPageChangeListener(this);
    }

    private void setRadioBtnSize(){
        RadioButton[] rbs = new RadioButton[4];
        rbs[0] =rb_deal;
        rbs[1] = rb_job;
        rbs[2] = rb_chater;
        rbs[3] = rb_mine;

        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            float scale=this.getApplicationContext().getResources().getDisplayMetrics().density;//get屏幕密度
            Rect r = new Rect(0, 0, (int) (32 * scale + 0.5f), (int) (32 * scale + 0.5f));
            //定义一个Rect边界
            drawables[1].setBounds(r);
            rb.setCompoundDrawables(null,drawables[1],null,null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_deal:
                vPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_job:
                vPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_chater:
                vPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_mine:
                vPager.setCurrentItem(PAGE_FOUR);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == 2){
            switch (vPager.getCurrentItem()){
                case PAGE_ONE:
                    rb_deal.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_job.setChecked(true);
                    break;
                case  PAGE_THREE:
                    rb_chater.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_mine.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
