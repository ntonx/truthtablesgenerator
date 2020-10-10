package com.example.logicproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;
@Service
/**
 * Class that generates truth tables
 **/
public class GeneratorService {

	
	public GeneratorService() {}

	public int[][] getValuesFromExpression(int nbrVariables, String expresion,List<String>datos) {
	
		List<String> mycadena = getPostFixString(expresion);
		System.out.println("MI EXPRESION POSFIJA:"+mycadena);
	    
		int nbrCombinaisons = (int) Math.pow(2, nbrVariables);
	    Boolean[] boolArray = new Boolean[nbrVariables+1];
	    int[] intArray = new int[nbrVariables+1];
	    
	    int complete [][] = new int [nbrCombinaisons][nbrVariables+1]; 
	    boolean tt [][] = new boolean [nbrCombinaisons][nbrVariables+1];  
//	    ArrayList<int[]> mysdatos=new ArrayList<int[]>();
	    
	    for (int j = 0; j < nbrCombinaisons; j++) {             
	        String    tempStr  = String.format("%"+nbrVariables+"s", Integer.toBinaryString(j)).replace(" ", "0");
	        boolean[] tempBool = new boolean[tempStr.length()+1];
	        boolean total = tempStr.charAt(0)=='1';
	        for(int i=0; i<tempStr.length(); i++){
	            tempBool[i]= tempStr.charAt(i)=='1';
	            if(i>0){	
	            	total= false;
	        //        total = total && tempBool[i];  //table for logical AND change operator to || for OR or ^ for XOR
	            }
	        }
	        tempBool[tempStr.length()] = total;
	        tt[j] = tempBool;
	    }

	    int posicionuno=-1;
	    int posicioncero=-1;
	    
	    for (int j = 0; j < nbrCombinaisons; j++) { 
	    	for (int i = 0; i <boolArray.length ; i++) {
   		// 			boolArray[i]= tt[j][i];
   		 		if(tt[j][i]==true) {
   		 			intArray[i]= 1;
   		 		}else {
   		 			intArray[i]= 0;
   		 			
   		 		}
   		 		
	   		 	if(datos.contains("1")) {
	   		 		posicionuno = datos.indexOf("1");
		 			if(i==posicionuno) {
		 				intArray[i]=1;
		 			}
			    }
	   		 	if(datos.contains("0")) {
	   		 		posicioncero = datos.indexOf("0");
			    	if(i==posicioncero) {
			    		intArray[i]=0;
			    	}
			    }	
	    	}
	    	
	    	int[]copia = intArray;
	    	List<String> mycadenacopia=new ArrayList<>(mycadena);
	    	int valor = calculatePerRow(copia,mycadenacopia,datos);
	    	//save in the final colum of each row the result of processing the row with posfix  
	    	intArray [nbrVariables] = valor;
//	    	mysdatos.add(j,(intArray));
			
	    	for (int i = 0; i < intArray.length; i++) {
	    		//build the array that contains all the processing data
				complete[j][i] = intArray[i];
			}
	    	
   	 		}
	    return complete;     
	}
	
	
	
	private int calculatePerRow(int[] intArray, List<String> mycadena, List<String> datos) {

		System.out.print(mycadena);
    	for (int i = 0; i <intArray.length ; i++) {
    		System.out.print(intArray[i]);
    		}
		final List<String> temporal = mycadena;
  	    int myresultado=0;
  	    for (int k=0;k<datos.size();k++) {
  	    	Collections.replaceAll(temporal,datos.get(k) ,String.valueOf(intArray[k]));
  	    	
  	    } System.out.print("#temp...." + temporal);
 
  	  myresultado = calculatePostFix(temporal);
  	System.out.println("#res:" + myresultado);
  	return myresultado;
 	}



	private int calculatePostFix(List<String> postFixList){
	        Stack<Integer> stack = new Stack<>();
	        for(int i=0;i<postFixList.size();i++){
	            String word = postFixList.get(i);
	            if(word.length()==1 && (word.charAt(0)=='+'||word.charAt(0)=='*')){
	                int number2 = stack.pop();
	                int number1 = stack.pop();
	                if(word.charAt(0)=='+'){
	                	if(number1==1||number2==1) {
	                		int number = 1;
	                    	stack.push(number);
	                    	}else {stack.push(0);}
	                }else if(word.charAt(0)=='*'){
	                	if(number1==1&&number2==1) {
	                		int number = 1;
	                    	stack.push(number);
	                    	}else {stack.push(0);}
	                 }
	            }else if(word.charAt(0)=='~') {
	            	int number = stack.pop();
                	if(number==0) {
                		number=1;
                	}else {
                		number=0;
                		}
                    stack.push(number);
	            }else if(word.charAt(0)=='>') {
	            	int number2 = stack.pop();
	            	int number1 = stack.pop();
	            //	int number1 = stack.pop();
	            //	int number2 = stack.pop();
	            	int number=0;
                	if(number1==1&&number2==0) {
                		number=0;
                	}else{// if(number2==0&&number1==0||number1==0&&number2==1||number1==1&&number2==1) {
                		number=1;
                		}
                    stack.push(number);
	            }else{
	                int number = Integer.parseInt(word);
	                stack.push(number);
	            }
	        }
	        return stack.peek();
	    }
	
	 private int getPreference(char c){
	        if(c=='+') return 1;
	        else if(c=='*') return 2;
	        else if(c=='~') return 3;
	        else if(c=='>') return 4;
	        else if(c=='<') return 5;
	        else return -1;
	    }
	 
	 
	 
	 ///ITS WORKING!!!!!!!!!!!
	 private List<String> getPostFixString(String s){
	        Stack<Character> stack = new Stack<>();
	        List<String> postFixList = new ArrayList<>();
	        boolean flag = false;
	        for(int i=0;i<s.length();i++){
	            char word = s.charAt(i);
	            if(word==' '){
	                continue;
	            }
	            if(word=='('){
	                stack.push(word);
	                flag = false;
	            }else if(word==')'){
	                flag = false;
	                while(!stack.isEmpty()){
	                    if(stack.peek()=='('){
	                        stack.pop();
	                        break;
	                    }else{
	                        postFixList.add(stack.pop()+"");
	                    }
	                }
	            }else if(word=='>'){
	                flag = false;
	                /*while(!stack.isEmpty()){
	                    if(stack.peek()=='('){
	                        stack.pop();
	                        break;
	                    }else{
	                        postFixList.add(stack.pop()+"");
	                    }
	                }*/
	                stack.push(word);
	                flag = false;
	            }else if(word=='+' || word=='*' || word=='~'){
	                flag = false;
	                if(stack.isEmpty()){
	                    stack.push(word);
	                }
	                else{
	                    while(!stack.isEmpty() && getPreference(stack.peek())>=getPreference(word)){
	                        postFixList.add(stack.pop()+"");
	                    }
	                    stack.push(word);
	                }
	            }else{
	                if(flag){
	                    String lastNumber = postFixList.get(postFixList.size()-1);
	                    lastNumber+=word;
	                    postFixList.set(postFixList.size()-1, lastNumber);
	                }else
	                postFixList.add(word+"");
	                flag = true;
	            }
	        }
	        while(!stack.isEmpty()){
	            postFixList.add(stack.pop()+"");
	        }
	        return postFixList;
	    }
	   
}
