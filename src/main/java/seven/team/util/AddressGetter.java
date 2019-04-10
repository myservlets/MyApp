package seven.team.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seven.team.sqlite.Province;

/**
 * Created by Administrator on 2019/3/29 0029.
 */
public class AddressGetter{
    private List<Province>provinces;

    public AddressGetter(){
        provinces = new ArrayList<>();
    }

    public void getAddress(Context context){
        StringBuilder stringBuilder =new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("china.json");
            InputStreamReader isr =new InputStreamReader(inputStream);
            BufferedReader reader =new BufferedReader(isr);
            String jsonLine;
            while((jsonLine = reader.readLine()) !=null) {
                stringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
            String result =  stringBuilder .toString();
            Gson gson = new Gson();
            provinces = gson.fromJson(result,new TypeToken<List<Province>>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public List<Province> getProvinces() {
        return provinces;
    }
}
