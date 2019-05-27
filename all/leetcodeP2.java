20. Valid Parentheses
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

class Solution
{
  public boolean isValid(String s)
  {
    Map<Character, Character> allowedValues = new HashMap<>();
    allowedValues.put('{','}');
    allowedValues.put('[',']');
    allowedValues.put('(',')');
    Stack<Character> stack = new Stack<>();
    for(int i=0;i<s.length();i++)
    {
      Character ch = s.charAt(i);
      if(allowedValues.get(ch) != null)
      {
        stack.push(ch);
      }
      else if(stack.isEmpty() || allowedValues.get(stack.pop()) != ch)
      {
        return false;
      }
    }
    return stack.isEmpty();
  }
}

21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

class Solution
{
  public ListNode mergeTwoLists(ListNode l1, ListNode l2)
  {
    ListNode sentinal = new ListNode(0);
    ListNode curr = sentinal;
    while(l1 != null && l2 != null)
    {
      if(l1.val <l2.val)
      {
        curr.next = l1;
        l1 = l1.next;
      }
      else
      {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }
    if(l1 == null)
    {
      curr.next = l2;
    }
    else
    {
      curr.next = l1;
    }
    return sentinal.next;
  }
}

23. Merge k Sorted Lists
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

class Solution
{
  public ListNode mergeKLists(ListNode[] lists)
  {
    ListNode sentinal = new ListNode(0);
    if(lists == null || lists.length < 1)
    {
      return null;
    }
    ListNode curr = sentinal;
    Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
    for(ListNode l : lists)
    {
      if(l != null)
      {
        minHeap.add(l);
      }
    }
    while(!minHeap.isEmpty())
    {
      ListNode currMin = minHeap.poll();
      curr.next = currMin;
      curr = curr.next;
      if(currMin.next != null)
      {
        minHeap.add(currMin.next);
      }
    }
    return sentinal.next;
  }
}

26. Remove Duplicates from Sorted Array
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

class Solution
{
  public int removeDuplicates(int[] nums)
  {
    if(nums == null || nums.length < 1)
    {
      return 0;
    }
    int dupCount = 0;
    for(int i=1;i<nums.length;i++)
    {
      if(nums[i-1] == nums[i])
      {
        dupCount++;
      }
      nums[i-dupCount] = nums[i];
    }
    return nums.length-dupCount;
  }
}

27. Remove Element
Given an array nums and a value val, remove all instances of that value in-place and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.

class Solution
{
  public int removeElement(int[] nums, int val)
  {
    if(nums == null || nums.length < 1)
    {
      return 0;
    }
    int valCount=0;
    for(int i=0;i<nums.length;i++)
    {
      nums[i-valCount] = nums[i];
      if(nums[i] == val)
      {
        valCount++;
      }
    }
    return nums.length-valCount;
  }
}

28. Implement strStr()
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
Clarification: For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

public class Solution
{
  public int strStr(String haystack, String needle)
  {
    int m = haystack.length();
    int n = needle.length();
    if(n == 0)
    {
      return 0;
    }
    for(int i=0;i<=m-n;i++)
    {
      if(haystack.substring(i,i+n).equals(needle))
      {
        return i;
      }
    }
    return -1;
  }
}

32. Longest Valid Parentheses
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

class Solution
{
  public int longestValidParentheses(String s)
  {
    if(s == null || s.length() == 0)
    {
      return 0;
    }
    int l = s.length();
    boolean[] valid = new boolean[l];
    Stack<Integer> stack = new Stack<>();
    for(int i=0;i<l;i++)
    {
      if(s.charAt(i) == '(')
      {
        stack.push(i);
      }
      else if(!stack.isEmpty())
      {
        valid[stack.pop()] = true;
        valid[i] = true;
      }
    }
    int maxLength = 0;
    int maxLengthEndingAtI = valid[0] ? 1: 0;
    for(int i=1;i<l;i++)
    {
      if(valid[i])
      {
        maxLengthEndingAtI++;
      }
      else
      {
        maxLengthEndingAtI = 0;
      }
      maxLength = Math.max(maxLength, maxLengthEndingAtI);
    }
    return maxLength;
  }
}

33. Search in Rotated Sorted Array
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

class Solution
{
  public int search(int[] nums, int target)
  {
    if(nums == null || nums.length == 0)
    {
      return -1;
    }
    int s = 0;
    int e = nums.length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(target == nums[mid])
      {
        return mid;
      }
      if(nums[s] <= nums[mid]) // left is sorted
      {
        if(target >= nums[s] && target <= nums[mid])
        {
          // target falls in range
          e = mid;
        }
        else
        {
          s = mid+1;
        }
      }
      else if (nums[mid] <= nums[e]) // right is sorted
      {
        if(target >= nums[mid] && target <= nums[e])
        {
          //target falls in range
          s = mid;
        }
        else
        {
          e = mid-1;
        }
      }
      else
      {
        return -1;
      }
    }
    return -1;
  }
}

34. Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

class Solution
{
  int firstOccuranceIndex(int[] nums, long target)
  {
    int s=0;
    int e=nums.length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(target <= nums[mid])
      {
        e = mid-1;
      }
      else
      {
        s = mid+1;
      }
    }
    return s;
  }

  public int[] searchRange(int[] nums, int target)
  {
    int[] out = {-1, -1};
    if(nums == null || nums.length == 0)
    {
      return out;
    }
    int s = firstOccuranceIndex(nums, target);
    if(s >= nums.length || nums[s] != target)
    {
      return out;
    }
    out[0] = s;
    out[1] = firstOccuranceIndex(nums, (long)target+1)-1;
    return out;
  }
}

35. Search Insert Position
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.

class Solution
{
  public int searchInsert(int[] nums, int target)
  {
    if(nums == null || nums.length < 1)
    {
      return 0;
    }
    int s = 0;
    int e = nums.length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(target < nums[mid])
      {
        e = mid-1;
      }
      else if (target > nums[mid])
      {
        s = mid+1;
      }
      else
      {
        return mid;
      }
    }
    return s;
  }
}

36. Valid Sudoku
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
A partially filled sudoku which is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

class Solution
{
  boolean check(char[][] board, int i, int j, Set<Integer> filled)
  {
    if(board[i][j] == '.')
    {
      return true;
    }
    int currValue = Character.getNumericValue(board[i][j]);
    if(currValue < 1 || currValue > 9 || filled.contains(currValue))
    {
      return false;
    }
    filled.add(currValue);
    return true;
  }

  boolean validateRows(char[][] board, int rows, int cols)
  {
    for(int i=0;i<rows;i++)
    {
      Set<Integer> filled = new HashSet<>();
      for(int j=0;j<cols;j++)
      {
        if(!check(board, i, j, filled))
        {
          return false;
        }
      }
    }
    return true;
  }

  boolean validateCols(char[][] board, int rows, int cols)
  {
    for(int i=0;i<cols;i++)
    {
      Set<Integer> filled = new HashSet<>();
      for(int j=0;j<rows;j++)
      {
        if(!check(board, j, i, filled))
        {
          return false;
        }
      }
    }
    return true;
  }

  boolean validateBoxes(char[][] board, int rows, int cols)
  {
    int n = (int) Math.sqrt(rows);
    int rowIndex = 0;
    while(rowIndex < rows)
    {
      int colIndex = 0;
      while(colIndex < cols)
      {
        Set<Integer> filled = new HashSet<>();
        for(int i=rowIndex;i<rowIndex+n;i++)
        {
          for(int j=colIndex;j<colIndex+n;j++)
          {
            if(!check(board, i, j, filled))
            {
              return false;
            }
          }
        }
        colIndex = colIndex + n;
      }
      rowIndex = rowIndex + n;
    }
    return true;
  }

  public boolean isValidSudoku(char[][] board)
  {
    if(board == null || board[0].length == 0)
    {
      return true;
    }
    int rows = board.length;
    int cols = board[0].length;
    return validateRows(board, rows, cols) && validateCols(board, rows, cols) && validateBoxes(board, rows, cols);
  }
}

37. Sudoku Solver
Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
Note:
The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

