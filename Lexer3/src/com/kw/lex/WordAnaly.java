package com.kw.lex;

import com.kw.lex.XMLReader;
import com.kw.lex.Mainframe;

public class WordAnaly {
	private int line = 1;
	private String word;
	private char[] Input;
	private String[] Defind; // 标识符数组
	private String[] Operator; // 操作符数组
	private String[] Exception; // 例外符数组
	private String analy = "";
	private String showString = "";
	private int j = 0;
	private boolean illegal = true; // 非法字符
	private boolean isstring = false; // 是否为字符串
	private boolean ischar = false; // 是否为字符

	public WordAnaly(String word, String XMLPath) {
		int start_comment; // `//` `/*`
		int end_comment; // `*/`以及 紧跟`//`之后的`\n`
		int flag;
		String test = "";
		String head, tail;

		while (true) {
			start_comment = word.indexOf("//");
			if (start_comment == -1)
				break;
			end_comment = word.indexOf("\n", start_comment);

			head = word.substring(0, start_comment);
			tail = word.substring(end_comment, word.length());
			word = new String(head + tail);
		}

		while (true) {
			start_comment = word.indexOf("/*");
			if (start_comment == -1)
				break;
			flag = word.indexOf("\n", start_comment);
			System.out.println(flag);
			end_comment = word.indexOf("*/", start_comment);
			while (flag < end_comment) {
				test = test + '\n';
				flag = word.indexOf("\n", flag + 1);
				// System.out.println("孔炜");
				// System.out.println(flag);

			}
			head = word.substring(0, start_comment);
			tail = word.substring(end_comment + 2, word.length());
			word = new String(head + test + tail);
		}

		this.word = word;
		Input = new char[word.length()];
		Defind = new XMLReader().startprase(XMLPath, "sign"); // 设置标识符
		Operator = new XMLReader().startprase(XMLPath, "opeator"); // 设置操作符
		Exception = new XMLReader().startprase(XMLPath, "special"); // 设置例外符
		for (int i = 0; i < word.length(); i++) // 把输入的字符串变成char数组
		{
			Input[i] = word.charAt(i);
		}

	}

	/**
	 * 操作符
	 */
	private boolean checkSpecialWord(char word) {
		boolean isspecial = false;
		String testword = word + "";
		for (int i = 0; i < Operator.length; i++) {
			if (testword.equals(Operator[i])) {
				isspecial = true;
			} else if (testword.equals(",")) {
				isspecial = true;
			}
		}
		return isspecial;

	}

	/**
	 * _,$
	 */
	private boolean checkException(char word) {
		boolean isexception = false;
		String testword = word + "";
		for (int i = 0; i < Exception.length; i++) {
			if (testword.equals(Exception[i])) {
				isexception = true;
			}
		}
		return isexception;
	}

	/**
	 * 判断是否为字符串或者是字符 i字符串临时标识 f 字符临时标识
	 */
	private void checkStringOrChar(int i, int f) {
		while (j < word.length() && i == 1) {
			analy += Input[j] + "";
			j++;
			if (j < word.length() && Input[j] == '"') {
				analy += Input[j] + "";
				j++;
				i = 2;
				isstring = true;
			}
		}
		while (j < word.length() && f == 1) {
			analy += Input[j] + "";
			j++;
			if (j < word.length() && Input[j] == '\'') {
				analy += Input[j] + "";
				j++;
				f = 2;
				ischar = true;
			}
		}
	}

	public void analy() {

		while (j < word.length()) {

			if (Character.isWhitespace(Input[j])) // 是否是空格
			{
				if (Input[j] == '\n') {
					line++;
				}

				j++;
			}

			else if (Character.isLetter(Input[j])
					|| this.checkException(Input[j])) // 是否是字母或者_,$
			{
				illegal = true;
				while (j < word.length() && Character.isLetter(Input[j])
						|| j < word.length() && Character.isDigit(Input[j])
						|| j < word.length() && this.checkException(Input[j])) // 判断是否是字母或者数字或者是_,$
				{
					analy += Input[j] + "";
					j++;
				}
				while (j < word.length() && !Character.isWhitespace(Input[j])
						&& !Character.isDigit(Input[j])
						&& !Character.isLetter(Input[j]) // 如果后面不是空白、数字、字母、操作符
						&& !this.checkSpecialWord(Input[j])) {
					analy += Input[j] + "";
					j++;
					illegal = false;
				}
				for (int z = 0; z < Defind.length; z++) // 是否是关键字
				{
					if (analy.equals(Defind[z])) {
						System.out.println(analy + "是关键字");
						showString += line + "   " + analy + "\t是关键字" + "\n";
						analy = "";
					}
				}
				if (!analy.equals("")) // 判断是否是合法的标识符
				{
					if (illegal) {
						System.out.println(analy + "是标识符");
						showString += line + "   " + analy + "\t是标识符" + "\n";
					}
					if (!illegal) {
						System.out.println(analy + "是非法符");
						showString += line + "   " + analy + "\t是非法符" + "\n";
					}
					analy = "";
				}
			}

			else if (Character.isDigit(Input[j])) // 判断是否是数字
			{
				illegal = true;
				while (j < word.length() && !Character.isWhitespace(Input[j])
						&& Character.isDigit(Input[j]) || j < word.length()
						&& Input[j] == '.' && !Character.isWhitespace(Input[j])) // 浮点数
				{
					analy += Input[j] + "";
					j++;
				}
				while (j < word.length() && !Character.isWhitespace(Input[j]) // 判断是否是非法的变量
						&& !this.checkSpecialWord(Input[j])) {
					analy += Input[j] + "";
					j++;
					illegal = false;
				}
				if (illegal) {
					System.out.println(analy + "是数值");
					showString += line + "   " + analy + "\t是数值" + "\n";
					analy = "";
				}
				if (!illegal) {
					System.out.println(analy + "是非法符");
					showString += line + "   " + analy + "\t是非法符" + "\n";
					analy = "";
				}

			}

			else if (!Character.isDigit(Input[j])
					&& !Character.isLetter(Input[j])
					&& !Character.isWhitespace(Input[j])) {
				illegal = true;
				int i = 0;
				int f = 0;

				while (j < word.length() && !Character.isWhitespace(Input[j])
						&& !Character.isDigit(Input[j])
						&& !Character.isLetter(Input[j])) {
					analy += Input[j] + "";
					if (Input[j] == '"') {
						i = 1;
					} else if (Input[j] == '\'') {
						f = 1;
					}
					j++;

					this.checkStringOrChar(i, f); // 是否是字符或者是字符串

					if (isstring) {
						System.out.println(analy + "是字符串");
						showString += line + "   " + analy + "\t是字符串" + "\n";
						isstring = false;
						i = 0;
						analy = "";
					}
					if (ischar) {
						System.out.println(analy + "是字符");
						showString += line + "   " + analy + "\t是字符" + "\n";
						ischar = false;
						f = 0;
						analy = "";
					}

				}
				for (int z = 0; z < Operator.length; z++) {
					if (analy.equals(Operator[z])) {
						illegal = true;
						System.out.println(analy + "是操作符");
						showString += line + "   " + analy + "\t是操作符" + "\n";
						analy = "";
					} else if (analy.equals(",")) {
						illegal = true;
						System.out.println(analy + "是操作符");
						showString += line + "   " + analy + "\t是操作符" + "\n";// 操作符
						analy = "";
					}
				}
			}
		}
		Mainframe.setText(showString); // 把结果显示到文本编辑框中
	}

}
