109. Convert Sorted List to Binary Search Tree
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

public class Solution
{
  TreeNode constructBST(ListNode head, int l)
  {
    if(l == 0)
    {
      return null;
    }
    int mid = (l+1)/2;
    // find mid node
    ListNode midNode = head;
    for(int i=1;i<mid;i++)
    {
      midNode = midNode.next;
    }
    TreeNode root = new TreeNode(midNode.val);
    root.left = constructBST(head, mid-1);
    root.right = constructBST(midNode.next, l-mid);
    return root;
  }

  public TreeNode sortedListToBST(ListNode head)
  {
    if(head == null)
    {
      return null;
    }
    int l=0;
    ListNode curr = head;
    while(curr != null)
    {
      curr = curr.next;
      l++;
    }
    return constructBST(head, l);
  }
}

110. Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

class Solution
{
  int getHeight(TreeNode curr)
  {
    if(curr == null)
    {
      return 0;
    }
    int lHeight = getHeight(curr.left);
    if(lHeight == -1)
    {
      return -1;
    }
    int rHeight = getHeight(curr.right);
    if(rHeight == -1)
    {
      return -1;
    }
    if(Math.abs(lHeight-rHeight) > 1)
    {
      return -1;
    }
    return 1+Math.max(lHeight, rHeight);

  }

  public boolean isBalanced(TreeNode root)
  {
    if(root == null)
    {
      return true;
    }
    return getHeight(root) != -1;
  }
}

111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

class Solution
{
  public int minDepth(TreeNode curr)
  {
    if(curr == null)
    {
      return 0;
    }
    int leftDepth = minDepth(curr.left);
    int rightDepth = minDepth(curr.right);
    if(leftDepth == 0 || rightDepth == 0)
    {
      return 1+leftDepth+rightDepth;
    }
    else
    {
      return 1+Math.min(leftDepth, rightDepth);
    }
  }
}

112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

class Solution
{
  public boolean hasPathSum(TreeNode curr, int sum)
  {
    if(curr == null)
    {
      return false;
    }
    if(curr.left == null && curr.right == null)
    {
      return curr.val == sum;
    }
    return hasPathSum(curr.left, sum-curr.val) || hasPathSum(curr.right, sum-curr.val);
  }
}

113. Path Sum II
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

public class Solution
{
  List<List<Integer>> out;

  void pathSums(TreeNode node, List<Integer> pathEntries, int currSum, int sum)
  {
    if(node == null)
    {
      return;
    }
    List<Integer> newPathEntries = new ArrayList<>();
    newPathEntries.addAll(pathEntries);
    newPathEntries.add(node.val);
    if(node.left == null && node.right == null)
    {
      if(sum == (currSum + node.val))
      {
        out.add(newPathEntries);
        return;
      }
    }
    pathSums(node.left, newPathEntries, currSum+node.val, sum);
    pathSums(node.right, newPathEntries, currSum+node.val, sum);
  }

  public List<List<Integer>> pathSum(TreeNode root, int sum)
  {
    out = new ArrayList<>();
    pathSums(root, new ArrayList<Integer>(), 0, sum);
    return out;
  }
}

114. Flatten Binary Tree to Linked List
Given a binary tree, flatten it to a linked list in-place.

public class Solution
{
  public void flatten(TreeNode root)
  {
    if(root == null)
    {
      return;
    }
    TreeNode left = root.left;
    TreeNode right = root.right;
    flatten(left);
    flatten(right);
    root.left = null;
    TreeNode curr = left;
    if(curr != null)
    {
      root.right = left;
      while(curr.right != null)
      {
        curr = curr.right;
      }
      curr.right = right;
    }
    return;
  }
}

116. Populating Next Right Pointers in Each Node
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

/*
// Definition for a Node.
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {}

  public Node(int _val,Node _left,Node _right,Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
};
*/
class Solution
{
  public Node connect(Node root)
  {
    if(root == null)
    {
      return root;
    }
    Queue<Node> q = new LinkedList<>();
    q.add(root);
    while(!q.isEmpty())
    {
      int numOfNodesInThisLevel = q.size();
      for(int i=0;i<numOfNodesInThisLevel;i++)
      {
        Node curr = q.remove();
        if(i <numOfNodesInThisLevel-1)
        {
          curr.next = q.peek();
        }
        if(curr.left != null)
        {
          q.add(curr.left);
        }
        if(curr.right != null)
        {
          q.add(curr.right);
        }
      }
    }
    return root;
  }
}

