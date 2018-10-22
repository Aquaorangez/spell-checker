package spellchecker;

import javax.swing.plaf.synth.*;
import javax.xml.soap.Node;

public class Main {

	public static void main(String[] args) throws Exception {
		BasicSpellChecker basicSpellChecker = new BasicSpellChecker();
		String dictionaryImportPath = "small_dictionary.txt";
		String dictionaryPath = "small_dictionary.pre";
		String documentPath = "small_document_four_unknown.txt";

		basicSpellChecker.importDictionary(dictionaryImportPath);
		basicSpellChecker.saveDictionary(dictionaryPath);

		basicSpellChecker.loadDocument(documentPath);

		// BinaryTreeNode demo2 =
		// basicSpellChecker.getDictionary().getDictionary().findNode("These",
		// basicSpellChecker.getDictionary().getRoot());
		// System.out.println(demo2.value);

		// String[] demo = basicSpellChecker.getDictionary().find("explosins");

		// // if (demo == null) {
		// // System.out.println("null");
		// }
		basicSpellChecker.replaceText(87, 96, "explosions");
		String[] demo = basicSpellChecker.spellCheck(true);

		for (int i = 0; i <= demo.length - 1; i++) {
			System.out.println(demo[i]);
		}
	}

}
