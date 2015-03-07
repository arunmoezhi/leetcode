/*
* @author arunmoezhi ramachandran
* @date 06-mar-2015
*/

/*
* Problem link : https://oj.leetcode.com/problems/container-with-most-water/
* Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.
*
* Note: You may not slant the container.
*/

/*
* Ideas:
* First lets think about the note " you may not slant the container".
* This makes sense right?. If we have a rectangular jar with one side lower than the other
* water would flow out from the higher side until it levels out when it reaches the lower level.
* Our objective here is to maximize the water content. This means we need to maximize the
* area between any two lines among the 'n' lines. Area between lines (i,ai) and (j,aj) is
* (j-i)* min(ai,aj). Why min? Because water would flow out till reaches the lower level.
* Simple solution is for any two lines 'i' and 'j' among the 'n' lines we compute the area
* and keep track of the maximum area. This is O(n^2). Why?
* Choosing 2 out 'n' lines is nC2 (n choose 2) which is O(n^2).
* Can we do better?
* Yes. Note that the x-co-ordinate is already sorted.
* Recall that area=(j-i)*min(ai,aj). We know that j is always greater than i.
* Without looking at 'ai' and 'aj' we can try to maximize the (j-i) term. How?
* By choosing 'i' to be the first line and 'j' to be the last line. 
* After computing area for this 'i' and 'j' combination we try the next combination.
* Here we drop one of the two lines. Which line to drop? It makes sense to drop the smaller line.
* Now we iterate
* To summarize, we start at the ends with two pointers. We compute area and also keep track of 
* maximum area. Then we drop the line with smaller height by either shifting the left pointer to
* the right or shifting the right pointer to the left. Here for each iteration we eliminate a line.
* So the time complexity is O(N)
*/
public class ContainerWithMostWater 
{
  public int maxArea(int[] h) 
  {
    int N = h.length;
    int i=0;
    int j= N-1;
    int area=0;
    while(i<j)
    {
			// keep track of maximum area
      area = Math.max(area,(j-i)*Math.min(h[i],h[j]));
      if(h[i] < h[j]) // left line has smaller height. So shift to right
      {
        i++;
      }
      else // right line has smaller height. So shift to left
      {
        j--;
      }
    }
    return area;
  }
}