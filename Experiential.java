import java.util.*;
import java.io.*;
import java.lang.String;
public class Experiential {
    public static void main(String[] args){
        try{
            frequency_tree file_one_words = new frequency_tree(); //avl tree for first file
            File file_one = new File("two.txt");
            Scanner reader_one = new Scanner(file_one);
            while(reader_one.hasNextLine()){
                String[] words_one = reader_one.nextLine().split(" ");
                for(String i:words_one){
                    file_one_words.put(i);
                }
            }
            System.out.print("File 1        :- ");
            file_one_words.display();
            reader_one.close();
            frequency_tree file_two_words = new frequency_tree();  ////avl tree for second file
            File file_two = new File("one.txt");
            Scanner reader_two = new Scanner(file_two);
            while(reader_two.hasNextLine()){
                String[] words_two = reader_two.nextLine().split(" ");
                for(String j:words_two){
                    file_two_words.put(j);
                }
            }
            System.out.print("File 2        :- ");
            file_two_words.display();
            reader_two.close();
        }catch (Exception e){
            System.out.println("File Not Found");
        }

    }
}
class frequency_tree {
    avl_node root = null;
    int left_right = 0;
    private avl_node track;
    private avl_node prev_track;
    void put(String key){
        avl_node fresh = new avl_node(key);
        if(root == null){
            root = fresh;
        }else{
            if(!contains(key)){
                if(left_right == -1){
                    prev_track.left = fresh;
                }
                if(left_right == 1){
                    prev_track.right = fresh;
                }
                calculate_balance(this.root);
                avl_balance(this.root,key);
            }else{
                track.value += 1;
            }
        }
    }
    private boolean contains(String key){
        track = root;
        prev_track = root;
        while(track!=null){
            prev_track = track;
            if(track.key.compareTo(key)>0){
                left_right = -1;
                track = track.left;
            }
            else if(track.key.compareTo(key)<0){
                left_right = 1;
                track = track.right;
            }else{
                break;
            }
        }
        return track != null;
    }
    private int height(avl_node root){
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return right>left?(right+1):(left+1);
    }
    void calculate_balance(avl_node root){
        if(root != null){
            root.balance = height(root.left) - height(root.right);
            calculate_balance(root.left);
            calculate_balance(root.right);
        }
    }
    void display(){
        avl_node trace = root;
        show_ascending(trace);
        System.out.println();
    }
    private void show_ascending(avl_node root){
        if(root != null){
            show_ascending(root.left);
            System.out.print("["+root.key+", " +root.value+", "+root.balance+"] ");
            show_ascending(root.right);
        }
    }
    void avl_balance(avl_node root, String key){
        if(root == null) return;
        avl_node prev = root;
        avl_node tracer = root;
        avl_node prev_unbalance = root;
        avl_node recent_unbalance = root;
        int i = 0,ind = 0;
        while(tracer!=null){
            if(tracer.balance>1 || tracer.balance<-1){
                prev_unbalance = prev;
                recent_unbalance = tracer;
                ind = i;
            }
            if(tracer.key.compareTo(key) == 0) break;
            else if(tracer.key.compareTo(key)<0){
                prev = tracer;
                tracer = tracer.right;
                i = 1;
            }else{
                prev = tracer;
                tracer = tracer.left;
                i = -1;
            }
        }
        if(recent_unbalance.balance>1 || recent_unbalance.balance<-1){
//            if(recent_unbalance.balance<-1 && height(recent_unbalance.right.right)>height(recent_unbalance.right.left)){ //Right right case
//                left_rotate(prev_unbalance,recent_unbalance,ind);
//            }
//            else if(recent_unbalance.balance>1 && height(recent_unbalance.left.right)<height(recent_unbalance.left.left)){ //Left left case
//                right_rotate(prev_unbalance,recent_unbalance,ind);
//            }
//            else if(recent_unbalance.balance>1 && height(recent_unbalance.left.right)>height(recent_unbalance.left.left)){ //Left right case
//                left_right_rotate(prev_unbalance,recent_unbalance,ind);
//            }
//            else if(recent_unbalance.balance<-1 && height(recent_unbalance.right.right)<height(recent_unbalance.right.left)){ //Right left case
//                right_left_rotate(prev_unbalance,recent_unbalance,ind);
//            }
            if(recent_unbalance.balance<-1 && recent_unbalance.right.balance<0){ //Right right case
                left_rotate(prev_unbalance,recent_unbalance,ind);
            }
            else if(recent_unbalance.balance>1 && recent_unbalance.left.balance>0){ //Left left case
                right_rotate(prev_unbalance,recent_unbalance,ind);
            }
            else if(recent_unbalance.balance>1 && recent_unbalance.left.balance<0){ //Left right case
                left_right_rotate(prev_unbalance,recent_unbalance,ind);
            }
            else if(recent_unbalance.balance<-1 && recent_unbalance.right.balance>0){ //Right left case
                right_left_rotate(prev_unbalance,recent_unbalance,ind);
            }
            calculate_balance(this.root);
        }
    }
    private void left_rotate(avl_node prev_unbalance, avl_node recent_unbalance, int ind){
        if(ind == -1){
            prev_unbalance.left = recent_unbalance.right;
            recent_unbalance.right = recent_unbalance.right.left;
            prev_unbalance.left.left = recent_unbalance;
        }else if(ind == 1){
            prev_unbalance.right = recent_unbalance.right;
            recent_unbalance.right = recent_unbalance.right.left;
            prev_unbalance.right.left = recent_unbalance;
        }else{
            recent_unbalance = recent_unbalance.right;
            prev_unbalance.right = recent_unbalance.left;
            recent_unbalance.left = prev_unbalance;
            this.root = recent_unbalance;
        }
    }
    private void right_rotate(avl_node prev_unbalance, avl_node recent_unbalance, int ind){
        if(ind == -1){
            prev_unbalance.left = recent_unbalance.left;
            recent_unbalance.left = recent_unbalance.left.right;
            prev_unbalance.left.right = recent_unbalance;
        }else if(ind == 1){
            prev_unbalance.right = recent_unbalance.left;
            recent_unbalance.left = recent_unbalance.left.right;
            prev_unbalance.right.right = recent_unbalance;
        }else{
            recent_unbalance = recent_unbalance.left;
            prev_unbalance.left = recent_unbalance.right;
            recent_unbalance.right = prev_unbalance;
            this.root = recent_unbalance;
        }
    }
    private void left_right_rotate(avl_node prev_unbalance, avl_node recent_unbalance, int ind){
        avl_node track = recent_unbalance;
        left_rotate(track,track.left,-1);
        right_rotate(prev_unbalance,recent_unbalance,ind);
    }
    private void right_left_rotate(avl_node prev_unbalance, avl_node recent_unbalance, int ind){
        avl_node track = recent_unbalance;
        right_rotate(track,track.right,1);
        left_rotate(prev_unbalance,recent_unbalance,ind);
    }
}
class avl_node { //avl_node implements interface Comparable to make sort collection of avl nodes on required way
    String key;
    int value;
    int balance;
    avl_node(String key){
        this.key = key;
        this.value = 1;
    }
    avl_node left = null;
    avl_node right = null;
}
