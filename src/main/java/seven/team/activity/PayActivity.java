package seven.team.activity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import seven.team.entity.Goods;
import seven.team.entity.Order;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private ImageView addressIcon;
    private TextView addressUserNmae;
    private TextView addressUserPhone;
    private TextView addressUser;
    private ImageView otherAddress;

    private TextView saler;
    private ImageView goodsIcon;
    private TextView goodsContent;
    private TextView goodsPrice;
    private TextView goodsQuantity;
    private TextView orderPrice;

    private TextView totalPrice;
    private Button commit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        bindData();
        initPage();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        addressIcon = findViewById(R.id.address_icon);
        addressUserNmae = findViewById(R.id.user_address_name);
        addressUserPhone = findViewById(R.id.user_address_phone);
        addressUser = findViewById(R.id.user_address);
        otherAddress = findViewById(R.id.other_address);


        saler = findViewById(R.id.saler);
        goodsIcon = findViewById(R.id.goods_icon);
        goodsContent = findViewById(R.id.goods_content);
        goodsPrice = findViewById(R.id.price);
        goodsQuantity = findViewById(R.id.quantity);
        orderPrice = findViewById(R.id.order_price);
        totalPrice = findViewById(R.id.summation);
        commit = findViewById(R.id.commit_order);

        returnFormer.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    private void initPage(){
        Order order = (Order) getIntent().getSerializableExtra("order");
        Goods goods = order.getGoods();
        saler.setText(goods.getNickName());
        goodsContent.setText(goods.getContent());
        String price = "￥" + String.valueOf(goods.getPrice());
        goodsPrice.setText(price);
        String quantity = "*" + String.valueOf(order.getCount());
        goodsQuantity.setText(quantity);
        String cost = "￥" + String.valueOf(order.getCount()*goods.getPrice());
        orderPrice.setText(cost);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                UsualIntent.toAnotherPageWithData("MyOrderActivity","0");
                break;
            case R.id.commit_order:
                Toast.makeText(this,"购买成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
