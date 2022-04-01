public class Merge {
    private int[] a;
    void print(int[] a){
        for (int j : a) System.out.print(j + " ");
        System.out.println();
    }
    void merge(int low,int hi,int mid){
        int k,i,j;
        int[] aux = new int[a.length];
        for(i=low;i<hi+1;i++) aux[i] = a[i];
        i = low;
        j = mid + 1;
        for(k=low;k<hi+1;k++)
        {
            if(i>mid) a[k] = aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(aux[i] < aux[j]) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }
    void merge_sort(int low,int hi){
        if(hi>low){
            int mid = low+((hi-low)/2);
            merge_sort(low,mid);
            merge_sort((mid+1),hi);
            merge(low,hi,mid);
        }
    }
    int[] Merge_Sort(int[] a){
        this.a = a;
        merge_sort(0,a.length-1);
        return this.a;
    }
    public static void main(String[] args){
        int[] a = {4,35,74,21,34,23,494,434,91,99,78,89,102,3,33,30};
        Merge one = new Merge();
        one.print(a);
        a = one.Merge_Sort(a);
        one.print(a);
    }
}
