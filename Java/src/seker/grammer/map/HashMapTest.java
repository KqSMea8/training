package seker.grammer.map;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    class Activity {
        String name;
        public Activity(String n) {
            name = n;
        }
        @Override
        public String toString() {
            return getClass().getSimpleName() + "[name=" + name + "]";
        }
        
    }
    
    class ActivityA extends Activity {
        public ActivityA(String n) {
            super(n);
        }
    }
    
    class ActivityB extends Activity {
        public ActivityB(String n) {
            super(n);
        }
    }
    
    private class ActivityRef extends WeakReference<Activity> {

        public ActivityRef(Activity r) {
            super(r);
        }

        public ActivityRef(Activity r, ReferenceQueue<? super Activity> q) {
            super(r, q);
        }

        @Override
        public String toString() {
            if (get() != null)
                return get().toString();
            return super.toString();
        }
    }
    
    private Map<String, ActivityRef> mActivityRefs = new HashMap<String, ActivityRef>();
    
    public void recordActivity(final Activity activity) {
        String key = activity.getClass().getName();
        ActivityRef reference = null;
        if (mActivityRefs.containsKey(key)) {
            reference = mActivityRefs.get(key);
            if (reference.get() != null) {
                System.out.println("Activity[" + key + "] has one more instance.");
            }
        }

        reference = new ActivityRef(activity);
        mActivityRefs.put(key, reference);
        dump();//dump activity 记录
    }
    
    private void dump() {
        System.out.println(mActivityRefs.toString());
    }
    
    public static void main(String[] args) {
        HashMapTest test = new HashMapTest();
        test.recordActivity(test.new Activity("1"));
        
        test.mActivityRefs.put("test_null", null);
        test.dump();
        
        test.recordActivity(test.new ActivityA("2"));
        test.recordActivity(test.new ActivityB("3"));
//        test.recordActivity(test.new ActivityB("4"));
//        test.recordActivity(test.new ActivityA("5"));
//        test.recordActivity(test.new Activity("6"));
        
        Collection<ActivityRef> values = test.mActivityRefs.values();
        for (ActivityRef value : values) {
            System.out.println(value.toString());
        }
    }
}
