package seven.team.activity;

import seven.team.adapter.AddRequestAdapter;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;
import seven.team.entity.LoginUser;
import seven.team.entity.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import seven.team.thread.GetFriendList;

import java.util.List;

public class FriendRequestActivity extends BaseActivity implements View.OnClickListener {
    public List<User>userList;
    private ImageView returnFormer;
    private RecyclerView recyclerView;
    private AddRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        new GetFriendList().execute(0, LoginUser.getLoginUser(),this);
//        initList();
        bindData();
    }
//    private void initList(){
//        userList = new ArrayList<>();
//        User user = new User();
//        userList.add(user);
//    }
    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        recyclerView = findViewById(R.id.add_request_recycler_view);
        returnFormer.setOnClickListener(this);
        adapter = new AddRequestAdapter(userList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                //UsualIntent.toAnotherPage("MainActivity");
                UsualIntent.toAnotherPage(MainActivity.class);
                break;
        }
    }

    // TODO: 2019/4/5 0005 获取到所有好友请求
    class AddRequestTask extends AsyncTask<Void,Integer,String>{

        @Override
        protected String doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
