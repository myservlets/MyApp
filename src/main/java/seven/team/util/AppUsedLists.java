package seven.team.util;

import seven.team.entity.*;

import java.util.ArrayList;
import java.util.List;

public class AppUsedLists {

    private static ArrayList<CommentItem>goodsRemarksList;
    private static ArrayList<Comment>userComments;
    private static ArrayList<Goods>historyBrowseList;
    private static ArrayList<Goods>shoppingCarList;
    private static ArrayList<Order>waitPayList;
    private static ArrayList<Order>waitSendList;
    private static ArrayList<Order>waitReceiveList;
    private static ArrayList<Order>waitRemarkList;
    private static ArrayList<Order>waitRefundList;
    private static ArrayList<Comment>comments;
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

    public static ArrayList<Comment> getComments() {
        comments = new ArrayList<>();
        Comment comment = new Comment();
        comments.add(comment);
        return comments;
    }

    public static void setComments(ArrayList<Comment> comments) {
        AppUsedLists.comments = comments;
    }

    public static ArrayList<Goods> getHistoryBrowseList() {
        return historyBrowseList;
    }

    public static void setHistoryBrowseList(ArrayList<Goods> historyBrowseList) {
        AppUsedLists.historyBrowseList = historyBrowseList;
    }

    public static ArrayList<Goods> getShoppingCarList() {
        return shoppingCarList;
    }

    public static void setShoppingCarList(ArrayList<Goods> shoppingCarList) {
        AppUsedLists.shoppingCarList = shoppingCarList;
    }

    public static ArrayList<Order> getWaitRefundList() {
        return waitRefundList;
    }

    public static void setWaitRefundList(ArrayList<Order> waitRefundList) {
        AppUsedLists.waitRefundList = waitRefundList;
    }

    public static ArrayList<Order> getWaitRemarkList() {
        return waitRemarkList;
    }

    public static void setWaitRemarkList(ArrayList<Order> waitRemarkList) {
        AppUsedLists.waitRemarkList = waitRemarkList;
    }

    public static ArrayList<Order> getWaitReceiveList() {
        return waitReceiveList;
    }

    public static void setWaitReceiveList(ArrayList<Order> waitReceiveList) {
        AppUsedLists.waitReceiveList = waitReceiveList;
    }

    public static ArrayList<Order> getWaitSendList() {
        return waitSendList;
    }

    public static void setWaitSendList(ArrayList<Order> waitSendList) {
        AppUsedLists.waitSendList = waitSendList;
    }

    public static ArrayList<Order> getWaitPayList() {
        return waitPayList;
    }

    public static void setWaitPayList(ArrayList<Order> waitPayList) {
        AppUsedLists.waitPayList = waitPayList;
    }

    public static ArrayList<CommentItem> getGoodsRemarksList() {
        return goodsRemarksList;
    }

    public static void setGoodsRemarksList(ArrayList<CommentItem> goodsRemarksList) {
        AppUsedLists.goodsRemarksList = goodsRemarksList;
    }

    public static ArrayList<Comment> getUserComments() {
        return userComments;
    }

    public static void setUserComments(ArrayList<Comment> userComments) {
        AppUsedLists.userComments = userComments;
    }
}
