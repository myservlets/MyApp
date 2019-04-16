package seven.team.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import seven.team.entity.User;
import seven.team.activity.ChatingActivity;
import seven.team.activity.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<User>mUserList;
    static class ViewHolder extends RecyclerView.ViewHolder{;
        private TextView txtName;
        private TextView txtNewChat;
        private LinearLayout layUserinfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            layUserinfo = itemView.findViewById(R.id.userinfo);
            txtNewChat = itemView.findViewById(R.id.new_chat_info);
        }
    }
    public UserAdapter(List<User>mUserList){ this.mUserList = mUserList; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.layUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                User opposeUser = mUserList.get(position);
                Intent intent = new Intent(view.getContext(), ChatingActivity.class);
                intent.putExtra("opposeUser",opposeUser);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User user = mUserList.get(i);
        viewHolder.txtName.setText(user.getNickname());
        viewHolder.txtNewChat.setText(user.getUserId());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
