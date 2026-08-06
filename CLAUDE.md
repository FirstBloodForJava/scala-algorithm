# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

LeetCode 算法练习仓库，使用 **Java 17** 和 **Scala** 编写，Maven 构建。包含约 1190 个解题文件，涵盖算法、数据结构、动态规划、周赛/双周赛题目。

## 构建与运行

```bash
# 编译全部源码
mvn compile

# 运行单个文件（带 main 方法）
mvn exec:java -Dexec.mainClass="com.oycm.algorithm.a.basic.Solution_1"
```

没有单元测试框架——每个 `Solution_*.java` / `Solution_*.scala` 文件自带 `main` 方法，内部使用 `println(结果 == 预期)` 进行手工验证。

## 代码结构

### 核心包（`com.oycm`）

| 包 | 内容 |
|---|---|
| `algorithm` | 按算法技巧组织的题目：滑动窗口(`a`)、二分查找(`d`)、枚举(`e`)、前缀和(`f`)、差分(`g`)、位运算(`h`)、回溯(`i`) |
| `datastructure` | 按数据结构组织的题目：二叉树、链表、堆、栈（含单调栈）、队列、并查集、字典树、树 |
| `dp` | 动态规划专题：爬楼梯/打家劫舍、LCS/LIS、背包、区间 DP、股票买卖 |
| `hot100` / `hot150` | LeetCode 热门 100/150 题 |
| `week` | 周赛题目，按年份和场次编号组织（如 `lc2026/No491`） |
| `dualweek` | 双周赛题目 |
| `month2026` | 2026 年每月每日一题 |
| `math` | 数学类题目 |
| `string` | 字符串算法：KMP、AC 自动机、Manacher、Z 函数、后缀数组 |
| `utils` | 工具类：JSON 转 INSERT 语句生成、日志分析 |

### 旧版 LeetCode 包（`com.leetcode`）

`com.leetcode.interview` 和 `com.leetcode.interview_question` 是较早的 Java 解题代码，按面试题集和题号分组。

### 通用数据结构

- `com.oycm.TreeNode` — 二叉树节点，含 `serialize`/`deserialize`（LeetCode 层序格式）
- `com.oycm.ListNode` — 链表节点

### 文件命名约定

- Scala：`Solution_N.scala`，用 `object` 定义（如 `object Solution_1`）
- Java：`Solution_N.java` 或 `Solution.java`
- 每个文件顶部注释包含题目名称、LeetCode 题号和 URL
- 算法分类使用了紧凑的短命名：`a`=滑动窗口, `d`=二分, `e`=枚举, `f`=前缀和, `g`=差分, `h`=位运算, `i`=回溯

## 技术栈

- **Java 17**（编译目标 17）
- **Scala**（通过 IntelliJ IDEA Scala 插件，无需 sbt）
- **Jackson 2.13.3**（JSON 处理，仅 utils 使用）
- **IntelliJ IDEA**（项目配置已提交 `.idea/`）
