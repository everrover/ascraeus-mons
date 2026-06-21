package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-score-with-co-prime-element/description/
 *
 * Fix a candidate selected value x in [1, maxVal] and count how many elements in nums are not co-prime with x using inclusion-exclusion over x's distinct prime factors: for each non-empty subset of prime factors, add/subtract multiplesCount[product] by Mobius sign. The modification cost is the not-co-prime count minus 1 (the chosen element itself, if x already exists in nums) or max(1, notCoprimeCount) if x must be introduced. Precompute multiplesCount[d] via a harmonic sieve (O(V log V)) and smallest prime factors via a linear sieve for fast factorization. Enumerate all candidates and track the maximum score = x - cost.
 *
 * TC: O(V log V) SC: O(V)
 * #array #math #number-theory #inclusion-exclusion #sieve #hard
 */

class MaximumScoreWithCoPrimeElement {
    private static final int MAX_VAL = 100_000;

    private int[] smallestPrimeFactor;

    private void computeSmallestPrimeFactors() {
        for (int i = 0; i <= MAX_VAL; i++) {
            smallestPrimeFactor[i] = i;
        }
        for (int i = 2; i <= MAX_VAL; i++) {
            // if spf[i] still equals i, i is prime
            if (smallestPrimeFactor[i] != i) continue;

            for (long j = (long) i * i; j <= MAX_VAL; j += i) {
                int jInt = (int) j;
                if (smallestPrimeFactor[jInt] == jInt) {
                    smallestPrimeFactor[jInt] = i;
                }
            }
        }
    }

    /**
     * Returns the distinct prime factors of x.
     */
    private List<Integer> distinctPrimeFactors(int x) {
        List<Integer> primes = new ArrayList<>();
        while (x > 1) {
            int p = smallestPrimeFactor[x];
            primes.add(p);
            while (x % p == 0) x /= p;
        }
        return primes;
    }

    public int maxScore(int[] nums, int maxVal) {
        int n = nums.length;

        smallestPrimeFactor = new int[MAX_VAL + 1];
        computeSmallestPrimeFactors();

        // frequency[v] = how many times v appears in nums
        int[] frequency = new int[MAX_VAL + 1];
        int maxNum = 0;
        for (int num : nums) {
            frequency[num]++;
            maxNum = Math.max(maxNum, num);
        }

        // multiplesCount[d] = number of elements in nums divisible by d
        int[] multiplesCount = new int[MAX_VAL + 1];
        multiplesCount[1] = n; // every element is divisible by 1
        for (int d = 2; d <= MAX_VAL; d++) {
            for (int multiple = d; multiple <= MAX_VAL; multiple += d) {
                multiplesCount[d] += frequency[multiple];
            }
        }

        int bestScore = Integer.MIN_VALUE;
        int upperBound = Math.max(maxVal, maxNum);

        for (int x = 1; x <= upperBound; x++) {
            // x is only a valid candidate if it's within [1, maxVal]
            // OR it already exists in nums (so we might "remove" it instead of using it as the new number)
            if (frequency[x] == 0 && x > maxVal) continue;

            List<Integer> primeFactors = distinctPrimeFactors(x);
            int k = primeFactors.size();

            // Inclusion-exclusion: count how many elements in nums
            // are NOT coprime with x (i.e., share at least one prime factor with x)
            int notCoprimeCount = 0;
            int subsetCount = 1 << k;
            for (int mask = 1; mask < subsetCount; mask++) {
                int product = 1;
                int bitsSet = 0;
                for (int bit = 0; bit < k; bit++) {
                    if ((mask & (1 << bit)) != 0) {
                        product *= primeFactors.get(bit);
                        bitsSet++;
                    }
                }
                if (bitsSet % 2 == 1) {
                    notCoprimeCount += multiplesCount[product];
                } else {
                    notCoprimeCount -= multiplesCount[product];
                }
            }

            // cost = number of elements that need to be removed to make x coprime with the rest
            int cost;
            if (frequency[x] == 0) {
                // x not in nums: we must "add" it, paying at least 1,
                // plus remove every conflicting element
                cost = Math.max(1, notCoprimeCount);
            } else {
                // x == 1 is coprime with everything, no removal needed
                // otherwise, x itself is one of the notCoprimeCount elements,
                // so don't count removing x itself
                cost = (x == 1) ? 0 : notCoprimeCount - 1;
            }

            bestScore = Math.max(bestScore, x - cost);
        }

        return bestScore;
    }
}
