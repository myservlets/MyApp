package seven.team.activity;

import seven.team.adapter.ShoppingCarAdapter;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;
import seven.team.entity.Goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener {
    private List<Goods> goods;
    private ImageView returnFormer;
    private RecyclerView recyclerView;
    private ShoppingCarAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initGoods();
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        returnFormer.setOnClickListener(this);
        recyclerView = findViewById(R.id.shopping_car_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShoppingCarAdapter(goods);
        recyclerView.setAdapter(adapter);
    }
    private void initGoods(){
        goods = new ArrayList<>();
        for(int i = 0;i<2;i++){
            Goods goods = new Goods();
            this.goods.add(goods);
        }
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
