//Q: https://leetcode.com/problems/linked-list-cycle-ii/
import org.w3c.dom.Node;

import java.util.LinkedList;

class LinkedListcycle {
    static class ListNode {  //Definition for singly-linked list.
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head; //slow pointer
        ListNode fast = head; //fast pointer
        boolean flag = false;
        while(fast!=null && fast.next!=null) //We've to check both fast≠null and fast.next≠null because in the case of odd numbers fast.next≠null will be invoked
        {
            slow = slow.next; //moving at 1X speed
            fast = fast.next.next; //moving at 2X speed
            if(fast==slow)
            {
                flag = true; //used to indicate if there's a cycle
                break;
            }
        }
        if(flag==false)
        {
            return null;
        }
        slow = head; //resetting the slow pointer to the start
        while(slow != fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        ListNode begin = new ListNode(3);
        begin.next = new ListNode(2);
        begin.next.next = new ListNode(0);
        begin.next.next.next = new ListNode(-4);
        begin.next.next.next.next = begin.next; //cycle at value 2
        LinkedListcycle ob = new LinkedListcycle();
        ListNode cycle = ob.detectCycle(begin);
        System.out.println("The cycle begins at: "+ cycle + " the value present in the node is:"+ cycle.val);
    }
}

//Time Complexity is O(n). Iterting over the nodes
//Space Complexity is O(1)

//Logic:
/*
We can make use of Slow and Fast pointers.
One pointer will start early and move slowly and another pointer will start late and move at 2X speed.
At some point, the slow and fast will pointers will meet if there's a cycle. If not the fast pointer will go out of range.
Once we detect there's a cycle, then reset either the slow and fast and increase one by one to find the beginning of the cycle
 */
