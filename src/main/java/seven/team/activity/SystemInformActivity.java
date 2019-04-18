package seven.team.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class SystemInformActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private TextView openInform;
    private TextView sound;
    private TextView light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_inform);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        openInform = findViewById(R.id.open_inform);
        sound = findViewById(R.id.sound);
        light = findViewById(R.id.light);

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
