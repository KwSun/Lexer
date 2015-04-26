/**
 * 对按钮的事件响应
 */
package com.kw.lex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.kw.lex.Mainframe;
import com.kw.lex.WordAnaly;
import com.kw.lex.ReadFile;

public class Action implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("start"))
		{
			new WordAnaly(Mainframe.getString(),"src/document.xml").analy();  //开始词法分析
		}
		else if(e.getActionCommand().equals("open"))
		{
			JFileChooser showFile  =  new JFileChooser();		//打开文件选择框
			showFile.showOpenDialog(null);
			
			if(showFile.getSelectedFile()!=null)
			{
				String textword=new ReadFile().startRead(showFile.getSelectedFile()); //把文件内容写到文本编辑框text1
				Mainframe.setTa1Text(textword);
			}
		}
	}

}
