package seven.team.util;

import seven.team.entity.ReceiveInfo;

public class AppUsedTemp {
    private static ReceiveInfo receiveInfo;

    public static ReceiveInfo getReceiveInfo() {
        return receiveInfo;
    }

    public static void setReceiveInfo(ReceiveInfo receiveInfo) {
        AppUsedTemp.receiveInfo = receiveInfo;
    }
}
