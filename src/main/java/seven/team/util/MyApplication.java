package seven.team.util;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import org.litepal.LitePal;

/**
 * Created by Administrator on 2019/3/24 0024.
 */
public class MyApplication extends Application {
    private static NotificationManager notificationManager;
    private static Context context;
    private static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){ return context; }

    public static NotificationManager getNotificationManager() { return notificationManager; }

        /*因为Toast是要运行在主线程的   所以需要到主线程哪里去显示toast*/
        public static void toastMsg(final String msg) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
