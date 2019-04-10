package seven.team.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import seven.team.adapter.ReceiveAddressAdapter;
import seven.team.util.BaseActivity;
import seven.team.entity.ReceiveInfo;
import seven.team.entity.User;
import seven.handler.ServletsConn;

public class ReceiveAddressActivity extends BaseActivity implements View.OnClickListener {

    private final int TO_ADDING_ADDRESS = 1;
    private ImageView btnBackFormer;
    private TextView txtToAddAddress;
    private List<ReceiveInfo> addressList;
    private RecyclerView recyclerView;
    private ReceiveAddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_address_list);
        initAddressList();
        bindData();
    }

    private void bindData(){
        recyclerView = findViewById(R.id.address_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ReceiveAddressAdapter(addressList);
        recyclerView.setAdapter(adapter);
        btnBackFormer = findViewById(R.id.back_set_page_2);
        btnBackFormer.setOnClickListener(this);
        txtToAddAddress = findViewById(R.id.to_add_address);
        txtToAddAddress.setOnClickListener(this);
    }

    private void initAddressList(){
        //User loginUser = LoginUser.getLoginUser();
        // TODO: 2019/3/25 0025  传送一个user对象，初始化list
        //new ReceiveAddressTask().execute(loginUser);

        addressList = new ArrayList<ReceiveInfo>();
        for(int i=0;i<2;i++){
            ReceiveInfo address = new ReceiveInfo();
            address.setName("姚连杰");
            address.setPhone("13297992961");
            address.setAddress("国际软件学院");
            addressList.add(address);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.back_set_page_2:
                finish();
                intent = new Intent(this, SystemSetActivity.class);
                startActivity(intent);
                break;
            case R.id.to_add_address:
                intent = new Intent(this, NewAddressActivity.class);
                startActivityForResult(intent,TO_ADDING_ADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TO_ADDING_ADDRESS:
                if(resultCode == RESULT_OK){
                    ReceiveInfo receiveInfo = (ReceiveInfo)data.getSerializableExtra("address");
                    if(receiveInfo!=null){
                        addressList.add(receiveInfo);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
    class ReceiveAddressTask extends AsyncTask<Object,Integer,String> {

        @Override
        protected String doInBackground(Object... objs) {
            User user = (User) objs[0];
            Gson gson = new Gson();
            String json = gson.toJson(user);
            ServletsConn.connServlets("ReceiveInfoList",json);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            int flag=0;//用于标识的信号量
            if(json != null){
                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(json,JsonObject.class);
                flag=Integer.parseInt(jsonObject.get("status").toString());
                if(flag==0){
                    //编辑成功
                    System.out.println("查询成功！！！");
                }
                //addressList = gson.fromJson(jsonObject.get("ArrayList<ReceiveInfo>"),new TypeToken<List<ReceiveInfo>>(){}.getType());
                addressList = new ArrayList<ReceiveInfo>();
                for(int i=0;i<2;i++){
                    ReceiveInfo address = new ReceiveInfo();
                    address.setName("姚连杰");
                    address.setPhone("13297992961");
                    address.setAddress("国际软件学院");
                    addressList.add(address);
                }
            }
            else {
                System.out.println("查询失败！！！");
            }
        }
    }
}
