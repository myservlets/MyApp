package seven.team.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import seven.team.fragment.Fragment_order_pay;
import seven.team.fragment.Fragment_order_receive;
import seven.team.fragment.Fragment_order_refund;
import seven.team.fragment.Fragment_order_remark;
import seven.team.fragment.Fragment_order_send;
import seven.team.activity.MyOrderActivity;

/**
 * Created by Administrator on 2019/3/14 0014.
 */
public class MyOrderPagerAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 5;
    private Fragment_order_pay wait_pay_page= null;
    private Fragment_order_send wait_send_page = null;
    private Fragment_order_receive wait_receive_page = null;
    private Fragment_order_remark wait_ramark_page = null;
    private Fragment_order_refund wait_refund_page = null;

    public MyOrderPagerAdapter(FragmentManager fm){
        super(fm);
        wait_pay_page = new Fragment_order_pay();
        wait_send_page = new Fragment_order_send();
        wait_receive_page = new Fragment_order_receive();
        wait_ramark_page = new Fragment_order_remark();
        wait_refund_page = new Fragment_order_refund();
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case MyOrderActivity.WAIT_PAY_PAGE:
                fragment = wait_pay_page;
                break;
            case MyOrderActivity.WAIT_SEND_PAGE:
                fragment = wait_send_page;
                break;
            case MyOrderActivity.WAIT_RECEIVE_PAGE:
                fragment = wait_receive_page;
                break;
            case MyOrderActivity.WAIT_REMARK_PAGE:
                fragment = wait_ramark_page;
                break;
            case MyOrderActivity.WAIT_REFUND_PAGE:
                fragment = wait_refund_page;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
