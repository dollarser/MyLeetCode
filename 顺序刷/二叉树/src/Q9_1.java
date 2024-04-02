import java.util.*;

/**
 * 通过每层计数的方式计算每一层的元素数
 */
public class Q9_1 {
    static class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> retVal = new ArrayList<Integer>();
            Queue<TreeNode> tmpQueue = new LinkedList<TreeNode>();
            if (root != null) tmpQueue.add(root);

            while (tmpQueue.size() != 0){
                int size = tmpQueue.size();
                List<Integer> lvlVals = new ArrayList<Integer>();
                for (int index = 0; index < size; index++){
                    TreeNode node = tmpQueue.poll();
                    lvlVals.add(node.val);
                    if (node.left != null) tmpQueue.add(node.left);
                    if (node.right != null) tmpQueue.add(node.right);
                }
                retVal.add(Collections.max(lvlVals));
            }

            return retVal;
        }
    }
}
