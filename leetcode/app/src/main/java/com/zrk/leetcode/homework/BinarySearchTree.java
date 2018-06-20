package com.zrk.leetcode.homework;

import android.app.MediaRouteActionProvider;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/1 10:03 1.0
 * @time 2018/6/1 10:03
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/1 10:03
 */

public class BinarySearchTree<T> {

    private Node<T> root;

    private Comparator<? super T> comparator;

    public BinarySearchTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public boolean delete(T data) {
        return delete(search(data));
    }

    public boolean delete(Node<T> node) {
        if (node == null)
            return false;
        if (node.getlChild() == null && node.getrChild() == null) {
            if (node.getParent() != null) {
                Node parent = node.getParent();
                if (parent.getlChild() == node) {
                    parent.setlChild(null);
                } else {
                    parent.setrChild(null);
                }
                node.setParent(null);
            }
            return true;
        }

        if (node.getlChild() == null || node.getrChild() == null) {
            Node parent = node.getParent();
            if (node.getlChild() != null) {
                node.getlChild().setParent(parent);
                if (parent != null) {
                    parent.setlChild(node.getlChild());
                }
            } else {
                node.getrChild().setParent(parent);
                if (parent != null) {
                    parent.setrChild(node.getrChild());
                }
            }
            return true;
        }

        Node<T> succesor = succesor(node.getData());
        if (succesor != null) {
            T data = succesor.getData();
            delete(succesor);
            node.setData(data);
        }
        return true;
    }

    public boolean insert(T data) {

        if (root == null) {
            root = new Node<>(data, null, null, null);
            return true;
        }

        Node<T> node = root;
        Node<T> p = null;
        while (node != null) {
            p = node;
            if (comparator.compare(data, node.data) < 0) {
                node = node.getlChild();
            } else {
                node = node.getrChild();
            }
        }
        if (comparator.compare(data, p.data) < 0) {
            p.setlChild(new Node(data, p, null, null));
        } else {
            p.setrChild(new Node(data, p, null, null));
        }
        return true;
    }


    //迭代，不使用栈
    public void travel(Consumer<? super T> consumer) {
        Node<T> node = root;
        while (node != null && node.getlChild() != null) {
            node = node.getlChild();
        }

        while (node != null) {
            consumer.consume(node.data);
            if (node.getrChild() != null) {
                node = node.getrChild();
                while (node != null && node.getlChild() != null) {
                    node = node.getlChild();
                }
            } else {
                while (node.getParent() != null && node.getParent().getrChild() == node) {
                    node = node.getParent();
                }
                node = node.getParent();
            }
        }
    }

