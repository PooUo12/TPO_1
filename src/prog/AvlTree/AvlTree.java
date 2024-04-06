package prog.AvlTree;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Getter
public class AvlTree {
    private AvlNode root;

    public void addNode(int value) {
        root = addRecursive(root, value); // Используем рекурсивную функцию для добавления узла
    }

    private AvlNode addRecursive(AvlNode current, int value) {
        if (current == null) {
            return new AvlNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(addRecursive(current.getRight(), value));
        } else {
            // Если значение уже существует, ничего не делаем
            return current;
        }

        current.updateHeight(); // Обновляем высоту текущего узла
        int balanceFactor = current.getBalanceFactor();

        // Выполняем балансировку дерева при необходимости
        if (balanceFactor > 1) {
            if (value < current.getLeft().getValue()) {
                return rotateRight(current);
            } else {
                current.setLeft(rotateLeft(current.getLeft()));
                return rotateRight(current);
            }
        }
        if (balanceFactor < -1) {
            if (value > current.getRight().getValue()) {
                return rotateLeft(current);
            } else {
                current.setRight(rotateRight(current.getRight()));
                return rotateLeft(current);
            }
        }
        return current;
    }

    private AvlNode rotateLeft(AvlNode a) {
        AvlNode b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        a.updateHeight();
        b.updateHeight();
        return b;
    }

    private AvlNode rotateRight(AvlNode a) {
        AvlNode b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        a.updateHeight();
        b.updateHeight();
        return b;
    }

    public static void printTree(AvlNode root) {
        printTreeRecursive(root, "", true);
    }

    private static void printTreeRecursive(AvlNode node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getValue());
            if (node.getLeft() != null || node.getRight() != null) {
                printTreeRecursive(node.getLeft(), prefix + (isTail ? "    " : "│   "), false);
                printTreeRecursive(node.getRight(), prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("./output.txt");
        PrintStream out = new PrintStream(fos);
        Scanner in = new Scanner(new File("input.txt"));

        int nodesCount = in.nextInt();
        int[][] arrayNodes = new int[nodesCount][3];
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < 3; j++) {
                arrayNodes[i][j] = in.nextInt();
            }
        }

        AvlTree tree = new AvlTree();
        for (int i = 0; i < nodesCount; i++) {
            tree.addNode(arrayNodes[i][0]);
        }

        in.close();
        out.close();

        System.out.println("---------------------------");
        printTree(tree.getRoot());
    }
}
