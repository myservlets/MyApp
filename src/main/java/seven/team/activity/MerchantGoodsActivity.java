package seven.team.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import okhttp3.*;
import seven.handler.ServletsConn;
import seven.team.entity.Goods;
import seven.team.entity.LoginUser;
import seven.team.util.BaseActivity;
import android.os.Bundle;
import seven.team.util.MyApplication;
import seven.team.util.MyProgressDialog;

import java.io.File;
import java.io.IOException;

public class MerchantGoodsActivity extends BaseActivity implements View.OnClickListener {

    public static MediaType mediaType = MediaType.parse("image/png");
    private MyProgressDialog progressDialog;
    private String imagePath;
    private Bitmap photoBitmap;
    private boolean isCamera;

    private ImageView returnFormer;
    private ImageView goodsIcon;
    private TextView title;
    private Button submitGoods;
    private TextView goodsNameTitle;
    private TextView goodsProducerTitle;
    private TextView goodsPriceTitle;
    private TextView goodsTypeTitle;
    private TextView goodsContentTitle;
    private TextView goodsDescripeTitle;
    private TextView goodsQuantityTitle;
    private EditText goodsName;
    private EditText goodsProducer;
    private EditText goodsPrice;
    private EditText goodsType;
    private EditText goodsContent;
    private EditText goodsDescripe;
    private EditText goodsQuantity;
    private LinearLayout goodsNameLayout;
    private LinearLayout goodsProducerLayout;
    private LinearLayout goodsPriceLayout;
    private LinearLayout goodsTypeLayout;
    private LinearLayout goodsContentLayout;
    private LinearLayout goodsDescripeLayout;
    private LinearLayout goodsQuantityLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_goods_list);
        bindData();
    }

    private void bindData(){
        returnFormer = findViewById(R.id.return_former);
        goodsIcon = findViewById(R.id.add_goods_icon);
        goodsIcon.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText("上架商品");
        returnFormer.setOnClickListener(this);
        submitGoods = findViewById(R.id.submit_goods);
        submitGoods.setOnClickListener(this);
        goodsNameLayout = findViewById(R.id.layout_goods_name);
        goodsProducerLayout = findViewById(R.id.layout_goods_producer);
        goodsPriceLayout = findViewById(R.id.layout_goods_price);
        goodsTypeLayout = findViewById(R.id.layout_goods_type);
        goodsContentLayout = findViewById(R.id.layout_goods_content);
        goodsDescripeLayout = findViewById(R.id.layout_goods_descripe);
        goodsQuantityLayout = findViewById(R.id.layout_goods_quantity);
        goodsNameTitle = goodsNameLayout.findViewById(R.id.text);
        goodsProducerTitle = goodsProducerLayout.findViewById(R.id.text);
        goodsPriceTitle = goodsPriceLayout.findViewById(R.id.text);
        goodsTypeTitle = goodsTypeLayout.findViewById(R.id.text);
        goodsContentTitle = goodsContentLayout.findViewById(R.id.text);
        goodsDescripeTitle = goodsDescripeLayout.findViewById(R.id.text);
        goodsQuantityTitle = goodsQuantityLayout.findViewById(R.id.text);
        goodsName = goodsNameLayout.findViewById(R.id.edit_text);
        goodsProducer = goodsProducerLayout.findViewById(R.id.edit_text);
        goodsPrice = goodsPriceLayout.findViewById(R.id.edit_text);
        goodsType = goodsTypeLayout.findViewById(R.id.edit_text);
        goodsContent = goodsContentLayout.findViewById(R.id.edit_text);
        goodsDescripe = goodsDescripeLayout.findViewById(R.id.edit_text);
        goodsQuantity = goodsQuantityLayout.findViewById(R.id.edit_text);
        goodsNameTitle.setText("商品名字");
        goodsProducerTitle.setText("生产厂家");
        goodsPriceTitle.setText("商品价格");
        goodsTypeTitle.setText("商品类型");
        goodsContentTitle.setText("商品简介");
        goodsDescripeTitle.setText("商品描述");
        goodsQuantityTitle.setText("商品数量");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                //UsualIntent.toAnotherPage("MainActivity");
                finish();
                break;
            case R.id.add_goods_icon:
                isCamera = false;
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,1);
                break;
                // TODO: 2019/4/16 0016 添加商品图
            case R.id.submit_goods:
                Goods goods = new Goods();
                goods.setGoodsName(goodsName.getText().toString());
                goods.setNickName(LoginUser.getLoginUser().getNickname());
                goods.setType(goodsType.getText().toString());
                goods.setContent(goodsContent.getText().toString());
                goods.setPrice(Double.parseDouble(goodsPrice.getText().toString()));
                goods.setDescribe(goodsDescripe.getText().toString());
                goods.setQuantity(Integer.parseInt(goodsQuantity.getText().toString()));
                goods.setUserId(LoginUser.getLoginUser().getUserId());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode==MainActivity.RESULT_OK){
                    handLeImageOnLitKat(data);
                }
                break;
        }
    }

    private void handLeImageOnLitKat(Intent data){
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        progressDialog = new MyProgressDialog(this);
        progressDialog.setMessage("上传头像中，请稍等");
        progressDialog.setTimeOut(5000, new MyProgressDialog.OnTimeOutListener() {
            @Override
            public void onTimeOut(MyProgressDialog dialog) {
                progressDialog.dismiss();
                Toast.makeText(MyApplication.getContext(),"头像上传超时",Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.setCancelable(false);
        progressDialog.show();
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = this.getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if(imagePath!=null){
            photoBitmap = BitmapFactory.decodeFile(imagePath);

            fileupload(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) { }
                @Override
                public void onResponse(Call call, Response response) throws IOException {  }});


        }
    }

    public void fileupload(Callback callback) {
        // 获得输入框中的路径
        String path = null;
        if(isCamera){
            path = this.getExternalCacheDir().getPath()+"/out_put.jpg";
        }else {
            path = imagePath;
        }
        mediaType = MediaType.parse("image/"+path.substring(path.lastIndexOf(".")+1));
        File file = new File(path);
        OkHttpClient client = new OkHttpClient();
        // 上传文件使用MultipartBody.Builder
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", LoginUser.getLoginUser().getUserId()) // 提交普通字段
                .addFormDataPart("sign", "0") // 提交普通字段
                .addFormDataPart("image", file.getName(), RequestBody.create(mediaType, file)) // 提交图片，第一个参数是键（name="第一个参数"），第二个参数是文件名，第三个是一个RequestBody
                .build();
        // POST请求
        Request request = new Request.Builder()
                .url(ServletsConn.host+"imagehandler")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
