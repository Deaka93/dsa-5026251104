package lw06.prelab;

import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    static Node insert(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data) root.left = insert(root.left, data);
        else if (data > root.data) root.right = insert(root.right, data);
        return root;
    }

    static boolean search(Node root, int target) {
        if (root == null) return false;
        if (target == root.data) return true;
        if (target < root.data) return search(root.left, target);
        return search(root.right, target);
    }

    static String preorder(Node root) {
        if (root == null) return "";
        return root.data + " " + preorder(root.left) + preorder(root.right);
    }

    static String inorder(Node root) {
        if (root == null) return "";
        return inorder(root.left) + root.data + " " + inorder(root.right);
    }

    static String postorder(Node root) {
        if (root == null) return "";
        return postorder(root.left) + postorder(root.right) + root.data + " ";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        Node root = null;
        String[] values = sc.nextLine().split(" ");
        for (String v : values) {
            root = insert(root, Integer.parseInt(v));
        }
        int target = sc.nextInt();

        System.out.println("Preorder : " + preorder(root).trim());
        System.out.println("Inorder : " + inorder(root).trim());
        System.out.println("Postorder : " + postorder(root).trim());
        System.out.println("Search " + target + " : " + search(root, target));

        sc.close();
    }
}