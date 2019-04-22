package seven.team.activity;

import seven.handler.ServletsConn;
import seven.team.adapter.RelativeUserAdapter;
import seven.team.util.BaseActivity;
import seven.team.entity.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchFriendsActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private List<User> relativeFriends;
    private String keyOfFriends;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RelativeUserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);
        bindData();
    }

    private void bindData(){
        relativeFriends = new ArrayList<>();
        searchView = findViewById(R.id.search_friends);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.search_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RelativeUserAdapter(relativeFriends);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.equals("")){
            relativeFriends.clear();
            adapter.notifyDataSetChanged();
            return false;
        }
        keyOfFriends = newText;
        //// TODO: 2019/3/22 0022  发送后台关键字
        new SearchRelativeFriends().execute(keyOfFriends);
        return true;
    }

    class SearchRelativeFriends extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            String keyword = params[0];
            int sign = 0;
            String json="{'sign':"+sign+",'keyword':"+keyword+"}";
            json = ServletsConn.connServlets("Search",json);
            return json;
        }
        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User>users = gson.fromJson(s,listType);
            relativeFriends.clear();
            for (User user:users){
                relativeFriends.add(user);
            }
            adapter.notifyDataSetChanged();
        }
    }


}
