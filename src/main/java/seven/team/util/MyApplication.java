package seven.team.util;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by Administrator on 2019/3/24 0024.
 */
public class MyApplication extends Application {
    private static NotificationManager notificationManager;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){ return context; }

    public static NotificationManager getNotificationManager() { return notificationManager; }


}
