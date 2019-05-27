82. Remove Duplicates from Sorted List II
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

class Solution
{
  public ListNode deleteDuplicates(ListNode head)
  {
    if(head == null || head.next == null)
    {
      return head;
    }
    ListNode sentinal = new ListNode(0);
    ListNode lastNonDupNode = sentinal;
    ListNode curr = head;
    boolean isDupNode = false;
    while(curr.next != null)
    {
      if(curr.val == curr.next.val)
      {
        isDupNode = true;
        curr.next = curr.next.next;
      }
      else
      {
        if(isDupNode)
        {
          isDupNode = false;
        }
        else
        {
          lastNonDupNode.next = curr;
          lastNonDupNode = lastNonDupNode.next;
        }
        curr = curr.next;
      }
    }
    if(isDupNode)
    {
       lastNonDupNode.next = null;
    }
    else
    {
      lastNonDupNode.next = curr;
    }
    return sentinal.next;
  }
}

83. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

class Solution
{
  public ListNode deleteDuplicates(ListNode head)
  {
    if(head == null || head.next == null)
    {
      return head;
    }
    ListNode curr = head;
    while(curr.next != null)
    {
      if(curr.val == curr.next.val)
      {
        curr.next = curr.next.next;
      }
      else
      {
        curr = curr.next;
      }
    }
    return head;
  }
}

88. Merge Sorted Array
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note: The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

class Solution
{
  public void merge(int[] nums1, int m, int[] nums2, int n)
  {
    int i=m-1;
    int j=n-1;
    int k=m+n-1;
    while(i>=0 && j>=0)
    {
      if(nums1[i] > nums2[j])
      {
        nums1[k--] = nums1[i--];
      }
      else
      {
        nums1[k--] = nums2[j--];
      }
    }
    while(j>=0)
    {
      nums1[k--] = nums2[j--];
    }
    return;
  }
}

89. Gray Code
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

public class Solution
{
  static List<Integer> out;

  public int binToInt(String s)
  {
    int x=0;
    for(int i=0;i<s.length();i++)
    {
      x += Math.pow(2,i)*Character.getNumericValue(s.charAt(s.length()-1-i));
    }
    return x;
  }

  public void yarg(String prefix, int n)
  {
    if(n==0)
    {
      out.add(binToInt(prefix));
      return;
    }
    gray(prefix+"1",n-1);
    yarg(prefix+"0",n-1);
  }

  public void gray(String prefix, int n)
  {
    if(n==0)
    {
      out.add(binToInt(prefix));
      return;
    }
    gray(prefix+"0",n-1);
    yarg(prefix+"1",n-1);
  }

  public List<Integer> grayCode(int n)
  {
    out = new ArrayList<Integer>();
    gray("",n);
    return out;
  }
}

91. Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

class Solution
{
  public int numDecodings(String s)
  {
    if(s == null || s.length() == 0)
    {
      return 0;
    }
    int l = s.length();
    int[] ways = new int[l+1];
    ways[l] = 1;
    ways[l-1] = s.charAt(l-1) == '0' ? 0 : 1;
    for(int i=l-2;i>=0;i--)
    {
      if(s.charAt(i) == '0')
      {
        continue;
      }
      if(s.charAt(i+1) == '0')
      {
        if(s.charAt(i) != '1' && s.charAt(i) != '2')
        {
          return 0;
        }
        ways[i] = ways[i+2];
      }
      else if(Integer.parseInt(s.substring(i,i+2)) > 26)
      {

        ways[i] = ways[i+1];
      }
      else
      {
        ways[i] = ways[i+1] + ways[i+2];
      }
    }
    return ways[0];
  }
}

92. Reverse Linked List II
Reverse a linked list from position m to n.
Note: 1 ≤ m ≤ n ≤ length of list.

class Solution
{
  public ListNode reverseBetween(ListNode head, int m, int n)
  {
    if(head == null || head.next == null)
    {
      return head;
    }
    ListNode sentinal = new ListNode(0);
    sentinal.next = head;
    ListNode tailOfFirst = sentinal;
    for(int i=0;i<m-1;i++)
    {
      tailOfFirst = tailOfFirst.next;
    }
    ListNode headOfSecond = tailOfFirst.next;
    ListNode prev = tailOfFirst;
    ListNode curr = headOfSecond;
    for(int i=0;i<n-m+1;i++)
    {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    tailOfFirst.next = prev;
    headOfSecond.next = curr;
    return sentinal.next;
  }
}

94. Binary Tree Inorder Traversal
Given a binary tree, return the inorder traversal of its nodes' values.

class Solution
{
  List<Integer> out;

