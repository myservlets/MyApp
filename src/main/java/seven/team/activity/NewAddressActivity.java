package seven.team.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.Manifest;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import seven.team.util.BaseActivity;
import seven.team.sqlite.Province;
import seven.team.entity.ReceiveInfo;

public class NewAddressActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener, AdapterView.OnItemSelectedListener {

    private List<String>provincelist = new ArrayList<>();
    private List<String>countrylist = new ArrayList<>();
    private List<String>citylist = new ArrayList<>();
    private static final int OPEN_CONTACT = 1;
    private String province;
    private String country;
    private String city;
    private ImageView txtBackLastPage;
    private TextView txtSave;
    private TextView txtName;
    private ImageView imgReactor;
    private TextView txtPhone;
    private Spinner spanProvince;
    private Spinner spanCountry;
    private Spinner spanCity;
    private List<Province>provinces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        initPlace();
        bindData();
    }
    private void initPlace(){
        // TODO: 2019/3/23 0023 从服务器获得省市县的json对象
        //存入本地
    }

    private void bindData(){
        txtBackLastPage = findViewById(R.id.back_receive_address_page);
        txtBackLastPage.setOnClickListener(this);
        txtSave = findViewById(R.id.save_as_new_address);
        txtSave.setOnClickListener(this);
        txtName = findViewById(R.id.add_receive_name);
        imgReactor = findViewById(R.id.from_reactor_add_info);
        imgReactor.setOnClickListener(this);
        txtPhone = findViewById(R.id.add_receive_phone);
        spanProvince = findViewById(R.id.province);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.item_address,provincelist);
        spanProvince.setAdapter(adapter1);
        spanCountry = findViewById(R.id.country);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.item_address,countrylist);
        spanProvince.setAdapter(adapter2);
        spanCity = findViewById(R.id.city);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,R.layout.item_address,citylist);
        spanProvince.setAdapter(adapter3);
        spanProvince.setOnItemSelectedListener(this);
        spanCountry.setOnItemSelectedListener(this);
        spanCity.setOnItemSelectedListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.back_receive_address_page:
                showDialog();
                break;
            case R.id.save_as_new_address:
                ReceiveInfo address = new ReceiveInfo();
                address.setName(txtName.getText().toString());
                address.setPhone(txtPhone.getText().toString());
                updateInfo(address);
                break;
            case R.id.from_reactor_add_info:
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
                }else {
                    readContacts();
                }
                break;
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("信息未保存，是否保存");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ReceiveInfo receiveInfo = new ReceiveInfo();
                receiveInfo.setName(txtName.getText().toString());
                receiveInfo.setPhone(txtPhone.getText().toString());
                updateInfo(receiveInfo);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ReceiveInfo receiveInfo = new ReceiveInfo();
                updateInfo(receiveInfo);
            }
        });
        builder.create();
        builder.show();
    }

    private void updateInfo(ReceiveInfo receiveInfo){

        if(receiveInfo!=null){
            Intent intent = new Intent();
            intent.putExtra("address",receiveInfo);
            setResult(RESULT_OK,intent);
        }
        this.finish();
    }

    private void readContacts() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,OPEN_CONTACT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case OPEN_CONTACT:
                if(resultCode == Activity.RESULT_OK){
                    ContentResolver resolver = getContentResolver();
                    Uri contactUri = data.getData();
                    Cursor cursor = managedQuery(contactUri,new String[]{},null,null,null);
                    cursor.moveToFirst();
                    String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,null,null);
                    assert phone != null;
                    String contactNumber = null;
                    while (phone.moveToNext()){
                        contactNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    txtName.setText(contactName);
                    txtPhone.setText(contactNumber);
                    cursor.close();
                    phone.close();
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.province:
                province = parent.getItemAtPosition(position).toString();
                break;
            case  R.id.country:
                country = parent.getItemAtPosition(position).toString();
                break;
            case R.id.city:
                city = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
