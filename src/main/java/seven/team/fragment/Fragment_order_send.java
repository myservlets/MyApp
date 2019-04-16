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

import seven.team.adapter.WaitSendGoodsAdapter;
import seven.team.util.AppUsedLists;
import seven.team.util.MyApplication;
import seven.team.activity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_order_send extends Fragment {
    private RecyclerView recyclerView;
    private List<Order> orderList;

    public Fragment_order_send() {
        if (AppUsedLists.getWaitSendList()==null){
            AppUsedLists.setWaitSendList(new ArrayList<Order>());
        }
        orderList = AppUsedLists.getWaitSendList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_send, container, false);
        recyclerView = view.findViewById(R.id.wait_send_recycler_view);
        WaitSendGoodsAdapter adapter = new WaitSendGoodsAdapter(orderList);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;    }

}
