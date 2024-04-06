package test;

import org.junit.jupiter.api.Test;
import prog.AvlTree.AvlTree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvlTreeTest {
    @Test
    public void testAddNode() {
        AvlTree tree = new AvlTree();

        // Добавление узла в пустое дерево (характерная точка 1)
        tree.addNode(10);
        assertEquals(10, tree.getRoot().getValue());

        // Добавление узла, который становится левым потомком корня (характерная точка 2)
        tree.addNode(5);
        assertEquals(5, tree.getRoot().getLeft().getValue());

        // Добавление узла, который становится правым потомком корня (характерная точка 3)
        tree.addNode(15);
        assertEquals(15, tree.getRoot().getRight().getValue());

        // Добавление узла, который становится левым потомком другого узла (характерная точка 4)
        tree.addNode(3);
        assertEquals(3, tree.getRoot().getLeft().getLeft().getValue());

        // Добавление узла, который становится правым потомком другого узла (характерная точка 5)
        tree.addNode(7);
        assertEquals(7, tree.getRoot().getLeft().getRight().getValue());
    }

    @Test
    public void testPrintTree() {
        AvlTree tree = new AvlTree();
        tree.addNode(10);
        tree.addNode(5);
        tree.addNode(15);
        tree.addNode(3);
        tree.addNode(7);

        // Заменяем стандартный поток вывода для тестирования вывода на консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Вызываем метод для вывода дерева на консоль
        AvlTree.printTree(tree.getRoot());

        // Получаем вывод на консоль в виде строки
        String printedTree = outputStream.toString().replaceAll("\r\n", "\n");

        // Ожидаемый вывод дерева
        String expectedTree =
                "└── 10\n" +
                        "    ├── 5\n" +
                        "    │   ├── 3\n" +
                        "    │   └── 7\n" +
                        "    └── 15\n";

        // Проверяем, что фактический вывод соответствует ожидаемому
        assertEquals(expectedTree, printedTree);

        // Восстанавливаем стандартный поток вывода
        System.setOut(System.out);
    }
}