  void inOrder(TreeNode curr)
  {
    if(curr == null)
    {
      return;
    }
    inOrder(curr.left);
    out.add(curr.val);
    inOrder(curr.right);
  }
  public List<Integer> inorderTraversal(TreeNode root)
  {
    out = new ArrayList<>();
    inOrder(root);
    return out;
  }
}

96. Unique Binary Search Trees
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

public class Solution
{
  int[] memo;
  public int trees(int n)
  {
    if( memo[n] != 0)
    {
      return memo[n];
    }
    int tempSum=0;
    for(int i=1;i<=n;i++)
    {
      tempSum = tempSum + trees(i-1) * trees(n-i);
    }
    memo[n] = tempSum;
    return tempSum;
  }

  public int numTrees(int n)
  {
    if(n < 2)
    {
      return 1;
    }
    memo = new int[n+1];
    memo[0] = 1;
    memo[1] = 1;
    trees(n);
    return memo[n];
  }
}

98. Validate Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).
Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

class Solution
{
  boolean isValid(TreeNode curr, long lowValue, long highValue)
  {
    if(curr == null)
    {
      return true;
    }
    return Long.valueOf(curr.val) >= lowValue && Long.valueOf(curr.val) <= highValue && isValid(curr.left, lowValue, (long)curr.val-1) && isValid(curr.right, (long)curr.val+1, highValue);
  }

  public boolean isValidBST(TreeNode root)
  {
    if(root == null)
    {
      return true;
    }
     return isValid(root.left, Long.MIN_VALUE, ((long)root.val)-1) && isValid(root.right, ((long)root.val)+1, Long.MAX_VALUE);
  }
}

100. Same Tree
Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

class Solution
{
  public boolean isSameTree(TreeNode p, TreeNode q)
  {
    if(p == null && q == null)
    {
      return true;
    }
    if(p == null || q == null)
    {
      return false;
    }
    return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
}

101. Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

class Solution
{
  boolean isValid(TreeNode left, TreeNode right)
  {
    if(left == null && right == null)
    {
      return true;
    }
    if(left == null || right == null)
    {
      return false;
    }
    return left.val == right.val && isValid(left.left, right.right) && isValid(left.right, right.left);
  }

  public boolean isSymmetric(TreeNode root)
  {
    if(root == null)
    {
      return true;
    }
    return isValid(root.left, root.right);
  }
}

102. Binary Tree Level Order Traversal
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

class Solution
{
  public List<List<Integer>> levelOrder(TreeNode root)
  {
    List<List<Integer>> out = new ArrayList<>();
    if(root == null)
    {
      return out;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while(!q.isEmpty())
    {
      int numOfElementsInThisLevel = q.size();
      List<Integer> elementsInThisLevel = new ArrayList<>();
      for(int i=0;i<numOfElementsInThisLevel;i++)
      {
        TreeNode curr = q.remove();
        elementsInThisLevel.add(curr.val);
        if(curr.left != null)
        {
          q.add(curr.left);
        }
        if(curr.right != null)
        {
          q.add(curr.right);
        }
      }
      out.add(elementsInThisLevel);
    }
    return out;
  }
}

104. Maximum Depth of Binary Tree
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

class Solution
{
  public int maxDepth(TreeNode curr)
  {
    if(curr == null)
    {
      return 0;
    }
    return 1 + Math.max(maxDepth(curr.left), maxDepth(curr.right));
  }
}

105. Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a tree, construct the binary tree.
Note: You may assume that duplicates do not exist in the tree.

public class Solution
{
  public int search(int[] inorder, int x, int is, int ie)
  {
    for(int i=is;i<=ie;i++)
    {
      if(inorder[i] == x)
      {
        return i;
      }
    }
    return -1;
  }

  public TreeNode build(int[] preorder, int[] inorder, int ps, int is, int ie)
  {
    if(is>ie || ps>=preorder.length)
    {
      return null;
    }

    int x = search(inorder, preorder[ps], is, ie);
    TreeNode root = new TreeNode(preorder[ps]);
    root.left = build(preorder, inorder, ps+1, is,x-1);
    root.right = build(preorder, inorder, ps+x+1-is, x+1, ie);
    return root;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder)
  {
    if(preorder.length == 0 )
    {
      return null;
    }
    return build(preorder, inorder, 0,0,preorder.length-1);
  }
}

108. Convert Sorted Array to Binary Search Tree
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

class Solution
{
  TreeNode arrayToBst(int[] nums, int s, int e)
  {
    if(s>e)
    {
      return null;
    }
    int mid = s + (e-s)/2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = arrayToBst(nums, s, mid-1);
    root.right = arrayToBst(nums, mid+1, e);
    return root;
  }

  public TreeNode sortedArrayToBST(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return null;
    }
    return arrayToBst(nums, 0, nums.length-1);
  }
}
