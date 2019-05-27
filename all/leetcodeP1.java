1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

class Solution
{
  public int[] twoSum(int[] nums, int target)
  {
    int[] out = {-1,-1};
    if(nums == null || nums.length < 2)
    {
      return out;
    }
    Map<Integer, Integer> existingElements = new HashMap<>();
    for(int i=0;i<nums.length;i++)
    {
      Integer firstNumberIndex = existingElements.get(target-nums[i]);
      if(firstNumberIndex == null)
      {
        existingElements.put(nums[i],i);
      }
      else
      {
        out[0] = firstNumberIndex;
        out[1] = i;
        return out;
      }
    }
    return out;
  }
}

2. Add Two Numbers
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   int val;
 *   ListNode next;
 *   ListNode(int x) { val = x; }
 * }
 */
class Solution
{
  public ListNode addTwoNumbers(ListNode l1, ListNode l2)
  {
    if(l1 == null)
    {
      return l2;
    }
    if(l2 == null)
    {
      return l1;
    }
    ListNode sentinal = new ListNode(0);
    ListNode curr = sentinal;
    int carry = 0;
    while(l1 != null && l2 != null)
    {
      int sum = l1.val + l2.val + carry;
      curr.next = new ListNode(sum%10);
      curr = curr.next;
      carry = sum/10;
      l1 = l1.next;
      l2 = l2.next;
    }
    while(l1 != null)
    {
      int sum = l1.val + carry;
      curr.next = new ListNode(sum%10);
      curr = curr.next;
      carry = sum/10;
      l1 = l1.next;
    }
    while(l2 != null)
    {
      int sum = l2.val + carry;
      curr.next = new ListNode(sum%10);
      curr = curr.next;
      carry = sum/10;
      l2 = l2.next;
    }
    if(carry > 0)
    {
      curr.next = new ListNode(carry);
    }
    return sentinal.next;
  }
}

3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

class Solution
{
  public int lengthOfLongestSubstring(String s)
  {
    if(s == null || s.length() == 0)
    {
      return 0;
    }
    Map<Character, Integer> wordMap = new HashMap<>();
    int maxLength = 0;
    int startOfWord = 0;
    for(int i=0;i<s.length();i++)
    {
      Integer duplicateCharIndex = wordMap.get(s.charAt(i));
      if(duplicateCharIndex != null && duplicateCharIndex >= startOfWord)
      {
        startOfWord = duplicateCharIndex+1;
      }
      maxLength = Math.max(maxLength, i-startOfWord+1);
      wordMap.put(s.charAt(i), i);
    }
    return maxLength;
  }
}

5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

public class Solution
{
  public String longestPalindrome(String s)
  {
    int n = s.length();
    boolean[][] longestPalindromeBetweenIandJ = new boolean[n][n];
    for(int i=0;i<n-1;i++)
    {
       longestPalindromeBetweenIandJ[i][i] = true;
      if(s.charAt(i) == s.charAt(i+1))
      {
        longestPalindromeBetweenIandJ[i][i+1] = true;
      }
    }
    longestPalindromeBetweenIandJ[n-1][n-1] = true;
    for(int j=2;j<n;j++)
    {
      for(int i=0;i<j-1;i++)
      {
        if(s.charAt(i) == s.charAt(j))
        {
          longestPalindromeBetweenIandJ[i][j] = longestPalindromeBetweenIandJ[i+1][j-1];
        }
      }
    }
    int start=0;
    int end=0;
    int maxLength=0;
    for(int i=0;i<n;i++)
    {
      for(int j=i;j<n;j++)
      {
        if(longestPalindromeBetweenIandJ[i][j] && maxLength < j-i+1)
        {
          maxLength = j-i+1;
          start=i;
          end=j;
        }
      }
    }
    return s.substring(start,end+1);
  }
}

6. ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

public class Solution
{
  public String convert(String s, int nRows)
  {
    int len = s.length();
    if(len<=nRows || nRows==1)
    {
      return s;
    }
    String out="";
    for(int i=0;i<nRows;i++)
    {
      int step1=2*(nRows-1-i);
      int step2=2*i;
      int index = i;
      out = out + s.charAt(index);
      while(true)
      {
        index += step1;
        if(index >=len)
        {
          break;
        }
        if(step1>0)
        {
          out = out + s.charAt(index);
        }
        index += step2;
        if(index >=len)
        {
          break;
        }
        if(step2>0)
        {
          out = out + s.charAt(index);
        }
      }
    }
    return out;
  }
}

