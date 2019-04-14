package seven.team.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class ActivityCollector {
    public static List<Activity>activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static boolean isActivityAlive(Activity activity){
        if (activities.contains(activity)){
            return false;
        }else return false;
    }

    public static void finishAll(){
        for(Activity activity : activities){
            activity.finish();
        }
        activities.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
