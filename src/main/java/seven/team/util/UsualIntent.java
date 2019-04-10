package seven.team.util;


import seven.team.activity.*;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Administrator on 2019/3/26 0026.
 */
public class UsualIntent {
    public static void toAnotherPage(String activity){
        Intent intent = null;
        Context context = MyApplication.getContext();
        switch (activity){
            case "LoginActivity":
                intent = new Intent(context,LoginActivity.class);
                break;
            case "LoginedUserActivity":
                intent = new Intent(context, LoginedUserActivity.class);
                break;
            case "ReceiveAddressActivity":
                intent = new Intent(context,ReceiveAddressActivity.class);
                break;
            case "SystemSafetyActivity":
                intent = new Intent(context, SystemSafetyActivity.class);
                break;
            case "SystemUsualUseActivity":
                intent = new Intent(context, SystemUsualUseActivity.class);
                break;
            case "SystemInformActivity":
                intent = new Intent(context, SystemInformActivity.class);
                break;
            case "AboutSysActivity":
                intent = new Intent(context, SystemAboutActivity.class);
                break;
            case "MainActivity":
                intent = new Intent(context,MainActivity.class);
                break;
            case "SystemSetActivity":
                intent = new Intent(context, SystemSetActivity.class);
                break;
            case "ShoppingCartActivity":
                intent = new Intent(context, ShoppingCartActivity.class);
                break;
            case "OrderRemarkActivity":
                intent = new Intent(context, OrderRemarkActivity.class);
                break;
            case "LogisticsActivity":
                intent = new Intent(context, LogisticsActivity.class);
                break;
            case "PayActivity":
                intent = new Intent(context, PayActivity.class);
                break;
            case "RefundDetailsActivity":
                intent = new Intent(context, RefundDetailsActivity.class);
                break;
            case "BrowseHistoryActivity":
                intent = new Intent(context, BrowseHistoryActivity.class);
                break;
            case "WishListActivity":
                intent = new Intent(context, WishListActivity.class);
                break;
            case "MyOrderActivity":
                intent = new Intent(context,MyOrderActivity.class);
                break;
            case "GoodsManageActivity":
                intent = new Intent(context, GoodsManageActivity.class);
                break;
            case "MerchantGoodsListActivity":
                intent = new Intent(context, MerchantGoodsListActivity.class);
                break;
            case "GoodsDetailsActivity":
                intent = new Intent(context, GoodsDetailsActivity.class);
                break;
            case "SearchFriendsActivity":
                intent = new Intent(context,SearchFriendsActivity.class);
                break;
            case "SearchGoodsActivity":
                intent = new Intent(context,SearchGoodsActivity.class);
                break;
            case "FriendRequestActivity":
                intent = new Intent(context, FriendRequestActivity.class);
                break;
            case "SalerActivity":
                intent = new Intent(context,SalerActivity.class);
                break;
            case "GoodsRemarkActivity":
                intent = new Intent(context,GoodsRemarkActivity.class);
        }
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
