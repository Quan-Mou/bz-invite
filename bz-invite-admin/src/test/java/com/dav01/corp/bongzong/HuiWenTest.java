package com.dav01.corp.bongzong;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: 权某人
 * @create: 2024-04-11 14:45
 * @Description:
 */



public class HuiWenTest {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("raceacar"));
        System.out.println(isPalindrome("0p"));

    }

    //
    public static boolean isPalindrome(String s) {

        if(Objects.equals(s, " ")) {
            return true;
        }



//        1. 转换为小写
        String str = s.toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();

        char[] chars1 = str.toCharArray();
        for(char item : chars1) {
            int numScore = (int) item;
            if((numScore >= 65 && numScore <= 90) || ( numScore>=48 && numScore <=57)) {
                stringBuilder.append(item);
            }

//            if((numScore >= 48 && numScore <= 90) && ( numScore>=48 && numScore <=57)) {
//                stringBuilder.append(item);
//            }
        }


//        65 - 90（大写字母）
//        97 - 122（小写字母)

//        Collectors.to
//        2. 移除非字母字符r

        System.out.println("排除后的字母：" + stringBuilder);

//        使用快慢指针遍历
        int length = stringBuilder.length();
        int left = 0;
        int right = length - 1;
        while(left < right) {
            if (stringBuilder.charAt(left) != stringBuilder.charAt(right)) {
                return false;
            }
                left++;
                right--;
        }
        System.out.println(stringBuilder + "： 是一个回文数");
        return true;
    }


    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


}
