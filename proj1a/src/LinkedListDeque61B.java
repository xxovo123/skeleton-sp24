import java.util.List;
import java.util.ArrayList; // import the ArrayList class
public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node{
        T item;
        Node next;
        Node pre;
        public Node(T x, Node n, Node p){
            item = x;
            next = n;
            pre = p;
        }
    }
    private Node sentinel;
    private int  size;

    public LinkedListDeque61B(){
        sentinel = new Node(null,null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    @Override
    public void addFirst(T x) {
        Node newFirst = new Node(x, sentinel.next, sentinel);
        sentinel.next.pre = newFirst;
        sentinel.next = newFirst;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newLast = new Node(x, sentinel, sentinel.pre);
        sentinel.pre.next = newLast;
        sentinel.pre = newLast;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node curNode = sentinel.next;
        while(curNode != sentinel){
            returnList.add(curNode.item);
            curNode = curNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(sentinel.next == sentinel && sentinel.pre == sentinel){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        } else {
            T retItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;
            return  retItem;
        }
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        } else {
            T retItem = sentinel.pre.item;
            sentinel.pre = sentinel.pre.pre;
            sentinel.pre.next = sentinel;
            return  retItem;
        }
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size()){
            return null;
        } else {
            int cnt = 0;
            Node curNode = sentinel.next;
            while (cnt < index) {
                curNode = curNode.next;
                cnt++;
            }
            return curNode.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >= size()){
            return null;
        } else {
            return getRecursiveHelper(sentinel.next,index);
        }
    }

    public T getRecursiveHelper(Node curNode, int index){
        if(index != 0){
            return getRecursiveHelper(curNode.next,index-1);
        } else {
            return curNode.item;
        }
    }
}
