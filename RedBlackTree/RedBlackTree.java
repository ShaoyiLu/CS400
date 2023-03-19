// --== CS400 Project One File Header ==--
// Name: Shaoyi Lu
// CSL Username: shaoyi
// Email: slu248@wisc.edu
// Lecture #: 001
// Notes to Grader: none

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree <T extends Comparable<T>>{

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public int blackHeight;//0 = red, 1 = black, and 2 = double-black
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public Node(T data) {
            this.data = data;
        }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) {
            root = newNode;
            size++;
            root.blackHeight = 1;
            return true;
        } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     *  this method may also be called recursively, in which case the input parameter
     *  may reference a different red node in the tree that potentially has a red parent
     *  node. The job of this enforceRBTreePropertiesAfterInsert method is to resolve
     *  any red-black tree property violations that are introduced by inserting each
     *  new new node into a red-black tree.
     */

    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
        Node<T> Parent = null;
        Node<T> Grandparent = null;
        Node<T> Uncle = null;


        if (newNode == root) {
            return;
        }

        Parent = newNode.parent;

        if(Parent.blackHeight == 1){
            return;
        }

        if (newNode.parent.parent == null) {
            return;
        } else {
            Grandparent = newNode.parent.parent;
            if ((Parent == Grandparent.leftChild)) {
                Uncle = Grandparent.rightChild;
            } else if (Parent == Grandparent.rightChild) {
                Uncle = Grandparent.leftChild;
            }
        }

        if (Grandparent != null) {
            if (Uncle != null && Uncle.blackHeight == 0) {
                Uncle.blackHeight = 1;
                Parent.blackHeight = Uncle.blackHeight;
                Grandparent.blackHeight = 0;
                enforceRBTreePropertiesAfterInsert(Grandparent);
            } else if (Uncle == null || Uncle.blackHeight == 1) {
                if ((newNode == Parent.leftChild) && (Parent == Grandparent.rightChild)) {
                    rotate(newNode, Parent);
                    Parent = newNode;
                    rotate(Parent, Grandparent);
                    Parent.blackHeight = 1;
                    Grandparent.blackHeight = 0;
                } else if ((newNode == Parent.rightChild) && (Parent == Grandparent.rightChild)) {
                    rotate(Parent, Grandparent);
                    Parent.blackHeight = 1;
                    Grandparent.blackHeight = 0;
                } else if ((newNode == Parent.leftChild) && (Parent == Grandparent.leftChild)) {
                    rotate(Parent, Grandparent);
                    Parent.blackHeight = 1;
                    Grandparent.blackHeight = 0;
                } else if ((newNode == Parent.rightChild) && (Parent == Grandparent.leftChild)) {
                    rotate(newNode, Parent);
                    Parent = newNode;
                    rotate(Parent, Grandparent);
                    Parent.blackHeight = 1;
                    Grandparent.blackHeight = 0;
                }
            }
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

            // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        // TODO: Implement this method.
        if(parent.rightChild == child){
            parent.rightChild = child.leftChild;
            if(child.leftChild != null){
                child.leftChild.parent = parent;
            }
            child.parent = parent.parent;
            if(parent.parent == null){
                root = child;
                child.parent = null;
            }else if(parent.isLeftChild()){
                parent.parent.leftChild = child;
            }else{
                parent.parent.rightChild = child;
            }
            child.leftChild = parent;
            parent.parent = child;
        }else if(parent.leftChild == child){
            parent.leftChild = child.rightChild;
            if (child.rightChild != null){
                child.rightChild.parent = parent;
            }
            child.parent = parent.parent;
            if (parent.parent == null){
                root = child;
                child.parent = null;
            }else if (parent.isLeftChild()){
                parent.parent.leftChild = child;
            }else{
                parent.parent.rightChild = child;
            }
            child.rightChild = parent;
            parent.parent = child;
        }
        else {
            throw new IllegalArgumentException("there are not matched!");
        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }


    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

//    protected RedBlackTree<Integer> RBT;
//
//    @BeforeEach
//    public void createInstane() {
//        RBT = new RedBlackTree<Integer>();
//    }

    //The @Test annotation allows JUnit to recognize its following
    //method as a test method
    @Test
    public void test1() {
        RedBlackTree<Integer> RBT = new RedBlackTree<Integer>();
        RBT.insert(8);
        RBT.insert(2);
        RBT.insert(7);
        RBT.insert(10);
        RBT.insert(9);
        RBT.insert(4);
        System.out.println(RBT.toLevelOrderString());
        assertEquals(7, RBT.root.data);
        assertEquals(2, RBT.root.leftChild.data);
        assertEquals(9, RBT.root.rightChild.data);
    }

    @Test
    public void test2() {
        RedBlackTree<Integer> RBT = new RedBlackTree<Integer>();
        RBT.insert(36);
        RBT.insert(98);
        RBT.insert(14);
        RBT.insert(55);
        RBT.insert(77);
        RBT.insert(89);
        assertEquals(0, RBT.root.rightChild.blackHeight);
        assertEquals(1, RBT.root.leftChild.blackHeight);
        assertEquals(1, RBT.root.blackHeight);
    }

    @Test
    public void test3() {
        RedBlackTree<Integer> RBT = new RedBlackTree<Integer>();
        RBT.insert(1096);
        RBT.insert(3);
        RBT.insert(9999);
        assertEquals(3, RBT.size());
        RBT.insert(7);
        RBT.insert(63);
        RBT.insert(4);
        assertEquals(6, RBT.size());
        assertEquals("[ 1096, 7, 9999, 3, 63, 4 ]", RBT.toLevelOrderString());
    }

    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {

    }

}