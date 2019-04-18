package seven.team.util;


import seven.team.activity.*;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Administrator on 2019/3/26 0026.
 */
public class UsualIntent {
    public static void toAnotherPage(Class<?>cla){

        Context context = MyApplication.getContext();
        Intent intent = new Intent(context,cla);
        context.startActivity(intent);
    }

    public static void toAnotherPageWithData(String activity,String data){
        Intent intent = null;
        Context context = MyApplication.getContext();
        switch (activity){
            case "MyOrderActivity":
                intent = new Intent(context, MyOrderActivity.class);
                intent.putExtra("show_page",data);
                break;
        }
        context.startActivity(intent);
    }
}
