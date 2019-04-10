package seven.team.adapter;

import seven.team.entity.Goods;
import seven.team.activity.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder> implements View.OnClickListener {
    private List<Goods> goods;

    @Override
    public void onClick(View v) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{;
        private RadioButton chooseIt;
        private TextView saler;
        private ImageView goodsIcon;
        private TextView description;
        private TextView price;
        private TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public ShoppingCarAdapter(List<Goods> goods){ this.goods = goods; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_shopping_car,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.chooseIt = view.findViewById(R.id.choose_it);
        viewHolder.saler = view.findViewById(R.id.saler);
        viewHolder.saler.setOnClickListener(this);
        viewHolder.goodsIcon = view.findViewById(R.id.goods_icon);
        viewHolder.description = view.findViewById(R.id.description);
        viewHolder.price = view.findViewById(R.id.price);
        viewHolder.amount = view.findViewById(R.id.quantity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Goods goods = this.goods.get(i);
    }
    @Override
    public int getItemCount() {
        return goods.size();
    }
}
