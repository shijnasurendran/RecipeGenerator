package com.recipe.generator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.sun.tools.javac.util.ArrayUtils;


//import com.sun.javafx.collections.MappingChange.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s = new String();
		List l = new ArrayList<Character>(4);

		String sampleString = "2-4A0r7-4k";
		int k = 4;
		//System.out.println(solution(sampleString, k));
		String[] s = {"a.b@example.com", "x@example.com", "x@exa.mple.com", "ab+1@example.com", "y@example.com", "y@example.com", "y@example.com"};
		System.out.println(solution(s));
	}
	
	static int solution(String[] L) {
		//List emailGroup = new ArrayList<String[]>();
		//Hashtable<String, ArrayList<String>> eg = new Hashtable<String, ArrayList<String>>();
		int result = 0;
		Map<String, ArrayList<String>> emailGroups = new HashMap<String, ArrayList<String>>();
		for (String single : L) {
			String[] email = single.split("@");
			String localName = email[0];
			String domainName = email[1];
			StringBuilder sb = new StringBuilder(changes(localName));
			
			sb.append("@"+domainName);
			//System.out.println(sb.toString());
			//if(emailGroups.get(domainName) != null)
			ArrayList<String> l;
			//ArrayUtils
			if(emailGroups.containsKey(domainName)) {
				l = emailGroups.get(domainName);
				l.add(sb.toString());
			}else {
				l = new ArrayList<String>();
				l.add(sb.toString());
				emailGroups.put(domainName, l);
			}
		}
		//System.out.println(emailGroups);
		for (String key : emailGroups.keySet()){
	        //iterate over keys
			if(emailGroups.get(key).size() > 1)
				result++;
	        System.out.println(key+" "+emailGroups.get(key));
	    }
		return result;
	}
	
	static String changes(String s) {
		s = s.replace(".", "");
		StringBuilder sb = new StringBuilder(s);
		if(s.contains("+")) {
			//System.out.println(s.subSequence(s.indexOf("+"), s.length()));
			sb.replace(sb.indexOf("+"), sb.length(), "");
			//s = s.replace(s.subSequence(s.indexOf("+"), s.length()-1), "");
		}
		return sb.toString();
	}
	
	/*public static String solution(String s, int k) {
		StringBuilder result = new StringBuilder();
		StringBuilder sb = new StringBuilder(s);
		//sb.sub
		int count = k, start = sb.length()-1, end = sb.length() - k -1;
		try {
		for(int i = sb.length(); i > 0; i--) {
			result.insert(0, sb.substring(start, end));
			if(start > 0 && end > 0 ) {
				start -= k;
				end -= k;
			}
			result.insert(0, '-');
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(result);
		return result.toString();
	}*/
	
	/* public static String solution(String S, int K) {
	        // write your code in Java SE 8
	        S.toLowerCase();
	        //System.out.println(S.replace("-",""));
	        StringBuilder sb = new StringBuilder(S.replace("-", "").toUpperCase());
	        System.out.println(sb.toString());
	        int length = sb.length() + sb.length()/K;
	        
	        List<String> l = new ArrayList<String>(length);
	        System.out.println(l.size());
	        String sol = new String();
	        int i=sb.length();
	        do{
	        	System.out.println(sb.substring(i-K,i));
	        	System.out.println(i + "---" + K);
	            //l.add(i-K-1,sb.substring(i-K,i));
	            l.add(sb.substring(i-K,i));
	            //l.add(i-K-1,"-");
	            //System.out.println(l.toString());
	            i = i-K;
	            
	        }while(i>0);
	        //System.out.print(l.toString());
	        String res = "";
	        for(int j=l.size()-1; j>=0;j--) {
	        	res +=  l.get(j);
	        	if(j!=0)
	        		res += "-";
	        }
	        return res;
	    }*/

}
