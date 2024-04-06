package test;

import org.junit.jupiter.api.Test;
import prog.AvlTree.AvlNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvlNodeTest {
    @Test
    public void testUpdateHeight() {
        AvlNode node = new AvlNode(5);
        assertEquals(1, node.getHeight());
        node.setLeft(new AvlNode(3));
        node.updateHeight();
        assertEquals(2, node.getHeight());
        node.setRight(new AvlNode(7));
        node.updateHeight();
        assertEquals(2, node.getHeight());
    }

    @Test
    public void testGetBalanceFactor() {
        AvlNode node = new AvlNode(5);
        assertEquals(0, node.getBalanceFactor());
        node.setLeft(new AvlNode(3));
        assertEquals(1, node.getBalanceFactor());
        node.setRight(new AvlNode(7));
        assertEquals(0, node.getBalanceFactor());
    }
}

