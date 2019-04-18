package seven.team.adapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.activity.GoodsDetailsActivity;
import seven.team.activity.R;
import seven.team.entity.Comment;
import seven.team.entity.Goods;
import seven.team.util.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class HistoryBrowseAdapter extends RecyclerView.Adapter<HistoryBrowseAdapter.ViewHolder> {
    private List<Goods>goodsList;

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView goodsIcon;
        private TextView goodsContent;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public HistoryBrowseAdapter(List<Goods>goodsList){ this.goodsList = goodsList; }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_browse,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.goodsIcon = view.findViewById(R.id.goods_icon);
        viewHolder.goodsContent = view.findViewById(R.id.goods_content);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), GoodsDetailsActivity.class);
                int position = viewHolder.getAdapterPosition();
                Goods goods = goodsList.get(position);
                intent.putExtra("goods_data",goods);

                Comment comment = new Comment();
                comment.setScore(3);
                comment.setContent("这本书非常的不错");
                intent.putExtra("comment_data",comment);


                MyApplication.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goods goods = goodsList.get(position);
        //holder.goodsIcon
        holder.goodsContent.setText(goods.getContent());
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }
}
