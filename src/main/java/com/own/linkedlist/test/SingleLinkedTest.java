package com.own.linkedlist.test;

/**
 * 单向链表
 * 注意事项：检查链表在以下边界条件下是否能正常运行
 *          1、链表为空
 *          2、链表只有一个元素、两个元素
 *          3、链表在头结点、尾结点处是否能正常运行
 *          4、警惕指针丢失
 *
 */
public class SingleLinkedTest {

    public Node head;

    public static class Node {
       public Object data;
       public Node next;

        public Node(Object data){
            this.data = data;
        }
    }

    /**
     * 获取环形链表的入口
     * 方法：利用双指针，
     *      指针一从表头开始每次移动一位，指针二从快慢指针相遇点开始每次移动一位，
     *      两指针相遇的结点即环形链表的入口点
     */
    public Node getCycleEnterance(Node meetingNode){
        Node p = head;
        while (true){
            if (p.data.equals(meetingNode.data)){
                System.out.println("环形链表入口结点："+p.data.toString());
                return p;
            }else {
                p = p.next;
                meetingNode = meetingNode.next;
            }
        }

    }

    /**
     * 判断是否是环形链表
     * @return
     */
    public Node isCycle(){
        Node meetingNode = null;
        Node fast = head;
        Node slow = head;
       while (fast != null && fast.next != null){
           fast = fast.next.next;
           slow = slow.next;
           if (fast.data.toString().equals(slow.data.toString())){
              meetingNode = fast;
              break;
           }
       }
       System.out.println("相遇结点是："+meetingNode.data.toString());
       return meetingNode;
    }
    /**
     * 判断是否是回文字符串
     */
    public boolean isPalindrome(){
        boolean b = true;
        Node p = head,q = head;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }

        /**
         *  循环完以上内容，q已经指向最后一个元素或倒数第二个了
         *  若q指向最后一个元素，该链表为奇数链表
         *  若q指向倒数第二个元素，该链表为偶数链表
         */
        if (q.next == null){
            Node next = p.next;
            Node node = inverseList(p).next;
            b = compareLink(node,next);
        }else if (q.next.next == null){
            Node next = p.next;
            Node node = inverseList(p);
            b = compareLink(node,next);
        }

        return b;
    }

    public boolean compareLink(Node a, Node b){
        while (a != null && b != null) {
            String aStr = a.data.toString();
            String bStr = b.data.toString();
            System.out.println("compare:" + aStr + "," + bStr);
            if (a.data.equals(b.data)){
                a = a.next;
                b = b.next;
                continue;
            } else {
                return false;
            }

        }
        return true;
    }

    /**
     * 从链表头至node(包括)结点开始反转
     * @param node
     */
    public Node inverseList(Node node){
        Node pre = null;
        Node next = null;
        Node p = head;
        while (p != null && p != node) {
            next = p.next;

            p.next = pre;
            pre = p;
            p = next;
        }

        p.next = pre;
        return p;
    }

    public void insertBefore(Object value, Node node){
        Node newNode = new Node(value);
        Node p = head;
        if (head == node) {
           insertToHead(value);
        }else {
            while (p.next != null && p.next != node) {
                p = p.next;
            }

            p.next = newNode;
            newNode.next = node;
        }
    }

    public void insertAfter(Object value, Node node){
        Node newNode = new Node(value);
        Node next = node.next;
        node.next = newNode;
        newNode.next = next;
    }

    public void insertToHead(Object value){
        Node newNode = new Node(value);
        if (head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertToTail(Object value){
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        }else {
            Node p = head;
            while (p.next != null){
                p = p.next;
            }

            p.next = newNode;
        }
    }

    /**
     * 时间复杂度为O(n)
     * @param n
     * @return
     */
    public Node getLastN(int n){
        int num = 0;
        Node p = head;
        while (p != null){
            p = p.next;
            num++;
        }

        if (n > num){
            throw new IllegalArgumentException("n > num");
        }
        int k = num - n + 1;    //倒数第n个转换为正数第k个
        int i = 1;
        Node h = head;
        while (i != k){
            h = h.next;
            i++;
        }

        System.out.println("倒数第"+ n + "个元素为" + h.data.toString());
        return h;
    }

    public Node getLast(){
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        return p;
    }



    public void print(){
        StringBuilder sb = new StringBuilder();
        Node p = head;

        while (p != null) {
            sb.append(p.data.toString());
            sb.append(",");
            p = p.next;
        }

        String s = sb.toString();
        System.out.println(s.substring(0,s.length()-1));
    }


    public void print(Node node){
        StringBuilder sb = new StringBuilder();
        Node p = node;

        while (p != null) {
            sb.append(p.data.toString());
            sb.append(",");
            p = p.next;
        }

        String s = sb.toString();
        System.out.println(s.substring(0,s.length()-1));
    }

    public static class LinkedTest{

        public void testCycle(){
            SingleLinkedTest linkedTest = new SingleLinkedTest();
            String a[] = {"a","b","c","d","e","f"};

            for (int i = 0; i < a.length; i++) {
                linkedTest.insertToTail(a[i]);
            }

            linkedTest.print();
            Node lastN = linkedTest.getLastN(4);
            Node last = linkedTest.getLast();
            last.next = lastN;
            Node meetingNode = linkedTest.isCycle();
            if (meetingNode != null){
                linkedTest.getCycleEnterance(meetingNode);
            }
        }

        public void testInverse(){
            SingleLinkedTest linkedTest = new SingleLinkedTest();
            String a[] = {"a","b","c","d","e","f"};

            for (int i = 0; i < a.length; i++) {
                linkedTest.insertToTail(a[i]);
            }
            Node last = linkedTest.getLast();
            Node node = linkedTest.inverseList(last);
            linkedTest.print(node);
        }


        public static void main(String[] args) {

        }

    }

}