118. Pascal's Triangle
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
Input: 5
Output:
[
   [1],
  [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

public class Solution
{
  public List<List<Integer>> generate(int numRows)
  {
    List<List<Integer>> out = new LinkedList<List<Integer>>();
    if(numRows<1)
    {
      return out;
    }
    int[] A = new int[numRows];
		int prev,tPrev;
    for(int i=0;i<numRows;i++)
    {
      A[i] = 0;
    }
    A[0] = 1;
    for(int i=0;i<numRows;i++) //line number
    {
			//compute the array. Why need 2 temp variables?
			prev = A[0];
			for(int j=1;j<=i;j++)
			{
				tPrev = prev;
				prev = A[j];
				A[j] = A[j] + tPrev;
			}
			List<Integer> line = new LinkedList<Integer>();
      for(int j=0;j<=i;j++)
      {
       line.add(A[j]);
      }
      out.add(line);
    }
    return out;
  }
}

119. Pascal's Triangle II
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
Note that the row index starts from 0.
Follow up: Could you optimize your algorithm to use only O(k) extra space?

public class Solution
{
  public List<Integer> getRow(int rowIndex)
  {
    List<Integer> out = new LinkedList<Integer>();
    if(rowIndex<0)
    {
      return out;
    }
    int tPrev,prev;
    int[] A = new int[rowIndex+1];
    for(int i=0;i<rowIndex+1;i++)
    {
      A[i] = 0;
    }
    A[0] = 1;
    for(int i=0;i<=rowIndex;i++)
    {
      prev = A[0];
      for(int j=1;j<=i;j++)
      {
        tPrev = prev;
        prev = A[j];
        A[j] = A[j] + tPrev;
      }
    }
    for(int i=0;i<A.length;i++)
    {
      out.add(A[i]);
    }
    return out;
  }
}

121. Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Note that you cannot sell a stock before you buy one.

class Solution
{
  public int maxProfit(int[] prices)
  {
    if(prices == null || prices.length < 2)
    {
      return 0;
    }
    int minBuyPriceSoFar = prices[0];
    int maxProfit = 0;
    for(int i=1;i<prices.length;i++)
    {
      maxProfit = Math.max(maxProfit, prices[i]-minBuyPriceSoFar);
      minBuyPriceSoFar = Math.min(minBuyPriceSoFar, prices[i]);
    }
    return maxProfit;
  }
}

122. Best Time to Buy and Sell Stock II
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

public class Solution
{
  public int maxProfit(int[] prices)
		{
    int total = 0;

    for (int i=0; i< prices.length-1;i++)
				{
      if (prices[i+1]>prices[i])
						{
        total = total + (prices[i+1]-prices[i]);
      }
    }
    return total;
  }
}

125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
Note: For the purpose of this problem, we define empty string as valid palindrome.

class Solution
{
  public boolean isPalindrome(String str)
  {
    if(str == null || str.length() < 2)
    {
      return true;
    }
    int s = 0;
    int e = str.length()-1;
    while(s<e)
    {
      int ascii = (int)str.charAt(s);
      if(ascii < 48 || ascii > 122 || (ascii > 90 && ascii < 97) || (ascii > 57 && ascii < 65))
      {
        s++;
        continue;
      }
      ascii = (int)str.charAt(e);
      if(ascii < 48 || ascii > 122 || (ascii > 90 && ascii < 97) || (ascii > 57 && ascii < 65))
      {
        e--;
        continue;
      }
      if(!str.substring(s,s+1).equalsIgnoreCase(str.substring(e, e+1)))
      {
        return false;
      }
      s++;
      e--;
    }
    return true;
  }
}

128. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
Your algorithm should run in O(n) complexity.

class Solution
{
  public int longestConsecutive(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    Arrays.sort(nums);
    int maxL = 1;
    int maxLEndingHere = 1;
    for(int i=1;i<nums.length;i++)
    {
      if(nums[i] == nums[i-1]+1)
      {
        maxLEndingHere = maxLEndingHere + 1;
      }
      else if(nums[i] != nums[i-1])
      {
        maxLEndingHere = 1;
      }
      maxL = Math.max(maxL, maxLEndingHere);
    }
    return maxL;
  }
}

130. Surrounded Regions
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by swapping all 'O's into 'X's in that surrounded region.
Explanation: Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not swapped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be swapped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

class Solution
{
  void dfs(char[][] board, boolean[][] visited, int i, int j)
  {
    if(i<0 || j<0 || i>=board.length || j>=board[0].length || visited[i][j] || board[i][j] != 'O')
    {
      return;
    }
    visited[i][j] = true;
    board[i][j] = '1';
    dfs(board, visited, i-1, j);
    dfs(board, visited, i+1, j);
    dfs(board, visited, i, j-1);
    dfs(board, visited, i, j+1);
  }

  public void solve(char[][] board)
  {
    if(board == null || board.length < 3 || board[0].length < 3)
    {
      return;
    }
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    for(int i=0;i<cols;i++)
    {
      if(!visited[0][i] && board[0][i] == 'O')
      {
        dfs(board, visited, 0, i);
      }
    }
    for(int i=1;i<rows;i++)
    {
      if(!visited[i][cols-1] && board[i][cols-1] == 'O')
      {
        dfs(board, visited, i, cols-1);
      }
    }
    for(int i=cols-2;i>=0;i--)
    {
      if(!visited[rows-1][i] && board[rows-1][i] == 'O')
      {
        dfs(board, visited, rows-1, i);
      }
    }
    for(int i=rows-2;i>=1;i--)
    {
      if(!visited[i][0] && board[i][0] == 'O')
      {
        dfs(board, visited, i, 0);
      }
    }

    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(board[i][j] == 'O')
        {
          board[i][j] = 'X';
        }
      }
    }
    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(board[i][j] == '1')
        {
          board[i][j] = 'O';
        }
      }
    }

    return;
  }
}

134. Gas Station
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
Note:
If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

class Solution
{
  public int canCompleteCircuit(int[] gas, int[] cost)
  {
    int l = gas.length;
    int startPos = 0;
    int currentGas = 0;
    for(int i=0;i<l;i++)
    {
      if(currentGas + gas[i] - cost[i] >= 0)
      {
        currentGas = currentGas + gas[i] - cost[i];
      }
      else
      {
        startPos = i+1;
        currentGas = 0;
      }
    }
    for(int i=0;i<startPos;i++)
    {
       if(currentGas + gas[i] - cost[i] >= 0)
      {
        currentGas = currentGas + gas[i] - cost[i];
      }
      else
      {
        return -1;
      }
    }
    return startPos;
  }
}
