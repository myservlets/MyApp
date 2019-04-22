package seven.team.fragment;

import seven.team.adapter.GoodsAdapter;
import seven.team.adapter.TypeAdapter;
import seven.team.thread.TypeGoodsListTask;
import seven.team.util.*;
import seven.team.entity.Goods;
import seven.team.activity.R;
import seven.team.activity.SearchGoodsActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_1 extends Fragment implements View.OnClickListener, OnBannerListener {

    public static MyProgressDialog myProgressDialog;
    private List<Goods>goodsList;
    private GoodsAdapter adapter;
    private List<String> typeList;
    private List<Integer> imageList;
    private List<Integer>images;
    private Banner banner;
    private RecyclerView proRecyclerView;
    public Fragment_1() {
        //goodsList = AppUsedLists.getBusinessGoodsList();
        goodsList = new ArrayList<>();
        typeList= new ArrayList<>();
        imageList= new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.hot1);
        images.add(R.drawable.hot2);
        images.add(R.drawable.hot3);
        initGoods();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,container,false);
        myProgressDialog = new MyProgressDialog(getContext());
        typeList = Arrays.asList(getResources().getStringArray(R.array.types));
        imageList.add(R.mipmap.book_type);
        imageList.add(R.mipmap.phone_type);
        imageList.add(R.mipmap.computer_type);
        imageList.add(R.mipmap.stationery_type);
        imageList.add(R.mipmap.cosmetics_type);
        imageList.add(R.mipmap.daily_use_type);
        imageList.add(R.mipmap.more_type);
        banner = view.findViewById(R.id.banner);
        banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(images);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(this);
        banner.start();
        //发布商品
        view.findViewById(R.id.search_goods).setOnClickListener(this);

        //快速回到顶部按钮
        FloatingActionButton fab= (FloatingActionButton)view.findViewById(R.id.fab);
        final NestedScrollView scroll_view = view.findViewById(R.id.scroll_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll_view.fling(0);
                scroll_view.smoothScrollTo(0, 0);
            }
        });
        //商品种类
        GridView gridView = view.findViewById(R.id.grid_view);//获取GridView组件
        TypeAdapter typeAdapter=new TypeAdapter(this.getContext(),typeList,imageList);
        gridView.setAdapter(typeAdapter);
        setGridViewHeight(gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==parent.getChildCount()-1){
                    UsualIntent.toAnotherPage(SearchGoodsActivity.class);
                }else {
                    TextView type=view.findViewById(R.id.type_name);
                    String key = type.getText().toString();
                    new TypeGoodsListTask().execute();
                    // TODO: 2019/4/5 0005 传送type到后台，接收该信息相关的商品列表
                }
            }
        });
        //推荐商品
        RecyclerView proRecyclerView = view.findViewById(R.id.recycle_goods_view);
        GridLayoutManager manager = new GridLayoutManager(this.getContext(),2);
        proRecyclerView.setLayoutManager(manager);
        adapter = new GoodsAdapter(goodsList);
        AppUsedAdapter.setGoodsAdapter(adapter);
        proRecyclerView.setAdapter(adapter);
        return view;
    }

    //TODO
    //与后台连接获取数据
    private void initGoods(){
        for(int i = 0;i<10;i++){
            Goods goods = new Goods();
            goods.setGoodsId(5);
            goods.setPrice(2.5);
            goods.setContent("软件工程二手");
            goods.setNickName("小明");
            goods.setDescribe("这是一本正版软件工程二手书籍");
            goods.setGoodsName("二手软件工程");
            goods.setQuantity(5);
            goods.setType("书籍");
            goodsList.add(goods);
        }
        //new GoodsLoadTask().execute();
    }

    public void setGridViewHeight(GridView gridview) {
        // 获取gridview的adapter
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int numColumns= 5; //5
        int totalHeight = 0;
        // 计算每一列的高度之和
        for (int i = 0; i < listAdapter.getCount(); i += numColumns) {
            // 获取gridview的每一个item
            View listItem = listAdapter.getView(i, null, gridview);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        // 获取gridview的布局参数
        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight;
        gridview.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_goods:{
                Intent intent = new Intent(this.getContext(), SearchGoodsActivity.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }

    // TODO: 2019/4/5 0005 轮转图片的点击事件
    @Override
    public void OnBannerClick(int position) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    // TODO: 2019/4/5 0005 获取相关类型的物品列表
    class typedGoodsTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
//            String key = strings[0];
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


}
