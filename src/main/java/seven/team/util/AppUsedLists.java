package seven.team.util;

import seven.team.entity.Goods;
import seven.team.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AppUsedLists {
    private static ArrayList<User> myfriendlist;
    private static ArrayList<User> applyfriendlist;
    private static ArrayList<User> beapplyfriendlist;
    private static ArrayList<Goods> businessGoodsList;

    public static ArrayList<User> getMyfriendlist() {
        return myfriendlist;
    }

    public static void setMyfriendlist(ArrayList<User> myfriendlist) {
        AppUsedLists.myfriendlist = myfriendlist;
    }

    public static ArrayList<User> getApplyfriendlist() {
        return applyfriendlist;
    }

    public static void setApplyfriendlist(ArrayList<User> applyfriendlist) {
        AppUsedLists.applyfriendlist = applyfriendlist;
    }

    public static ArrayList<User> getBeapplyfriendlist() {
        return beapplyfriendlist;
    }

    public static void setBeapplyfriendlist(ArrayList<User> beapplyfriendlist) {
        AppUsedLists.beapplyfriendlist = beapplyfriendlist;
    }

    public static ArrayList<Goods> getBusinessGoodsList() {
        return businessGoodsList;
    }

    public static void setBusinessGoodsList(ArrayList<Goods> businessGoodsList) {
        AppUsedLists.businessGoodsList = businessGoodsList;
    }
}
