package seven.team.activity;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.adapter.MyAdapter;
import seven.team.entity.GridItem;
import seven.team.util.BaseActivity;
import android.os.Bundle;
import seven.team.util.UsualIntent;

import java.util.ArrayList;

public class GoodsManageActivity extends BaseActivity implements View.OnClickListener {
    private GridView goodsGrid;
    private GridView taskGrid;
    private ImageView returnFormer;
    private TextView title;
    private BaseAdapter goodsAdapter = null;
    private BaseAdapter taskAdapter = null;
    private ArrayList<GridItem> goodsData = null;
    private ArrayList<GridItem> taskData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manage);
        bindData();
    }
    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        title.setText("商品管理");
        returnFormer.setOnClickListener(this);
        goodsGrid = findViewById(R.id.goods_manage_grid);
        taskGrid = findViewById(R.id.task_manage_grid);
        goodsData = new ArrayList<>();
        taskData = new ArrayList<>();
        GridItem item = new GridItem();
        item.setGridItemIcon(R.drawable.book);
        item.setGridItemName("上架商品");
        goodsData.add(item);
        GridItem item1 = new GridItem();
        item1.setGridItemIcon(R.drawable.book);
        item1.setGridItemName("订单管理");
        goodsData.add(item1);
        goodsData.add(item);
        goodsData.add(item);
        GridItem item2 = new GridItem();
        item2.setGridItemIcon(R.drawable.book);
        item2.setGridItemName("发布任务");
        taskData.add(item2);
        GridItem item3 = new GridItem();
        item3.setGridItemIcon(R.drawable.book);
        item3.setGridItemName("任务管理");
        taskData.add(item3);
        taskData.add(item2);
        taskData.add(item2);

        goodsAdapter = new MyAdapter<GridItem>(goodsData,R.layout.item_manage_grid) {

            @Override
            public void bindView(ViewHolder holder, GridItem obj) {
                holder.setImageResource(R.id.grid_item_icon, obj.getGridItemIcon());
                holder.setText(R.id.grid_item_name, obj.getGridItemName());
            }
        };
        goodsGrid.setAdapter(goodsAdapter);

        taskAdapter = new MyAdapter<GridItem>(taskData,R.layout.item_manage_grid) {
            @Override
            public void bindView(ViewHolder holder, GridItem obj) {
                holder.setImageResource(R.id.grid_item_icon,obj.getGridItemIcon());
                holder.setText(R.id.grid_item_name,obj.getGridItemName());
            }
        };
        taskGrid.setAdapter(taskAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                UsualIntent.toAnotherPage("MainActivity");
        }
    }
}
