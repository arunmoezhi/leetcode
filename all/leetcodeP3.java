48. Rotate Image
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
Note: You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

class Solution
{
  void transpose(int[][] M)
  {
    for(int i=0;i<M.length;i++)
    {
      for(int j=i;j<M[0].length;j++)
      {
        int temp = M[i][j];
        M[i][j] = M[j][i];
        M[j][i] = temp;
      }
    }
  }

  void rotateMirror(int[][] M)
  {
    for(int i=0;i<M.length;i++)
    {
      int s = 0;
      int e = M[0].length-1;
      while(s<e)
      {
        int temp = M[i][s];
        M[i][s] = M[i][e];
        M[i][e] = temp;
        s++;
        e--;
      }
    }
  }
  public void rotate(int[][] M)
  {
    if(M == null || M.length == 0)
    {
      return;
    }
    transpose(M);
    rotateMirror(M);
  }
}

49. Group Anagrams
Given an array of strings, group anagrams together.

class Solution
{
  public List<List<String>> groupAnagrams(String[] strs)
  {
    List<List<String>> out = new ArrayList<>();
    if(strs == null || strs.length == 0)
    {
      return out;
    }
    Map<String,List<String>> groups = new HashMap<>();
    for(String s : strs)
    {
      char[] chArray = s.toCharArray();
      Arrays.sort(chArray);
      String sortedString = String.valueOf(chArray);

      List<String> group = groups.get(sortedString);
      if(group == null)
      {
        group = new ArrayList<String>();
        groups.put(sortedString, group);
      }
      group.add(s);
    }
    for(Map.Entry<String, List<String>> e : groups.entrySet())
    {
      out.add(e.getValue());
    }
    return out;
  }
}

50. Pow(x, n)
Implement pow(x, n), which calculates x raised to the power n (x^n).

class Solution
{
  double pow(double x, int n)
  {
    if(n == 0)
    {
      return 1.0f;
    }

    double comp = pow(x, n/2);
    if(n%2 == 0)
    {
      return comp*comp;
    }
    else
    {
      return x*comp*comp;
    }
  }

  public double myPow(double x, int n)
  {
    if(x == 0 || x == 1)
    {
      return x;
    }
    double out = pow(Math.abs(x), Math.abs(n));
    double sign = 1.0f;
    if(x < 0 && n%2 == 1)
    {
      sign = -1.0f;
    }
    if (n < 0)
    {
      return sign/out;
    }
    else
    {
      return sign*out;
    }
  }
}

53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

class Solution
{
  public int maxSubArray(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    int maxSumSoFar = nums[0];
    int maxSumEndingHere = nums[0];
    for(int i=1;i<nums.length;i++)
    {
      maxSumEndingHere = Math.max(nums[i], maxSumEndingHere + nums[i]);
      maxSumSoFar = Math.max(maxSumSoFar, maxSumEndingHere);
    }
    return maxSumSoFar;
  }
}

54. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

class Solution
{
  List<Integer> out;

  void right(int[][] M, int row, int colS, int colE)
  {
    for(int i=colS;i<=colE;i++)
    {
      out.add(M[row][i]);
    }
  }

  void down(int[][] M, int col, int rowS, int rowE)
  {
    for(int i=rowS;i<=rowE;i++)
    {
      out.add(M[i][col]);
    }
  }

  void left(int[][] M, int row, int colS, int colE)
  {
    for(int i=colE;i>=colS;i--)
    {
      out.add(M[row][i]);
    }
  }

  void up(int[][] M, int col, int rowS, int rowE)
  {
    for(int i=rowE;i>=rowS;i--)
    {
      out.add(M[i][col]);
    }
  }

  public List<Integer> spiralOrder(int[][] M)
  {
    out = new ArrayList<>();
    if(M == null || M.length == 0)
    {
      return out;
    }
    int rowS = 0;
    int rowE = M.length-1;
    int colS = 0;
    int colE = M[0].length-1;
    while(rowS <= rowE && colS <= colE)
    {
      right(M, rowS, colS, colE);
      rowS++;
      if(rowS > rowE)
      {
        break;
      }
      down(M, colE, rowS, rowE);
      colE--;
      if(colS > colE)
      {
        break;
      }
      left(M, rowE, colS, colE);
      rowE--;
      if(rowS > rowE)
      {
        break;
      }
      up(M, colS, rowS, rowE);
      colS++;
      if(colS > colE)
      {
        break;
      }
    }
    return out;
  }
}

