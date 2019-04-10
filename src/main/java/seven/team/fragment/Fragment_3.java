package seven.team.fragment;


import seven.team.thread.GetFriendList;
import seven.team.util.AppUsedLists;
import seven.team.util.UsualIntent;
import seven.team.entity.LoginUser;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import seven.team.adapter.UserAdapter;
import seven.team.entity.User;
import seven.team.activity.R;
import seven.handler.WebSocketHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_3 extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private List<User>userList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Button search;

    private UserAdapter adapter;

    public Fragment_3() {
        userList = new ArrayList<>();
        //initUsers();
        //initWebSocketHandler();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3,container,false);
        search = view.findViewById(R.id.search_friends);
        search.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new UserAdapter(AppUsedLists.getMyfriendlist());

        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_friends);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    private void initWebSocketHandler() {
        LoginUser.setWebSocketHandler(new WebSocketHandler());
        LoginUser.getWebSocketHandler().connSever(LoginUser.getLoginUser().getUserId());
        LoginUser.getWebSocketHandler().setFragment3(this);
    }

    private void initUsers(){
        new GetFriendList().execute(2, LoginUser.getLoginUser());
    }
    private void ininPopWindow(View v){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_chater,null);
        TextView addFriends = view.findViewById(R.id.add_friends);
        TextView addRequests = view.findViewById(R.id.add_request);
        addFriends.setOnClickListener(this);
        addRequests.setOnClickListener(this);
        PopupWindow popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener(){@Override public boolean onTouch(View v, MotionEvent event) {return false;}});
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white_color)));
        popupWindow.showAsDropDown(v, 0, 0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_friends:
                ininPopWindow(v);
                break;
            case R.id.add_friends:
                UsualIntent.toAnotherPage("SearchFriendsActivity");
                break;
            case R.id.add_request:
                UsualIntent.toAnotherPage("FriendRequestActivity");
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                new GetFriendList().execute(2, LoginUser.getLoginUser());
//                adapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(true);
            }
        }).start();
    }
}
