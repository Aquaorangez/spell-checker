package spellchecker;

import java.util.*;

public class BinaryTree {
	private ArrayList<String> all = new ArrayList<String>();
	public BinaryTreeNode root;


	public BinaryTreeNode insert(String word, BinaryTreeNode cursor) {
		if (this.root == null) {
			this.root = new BinaryTreeNode(word);
		} else {
			if (cursor == null) {
				return new BinaryTreeNode(word);
			}
			int compare = word.compareTo(cursor.value);
			if (compare > 0)
				cursor.right = insert(word, cursor.right);
			else
				cursor.left = insert(word, cursor.left);
		}
		return cursor;
	}


	public BinaryTreeNode findNode(String word, BinaryTreeNode root) {
		while (root != null) {
			int compare = word.compareTo(root.value);
			if (compare > 0)
				if (root.right == null)
					return root;
				else
					return findNode(word, root.right);
			else if (compare < 0)
				if (root.left == null)
					return root;
				else
					return findNode(word, root.left);
			else
				return null;
		}
		return null;
	}


	public BinaryTreeNode findParent(BinaryTreeNode child, BinaryTreeNode currentRoot) {
		if (child == root || child == null)
			return null;

		else {
			int compare = child.value.compareTo(currentRoot.value);
			if (currentRoot.left == child || currentRoot.right == child)
				return currentRoot;
			else {
				if (compare < 0)
					return findParent(child, currentRoot.left);
				else
					return findParent(child, currentRoot.right);
			}
		}
	}


	public ArrayList<String> levelOrder() {
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {

			BinaryTreeNode tempNode = queue.poll();
			all.add(tempNode.value + "\n");

			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}
		return all;
	}


	public ArrayList<String> preorder(BinaryTreeNode root) {
		if (root == null) {
			return all;
		}
		all.add(root.value + "\n");
		preorder(root.left);
		preorder(root.right);

		return all;
	}


	public BinaryTreeNode insertLevelOrder(String[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		BinaryTreeNode node = new BinaryTreeNode(arr[mid]);

		node.left = insertLevelOrder(arr, start, mid - 1);
		node.right = insertLevelOrder(arr, mid + 1, end);

		return node;
	}

}
