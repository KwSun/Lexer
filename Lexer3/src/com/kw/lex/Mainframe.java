package com.kw.lex;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.kw.lex.Action;
public class Mainframe extends JFrame{
	private static final long serialVersionUID = 1L;
	private static JTextArea text1;
	private static JTextArea text2;
	private JButton jb=new JButton("Analysis"); 
	private JButton jb1=new JButton("open");
	private JLabel  jl1=new JLabel("input：");
	private JLabel  jl2=new JLabel("result：");
	public Mainframe() {
			super("词法分析");  
			this.setSize(600,448);
			this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2,
					(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2);     //设置位置在屏幕中间
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLayout(null);
			JPanel jp =new JPanel();
			JPanel jp1 =new JPanel();
			
			//对jp和jp1进行初始化
			jp.setSize(300, 400);
			jp1.setSize(300, 400);
			jp.setLocation(0, 0);
			jp1.setLocation(300, 0);
			jp.setLayout(null);
			jp1.setLayout(null);
			
			//对文本框text2和text1进行初始化并加入滚动条
			text1=new JTextArea(20,30);
			text2=new JTextArea(20,30);
			text2.setEditable(false);
		    JScrollPane scrol1=new JScrollPane(text1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JScrollPane scrol2=new JScrollPane(text2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    scrol1.setSize(260, 310);
		    scrol1.setLocation(18, 40);
		    scrol2.setSize(260, 310);
		    scrol2.setLocation(18, 40);
	        jp.add(scrol1);  
		    jp1.add(scrol2);
		    
		    //对按钮jb,jb1和对提示框进行设置
		    jb.setBounds(80, 370, 100, 25);
		    jb1.setBounds(80, 370, 100, 25);
		    jl1.setFont(new Font("楷书",Font.BOLD,12));
		    jl1.setSize(140, 25);
		    jl1.setLocation(0, 0);
		    jl2.setFont(new Font("楷书",Font.BOLD,12));
		    jl2.setSize(140, 25);
		    jl2.setLocation(0, 0);
		    
		    //添加panel到JPanel
		    jp.add(jb1);
		    jp.add(jl1);
			jp1.add(jb);
			jp1.add(jl2);
			this.add(jp);
			this.add(jp1);
			
			//对按钮jb,jb1,jb2设置监听
			jb.setActionCommand("start");
			jb1.setActionCommand("open");
			jb.addActionListener(new Action());
			jb1.addActionListener(new Action());
			
			this.setResizable(true);
			this.setVisible(true);
	}

	public static String getString() {
		String temp=text1.getText();
		return temp;
	}

	public static void setText(String text) {
		text2.setText(text);
	}
	
	public static void setTa1Text(String text)
	{
		text1.setText(text);
	}
	
	public static void main(String[] args) {
		
		new Mainframe();
	}
}
