package com.zereao;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  18:02
 */
public class TestUtil {
    @Test
    public void test01() throws ParseException {
//        Tue May 15 00:00:00 CST 2018
        String str = "Thu May 10 00:00:00 CST 2018";
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
        Date date = df.parse(str);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("转换后的值：" + sdf.format(date));
    }
}
