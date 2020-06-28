package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Job {
	int start;
	int finish;
	int profit;
	
	Job(int start, int finish, int profit) {
		this.start = start;
		this.finish = finish;
		this.profit = profit;
	}
}

public class WeightedJob {
	
	/*
	 * sort jobs on finish time
	 * 
	 * dp[n] = 0						, n=0;
	 * 		 = jobs[n-1].profit 		, n=1;
	 * 		 = Math.max(dp[n-1], jobs[n-1].profit + dp[k])
	 * 								k -> recent non colliding job
	 * 
	 * */
	
	public static int nonCollidingJob(Job[] jobs, int n) {
		int index = -1;
		for(int i=n-1;i>=1;i--) {
			if(jobs[i-1].finish <= jobs[n-1].start) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static int maxProfitRecursionHelper(Job[] jobs, int n) {
		if(n==0) {
			return 0;
		}
		else if(n==1) {
			return jobs[n-1].profit;
		}
		int inc = jobs[n-1].profit;
		int index = nonCollidingJob(jobs, n);
		if(index!=-1) {
			inc += maxProfitRecursionHelper(jobs, index);
		}
		int exc = maxProfitRecursionHelper(jobs, n-1);
		return Math.max(inc, exc);
	}
	// recursion
	public static int maxProfitRecursion(Job[] jobs, int n) {
		Arrays.sort(jobs, (j1,j2) -> j1.finish-j2.finish);
		return maxProfitRecursionHelper(jobs, n);
	}
	
	
	public static int maxProfitRecursionMemoHelper(Job[] jobs, int n, Map<Integer, Integer> map) {
		if(map.containsKey(n)) {
			return map.get(n);
		}
		if(n==0) {
			map.put(n, 0);
			return 0;
		}
		else if(n==1) {
			int res =  jobs[n-1].profit;
			map.put(n, res);
			return res;
		}
		int inc = jobs[n-1].profit;
		int index = nonCollidingJob(jobs, n);
		if(index!=-1) {
			inc += maxProfitRecursionMemoHelper(jobs, index, map);
		}
		int exc = maxProfitRecursionMemoHelper(jobs, n-1, map);
		int res = Math.max(inc, exc);
		map.put(n, res);
		return res;
	}
	
	// recursion with Memo
	public static int maxProfitRecursionMemo(Job[] jobs, int n) {
		Arrays.sort(jobs, (j1,j2) -> j1.finish-j2.finish);
		Map<Integer, Integer> map = new HashMap<>();
		return maxProfitRecursionMemoHelper(jobs, n, map);
	}
	
	// dp
	public static int maxProfitDpHelper(Job[] job, int n) {
		int[] dp = new int[n+1];
		int maxProfit = 0;
		for(int i=0;i<=n;i++) {
			if(i==0) {
				dp[i] = 0;
			}
			else if(i==1) {
				dp[i] = job[i-1].profit;
			}
			else {
				dp[i] = job[i-1].profit;
				for(int j=i-1;j>=1;j--) {
					if(job[j-1].finish <= job[i-1].start) {
						dp[i] += dp[j];
						break;
					}
				}
			}
			maxProfit = Math.max(maxProfit, dp[i]);
		}
		return maxProfit;
	}
	
	public static int maxProfitDp(Job[] jobs, int n) {
		Arrays.sort(jobs, (j1,j2) -> j1.finish-j2.finish);
		return maxProfitDpHelper(jobs, n);
	}

	public static void main(String[] args) {
		Job[] jobs1 = {new Job(3, 10, 20), 
				new Job(1, 2, 50), 
				new Job(6, 19, 100), 
				new Job(2, 100, 200) };
		Job[] jobs2 = {new Job(1, 3, 5), 
				new Job(2, 5, 6), 
				new Job(4, 6, 5), 
				new Job(6, 7, 4),
				new Job(5,8,11),
				new Job(7,9,2)};
		System.out.println(maxProfitRecursion(jobs2, jobs2.length));
		System.out.println(maxProfitRecursionMemo(jobs2, jobs2.length));
		System.out.println(maxProfitDp(jobs2, jobs2.length));
	}

}
