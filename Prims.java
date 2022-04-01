import java.io.*;
import java.util.*;

class span_node{
    String destination;
    String source;
    Double weight;
    span_node(String destination,String source,Double weight){
        this.destination = destination;
        this.source = source;
        this.weight = weight;
    }
}
public class Prims {
    ArrayList<span_node> mst = new ArrayList<span_node>();
    ArrayList<span_node> not_mst = new ArrayList<span_node>();
    Graph graph;
    Prims(Graph graph){
        this.graph = graph;
        mst.add(new span_node(graph.list_of_vertices.get(0).id,null,null));
        for(int c = 1;c<graph.list_of_vertices.size();c++){
            not_mst.add(new span_node(graph.list_of_vertices.get(c).id,null,Double.POSITIVE_INFINITY));
        }
        heapify();
    }
    void find_mst(){
        int l = graph.list_of_vertices.size();
        span_node fresh = mst.get(0);
        while(not_mst.size() != 0){
            String s = fresh.destination;
            vertex var = graph.list_of_vertices.get(graph.table.get(s));
            while (var!=null){
                for(span_node node:not_mst){
                    if(node.destination.equals(var.id) && node.weight> var.weight){
                        node.source = s;
                        node.weight = var.weight;
                    }
                }
                var = var.next;
            }
            heapify();
            if(not_mst.size() != 0){
                fresh = del_min();
                mst.add(fresh);
            }
        }
    }
    void print(){
        System.out.println();
        for(span_node s:mst){
            System.out.println(s.destination +" ("+s.source+", "+s.weight+") ");
        }
    }
    private span_node del_min(){
        span_node no = not_mst.get(0);
        not_mst.set(0,not_mst.get(not_mst.size()-1));
        not_mst.remove(not_mst.size()-1);
        sink(0);
        return no;
    }
    private void heapify(){
        int l = not_mst.size();
        for(int n = l/2;n>=0;n--){
            sink(n);
        }
    }
    private void sink(int i){
        int j = 2*i+1;
        int m;
        int l = not_mst.size();
        if(j >= l) return;
        while(j<l){
            if(j+1 < l) if(not_mst.get(j).weight > not_mst.get(j+1).weight) j++;
            if(not_mst.get(i).weight > not_mst.get(j).weight){
                span_node no = not_mst.get(i);
                not_mst.set(i,not_mst.get(j));
                not_mst.set(j,no);
                i = j;
                j = 2*i+1;
            }else break;
        }
    }
    public static void main(String... args){
        Graph one = new Graph();
        Prims m = new Prims(one);
        m.find_mst();
        m.print();
    }

}

class Graph{
    ArrayList<vertex> list_of_vertices = new ArrayList<>();
    Hashtable<String,Integer> table = new Hashtable<>();
    Graph(){
        try{
            String s = "";
            Scanner input = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int i = 0;
            while(true){
                System.out.print("\nEnter id of vertex : ");
                s = reader.readLine();
                if(s.equals("end")) break;
                list_of_vertices.add(new vertex(s,null,null));
                this.table.put(s,i);
                i++;
            }
            String first;
            double weight;
            String second;
            while(true){
                System.out.println("\nEnter first and second id's to connect");
                System.out.print("\nEnter first id : ");
                first = reader.readLine();
                if(first.equals("end")) break;
                System.out.print("\nEnter second id : ");
                second = reader.readLine();
                System.out.print("\nEnter weight of edge between "+first+" and "+second+" : ");
                weight = input.nextDouble();
                connect(first,second,weight);
            }
            display();
        }catch (Exception e){
            System.out.println("Input error");
        }
    }
    private void connect(String first,String second,Double weight){
        vertex track_first = null;
        vertex track_second = null;
        vertex second_to_first = new vertex(second,weight,null);
        vertex first_to_second = new vertex(first,weight,null);
        track_first = list_of_vertices.get(table.get(first));
        track_second = list_of_vertices.get(table.get(second));
        while(track_first.next != null){
            track_first = track_first.next;
        }
        track_first.next = second_to_first;
        while(track_second.next != null){
            track_second = track_second.next;
        }
        track_second.next = first_to_second;

    }
    void display(){
        for(vertex ver: list_of_vertices){
            System.out.print(ver.id +" -> ");
            if(ver.next != null){
                vertex track = ver.next;
                while(track != null){
                    if(track.next != null) System.out.print(track.id+","+track.weight+" -> ");
                    else System.out.print(track.id+","+track.weight);
                    track = track.next;
                }
            }
            System.out.println();
        }
    }
}
class vertex{
    String id;
    Double weight;
    vertex next;
    vertex(String id,Double weight,vertex next){
        this.id = id;
        this.weight = weight;
        this.next = next;
    }
}