7. Reverse Integer
Given a 32-bit signed integer, reverse digits of an integer.

class Solution
{
  public int reverse(int x)
  {
    String out = "";
    if(x < 0)
    {
      out += "-";
      x = Math.abs(x);
    }
    while(x != 0)
    {
      System.out.println(out);
      out += x%10;
      x = x/10;
    }
    System.out.println(out);
    int result=0;
    try
    {
      result = Integer.parseInt(out);
    }
    catch (NumberFormatException e)
    {
      return 0;
    }
    return result;
  }
}

8. String to Integer (atoi)
Implement atoi which converts a string to an integer.
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
If no valid conversion could be performed, a zero value is returned.

class Solution
{
  public int myAtoi(String str)
  {
    if(str == null || str.length() == 0)
    {
      return 0;
    }
    int index = 0;
    int l = str.length();
    String outString = "";
    while(index < l && str.charAt(index) == ' ')
    {
      // remove leading spaces
      index++;
    }
    if(index >= l)
    {
      return 0;
    }
    if(str.charAt(index) == '+' || str.charAt(index) == '-')
    {
      outString += str.charAt(index);
      index++;
    }
    if(index >= l)
    {
      return 0;
    }
    while(index < l && (int) str.charAt(index) >=48 && (int) str.charAt(index) <=57)
    {
      outString += str.charAt(index);
      index++;
    }
    if(outString.length() == 0 || (outString.length() == 1 && (outString.charAt(0) == '+' ||  outString.charAt(0) == '-')))
    {
      return 0;
    }
    try
    {
      return Integer.parseInt(outString);
    }
    catch(NumberFormatException e)
    {
      if(outString.charAt(0) == '-')
      {
        return Integer.MIN_VALUE;
      }
      return Integer.MAX_VALUE;
    }
  }
}

9. Palindrome Number
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
Follow up: Coud you solve it without converting the integer to a string?

public class Solution
{
  public boolean isPalindrome(int x)
  {
    if(x < 0)
    {
      return false;
    }
    long revX = 0;
    int xCopy = x;
    while(xCopy != 0)
    {
      revX = revX*10 + xCopy%10;
      if(revX > Integer.MAX_VALUE)
      {
        return false;
      }
      xCopy = xCopy/10;
    }
    return revX == x;
  }
}

11. Container With Most Water
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container and n is at least 2.

class Solution
{
  public int maxArea(int[] height)
  {
    if(height == null || height.length < 2)
    {
      return 0;
    }
    int maxArea = Integer.MIN_VALUE;
    int start = 0;
    int end = height.length-1;
    while(start < end)
    {
      int currArea = (end-start) * Math.min(height[start], height[end]);
      maxArea = Math.max(maxArea, currArea);
      if(height[start] <height[end])
      {
        start++;
      }
      else
      {
        end--;
      }
    }
    return maxArea;
  }
}

13. Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol     Value
I       1
V       5
X       10
L       50
C       100
D       500
M       1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

class Solution
{
  public int romanToInt(String s)
  {
    if(s == null || s.length() == 0)
    {
      return 0;
    }
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    int out=0;
    if(s.length() == 1)
    {
      return map.get(s.charAt(0));
    }
    int index = s.length()-1;
    while(index >= 1)
    {
      char curr = s.charAt(index);
      char prev = s.charAt(index-1);
      if(map.get(prev) < map.get(curr))
      {
        out = out + map.get(curr) - map.get(prev);
        index = index-2;
      }
      else
      {
        out = out + map.get(curr);
        index = index-1;
      }

    }
    if(s.length() > 1 && map.get(s.charAt(0)) >= map.get(s.charAt(1)))
    {
      out = out + map.get(s.charAt(0));
    }
    return out;
  }
}

14. Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

