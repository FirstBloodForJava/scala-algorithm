package com.oycm.week.No492;

public class Solution_1 {

    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        int size = Integer.MAX_VALUE;
        for (int i = capacity.length - 1; i >= 0; i--) {
            if (capacity[i] >= itemSize && size >= capacity[i]) {
                ans = i;
                size = capacity[i];
            }
        }
        return ans;
    }

}
