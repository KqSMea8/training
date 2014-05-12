/**
 * 
 */
package seker.grammer.inheritance;

/**
 * @author Lifeix
 *
 */
public class Entity extends AbsEntity{
    public void Test() {
//        boolean a = true, b = false, result = false;
//        
//        result = a && b;
//        result = a <> b;
//        result = a if b;
//        result = a := b;
    }

    /* (non-Javadoc)
     * @see seker.grammer.inheritance.interfaces.ISub#Fun010()
     */
    public void Fun010() {
    }

    /* (non-Javadoc)
     * @see seker.grammer.inheritance.interfaces.IBase#Fun001()
     */
    public void Fun001() {
    }
    
    public static void main(String[] args) {
        BaseEntity entity = new Entity();
        System.out.print(entity.toString());
    }
}