public class Solution
{
  public boolean solve(char[][] board)
  {
    int n = board.length;
    int[] unassignedLocation = findUnassignedLocation(board);
    if(unassignedLocation[0] == -1)
    {
      return true;
    }
    int row = unassignedLocation[0];
    int col = unassignedLocation[1];
    for(int i=0;i<n;i++)
    {
      if(isValidPlacement(board, row, col, i+1))
      {
        board[row][col] = Character.forDigit(i+1,n+1);
        if(solve(board))
        {
          return true;
        }
        else
        {
          board[row][col] = '.';
        }
      }
    }
    return false;
  }

  int[] findUnassignedLocation(char[][] board)
  {
    int[] location = new int[2];
    location[0] = -1;
    location[1] = -1;
    int n = board.length;
    assert(board[0].length == n);
    for(int i=0;i<n;i++)
    {
      for(int j=0;j<n;j++)
      {
        if(board[i][j] == '.')
        {
          location[0] = i;
          location[1] = j;
          return location;
        }
      }
    }
    return location;
  }

  boolean isValidPlacement(char[][] board, int row, int col, int val)
  {
    int n = board.length;
    char cVal = Character.forDigit(val, n+1);
    // verify row
    for(int i=0;i<n;i++)
    {
      if(board[row][i] == cVal)
      {
        return false;
      }
    }
    // verify col
    for(int i=0;i<n;i++)
    {
      if(board[i][col] == cVal)
      {
        return false;
      }
    }
    // verify grid
    int gridSize = (int) Math.sqrt(n);
    for(int i=0;i<gridSize;i++)
    {
      for(int j=0;j<gridSize;j++)
      {
        if(board[row-(row%gridSize)+i][col-(col%gridSize)+j] == cVal)
        {
          return false;
        }
      }
    }
    return true;
  }

  public void solveSudoku(char[][] board)
  {
    solve(board);
  }
}

41. First Missing Positive
Given an unsorted integer array, find the smallest missing positive integer.

class Solution
{
  public int firstMissingPositive(int[] A)
  {
    if(A == null || A.length == 0)
    {
      return 1;
    }
    Arrays.sort(A);
    int index = 0;
    int l = A.length;
    while(index < l && A[index] < 1)
    {
      index++;
    }
    int series = 1;
    while(index < l-1)
    {
      if(A[index] != series)
      {
        return series;
      }
      if(A[index] != A[index+1])
      {
        series++;
      }
      index++;
    }
    if(A[l-1] != series)
    {
      return series;
    }
    return series+1;
  }
}

46. Permutations
Given a collection of distinct integers, return all possible permutations.

class Solution
{
  List<List<Integer>> out;

  void permutation(List<Integer> prefix, List<Integer> in)
  {
    if(in.size() == 0)
    {
      out.add(prefix);
      return;
    }
    for(int i=0;i<in.size();i++)
    {
      List<Integer> newPrefix = new ArrayList<>();
      newPrefix.addAll(prefix);
      newPrefix.add(in.get(i));
      List<Integer> newIn = new ArrayList<>();
      newIn.addAll(in);
      newIn.remove(i);
      permutation(newPrefix, newIn);
    }
    return;
  }

  public List<List<Integer>> permute(int[] nums)
  {
    out = new ArrayList<>();
    List<Integer> prefix = new ArrayList<>();
    List<Integer> in = new ArrayList<>();
    for(int i=0;i<nums.length;i++)
    {
      in.add(nums[i]);
    }
    permutation(prefix, in);
    return out;
  }
}

47. Permutations II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

class Solution
{
  Set<List<Integer>> outSet;
  void permutation(List<Integer> prefix, List<Integer> in)
  {
    if(in.size() == 0)
    {
      outSet.add(prefix);
    }
    for(int i=0;i<in.size();i++)
    {
      List<Integer> newPrefix = new ArrayList<>();
      List<Integer> newIn = new ArrayList<>();

      newPrefix.addAll(prefix);
      newPrefix.add(in.get(i));

      newIn.addAll(in);
      newIn.remove(in.get(i));
      permutation(newPrefix, newIn);
    }
    return;
  }

  public List<List<Integer>> permuteUnique(int[] nums)
  {
    outSet = new HashSet<>();
    List<Integer> in = new ArrayList<>();
    for(int i : nums)
    {
      in.add(i);
    }
    permutation(new ArrayList<>(), in);
    List<List<Integer>> out = new ArrayList<>();
    for(List<Integer> l : outSet)
    {
      out.add(l);
    }
    return out;
  }
}
