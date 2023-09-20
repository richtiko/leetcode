class Solution {
    public int longestAwesome(String s) {
        int isOdd = 0;
        Map<Integer, Integer> isOddMap = new HashMap<>();
        int maxLength = 0;
        for(int i = 0 ; i < s.length() ; ++i) {
            if(!isOddMap.containsKey(isOdd)) {
                isOddMap.put(isOdd, i);
            }
            
            isOdd = isOdd ^ (1<<Character.getNumericValue(s.charAt(i)));

            if(isOddMap.containsKey(isOdd)) {
                maxLength = Integer.max(maxLength, i-isOddMap.get(isOdd)+1);
            } 
            for(int j = 0 ; j <= 9 ; ++j) {
                isOdd = isOdd^(1<<j);
                if(isOddMap.containsKey(isOdd)) {
                    maxLength = Integer.max(maxLength, i-isOddMap.get(isOdd)+1);
                }
                isOdd = isOdd ^ (1<<j);
            }
        }
        return maxLength;
    }
}