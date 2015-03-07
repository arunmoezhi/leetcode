/*
* @author arunmoezhi ramachandran
* @date 07-mar-2015
*/

/*
* Problem link : https://oj.leetcode.com/problems/3sum-closest/
* Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
* Return the sum of the three integers. You may assume that each input would have exactly one solution.
*
*For example, given array S = {-1 2 1 -4}, and target = 1.
*The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

/*
* Ideas:
* Simple solution would be to solve twoSumClosest for each A[i]. Assuming twoSumClosest takes O(n^2),
* the overall time complexity is O(n^3). Here for each element A[i], for all possible values of j & k, 
* with i<j<k we compute A[i]+[A[j]+A[k].
* Now let's assume the input is sorted. Should we have to consider all possible values of j & k?
* No. For each i, we set j=i+1 and k= end of array. In the subarray (j,k), 'j' points to the smallest
* element and 'k' points to the largest element. Now we compute their sum and find the diff
* with target. If diff is zero, then we are done. If the diff is +ve, we try to reduce it. 
* Since the array is sorted, we can reduce diff by leaving out the largest number 'k'.
* On the other hand if the diff is -ve, we try to increase it by leaving out the smallest number 'j'.
* Now for each 'i', we traverse all the elements of the subarray (j,k) O(1) times.
* So the overall time complexity is O(n^2).
*
*/
import java.util.Arrays;
public class ThreeSumClosest 
{
    public int threeSumClosest(int[] A, int target) 
    {
        int N = A.length;
				// If there are less than three elements we don't have a choice other than returning their entire sum
        if(N<=3)
        {
            int sum=0;
            for(int i=0;i<N;i++)
            {
                sum = sum+A[i];
            }
            return sum;
        }
        
        int absMinDiff = Integer.MAX_VALUE; // keep track of the minimum difference seen so far
        int minSum=0;
        Arrays.sort(A); // sort the array
        for(int i=0;i<N;i++) // for each element A[i], check the subarray (j,k)
        {
            int j=i+1;
            int k=N-1;
            while(j<k)
            {
                int currentSum = A[i]+A[j]+A[k];
                int diff = currentSum-target;
                if (diff > 0)
                {
                    if(diff < absMinDiff)
                    {
                        absMinDiff = diff;
                        minSum = currentSum;
                    }
                    k--; // drop the largest element
                }
                else if(diff < 0)
                {
                    if(-diff < absMinDiff)
                    {
                        absMinDiff = -diff;
                        minSum = currentSum;
                    }
                    j++; // drop the smallest element
                }

                else
                {
                    return currentSum;
                }
            }
        }
        return minSum;
    }
}