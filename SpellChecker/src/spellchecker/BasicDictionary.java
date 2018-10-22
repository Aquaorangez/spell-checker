package spellchecker;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

public class BasicDictionary implements Dictionary {

	private BinaryTree dictionary = new BinaryTree();
	private String[] dictionaryText = new String[5];
	private int count = 0;


	@Override
	public void importFile(String filename) throws Exception {
		String text = readFile(filename).toLowerCase();
		this.dictionaryText = text.split("\\s+");
		dictionary.root = dictionary.insertLevelOrder(dictionaryText, 0, dictionaryText.length - 1);

	}


	@Override
	public void load(String filename) throws Exception {
		this.count = 0;
		String text = readFile(filename).toLowerCase();
		this.dictionaryText = text.split("\\s+");

		for (int i = 0; i < dictionaryText.length; i++) {
			this.count++;
			getDictionary().insert(dictionaryText[i], getDictionary().root);
		}

	}


	@Override
	public void save(String filename) throws Exception {
		ArrayList<String> saved = new ArrayList<String>();
		saved = dictionary.preorder(dictionary.root);
		File f = new File(filename);
		FileWriter fw = new FileWriter(f);
		for (String word : saved) {
			fw.write(word);
		}
		fw.close();
	}


	@Override
	public String[] find(String wordFormat) {
		String word = wordFormat.toLowerCase();
		BinaryTreeNode found = getDictionary().findNode(word, getDictionary().root);
		BinaryTreeNode parent = getDictionary().findParent(found, getDictionary().root);
		String[] closest = new String[2];

		if (found != null) {
			int compare = word.compareTo(found.value);
			int comparePar = word.compareTo(parent.value);

			if (compare < 0 && comparePar > 0) {
				closest[0] = parent.value;
				closest[1] = found.value;
				return closest;
			} else if (compare < 0 && comparePar < 0) {
				while (comparePar < 0) {
					parent = getDictionary().findParent(parent, getDictionary().root);
					comparePar = word.compareTo(parent.value);
					if (comparePar > 0) {
						closest[0] = parent.value;
						break;
					} else
						closest[0] = "";
				}
				closest[1] = found.value;
				return closest;
			} else if (compare > 0 && comparePar < 0) {
				closest[0] = found.value;
				closest[1] = parent.value;
				return closest;
			} else if (compare > 0 && comparePar > 0) {
				closest[0] = found.value;
				closest[1] = "";
				return closest;
			}
		}
		return null;

	}


	@Override
	public void add(String word) {
		if (dictionary.root == null)
			dictionary.root = new BinaryTreeNode(word);

		if (dictionary.findNode(word, dictionary.root) != null) {
			dictionary.insert(word, dictionary.root);
			this.count++;
		}
	}


	@Override
	public BinaryTreeNode getRoot() {
		return this.getDictionary().root;
	}


	@Override
	public int getCount() {
		return this.count;

	}


	@Override
	public BinaryTree getDictionary() {
		return dictionary;
	}


	public void setRoot(BinaryTreeNode root) {
		dictionary.root = root;
	}

}
