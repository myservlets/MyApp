package seven.team.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import seven.team.entity.Goods;
import seven.team.entity.Order;
import seven.team.entity.ReceiveInfo;
import seven.team.util.AppUsedLists;
import seven.team.util.AppUsedTemp;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class PayActivity extends BaseActivity implements View.OnClickListener {
    public static final int CHOOSE_ADDRESS = 1;
    private ReceiveInfo receiveInfo;
    private Order order;

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
        otherAddress.setOnClickListener(this);


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
        receiveInfo = AppUsedTemp.getReceiveInfo();
        addressUserNmae.setText(receiveInfo.getName());
        addressUserPhone.setText(receiveInfo.getPhone());
        addressUser.setText(receiveInfo.getAddress());
        //addressIcon
        otherAddress.setImageDrawable(getDrawable(R.drawable.right_arraw_icon));

        order = (Order) getIntent().getSerializableExtra("order_data");
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
                if (AppUsedLists.getWaitPayList()==null){
                    AppUsedLists.setWaitPayList(new ArrayList<Order>());
                }
                AppUsedLists.getWaitPayList().add(order);
                UsualIntent.toAnotherPageWithData("MyOrderActivity","0");
                break;
            case R.id.commit_order:
                // TODO: 2019/4/11 0011 调用支付接口
                Toast.makeText(this,"购买成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.other_address:
                Intent intent = new Intent(this,ReceiveAddressActivity.class);
                startActivityForResult(intent,CHOOSE_ADDRESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_ADDRESS:
                if(resultCode==ReceiveAddressActivity.CHOOSED_A_ADDRESS){
                    ReceiveInfo receiveInfo = (ReceiveInfo) data.getSerializableExtra("address_data");
                    addressUser.setText(receiveInfo.getName());
                    addressUserPhone.setText(receiveInfo.getPhone());
                    addressUser.setText(receiveInfo.getAddress());
                    //addressIcon
                }
                break;
        }
    }
}
