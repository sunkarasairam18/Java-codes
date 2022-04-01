import java.io.*;
import java.util.*;
public class hash {
    public static void main(String... args){
        Hashtable<Integer,String> one = new Hashtable<>();
        one.put(1,"Sai ram");
        one.put(2,"Srinivas");
        System.out.println("hash "+one);
        System.out.println(""+one.remove(1));
        System.out.println("hash "+one);

    }
}
