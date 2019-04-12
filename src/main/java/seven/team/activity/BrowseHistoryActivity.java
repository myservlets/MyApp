package seven.team.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import seven.team.adapter.HistoryBrowseAdapter;
import seven.team.entity.Goods;
import seven.team.util.UsualIntent;

import java.util.ArrayList;
import java.util.List;

public class BrowseHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Goods>goodsList;
    private ImageView returnFormer;
    private TextView title;
    private RecyclerView recyclerView;
    private HistoryBrowseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_history);

        bindData();
    }

    private void bindData(){
        if (goodsList==null){
            goodsList = new ArrayList<>();
        }
        returnFormer = findViewById(R.id.return_former);
        title = findViewById(R.id.title);
        recyclerView = findViewById(R.id.history_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new HistoryBrowseAdapter(goodsList);
        recyclerView.setAdapter(adapter);
        title.setText("浏览历史");
        returnFormer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_former:
                UsualIntent.toAnotherPage("MainActivity");
        }
    }
}
