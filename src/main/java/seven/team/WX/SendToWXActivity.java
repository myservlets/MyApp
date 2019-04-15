package seven.team.WX;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import seven.team.activity.R;

public class SendToWXActivity extends Activity {
    private final static String TAG = "SendToWXActivity";
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID,false);
        setContentView(R.layout.activity_send_to_wx);
        initView();

    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    private void initView(){
        findViewById(R.id.send_text).setOnClickListener(new View.OnClickListener() {

            String text = "123";
            @Override
            public void onClick(View v) {
                WXTextObject textObj = new WXTextObject();
                textObj.text = text;

                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;
                // msg.title = "Will be ignored";
                msg.description = text;
                msg.mediaTagName = "我是mediaTagName啊";

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("text");
                req.message = msg;
                req.scene = mTargetScene;

                api.sendReq(req);
                //finish();
            }
        });
    }
}
