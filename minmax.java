class minMax {
    private static class node{
        int min;
        int max;
        void status() {
            System.out.println("Min : "+this.min+", Max: "+this.max);
        }
    }
    node MinMax(int[] k, int low, int hi){
        if(hi-low==1 || hi == low){
            node one = new node();
            one.min = Math.min(k[low], k[hi]);
            one.max = Math.max(k[low],k[hi]);
            return one;
        }
        int mid = (low+hi)/2;
        node two = MinMax(k,low,mid-1);
        node three = MinMax(k,mid,hi);
        node returning = new node();
        returning.min = Math.min(two.min,three.min);
        returning.max = Math.max(two.max, three.max);
        return returning;
    }
    public static void main(String... args){
        int[] a = {34,-20,-11,34,27,428,23,-32,31,47,49,94,25,10};
        minMax Ob = new minMax();
        node one = Ob.MinMax(a,0,a.length-1);
        one.status();
    }
}
