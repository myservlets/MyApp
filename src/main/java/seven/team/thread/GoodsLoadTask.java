package seven.team.thread;

import android.os.AsyncTask;
import android.os.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import seven.team.entity.Goods;
import seven.team.entity.User;
import seven.team.util.AppUsedLists;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoodsLoadTask extends AsyncTask<User,Integer,String> {

    @Override
    protected String doInBackground(User... users) {
        return null;
    }
    @Override
    protected void onPostExecute(String json) {
        ArrayList<Goods> list= new ArrayList<Goods>();
        list.add(new Goods());
        list.add(new Goods());
        AppUsedLists.setBusinessGoodsList(list);
    }
}
