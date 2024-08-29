package dsa.leetcode.jovianMoonOrbit;

public class FindInMountainArray {

    private interface MountainArray {
        int get(int index);
        int length();
    }

    /**
     * https://leetcode.com/problems/find-in-mountain-array/
     * 
     * To solve the problem, we initially identify the peak of the mountain array using binary search.
     * Then, we perform two more binary searches on both sides of the peak to find the target value.
     * 
     * TC: O(logn) SC: O(1)
     * #binary-search #interactive #hard
     */

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findMnt(mountainArr);
        int res = findTargetF(mountainArr, target, peak);
        if (res == -1) return findTargetS(mountainArr, target, peak);
        else return res;
    }

    private int findMnt(MountainArray mnt) {
        int l = 0, r = mnt.length() - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (mnt.get(mid) < mnt.get(mid + 1)) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int findTargetF(MountainArray mnt, int target, int peak) {
        int l = 0, r = peak, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            int b = mnt.get(mid);
            if (b < target) l = mid + 1;
            else if (b > target) r = mid - 1;
            else return mid;
        }
        return -1;
    }

    private int findTargetS(MountainArray mnt, int target, int peak) {
        int l = peak + 1, r = mnt.length() - 1, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            int b = mnt.get(mid);
            if (b > target) l = mid + 1;
            else if (b < target) r = mid - 1;
            else return mid;
        }
        return -1;
    }
}