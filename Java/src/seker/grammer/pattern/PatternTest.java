package seker.grammer.pattern;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lifeix
 *
 */
public class PatternTest {
    public static final String LX_BR    = "(\\$\\{lx\\-br\\})";
    
    public static final String LX_ASE   = "(\\$\\{lx\\-as\\-\\d+\\}[^\\$\\{lx\\-ae\\-\\d+\\}]*\\$\\{lx\\-ae\\-\\d+\\})";
    
    public static final String LX_IMAGE = "(\\$\\{lx\\-image\\-\\d+\\})";
    
    public static final String LX_END   = "(\\\\n)+|(\\s)+";
    
    /**
     * Good characters for Internationalized Resource Identifiers (IRI).
     * This comprises most common used Unicode characters allowed in IRI
     * as detailed in RFC 3987.
     * Specifically, those two byte Unicode characters are not included.
     */
    private static final String GOOD_IRI_CHAR =
        "a-zA-Z0-9\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF";
    
    /**
     *  Regular expression to match all IANA top-level domains for WEB_URL.
     *  List accurate as of 2010/02/05.  List taken from:
     *  http://data.iana.org/TLD/tlds-alpha-by-domain.txt
     *  This pattern is auto-generated by frameworks/base/common/tools/make-iana-tld-pattern.py
     */
    private static final String TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL =
        "(?:"
        + "(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])"
        + "|(?:biz|b[abdefghijmnorstvwyz])"
        + "|(?:cat|com|coop|c[acdfghiklmnoruvxyz])"
        + "|d[ejkmoz]"
        + "|(?:edu|e[cegrstu])"
        + "|f[ijkmor]"
        + "|(?:gov|g[abdefghilmnpqrstuwy])"
        + "|h[kmnrtu]"
        + "|(?:info|int|i[delmnoqrst])"
        + "|(?:jobs|j[emop])"
        + "|k[eghimnprwyz]"
        + "|l[abcikrstuvy]"
        + "|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])"
        + "|(?:name|net|n[acefgilopruz])"
        + "|(?:org|om)"
        + "|(?:pro|p[aefghklmnrstwy])"
        + "|qa"
        + "|r[eosuw]"
        + "|s[abcdeghijklmnortuvyz]"
        + "|(?:tel|travel|t[cdfghjklmnoprtvwz])"
        + "|u[agksyz]"
        + "|v[aceginu]"
        + "|w[fs]"
        + "|(?:xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-80akhbyknj4f|xn\\-\\-9t4b11yi5a|xn\\-\\-deba0ad|xn\\-\\-g6w251d|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-zckzah)" //SUPPRESS CHECKSTYLE
        + "|y[etu]"
        + "|z[amw]))";
    
    /**
     *  Regular expression pattern to match most part of RFC 3987
     *  Internationalized URLs, aka IRIs.  Commonly used Unicode characters are
     *  added.
     */
    private static final Pattern WEB_URL = Pattern.compile(
            "((?:(http|https|Http|Https):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
            + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
            + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
            + "((?:(?:[" + GOOD_IRI_CHAR + "][" + GOOD_IRI_CHAR + "\\-]{0,64}\\.)+"   // named host
            + TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL
            + "|(?:(?:25[0-5]|2[0-4]" // or ip address
            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]"
            + "|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]"
            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
            + "|[1-9][0-9]|[0-9])))"
            + "(?:\\:\\d{1,5})?)" // plus option port number
            + "(\\/(?:(?:[" + GOOD_IRI_CHAR + "\\;\\/\\?\\:\\@\\&\\=\\#\\~"  // plus option query params
            + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?"
            + "(?:\\b|$)"); // and finally, a word boundary or end of
                            // input.  This is to stop foo.sure from
                            // matching as foo.su
    
    /**
     * 判断一个字符串是否为合法url
     * @param query String
     * @return true: 是合法url
     */
    public static boolean isUrl(String query) {
        return WEB_URL.matcher(query).matches();
    }
    
    public static void main(String[] args) {
        
        String url = "http://m.ubox.cn/?c=pmcxxz";
        boolean isURL = isUrl(url);
        System.out.println(String.format("%s is url: %b", url, isURL));
        
        String content = "321 qwe ${lx-image-0} 123 ${lx-image-1} %^&*& ${lx-image-2} 的萨芬  ${lx-image-3}";
        
        Pattern pattern = Pattern.compile(LX_IMAGE);
//        String[] contents = pattern.split(content);
//        for (String str : contents) {
//            System.out.println(str);
//        }
        
        Matcher matcher = pattern.matcher(content);
        int start = 0;
        int end = 0;
        List<String> strs = new LinkedList<String>();
        while(matcher.find()) {
            start = end;
            end = matcher.start();
            strs.add(content.substring(start, end));
            
            start = end;
            end = matcher.end();
            strs.add(content.substring(start, end));
        }
        
        for (String str : strs) {
            System.out.println(String.format("%5b:%s", Pattern.matches(LX_IMAGE, str), str));
        }
    }
}
