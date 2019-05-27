198. House Robber
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

public class Solution
{
  public int rob(int[] A)
  {
    if(A == null || A.length == 0)
    {
      return 0;
    }
    int l = A.length;
    if(l == 1)
    {
      return A[0];
    }
    if(l == 2)
    {
      return Math.max(A[0], A[1]);
    }
    int pprev = A[0];
    int prev = Math.max(A[0], A[1]);
    int curr = 0;
    for(int i=2;i<l;i++)
    {
      curr = Math.max(prev, pprev + A[i]);
      pprev = prev;
      prev = curr;
    }
    return curr;
  }
}

200. Number of Islands
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

class Solution
{
  void dfs(char[][] grid, boolean[][] visited, int i, int j)
  {
    if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j] || grid[i][j] == '0')
    {
      return;
    }
    visited[i][j] = true;
    dfs(grid, visited, i-1, j); // up
    dfs(grid, visited, i+1, j); // down
    dfs(grid, visited, i, j-1); // left
    dfs(grid, visited, i, j+1); // right
  }

  public int numIslands(char[][] grid)
  {
    if(grid == null || grid.length == 0)
    {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int count = 0;
    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(!visited[i][j] && grid[i][j] != '0')
        {
          dfs(grid, visited, i, j);
          count++;
        }
      }
    }
    return count;
  }
}

203. Remove Linked List Elements
Remove all elements from a linked list of integers that have value val.

class Solution
{
  public ListNode removeElements(ListNode head, int val)
  {
    ListNode sentinal = new ListNode(0);
    sentinal.next = head;
    ListNode prev = sentinal;
    ListNode curr = head;
    while(curr != null)
    {
    if(curr.val == val)
    {
       curr = curr.next;
    }
    else
    {
      prev.next = curr;
      curr = curr.next;
      prev = prev.next;
    }
    }
    prev.next = null;
    return sentinal.next;
  }
}

205. Isomorphic Strings
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
Note: You may assume both s and t have the same length.

class Solution
{
  public boolean isIsomorphic(String s, String t)
  {
    if((s == null && t == null) || (s.length() == 0 && t.length() == 0))
    {
      return true;
    }
    if(s.length() != t.length())
    {
      return false;
    }
    Map<Character, Character> mapping = new HashMap<>();
    Set<Character> coDomain = new HashSet<>();
    for(int i=0;i<s.length();i++)
    {
      Character y = mapping.get(s.charAt(i));
      if(y == null)
      {
        if(coDomain.contains(t.charAt(i)))
        {
           return false;
        }
        // add a new mapping
        mapping.put(s.charAt(i), t.charAt(i));
        // keep track of the co-domain
        coDomain.add(t.charAt(i));
      }
      else
      {
        // mapping from s->t already exists. Validate it
        if(y != t.charAt(i))
        {
          return false;
        }
      }
    }
    return true;
  }
}

206. Reverse Linked List
Reverse a singly linked list.

