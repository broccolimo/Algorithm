package jy;

import java.util.Scanner;

import org.junit.Test;

public class Sort {
	public int[] Turn(String[] str){
		int l = str.length;
		int[] a = new int[l];
		for(int i = 0; i < l; i++){
			a[i] = Integer.parseInt(str[i]);
		} 
		return a;
	}
	
	public void printArray(int[] a){
		int length = a.length;
		for(int i = 0; i < length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}

	@Test
	public void InsertSort(){
		System.out.println("请输入一组数字，数字之间用一个空格隔开:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		for(int i = 1, l = a.length; i < l; i++){
			InsertSort_Insert(i, a[i], a);
			System.out.println("第" + i + "次排序后的结果如下:");
			for(int j = 0; j < l; j++){
				System.out.print(a[j] + " ");
			}
			System.out.println("");
		}
		System.out.println("-----------------------");
		System.out.println("经插入排序后，结果如下:");
		for(int i = 0, l = a.length; i < l; i++){
			System.out.print(a[i] + " ");
		}
	}

	public void InsertSort_Insert(int pos, int value, int[] a){
		int i = pos - 1;
		while(i >= 0 && a[i] > value){
			a[i + 1] = a[i];
			i--;
		}
		a[i + 1] = value;
	}

	@Test
	public void QuickSort(){
		System.out.println("请输入一组数字，数字之间用一个空格隔开:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		QuickSort_Main(a, 0, a.length - 1);
		System.out.println("-----------------------");
		System.out.println("经快速排序后，结果如下:");
		for(int i = 0, l = a.length; i < l; i++){
			System.out.print(a[i] + " ");
		}
	}

	public void QuickSort_Main(int[] a, int left, int right){
		if(left < right){
			int pos = QuickSort_Partition(a, left, right);
			QuickSort_Main(a, left, pos - 1);
			QuickSort_Main(a, pos + 1, right);
		}
	}
	//从left开始有2个index，都从left开始，区分情况++即可
	public int QuickSort_Partition(int[] a, int left, int right){
		int value = a[right];
		int pos = left;
		for(int i = left; i < right; i++){
			if(a[i] <= value){
				int temp = a[i];
				a[i] = a[pos];
				a[pos++] = temp;
			}
		}
		a[right] = a[pos];
		a[pos] = value;
		return pos;
	}

	/**
	 * 我们的目的是从小到大进行排序
	 * 建完大顶堆后最大的数就是根节点，是a[0]
	 * 如果要从小到大排序,那么a[a.length - 1](数组的最后一个数)就应该是a[0],交换即可
	 * 这样最大的数就排在最后了
	 * 之后对剩下的数组(除去最靠后的数)再建一次大顶堆，重复之前的操作，循环即可
	 * 
	 * 为何排序时，pos = 0?
	 * 如果还是n/2-1,岂不是重复了
	 * 这里也说明了建大顶堆的必要性 不会出现孙子比儿子还大的情况
	 * 所以放心从0开始向下递归,结果还是大顶堆，最大的数还是会出现在根结点处
	 * 
	 * 堆排序3个函数
	 * 建大顶堆，核心，排序
	 */
	@Test
	public void HeapSort(){
		try{
			System.out.println("请输入一组数字，数字之间用一个空格隔开:");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			sc.close();
			String[] str = s.split(" ");
			int[] a = Turn(str);
			int length = a.length;
			HeapSort_BuildHeap(a);
			for(int i = length - 1; i > 0; i--){
				int temp = a[0];
				a[0] = a[i];
				a[i] = temp;
				HeapSort_Heapify(a, 0, i);
			}
			System.out.println("经堆排序后，结果如下:");
			for(int i = 0; i < length; i++){
				System.out.print(a[i] + " ");
			}
		}
		catch(Exception e)
		{
			System.out.println("输入有误！");
		}
	}

	/**
	 * 建大顶堆
	 * 
	 * 为什么建堆的时候循环的数组下标是从后往前的
	 * 
	 * Heapify每次处理的都是pos节点和它的儿子，从方向上说是朝下的
	 * 一旦出现儿子比爸爸厉害，就处理儿子，方向也是朝下的
	 * 也就是说，每次Heapify都能保证以pos为根节点的子树是一个大顶堆
	 * 这样从下往上循环，最后整棵树必定是一个大顶堆
	 */
	public void HeapSort_BuildHeap(int[] a){
		int pos = (int) (Math.floor(a.length / 2) - 1);
		for(int i = pos; i >= 0; i--){
			HeapSort_Heapify(a, i, a.length);
		}
	}

	/**
	 * 建大顶堆的核心函数
	 * n的含义是数组中参与的元素个数
	 */
	public void HeapSort_Heapify(int[] a, int pos, int n){
		int left = pos * 2 + 1;
		int right = pos * 2 + 2;
		int largest = 0;
		if(left < n && a[left] > a[pos]){
			largest = left;
		}
		else{
			largest = pos;
		}
		if(right < n && a[right] > a[largest]){
			largest = right;
		}
		if(pos != largest){
			int temp = a[pos];
			a[pos] = a[largest];
			a[largest] = temp;
			HeapSort_Heapify(a, largest, n);
		}
	}

	/**
	 * 建小顶堆的核心函数
	 */
	public void HeapSort_HeapifyMin(int[] a, int pos, int n){
		int left = pos * 2 + 1;
		int right = pos * 2 + 2;
		int min = 0;
		if(left < n && a[left] < a[pos]){
			min = left;
		}
		else{
			min = pos;
		}
		if(right < n && a[right] < a[min]){
			min = right;
		}
		if(pos != min){
			int temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
			HeapSort_Heapify(a, min, n);
		}
	}

	/**
	 * 错误的想法
	 * 为何不能直接从根节点向下递归
	 * 
	 * 在Heapify中，如果儿子比爸爸大，交换位置，继续递归的原因是
	 * 可能原来儿子的儿子比爸爸还大，也就是比较爸爸和孙子的大小
	 * 
	 * 如果直接从根节点递归，有一种情况会被忽略
	 * 儿子比爸爸大 交换 孙子还比爸爸大 交换 孙子还比儿子大 无法交换
	 * 最终的结果是无法把最大的数置于根节点
	 */
	@Test
	public void HeapSort_Error_Heap(){
		int[] a = {1, 2, 3, 4, 5, 6, 7, 16, 9, 10, 11, 12, 13, 14, 15, 8};
		int length = a.length;
		HeapSort_Heapify(a, 0, length);
		for(int i = 0; i < length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	/**
	 * 每次循环都选取最小的数放在当前循环的第一位 
	 * 最慢 稳定n^2
	 */
	@Test
	public void SelectionSort(){
		System.out.println("请输入一组数字，数字之间用一个空格隔开:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		int length = a.length;
		for(int i = 0; i < length - 1; i++){
			int min = i;
			for(int j = i + 1; j < length; j++){
				if(a[j] < a[min]){
					min = j;
				}
			}
			if(i != min){
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
		System.out.println("经选择排序后，结果如下:");
		for(int i = 0; i < length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	/**
	 * 测试数据
	 * 2 5 3 0 2 3 0 3
	 */
	@Test
	public void CountSort(){
		System.out.println("请输入一组数字，数字之间用一个空格隔开:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		int k = 0;
		int length = a.length;
		for(int i = 0; i < length; i++){
			if(a[i] > k){
				k = a[i];
			}
		}
		k++;
		int[] c = new int[k];
		for(int i = 0; i < length; i++){
			c[a[i]]++;
		}
		System.out.println("c 初状态：");
		printArray(c);
		for(int i = 1; i < k; i++){
			c[i]+=c[i - 1];
		}
		System.out.println("c 完全体：");
		printArray(c);
		int[] b = new int[length];
		for(int i = length - 1; i >= 0; i--){
			b[c[a[i]] - 1] = a[i];
			c[a[i]]--;
			System.out.println("第" + (length - i) + "次 b");
			printArray(b);
			System.out.println("第" + (length - i) + "次 c");
			printArray(c);
		}
	}
}
