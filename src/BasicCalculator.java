import java.util.Stack;

class BasicCalculator {
    public int calculate(String s) {
        if(s==null || s.length()==0)
            return 0;
        int lastsign = '+'; //Since the minimum number is 1
        s = s.trim(); //to remove the spaces present in the string
        int num = 0;
        int result = 0;
        Stack<Integer> st = new Stack(); //Stack to store the numbers.
        for(int i =0; i<s.length();i++) //As soon as the index reaches the end of the string, the loop will be terminated
        {
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0'; //since we're resetting the number to 0
            }
            if(c!= ' ' && (!Character.isDigit(c) || i== s.length()-1))
            {
                if(lastsign=='+')
                    st.push(num); //pushing positive number
                else if(lastsign=='-')
                    st.push(-num); //pushing the negative number
                else if(lastsign == '*')
                    //if the lastsign is *, we need to pop the topmost element in the stack multiply the same with the current number.
                    // Then push the product to the stack. The product will be useful if any operator is present.
                    st.push(st.pop()*num);
                else
                    //if the lastsign is /, we need to pop the topmost element in the stack divide the same with the current number.
                    // Then push the result to the stack. The result will be useful if any operator is present.
                    st.push(st.pop()/num);
                //Resetting the number to 0 and lastsign to the current operator
                lastsign = c;
                num=0;
            }
        }
        while(!st.isEmpty())
        {
            result+=st.pop(); //adding all the numbers present in the stack
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "3+5 / 10";
        BasicCalculator ob = new BasicCalculator();
        int result = ob.calculate(input);
        System.out.println("The evaluation of the string is: "+ result);
    }
}

//Time complexity is O(n) - Since we're iterating over the string once.
//Space complexity is O(n) - We're making use of stack to store the elements.
//n denotes the length of the given string

//Logic:
//Only when the last sign is multiplication and we've reached at the end of the string, we'll push the digit to the stack
// When we encounter the digit in all the other cases, we don't push the number to the stack at that time
// When the sign changes, push the number to the stack.
// When the index points to a sign, we'll assign num as 0.