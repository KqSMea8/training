
package seker.grammer.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;


public class ReferenceTest {
    public static void main(String[] args) {
        ReferenceQueue<String> rq=new ReferenceQueue<String>();
        String str=new String("str--StrongReference");
        SoftReference<String> sr=new SoftReference<String>("str--SoftReference");
        WeakReference<String> wr=new WeakReference<String>("str--WeakReference");
        PhantomReference<String> pr =new PhantomReference<String>("str--PhantomReference",rq);
        
        System.out.println("-----------------before gc------------------");
        System.out.println(str);
        System.out.println(sr.get());
        System.out.println(wr.get());
        System.out.println(pr.get());
        Reference<? extends String> r=null;
        while((r=rq.poll())!=null){
            System.out.println("ReferenceQueue:"+" ref:"+r.toString()+" value:"+r.get());
        }
        
        System.gc();
        System.gc();
        System.gc();
        
        System.out.println("-----------------after gc------------------");
        System.out.println(str);
        System.out.println(sr.get());
        System.out.println(wr.get());
        System.out.println(pr.get());
        while((r=rq.poll())!=null){
            System.out.println("ReferenceQueue:"+" ref:"+r.toString()+" value:"+r.get());
        }
    }
}
