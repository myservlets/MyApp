package seven.team.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.activity.R;
import seven.team.entity.Comment;

import java.util.List;

public class GoodsRemarkAdapter extends RecyclerView.Adapter<GoodsRemarkAdapter.ViewHolder> {
    private List<Comment>commentList;

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView remarkerIcon;
        TextView remarkerName;
        TextView remarks;
        ImageView goodsPtoto;
        TextView remarkScore;
        TextView remarkDate;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public GoodsRemarkAdapter(List<Comment>commentList){ this.commentList = commentList; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_remark,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.remarkerIcon = view.findViewById(R.id.remarker_icon);
        viewHolder.remarkerName = view.findViewById(R.id.remarker_name);
        viewHolder.remarkScore = view.findViewById(R.id.remarker_score);
        viewHolder.remarkDate = view.findViewById(R.id.remark_date);
        viewHolder.remarks = view.findViewById(R.id.remarks);
        viewHolder.goodsPtoto = view.findViewById(R.id.remark_photo);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
