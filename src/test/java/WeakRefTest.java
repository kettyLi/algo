import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class WeakRefTest {

    public static void test(){
        // weakHashMap key is weak  An entry in a <tt>WeakHashMap</tt> will automatically be removed when
        // its key is no longer in ordinary use.
        WeakHashMap<Integer, List<WeakReference<String>>> wkMap = new WeakHashMap<>();

        // list element is WeakReference, an element in List will automatically be removed when it no longer in ordinary use
        List<WeakReference<String>> wkrList = new ArrayList<>();
        String a = new String("a");
        String b = new String("b");
        String c = new String("c");
        WeakReference<String> awkrStr = new WeakReference<String>(a);
        WeakReference<String> bwkrStr = new WeakReference<String>(b);
        WeakReference<String> cwkrStr = new WeakReference<String>(c);
        wkrList.add(awkrStr);
        wkrList.add(bwkrStr);
        wkrList.add(cwkrStr);
        wkMap.put(1,wkrList);

        a = null;
        b = null;
        c = null;
        System.out.println("--------------------- before GC");
        List<WeakReference<String>> weakReferences = wkMap.get(1);
        for (WeakReference<String> wk : weakReferences){
            String s = wk.get();
            System.out.println("s="+s);
        }

        System.out.println("--------------------- begin GC");
       System.gc();
       System.out.println("map size="+ weakReferences.size());
        for (WeakReference<String> wk : weakReferences){
            String s = wk.get();
            System.out.println("s="+s);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
