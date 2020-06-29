package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreak {
	/*
	 * 
	 * 
	 * */
	// recursion
	public static boolean wordBreakRecursion(String s, int left, int right, Set<String> set) {
		if(left > right) {
			return true;
		}
		for(int i=left;i<=right;i++) {
			String temp = s.substring(left,i+1);
			if(set.contains(temp) && wordBreakRecursion(s, i+1, right, set)) {
				return true;
			}
		}
		return false;
	}
	public static boolean wordBreakRecursion(String s, Set<String> set) {
		return wordBreakRecursion(s, 0, s.length()-1, set);
	}
	
	
	// recursion with Memo
	public static boolean wordBreakRecursionMemo(String s, int left, int right, Set<String> set, Map<String, Boolean> map) {
		String key  = left+" "+right;
		if(map.containsKey(key)) {
			return map.get(key);
		}
		if(left > right) {
			map.put(key, true);
			return true;
		}
		for(int i=left;i<=right;i++) {
			String temp = s.substring(left,i+1);
			if(set.contains(temp) && wordBreakRecursionMemo(s, i+1, right, set, map)) {
				map.put(key, true);
				return true;
			}
		}
		map.put(key, false);
		return false;
	}
	public static boolean wordBreakRecursionMemo(String s, Set<String> set) {
		Map<String, Boolean> map = new HashMap<>();
		return wordBreakRecursionMemo(s, 0, s.length()-1, set, map);
	}
	
	// dp
	public static boolean wordBreakDp(String s, Set<String> set) {
		if(s.length() == 0) {
			return true;
		}
		boolean[][] dp = new boolean[s.length()][s.length()];
		for(int i=0;i<s.length();i++) {
			if(set.contains(s.substring(i, i+1))) {
				dp[i][i] = true;
			}
			else {
				dp[i][i] = false;
			}
		}
		
		int n = s.length();
		for(int l=2;l<=n;l++) {
			for(int i=0;i<n-l+1;i++) {
				int j=i+l-1;
				if(set.contains(s.substring(i,j+1))) {
					dp[i][j] = true;
				}
				else {
					for(int k=i;k<j;k++) {
						String temp = s.substring(i,k+1);
						if(set.contains(temp) && dp[k+1][j]) {
							dp[i][j]  = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		 String temp_dictionary[] = {"mobile","samsung","sam","sung",  
                 "man","mango","icecream","and",  
                 "go","i","like","ice","cream"};
		 Set<String> set = new HashSet<>();
		 for(String s: temp_dictionary) {
			 set.add(s);
		 }
		 System.out.println(wordBreakRecursion("ilikesamsung", set));
		 System.out.println(wordBreakRecursion("iiiiiiii", set)); 
        System.out.println(wordBreakRecursion("", set)); 
        System.out.println(wordBreakRecursion("ilikelikeimangoiii", set)); 
        System.out.println(wordBreakRecursion("samsungandmango", set)); 
        System.out.println(wordBreakRecursion("samsungandmangok", set));
        System.out.println("-----------------------");
        System.out.println(wordBreakRecursionMemo("ilikesamsung", set));
		 System.out.println(wordBreakRecursionMemo("iiiiiiii", set)); 
       System.out.println(wordBreakRecursionMemo("", set)); 
       System.out.println(wordBreakRecursionMemo("ilikelikeimangoiii", set)); 
       System.out.println(wordBreakRecursionMemo("samsungandmango", set)); 
       System.out.println(wordBreakRecursionMemo("samsungandmangok", set));
       System.out.println("-----------------------");
       System.out.println(wordBreakDp("ilikesamsung", set));
		 System.out.println(wordBreakDp("iiiiiiii", set)); 
      System.out.println(wordBreakDp("", set)); 
      System.out.println(wordBreakDp("ilikelikeimangoiii", set)); 
      System.out.println(wordBreakDp("samsungandmango", set)); 
      System.out.println(wordBreakDp("samsungandmangok", set));
       
	}

}
