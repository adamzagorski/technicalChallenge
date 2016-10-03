package com.gft;

import java.util.*;
import java.util.jar.Pack200;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by amzk on 2016-09-22.
 */
public final class Nodes {

    public static NodeImpl convert(Node root) {
        return (NodeImpl) root;
    }

    public static Iterable<Node> convertToIterable(Node root) {
        return new NodeIterableFlatStructure(root);
    }

//    public static Iterable<Node> convertToFlatStructure(Node root) {
//        System.out.println(convert(root).getName());
//        Iterator<Node> iterator = convert(root).childrenList.iterator();
//        while (iterator.hasNext()) {
//            Node next = iterator.next();
//            convertToFlatStructure(next);
//        }
//        return null;
//    }

    static class NodeIterableFlatStructure implements Iterable<Node> {

        public Node root;

        public NodeIterableFlatStructure(Node root) {
            this.root = root;
        }

        @Override
        public Iterator<Node> iterator() {
            return new NodeIteratorFlatStructure(root);
        }

        static class NodeIteratorFlatStructure implements Iterator<Node> {

            private Stack<Iterator<Node>> stack;
            private Iterator<Node> nodeChildrens;
            private Node current;

            public NodeIteratorFlatStructure(Node root) {
                this.current = root.getChildrens().iterator().next();
                this.nodeChildrens = root.getChildrens().iterator();
                this.stack = new Stack<>();
            }

            @Override
            public boolean hasNext() {
                return nodeChildrens.hasNext() || !this.stack.empty();
            }

            @Override
            public Node next() {
                Node nestedChild = null;
                if (current.getChildrens().iterator().hasNext()) {
                    nestedChild = findNestedChild(current);
                }
                return nestedChild;
            }

            private Node findNestedChild(Node root) {
                if (nodeChildrens.hasNext()) {
                    Node child = nodeChildrens.next();
                    if (child.getChildrens().iterator().hasNext()) {
                        if (!stack.contains(nodeChildrens)) {
                            this.stack.push(nodeChildrens);
                            this.current = child;
                        }
                        nodeChildrens = child.getChildrens().iterator();
                        return findNestedChild(child);
                    } else {
                        return child;
                    }
                } else if (!this.stack.isEmpty()) {
                    nodeChildrens = stack.pop();
                    this.current = root;
                    return root;
                }
                throw new NoSuchElementException();

            }
        }
    }


    // Przeksztalcic Node to File
    // root to file a na wyjściu Observable<File>

}
