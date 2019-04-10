package seven.team.fragment;


import seven.team.entity.Goods;
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

import seven.team.adapter.WaitReceiveGoodsAdapter;
import seven.team.util.MyApplication;
import seven.team.activity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_order_receive extends Fragment {
    private RecyclerView recyclerView;
    private List<Order> orders;

    public Fragment_order_receive() {
        this.orders = new ArrayList<>();
        Order order = new Order();
        Goods goods = new Goods();
        goods.setPrice(1.2);
        goods.setDescribe("12331");
        goods.setNickName("姚连杰");
        order.setGoods(goods);
        order.setStatus(1);
        order.setCost(200.0);
        order.setCount(1);
        this.orders.add(order);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_receive, container, false);
        recyclerView = view.findViewById(R.id.wait_receive_recycler_view);
        WaitReceiveGoodsAdapter adapter = new WaitReceiveGoodsAdapter(orders);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
