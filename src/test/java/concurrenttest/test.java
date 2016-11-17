package concurrenttest;


import xmm.self.snowflake.IDGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by 肖明明 on 2016/11/9.
 */
public class test {

    static SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
    static BlockingQueue<Long> queue=new LinkedBlockingQueue();
    
    
    

    public static void main(String[] args) throws InterruptedException, ParseException {
//        long time=sdf.parse("2039-08-30 23:59:07").getTime();
        
        
        
        ExecutorService es= Executors.newFixedThreadPool(1000);
        long n=System.currentTimeMillis();
        for(int i=0;i<2000;i++){
            es.execute(new Runnable() {
                public void run() {
                    try {
                        for(int j=0;j<1000;j++){
                            long l= IDGenerator.getFlowIdWorkerInstance().nextId();
                            queue.put(l);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
        }
        long L=System.currentTimeMillis();
        System.out.println("worker thread prepared ok."+(L-n));
        es.shutdown();

        boolean b=es.awaitTermination(2, TimeUnit.SECONDS);
        while (!b) {
            b=es.awaitTermination(2, TimeUnit.SECONDS);
        }

        System.out.println(queue.size());

        System.out.println("over"+(System.currentTimeMillis()-L));

        Set<Long> set = new HashSet();
        Long h=queue.poll(50,TimeUnit.MILLISECONDS);
        while (h!=null){
            set.add(h);
            h=queue.poll(50,TimeUnit.MILLISECONDS);
        }
        System.out.println(set.size());

    }
}
