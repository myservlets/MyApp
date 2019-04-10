package seven.team.activity;

import android.os.Bundle;
import org.litepal.LitePal;

import seven.team.util.BaseActivity;


public class RegisteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        LitePal.getDatabase();
    }
}
