class Solution {
    private static int[][] nextInts = {{1},{2,0},{3,1},{4,2},{5,3},{6,4},{7,5},{8,6},{9,7},{8}};
    private static int mod = 7+1000000000;
    private List<Integer> lowI = new ArrayList<>();
    private List<Integer> highI = new ArrayList<>();
    private int[][] memo = new int[101][10];
    public int countSteppingNumbers(String low, String high) {
        for(int i=0;i<memo.length;++i){
            for(int j =0;j<memo[i].length;++j){
                //the -1 will eman that we have not set any number yet
                memo[i][j] = -1;
            }
        }
        //Create integer List for the bounds to make it esy when comparing with integers
        for(int i=0;i<low.length();++i){
            lowI.add((int)low.charAt(i)-(int)'0');
        }
        for(int i=0;i<high.length();++i){
            highI.add((int)high.charAt(i)-(int)'0');
        }
        int total = 0;
        for(int len = lowI.size() ; len <= highI.size() ;++len){
            total += (getCount( len) % mod);
            total %= mod;
        }
        return total;
    }
    private int getCount(int length){
        int count = 0;
        List<Integer> currentPath = new LinkedList<>();
        for(int i=1;i<10;++i) {
            if(length == lowI.size() && i < lowI.get(0)){
                //the first digit cannot be smaller then the first digit of the lower bound 
                // if the lengths are equal, otherwise the generated number will be smaller then the lower bound
                continue;
            } else if(length == highI.size() && i > highI.get(0)){
                //the first digit cannot be bigger then the first digit of the upper bound 
                // if the lengths are equal, otherwise the generated number will be bigger then the upper bound
                continue;
            }
            currentPath.add(i);
            boolean equalsLowerBound = length == lowI.size() && i <= lowI.get(0);
            boolean equalsUpperBound = length == highI.size() && i >= highI.get(0);
            count += (getCount(length-1, currentPath, equalsLowerBound, equalsUpperBound) % mod);
            count %= mod;
            currentPath.remove(0);
        }
        return count;
    }
    private  int getCount(int length, List<Integer> path, boolean equalsLowerBound, boolean equalsUpperBound){
        if(length == 0){
            return 1;
        }
        int last = path.get(path.size()-1);
        //if we have to check for the bounds we cannot use saved number because it does not consider the bounds
        if(!equalsLowerBound && !equalsUpperBound && memo[length][last] >= 0){
            return memo[length][last];
        }
        int count =0;
        for(int i=0;i<nextInts[last].length;++i){
            boolean nextEqualsLowerBound = equalsLowerBound;
            boolean nextEqualsUpperBound = equalsUpperBound;
            int nextDigitIndex=path.size();
            if(equalsLowerBound) {
                if(nextInts[last][i] < lowI.get(nextDigitIndex) ){
                    //if smaller then the lower bound do not continue generation
                    continue;
                } else if(nextInts[last][i] == lowI.get(nextDigitIndex)) {
                    //if euqls to the lower bound, need to keep cheking for next digits
                    nextEqualsLowerBound = true;
                } else {
                    //if the digit is bigger then the corresponding digit in the lower bound, 
                    //there is not need to keep cheking if we crossign the bound
                    nextEqualsLowerBound = false;
                }
            }
            if(equalsUpperBound) {
                if(nextInts[last][i] > highI.get(nextDigitIndex )){
                    //if bigger then upper bound do not continue the generation
                    continue;
                } else if(nextInts[last][i] == highI.get(nextDigitIndex)) {
                    //if euqls to the upper bound, need to keep cheking for next digits
                    nextEqualsUpperBound = true;
                } else {
                    //if the digit is smaller then the corresponding digit in the upper bound, 
                    //there is not need to keep cheking if we crossign the bound
                    nextEqualsUpperBound = false;
                }
            }
            path.add(nextInts[last][i]);
            count += (getCount(length-1, path, nextEqualsLowerBound, nextEqualsUpperBound)%mod);
            count %= mod;
            path.remove(path.size()-1);
        }
        //do not save the number if we had to check for the bounds
        if(!equalsLowerBound && !equalsUpperBound ){
            return memo[length][last] = count;
        }
        return count;
    }
}
