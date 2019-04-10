package seven.team.activity;

import seven.team.util.BaseActivity;
import seven.team.entity.Recruitment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecruitmentActivity extends BaseActivity implements View.OnClickListener {
    private Recruitment opposeRecruitment;
    private ImageView rImage;
    private TextView rName;
    private TextView rPrice;
    private TextView rDescribe;
    private TextView rUserName;//发布人名字


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);

        rImage = findViewById(R.id.recruit_image);
        rName = findViewById(R.id.recruit_name);
        rPrice = findViewById(R.id.recruit_price);
        rDescribe = findViewById(R.id.recruit_describe);
        rUserName=findViewById(R.id.recruit_user_name);

        findViewById(R.id.ret_pro).setOnClickListener(this);
        findViewById(R.id.image_share).setOnClickListener(this);
        findViewById(R.id.image_kefu).setOnClickListener(this);
        findViewById(R.id.image_add).setOnClickListener(this);
        findViewById(R.id.image_buy).setOnClickListener(this);

        Intent intent = getIntent();
        opposeRecruitment = (Recruitment) intent.getSerializableExtra("opposeRecruitment");
        //TODO
        rName.setText(opposeRecruitment.getName());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ret_pro:
                this.finish();
                break;
            case R.id.image_share:{
                Toast.makeText(RecruitmentActivity.this, "分享", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.image_kefu:{
                Toast.makeText(RecruitmentActivity.this, "客服", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.image_add:{
                Toast.makeText(RecruitmentActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.image_buy:{
                Toast.makeText(RecruitmentActivity.this, "直接购买", Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;


        }
    }
}
