package seven.team.activity;

import seven.team.adapter.RecruitAdapter;
import seven.team.util.ActivityCollector;
import seven.team.util.BaseActivity;
import seven.team.entity.Recruitment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchRecruitActivity extends BaseActivity implements View.OnClickListener {
    private List<Recruitment> oRecruit;//临时----------
    private List<Recruitment> relativeRecruit;//搜索结果
    private SearchView searchView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        relativeRecruit=new ArrayList<>();
        oRecruit=new ArrayList<>();//临时----------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recruit);
        searchView =  findViewById(R.id.search_recruit);
        recyclerView =  findViewById(R.id.search_recruit_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //临时数据
        for(int i=0;i<10;i++){
            Recruitment r=new Recruitment();
            r.setName(i+"");
            oRecruit.add(r);
        }
        //------------

        recyclerView.setAdapter(new RecruitAdapter(relativeRecruit));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                relativeRecruit = new ArrayList<>(match(oRecruit, query));
                recyclerView.setAdapter(new RecruitAdapter(relativeRecruit));
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    relativeRecruit = match(oRecruit, newText);
                    return true;
                }

                return false;
            }
        });

    }

    //todo
    //匹配方法，需要从select数据库获取数据
    //private List<Recruitment> match(String str){
    private List<Recruitment> match(List<Recruitment> oList,String str){
        List<Recruitment> result=new ArrayList<>();
        for(int i=0;i<oList.size();i++){
            if(str.equals(oList.get(i).getName()))result.add(oList.get(i));
        }
        return result;
    }

    private void bindData(){

      /*  user.setName("???");
        user.setUserId("??????");
        relativeFriends.add(user);
        searchView = findViewById(R.id.search_friends);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.search_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Seven.com.adapter = new RelativeUserAdapter(relativeFriends);
        recyclerView.setAdapter(Seven.com.adapter);*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;


        }
    }
}
