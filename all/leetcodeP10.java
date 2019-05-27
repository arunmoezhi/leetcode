239. Sliding Window Maximum
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
Note: You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
Follow up: Could you solve it in linear time?

class Solution
{
  public int[] maxSlidingWindow(int[] A, int k)
  {
    if(A == null || A.length == 0)
    {
      return new int[0];
    }
    int n = A.length;
    int[] max = new int[n-k+1];
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> b-a);
    for(int i=0;i<k;i++)
    {
      maxHeap.add(A[i]);
    }
    max[0] = maxHeap.peek();
    int s = 0;
    int maxIndex = 1;
    for(int i=k;i<n;i++)
    {
      maxHeap.remove(A[s++]); // remove leftmost element
      maxHeap.add(A[i]); // add current element
      max[maxIndex++] = maxHeap.peek();
    }
    return max;
  }
}

240. Search a 2D Matrix II
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

class Solution
{
  boolean searchRow(int[][] M , int rowId, int target)
  {
    int s = 0;
    int e = M[0].length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(target < M[rowId][mid])
      {
        e = mid-1;
      }
      else if(target > M[rowId][mid])
      {
        s = mid+1;
      }
      else
      {
        return true;
      }
    }
    return false;
  }

  public boolean searchMatrix(int[][] M, int target)
  {
    if(M == null || M.length == 0)
    {
      return false;
    }
    for(int i=0;i<M.length;i++)
    {
      if(searchRow(M,i, target))
      {
        return true;
      }
    }
    return false;
  }
}

242. Valid Anagram
Given two strings s and t , write a function to determine if t is an anagram of s.
Note: You may assume the string contains only lowercase alphabets.
Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?

class Solution
{
  public boolean isAnagram(String s, String t)
  {
    if (s == null && t == null)
    {
      return true;
    }
    if(s == null || t == null || s.length() != t.length())
    {
      return false;
    }
    char[] chS = s.toCharArray();
    Arrays.sort(chS);
    char[] chT = t.toCharArray();
    Arrays.sort(chT);
    return String.valueOf(chS).equals(String.valueOf(chT));
  }
}

257. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.

public class Solution
{
  List<String> out;

  void paths(TreeNode curr, String pathSoFar)
  {
    if(curr.left == null && curr.right == null)
    {
      // leaf node
      if(pathSoFar.length() == 0)
      {
        out.add(Integer.toString(curr.val));
      }
      else
      {
        out.add(pathSoFar + "->" + curr.val);
      }
      return;
    }
    if(curr.left != null)
    {
      if(pathSoFar.length() == 0)
      {
        paths(curr.left, Integer.toString(curr.val));
      }
      else
      {
        paths(curr.left, pathSoFar + "->" + curr.val);
      }
    }
    if(curr.right != null)
    {
      if(pathSoFar.length() == 0)
      {
        paths(curr.right, Integer.toString(curr.val));
      }
      else
      {
        paths(curr.right, pathSoFar + "->" + curr.val);
      }
    }
  }

  public List<String> binaryTreePaths(TreeNode root)
  {
    out = new ArrayList<String>();
    if(root == null)
    {
      return out;
    }
    paths(root, "");
    return out;
  }
}

274. H-Index
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
Note: If there are several possible values for h, the maximum one is taken as the h-index.

class Solution
{
  public int hIndex(int[] citations)
  {
    if(citations == null || citations.length == 0)
    {
      return 0;
    }
    Arrays.sort(citations);
    for(int i=citations.length-1;i>=0;i--)
    {
      if(citations.length-i > citations[i])
      {
        return citations.length-i-1;
      }
    }
    return citations.length;
  }
}

278. First Bad Version
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

/* The isBadVersion API is defined in the parent class VersionControl.
    boolean isBadVersion(int version); */

public class Solution extends VersionControl
{
  public int firstBadVersion(int n)
  {
    int s = 1;
    int e = n;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(isBadVersion(mid))
      {
        e = mid - 1;
      }
      else
      {
        s = mid + 1;
      }
    }
    return s;
  }
}

283. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note: You must do this in-place without making a copy of the array.
Minimize the total number of operations.

class Solution
{
  public void moveZeroes(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return;
    }
    int zeroCount = 0;
    for(int i=0;i<nums.length;i++)
    {
      nums[i-zeroCount] = nums[i];
      if(nums[i] == 0)
      {
        zeroCount++;
      }
    }
    for(int i=nums.length-zeroCount;i<nums.length;i++)
    {
      nums[i] = 0;
    }
    return;
  }
}

