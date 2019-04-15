package seven.team.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import seven.team.thread.GoodsLoadTask;
import seven.team.thread.ManageGoodsTask;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
        new ManageGoodsTask().execute(4);
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
