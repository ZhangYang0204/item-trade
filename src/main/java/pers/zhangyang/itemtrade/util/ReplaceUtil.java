package pers.zhangyang.itemtrade.util;


import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReplaceUtil {

    //吧list中pattern的换成replaceTo
    public static void format( List<String> list, String pattern, List<String> replaceTo){

        for (int i=0;i<list.size();i++){
            String s=list.get(i);
            if (s.contains(pattern)){
                list.remove(i);
                for (int j=replaceTo.size()-1;j>=0;j--) {
                    list.add(i, ReplaceUtil.replace(s, Collections.singletonMap(pattern,replaceTo.get(j))));
                }

            }

        }
    }
    public static String replace( String s,  Map<String,String> rep){

        if (s!=null) {
            for (String key : rep.keySet()) {
                s = s.replace(key, rep.get(key));
            }
        }
        return s;
    }

    public static void replace( List<String> s,  Map<String,String> rep){

        if (s!=null){
        for (String key:rep.keySet()){
            for (int i=0;i<s.size();i++){
                s.set(i,s.get(i).replace(key,rep.get(key)));
            }
        }
        }
    }


}

