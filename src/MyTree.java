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
        MyNode<T> target = contains(item);
        if (target == null) return false;

        // case 1: no child
        if (target.getLeft() == null && target.getRight() == null){
            // case 1a): no parent (root)
            if (target.getParent() == null){
                this.root = null;
                return true;
            }
            // case 1b): has parent (leaf node)
            MyNode<T> parentNode = target.getParent();
            int compare = item.compareTo(parentNode.getItem());
            if (compare > 0){
                parentNode.setRight(null);
            } else {
                parentNode.setLeft(null);
            }
            return true;
        }

        // case 2: one child
        if (target.getLeft() == null){
            // case 2a): no parent (root)
            if (target.getParent() == null){
                this.root = target.getRight();
                this.root.setParent(null);
                return true;
            }
            // case 2b): has parent (node)
            MyNode<T> parentNode = target.getParent();
            int compare = item.compareTo(parentNode.getItem());
            if (compare > 0){
                parentNode.setRight(target.getRight());
            } else {
                parentNode.setLeft(target.getRight());
            }
            target.getRight().setParent(parentNode);
            return true;
        }

        if (target.getRight() == null){
            // case 2c): no parent (root)
            if (target.getParent() == null){
                this.root = target.getLeft();
                this.root.setParent(null);
                return true;
            }
            // case 2d): has parent (node)
            MyNode<T> parentNode = target.getParent();
            int compare = item.compareTo(parentNode.getItem());
            if (compare > 0){
                parentNode.setRight(target.getLeft());
            } else {
                parentNode.setLeft(target.getLeft());
            }
            target.getLeft().setParent(parentNode);
            return true;
        }

        // case 3: two children
        MyNode<T> replace = getLeftMax(target);
        target.setItem(replace.getItem());
        MyNode<T> replaceParent = replace.getParent();
        MyNode<T> replaceLeft = replace.getLeft();
        // case 3a): replace = target left node
        if (replaceParent == target){
            replaceParent.setLeft(replaceLeft);
        }
        // case 3b): replace = target left node's descendents
        else {
            replaceParent.setRight(replaceLeft);
        }
        if (replaceLeft != null) {
            replaceLeft.setParent(replaceParent);
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
    private void inOrder(MyNode<T> curr, StringBuilder str){
        if (curr == null){
            return;
        }
        inOrder(curr.getLeft(), str);
        str.append(curr.getItem());
        str.append(", ");
        inOrder(curr.getRight(), str);
    }

    private MyNode<T> getLeftMax(MyNode<T> target){
        MyNode<T> curr = target.getLeft();
        while(curr.getRight() != null){
            curr = curr.getRight();
        }
        return curr;
    }

}
