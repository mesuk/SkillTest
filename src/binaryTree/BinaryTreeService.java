package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeService {

    private TreeNode root;

    public BinaryTreeService() {

    }

    public void addNode(Integer value) {
        //[3,9,20,null,null,15,7]
        if (root == null) {
            root = new TreeNode(value);
            return;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        TreeNode lastNode = new TreeNode(value);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            if (currentNode.left == null) {
                currentNode.left=(lastNode);
                break;
            } else if (currentNode.right == null) {
                currentNode.right=(lastNode);
                break;
            }
            queue.add(currentNode.left);
            queue.add(currentNode.right);
        }
    }

    public void printBfs() {
        //[3,9,20,null,null,15,7]

        List<List<Integer>> result = levelOrder(root);
        result.forEach(levelResult1 -> {
            System.out.println();
            levelResult1.forEach(item -> {
                System.out.print("" + item + " ");
            });

        });

    }

    public boolean isSymmetric() {
        //LeetCode 101. Symmetric binary tree

        if(root==null){
            return true;
        }



        return isMirror(root.left,root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        if(left==null && right==null)
            return true;


        if(left==null || right==null)
            return false;

        return (left.val==right.val)&&isMirror(left.right,right.left)&&isMirror(left.left,right.right);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        //LeetCode 102. Binary Tree Level Order Traversal
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }

}