289. Game
According to the Wikipedia's article: "The Game , also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

public class Solution
{

  int computeNextState(int[][] X, int i, int j, int m, int n)
  {
    int neighbourCount=0;
    if(i>0) // East
    {
      neighbourCount = neighbourCount + X[i-1][j];
    }
    if(i!=m-1) // West
    {
      neighbourCount = neighbourCount + X[i+1][j];
    }
    if(j>0) // North
    {
      neighbourCount = neighbourCount + X[i][j-1];
    }
    if(j!=n-1) // South
    {
      neighbourCount = neighbourCount + X[i][j+1];
    }
    if(i>0 && j>0) // North-East
    {
      neighbourCount = neighbourCount + X[i-1][j-1];
    }
    if(i!=m-1 && j!=n-1) // South-West
    {
      neighbourCount = neighbourCount + X[i+1][j+1];
    }
    if(i>0 && j!=n-1) // North-West
    {
      neighbourCount = neighbourCount + X[i-1][j+1];
    }
    if(i!=m-1 && j>0) // South-East
    {
      neighbourCount = neighbourCount + X[i+1][j-1];
    }
    if(X[i][j] == 1) // live cell
    {
      if(neighbourCount<2)
      {
        return 0;
      }
      if(neighbourCount<=3)
      {
        return 1;
      }
      return 0;
    }
    else
    {
      if(neighbourCount == 3)
      {
        return 1;
      }
      return 0;
    }
  }

  public void gameOfLife(int[][] A)
  {
    if(A == null || A.length == 0)
    {
      return;
    }
    int m = A.length;
    int n = A[0].length;
    int[][] B = new int[m][n];
    for(int i=0;i<m;i++)
    {
      for(int j=0;j<n;j++)
      {
        B[i][j] = computeNextState(A,i,j,m,n);
      }
    }
    for(int i=0;i<m;i++)
    {
      for(int j=0;j<n;j++)
      {
        A[i][j] = B[i][j];
      }
    }
  }
}

290. Word Pattern
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.

public class Solution
{
  public boolean wordPattern(String pattern, String str)
  {
    String[] tokens = str.split("\\s");
    if(pattern.length() != tokens.length)
    {
      return false;
    }
    HashMap<Character,String> m1 = new HashMap<>();
    HashMap<String,Character> m2 = new HashMap<>();
    for(int i=0;i<pattern.length();i++)
    {
      if(m1.get(pattern.charAt(i)) == null)
      {
        m1.put(pattern.charAt(i),tokens[i]);
        if(m2.put(tokens[i],pattern.charAt(i)) != null)
        {
          return false;
        }
      }
      else if(!m1.get(pattern.charAt(i)).equals(tokens[i]))
      {
        return false;
      }
    }
    return true;
  }
}

300. Longest Increasing Subsequence
Given an unsorted array of integers, find the length of longest increasing subsequence.
Note: There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

class Solution
{
  public int lengthOfLIS(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    int[] lisEndingAt = new int[nums.length];
    Arrays.fill(lisEndingAt, 1);
    int maxLength = 1;
    for(int i=1;i<nums.length;i++)
    {
      for(int j=0;j<i;j++)
      {
        if(nums[j] < nums[i])
        {
          lisEndingAt[i] = Math.max(lisEndingAt[i], lisEndingAt[j] + 1);
        }
      }
      maxLength = Math.max(maxLength, lisEndingAt[i]);
    }
    return maxLength;
  }
}

326. Power of Three
Given an integer, write a function to determine if it is a power of three.

class Solution
{
  public boolean isPowerOfThree(int n)
  {
    if(n < 1)
    {
      return false;
    }
    while(n % 3 == 0)
    {
      n = n/3;
    }
    return n == 1;
  }
}

328. Odd Even Linked List
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
Note: The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...

class Solution
{
  public ListNode oddEvenList(ListNode head)
  {
    if(head == null || head.next == null)
    {
      return head;
    }
    ListNode evenListHead = head.next;
    ListNode currOdd = head;
    ListNode currEven = head.next;
    ListNode curr = currEven.next;
    int index = 1;
    while(curr != null)
    {
      if(index % 2 == 0)
      {
        currEven.next = curr;
        currEven = currEven.next;
      }
      else
      {
        currOdd.next = curr;
        currOdd = currOdd.next;
      }
      index++;
      curr = curr.next;
    }
    currOdd.next = evenListHead;
    currEven.next = null;
    return head;
  }
}

