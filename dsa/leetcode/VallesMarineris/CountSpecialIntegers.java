package dsa.leetcode.VallesMarineris;

public class CountSpecialIntegers {

    /**
     * https://leetcode.com/problems/count-special-integers/description/
     *
     * Use digit dynamic programming to count the special numbers. A bitmask is utilized
     * to track used digits, ensuring all digits are distinct in the number being constructed.
     *
     * TC: O(d^2), where d is the number of digits in n. SC: O(d * 2^d)
     * #math #dynamic-programming #hard
     */
    
    public int countSpecialNumbers(int n) {
        // Implement the code logic for counting special numbers within given range
        int res = 0;
        // Logic to calculate special numbers
        int curr = pair[0], next = fact[pair[1] - 1] / fact[10 - digits.length];
        res += curr * next;
        if (pair[2] == 0) return res;

        if (pair[2] == 1) {
            pair = countAndMark(mark, digits[digits.length - 1] - '0');
            res += pair[0] + pair[2];
        }

        return (int)res;
    
    // Additional utility methods and logic to process data
    for(int i = 1; i < (digits.length - 1); i++){
        pair = countAndMark(mark, digits[i] - '0');
        // Further logic
    }
}