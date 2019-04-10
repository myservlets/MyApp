package seven.team.entity;

import android.graphics.Bitmap;
import seven.handler.WebSocketHandler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/3/25 0025.
 */
public class LoginUser {

    private static User loginUser;

    private static WebSocketHandler webSocketHandler;

    private static ArrayList<User> friendlist;

    private static Bitmap bitmap;

    private LoginUser(User loginUser){ LoginUser.loginUser = loginUser; }

    public static void setLoginUser(User loginUser) { new LoginUser(loginUser); }

    public static User getLoginUser() { return loginUser; }

    public static WebSocketHandler getWebSocketHandler() {
        return webSocketHandler;
    }

    public static void setWebSocketHandler(WebSocketHandler webSocketHandler) {
        LoginUser.webSocketHandler = webSocketHandler;
    }

    public static ArrayList<User> getFriendlist() {
        return friendlist;
    }

    public static void setFriendlist(ArrayList<User> friendlist) {
        LoginUser.friendlist = friendlist;
    }

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        LoginUser.bitmap = bitmap;
    }
}
