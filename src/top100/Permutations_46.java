package top100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 *
 * @author: Xiang Mingzhe
 * @version Permutations_46.java, v 0.1 2019/12/25 10:03 XiangMingZhe Exp $
 **/
public class Permutations_46 {
	public static List<List<Integer>> permute(int[] nums) {
		if (nums.length == 0) {
			return Collections.emptyList();
		}
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> preList = new LinkedList<>();
		boolean[] solider = new boolean[nums.length];
//		collection(res, nums, preList);
		collectionWithSolider(res, nums, preList, solider);
		return res;
	}

	/**
	 * 通用的回溯写法 {@code List.contains()} O(N)的复杂度 整体效率并不高
	 * 但此处使用了
	 * @param res
	 * @param nums
	 * @param preList
	 */
	private static void collection(List<List<Integer>> res, int[] nums, List<Integer> preList) {
		if (preList.size() == nums.length) {
			res.add(new ArrayList<>(preList));
			return;
		}
		for (int num : nums) {
			if (preList.contains(num)) {
				continue;
			}
			preList.add(num);
			collection(res, nums, preList);
			preList.remove(preList.size() - 1);
		}
	}

	/**
	 * Discuss里 别人比较好的方式 使用哨兵占位标记该元素是否被记录过
	 * 为避免使用 {@code List.contains()} O(N)的复杂度
	 * @param res
	 * @param nums
	 * @param preList
	 * @param solider
	 */
	private static void collectionWithSolider(List<List<Integer>> res, int[] nums, List<Integer> preList, boolean[] solider) {
		if (preList.size() == nums.length) {
			res.add(new ArrayList<>(preList));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (solider[i]) {
				continue;
			}
			solider[i] = true;
			preList.add(nums[i]);
			collectionWithSolider(res, nums, preList, solider);
			preList.remove(preList.size() - 1);
			solider[i] = false;
		}
	}

	public static void main(String[] args) {
		System.out.println(permute(new int[]{1,2,3}));
	}
}
