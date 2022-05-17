package pers.zhangyang.itemtrade.util;

import java.io.*;
import java.net.URL;

public class ResourceUtil {
    public static boolean deleteFile(File file){
        File[] files = file.listFiles();
        for(File deleteFile : files){
            if(deleteFile.isDirectory()){
                //判断如果是文件夹，则递归删除下面的文件后再删除该文件夹
                if(!deleteFile(deleteFile)){
                    //如果失败则返回
                    return false;
                }
            } else {
                //文件直接删除
                if(!deleteFile.delete()){
                    //如果失败则返回
                    return false;
                }
            }
        }
        file.delete();
        return true;
    }
    public static  String getFirstLine(URL url) throws IOException {
        if (url==null){
            throw new NullPointerException();
        }


        InputStream is= url.openStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String r= br.readLine();
        return  r;
    }
}
