package seven.team.fragment;


import seven.team.entity.Order;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import seven.team.adapter.WaitRemarkGoodsAdapter;
import seven.team.util.AppUsedLists;
import seven.team.util.MyApplication;
import seven.team.activity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_order_remark extends Fragment {
    private RecyclerView recyclerView;
    private List<Order> orderList;

    public Fragment_order_remark() {
        if (AppUsedLists.getWaitRemarkList()==null){
            AppUsedLists.setWaitRemarkList(new ArrayList<Order>());
        }
        orderList = AppUsedLists.getWaitRemarkList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_remark, container, false);
        recyclerView = view.findViewById(R.id.wait_remark_recycler_view);
        WaitRemarkGoodsAdapter adapter = new WaitRemarkGoodsAdapter(orderList);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
