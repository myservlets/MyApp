package seven.team.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import seven.team.adapter.MyOrderPagerAdapter;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    public final static int WAIT_PAY_PAGE = 0;
    public final static int WAIT_SEND_PAGE = 1;
    public final static int WAIT_RECEIVE_PAGE = 2;
    public final static int WAIT_REMARK_PAGE = 3;
    public final static int WAIT_REFUND_PAGE = 4;

    private ImageView returnFormer;
    private RadioGroup headNavigation;
    private RadioButton waitPayPage;
    private RadioButton waitSendPage;
    private RadioButton waitReceivePage;
    private RadioButton waitRemarkPage;
    private RadioButton waitRefundPage;
    private ViewPager viewPager;

    private MyOrderPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        bindView();
        setFirstPage();
    }
    private void setFirstPage(){
        String page = getIntent().getStringExtra("show_page");
        viewPager.setCurrentItem(Integer.parseInt(page));
        switch (Integer.parseInt(page))
        {
            case 0:
                waitPayPage.setChecked(true);
                break;
            case 1:
                waitSendPage.setChecked(true);
                break;
            case 2:
                waitReceivePage.setChecked(true);
                break;
            case 3:
                waitRemarkPage.setChecked(true);
                break;
            case 4:
                waitRefundPage.setChecked(true);
                break;
        }
    }
    private void bindView(){
        returnFormer = findViewById(R.id.return_former);
        returnFormer.setOnClickListener(this);

        headNavigation=findViewById(R.id.head_navigation);
        waitPayPage = findViewById(R.id.wait_pay_page);
        waitSendPage = findViewById(R.id.wait_send_page);
        waitReceivePage = findViewById(R.id.wait_receive_page);
        waitRemarkPage = findViewById(R.id.wait_remark_page);
        waitRefundPage = findViewById(R.id.wait_refund_page);
        headNavigation.setOnCheckedChangeListener(this);

        adapter = new MyOrderPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.vpager_my_order);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId){
        switch (checkedId) {
            case R.id.wait_pay_page:
                viewPager.setCurrentItem(WAIT_PAY_PAGE);
                break;
            case R.id.wait_send_page:
                viewPager.setCurrentItem(WAIT_SEND_PAGE);
                break;
            case R.id.wait_receive_page:
                viewPager.setCurrentItem(WAIT_RECEIVE_PAGE);
                break;
            case R.id.wait_remark_page:
                viewPager.setCurrentItem(WAIT_REMARK_PAGE);
                break;
            case R.id.wait_refund_page:
                viewPager.setCurrentItem(WAIT_REFUND_PAGE);
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
            switch (viewPager.getCurrentItem()){
                case WAIT_PAY_PAGE:
                    waitPayPage.setChecked(true);
                    break;
                case WAIT_SEND_PAGE:
                    waitSendPage.setChecked(true);
                    break;
                case WAIT_RECEIVE_PAGE:
                    waitReceivePage.setChecked(true);
                    break;
                case WAIT_REMARK_PAGE:
                    waitRemarkPage.setChecked(true);
                    break;
                case WAIT_REFUND_PAGE:
                    waitRefundPage.setChecked(true);
                    break;
            }
        }
    }
}
