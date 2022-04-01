import java.util.Scanner;

public class quick_union {
    int[] elements;
    int[] size;
    quick_union(int n){
        elements = new int[n];
        size = new int[n];
        for(int i = 0;i<n;i++)
            make_set(i);
    }
    void make_set(int i){
        elements[i] = i;
        size[i] = 1;
    }
    int find(int i){
        while(elements[i]!=i){
            elements[i] = elements[elements[i]];  //Path compression
            i = elements[i];
        }
        return i;
    }
    void union(int a,int b){
        int head_a = find(a);
        int head_b = find(b);
        if(head_a == head_b) return;
        if(size[head_a] < size[head_b]){
            elements[head_a] = head_b;
            size[head_b] += size[head_a];
            System.out.println("\nTree with root "+head_a+" is attached to root "+head_b);

        }else{
            elements[head_b] =  head_a;
            size[head_a] += size[head_b];
            System.out.println("\nTree with root "+head_b+" is attached to root "+head_a);

        }
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n,a,b;
        System.out.print("Enter number of nodes : ");
        n = input.nextInt();
        quick_union one = new quick_union(n);
        while(true){
            System.out.print("Enter a = ");
            a = input.nextInt();
            System.out.print("Enter b = ");
            b = input.nextInt();
            if(a == -1 || b == -1) break;
            one.union(a,b);
            one.show();
            System.out.println("\n");
        }
    }
    void show(){
        System.out.print("\nkeys   : ");
        int i;
        for(i = 0;i<elements.length-1;i++) System.out.print(i+" , ");
        System.out.print(i+" (If key and parent are Equal then the key is root of tree)");
        System.out.print("\nparent : ");
        for(i = 0;i<elements.length - 1;i++) System.out.print(elements[i]+" , ");
        System.out.print(elements[elements.length-1]);
    }

}
