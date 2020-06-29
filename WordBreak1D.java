package dp;

import java.util.HashSet;
import java.util.Set;

public class WordBreak1D {
	// dp
	public static boolean wordBreakDp(String s, Set<String> set) {
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[0] = true;
		for(int i=1;i<=n;i++) {
			for(int j=i-1;j>=0;j--) {
				String temp = s.substring(j,i);
				if(set.contains(temp) && dp[j]) {
					dp[i] = true;
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		 String temp_dictionary[] = {"mobile","samsung","sam","sung",  
                 "man","mango","icecream","and",  
                 "go","i","like","ice","cream"};
		 Set<String> set = new HashSet<>();
		 for(String s: temp_dictionary) {
			 set.add(s);
		 }
		 System.out.println(wordBreakDp("ilikesamsung", set));
		 System.out.println(wordBreakDp("iiiiiiii", set)); 
	     System.out.println(wordBreakDp("", set)); 
	     System.out.println(wordBreakDp("ilikelikeimangoiii", set)); 
	     System.out.println(wordBreakDp("samsungandmango", set)); 
	     System.out.println(wordBreakDp("samsungandmangok", set));

	}

}
