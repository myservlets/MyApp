package seven.team.adapter;

import seven.team.entity.Goods;
import seven.team.activity.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RelativeGoodsAdapter extends RecyclerView.Adapter<RelativeGoodsAdapter.ViewHolder>{
    private List<Goods>mGoodsList;
    static class ViewHolder extends RecyclerView.ViewHolder{;
        private TextView goodsName;
        private TextView goodsDescribe;
        private TextView goodsPrice;
        private TextView goodsQuantity;
        private LinearLayout layGoodsInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            goodsName = itemView.findViewById(R.id.goods_name);
            goodsDescribe = itemView.findViewById(R.id.goods_describe);
            goodsPrice = itemView.findViewById(R.id.goods_price);
            goodsQuantity = itemView.findViewById(R.id.goods_quantity);
            layGoodsInfo = itemView.findViewById(R.id.goods_item);
        }
    }
    public RelativeGoodsAdapter(List<Goods>mGoodsList){ this.mGoodsList = mGoodsList; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_searched_goods,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Goods goods = mGoodsList.get(i);
        //viewHolder.goodsName.setText(goods.getNickName());
        //viewHolder.goodsDescribe.setText(goods.getDescribe());
        //viewHolder.goodsPrice.setText(String.valueOf(goods.getPrice()));
        //viewHolder.goodsQuantity.setText(goods.getQuantity());
    }
    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}
