/*
* @author arunmoezhi ramachandran
* @date 06-mar-2015
*/

/*
* Problem link : https://oj.leetcode.com/problems/two-sum/
* Given an array of integers, find two numbers such that they add up to a specific target number.
* The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
* 
* Please note that your returned answers (both index1 and index2) are not zero-based.
* You may assume that each input would have exactly one solution.
*
* Input: numbers={2, 7, 11, 15}, target=9
* Output: index1=1, index2=2
*/

/*
* Ideas:
*	Let's try a simple solution. Let the target number be 'x'. 
* For each element A[i] in the input, compute y=x-A[i]. 
* Now scan through the entire array to see if 'y' is present
* If 'y' is present (say at location 'j') then return (i,j)
* Else iterate. This is O(N^2) as in the worst case we might compare 'N' elements for every 'i'.
* Modified version1:
* Here we do two things. We looked at each element once. Obviously we cannot avoid that.
* But for each element we looked, we did a linear search which is O(N). Can we do better?
* We could have done binary search if the input was sorted. That would have made each search as O(logN) bringing
* the total time to O(NlogN). So we could start by sorting the input which takes O(NlogN) and then doing
* a binary search for each N. So the total time is still O(NlogN)
* Modified version2:
* As a rule of thumb, I always check this:
* if a problem can be solved by sorting and then doing a binary search,
* then can I replace this O(NlogN) solution with a hashmap having expected O(N) complexity.
* In the problem it fits well. In the first pass through each element A[i] into a hashMap.
* In the second pass for each A[i] compute y=x-A[i]. Now check if 'y' is present in the hashMap.
* If it is there then we are done. Else iterate
* Note that we used a hashMap instead of hashSet. Why?
* Because we need to return the index of the two numbers whose sum matched 'x'.
* So in the hashMap we need to store the element as key and its index as value.
*/
import java.util.HashMap;
public class TwoSum 
{
  public int[] twoSum(int[] A, int target) 
  {
    int i=0;
    Integer index2=null;
		// Hashmap key:number, value:index
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		// first pass to fill the hashmap
    for(i=0;i<A.length;i++)
    {
      map.put(A[i],i);
    }
		// second pass to lookup in hashmap with O(1) complexity
    for(i=0;i<A.length;i++)
    {
      index2 = map.get(target - A[i]);
      if(index2 != null && index2!=i)
      {
        break;
      }
    }
    int[] out = {i+1,index2+1};
    return out;
  }	
}