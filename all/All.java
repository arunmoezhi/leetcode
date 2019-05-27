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

164. Maximum Gap
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
Return 0 if the array contains less than 2 elements.
Note: You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.

public class Solution
{
  public int maximumGap(int[] A)
  {
    if(A.length < 2)
    {
      return 0;
    }
    Arrays.sort(A);
    int maxDiff=Math.abs(A[0]-A[1]);
    for(int i=1;i<A.length-1;i++)
    {
      maxDiff = Math.max(maxDiff,Math.abs(A[i]-A[i+1]));
    }
    return maxDiff;
  }
}

167. Two Sum II - Input array is sorted
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
Note: Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.

public class Solution
{
  int binSearch(int[] A, int k)
  {
    int s=0;
    int e=A.length-1;
    while(s<=e)
    {
      int mid = s + (e-s)/2;
      if(k == A[mid])
      {
        return mid;
      }
      else if(k < A[mid])
      {
        e = mid-1;
      }
      else
      {
        s = mid+1;
      }
    }
    return -1;
  }

  public int[] twoSum_withBinarySearch(int[] A, int k)
  {
    int[] out = {-1,-1};
    for(int i=0;i<A.length;i++)
    {
      int index = binSearch(A, k-A[i]);
      if(index != -1 && index != i)
      {
        out[0] = Math.min(i, index) + 1;
        out[1] = Math.max(i, index) + 1;
        return out;
      }
    }
    return out;
  }
  public int[] twoSum(int[] A, int k)
  {
    int[] out = {-1,-1};
    int s=0;
    int e=A.length-1;
    while(s<=e)
    {
      int diff = k-(A[s]+A[e]);
      if(diff == 0)
      {
        out[0] = s+1;
        out[1] = e+1;
        return out;
      }
      else if (diff < 0)
      {
        e--;
      }
      else
      {
        s++;
      }
    }
    return out;
  }
}

169. Majority Element
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

class Solution
{
  public int majorityElement(int[] nums)
  {
    /* //O(nlog(n)) solution
    Arrays.sort(nums);
    return nums[(nums.length-1)/2];
    */
    int majorityCount = 1;
    int majorityElement = nums[0];
    for(int i=1;i<nums.length;i++)
    {
      if(nums[i] == majorityElement)
      {
        majorityCount++;
      }
      else
      {
        majorityCount--;
        if(majorityCount == 0)
        {
          majorityElement = nums[i];
          majorityCount = 1;
        }
      }
    }
    return majorityElement;
  }
}

172. Factorial Trailing Zeroes
Given an integer n, return the number of trailing zeroes in n!.

class Solution
{
  public int trailingZeroes(int n)
  {
    if(n < 2)
    {
      return 0;
    }
    long x = 5;
    long zCount = 0;
    while(x<=n)
    {
      zCount = zCount + (long) Math.floor(n/x);
      x = x*5;
    }
    return (int) zCount;
  }
}

173. Binary Search Tree Iterator
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

public class BSTIterator
{

  List<Integer> outList;

  public BSTIterator(TreeNode root)
  {
    outList = new LinkedList<Integer>();
    inOrder(root);
  }

