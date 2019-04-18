package seven.team.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;
import com.samluys.filtertab.*;
import com.samluys.filtertab.listener.OnSelectResultListener;
import seven.team.activity.R;
import seven.team.activity.SearchRecruitActivity;
import seven.team.adapter.RecruitAdapter;
import seven.team.entity.FilterEntity;
import seven.team.entity.Recruitment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_2 extends Fragment implements View.OnClickListener, OnSelectResultListener {


    private List<Recruitment> recruitmentList;
    private Button addRecruiments;
    private Button searchRecruitments;

//    private ExpandableView mExpandableView;
//    private Map<String, ExpandleItemView> mExpandleItemViews;

//    private String[] compAddress = { "北京","上海","湖北省","河南省","湖南省","甘肃省","广东省","安徽省" };
//    private String[] expeRequirement = { "无经验要求", "1-2年", "2-3年", "3-4年", "4年以上", "5年以上", "10年以上", "15年以上" };
//    private String[] eduRequirement = { "高中", "本科", "大专" , "硕士" , "博士" , "无学历要求" };
//    private String[] howToSort = { "地点由近到远", "地点由远到近", "经验要求由高到低", "经验要求由低到高" , "学历要求由低到高","学历要求由高到低" };

    public Fragment_2() {
        // Required empty public constructor
        recruitmentList = new ArrayList<Recruitment>();
        initRecruitment();
    }


    //TODO
    //与后台连接获取数据
    private void initRecruitment(){
        for(int i = 0;i<10;i++){
            Recruitment recruitment = new Recruitment();
            recruitment.setJobName("JAVA软件工程师"+i);
            recruitment.setJobPay("1"+i+"K-"+"2"+i+"K");
            recruitment.setCompanyName("腾讯分公司"+i);
            recruitment.setExperienceRequirement(i+"-"+(i+1)+"年");
            recruitmentList.add(recruitment);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);
//        //商品种类
//        view.findViewById(R.id.search_recruitments).setOnClickListener(this);
//        mExpandableView = (ExpandableView) view.findViewById(R.id.expandview);
//        mExpandleItemViews = new LinkedHashMap<>();
//        //不要问我为什么这么用，因为我想用LinkedHashMap
//        mExpandleItemViews.put("地点", new ExpandleItemView("地点", this.getContext(), Arrays.asList(compAddress)));
//        mExpandleItemViews.put("经验", new ExpandleItemView("经验", this.getContext(), Arrays.asList(expeRequirement)));
//        mExpandleItemViews.put("学历", new ExpandleItemView("学历", this.getContext(), Arrays.asList(eduRequirement)));
//        mExpandleItemViews.put("排序", new ExpandleItemView("排序", this.getContext(), Arrays.asList(howToSort)));
//        mExpandableView.initViews(new ArrayList<>(mExpandleItemViews.values()));

        StatusBarHelper.translucent(getActivity());
        String jsonStr = getJson(getContext(),"demo_data.json");
        FilterEntity filterEntity = JsonToObject(jsonStr, FilterEntity.class);

        FilterTabView ftb_filter = view.findViewById(R.id.ftb_filter);
        ftb_filter.removeViews();
        FilterInfoBean bean1 = new FilterInfoBean("区域", FilterTabConfig.FILTER_TYPE_AREA, filterEntity.getArea());
        FilterInfoBean bean2 = new FilterInfoBean("总价", FilterTabConfig.FILTER_TYPE_PRICE, filterEntity.getPrice());
        FilterInfoBean bean3 = new FilterInfoBean("户型", FilterTabConfig.FILTER_TYPE_SINGLE_SELECT, filterEntity.getHouseType());
        FilterInfoBean bean4 = new FilterInfoBean("筛选", FilterTabConfig.FILTER_TYPE_MUL_SELECT, filterEntity.getMulSelect());

        ftb_filter.addFilterItem(bean1.getTabName(), bean1.getFilterData(), bean1.getPopupType(), 0);
        ftb_filter.addFilterItem(bean2.getTabName(), bean2.getFilterData(), bean2.getPopupType(), 1);
        ftb_filter.addFilterItem(bean3.getTabName(), bean3.getFilterData(), bean3.getPopupType(), 2);
        ftb_filter.addFilterItem(bean4.getTabName(), bean4.getFilterData(), bean4.getPopupType(), 3);

        ftb_filter.setOnSelectResultListener(this);


        addRecruiments = view.findViewById(R.id.add_recruiments);
        searchRecruitments = view.findViewById(R.id.search_recruitments);
        addRecruiments.setOnClickListener(this);
        searchRecruitments.setOnClickListener(this);

        //推荐商品
        RecyclerView rRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_recruit);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rRecyclerView.setLayoutManager(layoutManager);
        RecruitAdapter adapter = new RecruitAdapter(recruitmentList);
        rRecyclerView.setAdapter(adapter);
        return view;
    }

    /**
     * 获取assets下的json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为 对象
     *
     * @param json
     * @param type
     * @return
     */
    public static <T> T JsonToObject(String json, Class<T> type) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void onSelectResult(FilterResultBean resultBean) {

        String message="";
        if(resultBean.getPopupType() == 3) {
            List<FilterResultBean.MulTypeBean> list = resultBean.getSelectList();
            for (int i = 0; i < list.size(); i++) {
                FilterResultBean.MulTypeBean bean = list.get(i);
                if (i == (list.size() - 1)) {
                    message = message + bean.getItemName();
                } else {
                    message = message + bean.getItemName() + ",";
                }
            }
        } else {
            message =resultBean.getItemId()+":"+resultBean.getName();
        }

        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }



    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_recruitments: {
                Intent intent = new Intent(this.getContext(), SearchRecruitActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.add_recruiments:{
                break;
            }

            default:
                break;
        }
    }


}
