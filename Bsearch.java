public class Bsearch{
    private int target;
    private int a[];
    private int binary_search(int low,int hi){
        if(low<=hi){
            int mid = (low+hi)/2;
            if(a[mid] == target){
                return mid;
            }
            else if(target < a[mid]){
                return binary_search(low,(mid-1));
            }
            else{
                return binary_search((mid+1),hi);
            }
        }
        return -1;
    }
    void Binary_search(int[] a,int target){
        this.target = target;
        this.a = a;
        int index = binary_search(0,(a.length-1));
        if( index == -1){
            System.out.println("Target Element "+ target+" Not Present");
        }
        else System.out.println(target+" is at index "+index);
    }
    public static void main(String[] args){
        int[] a ={2,3,4,5,6,7,8,9,10,15,18,20,23,28,29,31,33,45,46,54,55,87,91,109,199,234};
        Bsearch one = new Bsearch();
        one.Binary_search(a,23);
    }
}
