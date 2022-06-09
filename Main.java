public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);
        System.out.println("Preorder traversal of "+
                "constructed tree is : ");
        tree.preorderTraversal(tree.getRoot());
        tree.delete(10);
        System.out.println();
        System.out.println("Preorder traversal after "+
                "deletion of 10 :");
        tree.preorderTraversal(tree.getRoot());
    }
}
