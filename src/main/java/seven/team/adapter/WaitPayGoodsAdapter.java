package seven.team.adapter;

import seven.team.activity.MerchantGoodsActivity;
import seven.team.activity.PayActivity;
import seven.team.entity.Goods;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

import seven.team.util.UsualIntent;
import seven.team.entity.Order;
import seven.team.activity.R;

/**
 * Created by Administrator on 2019/3/30 0030.
 */
public class WaitPayGoodsAdapter extends RecyclerView.Adapter<WaitPayGoodsAdapter.ViewHolder> implements View.OnClickListener {

    private List<Order> orders;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_head:
                UsualIntent.toAnotherPage(MerchantGoodsActivity.class);
                break;
            case R.id.order_goods:
                break;
            case R.id.cancel_order:
                break;
            case R.id.to_pay:
                UsualIntent.toAnotherPage(PayActivity.class);
                break;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout orderHead;
        private LinearLayout orderGoods;
        private TextView saler;
        private TextView orderState;
        private ImageView orderIcon;
        private TextView description;
        private TextView price;
        private TextView quantity;
        private TextView totalPrice;
        private Button cancelOrder;
        private Button pay;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public WaitPayGoodsAdapter(List<Order> orders){ this.orders = orders; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wait_pay,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.orderHead = view.findViewById(R.id.order_head);
        viewHolder.orderGoods = view.findViewById(R.id.order_goods);
        viewHolder.orderHead.setOnClickListener(this);
        viewHolder.orderGoods.setOnClickListener(this);
        viewHolder.saler = view.findViewById(R.id.saler);
        viewHolder.orderState = view.findViewById(R.id.sales_state);
        viewHolder.orderIcon = view.findViewById(R.id.goods_icon);
        viewHolder.description = view.findViewById(R.id.description);
        viewHolder.price = view.findViewById(R.id.price);
        viewHolder.quantity = view.findViewById(R.id.quantity);
        viewHolder.totalPrice = view.findViewById(R.id.total_price);
        viewHolder.cancelOrder = view.findViewById(R.id.cancel_order);
        viewHolder.pay = view.findViewById(R.id.to_pay);
        viewHolder.cancelOrder.setOnClickListener(this);
        viewHolder.pay.setOnClickListener(this);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Order order = orders.get(i);
        Goods goods = order.getGoods();
        viewHolder.saler.setText(goods.getNickName());
        viewHolder.orderState.setText(String.valueOf(order.getStatus()));
        viewHolder.description.setText(goods.getDescribe());
        viewHolder.price.setText(String.valueOf(goods.getPrice()));
        viewHolder.quantity.setText(String.valueOf(order.getCount()));
        viewHolder.totalPrice.setText(String.valueOf(order.getCost()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
