65. Valid Number
Validate if a given string can be interpreted as a decimal number.
Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

#define START  0
#define SIGN   1
#define INTEGER  2
#define GOTDOT   3
#define DECIMAL  4
#define GOTE   5
#define EXPONENT 6
#define TRAIL  7
#define GOTDOT_AFTER_INT 8
#define EXP_SIGN 9


bool isNumber(char* s) {
  char *p = s;
  int state = START;
  do {
    switch (state) {
      case START:
        if (*p == ' ' || *p == '\t') { // catch other white spaces
          state = START;
        } else {
          if (*p == '-' || *p == '+') {
            state = SIGN;
          } else {
            if (*p >= '0' && *p <= '9') {
              state = INTEGER;
            } else {
              if (*p == '.') {
                state = GOTDOT;
              } else {
                return false;
              }
            }
          }
        }
        break;
      case SIGN:
        if (*p >= '0' && *p <= '9') {
          state = INTEGER;
        } else {
          if (*p == '.') {
            state = GOTDOT;
          } else {
            return false;
          }
        }
        break;
      case INTEGER:
        if (*p >= '0' && *p <= '9') {
          state = INTEGER;
        } else {
          if (*p == '.') {
            state = GOTDOT_AFTER_INT;
          } else {
            if (*p == 'e' || *p == 'E') {
              state = GOTE;
            } else {
              if (*p == ' ' || *p == '\t') { // catch other white spaces
                state = TRAIL;
              } else {
                return false;
              }
            }
          }
        }
        break;
      case GOTDOT:
        if (*p >= '0' && *p <= '9') {
          state = DECIMAL;
        } else {
          return false;
        }
        break;
      case GOTDOT_AFTER_INT:
        if (*p >= '0' && *p <= '9') {
          state = DECIMAL;
        } else {
          if (*p == 'e' || *p == 'E') {
            state = GOTE;
          } else {
            if (*p == ' ' || *p == '\t') { // catch other white spaces
              state = TRAIL;
            } else {
              return false;
            }
          }
        }
        break;
      case DECIMAL:
        if (*p >= '0' && *p <= '9') {
          state = DECIMAL;
        } else {
          if (*p == 'e' || *p == 'E') {
            state = GOTE;
          } else {
            if (*p == ' ' || *p == '\t') { // catch other white spaces
              state = TRAIL;
            } else {
              return false;
            }
          }
        }
        break;
      case GOTE:
        if (*p >= '0' && *p <= '9') {
          state = EXPONENT;
        } else {
          if (*p == '-' || *p == '+') {
            state = EXP_SIGN;
          } else {
            return false;
          }
        }
        break;
      case EXP_SIGN:
        if (*p >= '0' && *p <= '9') {
          state = EXPONENT;
        } else {
          return false;
        }
      case EXPONENT:
        if (*p >= '0' && *p <= '9') {
          state = EXPONENT;
        } else {
          if (*p == ' ' || *p == '\t') { // catch other white spaces
            state = TRAIL;
          } else {
            return false;
          }
        }
        break;
      case TRAIL:
        if (*p == ' ' || *p == '\t') { // catch other white spaces
          state = TRAIL;
        } else {
          return false;
        }
        break;
      default:
        return false;
    }
    p = p + 1;
  } while (*p != '\0');

  if (state == INTEGER || state == GOTDOT_AFTER_INT || state == DECIMAL || state == EXPONENT || state == TRAIL) {
    return true;
  } else {
    return false;
  }
}

66. Plus One
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.

class Solution
{
  public int[] plusOne(int[] digits)
  {
    int l = digits.length;
    int[] out = new int[l+1];
    int carry = 1;
    for(int i=digits.length-1;i>=0;i--)
    {
      int sum = digits[i] + carry;
      if(sum > 9)
      {
        out[i+1] = sum%10;
      }
      else
      {
        carry = 0;
        out[i+1] = sum;
      }
    }
    if(carry == 1)
    {
      out[0] = carry;
      return out;
    }
    else
    {
      return Arrays.copyOfRange(out, 1, l+1);
    }
  }
}

