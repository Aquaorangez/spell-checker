package spellchecker;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {
	private BasicDictionary dictionary = new BasicDictionary();
	private String document;
	private int index;


	@Override
	public void importDictionary(String filename) throws Exception {
		dictionary.load(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		dictionary.load(filename);
	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		dictionary.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		String text = readFile(filename);
		this.document = text;
	}


	@Override
	public void saveDocument(String filename) throws Exception {
		// TODO Auto-generated method stub

	}


	@Override
	public String getText() {
		return this.document;

	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		this.index = 0;

		String expr = "\\b[\\w']+\\b";
		Pattern p = Pattern.compile(expr);
		String text = this.document.substring(this.index);
		Matcher m = p.matcher(text);

		while (m.find()) {
			String[] demo = dictionary.find(m.group());
			this.index = this.index + m.group().length();
			if (demo == null) {

				if (this.index > this.document.length())
					break;
			}

			else {
				String[] checker = new String[4];
				checker[0] = m.group();
				checker[1] = Integer.toString(m.start());
				checker[2] = demo[0];
				checker[3] = demo[1];

				return checker;

			}
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		if (dictionary.find(word) != null)
			dictionary.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		String replacement = this.document.substring(startIndex, endIndex);
		this.document = this.document.substring(0, startIndex).concat(replacementText)
				.concat(this.document.substring(endIndex));

		this.index = endIndex + replacementText.length() - replacement.length();
	}


	public BasicDictionary getDictionary() {
		return this.dictionary;
	}

}
