package seven.team.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.util.BaseActivity;

public class MerchantTaskActivity extends BaseActivity implements View.OnClickListener {
    private ImageView returnFormer;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_task);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        title.setText("上传任务");
        returnFormer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                break;
        }
    }
}