class Solution
{
  public ListNode reverseList(ListNode head)
  {
    ListNode prev = null;
    ListNode curr = head;
    while(curr != null)
    {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }
}

207. Course Schedule
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

public class Pair
{
  int nodeId;
  int inDegree;
  Pair(int nodeId, int inDegree)
  {
    this.nodeId = nodeId;
    this.inDegree = inDegree;
  }
}

class Solution
{
  public boolean canFinish(int V, int[][] E)
  {
    if(V <2)
    {
      return true;
    }
    // represent the graph as adjacency list
    // get the in-degree for every node
    // store the nodes in a min heap sorted by their in-degree
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    int[] inDegree = new int[V];
    // populate the adjacent list and the indegree of every node
    for(int i=0;i<E.length;i++)
    {
      int u = E[i][0];
      int v = E[i][1];
      inDegree[v]++;
      List<Integer> neighbours = adjList.get(u);
      if(neighbours == null)
      {
        neighbours = new LinkedList<>();
        adjList.put(u, neighbours);
      }
      neighbours.add(v);
    }
    // create a min-heap of nodes sorted by their in-degree
    Queue<Pair> pq = new PriorityQueue<>((a,b) -> a.inDegree-b.inDegree);
    Pair[] nodesWithInDegree = new Pair[V];
    for(int i=0;i<V;i++)
    {
      nodesWithInDegree[i] = new Pair(i, inDegree[i]);
      pq.add(nodesWithInDegree[i]);
    }
    // keep removing nodes from heap and update the in-degree of neighbours
    while(!pq.isEmpty())
    {
      Pair u = pq.remove();
      if(u.inDegree != 0)
      {
        // directed cycle exists. So topological ordering is not possible
        return false;
      }
      List<Integer> neighbours = adjList.get(u.nodeId);
      if(neighbours == null)
      {
        continue;
      }
      for(int v : neighbours)
      {
        Pair p = nodesWithInDegree[v];
        p.inDegree--;
        pq.remove(p);
        pq.add(p);
      }
    }
    return true;
  }
}

208. Implement Trie (Prefix Tree)
Implement a trie with insert, search, and startsWith methods.
Note: You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.

class TrieNode
{
  final int ALPHABET_SIZE = 26;
  // Initialize your data structure here.
  public TrieNode()
  {
		this.children = new TrieNode[ALPHABET_SIZE];
		this.isEndOfAWord = new boolean[ALPHABET_SIZE];
  }

  public TrieNode(int key)
  {
		this.children = new TrieNode[ALPHABET_SIZE];
    this.children[key] = new TrieNode();
		this.isEndOfAWord = new boolean[ALPHABET_SIZE];
  }

  public TrieNode(char key, int endIndex)
  {
		this.children = new TrieNode[ALPHABET_SIZE];
    this.children[key] = new TrieNode();
    this.isEndOfAWord = new boolean[ALPHABET_SIZE];
		this.isEndOfAWord[endIndex] = true;
  }

  boolean[] isEndOfAWord;
  TrieNode[] children;
}

public class Trie
{
	final int ALPHABET_SIZE = 26;
  private TrieNode root;

  public Trie()
  {
    root = new TrieNode();
		for(int i=0;i<ALPHABET_SIZE;i++)
		{
			root.isEndOfAWord[i] = true;
		}
  }

  int getIndex(char x)
  {
    return x-'a';
  }

  // Inserts a word into the trie.
	public void insert(String word)
  {
    TrieNode curr = root;
    TrieNode prev = null;
    int wordIndex = 0;
    int index = 0;
    while(curr != null && wordIndex < word.length())
    {
    	index = getIndex(word.charAt(wordIndex));
    	wordIndex++;
    	prev = curr;
    	curr = curr.children[index];
    }
    if(curr != null && wordIndex == word.length())
    {
    	curr.isEndOfAWord[index] = true;
    	return;
    }
    wordIndex--;
    curr = prev;
    while(wordIndex < word.length())
    {
    	index = getIndex(word.charAt(wordIndex));
    	wordIndex++;
    	curr.children[index] = new TrieNode();
    	curr = curr.children[index];
    }
    curr.isEndOfAWord[index] = true;
	}

  // Returns if the word is in the trie.
  public boolean search(String word)
  {
    TrieNode curr = root;
    int index=0;
    for(int i=0;i<word.length();i++)
    {
      index = getIndex(word.charAt(i));
      if(curr.children[index] != null)
      {
        curr = curr.children[index];
      }
      else
      {
        return false;
      }
    }
    return curr.isEndOfAWord[index];
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix)
  {
    TrieNode curr = root;
    int index=0;
    for(int i=0;i<prefix.length();i++)
    {
      index = getIndex(prefix.charAt(i));
      if(curr.children[index] != null)
      {
        curr = curr.children[index];
      }
      else
      {
        return false;
      }
    }
	  return true;
  }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");


210. Course Schedule II
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

class Pair
{
	int nodeId;
  int currInDegree;

