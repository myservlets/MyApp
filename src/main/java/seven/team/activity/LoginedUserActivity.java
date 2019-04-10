package seven.team.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import seven.team.adapter.LoginedUserAdapter;
import seven.team.util.BaseActivity;
import seven.team.entity.User;

public class LoginedUserActivity extends BaseActivity implements View.OnClickListener {

    private List<User>userList;
    private RecyclerView recyclerView;
    private RelativeLayout exit_user;
    private RelativeLayout adding_user;
    private ImageView back_set_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        initUserlist();
        bindData();
    }

    private void bindData(){
        exit_user = findViewById(R.id.exit_user);
        exit_user.setOnClickListener(this);
        adding_user = findViewById(R.id.user_adding);
        adding_user.setOnClickListener(this);
        back_set_1 = findViewById(R.id.back_set_page_1);
        back_set_1.setOnClickListener(this);
        recyclerView = findViewById(R.id.changeUser_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LoginedUserAdapter adapter = new LoginedUserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }
    private void initUserlist(){
        userList = new ArrayList<User>();
        for(int i = 0;i<2;i++){
            User user = new User();
            user.setUserId("775234463");
            user.setNickname("木水先生");
            userList.add(user);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.exit_user:
                finishBesidesLogin();
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.user_adding:
                break;
            case  R.id.back_set_page_1:
                finish();
                intent = new Intent(this, SystemSetActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void finishBesidesLogin(){

    }
}
