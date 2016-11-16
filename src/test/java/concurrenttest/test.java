package concurrenttest;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by 肖明明 on 2016/11/9.
 */
public class test {


    public static void main(String[] args) throws InterruptedException, ParseException {
        long time=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").parse("2039-08-30 23:59:07").getTime();

//        long h3 = System.currentTimeMillis() - 1403854494756L << 22L | 1 << 12L | 467;
//        System.out.println(h3);
        long h4 = 0 << 22L | 3 << 12L | 0;
        System.out.println(h4);


    }
}
