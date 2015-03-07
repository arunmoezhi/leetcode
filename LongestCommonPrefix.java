/*
* @author arunmoezhi ramachandran
* @date 06-mar-2015
*/

/*
* Problem link : https://oj.leetcode.com/problems/longest-common-prefix/
* Write a function to find the longest common prefix string amongst an array of strings.
* prefix means a word, letter, or number placed before another.
* Two or more words to have a common prefix means that the first 'x' characters of all 
* the words are same.
* Example:
* (i) "aabcd", "aabx" -> "aab"
* (ii) "bcd", "bcdx", "acdx" -> ""
* (iii) "bc", "bcdxg", "bcdx" -> "bc"
*/

/*
* Ideas:
* When we try to find the common prefix of 'n' strings each of different length, we observe that
* the prefix cannot be larger than the string having the minimum length. See example (iii)
* So we can start by finding the minimum length. This is easy. Just scan through the list
* of strings, computing their length and keeping track of the minimum length 'minLength'.
*	Since a common prefix is present in every string, it is enough if we take any arbitrary string
* as reference. Now if the common prefix has a length 'x', then it means that the first 'x' characters
* of ALL the strings are same. Now we have an algorithm. Take an arbitrary string, check if its first
* character matches the first character in every other string. If it matches, then do the same for the 
* next character and so on until we have checked 'minLength' characters. Note that 'x' is always less than
* or equal to 'minLength'. If we find a mismatch at some point 'i', then all the characters upto 'i-1' are
* same meaning the common prefix is 0 to i-1.
*/

public class LongestCommonPrefix
{
	public String longestCommonPrefix(String[] strs) 
	{
		// boundary conditions
		if(strs.length==0)
		{
			return "";
		}
		String out="";
		int[] length = new int[strs.length];
		int minLength=Integer.MAX_VALUE;
		//compute minLength - length of the shortest string 
		for(int i=0;i<strs.length;i++)
		{
			length[i] = strs[i].length();
			minLength = Math.min(minLength,length[i]);
		}
		for(int i=0;i<minLength;i++) // for every character from 0 to minLength-1
		{
			for(int j=1;j<strs.length;j++) // for all the strings
			{
				// compare characters of the first string (arbitrary) to all other strings
				if(strs[0].charAt(i)!=strs[j].charAt(i))
				{
					return out;
				}
			}
			out = out + strs[0].charAt(i);
		}
		return out;
	}
}