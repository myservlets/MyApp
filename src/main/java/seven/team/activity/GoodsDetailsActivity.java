package seven.team.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import de.hdodenhof.circleimageview.CircleImageView;
import seven.team.adapter.GoodsAdapter;
import seven.team.entity.*;
import seven.team.thread.GoodsRemarksTask;
import seven.team.util.BaseActivity;
import android.os.Bundle;
import android.view.View;
import seven.team.util.UsualIntent;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener {
    private List<Goods> recommentGoodsList;
    private GoodsAdapter adapter;
    private List<Integer>images;
    private Goods goods;
    private Comment comment;
    private User saler;

    private ImageView returnFormer;
    private TextView title;
    private Banner banner;
    private TextView price;
    private TextView content;
    private TextView goodsRemarks;
    private CircleImageView userIcon;
    private TextView userNickname;
    private TextView userRemark;
    private TextView salerName;
    private RecyclerView goodsRecommend;
    private TextView goodsDescription;
    private TextView toSalerPage;
    private TextView chatWithSaler;
    private TextView wishIt;
    private Button addShoppingCar;
    private Button payForIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        initImages();
        initGoods();
        initPage();
        bindData();

    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        title.setText("商品详情");
        banner = findViewById(R.id.image_banner);
        banner.isAutoPlay(false);
        banner.setViewPagerIsScroll(true);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(images);
        banner.start();
        price = findViewById(R.id.goods_price);
        price.setText(String.valueOf(goods.getPrice()));
        content = findViewById(R.id.goods_content);
        content.setText(goods.getContent());
        goodsRemarks = findViewById(R.id.all_remarks);
        userIcon = findViewById(R.id.user_icon);
        userNickname = findViewById(R.id.user_nickname);
        userRemark = findViewById(R.id.user_remark);
        userRemark.setText(comment.getContent());
        salerName = findViewById(R.id.saler);
        goodsDescription = findViewById(R.id.goods_description);
        goodsDescription.setText(goods.getDescribe());
        goodsRecommend = findViewById(R.id.goods_recommend_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        goodsRecommend.setLayoutManager(manager);
        adapter = new GoodsAdapter(recommentGoodsList);
        goodsRecommend.setAdapter(adapter);
        toSalerPage = findViewById(R.id.saler_page);
        chatWithSaler = findViewById(R.id.saler_chatter);
        wishIt = findViewById(R.id.wish);
        addShoppingCar = findViewById(R.id.add_shopping_car);
        payForIt = findViewById(R.id.pay_for_it);
        returnFormer.setOnClickListener(this);
        goodsRemarks.setOnClickListener(this);
        toSalerPage.setOnClickListener(this);
        chatWithSaler.setOnClickListener(this);
        wishIt.setOnClickListener(this);
        addShoppingCar.setOnClickListener(this);
        payForIt.setOnClickListener(this);
    }
    private void initGoods(){
        recommentGoodsList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            Goods goods = new Goods();
            recommentGoodsList.add(goods);
        }
    }

    private void initPage(){
        goods = (Goods)getIntent().getSerializableExtra("goods_data");
        comment = (Comment)getIntent().getSerializableExtra("comment_data");
    }
    private void initImages(){
        images = new ArrayList<>();
        images.add(R.drawable.book);
        images.add(R.drawable.book);
        images.add(R.drawable.book);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO: 2019/4/10 0010 获取该物品所有的评论
        //new GoodsRemarksTask().execute(1,goods);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.return_former:
                finish();
                UsualIntent.toAnotherPage("MainActivity");
                break;
            case R.id.all_remarks:
                UsualIntent.toAnotherPage("GoodsRemarkActivity");
                break;
            case R.id.saler_page:
                UsualIntent.toAnotherPage("SalerActivity");
                break;
            case R.id.saler_chatter:
                intent = new Intent(v.getContext(),ChatingActivity.class);
                saler = new User();
                saler.setNickname("saler");
                saler.setUserId("456");
                intent.putExtra("opposeUser",saler);
                v.getContext().startActivity(intent);
                break;
            case R.id.wish:
                if(WishListActivity.goodsList==null){
                    WishListActivity.goodsList = new ArrayList<>();
                }
                if (WishListActivity.goodsList.contains(goods)){
                    WishListActivity.goodsList.remove(goods);
                    Toast.makeText(this,"取消收藏夹成功",Toast.LENGTH_SHORT).show();
                }else {
                    WishListActivity.goodsList.add(goods);
                    Toast.makeText(this,"加入收藏夹成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_shopping_car:
                if(ShoppingCartActivity.goodsList==null){
                    ShoppingCartActivity.goodsList = new ArrayList<>();
                }
                if (ShoppingCartActivity.goodsList.contains(goods)){
                    ShoppingCartActivity.goodsList.remove(goods);
                    Toast.makeText(this,"取出购物车成功",Toast.LENGTH_SHORT).show();
                }else {
                    ShoppingCartActivity.goodsList.add(goods);
                    Toast.makeText(this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pay_for_it:
                finish();
                Order order = new Order();
                goods.setNickName("小明书店");
                goods.setContent("正版二手软件工程");
                goods.setPrice(20);
                order.setGoods(goods);
                order.setStatus(0);
                order.setCount(1);
                order.setCost(order.getCount()*goods.getPrice());
                intent = new Intent(this,PayActivity.class);
                intent.putExtra("order_data",order);
                startActivity(intent);
                break;
        }
    }
}
