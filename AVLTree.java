public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public AVLNode getRoot() {
        return root;
    }

    public AVLTree insert(int data) {
        this.root = insert(this.root, data);
        return this;
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    public void preorderTraversal(AVLNode node){
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " ");

        /* then recur on left subtree */
        preorderTraversal(node.left);

        /* now recur on right subtree */
        preorderTraversal(node.right);
    }


    private AVLNode delete(AVLNode node, int data) {
        if (node == null) {
            return null;
        }
        if (node.key > data) {
            node.left = delete(node.left, data);
        } else if (node.key < data  ) {
            node.right = delete(node.right, data);
        } else {
            //  One Child or less
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            //  Two child
            node.key = getMax(node.left);
            node.left = delete(node.right, data);
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private int getMax(AVLNode node) {
        if (node.right != null) {
            return getMax(node.right);
        }
        return node.key;
    }

    private AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }
        if (node.key > data) {
            node.left = insert(node.left, data);
        } else if (node.key < data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private AVLNode applyRotation(AVLNode node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private AVLNode rotateRight(AVLNode root) {
        AVLNode left = root.left;
        AVLNode leftRight = left.right;
        root.left = leftRight;
        left.right = root;
        updateHeight(root);
        updateHeight(left);
        return left;
    }

    private AVLNode rotateLeft(AVLNode root) {
        AVLNode right = root.right;
        AVLNode rightLeft = right.left;
        right.left = root;
        root.right = rightLeft;
        updateHeight(root);
        updateHeight(right);
        return right;
    }

    private int balance(AVLNode node) {
        if (node != null) {
            return height(node.left) - height(node.right);
        }
        return 0;
    }

    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) +1;
    }

    private int height(AVLNode node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }
}
