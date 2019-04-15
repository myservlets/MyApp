package seven.team.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import seven.team.activity.PayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class ActivityCollector {
    private static List<BaseActivity>activities = new ArrayList<>();
    public static void addActivity(BaseActivity activity){
        activities.add(activity);
    }
    public static void removeActivity(BaseActivity activity){
        activities.remove(activity);
    }

    public static boolean isPayActivityAlive(){
        boolean isAlive = false;
        for (Activity activity:activities){
            if(activity instanceof PayActivity){
                isAlive = true;
                break;
            }
        }
        return isAlive;
    }

    public static void finishAll(){
        for(Activity activity : activities){
            activity.finish();
        }
        activities.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
