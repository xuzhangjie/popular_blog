package cn.edu.guet.popular_blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pangjian
 * @ClassName TimeUtil
 * @Description 时间工具类
 * @date 2021/7/7 13:23
 */

public class TimeUtil {

    /**
     * @Description:获取当前时间的字符串表示
     * @return java.lang.String
     * @date 2021/7/7 15:55
    */
    public static String getCurrentTime(){

        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(date);//获取String类型的时间
        return  str;
    }


}
