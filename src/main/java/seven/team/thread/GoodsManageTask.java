package seven.team.thread;

import android.os.AsyncTask;
import android.os.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.handler.ServletsConn;
import seven.team.adapter.GoodsAdapter;
import seven.team.entity.Goods;
import seven.team.entity.GoodsDetails;
import seven.team.entity.User;
import seven.team.util.AppUsedTemp;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoodsManageTask extends AsyncTask<Object,Integer,String> {
    @Override
    protected String doInBackground(Object...objects) {
        String json = null;
        Goods goods = null;
        User user = null;
        Gson gson = new Gson();
        int sign = (Integer) objects[0];
        switch (sign){
            case 0://发布商品
                goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'Goods':"+gson.toJson(goods)+"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
            case 1://查询商品
                user = (User) objects[1];
                json = "{'sign':"+ sign +",'userId':"+user.getUserId()+"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
            case 2://修改商品
                goods = (Goods)objects[1];
                json = "{'sign':"+ sign +",'Goods':"+gson.toJson(goods)+"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
            case 3://删除商品
                goods = (Goods)objects[1];
                json = "{'sign':"+ sign +",'goodsId':"+ goods.getGoodsId() +"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
            case 4://查询所有已发布的商品
                json = "{'sign':"+ sign +"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
            case 5://获取商品详情
                goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'goodsId':"+ goods.getGoodsId() +"}";
                json = ServletsConn.connServlets("HandleGoodsInfo",json);
                break;
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        ArrayList<Goods>goodsArrayList = null;
        Type listType = null;
        GoodsDetails goodsDetails = null;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        int result = Integer.parseInt(jsonObject.get("status").toString());
        switch (result){
            case -1:
                System.out.println("数据库异常");
                break;
            case 0:
                System.out.println("发布成功");
                break;
            case 1:
                System.out.println("发布失败");
                break;
            case 2:
                System.out.println("查询该用户发布商品成功");
                listType = new TypeToken<ArrayList<Goods>>() {}.getType();
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
                System.out.println("查询所有发布商品成功");
                listType = new TypeToken<ArrayList<Goods>>() {}.getType();
                goodsArrayList = gson.fromJson(jsonObject.get("ArrayList<Goods>").toString(),listType);
                System.out.println(goodsArrayList);
                break;
            case 9:
                System.out.println("查询失败");
                break;
            case 10:
                System.out.println("商品详情");
                goodsDetails = gson.fromJson(jsonObject.get("goodsDetails").toString(),GoodsDetails.class);
                AppUsedTemp.setGoodsDetails(goodsDetails);
                Message message = new Message();
                message.what = 0;
                GoodsAdapter.handler.sendMessage(message);
                System.out.println(goodsDetails);
                break;
        }
    }
}
