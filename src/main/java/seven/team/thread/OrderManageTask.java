package seven.team.thread;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.handler.ServletsConn;
import seven.team.entity.Order;
import seven.team.entity.User;
import seven.team.util.AppUsedLists;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static seven.handler.ServletsConn.connServlets;

public class OrderManageTask extends AsyncTask<Object,Integer,String> {
    private int status;
    @Override
    protected String doInBackground(Object... objects) {
        User user = null;
        Order order = null;
        String json = null;
        int sign = (Integer) objects[0];
        Gson gson = new Gson();
        switch (sign){
            case 0://添加订单
                order = (Order) objects[1];
                json = "{'sign':"+ sign +",'Order':"+gson.toJson(order)+"}";
                json =  connServlets("Order",json);
                break;
            case 1://修改订单
                order = (Order) objects[1];
                json = "{'sign':"+ sign +",'Order':"+gson.toJson(order)+"}";
                json = ServletsConn.connServlets("Order",json);
                break;
            case 2://删除订单
                order = (Order) objects[1];
                json = "{'sign':"+ sign +",'Order':"+gson.toJson(order)+"}";
                json = ServletsConn.connServlets("Order",json);
                break;
            case 3://查询订单
                user = (User) objects[1];
                status = (Integer) objects[2];// 0/购物车 1/待付款 2/待发货 3/待收货 4/待评论 5/售后
                json = "{'sign':"+ sign +",'userId':"+user.getUserId()+",'status':"+status+"}";
                json = ServletsConn.connServlets("Order",json);
                break;
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        ArrayList<Order> orders;
        Type listType;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        int result = Integer.parseInt(jsonObject.get("status").toString());
        switch (result){
            case 0:
                System.out.println("添加成功");
                break;
            case 1:
                System.out.println("添加失败");
                break;
            case 2:
                System.out.println("修改成功");
                break;
            case 3:
                System.out.println("修改失败");
                break;
            case 4:
                System.out.println("删除成功");
                break;
            case 5:
                System.out.println("删除失败");
                break;
            case 6:
                System.out.println("查询成功");
                listType = new TypeToken<ArrayList<Order>>() {}.getType();
                orders = gson.fromJson(jsonObject.get("ArrayList<Order>").toString(),listType);
                switch (status){
                    case 0:
                        AppUsedLists.setShoppingCarOrderList(orders);
                        break;
                    case 1:
                        AppUsedLists.setWaitPayList(orders);
                        break;
                    case 2:
                        AppUsedLists.setWaitSendList(orders);
                        break;
                    case 3:
                        AppUsedLists.setWaitReceiveList(orders);
                        break;
                    case 4:
                        AppUsedLists.setWaitRemarkList(orders);
                        break;
                    case 5:
                        AppUsedLists.setWaitRefundList(orders);
                        break;
                }
                System.out.println(orders);
                break;
        }
    }
}
