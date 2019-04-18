package seven.team.activity;

import android.view.View;
import android.widget.*;
import seven.team.entity.Goods;
import seven.team.entity.LoginUser;
import seven.team.util.BaseActivity;
import android.os.Bundle;
import seven.team.util.UsualIntent;

public class MerchantGoodsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private ImageView goodsIcon;
    private TextView title;
    private Button submitGoods;
    private TextView goodsNameTitle;
    private TextView goodsProducerTitle;
    private TextView goodsPriceTitle;
    private TextView goodsTypeTitle;
    private TextView goodsContentTitle;
    private TextView goodsDescripeTitle;
    private TextView goodsQuantityTitle;
    private EditText goodsName;
    private EditText goodsProducer;
    private EditText goodsPrice;
    private EditText goodsType;
    private EditText goodsContent;
    private EditText goodsDescripe;
    private EditText goodsQuantity;
    private LinearLayout goodsNameLayout;
    private LinearLayout goodsProducerLayout;
    private LinearLayout goodsPriceLayout;
    private LinearLayout goodsTypeLayout;
    private LinearLayout goodsContentLayout;
    private LinearLayout goodsDescripeLayout;
    private LinearLayout goodsQuantityLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_goods_list);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        goodsIcon = findViewById(R.id.image_goods_icon);
        goodsIcon.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText("上架商品");
        returnFormer.setOnClickListener(this);
        submitGoods = findViewById(R.id.submit_goods);
        submitGoods.setOnClickListener(this);
        goodsNameLayout = findViewById(R.id.layout_goods_name);
        goodsProducerLayout = findViewById(R.id.layout_goods_producer);
        goodsPriceLayout = findViewById(R.id.layout_goods_price);
        goodsTypeLayout = findViewById(R.id.layout_goods_type);
        goodsContentLayout = findViewById(R.id.layout_goods_content);
        goodsDescripeLayout = findViewById(R.id.layout_goods_descripe);
        goodsQuantityLayout = findViewById(R.id.layout_goods_quantity);
        goodsNameTitle = goodsNameLayout.findViewById(R.id.text);
        goodsProducerTitle = goodsProducerLayout.findViewById(R.id.text);
        goodsPriceTitle = goodsPriceLayout.findViewById(R.id.text);
        goodsTypeTitle = goodsTypeLayout.findViewById(R.id.text);
        goodsContentTitle = goodsContentLayout.findViewById(R.id.text);
        goodsDescripeTitle = goodsDescripeLayout.findViewById(R.id.text);
        goodsQuantityTitle = goodsQuantityLayout.findViewById(R.id.text);
        goodsName = goodsNameLayout.findViewById(R.id.edit_text);
        goodsProducer = goodsProducerLayout.findViewById(R.id.edit_text);
        goodsPrice = goodsPriceLayout.findViewById(R.id.edit_text);
        goodsType = goodsTypeLayout.findViewById(R.id.edit_text);
        goodsContent = goodsContentLayout.findViewById(R.id.edit_text);
        goodsDescripe = goodsDescripeLayout.findViewById(R.id.edit_text);
        goodsQuantity = goodsQuantityLayout.findViewById(R.id.edit_text);
        goodsNameTitle.setText("商品名字");
        goodsProducerTitle.setText("生产厂家");
        goodsPriceTitle.setText("商品价格");
        goodsTypeTitle.setText("商品类型");
        goodsContentTitle.setText("商品简介");
        goodsDescripeTitle.setText("商品描述");
        goodsQuantityTitle.setText("商品数量");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                //UsualIntent.toAnotherPage("MainActivity");
                finish();
                break;
            case R.id.goods_icon:

                // TODO: 2019/4/16 0016 添加商品图片
                break;
            case R.id.submit_goods:
                Goods goods = new Goods();
                goods.setGoodsName(goodsName.getText().toString());
                goods.setNickName(LoginUser.getLoginUser().getNickname());
                goods.setType(goodsType.getText().toString());
                goods.setContent(goodsContent.getText().toString());
                goods.setPrice(Double.parseDouble(goodsPrice.getText().toString()));
                goods.setDescribe(goodsDescripe.getText().toString());
                goods.setQuantity(Integer.parseInt(goodsQuantity.getText().toString()));
                goods.setUserId(LoginUser.getLoginUser().getUserId());
                break;
        }
    }
}
