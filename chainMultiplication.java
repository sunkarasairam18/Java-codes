import java.util.ArrayList;

public class chainMultiplication{
    int[] p_values;
    ArrayList<int[][]> matrix_list;
    int[][] optimal_operations;
    int[][] k_values;
    int[][] result;
    chainMultiplication(ArrayList<int[][]> matrix_list){
        this.matrix_list = matrix_list;
        int s = matrix_list.size();
        optimal_operations = new int[s+1][s+1];
        k_values = new int[s+1][s+1];
        p_values = new int[s+1];
        p_values[0] = matrix_list.get(0).length;
        for(int i = 1;i<=s;i++){
            p_values[i] = matrix_list.get(i-1)[0].length;
            optimal_operations[0][i] = i;
            optimal_operations[i][0] = i;
            k_values[0][i] = i;
            k_values[i][0] = i;
        }
    }
    void parenthesize(){   // finds k values
        int c = 1;
        int l = matrix_list.size();
        while(c < l){
            int i = 1;
            while(i+c<=l){
                int j = i+c,optimal_k = 0;
                int least = Integer.MAX_VALUE;
                for(int k = i;k<j;k++){
                    int value = optimal_operations[i][k] + optimal_operations[k+1][j] + (p_values[i-1]*p_values[k]*p_values[j]);
                    if(value<least){
                        optimal_k = k;
                        least = value;
                    }
                }
                optimal_operations[i][j] = least;
                k_values[i][j] = optimal_k;
                i++;
            }
            c++;
        }
    }
    void display_operations(){
        System.out.println("        OPERATIONS        ");
        int l = matrix_list.size();
        for(int i = 0;i<=l;i++){
            for(int j = 0;j<=l;j++){
                System.out.printf("%4d", optimal_operations[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }
    void display_k_values(){
        System.out.println("        K-VALUES        ");
        int l = matrix_list.size();
        for(int i = 0;i<=l;i++){
            for(int j = 0;j<=l;j++){
                System.out.printf("%4d",k_values[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }
    void calculate_result(){
        result = mul(1, matrix_list.size());
    }
    void display_result(){
        System.out.println("                    RESULTANT MATRIX       ");
        int m = result.length;
        int n = result[0].length;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                System.out.printf("%10d",result[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }
    int[][] mul(int i,int j){
        if(i<j){
            int[][] one = mul(i,k_values[i][j]);
            int[][] two = mul(k_values[i][j]+1,j);
            return matrix_mul(one,two);
        }
        return matrix_list.get(i-1);
    }
    private int[][] matrix_mul(int[][] a, int[][] b){
        int[][] c = new int[a.length][b[0].length];
        for(int i = 0;i<a.length;i++)
            for(int j = 0;j<b[0].length;j++){
                c[i][j] = 0;
                for(int k = 0;k<a[0].length;k++) c[i][j] += a[i][k]*b[k][j];
            }
        return c;
    }
    public static void main(String[] args){
        int[][] a1 = {{3,4,5,6},{1,3,4,5},{3,7,8,6},{1,6,8,12},{11,4,5,32}}; // 5 X 4
        int[][] a2 = {{13,4,5,6,2,1},{4,3,44,3,55,22},{6,7,55,4,12,11},{54,72,2,23,12,2}};  // 4 X 6
        int[][] a3 = {{5,6},{12,6},{7,6},{1,4},{5,8},{2,9}};  // 6 X 2
        int[][] a4 = {{23,2,1,19,36,3,8},{29,1,61,2,7,9,11}};  // 2 X 7
        ArrayList<int[][]> matrix_list = new ArrayList<int[][]>();
        matrix_list.add(a1);
        matrix_list.add(a2);
        matrix_list.add(a3);
        matrix_list.add(a4);
        chainMultiplication calculate = new chainMultiplication(matrix_list);
        calculate.parenthesize();
        calculate.display_operations();
        calculate.display_k_values();
        calculate.calculate_result();
        calculate.display_result();
    }
}

