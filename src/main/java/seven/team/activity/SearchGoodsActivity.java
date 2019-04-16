package seven.team.activity;

import seven.team.adapter.RelativeGoodsAdapter;
import seven.team.util.ActivityCollector;
import seven.team.util.BaseActivity;
import seven.team.entity.Goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchGoodsActivity extends BaseActivity implements View.OnClickListener {
    private List<Goods> oPro;//临时----------
    private List<Goods> relativePro;//搜索结果
    private SearchView searchView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        relativePro=new ArrayList<>();
        oPro=new ArrayList<>();//临时----------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        searchView =  findViewById(R.id.search_goods);
        recyclerView =  findViewById(R.id.search_goods_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //临时数据
        for(int i=0;i<10;i++){
            Goods r=new Goods();
            oPro.add(r);
        }
        //------------

        recyclerView.setAdapter(new RelativeGoodsAdapter(relativePro));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                relativePro = new ArrayList<>(match(oPro, query));
                recyclerView.setAdapter(new RelativeGoodsAdapter(relativePro));
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    relativePro = match(oPro, newText);
                    return true;
                }

                return false;
            }
        });

    }

    //todo
    //匹配方法，需要从select数据库获取数据
    //private List<Recruitment> match(String str){
    private List<Goods> match(List<Goods> oList, String str){
        List<Goods> result=new ArrayList<>();
        for(int i=0;i<oList.size();i++){
            if(str.equals(oList.get(i).getGoodsName()))result.add(oList.get(i));
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