  Pair(int nodeId, int currInDegree)
  {
    this.nodeId = nodeId;
    this.currInDegree = currInDegree;
  }
}

public class Solution
{
  public int[] findOrder(int V, int[][] E)
  {
    int[] out = new int[V];
    int[] currInDegree = new int[V];
		Map<Integer, List<Integer>> adjList = new HashMap<>();
    PriorityQueue<Pair> q = new PriorityQueue<>((a,b) -> a.currInDegree - b.currInDegree);
    Map<Integer, Pair> map = new HashMap<>();
    for(int i=0;i<E.length;i++)
    {
      int u = E[i][0];
      int v = E[i][1];
      // process edge (u,v)
      currInDegree[v]++;
      List<Integer> outNeighbours = adjList.get(u);
      if(outNeighbours == null)
      {
        outNeighbours = new ArrayList<>();
        adjList.put(u, outNeighbours);
      }
      outNeighbours.add(v);
    }
    // sort all the nodes based on their in-degree
    for(int i=0;i<V;i++)
    {
      Pair p = new Pair(i, currInDegree[i]);
      q.offer(p);
      map.put(i, p); //for O(1) lookup of a Pair in the queue
    }
    int index = 0;
    while(!q.isEmpty())
    {
      Pair p = q.remove();
      if(p.currInDegree != 0)
      {
        return new int[0];
      }
      int u = p.nodeId;
      out[index++] = u;
      // for all outgoing edges (u,v) from u, decrease the in-degree of v
      List<Integer> outNeighbours = adjList.get(u);
      if(outNeighbours == null)
      {
        continue;
      }
      for(int v : outNeighbours)
      {
        // process edge (u,v) . reduce the in-degree of v
        Pair x = map.get(v);
        x.currInDegree--;
        q.remove(x);
        q.offer(x);
      }
    }
    int s=0;
    int e = V-1;
    while(s<e)
    {
      int temp = out[s];
      out[s] = out[e];
      out[e] = temp;
      s++;
      e--;
    }
    return out;
  }
}

213. House Robber II
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

public class Solution
{
  public int rob(int[] A)
  {
    if(A == null || A.length == 0)
    {
      return 0;
    }

    int l = A.length;

    if(l == 1)
    {
      return A[0];
    }
    if(l == 2)
    {
      return Math.max(A[0], A[1]);
    }

    int[] P = new int[l];
    P[0] = A[0];
    P[1] = Math.max(A[0], A[1]);

    for(int i=2;i<l-1;i++)
    {
      P[i] = Math.max(P[i-2] + A[i], P[i-1]);
    }
    int maxP = P[l-2];

    P = new int[l];
    P[0] = A[1];
    P[1] = Math.max(A[1], A[2]);

    for(int i=2;i<l-1;i++)
    {
      P[i] = Math.max(P[i-2] + A[i+1], P[i-1]);
    }
    return Math.max(maxP, P[l-2]);
  }
}

217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

class Solution
{
  int[] buffer;

  void merge(int[] A, int p, int q, int r)
  {
    int index1 = p;
    int index2 = q+1;
    int index = p;
    while(index1 <= q && index2 <= r)
    {
      if(A[index1] <= A[index2])
      {
        buffer[index] = A[index1];
        index1++;
      }
      else
      {
        buffer[index] = A[index2];
        index2++;
      }
      index++;
    }
    while(index1 <= q)
    {
      buffer[index] = A[index1];
      index1++;
      index++;
    }
    while(index2 <= r)
    {
      buffer[index] = A[index2];
      index2++;
      index++;
    }
    for(int i=p;i<=r;i++)
    {
      A[i] = buffer[i];
    }
  }

