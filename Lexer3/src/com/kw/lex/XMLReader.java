package com.kw.lex;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	
	private Element init(String Path)
	{
		Element elmtInfo = null;
		try
		{
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = factory.newDocumentBuilder();
         Document doc = db.parse(new File(Path));      
         elmtInfo = doc.getDocumentElement();    
       
		}catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
		catch (SAXException e)
        {
            e.printStackTrace();
        }
		catch (IOException e)
        {
            e.printStackTrace();
        }
		
		return elmtInfo;
	}
	
	private String praseNode(Node result,String NodeName)
	{
		String text="";

	        NodeList ns = result.getChildNodes();

	        for (int j = 0; j < ns.getLength(); j++)
	        {
	            Node record = ns.item(j);

	            if (record.getNodeType() == Node.ELEMENT_NODE && record.getNodeName().equals("value"))
	            {
	                text=record.getTextContent();
	            }
	    }
		return text;
	}
	
	public String[] startprase(String Path,String Nodename)
	{
		NodeList nodes = this.init(Path).getChildNodes();
		String[] Content=null;
		for (int i = 0; i < nodes.getLength(); i++)
		{
		    Node result = nodes.item(i);     //获取结点
		    if (result.getNodeType() == Node.ELEMENT_NODE && result.getNodeName().equals(Nodename))
		    {
		    	Content=this.praseNode(result, Nodename).split(",");
		    }
		}
		return Content;
	}

	public static void main(String[] args) {
		
		new XMLReader();
    }
	
}
