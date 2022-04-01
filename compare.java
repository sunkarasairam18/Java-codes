import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class compare {
    public static void main(String[] args){
        ArrayList<TypeOfMaterial> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int n,i = 1,size,value,bag_size = 20;
//        System.out.print("Enter Number of Items : ");
//        n = input.nextInt();
//        while(i <= n){
//            System.out.print("Enter size of type "+i+" Material : ");
//            size = input.nextInt();
//            System.out.print("Enter value of type "+i+" Material : ");
//            value = input.nextInt();
//            list.add(new TypeOfMaterial(i,size,value));
//            i++;
//        }
//        System.out.print("Enter Bag size : ");
//        bag_size = input.nextInt();
        list.add(new TypeOfMaterial(1,18,25));
        list.add(new TypeOfMaterial(2,15,24));
        list.add(new TypeOfMaterial(3,10,15));
        System.out.println("STRATEGY ONE ");
        list.sort(new strategy_one());
        knapsack_solution(list,bag_size);
        System.out.println("STRATEGY TWO ");
        list.sort(new strategy_two());
        knapsack_solution(list,bag_size);
        System.out.println("STRATEGY THREE ");
        list.sort(new strategy_three());
        knapsack_solution(list,bag_size);
    }
    static void show(ArrayList<TypeOfMaterial> one){
        for(TypeOfMaterial node:one){
            System.out.println(node.type+" "+node.size+" "+node.value);
        }
    }
    static void knapsack_solution(ArrayList<TypeOfMaterial> a,int bag){
        float value = 0;
        for(TypeOfMaterial i:a){
            if(bag <= 0) break;
            if(bag >= i.size){
                System.out.println("Type "+i.type+" with "+i.size+"/"+i.size+" part of value "+i.value);
                value += i.value;
            }
            else{
                System.out.println("Type "+i.type+" with "+(bag)+"/"+i.size+" part of value "+i.value);
                value += ((float)(bag)/(float)(i.size))*i.value;
            }
            bag -= i.size;
        }
        System.out.println("Total value = "+value+" \n");
    }
}
class TypeOfMaterial{
    int type;
    int size;
    int value;
    float ratio;
    TypeOfMaterial(int type,int size,int value){
        this.type = type;
        this.size = size;
        this.value = value;
        this.ratio = (float)value/(float)size;
    }
}
class strategy_one implements Comparator<TypeOfMaterial>{   //Non Increasing based on value
    public int compare(TypeOfMaterial one,TypeOfMaterial two){
        int i = -1;
        if(one.value < two.value) i = 1;
        if(one.value == two.value) i = 0;
        return i;
    }
}
class strategy_two implements Comparator<TypeOfMaterial>{    //Non Decreasing based on size
    public int compare(TypeOfMaterial one,TypeOfMaterial two){
        int i = 1;
        if(one.size < two.size) i = -1;
        if(one.size == two.size) i = 0;
        return i;
    }
}
class strategy_three implements Comparator<TypeOfMaterial>{   //Non Increasing based on ratio
    public int compare(TypeOfMaterial one,TypeOfMaterial two){
        int i = -1;
        if(one.ratio < two.ratio) i = 1;
        if(one.ratio == two.ratio) i = 0;
        return i;
    }
}