344. Reverse String
Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.

class Solution
{
  public void reverseString(char[] in)
  {
    if(in == null)
    {
      return;
    }
    int s = 0;
    int e = in.length-1;
    while(s<e)
    {
      char temp = in[s];
      in[s] = in[e];
      in[e] = temp;
      s++;
      e--;
    }
    return;
  }
}

347. Top K Frequent Elements
Given a non-empty array of integers, return the k most frequent elements.

Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

public class Pair
{
  int num;
  int count;
  Pair(int num, int count)
  {
    this.num = num;
    this.count = count;
  }
}

class Solution
{
  public List<Integer> topKFrequent(int[] nums, int k)
  {
    Map<Integer, Integer> numCount = new HashMap<>();
    for(int i=0;i<nums.length;i++)
    {
      Integer value = numCount.get(nums[i]);
      if(value == null)
      {
        numCount.put(nums[i], 1);
      }
      else
      {
        numCount.put(nums[i], value+1);
      }
    }
    Queue<Pair> pq = new PriorityQueue<>((a,b) -> a.count-b.count);
    for(Map.Entry<Integer, Integer> e : numCount.entrySet())
    {
      if(pq.size() < k)
      {
        pq.add(new Pair(e.getKey(), e.getValue()));
      }
      else
      {
        Pair min = pq.peek();
        if(e.getValue() > min.count)
        {
          pq.poll();
          pq.add(new Pair(e.getKey(), e.getValue()));
        }
      }
    }
    List<Integer> out = new ArrayList<>();
    for(Pair p : pq)
    {
      out.add(p.num);
    }
    return out;
  }
}

380. Insert Delete GetRandom O(1)
Design a data structure that supports all following operations in average O(1) time.
insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

public class RandomizedSet
{

