package seker.grammer.classes;

import java.util.ArrayList;
import java.util.List;

interface Fruit {  
    
}  

class Apple implements Fruit {  
    
}  

/**
 * @author Lifeix
 *
 */
public class ClassName {
    public static void main(String[] args) {  
        Fruit apple=new Apple();  
        System.out.println(apple.getClass().getCanonicalName());//返回com.test.Apple  
        System.out.println(apple.getClass().getSimpleName());//Apple  
        System.out.println(apple.getClass().getName());//返回com.test.Apple  
          
        Apple[] arrApple=new Apple[]{};  
        System.out.println(arrApple.getClass().getCanonicalName());//返回com.test.Apple[]  
        System.out.println(arrApple.getClass().getSimpleName());//返回Apple[]  
        System.out.println(arrApple.getClass().getName());//返回[Lcom.test.Apple;  
          
        System.out.println(String.class.getCanonicalName());//返回java.lang.String  
        System.out.println(String.class.getSimpleName());//返回String  
        System.out.println(String.class.getName());//返回java.lang.String  
          
        System.out.println(int.class.getCanonicalName());//返回int  
        System.out.println(int.class.getSimpleName());//返回int  
        System.out.println(int.class.getName());//返回int  
          
        Apple a1=new Apple();  
        Apple a2=new Apple();  
        List<Apple> appleList=new ArrayList<Apple>();  
        appleList.add(a1);  
        appleList.add(a2);  
        System.out.println(appleList.getClass().getCanonicalName());//返回java.util.ArrayList  
        System.out.println(appleList.getClass().getSimpleName());//返回ArrayList  
        System.out.println(appleList.getClass().getName());//返回java.util.ArrayList  
    }  
}
