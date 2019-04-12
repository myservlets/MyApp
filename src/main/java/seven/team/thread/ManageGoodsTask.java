package seven.team.thread;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.handler.ServletsConn;
import seven.team.entity.Goods;
import seven.team.entity.User;
import seven.team.util.AppUsedLists;
import seven.team.util.MyApplication;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManageGoodsTask extends AsyncTask<Object,Integer,String> {
    private static final int PUNISH_GOODS = 0;
    private static final int SEARCH_USER_GOODS = 1;
    private static final int CHANGE_USER_GOODS = 2;
    private static final int DELETE_USER_GOODS = 3;
    private static final int SEARCH_ALL_GOODS = 4;
    @Override
    protected String doInBackground(Object... objects) {
        int sign = (Integer) objects[0];
        String json = null;
        Gson gson;
        Goods goods;
        User user;
        switch (sign){
            case PUNISH_GOODS:
                gson = new Gson();
                goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'Goods':"+gson.toJson(goods)+"}";
                break;
            case SEARCH_USER_GOODS:
                user = (User) objects[1];
                json = "{'sign':"+ sign +",'userId':"+user.getUserId()+"}";
                break;
            case CHANGE_USER_GOODS:
                gson = new Gson();
                goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'Goods':"+gson.toJson(goods)+"}";
                break;
            case DELETE_USER_GOODS:
                gson = new Gson();
                goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'goodsId':"+ goods.getGoodsId() +"}";
                break;
            case SEARCH_ALL_GOODS:
                json = "{'sign':"+ sign +"}";
                break;
        }
        return ServletsConn.connServlets("HandleGoodsInfo",json);
    }

    @Override
    protected void onPostExecute(String json) {
        int result;
        if(json == null) return;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        result = Integer.parseInt(jsonObject.get("status").toString());
        ArrayList<Goods> goodsArrayList;
        switch (result){
            case 0:
                System.out.println("发布成功");
                break;
            case 1:
                System.out.println("发布失败");
                break;
            case 2:
                System.out.println("查询成功");
                Type listType = new TypeToken<ArrayList<Goods>>() {}.getType();
                goodsArrayList = gson.fromJson(jsonObject.get("ArrayList<Goods>").toString(),listType);
                System.out.println(goodsArrayList);
                break;
            case 3:
                System.out.println("查询失败");
                break;
            case 4:
                System.out.println("修改成功");
                break;
            case 5:
                System.out.println("修改失败");
                break;
            case 6:
                System.out.println("删除成功");
                break;
            case 7:
                System.out.println("删除失败");
            case 8:
                System.out.println("所有查询成功");
                listType = new TypeToken<ArrayList<Goods>>() {}.getType();
                goodsArrayList = gson.fromJson(jsonObject.get("ArrayList<Goods>").toString(),listType);
                AppUsedLists.setBusinessGoodsList(goodsArrayList);
                break;
            case 9:
                System.out.println("所有查询失败");
                break;
            case 10:
                System.out.println("数据库异常");
                MyApplication.toastMsg("数据库异常");
                break;
        }
    }
}
