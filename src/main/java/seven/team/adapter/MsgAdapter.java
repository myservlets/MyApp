package seven.team.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import seven.team.entity.ChatMSG;
import seven.team.activity.R;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<ChatMSG> mChatMSGList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;

        private ImageView leftHead;
        private ImageView rightHead;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            DisplayMetrics dm = leftLayout.getResources().getDisplayMetrics();
            int width = dm.widthPixels;     // 屏幕宽度（像素）
            float scale = dm.density;
            leftMsg = itemView.findViewById(R.id.left_Msg);
            leftMsg.setMaxWidth((int)(0.8*width)-(int)(50*scale+0.5f));
            rightMsg = itemView.findViewById(R.id.right_Msg);
            rightMsg.setMaxWidth((int)(0.8*width)-(int)(50*scale+0.5f));
            leftHead = itemView.findViewById(R.id.image_head_left);
            rightHead = itemView.findViewById(R.id.image_head_right);
        }
    }

    public MsgAdapter(List<ChatMSG> mChatMSGList){ this.mChatMSGList = mChatMSGList; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ChatMSG chatMSG = mChatMSGList.get(i);
        if(chatMSG.getType() == ChatMSG.TYPE_RECEIVE){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(chatMSG.getContent());
        }
        if(chatMSG.getType() == ChatMSG.TYPE_SENT){
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(chatMSG.getContent());
        }
    }
    @Override
    public int getItemCount() {
        return mChatMSGList.size();
    }
}
