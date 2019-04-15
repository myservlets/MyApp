package seven.team.thread;

import android.os.AsyncTask;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.handler.ServletsConn;
import seven.team.entity.LoginUser;
import seven.team.entity.User;
import seven.team.util.AppUsedLists;
import seven.team.util.MyApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetFriendList extends AsyncTask<Object,Integer,String> {
    private int sign = -1;
    @Override
    protected String doInBackground(Object... objects) {
        sign = (int)objects[0];
        User user = (User)objects[1];
        Gson gson = new Gson();
        String json = "{'sign':"+sign+",'User':"+gson.toJson(user)+"}";
        return ServletsConn.connServlets("friends",json);
    }
    @Override
    protected void onPostExecute(String json) {
        ArrayList<User> friendList = jsonToArrayList(json,User.class);
        switch (sign){
            case 0:
                AppUsedLists.setApplyfriendlist(friendList);
                break;
            case 1:
                AppUsedLists.setBeapplyfriendlist(friendList);
                break;
            case 2:
                friendList.add(0, LoginUser.getLoginUser());
                AppUsedLists.setMyfriendlist(friendList);
                break;

        }
        Toast.makeText(MyApplication.getContext(),json,Toast.LENGTH_LONG).show();
    }



    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<T>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

}