  void mergeSort(int[] A, int p, int r)
  {
    if(p >= r)
    {
      return;
    }
    int q = p + (r-p)/2;
    mergeSort(A, p, q);
    mergeSort(A, q+1, r);
    merge(A, p, q, r);
  }

  public boolean containsDuplicate(int[] A)
  {
    if(A == null || A.length == 0)
    {
      return false;
    }
    buffer = new int[A.length];
    mergeSort(A, 0, A.length-1);
    for(int i=1;i<A.length;i++)
    {
      if(A[i] == A[i-1])
      {
        return true;
      }
    }
    return false;
  }
}

229. Majority Element II
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
Note: The algorithm should run in linear time and in O(1) space.

class Solution
{
  public List<Integer> majorityElement(int[] nums)
  {
    List<Integer> out = new ArrayList<>();
    if(nums == null || nums.length == 0)
    {
      return out;
    }
    int l = nums.length;
    Arrays.sort(nums);
    int count = 1;
    for(int i=1;i<l;i++)
    {
      if(nums[i] == nums[i-1])
      {
        count++;
      }
      else
      {
        if(count > (int) Math.floor(l/3))
        {
          out.add(nums[i-1]);
          if(out.size() == 2)
          {
            return out;
          }
        }
        count = 1;
      }
    }
    if(count > (int) Math.floor(l/3) && (out.size() == 0 || out.get(0) != nums[l-1]))
    {
      out.add(nums[l-1]);
    }
    return out;
  }
}

235. Lowest Common Ancestor of a Binary Search Tree
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
Note: All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.

public class Solution
{
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
  {
    if(root == null)
    {
      return null;
    }
    if(root == p || root == q)
    {
      return root;
    }

    TreeNode lcaL = null;
    TreeNode lcaR = null;

    if(p.val < root.val || q.val < root.val)
    {
      lcaL = lowestCommonAncestor(root.left, p, q);
    }
    if(p.val > root.val || q.val > root.val)
    {
      lcaR = lowestCommonAncestor(root.right, p, q);
    }
    if(lcaL != null && lcaR != null)
    {
      return root;
    }
    if(lcaL == null)
    {
      return lcaR;
    }
    else
    {
      return lcaL;
    }
  }
}

236. Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
Note: All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

public class Solution
{
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
  {
    if(root == null)
    {
      return null;
    }
    if( root == p || root == q)
    {
      return root;
    }
    TreeNode lcaL = lowestCommonAncestor(root.left, p, q);
    TreeNode lcaR = lowestCommonAncestor(root.right, p, q);
    if(lcaL != null && lcaR != null)
    {
      return root;
    }
    if (lcaL == null)
    {
      return lcaR;
    }
    else
    {
      return lcaL;
    }
  }
}

237. Delete Node in a Linked List
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Note: The linked list will have at least two elements.
All of the nodes' values will be unique.
The given node will not be the tail and it will always be a valid node of the linked list.
Do not return anything from your function.

class Solution
{
  public void deleteNode(ListNode node)
  {
    if(node == null || node.next == null)
    {
      return;
    }
    node.val = node.next.val;
    node.next = node.next.next;
    return;

  }
}

238. Product of Array Except Self
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Note: Please solve it without division and in O(n).
Follow up: Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

class Solution
{
  public int[] productExceptSelf(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return nums;
    }
    int n = nums.length;
    int[] out = new int[n];
    long product = 1;
    int zeroCount = 0;
    int firstZeroIndex = -1;
    for(int i=0;i<n;i++)
    {
      if(nums[i] == 0)
      {
        zeroCount++;
        if(zeroCount == 2)
        {
          return out;
        }
        firstZeroIndex = i;
        continue;
      }
      product = product * nums[i];
    }
    if(firstZeroIndex > -1)
    {
      out[firstZeroIndex] = (int) product;
      return out;
    }
    for(int i=0;i<n;i++)
    {
      out[i] = (int) product / nums[i];
    }
    return out;
  }
}
