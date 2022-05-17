package pers.zhangyang.itemtrade.util;


import pers.zhangyang.itemtrade.meta.GoodMeta;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    //返回第x页的内容,
    public static List<GoodMeta> pageGoodMeta(int pageIndex, int capacity, List<GoodMeta> goodMetaList){
        List<GoodMeta> rl=new ArrayList<>();
        for (int i=pageIndex*capacity;i<pageIndex*capacity+capacity;i++){
            if (goodMetaList.size()<=i){break;}
            rl.add(goodMetaList.get(i));
        }

        return rl;
    }
    //由所有物品数量和每页能容纳的物品数量，计算出最后一页的下标 最少为0
    public static int computeMaxPageIndex(int allItemSlotAmount,int capacity){
        int maxPage=0;
        if (allItemSlotAmount!=0)
            maxPage=(allItemSlotAmount%capacity==0?allItemSlotAmount/capacity-1:allItemSlotAmount/capacity);
        return maxPage;
    }
}