  /** Initialize your data structure here. */
  Map<Integer, Integer> map; // <key, value> : <element value, index in list>
  List<Integer> list; // current valid entries - used to return a random entry
  public RandomizedSet()
  {
    map = new HashMap<>();
    list = new ArrayList<>();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val)
  {
    Integer indexInList = map.get(val);
    if(indexInList != null)
    {
      // value already present. Don't do anything
      return false;
    }
    map.put(val, list.size()); // add <element value, index in list>
    list.add(val); // append the element to the list. Happens at index list.size()
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val)
  {
    Integer indexInList = map.get(val); // get the index of the element in the list
    if(indexInList == null)
    {
      // value not present. Don't do anything;
      return false;
    }
    int valueAtLastIndex = list.get(list.size()-1); // get the last element from the list
    list.set(indexInList, valueAtLastIndex); // copy the last element to the location of the element to be removed
    list.remove(list.size()-1); // remove the extra copy of the last element at the tail
    map.put(valueAtLastIndex, indexInList); //update the index of the last element to its new position (index of the removed element)
    map.remove(val); // finally remove the element from the map
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom()
  {
    return list.get((int) (list.size() * Math.random())); // return a random element from the active list
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

384. Shuffle an Array
Shuffle a set of numbers without duplicates.

class Solution
{

  int[] orig;
  int[] shuffled;
  List<Integer> bag;
  public Solution(int[] nums)
  {
    orig = new int[nums.length];
    shuffled = new int[nums.length];
    bag = new LinkedList<>();
    for(int i=0;i<nums.length;i++)
    {
      orig[i] = nums[i];
    }
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset()
  {
    return orig;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle()
  {
    for(int i=0;i<orig.length;i++)
    {
      bag.add(orig[i]);
    }
    Random r = new Random();
    for(int i=0;i<orig.length;i++)
    {
      int index = r.nextInt(bag.size());
      shuffled[i] = bag.remove(index);
    }
    return shuffled;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

387. First Unique Character in a String
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Note: You may assume the string contain only lowercase letters.

class Solution
{
  public int firstUniqChar(String s)
  {
    if(s == null || s.length() == 0)
    {
      return -1;
    }
    Set<Character> dupSet = new HashSet<>();
    Set<Character> uniqueSet = new HashSet<>();
    for(int i=0;i<s.length();i++)
    {
      char ch = s.charAt(i);
      if(uniqueSet.contains(ch))
      {
        uniqueSet.remove(ch);
        dupSet.add(ch);
      }
      else if(!dupSet.contains(ch))
      {
        uniqueSet.add(ch);
      }
    }
    for(int i=0;i<s.length();i++)
    {
      if(uniqueSet.contains(s.charAt(i)))
      {
        return i;
      }
    }
    return -1;
  }
}

622. Design Circular Queue
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
Your implementation should support following operations:
MyCircularQueue(k): Constructor, set the size of the queue to be k.
Front: Get the front item from the queue. If the queue is empty, return -1.
Rear: Get the last item from the queue. If the queue is empty, return -1.
enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
isEmpty(): Checks whether the circular queue is empty or not.
isFull(): Checks whether the circular queue is full or not.
Note: All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Queue library.

class MyCircularQueue
{
  int size;
  int[] buffer;
  int tail;// add here
  int head;// remove here
  int count;

  /** Initialize your data structure here. Set the size of the queue to be k. */
  public MyCircularQueue(int k)
  {
    this.size = k;
    this.buffer = new int[k];
    this.tail = 0;
    this.head = 0;
    this.count = 0;
  }

  /** Insert an element into the circular queue. Return true if the operation is successful. */
  public boolean enQueue(int value)
  {
    if(isFull())
    {
      return false;
    }
    buffer[tail] = value;
    tail = (tail+1)%size;
    count++;
    return true;
  }

  /** Delete an element from the circular queue. Return true if the operation is successful. */
  public boolean deQueue()
  {
    if(isEmpty())
    {
      return false;
    }
    head = (head+1)%size;
    count--;
    return true;
  }

  /** Get the front item from the queue. */
  public int Front()
  {
    if(isEmpty())
    {
      return -1;
    }
    return buffer[head];
  }

  /** Get the last item from the queue. */
  public int Rear()
  {
    if(isEmpty())
    {
      return -1;
    }
    if(tail == 0)
    {
      return buffer[size-1];
    }
    return buffer[(tail-1)%size];
  }

  /** Checks whether the circular queue is empty or not. */
  public boolean isEmpty()
  {
    return count == 0;
  }

  /** Checks whether the circular queue is full or not. */
  public boolean isFull()
  {
    return count == size;
  }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

641. Design Circular Deque
Design your implementation of the circular double-ended queue (deque).
Your implementation should support following operations:
MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not.
isFull(): Checks whether Deque is full or not.

class MyCircularDeque
{
  int[] buffer;
  int head;
  int tail;
  int count;
  int size;
  /** Initialize your data structure here. Set the size of the deque to be k. */
  public MyCircularDeque(int k)
  {
    size = k;
    buffer = new int[size];
    head = 0; // points to the insert location
    tail = 0; // point to the last element
    count = 0;
  }

  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  public boolean insertFront(int value)
  {
    if(isFull())
    {
      return false;
    }
    buffer[head] = value;
    head = (head+1)%size;
    count++;
    return true;
  }

  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  public boolean insertLast(int value)
  {
    if(isFull())
    {
      return false;
    }
    int insertIndex=0;
    if(tail==0)
    {
      insertIndex = size-1;
    }
    else
    {
      insertIndex = tail-1;
    }
    buffer[insertIndex] = value;
    tail = insertIndex;
    count++;
    return true;
  }

  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  public boolean deleteFront()
  {
    if(isEmpty())
    {
      return false;
    }
    if(head == 0)
    {
      head = size-1;
    }
    else
    {
      head--;
    }
    count--;
    return true;
  }

  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  public boolean deleteLast()
  {
    if(isEmpty())
    {
      return false;
    }
    tail = (tail+1)%size;
    count--;
    return true;
  }

  /** Get the front item from the deque. */
  public int getFront()
  {
    if(isEmpty())
    {
      return -1;
    }
    if(head == 0)
    {
      return buffer[size-1];
    }
    else
    {
      return buffer[head-1];
    }
  }

  /** Get the last item from the deque. */
  public int getRear()
  {
    if(isEmpty())
    {
      return -1;
    }
    return buffer[tail];
  }

  /** Checks whether the circular deque is empty or not. */
  public boolean isEmpty()
  {
    return count == 0;
  }

  /** Checks whether the circular deque is full or not. */
  public boolean isFull()
  {
    return count == size;
  }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