55. Jump Game
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

class Solution
{
  public boolean canJump(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return false;
    }
    if(nums.length == 1)
    {
      return true;
    }
    int needed = 1;
    for(int i=nums.length-2;i>0;i--)
    {
      if(nums[i] < needed)
      {
        needed++;
      }
      else
      {
        needed=1;
      }
    }
    return nums[0] >= needed;
  }
}

56. Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

/**
 * Definition for an interval.
 * public class Interval
 {
 *   int start;
 *   int end;
 *   Interval() { start = 0; end = 0; }
 *   Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution
{
  public List<Interval> merge(List<Interval> intervals)
  {
    List<Interval> out = new ArrayList<>();
    if(intervals == null || intervals.size() == 0)
    {
      return out;
    }

    PriorityQueue<Interval> sortedIntervals = new PriorityQueue<>((a,b)->a.start-b.start);
    for(Interval i : intervals)
    {
      sortedIntervals.add(i);
    }
    Interval curr = sortedIntervals.remove();
    Interval nextInterval = null;
    while(!sortedIntervals.isEmpty())
    {
      nextInterval = sortedIntervals.remove();
      if(curr.end < nextInterval.start)
      {
        out.add(curr);
        curr = nextInterval;
      }
      else
      {
        curr.end = Math.max(curr.end, nextInterval.end);
      }
    }
    if(nextInterval == null || curr.end >= nextInterval.start)
    {
      out.add(curr);
    }
    return out;
  }
}

57. Insert Interval
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.

class Solution
{
  public List<Interval> insert(List<Interval> intervals, Interval newInterval)
  {
    List<Interval> out = new ArrayList<>();
    if(intervals == null || intervals.size() == 0)
    {
      out.add(newInterval);
      return out;
    }
    int l = intervals.size();
    int index = 0;
    while(index<l && intervals.get(index).end < newInterval.start)
    {
      // add all the non-overlapping intervals to the left of newInterval to out
      out.add(intervals.get(index));
      index++;
    }
    while(index<l && intervals.get(index).end >= newInterval.start && intervals.get(index).start <= newInterval.end)
    {
      // merge all overlapping intervals
      newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
      newInterval.end   = Math.max(intervals.get(index).end, newInterval.end);
      index++;
    }
    out.add(newInterval);
    while(index<l)
    {
      out.add(intervals.get(index));
      index++;
    }
    return out;
  }
}

58. Length of Last Word
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
If the last word does not exist, return 0.
Note: A word is defined as a character sequence consists of non-space characters only.

class Solution
{
  public int lengthOfLastWord(String s)
  {
    if(s == null || s.length() ==0)
    {
      return 0;
    }

    int lastLetterIndex = s.length()-1;

    // trim trailing spaces
    while(lastLetterIndex >= 0)
    {
      if(s.charAt(lastLetterIndex) != ' ')
      {
        break;
      }
      lastLetterIndex--;
    }

    int firstLetterIndex = lastLetterIndex;
    // from the right find the first space after the first word
    while(firstLetterIndex >= 0)
    {
      if(s.charAt(firstLetterIndex) == ' ')
      {
        return lastLetterIndex - firstLetterIndex;
      }
      firstLetterIndex--;
    }
    return lastLetterIndex+1;
  }
}

59. Spiral Matrix II
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

class Solution
{
  int right(int[][] out, int row, int colS, int colE, int index)
  {
    for(int i=colS;i<=colE;i++)
    {
      out[row][i] = index++;
    }
    return index;
  }

  int down(int[][] out, int col, int rowS, int rowE, int index)
  {
    for(int i=rowS;i<=rowE;i++)
    {
      out[i][col] = index++;
    }
    return index;
  }

  int left(int[][] out, int row, int colS, int colE, int index)
  {
    for(int i=colE;i>=colS;i--)
    {
      out[row][i] = index++;
    }
    return index;
  }

  int up(int[][] out, int col, int rowS, int rowE, int index)
  {
    for(int i=rowE;i>=rowS;i--)
    {
      out[i][col] = index++;
    }
    return index;
  }

  public int[][] generateMatrix(int n)
  {
    if(n == 0)
    {
      return null;
    }
    int[][] out = new int[n][n];
    int rowS = 0;
    int rowE = n-1;
    int colS = 0;
    int colE = n-1;
    int index = 1;
    while(rowS <= rowE && colS <= colE)
    {
      index = right(out, rowS, colS, colE, index);
      rowS++;
      if(rowS > rowE)
      {
        break;
      }
      index = down(out, colE, rowS, rowE, index);
      colE--;
      if(colS > colE)
      {
        break;
      }
      index = left(out, rowE, colS, colE, index);
      rowE--;
      if(rowS > rowE)
      {
        break;
      }
      index = up(out, colS, rowS, rowE, index);
      colS++;
    }
    return out;
  }
}

60. Permutation Sequence
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
Note:
Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.

public class Solution
{
  //http://en.wikipedia.org/wiki/Factorial_number_system#Definition
  // http://en.wikipedia.org/wiki/Factorial_number_system#Permutations

  public String getPermutation(int n, int k)
  {
    k--;
    int i;
		int[] out = new int[n];
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		for(i=0;i<n;i++)
		{
			out[i]=0;
			permutation.add(i+1);
		}
		i=1;
		while(k!=0)
		{
			out[n-i]=k%i;
			k=k/i;
			i++;
		}
		String S="";
		i=0;
		while(!permutation.isEmpty())
		{
			S = S + permutation.remove(out[i++]);
		}
		return S;
  }
}

61. Rotate List
Given a linked list, rotate the list to the right by k places, where k is non-negative.

class Solution
{
  public ListNode rotateRight(ListNode head, int k)
  {
    if(head == null || head.next == null)
    {
      return head;
    }
    ListNode curr = head;
    int l=1;
    while(curr.next != null)
    {
      l++;
      curr = curr.next;
    }
    curr.next = head;

    k = k%l;
    curr = head;
    for(int i=0;i<l-k-1;i++)
    {
      curr = curr.next;
    }
    ListNode newHead = curr.next;
    curr.next = null;
    return newHead;
  }
}


62. Unique Paths
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

class Solution
{
  public int uniquePaths(int m, int n)
  {
    int[] prevRow = new int[n];
    for(int i=0;i<n;i++)
    {
      prevRow[i] = 1;
    }
    int currCell = 1;
    for(int i=1;i<m;i++)
    {
      int leftCell = 0;
      for(int j=0;j<n;j++)
      {
        currCell = leftCell + prevRow[j];
        leftCell = currCell;
        prevRow[j] = currCell;
      }
    }
    return currCell;
  }
}

63. Unique Paths II
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?

class Solution
{
  public int uniquePathsWithObstacles(int[][] obstacleGrid)
  {
    if(obstacleGrid == null || obstacleGrid.length == 0)
    {
      return 0;
    }
    int rows = obstacleGrid.length;
    int cols = obstacleGrid[0].length;
    int[][] ways = new int[rows][cols];
    for(int i=0;i<rows;i++)
    {
      if(obstacleGrid[i][0] == 0)
      {
        ways[i][0] = 1;
      }
      else
      {
        break;
      }
    }
    for(int i=0;i<cols;i++)
    {
      if(obstacleGrid[0][i] == 0)
      {
        ways[0][i] = 1;
      }
      else
      {
        break;
      }
    }
    for(int i=1;i<rows;i++)
    {
      for(int j=1;j<cols;j++)
      {
        if(obstacleGrid[i][j] == 0)
        {
          ways[i][j] = ways[i-1][j] + ways[i][j-1];
        }
      }
    }
    return ways[rows-1][cols-1];
  }
}

64. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

class Solution
{
  public int minPathSum(int[][] grid)
  {
    if(grid == null || grid.length == 0)
    {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    int[][] sum = new int[rows][cols];
    sum[0][0] = grid[0][0];
    for(int i=1;i<rows;i++)
    {
      sum[i][0] = sum[i-1][0] + grid[i][0];
    }
    for(int i=1;i<cols;i++)
    {
      sum[0][i] = sum[0][i-1] + grid[0][i];
    }
    for(int i=1;i<rows;i++)
    {
      for(int j=1;j<cols;j++)
      {
        sum[i][j] = Math.min(sum[i-1][j], sum[i][j-1]) + grid[i][j];
      }
    }
    return sum[rows-1][cols-1];
  }
}
