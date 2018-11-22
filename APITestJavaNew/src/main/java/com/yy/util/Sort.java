package com.yy.util;

public class Sort {
	
	public static void main(String [] args){
		int a[]={1,2,5,4,6,4,32,9,6};
		//selectedSort(a);
		//insertSort(a);
		QuickSort(a,1,a.length-1);
		printArray(a);
	}
	

	// 快速排序,基本思想：选取一个值作为基准值，通过一趟排序把比它小得的元素放左边，比他大的元素放右边。再继续对两组元素作同样的操作，递归调用
	public static int[] QuickSort(int a[], int low, int high) {
		if (low < high) {
			int base = Partition(a, low, high);
			QuickSort(a, low, base - 1);
			QuickSort(a, base + 1, high);
		}

		return a;
	}

	// 根据基准值分区，比基准值小的放左边，比基准值大的放右边
	public static int Partition(int a[], int low, int high) {
		int base = a[low];
		while (low < high) {
			while (low < high && a[high] >= base) {
				high--;
			}
			swap(a, low, high);

			while (low < high && a[low] <= base) {
				low++;
			}
			swap(a, low, high);
		}
		return low;
	}

	// 交换位置
	public static void swap(int a[], int low, int high) {
		int temp;
		temp = a[low];
		a[low] = a[high];
		a[high] = temp;
	}
	public static void insertSort(int [] a){
		for(int i=1;i<a.length;i++){
			int temp=a[i];
			int j;
			for(j=i-1;j>=0&&a[j]>temp;j--){
				a[j+1]=a[j];				
			}
			a[j+1]=temp;
			System.out.println("当前j为"+j);
		}
	}
	public static void selectedSort(int [] a)
	{
		for(int i=0;i<a.length;i++){
		   int min=i;
		   for(int j=i+1;j<a.length;j++)
		   {
			   if(a[j]<a[min]){
				   min=j;
			   }
		   }
		   if(i!=min){
			   int temp=a[min];
			   a[min]=a[i];
			   a[i]=temp;
		   }
		}		
	}
	public static void printArray(int[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
		
	}
}


