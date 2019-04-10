package seven.team.adapter;

import android.content.Intent;
import seven.team.activity.GoodsDetailsActivity;
import seven.team.util.MyApplication;
import seven.team.util.UsualIntent;
import seven.team.entity.Goods;
import seven.team.activity.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder>{
    private List<Goods> goodsList;

    class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView goodsIcon;
        private TextView descripe;
        public ViewHolder(View itemView) { super(itemView); }
    }
    public GoodsAdapter(List<Goods> goodsList){this.goodsList = goodsList;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView = view.findViewById(R.id.goods_card_view);
        viewHolder.goodsIcon = view.findViewById(R.id.goods_icon);
        viewHolder.descripe = view.findViewById(R.id.description);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContext(), GoodsDetailsActivity.class);
                Goods goods = goodsList.get(pos);
                intent.putExtra("goods_details",goods);
                MyApplication.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goods goods = goodsList.get(position);
//        holder.descripe.setText("132131313");
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }


}
