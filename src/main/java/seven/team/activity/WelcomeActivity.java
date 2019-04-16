package seven.team.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import org.litepal.LitePal;
import seven.team.entity.ReceiveInfo;
import seven.team.entity.User;
import seven.team.thread.*;
import seven.team.util.AppUsedTemp;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
        initData();
        LitePal.getDatabase();
    }
    private void initData(){
        User user = LitePal.findFirst(User.class);
        ReceiveInfo receiveInfo = new ReceiveInfo();
        receiveInfo.setName("小明");
        receiveInfo.setPhone("11000000000");
        receiveInfo.setAddress("湖北省武汉大学");
        AppUsedTemp.setReceiveInfo(receiveInfo);
        //new ManageGoodsTask().execute(4);
        new DefaultReceiveInfoTask().execute();
        new WishGoodsTask().execute();
        new HistoryBrowseTask().execute();
        new OrderListTask().execute();

        //new GoodsRemarksTask().execute(2,user);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    private void getHome(){
        UsualIntent.toAnotherPage("LoginActivity");
        finish();
    }
}
