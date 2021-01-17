
import java.util.*;

public class RecursionQuestion {

	public static String killCommas(String s) {
		if(s.equals("")) {
			return "";
		}
		if(s.length() == 1) {
			return s;
		}
		
		if(s.charAt(0) == ',') {
			return killCommas(s.substring(1));
		}
		else {
			return s.charAt(0) + killCommas(s.substring(1));
		}
	}
	
	public static int sumDigits(int num) {
		if(num < 10) {
			return num;
		}
		
		return (num % 10) + sumDigits(num / 10);
	}
	
	public static void powerSet(int[] arr) {
		for(ArrayList<Integer> set : createPowSet(arr)) {
			System.out.println(set);			
		}
	}
	
	static ArrayList<ArrayList<Integer>> createPowSet(int[] arr){
		if(arr.length == 0) {
			return null;
		}
		if(arr.length == 1) {
			ArrayList<Integer> al1 = new ArrayList<Integer>();
			al1.add(arr[0]);
			ArrayList<ArrayList<Integer>> al2 = new ArrayList<ArrayList<Integer>>();
			al2.add(al1);
			return al2;
		}
		
		ArrayList<ArrayList<Integer>> currPowSet = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currSet = new ArrayList<Integer>();
		currPowSet.add(currSet);
		for(int i = 0; i < arr.length; i++) {
			currSet.add(arr[i]);
			int[] partialCopy = new int[arr.length - 1];
			int offset = 0;
			for(int j = 0; j < arr.length; j++) {
				if(j != i) {
					partialCopy[j - offset] = arr[j];
				}
				else {
					offset = 1;
				}
			}
			currPowSet.addAll(createPowSet(partialCopy));
		}
		
		ArrayList<ArrayList<Integer>> uniquePowSet = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> list : currPowSet) {
			if(!uniquePowSet.contains(list)) {
				uniquePowSet.add(list);
			}
		}
		uniquePowSet.sort((ArrayList<Integer> x, ArrayList<Integer> y) -> y.size() - x.size());
		return uniquePowSet;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(killCommas("a,apple,c, ,d").equals("aapplec d"));
		System.out.println(killCommas("d").equals("d"));
		System.out.println(killCommas(",d").equals("d"));
		System.out.println(killCommas("").equals(""));
		
		System.out.println(sumDigits(45) == 9);
		System.out.println(sumDigits(4) == 4);
		System.out.println(sumDigits(10) == 1);
		System.out.println(sumDigits(8673) == 24);
		
		System.out.println();
		
		int[] set1 = {1, 2, 3, 4};
		powerSet(set1);
		
		System.out.println();
		
		int[] set2 = {1, 2, 3, 4, 8, 9};
		powerSet(set2);	
		
		System.out.println();
		
		int[] set3 = {9};
		powerSet(set3);			
	}

}
