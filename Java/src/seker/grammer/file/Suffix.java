/**
 * 
 */
package seker.grammer.file;

/**
 * @author Lifeix
 *
 */
public class Suffix {
    public static void main(String[] args) {
        split("lxconnect://success?u=1575283&p=c54ddb5f60caa3495a16f70c76140f27", "?u=", "&p=");
        
        String remote = "http://static.l99.com/d3dlcm/VfZ2Ft/ZV9pbWF/nZXM=/items/200x200/tanxianjia_1.png?version=0";
        getSuffix(remote);
        
        remote = "http://static.l99.com/d3dlcm/VfZ2Ft/ZV9pbWF/nZXM=/items/200x200/tanxianjia_1.png";
        getSuffix(remote);
        
        remote = "http://avatar.l99.com/50x50/11/MjAwOTA3MDgxMDE0MTZfNTkuMTc0Ljc4LjEwNl80Mjc3ODU=..jpg";
        getSuffix(remote);
        
        remote = "http://proxy.dev.xy.l99.com/image.php?type=avatar50&ifile=30/MjAwODA5MDkwMzQzMTRfMjIwLjI0OS43OS4zNV81NDgyMDM=.jpg";
        getSuffix(remote);
        
        remote = "http://proxy.dev.xy.l99.com/image.php?type=avatar50&ifile=30/MjAwODA5MDkwMzQzMTRfMjIwLjI0OS43OS4zNV81NDgyMDM=.jpg";
        getSuffix(remote);
    }
    
    private static String getSuffix(String remote) {
//        int start = remote.lastIndexOf(".");
//        int end = remote.lastIndexOf("?");
//        end = (-1 == end || end < start)  ? remote.length() : end;
        
        int start = remote.lastIndexOf(".");
        int end = remote.lastIndexOf("?version=");
        end = (-1 == end || end < start) ? remote.length() : end;
        
        String suffix = remote.substring(start, end);
        System.out.println(remote);
        System.out.println(suffix);
        return suffix;
    }
    
    private static String[] split(final String str, final String s1, final String s2) {
        String[] result = new String[2];
        int index1 = str.indexOf(s1);
        int index2 = str.indexOf(s2);
        
        result[0] = str.substring(index1 + s1.length(), index2);
        result[1] = str.substring(index2 + s2.length());
        
        System.out.println(result[0]);
        System.out.println(result[1]);
                
        return result;
    }
}
