package seven.team.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import seven.team.adapter.GoodsRemarkAdapter;
import seven.team.entity.Comment;
import seven.team.entity.CommentItem;
import seven.team.util.AppUsedLists;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

import java.util.ArrayList;
import java.util.List;

public class GoodsRemarkActivity extends BaseActivity implements View.OnClickListener {

    private List<CommentItem>commentList;
    private ImageView returnFormer;
    private TextView tittle;
    private RecyclerView recyclerView;
    private GoodsRemarkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_remark);
        initData();
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        tittle = findViewById(R.id.title);
        tittle.setText("商品评论");
        recyclerView = findViewById(R.id.remark_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new GoodsRemarkAdapter(commentList);
        recyclerView.setAdapter(adapter);
        returnFormer.setOnClickListener(this);
    }

    private void initData(){
        commentList = AppUsedLists.getGoodsRemarksList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                UsualIntent.toAnotherPage("GoodsDetailsActivity");
                break;
        }
    }
}