    /**
     * 层次遍历
     *
     * @param consumer
     */
    public void travelInLevel(Consumer<? super T> consumer) {
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            Node<T> pop = queue.removeFirst();
            if (pop != null) {
                consumer.consume(pop.data);
            }

            if (pop.getlChild() != null) {
                queue.addLast(pop.getlChild());
            }

            if (pop.getrChild() != null) {
                queue.addLast(pop.getrChild());
            }
        }
    }

    public LinkNode<T> toSortedLinkList() {
        if (root == null) {
            return null;
        }

        //find left
        Node<T> node = root;
        while (node != null && node.getlChild() != null) {
            node = node.getlChild();
        }

        LinkNode<T> head = new LinkNode<T>(node.getData());

        LinkNode<T> linkNode = head;

        while ((node = findNext(node)) != null) {
            LinkNode<T> newNode = new LinkNode<T>(node.getData(), linkNode, null);
            linkNode.setRight(newNode);
            linkNode = newNode;
        }

        return head;
    }

    private Node<T> findNext(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.getrChild() != null) {
            node = node.getrChild();
            while (node != null && node.getlChild() != null) {
                node = node.getlChild();
            }
            return node;
        }

        if (node.getParent() != null) {
            Node<T> p = node.getParent();
            while (node != null && p != null && p.getrChild() == node) {
                node = p;
                p = node.getParent();
            }
            if (p != null && p.getlChild() == node) {
                return p;
            }
        }
        return null;
    }

    /**
     * 深度
     *
     * @return
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.getlChild()), getHeight(root.getrChild())) + 1;
    }

    /**
     * 树的宽度，即最大的层节点数
     *
     * @return
     */
    public int getWidth() {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        Node node = root;
        int max = 0;
        queue.addLast(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = max < size ? size : max;
            while (--size >= 0) {
                Node t = queue.removeFirst();
                if (t.getlChild() != null) {
                    queue.addLast(t.getlChild());
                }
                if (t.getrChild() != null) {
                    queue.addLast(t.getrChild());
                }
            }
        }
        return max;
    }

    public int getNodeCount() {
        if (root == null) {
            return 0;
        }
        return getNodeCount(root.getlChild()) + getNodeCount(root.getrChild()) + 1;
    }

    private int getNodeCount(Node node) {
        if (node == null)
            return 0;
        return getNodeCount(node.getlChild()) + getNodeCount(node.getrChild()) + 1;
    }


    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.getlChild()), getHeight(node.getrChild())) + 1;
    }

    public Node<T> getMin() {
        return getMin(root);
    }


    public Node<T> getMax() {
        return getMax(root);
    }


    /**
     * 后继
     *
     * @param data
     * @return
     */
    public Node<T> succesor(T data) {
        Node<T> node = search(data);
        if (node == null) {
            return null;
        }
        if (node.getrChild() != null) {
            return getMin(node.getrChild());
        }

        while (node.getParent() != null && node.getParent().getrChild() == node) {
            node = node.getParent();
        }
        return node.getParent();
    }

    /**
     * 前驱
     *
     * @param data
     * @return
     */
    public Node<T> predeccesor(T data) {
        Node<T> node = search(data);
        if (node == null)
            return null;

        if (node.getlChild() != null) {
            return getMax(node.getlChild());
        }

        while (node.getParent() != null && node.getParent().getlChild() == node) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private Node<T> getMin(Node<T> root) {
        if (root == null)
            return null;
        while (root != null && root.getlChild() != null) {
            root = root.getlChild();
        }
        return root;
    }

    private Node<T> getMax(Node<T> root) {
        if (root == null)
            return null;
        while (root != null && root.getrChild() != null) {
            root = root.getrChild();
        }
        return root;
    }

    /**
     * search
     *
     * @param data
     * @return
     */
    public Node<T> search(T data) {
        if (root == null) {
            root = new Node(data, null, null, null);
            return root;
        }
        Node<T> t = root;
        int compare = 0;
        while (t != null && (compare = comparator.compare(data, t.data)) != 0) {
            if (compare < 0) {
                //left
                t = t.getlChild();
            } else {
                t = t.getrChild();
            }
        }
        return t;
    }

    public static class Node<T> {
        T data;
        Node parent;
        Node lChild;
        Node rChild;

        public Node() {
        }

        public Node(T data, Node parent, Node lChild, Node rChild) {
            this.data = data;
            this.parent = parent;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getlChild() {
            return lChild;
        }

        public void setlChild(Node lChild) {
            this.lChild = lChild;
        }

        public Node getrChild() {
            return rChild;
        }

        public void setrChild(Node rChild) {
            this.rChild = rChild;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class LinkNode<T> {
        T data;
        LinkNode<T> left;
        LinkNode<T> right;

        public LinkNode(T data) {
            this.data = data;
        }

        public LinkNode(T data, LinkNode<T> left, LinkNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public LinkNode<T> getLeft() {
            return left;
        }

        public void setLeft(LinkNode<T> left) {
            this.left = left;
        }

        public LinkNode<T> getRight() {
            return right;
        }

        public void setRight(LinkNode<T> right) {
            this.right = right;
        }
    }
}
