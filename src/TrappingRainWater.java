//Q: https://leetcode.com/problems/trapping-rain-water/

class TrappingRainWater {
    public int trap(int[] height) {
        int l = 0; //left index
        int r = (height.length) - 1; //right index
        int lw = 0; //Left wall
        int rw = 0; //right wall
        int res = 0;
        while (l <= r) {
            if (lw <= rw) { //processing left side
                if (height[l] < lw) //if this is true then water can be trapped
                    res += 1 * (lw - height[l]); //1 signifies the width
                else {
                    lw = height[l]; //assigning the new height
                }
                l++;
            } else { //processing right side
                if (height[r] < rw) { //if this is true then water can be trapped
                    res += 1 * (rw - height[r]);
                } else {
                    rw = height[r]; //assigning the new height
                }
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater ob = new TrappingRainWater();
        int sum = ob.trap(height);
        System.out.println("Amount of water that can be trapped between the building is: "+ sum);
    }
}

//Time complexity is O(n) - iterating over the array
//Space complexity is O(1)

//Logic:
//If we want to trap the water then lw has to be greater than L and RW has to be greater than R
