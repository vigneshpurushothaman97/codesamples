//Q: https://leetcode.com/problems/course-schedule/

import java.util.*;

public class CourseScheduler {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(prerequisites ==null || prerequisites.length==0)
            return true;
    HashMap<Integer, List<Integer>> map = new HashMap(); //to maintain the adjacency list
    int[] indegrees = new int[numCourses]; //to store the dependencies of each node
    //calculate te number of dependencies
        for(int[] pre: prerequisites)
    {
        int in = pre[0]; //incoming node
        int out = pre[1]; //outgoing node
        indegrees[in]++;
        if(!map.containsKey(out))
        {
            map.put(out, new ArrayList());
        }
        map.get(out).add(in);
    }
    //indegrees and adj list
    Queue<Integer> q = new LinkedList();
        for(int i=0;i<indegrees.length;i++) //iterating over the array to find the independent nodes
        {
            if(indegrees[i]==0)
            {
                q.add(i); //adding to the queue
            }
        }
        if(q.size()==0) //if there are no independent nodes at this point, then the courses can't be completed.
                return false;

    //pop elements and reduce dependencies
    int count =0;
        while(!q.isEmpty())
        {
            int current = q.poll();
            count++; //when we've completed the courses, we're incrementing this count
        List<Integer> edges = map.get(current); // to get the dependencies of each node
        if(edges!=null)
        {
            for(int edge : edges)
            {
                indegrees[edge]--; //decreasing the dependency
                if(indegrees[edge]==0) //means we've completed one course(independent)
                {
                    q.add(edge);
                }
            }
        }
    }
        if(count!=numCourses) //when count is not equal to the given number of courses then we've not completed all the course
            return false;
        return true;
}
    public static void main(String[] args) {
        int numberOfCourses = 6;
        int[][] prereq = {{1,0},{2,0},{5,2},{5,1},{3,2},{4,1},{4,3}};
        CourseScheduler ob = new CourseScheduler();
        boolean check = ob.canFinish(numberOfCourses,prereq);
        System.out.println("Can the courses be complemented? "+ check);
    }
}

//Graph problem
//Time complexity is O(v+e) v is the vertices and e is all the edges. From the code it might look that it's O(v*e) but it's not.
//Space Complexity is O(v)

//Logic:
/*
we can iterate over the initial array to check what are the courses that are dependent and create an adjacency list
We can start from the node which has 0 dependency. If there are two nodes which 0 dependencies then we'll begin at both nodes
Instead of traversing the array again and again to find the dependencies, we can create a hashmap so that we can reduce the traversal.
 */
