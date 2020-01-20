package cn.edu.xjtu.cad.hehe.cai_kg.spider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * 搜索链接处理
     *
     * 保留搜索关键词q
     * 保留搜索页码p
     * 保留每页显示的数目n
     *
     * @param urlString 搜索链接，要求必须是绝对地址
     * @return 返回修剪后的链接
     */
    public static String clipSearchURL(String urlString) {
        String[] arr = urlString.split("[?]");
        String res = "";
        if (arr.length == 2) {
            String newP = Arrays.stream(arr[1].split("&"))
                    .filter(p -> p.contains("q=") || p.contains("p="))
                    .collect(Collectors.joining("&"));
            //需要设置每页的个数为100
            res = arr[0] + "?" + newP + "&n=100";
            LOGGER.debug("编辑后的URL+" + res);
        }
        return res;
    }

    public static List<String> getURLParam(String urlString, String key){
        String[] arr = urlString.split("[?]");
        List<String> res = new ArrayList<>();
        if (arr.length == 2) {
            Arrays.stream(arr[1].split("&"))
                    .filter(p -> p.contains(key+"="))
                    .forEach(p->res.add(p.split("=")[1]));
            //需要设置每页的个数为100
        }
        return res;
    }

    public static String halfWidth2FullWidth(String input) {
        if(input==null) return "";
        char c[] = input.toCharArray();
        for ( int i=0; i<c.length;i++ ) {
            if (c[i] ==' ') {
                c[i] = '\u3000';
            }
            else if (c[i]<'\177') {
                c[i]= (char) (c[i]+65248);
            }
        }
        return new String(c);
    }

}
