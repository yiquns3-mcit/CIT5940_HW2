public class MyNode<T> {

    private T item;
    private MyNode<T> left;
    private MyNode<T> right;
    private MyNode<T> parent;

    public MyNode(T item) {
        this.item = item;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public T getItem(){
        return this.item;
    }

    public MyNode<T> getLeft(){
        return this.left;
    }

    public MyNode<T> getRight(){
        return this.right;
    }

    public MyNode<T> getParent(){
        return this.parent;
    }

    public void setItem(T item){
        this.item = item;
    }

    public void setLeft(MyNode<T> node){
        this.left = node;
    }

    public void setRight(MyNode<T> node){
        this.right = node;
    }

    public void setParent(MyNode<T> node){
        this.parent = node;
    }

}
