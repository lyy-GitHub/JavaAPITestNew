package com.yy.util;

import java.util.LinkedList;
import java.util.Stack;

public class NodeOperation {

	static Node head=null;
	static Node current=null;
	public static void main(String[] args) {
		int[] a={2,4,3,4,5,6,7};
		for(int i=0;i<a.length;i++){
			addNode(a[i]);
		}
		reverseNode(head);
		bianli(head);
		
//		System.out.println(getNodeSize(head));
//		System.out.println(getKNode(head,3).getData());
//		System.out.println(getLastKNode(3));
//		System.out.println(getMiddle(head));
//		endToStart(head);
//		System.out.println(hasCycle(head));
//		LinkedList l1=new LinkedList();
//		for(int i=0;i<5;i++){
//			l1.add(i);
//		}
//		LinkedList l2=new LinkedList();
//		for(int i=1;i<10;i+=2){
//			l2.add(i);
//		}
//		System.out.println(l2.getFirst());
//		LinkedList l3=new LinkedList();
//		l3.head=mergeLinkedList(l1.head,l2.head);
	}
	//	　1、单链表的创建和遍历
	public static void addNode(int data){

		Node node=new Node(data);
		if(head==null){
			head=node;
			current=head;
		}		
		else{
			current.next=node;	
			current=current.next;
		}
	}

	public static void bianli(Node head){
		current=head;
		while(current!=null){
			System.out.println(current.getData());
			current=current.next;
		}
	}
	//	　　2、求单链表中节点的个数
	public static int getNodeSize(Node head){
		current=head;
		int size=0;
		while(current!=null){
			current=current.next;
			size++;
		}
		return size;
	}
	//	　　3、查找单链表中的倒数第k个结点（剑指offer，题15）
	public static Node getKNode(Node node,int k){		
		int index=getNodeSize(node)-k;
		current=node;
		for(int i=0;i<index;i++){
			current=current.next;
		}
		return current;
	}
	public static int getLastKNode(int k){
		current=head;
		Node first=head;
		Node second=head;
		for(int i=0;i<k;i++){
			second=second.next;
		}
		while(second!=null){
			first=first.next;
			second=second.next;
		}
		return first.getData();
	}
	//	　　4、查找单链表中的中间结点
	public static int getMiddle(Node node){
		current=node;
		Node first=node;
		Node second=node;
		while(second!=null&&second.next!=null){
			first=first.next;
			second=second.next.next;
		}
		return first.data;
	}

	//	　　5、合并两个有序的单链表，合并之后的链表依然有序【出现频率高】（剑指offer，题17）
	public static Node mergeLinkedList(Node head1,Node head2){
		Node head3=null;
		if(head1.getData()>head2.data){
			head3=head2;
			current=head2;
			head2=head2.next;
		}else{
			head3=head1;
			current=head1;
			head1=head1.next;
		}
		while(head1!=null&&head2!=null){
			if(head1.getData()>head2.getData()){
				current=head2;
				current=current.next;
				head2=head2.next;
			}else{
				current=head1;
				current=current.next;
				head1=head1.next;
			}
		}
		if(head1!=null){
			current.next=head1;
		}
		if(head2!=null){
			current.next=head2;
		}
		return head3;
	}
	//	　　6、单链表的反转【出现频率最高】（剑指offer，题16）
	public static Node reverseNode(Node node){
		Node pre=node;
		current=node.next;		
		while(current!=null){	
			Node temp=current.next;
			current.next=pre;
			pre=current;
			current=temp;
			
		}
		pre.setNext(null);
		return node;
	}
	
	//	　　7、从尾到头打印单链表（剑指offer，题5）
	public static void endToStart(Node node){
		Stack<Node> stack=new Stack<Node>();
		current=node;
		while(current!=null){
			stack.push(current);
			current=current.next;			
		}
		while(stack.size()>0){
			System.out.println(stack.pop().getData());
		}
	}
	//
	//	　　8、判断单链表是否有环
	public static boolean hasCycle(Node node){
		Node first=node;
		Node second=node;
		while(second!=null&&second.next!=null){
			second=second.next.next;
			first=first.next;
			if(second==first){
				return true;
			}
		}
		return false;
	}
	//
	//	　　9、取出有环链表中，环的长度
	public static Node hasFirstCycleNode(Node node){
		Node first=node;
		Node second=node;
		while(second!=null&&second.next!=null){
			second=second.next.next;
			first=first.next;
			if(second==first){
				return first;
			}
		}
		return first;
	}
	public static int getCycleLength(Node node){
		current=node;
		int size=0;
		while(current!=null){
			size++;
			current=current.next;
			if(current==node){
				return size;
			}
		}
		return size;
	}
	//
	//	　　10、单链表中，取出环的起始点（剑指offer，题56）。本题需利用上面的第8题和第9题。
	//
	//	　　11、判断两个单链表相交的第一个交点（剑指offer，题37）
	public static Node getFirstCrossNode(Node node1,Node node2){
		int l1=getNodeSize(node1);
		int l2=getNodeSize(node2);
		int dis=0;
		Node longNode;
		Node shortNode;
		if(l1>l2){
			longNode=node1;
			shortNode=node2;
			dis=l1-l2;			
		}
		else{
			longNode=node2;
			shortNode=node1;
			dis=l2-l1;
		}
		for(int i=0;i<dis;i++){
			longNode=longNode.next;
		}
		while(longNode!=null&&shortNode!=null){
			if(longNode==shortNode){
				return longNode;
			}
			longNode=longNode.next;
			shortNode=shortNode.next;
		}
		return null;
	}



}
class Node{
	int data;
	Node next;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node(int data) {
		this.data = data;
	}
}
