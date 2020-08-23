package producerconsumer;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ProducerConsumer {

    class Consumer implements Runnable {
        private String name;
        private Storage storage = null;

        public Consumer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++)  {
                    System.out.println(name + "准备消费产品.");
                    Product product = storage.pop();
                    System.out.println(name + "已消费产品:"+product.toString());
                    System.out.println("------------------------------");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    class Producer implements Runnable {
        private String name;
        private Storage storage = null;

        public Producer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
                    System.out.println(name + "准备生产(" + product.toString() + ").");
                    storage.push(product);
                    System.out.println(name + "已生产(" + product.toString() + ").");
                    System.out.println("---------------------------------");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    public class Product {
        private int id;

        public Product(int id) {
            this.id = id;
        }

        public String toString() {
            return "product id:" + this.id;
        }
    }

    public class Storage {
        BlockingQueue<Product> queue = new LinkedBlockingDeque<>(10);

        public void push(Product product) throws InterruptedException {
            queue.put(product);
        }

        public Product pop() throws InterruptedException {
            return queue.take();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Storage storage = pc.new Storage();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Producer producer1 = pc.new Producer("producer-hery", storage);
        //Producer producer2 = pc.new Producer("producer-ketty", storage);

        Consumer consumer1 = pc.new Consumer("consumer-li1", storage);
        Consumer consumer2 = pc.new Consumer("consumer-li2", storage);
        Consumer consumer3 = pc.new Consumer("consumer-li3", storage);

        executorService.submit(producer1);
      //  executorService.submit(producer2);

        executorService.submit(consumer1);
        executorService.submit(consumer2);
        executorService.submit(consumer3);


        new ArrayList<>();
    }
}
