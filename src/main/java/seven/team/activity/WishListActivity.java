package seven.team.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.adapter.GoodsAdapter;
import seven.team.util.BaseActivity;
import seven.team.entity.Goods;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import seven.team.util.UsualIntent;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView returnFormer;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        title.setText("浏览历史");
        returnFormer.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                UsualIntent.toAnotherPage("MainActivity");
                break;
        }
    }
}
