package seven.team.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * Created by Administrator on 2019/3/31 0031.
 */
public class NetWorkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info==null||!info.isAvailable()){
            Toast.makeText(context,"请检查您的网络！",Toast.LENGTH_SHORT).show();
        }
    }
}