  void inOrder(TreeNode root)
  {
    if(root==null)
    {
      return;
    }
    inOrder(root.left);
    outList.add(root.val);
    inOrder(root.right);
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext()
  {
    return !outList.isEmpty();
  }

  /** @return the next smallest number */
  public int next()
  {
    return outList.remove(0);
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

174. Dungeon Game
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer.
Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
Note: The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

public class Solution
{
  public int calculateMinimumHP(int[][] A)
  {
    int m,n;
    m=A.length;
    if(m==0)
    {
      return 0;
    }
    n=A[0].length;
    int[][] minH = new int[m+1][n+1];
    for(int i=m;i>=0;i--)
    {
      minH[i][n] = Integer.MAX_VALUE;
    }
    for(int i=n;i>=0;i--)
    {
      minH[m][i] = Integer.MAX_VALUE;
    }
    minH[m][n-1] = 1;
    minH[m-1][n] = 1;
    for(int i=m-1;i>=0;i--)
    {
      for(int j=n-1;j>=0;j--)
      {
        int need = Math.min(minH[i+1][j],minH[i][j+1]) - A[i][j];
        minH[i][j] = need <= 0 ? 1 : need;
      }
    }
    return minH[0][0];
  }
}

175. Combine Two Tables
Create table Person (PersonId int, FirstName varchar(255), LastName varchar(255))
Create table Address (AddressId int, PersonId int, City varchar(255), State varchar(255))
Truncate table Person
insert into Person (PersonId, LastName, FirstName) values ('1', 'Wang', 'Allen')
Truncate table Address
insert into Address (AddressId, PersonId, City, State) values ('1', '2', 'New York City', 'New York')
Table: Person
+-------------+---------+
| Column Name | Type  |
+-------------+---------+
| PersonId  | int   |
| FirstName   | varchar |
| LastName  | varchar |
+-------------+---------+
PersonId is the primary key column for this table.
Table: Address
+-------------+---------+
| Column Name | Type  |
+-------------+---------+
| AddressId   | int   |
| PersonId  | int   |
| City    | varchar |
| State     | varchar |
+-------------+---------+
AddressId is the primary key column for this table.
Write a SQL query for a report that provides the following information for each person in the Person table, regardless if there is an address for each of those people:
FirstName, LastName, City, State

select FirstName,LastName,City,State
from Person A left outer join Address B
on A.PersonId = B.PersonId;

176. Second Highest Salary
Create table If Not Exists Employee (Id int, Salary int)
Truncate table Employee
insert into Employee (Id, Salary) values ('1', '100')
insert into Employee (Id, Salary) values ('2', '200')
insert into Employee (Id, Salary) values ('3', '300')
Write a SQL query to get the second highest salary from the Employee table.
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100  |
| 2  | 200  |
| 3  | 300  |
+----+--------+
For example, given the above Employee table, the query should return 200 as the second highest salary. If there is no second highest salary, then the query should return null.
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200         |
+---------------------+

select max(Salary) from Employee
where Id not in
(
  select Id from Employee
  where Salary =
  (
    select max(Salary) from Employee

  )
)
;

179. Largest Number
Given a list of non negative integers, arrange them such that they form the largest number.
Note: The result may be very large, so you need to return a string instead of an integer.

public class Solution implements Comparator<String>
{
  public String largestNumber(int[] num)
  {
    int l = num.length;
    ArrayList<String> s = new ArrayList<String>();
    for(int i=0;i<l;i++)
    {
      s.add(Integer.toString(num[i]));
    }
    Collections.sort(s,new Solution());
    String out="";
    for(int i=0;i<l;i++)
    {
      out = out + s.get(i);
    }
    if(out.charAt(0)=='0')
    {
      return "0";
    }
    return out;
  }

  public int compare(String x, String y)
  {
    if(x.length() == y.length())
    {
      return y.compareTo(x);
    }
    String xy = x+y;
    String yx = y+x;
    return yx.compareTo(xy);
  }
}

181. Employees Earning More Than Their Managers
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, ManagerId int)
Truncate table Employee
insert into Employee (Id, Name, Salary, ManagerId) values ('1', 'Joe', '70000', '3')
insert into Employee (Id, Name, Salary, ManagerId) values ('2', 'Henry', '80000', '4')
insert into Employee (Id, Name, Salary, ManagerId) values ('3', 'Sam', '60000', 'None')
insert into Employee (Id, Name, Salary, ManagerId) values ('4', 'Max', '90000', 'None')
The Employee table holds all employees including their managers. Every employee has an Id, and there is also a column for the manager Id.
+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3     |
| 2  | Henry | 80000  | 4     |
| 3  | Sam   | 60000  | NULL    |
| 4  | Max   | 90000  | NULL    |
+----+-------+--------+-----------+
Given the Employee table, write a SQL query that finds out employees who earn more than their managers. For the above table, Joe is the only employee who earns more than his manager.
+----------+
| Employee |
+----------+
| Joe    |
+----------+

select A.Name as Employee
from Employee A, Employee B
where A.ManagerId = B.Id
And A.Salary > B.salary

182. Duplicate Emails
Create table If Not Exists Person (Id int, Email varchar(255))
Truncate table Person
insert into Person (Id, Email) values ('1', 'a@b.com')
insert into Person (Id, Email) values ('2', 'c@d.com')
insert into Person (Id, Email) values ('3', 'a@b.com')
Write a SQL query to find all duplicate emails in a table named Person.
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
For example, your query should return the following for the above table:
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
Note: All emails are in lowercase.

select Email from Person
group by Email
having count(*) >1;

183. Customers Who Never Order
Create table If Not Exists Customers (Id int, Name varchar(255))
Create table If Not Exists Orders (Id int, CustomerId int)
Truncate table Customers
insert into Customers (Id, Name) values ('1', 'Joe')
insert into Customers (Id, Name) values ('2', 'Henry')
insert into Customers (Id, Name) values ('3', 'Sam')
insert into Customers (Id, Name) values ('4', 'Max')
Truncate table Orders
insert into Orders (Id, CustomerId) values ('1', '3')
insert into Orders (Id, CustomerId) values ('2', '1')
Suppose that a website contains two tables, the Customers table and the Orders table. Write a SQL query to find all customers who never order anything.
Table: Customers.
+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Table: Orders.
+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3      |
| 2  | 1      |
+----+------------+
Using the above tables as example, return the following:
+-----------+
| Customers |
+-----------+
| Henry   |
| Max     |
+-----------+

select Name as Customers from Customers
where Id not in
        (
          select distinct CustomerId from Orders
        )
;

184. Department Highest Salary
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, DepartmentId int)
Create table If Not Exists Department (Id int, Name varchar(255))
Truncate table Employee
insert into Employee (Id, Name, Salary, DepartmentId) values ('1', 'Joe', '70000', '1')
insert into Employee (Id, Name, Salary, DepartmentId) values ('2', 'Jim', '90000', '1')
insert into Employee (Id, Name, Salary, DepartmentId) values ('3', 'Henry', '80000', '2')
insert into Employee (Id, Name, Salary, DepartmentId) values ('4', 'Sam', '60000', '2')
insert into Employee (Id, Name, Salary, DepartmentId) values ('5', 'Max', '90000', '1')
Truncate table Department
insert into Department (Id, Name) values ('1', 'IT')
insert into Department (Id, Name) values ('2', 'Sales')
The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.
+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1      |
| 2  | Jim   | 90000  | 1      |
| 3  | Henry | 80000  | 2      |
| 4  | Sam   | 60000  | 2      |
| 5  | Max   | 90000  | 1      |
+----+-------+--------+--------------+
The Department table holds all departments of the company.
+----+----------+
| Id | Name   |
+----+----------+
| 1  | IT     |
| 2  | Sales  |
+----+----------+
Write a SQL query to find employees who have the highest salary in each of the departments. For the above tables, your SQL query should return the following rows (order of rows does not matter).
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT     | Max    | 90000  |
| IT     | Jim    | 90000  |
| Sales    | Henry  | 80000  |
+------------+----------+--------+
Explanation:
Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.

select C.Name as Department,A.Name as Employee,A.Salary as Salary
from Employee A,
(
select DepartmentId, max(Salary) as Salary from Employee
group by DepartmentId
)B,
Department C
where A.DepartmentId=B.DepartmentId
and A.DepartmentId=C.Id
and A.Salary=B.Salary;

187. Repeated DNA Sequences
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

class Solution
{
  public List<String> findRepeatedDnaSequences(String s)
  {
    if(s == null || s.length() < 11)
    {
      return new ArrayList<String>();
    }
    Set<String> sequences = new HashSet<>();
    Set<String> uniqueDupSequences = new HashSet<>();
    String curr = s.substring(0, 10);
    sequences.add(curr);
    for(int i=10;i<s.length();i++)
    {
      String next = curr.substring(1) + s.charAt(i);
      if(sequences.contains(next))
      {
        uniqueDupSequences.add(next);
      }
      else
      {
        sequences.add(next);
      }
      curr = next;
    }
    List<String> out = new ArrayList<>();
    for(String str : uniqueDupSequences)
    {
      out.add(str);
    }
    return out;
  }
}

189. Rotate Array
Given an array, rotate the array to the right by k steps, where k is non-negative.

class Solution
{
  void rotate(int[] nums, int s, int e)
  {
    while(s<e)
    {
      int temp = nums[s];
      nums[s] = nums[e];
      nums[e] = temp;
      s++;
      e--;
    }
    return;
  }

  public void rotate(int[] nums, int k)
  {
    if(nums == null || nums.length < 2)
    {
      return;
    }
    int l = nums.length;
    k = k%l;
    rotate(nums, 0, l-k-1);
    rotate(nums, l-k, l-1);
    rotate(nums, 0, l-1);
  }
}

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
