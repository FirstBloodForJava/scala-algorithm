package com.oycm.datastructure.tree.diameter;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 3203. <a href="https://leetcode.cn/problems/find-minimum-diameter-after-merging-two-trees/description/">合并两棵树后的最小直径</a> 2266
     * <p>
     * 给你两棵 无向 树，分别有 n 和 m 个节点，节点编号分别为 0 到 n - 1 和 0 到 m - 1 。
     *
     * @param edges1 长度 n-1, edges1[i] = [a, b] 表示在一棵树中节点 a 和 b 之间有一条边
     * @param edges2 长度 m-1, edges2[i] = [a, b] 表示在一棵树中节点 a 和 b 之间有一条边
     * @return
     */
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        /*
        在第一棵树和第二棵树中分别选一个节点，并用一条边连接它们。
        请你返回添加边后得到的树中，最小直径 为多少。
        一棵树的 直径 指的是树中任意两个节点之间的最长路径长度。
        求最小化的最大值
         */
        /*
        选 tree1 的节点 a 连接 tree2 的节点 b，这里的树的直径是 下面三个结果的最大值最小化
            tree1 的直径
            tree2 的直径
            tree1 以 a 为根节点的最大路径 + tree2 以 b 为根节点的最大路径 + 1
        tree1, tree2 的直径是固定的，取两个的最大值（合并后直径最小值），也就是说，如果存在 第三点的和小于该值，最小直径可为 max(tree1Len, tree2Len)
        优化思路：第三种方式的直径为以下三部分之和
            节点 a 在 tree1 到最远点的距离最小值
            节点 b 在 tree2 到最远点的距离最小值
            节点 a 和 节点 b 的距离 1
        最小化的最大值为树直径的中间, 为 直径 d / 2 上取整
        树的直径两个端点记为 A, B, 如果点 v 是直径上的某个点, vA + vB = L，
            v 到树种非直径的最大距离记为 vx, 其中 vx 一定不为 > max(vA, vB), 如果大于了，意味着 L 不是树的直径
            vA, VB 的最大值为 L/2 上取整
        其他非直径上的点，是可以和 A, B 直径中的点相连的，其值肯定大于等于 L/2 上取整 + 1，所以树中节点到最远的的距离为树直径的中点
         */
        int n = edges1.length + 1, m = edges2.length + 1;
        List<Integer>[] g1 = new ArrayList[n];
        Arrays.setAll(g1, l -> new ArrayList<>());
        for (int[] row : edges1) {
            g1[row[0]].add(row[1]);
            g1[row[1]].add(row[0]);
        }

        List<Integer>[] g2 = new ArrayList[m];
        Arrays.setAll(g2, l -> new ArrayList<>());
        for (int[] row : edges2) {
            g2[row[0]].add(row[1]);
            g2[row[1]].add(row[0]);
        }
        maxDiameter(0, -1, g1);
        int d1 = diameter;
        diameter = 0;
        maxDiameter(0, -1, g2);


        return Math.max(Math.max(d1, diameter), (d1 + 1) / 2 + (diameter + 1) / 2 + 1);
    }

    // 求两棵树的最大直径
    int diameter;

    public int maxDiameter(int cur, int fa, List<Integer>[] g) {
        int maxLen = 0;
        for (int next : g[cur]) {
            if (next != fa && next != -1) {
                int childLen = maxDiameter(next, cur, g) + 1;
                diameter = Math.max(diameter, childLen + maxLen);
                maxLen = Math.max(maxLen, childLen);
            }
        }
        return maxLen;
    }

    public int dfsTreeHeight(int cur, int fa, List<Integer>[] g) {
        int maxLen = 0;
        for (int next : g[cur]) {
            if (next != fa && next != -1) {
                int childLen = dfsTreeHeight(next, cur, g) + 1;
                if (childLen > maxLen) maxLen = childLen;
            }
        }
        return maxLen;
    }



    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();
        System.out.println(solution.minimumDiameterAfterMerge(
                DataCreateUtils.twoDimensionInts("[[5,0],[9,2],[15,5],[41,8],[39,9],[31,11],[24,12],[26,16],[40,17],[15,19],[6,15],[29,20],[6,21],[14,6],[13,14],[37,22],[27,23],[4,26],[29,4],[18,28],[40,18],[29,32],[3,36],[10,3],[35,10],[13,35],[33,37],[31,38],[7,31],[33,7],[29,33],[1,29],[1,39],[34,1],[25,34],[24,25],[27,24],[13,27],[30,13],[30,40],[30,41]]"),
                DataCreateUtils.twoDimensionInts("[[0,4],[68,7],[45,10],[21,12],[1,13],[16,17],[3,19],[29,3],[28,27],[1,29],[60,30],[8,31],[65,33],[83,34],[40,37],[23,43],[79,44],[21,47],[26,52],[77,55],[35,56],[8,57],[78,59],[45,60],[41,62],[38,41],[21,38],[25,21],[39,25],[1,65],[22,66],[23,67],[74,23],[53,68],[54,53],[32,70],[45,32],[6,45],[79,6],[9,71],[69,9],[20,69],[51,20],[26,72],[11,26],[79,11],[1,73],[61,1],[35,61],[40,35],[24,40],[48,74],[81,48],[16,76],[79,16],[46,77],[28,46],[39,28],[15,39],[36,15],[5,78],[36,5],[14,36],[24,79],[49,24],[54,49],[22,80],[18,22],[42,18],[75,42],[50,75],[8,50],[2,81],[8,2],[0,8],[54,0],[58,54],[64,58],[51,64],[83,51],[14,82],[63,14],[63,83]]")
        ));

    }

}
