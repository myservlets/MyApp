package seven.team.fragment;


import android.widget.Toast;
import seven.handler.ServletsConn;
import seven.team.entity.LoginUser;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import seven.team.util.UsualIntent;
import seven.team.activity.MainActivity;
import seven.team.activity.R;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_4 extends Fragment implements View.OnClickListener {

    public static MediaType mediaType = MediaType.parse("image/png");

    private static final int CHOOSE_FROM_PHOTOS = 1;
    private static final int CHOOSE_FROM_CAMERA = 2;

    private FloatingActionButton floatingActionButton;
    private Bitmap cameraBitmap;
    private Bitmap photoBitmap;
    private String imagePath;
    private Uri imageUri;
    private CircleImageView imgUserHead;
    private TextView nickName;
    private Button shopCart;
    private Button wishList;
    private Button browseHistory;
    private Button toSetPage;
    private AlertDialog dialog;
    private TextView allOrders;
    private TextView waitPay;
    private TextView waitSend;
    private TextView waitReceive;
    private TextView waitRemark;
    private TextView waitFund;
    private boolean isCamera;
    private Handler handler = new Handler();
    public Fragment_4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4,container,false);
        imgUserHead = view.findViewById(R.id.user_icon);
        imgUserHead.setOnClickListener(this);
        nickName = view.findViewById(R.id.nickname);
        nickName.setOnClickListener(this);
        toSetPage = view.findViewById(R.id.to_set_page);
        toSetPage.setOnClickListener(this);
        browseHistory = view.findViewById(R.id.browse_history);
        browseHistory.setOnClickListener(this);
        wishList = view.findViewById(R.id.wish_list);
        wishList.setOnClickListener(this);
        shopCart = view.findViewById(R.id.grounding_manage);
        shopCart.setOnClickListener(this);
        allOrders = view.findViewById(R.id.all_orders);
        allOrders.setOnClickListener(this);
        waitPay = view.findViewById(R.id.wait_pay);
        waitSend = view.findViewById(R.id.wait_send);
        waitReceive = view.findViewById(R.id.wait_receive);
        waitRemark = view.findViewById(R.id.wait_remark);
        waitFund = view.findViewById(R.id.wait_refund);
        waitPay.setOnClickListener(this);
        waitSend.setOnClickListener(this);
        waitReceive.setOnClickListener(this);
        waitRemark.setOnClickListener(this);
        waitFund.setOnClickListener(this);
        floatingActionButton = view.findViewById(R.id.goods_car);
        floatingActionButton.setOnClickListener(this);
        init();
        return view;
    }

    private void showTypeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialog = builder.create();
        View view = View.inflate(getActivity(),R.layout.dialog_choose_head_icon,null);
        TextView camera = view.findViewById(R.id.choose_from_camera);
        TextView photos = view.findViewById(R.id.choose_from_photos);
        TextView cancel = view.findViewById(R.id.cancel);
        camera.setOnClickListener(this);
        photos.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.user_icon:
                showTypeDialog();
                break;
            case R.id.nickname:
                break;
            case R.id.to_set_page:
                UsualIntent.toAnotherPage("SystemSetActivity");
                break;
            case R.id.grounding_manage:
                UsualIntent.toAnotherPage("GoodsManageActivity");
                break;
            case R.id.choose_from_camera:
                isCamera = true;
                File outputImage = new File(getActivity().getExternalCacheDir(),"out_put.jpg");

                try {
                    if(outputImage.exists())
                        outputImage.delete();
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(getActivity(),"seven.front.camera.provider",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,CHOOSE_FROM_CAMERA);
                dialog.dismiss();

                break;
            case R.id.choose_from_photos:
                isCamera = false;
                intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_FROM_PHOTOS);
                dialog.dismiss();
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
            case R.id.all_orders:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","0");
                break;
            case R.id.wait_pay:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","0");
                break;
            case R.id.wait_send:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","1");
                break;
            case R.id.wait_receive:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","2");
                break;
            case R.id.wait_remark:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","3");
                break;
            case R.id.wait_refund:
                UsualIntent.toAnotherPageWithData("MyOrderActivity","4");
                break;
            case R.id.goods_car:
                UsualIntent.toAnotherPage("ShoppingCartActivity");
                break;
            case R.id.browse_history:
                UsualIntent.toAnotherPage("BrowseHistoryActivity");
                break;
            case R.id.wish_list:
                UsualIntent.toAnotherPage("WishListActivity");
                break;
        }
    }

    private void init(){
        imgUserHead.setImageBitmap(LoginUser.getBitmap());
    }

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            imgUserHead.setImageBitmap(photoBitmap);
            Toast.makeText(getContext(),"上传速度有限，请稍等",Toast.LENGTH_SHORT).show();
        }
    };


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imgUserHead.setImageBitmap(cameraBitmap);
            Toast.makeText(getContext(),"上传速度有限，请稍等",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        switch (requestCode){
            case CHOOSE_FROM_CAMERA:
                if(resultCode== MainActivity.RESULT_OK){
                    try {
                        cameraBitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        cameraBitmap = rotaingImage(cameraBitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    fileupload(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) { }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException { handler.post(runnable);}});
                }
                break;
            case CHOOSE_FROM_PHOTOS:
                if (resultCode==MainActivity.RESULT_OK){
                    handLeImageOnLitKat(data);
                }
                break;
        }
    }




    private void handLeImageOnLitKat(Intent data){
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getActivity(),uri)){
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
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri,null,selection,null,null);
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
                public void onResponse(Call call, Response response) throws IOException { handler.post(runnable1); }});


        }
    }

    private Bitmap rotaingImage(Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    public void fileupload(Callback callback) {
        // 获得输入框中的路径
        String path = null;
        if(isCamera){
            path = getActivity().getExternalCacheDir().getPath()+"/out_put.jpg";
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
                //.url("http://243i4s6955.zicp.vip/MyServlets_war_exploded/imagehandler")
                .url(ServletsConn.host+"imagehandler")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

//    public void fileupload(Callback callback) {
//        // 获得输入框中的路径
//        String path = null;
//        if(isCamera){
//            path = getActivity().getExternalCacheDir().getPath()+"/out_put.jpg";
//        }else {
//            path = imagePath;
//        }
//        mediaType = MediaType.parse("image/"+path.substring(path.lastIndexOf(".")+1));
//        File file = new File(path);
//        OkHttpClient client = new OkHttpClient();
//        // 上传文件使用MultipartBody.Builder
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("goodsId", LoginUser.getLoginUser().getUserId()) // 提交普通字段
//                .addFormDataPart("sign", "1") // 提交普通字段
//                .addFormDataPart("1", file.getName(), RequestBody.create(mediaType, file)) // 提交图片，第一个参数是键（name="第一个参数"），第二个参数是文件名，第三个是一个RequestBody
//                .addFormDataPart("2", file.getName(), RequestBody.create(mediaType, file))
//                .addFormDataPart("3", file.getName(), RequestBody.create(mediaType, file))
//                .build();
//        // POST请求
//        Request request = new Request.Builder()
//                //.url("http://243i4s6955.zicp.vip/MyServlets_war_exploded/imagehandler")
//                .url(ServletsConn.host+"imagehandler")
//                .post(requestBody)
//                .build();
//        client.newCall(request).enqueue(callback);
//    }
}
