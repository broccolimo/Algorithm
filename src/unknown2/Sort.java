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
		System.out.println("������һ�����֣�����֮����һ���ո����:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		for(int i = 1, l = a.length; i < l; i++){
			InsertSort_Insert(i, a[i], a);
			System.out.println("��" + i + "�������Ľ������:");
			for(int j = 0; j < l; j++){
				System.out.print(a[j] + " ");
			}
			System.out.println("");
		}
		System.out.println("-----------------------");
		System.out.println("����������󣬽������:");
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
		System.out.println("������һ�����֣�����֮����һ���ո����:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = Turn(str);
		QuickSort_Main(a, 0, a.length - 1);
		System.out.println("-----------------------");
		System.out.println("����������󣬽������:");
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
	//��left��ʼ��2��index������left��ʼ���������++����
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
	 * ���ǵ�Ŀ���Ǵ�С�����������
	 * ����󶥶Ѻ����������Ǹ��ڵ㣬��a[0]
	 * ���Ҫ��С��������,��ôa[a.length - 1](��������һ����)��Ӧ����a[0],��������
	 * ���������������������
	 * ֮���ʣ�µ�����(��ȥ������)�ٽ�һ�δ󶥶ѣ��ظ�֮ǰ�Ĳ�����ѭ������
	 * 
	 * Ϊ������ʱ��pos = 0?
	 * �������n/2-1,�����ظ���
	 * ����Ҳ˵���˽��󶥶ѵı�Ҫ�� ����������ӱȶ��ӻ�������
	 * ���Է��Ĵ�0��ʼ���µݹ�,������Ǵ󶥶ѣ����������ǻ�����ڸ���㴦
	 * 
	 * ������3������
	 * ���󶥶ѣ����ģ�����
	 */
	@Test
	public void HeapSort(){
		try{
			System.out.println("������һ�����֣�����֮����һ���ո����:");
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
			System.out.println("��������󣬽������:");
			for(int i = 0; i < length; i++){
				System.out.print(a[i] + " ");
			}
		}
		catch(Exception e)
		{
			System.out.println("��������");
		}
	}

	/**
	 * ���󶥶�
	 * 
	 * Ϊʲô���ѵ�ʱ��ѭ���������±��ǴӺ���ǰ��
	 * 
	 * Heapifyÿ�δ���Ķ���pos�ڵ�����Ķ��ӣ��ӷ�����˵�ǳ��µ�
	 * һ�����ֶ��ӱȰְ��������ʹ�����ӣ�����Ҳ�ǳ��µ�
	 * Ҳ����˵��ÿ��Heapify���ܱ�֤��posΪ���ڵ��������һ���󶥶�
	 * ������������ѭ��������������ض���һ���󶥶�
	 */
	public void HeapSort_BuildHeap(int[] a){
		int pos = (int) (Math.floor(a.length / 2) - 1);
		for(int i = pos; i >= 0; i--){
			HeapSort_Heapify(a, i, a.length);
		}
	}

	/**
	 * ���󶥶ѵĺ��ĺ���
	 * n�ĺ����������в����Ԫ�ظ���
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
	 * ��С���ѵĺ��ĺ���
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
	 * ������뷨
	 * Ϊ�β���ֱ�ӴӸ��ڵ����µݹ�
	 * 
	 * ��Heapify�У�������ӱȰְִ󣬽���λ�ã������ݹ��ԭ����
	 * ����ԭ�����ӵĶ��ӱȰְֻ���Ҳ���ǱȽϰְֺ����ӵĴ�С
	 * 
	 * ���ֱ�ӴӸ��ڵ�ݹ飬��һ������ᱻ����
	 * ���ӱȰְִ� ���� ���ӻ��Ȱְִ� ���� ���ӻ��ȶ��Ӵ� �޷�����
	 * ���յĽ�����޷������������ڸ��ڵ�
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
	 * ÿ��ѭ����ѡȡ��С�������ڵ�ǰѭ���ĵ�һλ 
	 * ���� �ȶ�n^2
	 */
	@Test
	public void SelectionSort(){
		System.out.println("������һ�����֣�����֮����һ���ո����:");
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
		System.out.println("��ѡ������󣬽������:");
		for(int i = 0; i < length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	/**
	 * ��������
	 * 2 5 3 0 2 3 0 3
	 */
	@Test
	public void CountSort(){
		System.out.println("������һ�����֣�����֮����һ���ո����:");
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
		System.out.println("c ��״̬��");
		printArray(c);
		for(int i = 1; i < k; i++){
			c[i]+=c[i - 1];
		}
		System.out.println("c ��ȫ�壺");
		printArray(c);
		int[] b = new int[length];
		for(int i = length - 1; i >= 0; i--){
			b[c[a[i]] - 1] = a[i];
			c[a[i]]--;
			System.out.println("��" + (length - i) + "�� b");
			printArray(b);
			System.out.println("��" + (length - i) + "�� c");
			printArray(c);
		}
	}
}
