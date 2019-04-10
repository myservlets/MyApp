package seven.team.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class SystemAboutActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private TextView versionUpdate;
    private TextView help;
    private TextView feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_system);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        versionUpdate = findViewById(R.id.version_update);
        help = findViewById(R.id.help);
        feedback = findViewById(R.id.feedback);
        returnFormer.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                UsualIntent.toAnotherPage("SystemSetActivity");
                break;
        }
    }
}
