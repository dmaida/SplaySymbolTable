import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

public class SplaySymbolTable<K extends Comparable<K>, V> implements SymbolTable<K, V> {

  /*
  Daniel Maida
  daniel.maida@wsu.edu
  CS 223
   */

    private class Node {
        public K key;
        public V val;
        public Node left, right;
        public Node parent; // null for root

        public Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    private Node root;
    public int rotations;
    public int comparisons;

    public SplaySymbolTable() {
        root = null;
    }

    private void rotateRight(Node r) {
        rotations++;
        Node tree = r.parent;

        r.parent = tree.parent;
        tree.parent = r;
        tree.left = r.right;

        if (r.right != null) {
            r.right.parent = tree;
        }
        r.right = tree;
        if (r.parent != null) {
            if (r.parent.right == tree) {
                r.parent.right = r;
            } else {
                r.parent.left = r;

            }
        }
        if (r.parent == null) {
            root = r;
        }
    }

    private void rotateLeft(Node r) {
        rotations++;

        Node tree = r.parent;
        r.parent = tree.parent;
        tree.parent = r;
        tree.right = r.left;

        if (r.left != null) {
            r.left.parent = tree;
        }
        r.left = tree;

        if (r.parent != null) {
            if (r.parent.right == tree) {
                r.parent.right = r;
            } else {
                r.parent.left = r;

            }
        }
        if (r.parent == null) {
            root = r;
        }

    }

    private void splay(Node x) {
        assert root != null;
        assert x.parent != null;
        assert x.parent.parent != null;

        while (x.parent != null && x.parent.parent != null) {
            Node p = x.parent;
            Node g = x.parent.parent;


            if (g.left == p) {

                if (p.left == x) {  //case zig-zig
                    rotateRight(p);
                    rotateRight(x);

                } else { //  case zig-zag
                    rotateLeft(x);
                    rotateRight(x);
                }
            } else if (p.left == x) { // case zag-zig:

                rotateRight(x);
                rotateLeft(x);
            } else { // case zag-zag
                rotateLeft(p);
                rotateLeft(x);
            }
        }
        if (x != root) {

            if (root.left == x) {

                rotateRight(x);
            } else {
                rotateLeft(x);
            }
        }
    }

    @Override
    public void insert(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }

        Node x = root;

        while (true) {

            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                comparisons++;
                x.key = key;
                x.val = val;
                splay(x);
                break;
            }
            if (cmp < 0) {

                if (x.left == null) {
                    comparisons++;
                    x.left = new Node(key, val);
                    x.left.parent = x;
                    x = x.left;
                    break;
                }
                comparisons++;
                x = x.left;
            } else {
                if (x.right == null) {
                    comparisons++;

                    x.right = new Node(key, val);
                    x.right.parent = x;
                    x = x.right;
                    break;
                }
                comparisons++;
                x = x.right;
            }
        }
        //splay(x);

    }

    @Override
    public V search(K key) {
        if (root == null) {
            return null;
        }

        Node x = root;
        V val = null;
        while (true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                comparisons++;
                val = x.val;
                break;
            }
            if (cmp < 0) {
                comparisons++;
                if (x.left == null) break;
                x = x.left;
            } else {
                comparisons++;
                if (x.right == null) break;
                x = x.right;
            }
        }
        splay(x);
        return val;
    }

    private void serializeAux(Node tree, Vector<String> vec) {
        if (tree == null)
            vec.addElement(null);
        else {
            vec.addElement(tree.key.toString() + ":black");
            serializeAux(tree.left, vec);
            serializeAux(tree.right, vec);
        }
    }

    public Vector<String> serialize() {
        Vector<String> vec = new Vector<String>();
        serializeAux(root, vec);
        return vec;
    }

    void printTree(String fname) {
        Vector<String> st = serialize();
        TreePrinter treePrinter = new TreePrinter(st);
        treePrinter.fontSize = 14;
        treePrinter.nodeRadius = 14;
        try {
            FileOutputStream out = new FileOutputStream(fname);
            PrintStream ps = new PrintStream(out);
            treePrinter.printSVG(ps);
        } catch (FileNotFoundException e) {
        }
    }

    public static void main(String[] args) {
        SplaySymbolTable<Integer, Integer> symtab = new SplaySymbolTable<>();
/*
        symtab.insert(10, 10);
        symtab.insert(11, 11);
        symtab.insert(19, 19);
        symtab.insert(2, 2);
        symtab.insert(4, 4);
        symtab.insert(5, 5);
        symtab.insert(7, 7);
        symtab.insert(10, 10);
        symtab.search(2);
*/
        symtab.insert(2, 2);
        symtab.insert(1, 1);
        symtab.insert(4, 4);
        symtab.insert(5, 5);
        symtab.insert(9 ,9);
        symtab.rotateLeft(symtab.root.right.right);

        symtab.printTree("splay-insert-.svg");

    }

}
