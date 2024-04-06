package prog.AvlTree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class AvlNode {
    private final Integer value;
    @Setter
    AvlNode parent;
    @Setter
    AvlNode left;
    private
    @Setter AvlNode right;
    private
    @Setter int balanceFactor;
    private
    @Setter int height;

    public AvlNode(int value) {
        this.parent = null; // root
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1; // Height of a leaf node is always 1
    }

    public AvlNode(int value, AvlNode parent) {
        this.parent = parent; // child
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1; // Height of a leaf node is always 1
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public int getHeight() {
        if (this.value == null) return 0;
        if (this.left == null && this.right == null)
            return 1;
        int leftHeight = (this.getLeft() == null) ? 0 : this.getLeft().getHeight();
        int rightHeight = (this.getRight() == null) ? 0 : this.getRight().getHeight();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void updateHeight() {
        int leftHeight = (this.left == null) ? 0 : this.left.height;
        int rightHeight = (this.right == null) ? 0 : this.right.height;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public int getBalanceFactor() {
        int leftHeight = (this.left == null) ? 0 : this.left.height;
        int rightHeight = (this.right == null) ? 0 : this.right.height;
        return leftHeight - rightHeight;
    }
}
