public class Strassen {
    public static void main(String[] args){
        int[][] a = new int[8][8];
        int[][] b = new int[8][8];
        int m,n;
        for(m = 0;m<8;m++)
            for(n = 0;n<8;n++)
                a[m][n] = m*8+n;
        for(m = 0;m<8;m++)
            for(n = 0;n<8;n++)
                b[m][n] = 63-(m*8+n);
        System.out.println("Matrix A ("+a.length+" x "+a.length+")");
        print(a);
        System.out.println("Matrix B ("+b.length+" x "+b.length+")");
        print(b);
        int[][] ab = multiplyMatrix(a,b);
        System.out.println("Matrix A x B ("+ab.length+" x "+ab.length+") (Strassens Algo)");
        print(ab);
        System.out.println("Matrix A x B ("+ab.length+" x "+ab.length+") (Brute Force Algo)");
        matrix_mul(a,b);
    }
    static void matrix_mul(int[][] a, int[][] b){
        int[][] c = new int[a.length][b[0].length];
        for(int i = 0;i<a.length;i++)
            for(int j = 0;j<b[0].length;j++){
                c[i][j] = 0;
                for(int k = 0;k<a[0].length;k++) c[i][j] += a[i][k]*b[k][j];
            }
        print(c);
    }
    static void print(int[][] a){
        int n = a[0].length;
        int m = a.length;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++)
                System.out.print(String.format("%6d",a[i][j])+" ");
            System.out.println();
        }
        System.out.println();
    }
    static int[][] multiplyMatrix(int[][] a, int[][] b){
        int[][] c = null;
        if(a.length == 1){
            c = new int[1][1];
            c[0][0] = a[0][0]*b[0][0];
        }
        if(a.length>1){
            c = new int[a.length][a[0].length];
            int[][] a_one_one = subMatrix(a,0,0,a.length/2,a[0].length/2);
            int[][] a_one_two = subMatrix(a,0,a[0].length/2,a.length/2,a[0].length);
            int[][] a_two_one = subMatrix(a,a.length/2,0,a.length,a[0].length/2);
            int[][] a_two_two = subMatrix(a,a.length/2,a[0].length/2,a.length,a[0].length);
            int[][] b_one_one = subMatrix(b,0,0,b.length/2,b[0].length/2);
            int[][] b_one_two = subMatrix(b,0,b[0].length/2,b.length/2,b[0].length);
            int[][] b_two_one = subMatrix(b,b.length/2,0,b.length,b[0].length/2);
            int[][] b_two_two = subMatrix(b,b.length/2,b[0].length/2,b.length,b[0].length);
            int[][] d_one = multiplyMatrix(addMatrix(a_one_one,a_two_two),addMatrix(b_one_one,b_two_two));
            int[][] d_two = multiplyMatrix(addMatrix(a_two_one,a_two_two),b_one_one);
            int[][] d_three = multiplyMatrix(a_one_one,subtractMatrix(b_one_two,b_two_two));
            int[][] d_four = multiplyMatrix(a_two_two,subtractMatrix(b_two_one,b_one_one));
            int[][] d_five = multiplyMatrix(addMatrix(a_one_one,a_one_two),b_two_two);
            int[][] d_six = multiplyMatrix(subtractMatrix(a_two_one,a_one_one),addMatrix(b_one_one,b_one_two));
            int[][] d_seven = multiplyMatrix(subtractMatrix(a_one_two,a_two_two),addMatrix(b_two_one,b_two_two));
            c = fitIntoC(subtractMatrix(addMatrix(addMatrix(d_one,d_four),d_seven),d_five),addMatrix(d_three,d_five),addMatrix(d_two,d_four),subtractMatrix(addMatrix(addMatrix(d_one,d_three),d_six),d_two));
        }
        return c;
    }
    static int[][] fitIntoC(int[][] a,int[][] b,int[][] c,int[][] d){
        int[][] out = new int[2*a.length][2*a.length];
        int i,j;
        for(i = 0;i<a.length;i++){
            for(j = 0;j<a.length;j++){
                out[i][j] = a[i][j];
            }
        }
        for(i = 0;i<b.length;i++){
            for(j = b.length;j<2*b.length;j++){
                out[i][j] = b[i][j-b.length];
            }
        }
        for(i = c.length;i<2*c.length;i++){
            for(j = 0;j<c.length;j++){
                out[i][j] = c[i-c.length][j];
            }
        }
        for(i = d.length;i<2*d.length;i++){
            for(j = d.length;j<2*d.length;j++){
                out[i][j] = d[i-d.length][j-d.length];
            }
        }
        return out;
    }
    static int[][] addMatrix(int[][] a,int[][] b){
        int[][] c = new int[a.length][a.length];
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a[0].length;j++){
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    static int[][] subtractMatrix(int[][] a,int[][] b){
        int[][] c = new int[a.length][a.length];
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a[0].length;j++){
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
    static int[][] subMatrix(int[][] a,int row_top,int column_left,int row_down,int column_right){
        int[][] b = new int[row_down-row_top][column_right-column_left];
        for(int i = row_top;i<row_down;i++){
            for(int j = column_left;j<column_right;j++){
                b[i-row_top][j-column_left] = a[i][j];
            }
        }
        return b;
    }
}
