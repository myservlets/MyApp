package seven.team.adapter;

import seven.team.util.MyApplication;
import seven.team.entity.LoginUser;
import seven.team.entity.User;
import seven.team.activity.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import seven.handler.ServletsConn;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class RelativeUserAdapter extends RecyclerView.Adapter<RelativeUserAdapter.ViewHolder> implements View.OnClickListener {
    private List<User>userList;
    private User user;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relative_user_layout:
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(v.getContext());
                normalDialog.setMessage("是否添加对方为好友")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new SureAddFriends().execute(0,LoginUser.getLoginUser(),user);
                                }
                            })
                            .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                normalDialog.show();
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtUserName;
        private LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.relative_name);
            txtUserName = itemView.findViewById(R.id.relative_username);
            layout = itemView.findViewById(R.id.relative_user_layout);
        }
    }
    public RelativeUserAdapter(List<User>userList){
        this.userList = userList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_searched_user,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.layout.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            user = userList.get(i);
            viewHolder.txtName.setText(user.getNickname());
            viewHolder.txtUserName.setText(user.getUserId());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    class SureAddFriends extends AsyncTask<Object,Integer,String>{

        @Override
        protected String doInBackground(Object... params) {
            int sign = (Integer) params[0];
            User loginuser = (User) params[1];
            User user = (User)params[2];
            Gson gson = new Gson();
            String json = "{'sign':"+sign+",'User0':"+gson.toJson(loginuser)+",'User1':"+gson.toJson(user)+"}";
            json = ServletsConn.connServlets("addfriend",json);
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            // status：0/已发送好友请求、1/用户不存在、2/失败、3/该用户已在你的好友列表、4/你已接受该用户的好友请求
            int flag=0;
            if(s != null){
                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(s,JsonObject.class);
                flag=Integer.parseInt(jsonObject.get("status").toString());
                if(flag==0){
                    Toast.makeText(MyApplication.getContext(),"已成功发送",Toast.LENGTH_SHORT).show();
                }else if (flag==1){
                    Toast.makeText(MyApplication.getContext(),"登录失败",Toast.LENGTH_SHORT).show();
                }
                else if(flag == 2){
                    Toast.makeText(MyApplication.getContext(),"添加",Toast.LENGTH_SHORT).show();
                }
                else if(flag == 3){
                    Toast.makeText(MyApplication.getContext(),"该用户已经在您的列表",Toast.LENGTH_SHORT).show();
                }
                else if(flag == 4){
                    Toast.makeText(MyApplication.getContext(),"已经接受",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(MyApplication.getContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
