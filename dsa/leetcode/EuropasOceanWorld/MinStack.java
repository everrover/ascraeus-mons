package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MinStack {

    private Stack<Long> stack;
    private Stack<Long> aux;

    /**
     * https://leetcode.com/problems/min-stack/?envType=company&envId=makemytrip&favoriteSlug=makemytrip-all
     *
     * The auxiliary stack is used to store the current minimum at each level on stack operation.
     * This allows retrieval of the minimum element in constant time.
     *
     * TC: O(1) SC: O(n)
     * #stack #design #medium
     */

    public MinStack() {
        stack = new Stack<>();
        aux = new Stack<>();
    }

    public void push(long x) {
        if (stack.isEmpty()) {
            stack.push(x);
            aux.push(x);
            return;
        }
        stack.push(x);
        aux.push(Long.min(x, aux.peek()));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            aux.pop();
        }
    }

    public long top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        throw new EmptyStackException();
    }

    public long getMin() {
        if (!aux.isEmpty()) {
            return aux.peek();
        }
        throw new EmptyStackException();
    }
}