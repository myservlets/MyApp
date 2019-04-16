package seven.team.util;

import seven.team.adapter.GoodsAdapter;

public class AppUsedAdapter {
    private static GoodsAdapter goodsAdapter;

    public static GoodsAdapter getGoodsAdapter() {
        return goodsAdapter;
    }

    public static void setGoodsAdapter(GoodsAdapter goodsAdapter) {
        AppUsedAdapter.goodsAdapter = goodsAdapter;
    }
}
