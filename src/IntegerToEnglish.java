//Q: https://leetcode.com/problems/integer-to-english-words/

class IntegerToEnglish {
    //below_20 array contains the strings below 20 since these can be represented uniquely.
    // The first element is empty string because we can obtain the string from the index.
    String[] below_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen"};
    //tens array contains the strings which can be represented uniquely.
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    //thousands array represents the suffix after each triplet.
    String[] thousands = {"", "Thousand", "Million", "Billion"};

    public  String numberToWords(int num) {
        if(num==0)
            return "Zero";
        String result = ""; //for storing the result
        int i=0;
        while(num>0)
        {
            if(num%1000!=0)
            {
                result = helper(num%1000) + thousands[i] + " " + result;
            }
            num = num/1000;
            i++;
        }
        return result.trim();
    }
    private String helper(int num) //to help us decode the string
    {
        if(num==0)
            return "";
        else if(num<20)
            return below_20[num]+" ";
        else if(num<100)
            return tens[num/10] + " " + helper(num%10); //to obtain the
        else return below_20[num/100] + " " + "Hundred" + " " + helper(num%100);
    }

    public static void main(String[] args) {
        int number = 1234567891;
        IntegerToEnglish ob = new IntegerToEnglish();
        String output = ob.numberToWords(number);
        System.out.println("The English word expression is: "+ output);
    }
}

//Time complexity is O(1) - The recursion stack is always contant. Maximum recursion is 2
//Space complexity is O(1) - Constant because there are only finite number of entries in each stack

//Logic:
//Any number can be categorized into 3 categories. If number greater than 100, less than 100 and less than 20.
//We'll divide the whole digits into triplets. The first triplet will not have any suffix, the second triplet suffix will be thousand

/*
Below 100 array:

For example 43:

{ten, twenty, thirty,.....}

To get the index of the 10 array then num / 10

To get three, we do num % 10 → below_20 array.

Greater than 100 array:

543 /100 → below 20 array + hundred

Then call the function recursively to decode the rest.
 */
