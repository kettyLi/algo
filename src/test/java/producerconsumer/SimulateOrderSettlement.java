package producerconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class SimulateOrderSettlement {

    class Consumer implements Runnable {
        private String name;
        private Storage storage;

        public Consumer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
               for (int i = 0; i < 10; i++){
                System.out.println("消费者:"+name+"开始消费");
                List<String> pop = storage.pop();
                System.out.println("消费者:"+name+ "已消费的消息是："+ pop.toString());
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Producter implements Runnable {
        private List<String> msg;
        private Storage storage;

        public Producter(List<String> msg, Storage storage) {
            this.msg = msg;
            this.storage = storage;
        }


        @Override
        public void run() {

                try {
                    System.out.println("准备生产消息："+ msg.toString());
                    storage.push(msg);
                    System.out.println("已生产消息："+ msg.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        public void productMsg(int n){

        }
    }

    class Storage {
        BlockingQueue<List<String>> queue = new LinkedBlockingDeque<>(10);

        public void push(List<String> msg) throws InterruptedException {
            queue.put(msg);
        }

        public List<String> pop() throws InterruptedException {
            return queue.take();
        }
    }

    public static void main(String[] args) {
        SimulateOrderSettlement settlement = new SimulateOrderSettlement();
        Storage storage = settlement.new Storage();
        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 模拟每次查询10条数据，共有48条数据
         */
        //生成生产者的个数--分页查询订单的页数
        for (int i = 0; i < 5; i++) {
            List<String> msg = new ArrayList<>();
            //每页的条数
            for (int j = 0; j < 10; j++) {
                msg.add((int)(Math.random() * 100) + "");
            }

            Producter producter = settlement.new Producter(msg, storage);
            executorService.submit(producter);
        }

        Consumer consumer1 = settlement.new Consumer("consumer-1", storage);
        Consumer consumer2 = settlement.new Consumer("consumer-2", storage);

        executorService.submit(consumer1);
        executorService.submit(consumer2);

    }

}
