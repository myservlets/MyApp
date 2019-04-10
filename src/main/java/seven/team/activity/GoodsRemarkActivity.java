package seven.team.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.util.BaseActivity;
import seven.team.util.UsualIntent;

public class GoodsRemarkActivity extends BaseActivity implements View.OnClickListener {

    private ImageView returnFormer;
    private TextView tittle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_remark);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        tittle = findViewById(R.id.title);
        tittle.setText("商品评论");
        returnFormer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                UsualIntent.toAnotherPage("GoodsDetailsActivity");
                break;
        }
    }
}
