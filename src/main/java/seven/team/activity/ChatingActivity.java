package seven.team.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Handler;
import seven.team.activity.R;
import seven.team.adapter.MsgAdapter;
import seven.team.entity.ChatMSG;
import seven.team.entity.LoginUser;
import seven.team.entity.User;
import seven.team.util.BaseActivity;

public class ChatingActivity extends BaseActivity implements View.OnClickListener {



    public List<ChatMSG> chatMSGList = new ArrayList<ChatMSG>();
    public User opposeUser;
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private TextView opposeText;
    private ImageView retChater;
    private MsgAdapter adapter;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        initMsgs();
        bindData();
        flag = true;

        opposeText.setText(opposeUser.getNickname());
    }

    private void bindData(){
        opposeUser = (User) getIntent().getSerializableExtra("opposeUser");
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(chatMSGList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(this);
        opposeText = findViewById(R.id.oppose_name);
        retChater = findViewById(R.id.return_former);
        retChater.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(flag){
            init();
            flag = false;
        }else {
            LoginUser.getWebSocketHandler().setContext(null);
            this.finish();
        }

    }

    private void init() {
        //webSocketHandler = ;
        LoginUser.getWebSocketHandler().setContext(this);
        //webSocketHandler.connSever(LoginUser.getLoginUser().getUserId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chating,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private void initMsgs(){

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.send:
                String content = inputText.getText().toString();
                if(!content.equals("")){
                    ChatMSG chatMSG = new ChatMSG(content, ChatMSG.TYPE_SENT);
                    chatMSGList.add(chatMSG);
                    adapter.notifyItemInserted(chatMSGList.size() - 1);//刷新消息展示
                    msgRecyclerView.scrollToPosition(chatMSGList.size() - 1);//定位到最后一行

                    ChatMSG msg = new ChatMSG();
                    msg.setFromid(LoginUser.getLoginUser().getUserId());
                    //msg.setTargetid(opposeUser.getUserId());
                    msg.setTargetid(opposeUser.getUserId());
                    msg.setContent(content);
                    msg.setDate(new Date());
                    //webSocketHandler.sendMsg(msg);
                    LoginUser.getWebSocketHandler().sendMsg(msg);
                    inputText.setText("");
                }
                break;
            case R.id.return_former:
                intent = new Intent(this,MainActivity.class);
                LoginUser.getWebSocketHandler().setContext(null);
                this.finish();
                startActivity(intent);
                break;
        }
    }

    // 构建Runnable对象。在runnable中更新界面
//    public Runnable   runnableUi=new  Runnable(){
//        @Override
//        public void run() {
//            //更新界面
//            textView.setText(msg);
//        }
//    };
    public Handler msghandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            adapter.notifyItemChanged(chatMSGList.size() - 1);//刷新消息展示
            msgRecyclerView.scrollToPosition(chatMSGList.size() - 1);//定位到最后一行
//            if(currentMSG!=null){
//                ChatMSG chatMSG = new ChatMSG(currentMSG, ChatMSG.TYPE_RECEIVE);
//                chatMSGList.add(chatMSG);
//                adapter.notifyItemInserted(chatMSGList.size() - 1);//刷新消息展示
//                msgRecyclerView.scrollToPosition(chatMSGList.size() - 1);//定位到最后一行
//                currentMSG = null;
//            }
        }
    };
}
