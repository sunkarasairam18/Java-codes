public class Good {
    static void heapify(int[] i){
        for(int n = i.length/2;n>=0;n--){
            sink(n,i);
        }
    }
    static void display(int[] i){
        for(int j = 0;j<i.length;j++){
            System.out.print(i[j]+" ");
        }
        System.out.println();
    }
    static void sink(int i,int[] a){
        int j = 2*i+1;
        int m;
        if(j >= a.length) return;
        while(j<a.length){
            if(j+1 < a.length) if(a[j] < a[j+1]) j++;
            if(a[i] < a[j]){
                m = a[i];
                a[i] = a[j];
                a[j] = m;
                i = j;
                j = 2*i+1;
            }else break;
        }
    }
    public static void main(String... args){
        int[] a = {2,3,4,5,6,7};
        display(a);
        heapify(a);
        display(a);
    }
}
