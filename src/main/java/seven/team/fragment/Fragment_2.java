package seven.team.fragment;


import seven.team.adapter.RecruitAdapter;
import seven.team.entity.Recruitment;
import seven.team.activity.SearchRecruitActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import seven.team.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_2 extends Fragment implements View.OnClickListener {


    private List<Recruitment> recruitmentList;

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
            recruitment.setName("兼职名称"+i);
            recruitment.setDescribe("兼职"+i+"介绍");

            recruitmentList.add(recruitment);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);
        //商品种类
        view.findViewById(R.id.search_recruitments).setOnClickListener(this);



        //推荐商品
        RecyclerView rRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_recruit);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rRecyclerView.setLayoutManager(layoutManager);
        RecruitAdapter adapter = new RecruitAdapter(recruitmentList);
        rRecyclerView.setAdapter(adapter);
        return view;
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_recruitments:{
                Intent intent = new Intent(this.getContext(),SearchRecruitActivity.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }


}
