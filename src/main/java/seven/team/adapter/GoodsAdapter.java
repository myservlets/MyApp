package seven.team.adapter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import seven.team.activity.GoodsDetailsActivity;
import seven.team.fragment.Fragment_1;
import seven.team.thread.GoodsManageTask;
import seven.team.util.MyApplication;
import seven.team.util.MyProgressDialog;
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
    private static Intent intent;

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
                intent = new Intent(view.getContext(), GoodsDetailsActivity.class);
                Goods goods = goodsList.get(pos);
                goods.setGoodsId(5);
//                intent.putExtra("goods_data",goods);
//
//                Comment comment = new Comment();
//                comment.setScore(3);
//                comment.setContent("这本书非常的不错");
//                intent.putExtra("comment_data",comment);

                // TODO: 2019/4/10 0010
                Fragment_1.myProgressDialog.setMessage("请稍等");
                Fragment_1.myProgressDialog.setTimeOut(5000, new MyProgressDialog.OnTimeOutListener() {
                    @Override
                    public void onTimeOut(MyProgressDialog dialog) {
                        Fragment_1.myProgressDialog.dismiss();
                        Toast.makeText(MyApplication.getContext(),"登录超时请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
                Fragment_1.myProgressDialog.setCancelable(false);
                Fragment_1.myProgressDialog.show();
                new GoodsManageTask().execute(5,goods);
            }
        });
        return viewHolder;
    }
    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Fragment_1.myProgressDialog.dismiss();
                    MyApplication.getContext().startActivity(intent);
            }
        }
    };

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