67. Add Binary
Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contains only characters 1 or 0.

public class Solution
{
  public String addBinary(String a, String b)
  {
    if(a == null || a.length() == 0)
    {
      return b;
    }
    if(b == null || b.length() == 0)
    {
      return a;
    }
    int aLen = a.length();
    int bLen = b.length();
    int n = Math.max(aLen, bLen) + 1;
    int[] out = new int[n];
    int carry=0;
    int aIndex = aLen-1;
    int bIndex = bLen-1;
    int outIndex = n-1;
    while(aIndex >=0 && bIndex >=0)
    {
      int sum = Character.getNumericValue(a.charAt(aIndex)) +
            Character.getNumericValue(b.charAt(bIndex)) +
            carry;
      if(sum == 0)
      {
        out[outIndex] = 0;
        carry = 0;
      }
      else if(sum == 1)
      {
        out[outIndex] = 1;
        carry = 0;
      }
      else if(sum == 2)
      {
        out[outIndex] = 0;
        carry = 1;
      }
      else
      {
        out[outIndex] = 1;
        carry = 1;
      }
      //System.out.println(sum + " " + out[outIndex] + " " + aIndex + " " + bIndex);
      aIndex--;
      bIndex--;
      outIndex--;
    }
    while(aIndex >=0)
    {
      int sum = Character.getNumericValue(a.charAt(aIndex)) +
            carry;
      if(sum == 0)
      {
        out[outIndex] = 0;
        carry = 0;
      }
      else if(sum == 1)
      {
        out[outIndex] = 1;
        carry = 0;
      }
      else if(sum == 2)
      {
        out[outIndex] = 0;
        carry = 1;
      }
      else
      {
        out[outIndex] = 1;
        carry = 1;
      }
      aIndex--;
      outIndex--;
    }
    while(bIndex >=0)
    {
      int sum = Character.getNumericValue(b.charAt(bIndex)) +
            carry;
      if(sum == 0)
      {
        out[outIndex] = 0;
        carry = 0;
      }
      else if(sum == 1)
      {
        out[outIndex] = 1;
        carry = 0;
      }
      else if(sum == 2)
      {
        out[outIndex] = 0;
        carry = 1;
      }
      else
      {
        out[outIndex] = 1;
        carry = 1;
      }
      bIndex--;
      outIndex--;
    }
    out[outIndex] = carry;
    String outString = "";
    int i=0;
    while(i<n)
    {
      if(out[i] == 0)
      {
        i++;
      }
      else
      {
        break;
      }
    }
    for(;i<n;i++)
    {
       outString = outString + out[i];
    }
    if(outString.length() ==0 )
    {
      return "0";
    }
    return outString;
  }
}

69. Sqrt(x)
Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

class Solution
{
  public int mySqrt(int x)
  {
    long s = 1;
    long e = x;
    while(s<=e)
    {
      long mid = s + (e-s)/2;
      long midSquare = mid*mid;
      if(midSquare > x)
      {
        e = mid-1;
      }
      else if (midSquare < x)
      {
        s = mid+1;
      }
      else
      {
        return (int)mid;
      }
    }
    return (int)e;
  }
}

70. Climbing Stairs
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Note: Given n will be a positive integer.

class Solution
{
  public int climbStairs(int n)
  {
    if(n < 3)
    {
      return n;
    }
    int step1 = 1;
    int step2 = 2;
    int out = 0;
    for(int i=3;i<=n;i++)
    {
      out = step1+ step2;
      step1 = step2;
      step2 = out;
    }
    return out;
  }
}

71. Simplify Path
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

