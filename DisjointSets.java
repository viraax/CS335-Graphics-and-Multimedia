public class DisjointSets {
    int[] testArr;

    public DisjointSets(int numElements){
        testArr = new int[numElements];
        for(int i = 0; i< testArr.length; i++)
            testArr[i] = -1;
    }


    public void union(int pos, int pos1){
        if(testArr[pos1]< testArr[pos]){
            testArr[pos1]+= testArr[pos];
            testArr[pos]=pos1;

        }
        else{
            testArr[pos]+= testArr[pos1];
            testArr[pos1]=pos;
        }
    }

    public int find(int x){
        if(testArr[x]<0)
            return x;
        else
            return testArr[x]=find(testArr[x]);
    }

    public String toString(){
        String ans = "";
        for(int i = 0; i < testArr.length; i++)
            ans += "     " + testArr[i];
        return ans;

    }
}