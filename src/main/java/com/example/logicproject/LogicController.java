package com.example.logicproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LogicController {
	
	 @Autowired
	 TokenizerService tokenService;
	 GeneratorService truthTablesGenerator;
	 BuilHTMLService htmlBuilder;
	
	@PostMapping(value="/",produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String statementSubmit(@ModelAttribute Statement mychar, Model model) {
		model.addAttribute("mystatement", mychar);
		
		String expresion = mychar.content;
    //	String expresion="a+b*c*e+f*g";
	//	String	expresion="(p+q)*~(p*q)";
		
		tokenService = new TokenizerService();
		List<String>datos = tokenService.getOperands(expresion);
		
		truthTablesGenerator= new GeneratorService();
		
		int[][] res = truthTablesGenerator.getValuesFromExpression(datos.size(),expresion,datos);
		
		htmlBuilder=new BuilHTMLService();
		
		String page = htmlBuilder.getHead();

		String m = "<th scope=\"col\">";
		String m2= "</th>";
		for(int i = 0;i<datos.size();i++) {
			String aux = "";
			aux = m+datos.get(i)+m2;
			page=page+aux;
		}
		
		page=page+m+expresion+m2+htmlBuilder.getMiddle();
		 
		String m4 = "<td>";
		String m5 = "</td>";
		String aux2 = "";
		String m6="</tr>";
		for (int[] row : res) {            
	        for (int c : row) { 
	        	aux2=aux2+m4+c+m5;
	        	System.out.print(c + "\t");        
	        }
	        aux2 = aux2+m6;
	        System.out.println();
	    }      
		page = page+aux2;
		
	  return page+htmlBuilder.getEnd();
	}
	
	
	    
	@GetMapping("/")
	public String statementForm(Model model) {
		model.addAttribute("mystatement", new Statement());
		return "index";
	}
		
}
