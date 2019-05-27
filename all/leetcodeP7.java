136. Single Number
Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

class Solution
{
  public int singleNumber(int[] nums)
  {
    int checkSum=0;
    for(int i=0;i<nums.length;i++)
    {
      checkSum = checkSum ^ nums[i];
    }
    return checkSum;
  }
}

139. Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Note: The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

class Solution
{
  Map<String, Boolean> memo;
  boolean canBreak(String str, Set<String> dict, int s, int e)
  {
    if(s>e)
    {
      return false;
    }
    if(dict.contains(str.substring(s, e+1)))
    {
      return true;
    }
    else if(s==e)
    {
      return false;
    }
    Boolean value = memo.get(s + "," + e);
    if(value != null)
    {
      return value;
    }
    for(int i=s;i<e;i++)
    {
      if(canBreak(str, dict, s, i) && canBreak(str, dict, i+1, e))
      {
        memo.put(s + "," + e, true);
        return true;
      }
    }
    memo.put(s + "," + e, false);
    return false;
  }

  public boolean wordBreak(String s, List<String> wordDict)
  {
    Set<String> dict = new HashSet<>();
    for(String str : wordDict)
    {
      dict.add(str);
    }
    memo = new HashMap<>();
    return canBreak(s, dict, 0, s.length()-1);
  }
}

141. Linked List Cycle
Given a linked list, determine if it has a cycle in it.
To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

public class Solution
{
  public boolean hasCycle(ListNode head)
  {
    if(head == null || head.next == null)
    {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null)
    {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast)
      {
        return true;
      }
    }
    return false;
  }
}

142. Linked List Cycle II
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
Note: Do not modify the linked list.

public class Solution
{
  public ListNode detectCycle(ListNode head)
  {
    if(head == null || head.next == null)
    {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null)
    {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast)
      {
        // found a cycle
        slow = head;
        while(slow != fast)
        {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;
      }
    }
    return null;
  }
}

143. Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You may not modify the values in the list's nodes, only nodes itself may be changed.

struct ListNode* reverse(struct ListNode* listB)
{
	if(listB==NULL)
	{
		return NULL;
	}
	struct ListNode* prev;
	struct ListNode* curr;
	struct ListNode* next;
	prev=NULL;
	curr=listB;
	next=curr->next;
	while(next!=NULL)
	{
		curr->next=prev;
		prev=curr;
		curr=next;
		next=next->next;
	}
	curr->next=prev;
	return curr;
}

 void merge(struct ListNode* listA,struct ListNode* listB)
 {
   struct ListNode* currA=listA;
   struct ListNode* currB=listB;
   struct ListNode* temp;
   while(currA->next!=NULL)
   {
     temp=currA->next;
     currA->next=currB;
     currB=currB->next;
     (currA->next)->next=temp;
     currA=temp;
   }
   currA->next=currB;
 }

void reorderList(struct ListNode *head)
{
  if(head==NULL || head->next==NULL)
  {
    return;
  }
  int l=0;
  struct ListNode* curr=head;
  struct ListNode* listA=head;
  struct ListNode* listB=head;
  while(curr!=NULL)
  {
    curr=curr->next;
    l++;
  }
  for(int i=0;i<l/2-1;i++)
  {
    listA=listA->next;
  }
  listB=listA->next;
  listA->next=NULL;
  listB=reverse(listB);
  merge(head,listB);
}

144. Binary Tree Preorder Traversal
Given a binary tree, return the preorder traversal of its nodes' values.

class Solution
{
  List<Integer> out;

  void preOrder(TreeNode curr)
  {
    if(curr == null)
    {
      return;
    }
    out.add(curr.val);
    preOrder(curr.left);
    preOrder(curr.right);
  }

  public List<Integer> preorderTraversal(TreeNode root)
  {
    out = new ArrayList<>();
    preOrder(root);
    return out;
  }
}

145. Binary Tree Postorder Traversal

class Solution
{
  List<Integer> out;

