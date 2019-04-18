package seven.team.util;

import seven.team.entity.GoodsDetails;
import seven.team.entity.ReceiveInfo;

public class AppUsedTemp {
    private static ReceiveInfo receiveInfo;
    private static GoodsDetails goodsDetails;

    public static ReceiveInfo getReceiveInfo() {
        return receiveInfo;
    }

    public static void setReceiveInfo(ReceiveInfo receiveInfo) {
        AppUsedTemp.receiveInfo = receiveInfo;
    }

    public static GoodsDetails getGoodsDetails() {
        return goodsDetails;
    }

    public static void setGoodsDetails(GoodsDetails goodsDetails) {
        AppUsedTemp.goodsDetails = goodsDetails;
    }
}
