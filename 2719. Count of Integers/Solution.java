class Solution {
    int[][][] memo; 
    final static int MOD = 1000000007;
    int[] num1;
    int[] num2;
    int min_sum; 
    int max_sum;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        this.num1 = toArray(num1);
        this.num2 = toArray(num2);
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        memo = createEmptyArray(this.num2.length+1, min_sum+1, max_sum+1);
        int totalCount = 0;
        for(int n = this.num1.length ; n <= this.num2.length ; ++n){
            boolean checkNum1 = (n == this.num1.length);
            boolean checkNum2 = (n == this.num2.length);
            totalCount =  modAdd(totalCount, countInts(n, this.min_sum, this.max_sum, false, checkNum1, checkNum2));
        }
        return totalCount;
    }
    private int countInts(int n, int min_sum, int max_sum, boolean from0, boolean checkNum1, boolean checkNum2){
        
        if(n == 0){
            return 0;
        }
        //We can only use cache if we don't have a constraint of checking with num1 or num2
        if(memo[n][min_sum][max_sum] != -1 && checkNum1 == false && checkNum2 == false){
            return memo[n][min_sum][max_sum];
        }
        int smallestDigit = checkNum1 ? num1[num1.length-n] : (from0?0:1);
        int largestDigit = checkNum2 ? num2[num2.length-n] : 9;
        if(n == 1){
            int val = Integer.min(largestDigit, max_sum)-Integer.max(min_sum, smallestDigit)+1;
            return val > 0 ? val:0;
        }
        int totalCount = 0;
        for(int i = smallestDigit; i <= largestDigit && i <= max_sum ; ++i){
            boolean newCheckNum1 = checkNum1 && (num1[num1.length-n] == i);
            boolean newCheckNum2 = checkNum2 && (num2[num2.length-n] == i);
            
            totalCount = modAdd(totalCount, countInts(n-1, Integer.max(min_sum-i, 0), max_sum-i, true, newCheckNum1, newCheckNum2));
        }
        if(!checkNum1 && !checkNum2){
            memo[n][min_sum][max_sum] = totalCount;
        }
        return totalCount;
    }
    private int modAdd(int left, int right){
        return ((left%MOD)+(right%MOD))%MOD;
    }
    private int[][][] createEmptyArray (int x, int y, int z){
        int[][][] arr = new int[x][][];
        for(int i = 0 ; i < arr.length ; ++i){
            arr[i] = new int[y][];
            for(int j = 0 ; j < arr[i].length ; ++j){
                arr[i][j] = new int[z];
                Arrays.fill(arr[i][j], -1);   
            }
        }
        return arr;
    }
    private int[] toArray(String s) {
        int[] num = new int[s.length()];
        for (int i=0 ; i < s.length() ; ++i){
            num[i] = Character.getNumericValue(s.charAt(i));
        }
        return num;
    }
}