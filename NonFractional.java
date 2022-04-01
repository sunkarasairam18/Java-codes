import java.util.ArrayList;

public class NonFractional {
    ArrayList<item> list;
    ArrayList<Integer> Knapsack_bag = new ArrayList<Integer>();
    int[][] B;
    int n;
    int w;
    NonFractional(ArrayList<item> list,int weight){
        this.list = list;
        n = list.size();
        w = weight;
        B = new int[n+1][w+1];
    }
    void subset_max_benefit(){
        int track_weight = w;
        for(int i = 1;i <= n;i++){
            for(int j = 0;j <= w;j++){
                if(list.get(i-1).weight<=j){
                    if(list.get(i-1).benefit+B[i-1][j-list.get(i-1).weight]>B[i-1][j]){
                        B[i][j] = list.get(i-1).benefit + B[i-1][j-list.get(i-1).weight];
                    }else{
                        B[i][j] = B[i-1][j];
                    }
                }else{
                    B[i][j] = B[i-1][j];
                }
            }
        }
    }
    void include_items(){
        int i = n,k = w;
        while(i>0 && k>0){
            if(B[i][k]!=B[i-1][k]){
                Knapsack_bag.add(i);
                k = k-list.get(i-1).weight;
            }
            i = i - 1;
        }
    }
    void show_knapsack_bag(){
        for(int i = 0;i<Knapsack_bag.size();i++){
            System.out.println("Type : "+list.get(Knapsack_bag.get(i)-1).type+", Weight : "+list.get(Knapsack_bag.get(i)-1).weight+", Benefit : "+list.get(Knapsack_bag.get(i)-1).benefit);
        }
    }
    void show_B(){
        for(int i = 0;i<=n;i++){
            for(int j = 0;j<=w;j++){
                System.out.printf("%2d",B[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        ArrayList<item> list = new ArrayList<item>();
        item one = new item(1,2,3);
        list.add(one);
        one = new item(2,3,4);
        list.add(one);
        one = new item(3,4,5);
        list.add(one);
        one = new item(4,5,6);
        list.add(one);
        NonFractional knapsack = new NonFractional(list,7);
        knapsack.subset_max_benefit();
        knapsack.include_items();
        knapsack.show_knapsack_bag();
        knapsack.show_B();
    }
}
class item{
    int type;
    int benefit;
    int weight;
    item(int type,int weight,int benefit){
        this.type = type;
        this.benefit = benefit;
        this.weight = weight;
    }
}