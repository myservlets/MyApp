package seven.team.activity;

import android.app.ProgressDialog;
import seven.team.util.AddressGetter;
import seven.team.util.BaseActivity;
import seven.team.util.MyApplication;
import seven.team.sqlite.Province;
import seven.team.entity.LoginUser;
import seven.team.entity.User;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import seven.handler.ServletsConn;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher ,View.OnTouchListener{
    private final static int PERMISSION_REQUEST = 1;
    public LocationClient mLocationClient;
    private ProgressDialog progressDialog;//弹出的提示框
    private Button btnLogin;
    private EditText username;
    private EditText password;
    private TextView rigiste_user;
    private TextView remember_psd;
    private View lay_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        bindData();
        initPermission();
        initAddress();
    }

    private void bindData(){
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        username = findViewById(R.id.txtUsername);
        username.addTextChangedListener(this);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        rigiste_user = findViewById(R.id.registe_new_user);
        remember_psd = findViewById(R.id.remember_psd);
        rigiste_user.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        rigiste_user.getPaint().setAntiAlias(true);
        remember_psd.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        remember_psd.getPaint().setAntiAlias(true);
        lay_login = findViewById(R.id.login_layout);
        lay_login.setOnTouchListener(this);
    }

    private void initPermission(){
        List<String>permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[]permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,PERMISSION_REQUEST);
        }else {
            requestLocation();
        }
    }

    private void initAddress(){
    }

    private void requestLocation(){
        mLocationClient.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                User user = new User();
                user.setUserId(username.getText().toString());
                user.setPassword(password.getText().toString());

                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("正在登陆，请稍等");
                progressDialog.setCancelable(false);
                progressDialog.show();

                //// TODO: 2019/3/22 0022 检查本地是否有该用户的信息，若有，则不必访问服务器
                new LoginTask().execute(user);
                //Intent intent = new Intent(MyApplication.getContext(),MainActivity.class);
                //startActivity(intent);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //// TODO: 2019/3/22 0022 检查本地登录过的账号，是够有此用户的信息，若有则从本地获取用户
        //List<User>users = DataSupport.where("userId",username.getText().toString()).find(User.class);
        //if(users!=null){
        //    loginUser = users.get(0);
        //}
    }

    @Override
    public void afterTextChanged(Editable s) {

    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        lay_login.setFocusable(true);
        lay_login.setFocusableInTouchMode(true);
        lay_login.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rigiste_user.getWindowToken(), 0);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST:
                if(grantResults.length > 0){
                    for(int result : grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"您的权限未通过",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }
                break;
        }
    }
    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //// TODO: 2019/3/22 0022 获取到经纬度
            //当前地址
            StringBuilder sb = new StringBuilder();
            sb.append(bdLocation.getProvince())
                    .append(bdLocation.getCity())
                    .append(bdLocation.getDistrict())
                    .append(bdLocation.getStreet());
        }
    }

    class LoginTask extends AsyncTask<Object,Integer,String> {
        @Override
        protected String doInBackground(Object... objects) {
            User user = (User)objects[0];
            Gson gson=new Gson();
            String json=gson.toJson(user);
            json = ServletsConn.connServlets("login",json);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            int flag=0;
            if(json != null){
                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(json,JsonObject.class);
                flag=Integer.parseInt(jsonObject.get("status").toString());
                LoginUser.setLoginUser(gson.fromJson(jsonObject.get("User").toString(),User.class));
                if(flag==0){
                    progressDialog.dismiss();
                    Intent intent = new Intent(MyApplication.getContext(),MainActivity.class);
                    startActivity(intent);
                    // TODO: 2019/3/24 0024 获取到所有信息之后
                    //loginUser.save();
                }else if (flag==1){
                    Toast.makeText(MyApplication.getContext(),"登录失败",Toast.LENGTH_SHORT).show();
                }
                else if(flag == 2){
                    Toast.makeText(MyApplication.getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(MyApplication.getContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class obtainAddress extends AsyncTask<Object,Integer,List<Province>>{
        List<Province>provinces;
        @Override
        protected List<Province> doInBackground(Object... params) {
            AddressGetter addressGetter = new AddressGetter();
            addressGetter.getAddress(MyApplication.getContext());
            provinces = addressGetter.getProvinces();
            return provinces;
        }

        @Override
        protected void onPostExecute(List<Province> provinces) {
            //// TODO: 2019/3/30 0030 存入数据库
        }
    }

}