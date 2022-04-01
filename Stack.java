import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{

    public Iterator<Item> iterator(){return new Middle();}

    private class Middle implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current!=null;}
        public Item next(){
            Item data = current.data;
            current = current.next;
            return data;
        }
    }

    private class Node{
        Item data;
        Node next;
    }
    Node first;
    Stack(){
        first = null;
    }
    void push(Item item){
        Node one = new Node();
        one.data = item;
        one.next = first;
        first = one;
    }
    Item pop(){
        Item data = first.data;
        first = first.next;
        return data;
    }

    void display(){
        for(Item l:this){
            System.out.print(l+" -> ");
        }
        System.out.println();
    }
    public static void main(String... args){
        Stack stack = new Stack<String>();
        stack.push("virat");
        stack.push("Abd");
        stack.push("Srinu");
        stack.push("Sai");
        stack.display();
        System.out.println(stack.pop());
        stack.display();

    }
}

