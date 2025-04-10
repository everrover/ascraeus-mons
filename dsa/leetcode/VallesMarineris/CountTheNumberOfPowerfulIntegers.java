package dsa.leetcode.VallesMarineris;

/**
 * https://leetcode.com/problems/count-the-number-of-powerful-integers/description/?envType=daily-question&envId=2025-04-10
 *
 * Count integers with digits at most the limit and suffix specific to the integer. 
 * Using it, we can deduct the number of integers that are less than the given integer, calc(start-1),
 * from the number of integers that are less than or equal to the ending integer, calc(finish).
 * 
 * Now for a giv
 * 
 * I applied DP as well, but found that combinatoric dedcutions were being done internally through 
 * it's state transitions. So I used a combinatoric approach to find the number of integers...
 * 
 * 1. So, for a given MSD, we can find the reqd count using `(digit-1[to not include MSD]+1[to accomodate 0])*possible-states-in-prefix`.
 * Here digit is restricted to the limit, and the prefix is the number of digits in the suffix, i.e. res += max(limit,MSD)*pow(limit, prefixlen-1)
 * 
 * 2. Now, for MSD, we can iterate through the digits in prefix and find the combinations from MSD[0] to MSD[1], i.e. res += pow(limit, prefixlen-1-i[=0])
 * Same for MSD[1] to MSD[2], i.e. res += pow(limit, prefixlen-1-i[=1]) and so on...
 * 
 * 3. If limit < digit, then we stop here and return the count. Since we can't use any more numbers after `limit`
 * 
 * 4. If suffix-in-num > suffix, then we can add 1 to the count, since we can use the suffix as well.
 * 
 * I merged steps 1 & 2, and used conditional logic for 3 and 4. This is exactly how we stepping through DP transitions.
 * 
 * TC: O(n * m) SC: O(n * m), n = number of digits, m = possible states
 * #dynamic-programming #math #string #hard
 */

class CountTheNumberOfPowerfulIntegers {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return calc(Long.toString(finish), s, limit)-calc(Long.toString(start-1), s, limit);
    }
    
    private long calc(String num, String suf, int limit){
        if(num.length() < suf.length()) return 0L;
        else if(num.length() == suf.length()) return num.compareTo(suf)>=0?1L:0L;
        int preLen = num.length()-suf.length();
        String sufInNum = num.substring(preLen);
        long count = 0;
        for(int i=0; i<preLen; i++) {
            int digit = num.charAt(i)-'0';
            if(limit<digit) {
                count += (long) Math.pow(limit+1, preLen-i);
                return count;
            }
            count += (long)digit * (long)Math.pow(limit+1, preLen-1-i);
        }
        int r = sufInNum.compareTo(suf);
        if(r>=0) count++;
        return count;
    }
}