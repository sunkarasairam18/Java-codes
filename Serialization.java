import java.io.*;

public class Serialization {
    public static void main(String... args){
        game one = new game("SAI RAM",3);
        game two = new game("VIRAT KOHLI",6);
        try{
//            FileOutputStream output = new FileOutputStream("Gamers");
//            ObjectOutputStream obOut = new ObjectOutputStream(output);
//            obOut.writeObject(one);
//            obOut.writeObject(two);
//            obOut.close();
            FileInputStream input = new FileInputStream("Gamers");
            ObjectInputStream obIn = new ObjectInputStream(input);
            Object three = obIn.readObject();
            game four = (game)three;
            System.out.println(four.name+" ,"+four.level);
            obIn.close();
        }catch (Exception e){System.out.println("Serialization Failed");}

    }
}
class game implements Serializable{
    game(String name,int level){
        this.name = name;
        this.level = level;
    }
    String name;
    int level;
}