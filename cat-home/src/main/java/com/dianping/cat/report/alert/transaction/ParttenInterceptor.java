package com.dianping.cat.report.alert.transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Usage:
 * <p>
 * Description:
 * User: fuxinpeng
 * Date: 2018-11-28
 * Time: 11:44 AM
 */
public class ParttenInterceptor {
    public static final String PARTTEN_LIKE = "*";
    public static final String PARTTEN_MULTI = ",";
    public static final String PARTTEN_ALL = ":xyf_partten_all:";
    public static final String PARTTEN_EITHER = ":xyf_partten_either:";
    public static Set<String> matchNames(Set<String> reportNames,String parttenedName){
        String[] split = null;
        if(parttenedName.contains(PARTTEN_ALL)){
            split = parttenedName.split(PARTTEN_ALL);
        }else{
            split = parttenedName.split(PARTTEN_EITHER);
        }
        String nameMulti = split[split.length - 1];
        String[] names = nameMulti.split(PARTTEN_MULTI);
        Set<String> namesLike = new HashSet<>();
        Set<String> namesEntirely = new HashSet<>();
        for (String name :
                names) {
            if(name.contains(PARTTEN_LIKE)){
                namesLike.add(name);
            }else{
                namesEntirely.add(name);
            }
        }

        Set<String> matchNames = new HashSet<>();
        for (String reportName :
                reportNames) {
            if(namesEntirely.contains(reportName)){
                matchNames.add(reportName);
                continue;
            }
            if(matchLike(namesLike,reportName)){
                matchNames.add(reportName);
            }
        }
        return matchNames;
    }

    private static boolean matchLike(Set<String> namesLike, String reportName) {
        for (String nameLike :
                namesLike) {
            nameLike = nameLike.trim();
            int i = nameLike.indexOf(PARTTEN_LIKE);
            if(i == nameLike.length()-1
                    && reportName.startsWith(nameLike.substring(0,nameLike.length()-1))){
                return true;
            }else if(i == 0
                    && reportName.endsWith(nameLike.substring(1))){
                return true;
            }
        }
        return false;
    }
    public static String getAlertName(String parttenedName,String defaultName){
        String[] split = null;
        if(parttenedName.contains(PARTTEN_ALL)){
            split = parttenedName.split(PARTTEN_ALL);
        }else{
            split = parttenedName.split(PARTTEN_EITHER);
        }
        if(split.length < 2){
            return defaultName;
        }
        return split[0];
    }
    public static boolean isParttenedName(String name){
        return name != null
                && (name.contains(PARTTEN_ALL) || name.contains(PARTTEN_EITHER));
    }

    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        String a = "sadb*";
        String b = "*asdf";
        String c = "*aaasdf";
        s.add(a);
        s.add(b);
        s.add(c);
        System.out.println(matchLike(s,"saddbsdfjasdf"));
        String name = a.substring(0, a.length()-1);
        System.out.println(a.length());
        System.out.println(a.indexOf("*"));
        System.out.println(name);
        System.out.println("*sadb".substring(1));

        Set<String> r = new HashSet<>();
        r.add("myname1");
        r.add("1myname");
        r.add("myname2");
        r.add("myname3");
        r.add("2myname");
        String p = "pulada:xyf_partten_either:myname1,*myname";
        System.out.println("**");
        System.out.println(getAlertName(p,"abchaah"));
        System.out.println("**");
        System.out.println(matchNames(r,p));

        System.out.println("*^*");
        System.out.println((":xyf_partten_all:;sdjfsdjf").contains(PARTTEN_ALL));
        System.out.println(PARTTEN_ALL.length());
    }
}
