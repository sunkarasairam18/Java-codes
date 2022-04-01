import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Huffman {
    static void decoding(String name, String decode_file_name, Node tree_head){
        try{
            File file = new File(name);
            Node tracker = tree_head;
            Scanner reader_three = new Scanner(file);
            String new_file = "";
            while(reader_three.hasNextLine()){
                String s = reader_three.nextLine();
                int l = s.length();
                for(int c = 0;c<l;c++){
                    if(s.charAt(c) == '0') tracker = tracker.left;
                    else tracker = tracker.right;
                    if(tracker.key != null){
                        if(tracker.key.equals("\\n")){
                            new_file += "\n";
                        }
                        else{
                            new_file += tracker.key;
                        }
                        tracker = tree_head;      //after code for certain character found,it has to calculate character for other character
                    }
                }
            }
            reader_three.close();
            FileWriter writer = new FileWriter(decode_file_name);  //creates encoded file
            writer.write(new_file);     //writes at a time
            writer.close();
        }catch (Exception e){
            System.out.println("Can't read");
        }
    }
    public static void main(String... args){
        encoding("Text.txt","encoded.txt");
        decoding("encoded.txt","decoded.txt",tree_head);
    }
    static Node track;  //To refer a node when calculating frequency
    static Node tree_head;   // Tree head of huffman
    static ArrayList<Node> character_code = new ArrayList<Node>();  //After finding code for each character,this list can be used to match character with it's code
    static void encoding(String name,String encode_name){
        File file = new File(name);
        node_Priority_Queue PQ_of_characters = new node_Priority_Queue();   //Count of each character,useful while forming tree
        try{
            Scanner reader_one = new Scanner(file);
            while (reader_one.hasNextLine()){
                String s = reader_one.nextLine();
                int l = s.length();
                for(int c = 0;c < l;c++){
                    if(contains(PQ_of_characters.list_of_characters,""+s.charAt(c))) track.frequency++;
                    else{
                        PQ_of_characters.add(new Node(""+s.charAt(c),1));
                    }
                }
                if(reader_one.hasNextLine()){
                    if(contains(PQ_of_characters.list_of_characters,"\\n")) track.frequency++;
                    else{
                        PQ_of_characters.add(new Node("\\n",1));
                    }
                }
            }
            reader_one.close();
            PQ_of_characters.hepify();  //Forms a heap
            for (Node list_of_character : PQ_of_characters.list_of_characters) {
                System.out.println(list_of_character.key + ", " + list_of_character.frequency);
            }
            form_a_tree(PQ_of_characters);  //Forms huffman tree
            fill_character_code();    //Finds code for each character
            try{
                Scanner reader_two = new Scanner(file);
                String bits = "";
                while (reader_two.hasNextLine()){
                    String s = reader_two.nextLine();
                    int l = s.length();
                    for(int c = 0;c < l;c++){
                        if(contains(character_code,""+s.charAt(c))) bits += track.code;   //track variable finds the code,while calling contains function
                    }
                    if(reader_two.hasNextLine()) if(contains(character_code,"\\n")) bits+= track.code;
                }
                reader_two.close();
                FileWriter output = new FileWriter(encode_name);  //new encoded file
                output.write(bits); //writing to encoded file at a time
                output.close();
            }catch (Exception e){
                System.out.println("Can't Write");
            }
            for (Node list_of_character : character_code) {
                System.out.println(list_of_character.key + ", " + list_of_character.code);
            }
        }catch (Exception e){
            System.out.println("Can't Read");
        }

    }
    static String s = "";
    static void fill_character_code(){
        find_code(tree_head);
    }
    static void find_code(Node head){
        if(head.key == null){
            s += "0";   // left branch zero
            find_code(head.left);
            s = s.substring(0,s.length()-1);
            s += "1";   // right branch one
            find_code(head.right);
            s = s.substring(0,s.length()-1);
        }
        else{
            character_code.add(new Node(head.key, s));
        }
    }
    static void form_a_tree(node_Priority_Queue list){
        while (list.list_of_characters.size()!=1){
            Node node_one = list.del_min();   //Poping two characters with least frequency
            Node node_two = list.del_min();
            Node create = new Node(null, node_one.frequency+ node_two.frequency);
            create.right = node_one;
            create.left = node_two;
            list.add_maintain_heap(create);
        }
        tree_head = list.list_of_characters.get(0);   //at last one node will be in priority queue
    }
    static boolean contains(ArrayList<Node> f, String s){
        for (Node node : f) {
            track = node;
            if (track.key.equals(s)) return true;
        }
        return false;
    }
}
class node_Priority_Queue{
    ArrayList<Node> list_of_characters = new ArrayList<Node>();
    void add(Node item){
        list_of_characters.add(item);
    }
    void add_maintain_heap(Node item){
        list_of_characters.add(item);
        swim();
    }
    void hepify(){
        int i = (list_of_characters.size()-1)/2;
        while (i >= 0){
            sink(i);
            i--;
        }
    }
    private void sink(int index){
        int j = 2*index + 1;
        int l = list_of_characters.size();
        while(j<l){
            if(j+1 < l) if(list_of_characters.get(j).frequency>list_of_characters.get(j+1).frequency) j++;
            if(list_of_characters.get(index).frequency>list_of_characters.get(j).frequency){
                Node change = list_of_characters.get(index);
                list_of_characters.set(index,list_of_characters.get(j));
                list_of_characters.set(j,change);
                index = j;
                j = 2*index+1;
            }else break;
        }
    }
    private void swim(){
        int i = list_of_characters.size() - 1;
        int j = i/2;
        while(i>0){
            if(list_of_characters.get(j).frequency>list_of_characters.get(i).frequency){
                Node change = list_of_characters.get(i);
                list_of_characters.set(i,list_of_characters.get(j));
                list_of_characters.set(j,change);
                i = j;
                j = i/2;
            }else break;
        }
    }
    Node del_min(){
        Node top = list_of_characters.get(0);
        list_of_characters.set(0,list_of_characters.get(list_of_characters.size()-1));
        list_of_characters.remove(list_of_characters.size()-1);
        sink(0);
        return top;
    }
}
class Node{
    Node left;
    Node right;
    String key;
    int frequency;
    String code;
    Node(String key, int frequency){
        this.key = key;
        this.frequency = frequency;
    }
    Node(String key, String code){
        this.key = key;
        this.code = code;
    }
}