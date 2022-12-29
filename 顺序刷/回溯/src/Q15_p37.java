import java.util.LinkedList;
import java.util.List;

/**
 * 37. 解数独
 * https://leetcode.cn/problems/sudoku-solver/
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则： 数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 空白格用 '.' 表示。
 */
public class Q15_p37 {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        Solution solution = new Solution();
        solution.solveSudoku(board);
        int cnt = 0;
        //数组转链表
        List<List<Character>> ans = new LinkedList();
        for (int i = 0; i < board.length; i++) {
            List<Character> list = new LinkedList();
            for (int j = 0; j < board[i].length; j++) {
                Character c = board[i][j];
                if (c=='.') cnt++;
                list.add(c);
            }
            //Arrays.asList(board)
            ans.add(list);
        }
        System.out.println(ans);
        System.out.println(cnt);
    }

    //回溯
    static class Solution {
        public void solveSudoku(char[][] board) {
            backtracking(board);
        }

        Boolean backtracking(char[][] board) {
            //递归 数独中的空格约51个, 如果不剪枝的递归将是9^51次方,通过判减少数字范围,缩小9,通过剪枝缩小51
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //非空格跳过
                    if (board[i][j] != '.') continue;
                    //尝试填入1~9,记录是否存在匹配的结果
                    for (char c = '1'; c <= '9'; c++) {
                        //满足数独条件,则填入并立即返回
                        if (check(board, i, j, c)) {
                            board[i][j] = c;
                            /**
                             * 注意存在填入1~9,找到一组合适的立即返回,不再修改board
                             */
                            if (backtracking(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    /**
                     * 填入1~9都不满足的情况,返回false
                     */
                    return false;
                }
            }
            //遍历完没有返回false,则返回true
            return true;
        }
        /**
         * 自己写的有问题的部分
          */
        void backtracking_bak(char[][] board, int row) {
            //结束条件
            if (row >= 9) return;
            //递归
            for (int i = 0; i < board[row].length; i++) {
                //空格尝试填入
                if (board[row][i]=='.') {
                    //尝试填入1~9
                    for (char j = '1'; j <= '9'; j++) {
                        //满足数独条件,则填入
                        System.out.println(check(board, row, i, j));
                        System.out.println("("+ row+" "+i+")");
                        if (check(board, row, i, j)) {
                            board[row][i] = j;
                            backtracking_bak(board, row+1);
                            board[row][i] = '.';
                        }
                    }
                }
            }
        }
        //复杂度 27
        Boolean check(char[][] board, int row, int col, char num) {
            //检查行
            for (int i = 0; i < 9; i++) {
                if (board[i][col]==num) {
                    return false;
                }
            }
            //检查列
            for (int i = 0; i < 9; i++) {
                if (board[row][i]==num) {
                    return false;
                }
            }
            //检查小方块
            for (int i = row/3*3; i < row/3*3+3; i++) {
                for (int j = col/3*3; j < col/3*3+3; j++) {
                    if (board[i][j] == num) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
