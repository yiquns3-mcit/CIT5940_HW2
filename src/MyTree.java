public class MyTree<T extends Comparable<T>> {

    private MyNode<T> root;

    public MyTree(){
        this.root = null;
    }

    public MyNode<T> insert(T item){
        if (item == null){
            throw new IllegalArgumentException("Insertion Failed: item is null");
        }
        if (this.root == null){
            this.root = new MyNode<>(item);
            return this.root;
        }
        MyNode<T> curr = root;
        MyNode<T> parent = null;
        while (curr != null){
            parent = curr;
            int compare = item.compareTo(curr.getItem());
            if (compare == 0){
                return curr;
            } else if (compare > 0){
                curr = curr.getRight();
            } else {
                curr = curr.getLeft();
            }
        }
        MyNode<T> newNode = new MyNode<>(item);
        newNode.setParent(parent);
        int compare = (newNode.getItem()).compareTo(parent.getItem());
        if (compare > 0){
            parent.setRight(newNode);
        } else if (compare < 0){
            parent.setLeft(newNode);
        }
        return newNode;
    }

    public MyNode<T> contains(T item){
        if(item == null) {
            throw new IllegalArgumentException("Contains Failed: item is null");
        }
        MyNode<T> curr = this.root;
        while (curr != null){
            int compare = item.compareTo(curr.getItem());
            if (compare == 0){
                return curr;
            } else if (compare > 0){
                curr = curr.getRight();
            } else {
                curr = curr.getLeft();
            }
        }
        return null;
    }

    public boolean remove(T item){
        if (item == null) {
            throw new IllegalArgumentException("Remove Failed: item is null");
        }

        // Single traversal
        MyNode<T> target = this.root;
        while (target != null) {
            int compare = item.compareTo(target.getItem());
            if (compare == 0) {
                break;
            } else if (compare > 0) {
                target = target.getRight();
            } else {
                target = target.getLeft();
            }
        }

        if (target == null) return false;

        // case 1: no child
        if (target.getLeft() == null && target.getRight() == null){
            transplant(target, null);
            return true;
        }

        // case 2: one child
        if (target.getLeft() == null){
            transplant(target, target.getRight());
            return true;
        }

        if (target.getRight() == null){
            transplant(target, target.getLeft());
            return true;
        }

        // case 3: two children
        // Find the minimum node in target's right subtree (in-order successor)
        MyNode<T> replace = getRightMin(target);
        MyNode<T> replaceRight = replace.getRight();
        // case 3a) replace is target's direct right child
        if (replace.getParent() == target) {
            // step 1. connect target's parent with replace directly
            transplant(target, replace);
            // step 2. replace inherit target's left child
            replace.setLeft(target.getLeft());
            if (target.getLeft() != null) {
                target.getLeft().setParent(replace);
            }
        }
        // case 3b) replace is a left descendant of target's right subtree
        else {
            // step 1. remove replace node with its right child first
            transplant(replace, replaceRight);
            // step 2. connect target's parent with replace directly
            transplant(target, replace);
            // step 3. connect target's subtrees with replace directly
            replace.setLeft(target.getLeft());
            if (target.getLeft() != null) {
                target.getLeft().setParent(replace);
            }
            replace.setRight(target.getRight());
            if (target.getRight() != null) {
                target.getRight().setParent(replace);
            }
        }

        return true;
    }

    public String toString(){
        if (this.root == null) return "";
        StringBuilder str = new StringBuilder();
        inOrder(this.root, str);
        if (str.length() >= 2){
            str.delete(str.length() - 2, str.length());
        }
        return str.toString();
    }

    public MyNode<T> getRoot(){
        return this.root;
    }

    // Helper Functions

    // inOrder():
    //      Performs in-order traversal of the tree.
    private void inOrder(MyNode<T> curr, StringBuilder str){
        if (curr == null){
            return;
        }
        inOrder(curr.getLeft(), str);
        str.append(curr.getItem());
        str.append(", ");
        inOrder(curr.getRight(), str);
    }

    // transplant():
    //      Replaces node “remove” (root or non-root, with 0 or 1 child) with node "replace" (child or null)
    // possible inputs:
    //      remove = root / non-root
    //      replace = null(remove has no child) / the only child of the remove
    // goal:
    //      parent(remove)'s child node -> replace
    //      parent(replace) -> parent(remove).
    private void transplant(MyNode<T> remove, MyNode<T> replace){
        if (remove.getParent() == null){
            this.root = replace;
        }
        else if (remove == remove.getParent().getLeft()) {
            remove.getParent().setLeft(replace);
        }
        else {
            remove.getParent().setRight(replace);
        }
        if (replace != null) {
            replace.setParent(remove.getParent());
        }
    }

    // getRightMin()
    //      Finds the minimum node in the right subtree (in-order successor)
    private MyNode<T> getRightMin(MyNode<T> target){
        MyNode<T> curr = target.getRight();
        while(curr.getLeft() != null){
            curr = curr.getLeft();
        }
        return curr;
    }

}
