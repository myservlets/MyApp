package seven.team.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class SystemSafetyActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private TextView changePassword;
    private TextView bindPhone;
    private TextView bindEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_safety);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        changePassword = findViewById(R.id.change_password);
        bindPhone = findViewById(R.id.bind_phone);
        bindEmail = findViewById(R.id.bind_email);

        returnFormer.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                //UsualIntent.toAnotherPage("SystemSetActivity");
                break;
        }
    }
}
