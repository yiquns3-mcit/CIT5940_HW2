public class MyHashTable<T extends Comparable<T>> {

    private MyTree<T>[] body;
    private int capacity;
    private int size;

    public MyHashTable() {
        this.capacity = 701;
        this.body = new MyTree[this.capacity];
        this.size = 0;
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Failed: Capacity must be positive");
        }
        this.capacity = capacity;
        this.body = new MyTree[capacity];
        this.size = 0;
    }

    // add():
    //      Adds an item to the hash table
    public MyNode<T> add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Add Failed: item is null");
        }
        // Compute hash index
        int index = hash(item);
        // If bucket is empty, create a new tree
        if (this.body[index] == null) {
            this.body[index] = new MyTree<>();
        }
        // Insert into the tree at this bucket
        MyNode<T> result = this.body[index].insert(item);
        // If this is a new node (not a duplicate), increment size
        if (this.body[index].isNewAdded()){
            this.size ++;
        }
        return result;
    }

    // contains():
    //      Checks if an item exists in the hash table
    public MyNode<T> contains(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Contains Failed: item is null");
        }
        // locate the bucket that may store the item
        int index = hash(item);
        // bucket is null or empty
        if (this.body[index] == null || this.body[index].getRoot() == null) {
            return null;
        }
        // use MyTree.contains() to search for item
        return this.body[index].contains(item);
    }

    // remove():
    //      Removes an item from the hash table if present
    public boolean remove(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Remove Failed: item is null");
        }
        // locate the bucket that may store the item
        int index = hash(item);
        // bucket is null or empty
        if (this.body[index] == null || this.body[index].getRoot() == null) {
            return false;
        }
        // update size if item is removed (decrement)
        boolean isRemoved = this.body[index].remove(item);
        if (isRemoved) {
            this.size--;
        }
        return isRemoved;
    }

    // isEmpty():
    //      Checks if the hash table is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    // size():
    //      Returns the number of items in the hash table
    public int size() {
        return this.size;
    }

    // clear():
    //      Empties the hash table
    public void clear() {
        this.body = new MyTree[this.capacity];
        this.size = 0;
    }

    // main():
    //      Main method for testing the hash table implementation.
    public static void main(String[] args) {
        // Create a new MyHashTable
        MyHashTable<String> ht = new MyHashTable<>();
        System.out.println("=== MyHashTable Test ===\n");

        // add() test: Insert at least 5 unique Strings and print the returned result
        System.out.println("1. Adding 5 unique strings");
        System.out.println("Add 'I' | Result: " + ht.add("I"));
        System.out.println("Add 'like' | Result: " + ht.add("like"));
        System.out.println("Add '5940' | Result: " + ht.add("5940"));
        System.out.println("Add 'Data' | Result: " + ht.add("Data"));
        System.out.println("Add 'Structure' | Result: " + ht.add("Structure"));
        System.out.println();

        // size() test
        System.out.println("2. Current Size");
        System.out.println("HashTable Size: " + ht.size());
        System.out.println();

        // contains() test
        System.out.println("3. Testing contains()");
        System.out.println("Contains 'I' (existing) | Result: " + ht.contains("I"));
        System.out.println("Contains 'like' (existing) | Result: " + ht.contains("like"));
        System.out.println("Contains 'Apple' (missing) | Result: " + ht.contains("Apple"));
        System.out.println("Contains '5940' (existing) | Result: " + ht.contains("5940"));
        System.out.println("Contains 'Data' (existing) | Result: " + ht.contains("Data"));
        System.out.println("Contains 'Database' (missing) | Result: " + ht.contains("Database"));
        System.out.println("Contains 'Structure' (existing) | Result: " + ht.contains("Structure"));
        System.out.println();

        // add() duplicate test
        System.out.println("4. Inserting a Duplicate Item");
        System.out.println("Add 'Data' again (duplicate) | Result: " + ht.add("Data"));
        System.out.println("Size after adding duplicate (unchange): " + ht.size());
        System.out.println();

        // remove() test
        System.out.println("5. Removing an Item");
        System.out.println("Remove 'Structure' (existing) | Result: " + ht.remove("Structure"));
        System.out.println("Size after existing removal (decrement): " + ht.size());
        System.out.println();
        System.out.println("6. Removing the Same Item Again");
        System.out.println("Remove 'Structure' (missing) | Result: " + ht.remove("Structure"));
        System.out.println("Size after missing removal (unchange): " + ht.size());
    }

    // Helper Functions

    // hash():
    //      Computes the hash index for a given item
    private int hash(T item){
        return (item.hashCode() & 0x7FFFFFFF) % this.capacity;
    }

}
