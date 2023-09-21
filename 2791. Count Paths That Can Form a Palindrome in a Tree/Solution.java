class Solution {
    Map<Integer, Integer> isOddCounts  = new HashMap<Integer, Integer>();
    Map<Integer, Integer> nodeToIsOddMap = new HashMap<>();
    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        long totalCount = 0;
        nodeToIsOddMap.put(0,0);
        for(int j = n-1;j>=0;--j){
            totalCount += countPaths(parent, s, j);
        }
        return totalCount;
    }
    private long countPaths(List<Integer> parent, String s, int j) {
        if(nodeToIsOddMap.containsKey(j)) {
            return 0;
        }
        
        long parentCount = countPaths(parent, s, parent.get(j));
        int parentIsOdd = nodeToIsOddMap.get(parent.get(j));
        int isOdd = parentIsOdd ^ (1<< (Integer.valueOf(s.charAt(j)) - Integer.valueOf('a')));

        long count = parentCount;
        if(isOddCounts.containsKey(isOdd)){
            count += isOddCounts.get(isOdd);
        }
        if(isOdd == 0) {
            count += 1;
        }
        for(int i=0;i<26;++i){
            isOdd = isOdd ^ (1 << i);
            if(isOddCounts.containsKey(isOdd)){
                count += isOddCounts.get(isOdd);
            } 
            if (isOdd == 0){
                count +=1;
            }
            isOdd = isOdd ^ (1 << i);
        }
        nodeToIsOddMap.put(j, isOdd);
        if(isOddCounts.containsKey(isOdd)){
            isOddCounts.put(isOdd, isOddCounts.get(isOdd)+1);
        } else {
            isOddCounts.put(isOdd, 1);
        }
        return count;
    }
}