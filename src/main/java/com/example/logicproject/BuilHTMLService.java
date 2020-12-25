package com.example.logicproject;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class BuilHTMLService {

	public BuilHTMLService() {}
	
	public String getHead() {
		return "<!DOCTYPE HTML>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"    <link rel=\"stylesheet\" href=\"css/mystyle.css\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"css/main2.css\">\r\n" + 
				"	<title>Computational Mathematics</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"<h1>Truth tables generator</h1>"+
				"    <div class=\"container container-padding50\">\r\n" + 
				"        <table>\r\n" + 
				"            <thead>\r\n" + 
				"              <tr>";
	}
	
	
	public String getMiddle() {
		return "</tr>\r\n" + 
				"      </thead>\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>";
	}
	
	public String getEnd(ArrayList<Integer> response) {
		System.out.print(response);
		String tautology = "";
		String contingency = "";
		String negation = "";
		String satisfiable = "";
		if(response.contains(1)&&response.contains(0)) {
			tautology = "No";
			negation = "No";
			contingency = "Yes";
			satisfiable = "Yes";
		}else if (response.contains(1)&&!response.contains(0)) {
			tautology = "Yes";
			negation = "No";
			contingency = "Yes";
			satisfiable = "Yes";
		}else if (response.contains(0)&&!response.contains(1)) {
			tautology = "No";
			negation = "Yes";
			contingency = "No";
			satisfiable = "No";
		}else if (response.contains(0)&&response.contains(1)) {
			tautology = "No";
			negation = "No";
			contingency = "Yes";
			satisfiable = "Yes";
		}
		return "\r\n" + 
				"              \r\n" + 
				"            </tbody>\r\n" + 
				"          </table>\r\n" + 
				"    </div>\r\n" +
				"</div>"+
				"<div align=\"left\">"
				+ "<p>Is it a satisfiable?  "+satisfiable+
				"</p><p>Is  it a tautology?  "+tautology+
				" </p><p>Is it a contradiction?  "+negation+
				"</p><p>Is it a contingency?  "+contingency+"</p></div>"+
				"</body>\r\n" + 
				"\r\n" + 
				"</html>\r\n" + 
				"";		
	}
}
