package seven.team.activity;

import seven.team.util.UsualIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RefundDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView returnFormer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_details);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        returnFormer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                finish();
                //UsualIntent.toAnotherPage("MyOrderActivity");
                break;
        }
    }
}