class Solution
{
  public String longestCommonPrefix(String[] strs)
  {
    if(strs == null || strs.length == 0)
    {
      return "";
    }
    int minLength = strs[0].length();
    for(int i=1;i<strs.length;i++)
    {
      minLength = Math.min(minLength, strs[i].length());
    }
    for(int i=0;i<minLength;i++)
    {
      for(int j=1;j<strs.length;j++)
      {
        if(strs[0].charAt(i) != strs[j].charAt(i))
        {
          return strs[0].substring(0,i);
        }
      }
    }
    return strs[0].substring(0, minLength);
  }
}

15. 3Sum
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note: The solution set must not contain duplicate triplets.

class Solution
{
  public List<List<Integer>> threeSum(int[] nums)
  {
    Set<List<Integer>> out = new HashSet<>();
    if(nums == null || nums.length < 3)
    {
      return new ArrayList<List<Integer>>();
    }
    Arrays.sort(nums);

    for(int i=0;i<nums.length-2;i++)
    {
      int s = i+1;
      int e = nums.length-1;
      while(s<e)
      {
        int currSum = nums[i]+nums[s]+nums[e];
        if(currSum < 0)
        {
          s++;
        }
        else if(currSum > 0)
        {
          e--;
        }
        else
        {
          List<Integer> entry = new ArrayList<>();
          entry.add(nums[i]);
          entry.add(nums[s]);
          entry.add(nums[e]);
          out.add(entry);
          s++;
          e--;
        }
      }
    }

    return out.stream().collect(Collectors.toList());
  }
}

16. 3Sum Closest
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

class Solution
{
  public int threeSumClosest(int[] nums, int target)
  {
    if(nums == null || nums.length < 3)
    {
      return 0;
    }
    Arrays.sort(nums);
    int minAbsDiff = Integer.MAX_VALUE;
    int minSum = 0;
    for(int i=0;i<nums.length-2;i++)
    {
      int s = i+1;
      int e = nums.length-1;

      while(s<e)
      {
        int currSum = nums[i]+nums[s]+nums[e];
        int currAbsDiff = Math.abs(target-currSum);
        int currDiff = target - currSum;
        if(currAbsDiff < minAbsDiff)
        {
          minAbsDiff = currAbsDiff;
          minSum = currSum;
        }
        if(currDiff > 0)
        {
          s++;
        }
        else if (currDiff < 0)
        {
          e--;
        }
        else
        {
          return currSum;
        }
      }
    }
    return minSum;
  }
}

17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
2-abc
3-def
4-ghi
5-jkl
6-mno
7-pqrs
8-tuv
9-wxyz
Note: Although the above answer is in lexicographical order, your answer could be in any order you want.

public class Solution
{
  List<String> out;
  void combinations(String prefix, String[] in, int d)
  {
    if(in.length == d)
    {
      out.add(prefix);
      return;
    }
    for(int i=0;i<in[d].length();i++)
    {
      combinations(prefix+in[d].charAt(i), in, d+1);
    }

  }

  String[] getString(String digits)
  {
    String[] in = new String[digits.length()];
    for(int i=0;i<digits.length();i++)
    {
      char num = digits.charAt(i);
      switch (num)
      {
        case '0':
        case '1':
          in[i] = "";
          break;
        case '2':
          in[i] = "abc";
          break;
        case '3':
          in[i] = "def";
          break;
        case '4':
          in[i] = "ghi";
          break;
        case '5':
          in[i] = "jkl";
          break;
        case '6':
          in[i] = "mno";
          break;
        case '7':
          in[i] = "pqrs";
          break;
        case '8':
          in[i] = "tuv";
          break;
        case '9':
          in[i] = "wxyz";
          break;
      }
    }
    return in;
  }


  public List<String> letterCombinations(String digits)
  {
    out = new ArrayList<>();
    if(digits == null || digits.length() == 0)
    {
      return out;
    }
    String[] in = getString(digits);
    combinations("", in, 0);
    return out;
  }
}

19. Remove Nth Node From End of List
Given a linked list, remove the n-th node from the end of list and return its head.

class Solution
{
  public ListNode removeNthFromEnd(ListNode head, int n)
  {
    ListNode sentinal = new ListNode(0);

    sentinal.next = head;
    int l=0;
    ListNode curr = head;
    while(curr != null)
    {
      l++;
      curr = curr.next;
    }
    curr = sentinal;
    for(int i=0;i<l-n;i++)
    {
      curr = curr.next;
    }
    curr.next = curr.next.next;
    return sentinal.next;
  }
}
