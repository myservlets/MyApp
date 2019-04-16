package seven.team.adapter;


import seven.team.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;


public class TypeAdapter extends BaseAdapter {
    private Context context;
    private List<String> typeList;
    private List<Integer> imageList;
    LayoutInflater layoutInflater;
    private ImageView typeImage;
    private TextView typeName;

    public TypeAdapter(Context context, List<String> typeList, List<Integer> imageList) {
       this.context = context;
       this.typeList = typeList;
        this.imageList = imageList;
       layoutInflater = LayoutInflater.from(context);
   }

    @Override
    public int getCount() {
        return typeList.size()+1;
    }

    @Override
    public  Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_type, null);
        typeName = convertView.findViewById(R.id.type_name);
        typeImage=convertView.findViewById(R.id.type_image);
        if (position < typeList.size()) {
            String type = typeList.get(position);
            Integer src = imageList.get(position);
            typeName.setText(type);
            typeImage.setImageResource(src);
        }else{
            typeName.setText("更多");//最后一个显示加号图片
            typeImage.setImageResource(R.mipmap.more_type);
        }
        return convertView;
    }
}

