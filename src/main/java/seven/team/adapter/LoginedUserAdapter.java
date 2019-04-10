package seven.team.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import seven.team.entity.User;
import seven.team.activity.R;

/**
 * Created by Administrator on 2019/3/18 0018.
 */
public class LoginedUserAdapter extends RecyclerView.Adapter<LoginedUserAdapter.ViewHolder> {
    private List<User>userList;
    public LoginedUserAdapter(List<User> userList){
        this.userList = userList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private int iconChangeUser_icon;
        private TextView txtChangeUser_name;
        private TextView txtChangeUser_username;
        public ViewHolder(View itemView) {
            super(itemView);
            txtChangeUser_name = itemView.findViewById(R.id.changeUser_name);
            txtChangeUser_username = itemView.findViewById(R.id.changeUser_username);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_logined_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User user = userList.get(i);
        viewHolder.txtChangeUser_name.setText(user.getNickname());
        viewHolder.txtChangeUser_username.setText(user.getUserId());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
