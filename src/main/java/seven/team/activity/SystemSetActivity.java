package seven.team.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import seven.team.util.ActivityCollector;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class SystemSetActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout toChangeUser;
    private RelativeLayout toAddress;
    private RelativeLayout toSafety;
    private RelativeLayout toMessageInform;
    private RelativeLayout toUsual;
    private RelativeLayout toAbout;
    private RelativeLayout toExitSys;
    private ImageView returnFormer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        bindData();
    }
    private void bindData(){
        toChangeUser = findViewById(R.id.to_change_user);
        toAddress = findViewById(R.id.to_receive_address);
        toSafety = findViewById(R.id.to_safety);
        toMessageInform = findViewById(R.id.to_message_model);
        toUsual = findViewById(R.id.to_usual_usage);
        toAbout = findViewById(R.id.to_about_system);
        toExitSys = findViewById(R.id.to_exit_system);
        returnFormer = findViewById(R.id.back_frag_4);
        toChangeUser.setOnClickListener(this);
        toAddress.setOnClickListener(this);
        toSafety.setOnClickListener(this);
        toMessageInform.setOnClickListener(this);
        toUsual.setOnClickListener(this);
        toAbout.setOnClickListener(this);
        toExitSys.setOnClickListener(this);
        returnFormer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_change_user:
                UsualIntent.toAnotherPage("LoginedUserActivity");
                break;
            case R.id.to_receive_address:
                UsualIntent.toAnotherPage("ReceiveAddressActivity");
                break;
            case R.id.to_safety:
                UsualIntent.toAnotherPage("SystemSafetyActivity");
                break;
            case R.id.to_message_model:
                UsualIntent.toAnotherPage("SystemInformActivity");
                break;
            case R.id.to_usual_usage:
                UsualIntent.toAnotherPage("SystemUsualUseActivity");
                break;
            case R.id.to_about_system:
                UsualIntent.toAnotherPage("AboutSysActivity");
                break;
            case R.id.back_frag_4:
                finish();
                UsualIntent.toAnotherPage("MainActivity");
                break;
            case R.id.to_exit_system:
                ActivityCollector.finishAll();
                break;
        }
    }
    class LoadAddress extends AsyncTask<Object,Integer,String>{

        @Override
        protected String doInBackground(Object... params) {
            String object = (String) params[0];
            Gson gson = new Gson();
            gson.fromJson(object,String.class);
            return object;
        }
    }
}
