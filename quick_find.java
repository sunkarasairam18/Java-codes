import java.util.*;

public class quick_find{
    int[] element_set_id;
    ArrayList<start_node> list = new ArrayList<start_node>();
    quick_find(int[] elements){
        int l = elements.length;
        element_set_id = new int[l];
        for(int i = 0;i<l;i++){
            Make_set(i);
        }
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int a,b;
        int[] nodes = {0,1,2,3,4,5};
        quick_find one = new quick_find(nodes);
        one.display();
        while(true){
            System.out.print("\nEnter a = ");
            a = input.nextInt();
            System.out.print("Enter b = ");
            b = input.nextInt();
            if(a == -1 || b == -1) break;
            one.union(a,b);
            one.display();
            System.out.println();
        }
    }
    void Make_set(int n){
            element_set_id[n] = n;
            list_node l_node = new list_node(n,null);
            start_node create = new start_node(l_node);
            list.add(create);
    }
    int find(int n){
        return element_set_id[n];
    }
    void union(int a,int b){
        if(find(a)!=find(b)){
            int l = list.size();
            int i,j;
            for(i = 0;i<l;i++){
                if(list.get(i).first.data == element_set_id[a]){
                    break;
                }
            }
            for(j = 0;j<l;j++){
                if(list.get(j).first.data == element_set_id[b]){
                    break;
                }
            }
            if(list.get(i).size > list.get(j).size){
                start_node three = list.get(j);
                list.set(j,list.get(i));
                list.set(i,three);
                int c = a;
                a = b;
                b = c;
            }

            list_node update = list.get(i).first;
            while (update != null){
                element_set_id[update.data] = element_set_id[b];
                update = update.next;
            }
            list.get(j).size += list.get(i).size;
            list.get(j).last.next = list.get(i).first;
            list.get(j).last = list.get(i).last;
            list.remove(i);
        }
    }
    void display(){
        for(start_node one:list){
            list_node track = one.first;
            System.out.print("size : "+one.size+" ,list : ");
            while(track!=null){
                System.out.printf("%2d -> ",track.data);
                track = track.next;
            }
            System.out.print("null\n");
        }
    }
}
class start_node{
    int size;
    list_node first;
    list_node last;
    start_node(list_node one){
        size = 1;
        this.first = one;
        this.last = one;
    }
}
class list_node{
    int data;
    list_node next;
    list_node(int data,list_node next){
        this.data = data;
        this.next = next;
    }
}
