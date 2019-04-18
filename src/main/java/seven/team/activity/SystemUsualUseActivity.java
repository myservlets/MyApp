package seven.team.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class SystemUsualUseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private TextView figerPay;
    private TextView sweep;
    private TextView shark;
    private TextView changeTheme;
    private TextView changePopple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_usual_use);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        figerPay = findViewById(R.id.figer_pay);
        sweep = findViewById(R.id.sweep_it);
        shark = findViewById(R.id.shark_it);
        changeTheme = findViewById(R.id.change_theme);
        changePopple = findViewById(R.id.change_popple);

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
