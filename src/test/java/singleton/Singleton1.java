package singleton;

public class Singleton1 {

    //饿汉式-1
    /*
    private Singleton(){}

    private static Singleton singleton = new Singleton();
    public static Singleton getSingleton(){
        return singleton;
    }
    */
    // 懒汉式

/* private static volatile Singleton singleton;
    private Singleton(){}

    public static  Singleton  getInstance(){
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }*/

    //静态内部类
  private Singleton1(){
      System.out.println("调用构造函数初始化对象");
  }

  private static class SingletonInstance {

      private static final Singleton1 SINGLETON = new Singleton1();

      public static void test(){
          System.out.println("static inner class test");
      }
  }

  public static Singleton1 getInstance(){
      return SingletonInstance.SINGLETON;
  }

  public static void test(){
      System.out.println("test");
  }

    public static void main(String[] args) {
        Singleton1.SingletonInstance.test();
    }

}
