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
