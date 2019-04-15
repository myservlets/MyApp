package seven.team.thread;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.handler.ServletsConn;
import seven.team.entity.Comment;
import seven.team.entity.CommentItem;
import seven.team.entity.Goods;
import seven.team.entity.User;
import seven.team.util.AppUsedLists;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoodsRemarksTask extends AsyncTask<Object,Integer,String> {
    @Override
    protected String doInBackground(Object... objects) {
        int sign = (Integer) objects[0];
        Gson gson = new Gson();
        String json = null;
        switch (sign){
            case 0://评论商品
                Comment comment = (Comment) objects[1];
                json = "{'sign':"+ sign +",'comment':"+gson.toJson(comment)+"}";
                json = ServletsConn.connServlets("comment",json);
                break;
            case 1://查询商品所有评论数据
                Goods goods = (Goods) objects[1];
                json = "{'sign':"+ sign +",'goodsId':"+goods.getGoodsId()+"}";
                json = ServletsConn.connServlets("comment",json);
                break;
            case 2://查询该用户所有评论
                User user = (User) objects[1];
                json = "{'sign':"+ sign +",'userId':"+user.getUserId()+"}";
                json = ServletsConn.connServlets("comment",json);
                break;
        }
        return json;
    }
    @Override
    protected void onPostExecute(String s) {
        if(s == null) return;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        int result = Integer.parseInt(jsonObject.get("status").toString());
        Type listType;
        switch (result){
            case 0:
                System.out.println("评论成功");//评论商品
                break;
            case 1:
                System.out.println("评论失败");
                break;
            case 2:
                listType = new TypeToken<ArrayList<CommentItem>>() {}.getType();
                ArrayList<CommentItem> commentItems = gson.fromJson(jsonObject.get("ArrayList<CommentItem>").toString(),listType);
                if (AppUsedLists.getGoodsRemarksList()==null){
                    AppUsedLists.setGoodsRemarksList(new ArrayList<CommentItem>());
                }
                AppUsedLists.setGoodsRemarksList(commentItems);
                break;
            case 3:
                //查询该用户发布过的评论
                listType = new TypeToken<ArrayList<Comment>>() {}.getType();
                ArrayList<Comment> comments = gson.fromJson(jsonObject.get("ArrayList<Comment>").toString(),listType);
                System.out.println(comments);
                break;
        }
    }
}
