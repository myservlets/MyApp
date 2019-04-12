package seven.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import seven.team.activity.ChatingActivity;
import seven.team.entity.ChatMSG;
import seven.team.entity.User;
import seven.team.fragment.Fragment_3;
import seven.team.util.AppUsedLists;
import seven.team.util.MyApplication;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;


public class WebSocketHandler {
    private static String hostURL = "192.168.1.101";


    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;
    private Thread readThread;
    private Thread sendThread;
    private Thread showThread;
    private CopyOnWriteArrayList<ChatMSG> receiveMSGs;

    private boolean threadflag = true;

    private ChatingActivity context;
    private Fragment_3 fragment3;

    public WebSocketHandler(){
        receiveMSGs = new CopyOnWriteArrayList<>();
    }
    public void connSever(final String myid){
        new Thread(){
            @Override
            public void run() {
                try {

                    InetAddress ip = InetAddress.getByName("243i4s6955.zicp.vip");
                    hostURL = ip.getHostAddress();//+":25781";

                    socket = new Socket(hostURL,25781);
                    Log.i("WebSocketHandler", "建立连接：" + socket);
                    readbuff();
                    showMSGs();
                    ChatMSG chatMSG = new ChatMSG();
                    chatMSG.setFromid(myid);
                    chatMSG.setTargetid(myid);
                    chatMSG.setContent("成功连接到聊天服务器");
                    sendMsg(chatMSG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void sendMsg(final ChatMSG msg){
        sendThread = new Thread() {
            @Override
            public void run() {
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream(),"utf-8"));
                    String json = new Gson().toJson(msg);
                    writer.write(json + "\n"); // 写一个UTF-8的信息
                    writer.flush();
                    Log.i("WebSocketHandler", "发送消息：" + json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendThread.start();
    }

    public void readbuff() {
        readThread = new Thread(){
            @Override
            public void run() {
                try {
                    reader = new BufferedReader(new InputStreamReader(
                            socket.getInputStream(),"utf-8"));
                    while (threadflag){
                        // 读取数据
                        if(reader.ready()){
                            String json = reader.readLine();
                            Gson gson = new Gson();
                            final ChatMSG msg=gson.fromJson(json, ChatMSG.class);
                            if(msg.getContent().equals("成功连接到聊天服务器")){
                                Log.i("WebSocketHandler", "收到消息：" + json);
                                continue;
                            }else if(msg.getContent().equals("")){
                                continue;
                            }
                            receiveMSGs.add(msg);
                            bubbleSort();
                            if(context == null){
                                for(final User friend: AppUsedLists.getMyfriendlist()){
                                    if(msg.getFromid().equals(friend.getUserId())){
                                        Message message = new Message();
                                        message.obj = "收到来自"+friend.getNickname()+"的消息:"+msg.getContent();
                                        handler.sendMessage(message);
                                    }
                                }
                            }
//                            if(context!=null){
//                                context.currentMSG = msg.getContent();
//                                Message message = new Message();
//                                context.msghandler.sendMessage(message);
//                                Log.i("WebSocketHandler", "收到消息：" + json);
//                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        readThread.start();
    }

    public void showMSGs(){
        showThread = new Thread(){
            @Override
            public void run(){
                while (threadflag){
                    if(receiveMSGs.size()>0){
                        if(context!=null){
                            for(ChatMSG msg:receiveMSGs){
                                if(context.opposeUser.getUserId().equals(msg.getFromid())){
                                    msg.setType(ChatMSG.TYPE_RECEIVE);
                                    context.chatMSGList.add(msg);
                                    Message message = new Message();
                                    context.msghandler.sendMessage(message);
                                    receiveMSGs.remove(msg);
                                    Log.i("WebSocketHandler", "收到消息：" + msg.getContent());
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        showThread.start();

    }
    public void closeAll(){
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        threadflag = false;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String str = (String) msg.obj;
            Toast.makeText(MyApplication.getContext(),str,Toast.LENGTH_LONG).show();
        };
    };

    public void setContext(ChatingActivity context) {
        this.context = context;
    }

    public void setFragment3(Fragment_3 fragment3) {
        this.fragment3 = fragment3;
    }

    public void bubbleSort() {
        int size = receiveMSGs.size();
        if (size < 2) return;       //如果只有一个元素就不用排序了

        for (int i = 0; i < size; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < size - i - 1; ++j) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (receiveMSGs.get(j).getDate().getTime() > receiveMSGs.get(j+1).getDate().getTime()) {        //即这两个相邻的数是逆序的，交换
                    ChatMSG temp = receiveMSGs.get(j);
                    receiveMSGs.set(j,receiveMSGs.get(j+1));
                    receiveMSGs.set(j+1,temp);
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }
}
