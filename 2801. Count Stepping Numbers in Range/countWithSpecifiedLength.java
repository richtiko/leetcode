class Solution {
    private static int[][] nextInts = {{1},{2,0},{3,1},{4,2},{5,3},{6,4},{7,5},{8,6},{9,7},{8}};
    private static int mod = 7+1000000000;

    public int countSteppingNumbers(String low, String high) {
        
        return getCount( low.length()) % mod;
    }
    private  int getCount( int length){
        int count = 0;
        List<Integer> currentPath = new LinkedList<>();
        for(int i=1;i<10;++i) {
            currentPath.add(i);
            count += (getCount(length-1, currentPath) % mod);
            count %= mod;
            currentPath.remove(0);
        }
        return count;
    }
    private  int getCount(int length, List<Integer> path){
        if(length == 0){
            return 1;
        }
        int last = path.get(path.size()-1);
        int count =0;
        for(int i=0;i<nextInts[last].length;++i){
            
            path.add(nextInts[last][i]);
            count += (getCount(length-1, path)%mod);
            count %= mod;
            path.remove(path.size()-1);
        }
       
        return count;
    }
}