public class Solution
{
  public String simplifyPath(String path)
  {
    Set<String> stringsToIgnore = new HashSet<String>();
    Stack<String> stack = new Stack<>();
    stringsToIgnore.add(".");
    stringsToIgnore.add("..");
    stringsToIgnore.add("");
    for(String s : path.split("/"))
    {
      if(s.equals(".."))
      {
        if(!stack.isEmpty())
        {
          stack.pop();
        }
      }
      if(!stringsToIgnore.contains(s))
      {
        stack.push(s);
      }
    }
    String out = "";
    if(stack.isEmpty())
    {
      return "/";
    }
    while(!stack.isEmpty())
    {
      out = "/" + stack.pop() + out;
    }
    return out;

  }
}

72. Edit Distance
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character

public class Solution
{
  public int minDistance(String word1, String word2)
  {
    int l1=word1.length();
    int l2=word2.length();
    int[][] out = new int[l1+1][l2+1];
    for(int i=1;i<=l1;i++)
    {
      out[i][0] = i;
    }
    for(int i=1;i<=l2;i++)
    {
      out[0][i] = i;
    }
    for(int i=1;i<=l1;i++)
    {
      for(int j=1;j<=l2;j++)
      {
        if(word1.charAt(i-1) == word2.charAt(j-1))
        {
          out[i][j] = out[i-1][j-1];
        }
        else
        {
          out[i][j] = 1+Math.min(Math.min(out[i-1][j],out[i][j-1]),out[i-1][j-1]);
        }
      }
    }
    return out[l1][l2];
  }
}

73. Set Matrix Zeroes
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

class Solution
{
  public void setZeroes(int[][] A)
  {
    if(A == null || A.length == 0)
    {
      return;
    }

    int rows = A.length;
    int cols = A[0].length;
    int sentinal = Integer.MIN_VALUE + 1;

    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(A[i][j] == 0)
        {
          for(int k=0;k<cols;k++)
          {
            if(A[i][k] != 0)
            {
              A[i][k] = sentinal;
            }
          }
          break;
        }
      }
    }
    for(int i=0;i<cols;i++)
    {
      for(int j=0;j<rows;j++)
      {
        if(A[j][i] == 0)
        {
          for(int k=0;k<rows;k++)
          {
            if(A[k][i] != 0)
            {
              A[k][i] = sentinal;
            }
          }
          break;
        }
      }
    }
    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(A[i][j] == sentinal)
        {
          A[i][j] = 0;
        }
      }
    }
    return;
  }
}

74. Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

class Solution
{
  public boolean searchMatrix(int[][] M, int target)
  {
    if(M == null || M.length == 0)
    {
      return false;
    }
    int rows = M.length;
    int cols = M[0].length;
    int numOfElements = rows*cols;
    int s = 0;
    int e = numOfElements-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      int rowId = mid / cols;
      int colId = mid % cols;
      if(target < M[rowId][colId])
      {
        e = mid-1;
      }
      else if(target > M[rowId][colId])
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
}

75. Sort Colors
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.
Follow up: A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

public class Solution
{
  public void swap(int[] nums, int i, int j)
  {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    return;
  }

  public void sortColors(int[] nums)
  {
    int zeroIndex=0;
    int twoIndex=nums.length-1;
    int i=0;
    while(i<=twoIndex)
    {
      if(nums[i] == 0)
      {
        swap(nums,zeroIndex,i);
        zeroIndex++;
        i++;
      }
      else if(nums[i] == 2)
      {
        swap(nums,twoIndex,i);
        twoIndex--;
      }
      else
      {
        i++;
      }
    }
  }
}

77. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

class Solution
{
  List<List<Integer>> out;

  void combination(List<Integer> prefix, List<Integer> in, int k)
  {
    if(prefix.size() == k)
    {
      out.add(prefix);
      return;
    }
    if(in.size() == 0)
    {
      return;
    }
    List<Integer> newPrefix = new ArrayList<>();
    List<Integer> newIn = new ArrayList<>();

    newPrefix.addAll(prefix);
    newPrefix.add(in.get(0));

    newIn.addAll(in);
    newIn.remove(in.get(0));

    combination(newPrefix, newIn, k);
    combination(prefix, newIn, k);

    return;
  }

