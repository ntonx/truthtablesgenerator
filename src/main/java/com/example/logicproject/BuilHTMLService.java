package com.example.logicproject;

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
				"	<title>Logic project</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <div>\r\n" + 
				"        <table>\r\n" + 
				"            <caption>Truth tables builder</caption>\r\n" + 
				"            <thead>\r\n" + 
				"              <tr>";
	}
	
	
	public String getMiddle() {
		return "</tr>\r\n" + 
				"      </thead>\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>";
	}
	
	public String getEnd() {
		return "\r\n" + 
				"              \r\n" + 
				"            </tbody>\r\n" + 
				"          </table>\r\n" + 
				"    </div>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>\r\n" + 
				"";		
	}
}
