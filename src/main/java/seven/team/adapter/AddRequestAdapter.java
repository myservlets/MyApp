package seven.team.adapter;

import seven.team.entity.User;
import seven.team.activity.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AddRequestAdapter extends RecyclerView.Adapter<AddRequestAdapter.ViewHolder> {
    private List<User>users;
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView userIcon;
        private TextView userName;
        private TextView userExtra;
        private Button agreeAdd;
        public ViewHolder(View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon);
            userName = itemView.findViewById(R.id.user_name);
            userExtra = itemView.findViewById(R.id.user_extra);
            agreeAdd = itemView.findViewById(R.id.agree_add);
        }
    }

    public AddRequestAdapter(List<User> users){
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_request,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
