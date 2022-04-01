public class subsequence {
    int m,n;
    int[][] count;
    String[][] pointers;
    String one,two,LCS = "";
    int length_of_subsequence;
    subsequence(String one,String two){
        this.one = one;
        this.two = two;
        this.m = one.length();
        this.n = two.length();
        count = new int[m+1][n+1];
        pointers = new String[m+1][n+1];
        for(int i = 0;i<m+1;i++) pointers[i][0] = "SKIP_X";
        for(int j = 0;j<n+1;j++) pointers[0][j] = "SKIP_Y";
        find_length();
        find_LCS();
    }
    void find_length(){
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(one.charAt(i-1) == two.charAt(j-1)){
                    count[i][j] = count[i-1][j-1] + 1;
                    pointers[i][j] = "ADD_XY";
                }
                else if(count[i-1][j] >= count[i][j-1]){
                    count[i][j] = count[i-1][j];
                    pointers[i][j] = "SKIP_X";
                }
                else{
                    count[i][j] = count[i][j-1];
                    pointers[i][j] = "SKIP_Y";
                }
            }
        }
        length_of_subsequence = count[m][n];
    }
    void find_LCS(){
        int i = m,j = n;
        while(i!=0 && j!=0){
            if(pointers[i][j] == "ADD_XY"){
                LCS = two.charAt(j-1) + LCS;
                i--;
                j--;
                continue;
            }
            if(pointers[i][j] == "SKIP_X"){
                i--;
                continue;
            }
            if(pointers[i][j] == "SKIP_Y"){
                j--;
            }
        }
    }
    public static void main(String[] args){
        subsequence find = new subsequence("TOMJERRY","JERY");
        System.out.println(find.LCS);
    }
}
