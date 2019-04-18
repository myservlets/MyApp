package seven.team.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import org.litepal.LitePal;
import seven.team.entity.LoginUser;
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
        new DefaultReceiveInfoTask().execute();
        new WishGoodsTask().execute();
        new HistoryBrowseTask().execute();
        User user1 = new User();
        user1.setUserId("1");
        new OrderManageTask().execute(3,user1,0);
        new OrderManageTask().execute(3,user1,1);
        new OrderManageTask().execute(3,user1,2);
        new OrderManageTask().execute(3,user1,3);
        new OrderManageTask().execute(3,user1,4);
        new OrderManageTask().execute(3,user1,5);

        new GoodsManageTask().execute(4);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    private void getHome(){
        UsualIntent.toAnotherPage(LoginActivity.class);
        finish();
    }
}