  void postOrder(TreeNode curr)
  {
    if(curr == null)
    {
      return;
    }
    postOrder(curr.left);
    postOrder(curr.right);
    out.add(curr.val);
  }

  public List<Integer> postorderTraversal(TreeNode root)
  {
    out = new LinkedList<>();
    postOrder(root);
    return out;
  }
}

146. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
The cache is initialized with a positive capacity.
Follow up: Could you do both operations in O(1) time complexity?

class LRUCache
{
  Map<Integer, Integer> cache;
  Queue<Integer> q;
  int capacity;
  public LRUCache(int capacity)
  {
    this.capacity = capacity;
    cache = new HashMap<>();
    q = new LinkedList<>();
  }

  public int get(int key)
  {
    Integer val = cache.get(key);
    if(val == null)
    {
      return -1;
    }
    // key exists. Move it to the tail of the queue.
    q.remove(key);
    q.add(key);
    return val;
  }

  public void put(int key, int value)
  {
    Integer val = cache.get(key);
    if(val != null)
    {
       // key exists. overwrite value
       cache.put(key, value);
       // Move it to the tail of the queue.
       q.remove(key);
       q.add(key);
       return;
    }
    if(q.size() == capacity)
    {
      // evict old entry from both map and queue
      Integer removedKey = q.remove();
      cache.remove(removedKey);
    }
    // add new entry to both map and queue
    cache.put(key, value);
    q.add(key);
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

149. Max Points on a Line
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

/**
 * Definition for a point.
 * class Point {
 *   int x;
 *   int y;
 *   Point() { x = 0; y = 0; }
 *   Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution
{
  public int maxPoints(Point[] points)
  {
    if(points == null || points.length == 0)
    {
      return 0;
    }

    int maxPointsInALine = 0;
    for(int i=0;i<points.length;i++)
    {
      int dupCount = 1;
      double slope = 0.0f;
      Map<Double, Integer> slopeMap = new HashMap<>();
      for(int j=i+1;j<points.length;j++)
      {
        if(points[i].x == points[j].x && points[i].y == points[j].y)
        {
          dupCount++;
          continue;
        }
        else if(points[i].x == points[j].x)
        {
          // infinite slope
          slope = Double.MAX_VALUE;
        }
        else if (points[i].y == points[j].y)
        {
          // zero slope
          slope = 0.0f;
        }
        else
        {
          slope =  (points[j].y - points[i].y) * 1.0f / (double) (points[j].x - points[i].x);
        }
        Integer count = slopeMap.get(slope);
        if(count == null)
        {
          count = 1;
        }
        else
        {
          count++;
        }
        slopeMap.put(slope, count);
      }
      int max=0;
      for(Integer count : slopeMap.values())
      {
        max = Math.max(max, count);
      }
      max = max+dupCount;
      maxPointsInALine = Math.max(maxPointsInALine, max);
    }
    return maxPointsInALine;
  }
}

150. Evaluate Reverse Polish Notation
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Note: Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.

class Solution
{
  public int evalRPN(String[] tokens)
  {
    if(tokens == null || tokens.length == 0)
    {
      return 0;
    }
    Stack<Integer> stack = new Stack<>();
    for(int i=0;i<tokens.length;i++)
    {
      try
      {
        stack.push(Integer.parseInt(tokens[i]));
      }
      catch(NumberFormatException nfe)
      {
        int b = stack.pop();
        int a = stack.pop();
        int result = 0;
        if(tokens[i].equals("+"))
        {
          result = a + b;
        }
        else if (tokens[i].equals("-"))
        {
          result = a - b;
        }
        else if (tokens[i].equals("*"))
        {
          result = a * b;
        }
        else if (tokens[i].equals("/"))
        {
          result = a / b;
        }
        stack.push(result);
      }
    }
    return stack.pop();
  }
}

151. Reverse Words in a String
Given an input string, reverse the string word by word.
Note: A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.

class Solution
{
  public String reverseWords(String s)
  {
    if(s == null || s.length() == 0)
    {
      return s;
    }
    String out = "";
    int index = s.length()-1;
    int wordEndIndex = -1;
    while(index >= 0)
    {
      if(s.charAt(index) != ' ')
      {
        if(wordEndIndex == -1)
        {
          wordEndIndex = index;
        }
      }
      else
      {
        if(wordEndIndex != -1)
        {
          out = out + s.substring(index+1, wordEndIndex+1) + " ";
          wordEndIndex = -1;
        }
      }
      index--;
    }
    if(wordEndIndex != -1)
    {
      out = out + s.substring(0, wordEndIndex+1);
    }
    else if (out.length() > 0)
    {
      out = out.substring(0, out.length()-1);
    }
    return out;
  }
}

152. Maximum Product Subarray
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

class Solution
{
  public int maxProduct(int[] A)
  {
    if(A == null || A.length == 0)
    {
      return 0;
    }
    int maxProductAtIndex = A[0];
    int minProductAtIndex = A[0];
    int maxProductSoFar = A[0];

    for(int i=1;i<A.length;i++)
    {
      int option1 = A[i];
      int option2 = maxProductAtIndex * A[i];
      int option3 = minProductAtIndex * A[i];

      maxProductAtIndex = Math.max(Math.max(option1, option2), option3);
      minProductAtIndex = Math.min(Math.min(option1, option2), option3);
      maxProductSoFar = Math.max(maxProductSoFar, maxProductAtIndex);
    }
    return maxProductSoFar;
  }
}

153. Find Minimum in Rotated Sorted Array
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.

class Solution
{
  public int findMin(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    int s = 0;
    int e = nums.length-1;
    int min = Integer.MAX_VALUE;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(nums[s] <= nums[mid])
      {
        min = Math.min(min, nums[s]);
        s = mid + 1;
      }
      else
      {
        min = Math.min(min, nums[mid]);
        e = mid - 1;
      }
    }
    return min;
  }
}

155. Min Stack
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

class Pair
{
  int value;
  int count;
  Pair(int value)
  {
    this.value = value;
    this.count = 1;
  }
}

class MinStack
{

  Stack<Integer> mainStack;
  Stack<Pair> minStack;

  public MinStack()
  {
    mainStack = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int x)
  {
    mainStack.push(x);
    if(minStack.isEmpty())
    {
      minStack.push(new Pair(x));
    }
    else
    {
      Pair topElement = minStack.peek();
      if(x < topElement.value)
      {
        minStack.push(new Pair(x));
      }
      else
      {
        topElement.count++;
      }
    }
  }

  public void pop()
  {
    mainStack.pop();
    Pair topElement = minStack.peek();
    if(topElement.count > 1)
    {
      topElement.count--;
    }
    else
    {
      minStack.pop();
    }
  }

  public int top()
  {
    return mainStack.peek();
  }

  public int getMin()
  {
    return minStack.peek().value;
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

160. Intersection of Two Linked Lists
Write a program to find the node at which the intersection of two singly linked lists begins.
Notes: If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

public class Solution
{
  public ListNode getIntersectionNode(ListNode headA, ListNode headB)
  {
    ListNode currA = headA;
    ListNode currB = headB;
    int lA=0;
    int lB=0;

    // get length of A
    while(currA != null)
    {
      lA++;
      currA = currA.next;
    }

    // get length of B
    while(currB != null)
    {
      lB++;
      currB = currB.next;
    }

    currA = headA;
    currB = headB;

    // shift the longer list by lA~lB
    if(lA > lB)
    {
      for(int i=0;i<(lA-lB);i++)
      {
        currA = currA.next;
      }
    }
    else if(lB > lA)
    {
      for(int i=0;i<(lB-lA);i++)
      {
        currB = currB.next;
      }
    }
    while(currA != null && currB != null)
    {
      if(currA == currB)
      {
        return currA;
      }
      currA = currA.next;
      currB = currB.next;
    }
    return null;
  }
}
