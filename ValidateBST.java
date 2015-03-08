/*
* @author arunmoezhi ramachandran
* @date 06-mar-2015
*/

/*
* Problem link : hhttps://oj.leetcode.com/problems/validate-binary-search-tree/
*	Given a binary tree, determine if it is a valid binary search tree (BST).
*	Assume a BST is defined as follows:
*	The left subtree of a node contains only nodes with keys less than the node's key.
*	The right subtree of a node contains only nodes with keys greater than the node's key.
*	Both the left and right subtrees must also be binary search trees.
*/

/*
* Ideas:
* The recursive definition of BST should give a clue.
* This problem is easily solved by recursion.
* A common pitfall is to recursively check this:
*	root.left.val < root.val AND root.right.val > root.val AND
* recursive check if root.left is valid AND root.right is valid
* An example where this would fail is :
* 			4
*			1			6
*					3		7
* Here in the right subtree of 4, 3 is less than 6 but greater than 4.
* So at each level in the recursion we need to keep track of the range of values (min & max)
*	allowed in each subtree.
*	Lets begin with the root. The root can have any value. So we set the range as (-INF,+INF)
*	Now lets say root has value x. The left subtree can then have values from (-INF,x).
*	Similarly the right subtree can have values from (x,+INF). Recurse :) 
*/

class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ValidateBST
{
    public boolean isValidBinaryST(TreeNode root, long min, long max)
    {
        if(root == null)
        {
            return true;
        }
        return root.val > min && root.val < max && isValidBinaryST(root.left,min,root.val) && isValidBinaryST(root.right,root.val,max);
    }
    
    public boolean isValidBST(TreeNode root) 
    {
        return isValidBinaryST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
}