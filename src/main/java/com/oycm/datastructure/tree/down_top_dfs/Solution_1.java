package com.oycm.datastructure.tree.down_top_dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_1 {

    /**
     * 690. <a href="https://leetcode.cn/problems/employee-importance/description/">员工的重要性</a>
     *
     * @param employees
     * @param id        表示一个目标员工的 ID
     * @return id 员工和他所有下属的重要度的 总和
     */
    public int getImportance(List<Employee> employees, int id) {
        /*
        给定一个整数 id 表示一个员工的 ID，返回这个员工和他所有下属的重要度的 总和
        一名员工最多有一名直接领导，并可能有多名下属。
         */
        Map<Integer, Employee> map = new HashMap<>(employees.size());
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        return dfs(id, map);
    }

    public int dfs(int leadId, Map<Integer, Employee> map) {
        Employee lead = map.get(leadId);
        int imps = lead.importance;
        for (int sub : lead.subordinates) {
            imps += dfs(sub, map);
        }

        return imps;
    }
}

class Employee {
    public int id;
    // 重要度
    public int importance;
    // 员工 id 的下属列表
    public List<Integer> subordinates;
}
