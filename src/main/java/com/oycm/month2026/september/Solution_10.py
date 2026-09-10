class Solution_10:
    def averageOfSubtree(self, root: TreeNode) -> int:
        # 自底向上返回
        ans = 0

        def dfs(node: TreeNode | Node) -> tuple[int, int]:
            if node is None:
                return 0, 0
            left_sum, left_size = dfs(node.left)
            right_sum, right_size = dfs(node.right)
            sum_ = left_sum + right_sum + node.val
            size = left_size + right_size + 1
            if node.val == sum_ // size:
                nonlocal ans
                ans += 1
            return sum_, size
        dfs(root)

        return ans