package seven.team.activity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import seven.team.adapter.MyAdapter;
import seven.team.entity.GridItem;
import seven.team.util.BaseActivity;
import android.os.Bundle;
import seven.team.util.UsualIntent;

import java.util.ArrayList;

public class ManageActivity extends BaseActivity {
    private ImageView returnFormer;
    private TextView title;
    private GridLayout goodsManageLayout;
    private GridLayout taskManageLayout;
    private TextView publishGoods;
    private TextView myOrder;
    private TextView myGoods;
    private TextView myBargain;
    private TextView publishTask;
    private TextView mySendTask;
    private TextView myReceiveTask;
    private TextView myPreEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manage);
        bindData();
        initClickListener();
    }
    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        title.setText("管理");
        goodsManageLayout = findViewById(R.id.layout_goods_manage);
        publishGoods = goodsManageLayout.findViewById(R.id.item1);
        publishGoods.setText("上架商品");
        myOrder = goodsManageLayout.findViewById(R.id.item2);
        myOrder.setText("我的订单");
        myGoods = goodsManageLayout.findViewById(R.id.item3);
        myGoods.setText("我的商品");
        myBargain = goodsManageLayout.findViewById(R.id.item4);
        myBargain.setText("我的交易");
        taskManageLayout = findViewById(R.id.layout_task_manage);
        publishTask = taskManageLayout.findViewById(R.id.item1);
        publishTask.setText("发布其他");
        mySendTask = taskManageLayout.findViewById(R.id.item2);
        mySendTask.setText("发布的");
        myReceiveTask = taskManageLayout.findViewById(R.id.item3);
        myReceiveTask.setText("接受的");
        myPreEdit = taskManageLayout.findViewById(R.id.item4);
        myPreEdit.setText("编辑");
    }

    private void Inten(Class<?>cla){
        Intent intent = new Intent(this,cla);
        startActivity(intent);
    }

    private void initClickListener(){
        returnFormer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        publishGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UsualIntent.toAnotherPage("MerchantGoodsActivity");
                UsualIntent.toAnotherPage(MerchantGoodsActivity.class);
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPageWithData("MyOrderActivity","1");
            }
        });;
        myGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPage(MyGoodsActivity.class);
            }
        });;
        myBargain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPage(MyBarginActivity.class);
            }
        });;
        publishTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UsualIntent.toAnotherPage("MerchantTaskActivity");
                Inten(MerchantTaskActivity.class);
            }
        });;
        mySendTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPage(MySendTaskActivity.class);
            }
        });;
        myReceiveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPage(MyReceiveTaskActivity.class);
            }
        });;
        myPreEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsualIntent.toAnotherPage(EditTaskActivity.class);
            }
        });;

    }
}
