package dsa.leetcode.VallesMarineris;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/product-of-the-last-k-numbers/description/?envType=daily-question&envId=2025-02-14
 *
 * Solution:
 * Keep all prefix products of numbers in an array, then calculate the product of last K elements in O(1) complexity.
 * When a zero number is added, clean the array of prefix products.
 * 
 * TC: O(1) for both add and getProduct methods
 * SC: O(n)
 * #array #math #design #data-stream #prefix-sum #medium
 */

public class ProductOfTheLastKNumbers {
    private List<Long> pre;
    private int zeromark;

    public ProductOfTheLastKNumbers() {
        this.pre = new ArrayList<>();
        this.pre.add(1L);
        this.zeromark = -1;
    }

    public void add(int num) {
        long res = num == 0 ? 0L : num * this.pre.get(this.pre.size() - 1);
        if (num == 0) {
            this.zeromark = this.pre.size();
            this.pre.add(1L);
        } else {
            this.pre.add(res);
        }
    }

    public int getProduct(int k) {
        int n = this.pre.size();
        if (this.zeromark >= n - k) {
            return 0;
        }
        return (int)(this.pre.get(n - 1) / this.pre.get(n - k - 1));
    }

    public static void main(String[] args) {
        ProductOfTheLastKNumbers productOfNumbers = new ProductOfTheLastKNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        System.out.println(productOfNumbers.getProduct(2)); // 20
        System.out.println(productOfNumbers.getProduct(3)); // 40
        System.out.println(productOfNumbers.getProduct(4)); // 0
        productOfNumbers.add(8);
        System.out.println(productOfNumbers.getProduct(2)); // 32
    }
}