  public List<List<Integer>> combine(int n, int k)
  {
    out = new ArrayList<>();
    List<Integer> in = new ArrayList<>();
    for(int i=1;i<=n;i++)
    {
      in.add(i);
    }
    combination(new ArrayList<>(), in, k);
    return out;
  }
}

78. Subsets
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

class Solution
{
  List<List<Integer>> out;

  void powerSet(List<Integer> prefix, List<Integer> in)
  {
    if(in.size() == 0)
    {
      out.add(prefix);
      return;
    }
    List<Integer> newPrefix = new ArrayList<>();
    List<Integer> newIn = new ArrayList<>();

    newPrefix.addAll(prefix);
    newPrefix.add(in.get(0));

    newIn.addAll(in);
    newIn.remove(0);

    powerSet(prefix, newIn); //without i
    powerSet(newPrefix, newIn); // with i
    return;
  }

  public List<List<Integer>> subsets(int[] nums)
  {
    out = new ArrayList<>();
    if(nums == null || nums.length == 0)
    {
      return out;
    }
    List<Integer> in = new ArrayList<>();
    for(Integer i : nums)
    {
      in.add(i);
    }
    powerSet(new ArrayList<>(), in);
    return out;
  }
}

79. Word Search
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

class Solution
{
  boolean search(char[][] board, String word, int i, int j, int index, boolean[][] visited)
  {
    if(index == word.length())
    {
      return true;
    }
    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length
       || visited[i][j] == true || board[i][j] != word.charAt(index))
    {
      return false;
    }
    visited[i][j] = true;
    boolean found = search(board, word, i+1, j, index+1, visited)
      || search(board, word, i-1, j, index+1, visited)
      || search(board, word, i, j+1, index+1, visited)
      || search(board, word, i, j-1, index+1, visited);
    visited[i][j] = false;
    return found;
  }

  public boolean exist(char[][] board, String word)
  {
    if(board == null || board.length == 0 || word == null || word.length() == 0)
    {
      return false;
    }
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    for(int i=0;i<rows;i++)
    {
      for(int j=0;j<cols;j++)
      {
        if(search(board, word, i, j, 0, visited))
        {
          return true;
        }
      }
    }
    return false;
  }
}

80. Remove Duplicates from Sorted Array II
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

class Solution
{
  public int removeDuplicates(int[] nums)
  {
    if(nums == null || nums.length == 0)
    {
      return 0;
    }
    int k=2;
    if(nums.length<k)
    {
      return nums.length;
    }
    int dupCount = 0;
    for(int i=0;i<nums.length-k;i++)
    {
      nums[i-dupCount] = nums[i];
      if(nums[i] == nums[i+k])
      {
        dupCount++;
      }
    }
    for(int i=nums.length-k;i<nums.length;i++)
    {
      nums[i-dupCount] = nums[i];
    }
    return nums.length - dupCount;
  }
}

81. Search in Rotated Sorted Array II
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.

class Solution
{
  public boolean search(int[] nums, int target)
  {
    if(nums == null || nums.length == 0)
    {
      return false;
    }
    int s=0;
    int e = nums.length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      while(s < e && mid > s && nums[s] == nums[mid] && target != nums[mid])
      {
        s++;
        mid--;
      }
      if(target == nums[mid])
      {
        return true;
      }
      if(nums[s] <= nums[mid])
      {
        if(target >= nums[s] && target <= nums[mid])
        {
          e = mid;
        }
        else
        {
          s = mid+1;
        }
      }
      else
      {
        while(mid < e && e > s && nums[mid] == nums[e] && target != nums[mid])
        {
          mid++;
          e--;
        }
        if(target >= nums[mid] && target <= nums[e])
        {
          s = mid;
        }
        else
        {
          e = mid-1;
        }
      }
    }
    return false;
  }
}
