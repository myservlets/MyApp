package seven.team.util;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import seven.team.broadcast.ForceOffLineReceiver;
import seven.team.broadcast.NetWorkChangeReceiver;

public class BaseActivity extends AppCompatActivity {
    private ForceOffLineReceiver forceOffReceiver;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openForceOffBroad();;
        openInternetBroad();
    }

    private void openForceOffBroad(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BroadCast.FORCE_OFFLINE");
        forceOffReceiver = new ForceOffLineReceiver();
        registerReceiver(forceOffReceiver,intentFilter);
    }

    private void openInternetBroad(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(forceOffReceiver !=null){
            unregisterReceiver(forceOffReceiver);
            forceOffReceiver = null;
        }
        if(netWorkChangeReceiver!=null){
            unregisterReceiver(netWorkChangeReceiver);
            netWorkChangeReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
