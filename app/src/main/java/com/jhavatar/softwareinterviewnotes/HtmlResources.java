package com.jhavatar.softwareinterviewnotes;

import java.util.Locale;

import android.app.Activity;

import com.jhavatar.softwareinterviewnotes.R; 

public class HtmlResources {
	static final int[] TITLE_IDS = {R.string.title_toc,
		R.string.title_cc_theory,
		R.string.title_complexity_measures,
		R.string.title_complexity_classes,
		R.string.title_data_structures,
		R.string.title_sort_algorithms, 
		R.string.title_search_algorithms,
		R.string.title_programming_paradigms,
		R.string.title_oo_programming, 
		R.string.title_design_principles, 
		R.string.title_concurrency,
		R.string.title_design_patterns,
		R.string.title_creational_design_patterns,
		R.string.title_structural_design_patterns,
		R.string.title_behavioral_design_patterns,
		R.string.title_concurrency_design_patterns,
		R.string.title_tcpip,
		R.string.title_web_services,
		R.string.title_nosql,
		R.string.title_android};
	
	static final String[] HTML_FILE_NAMES = {"", 
					"ComputationalComplexityTheory.html",
					"ComplexityMeasures.html",
					"ComplexityClasses.html",
					"DataStructures.html",
					"SortingAlgorithms.html",  
					"SearchAlgorithms.html",  
					"ProgrammingParadigms.html",
					"ObjectOientedProgramming.html", 
					"DesignPrinciples.html", 
					"Concurrency.html",
					"DesignPatterns.html",
					"CreationalDesignPatterns.html",
					"StructuralDesignPatterns.html",
					"BehavioralDesignPatterns.html",
					"ConcurrencyDesignPatterns.html",
					"tcpip.html",
					"WebServices.html",
					"NoSQL.html",
					"Android.html"};
	
	static final String[] NUMBERING = {
	"",
	"1",
	"1.1",
	"1.2",
	"2",
	"3",
	"4",
	"5",
	"6",
	"6.1",
	"7",
	"8",
	"8.1",
	"8.2",
	"8.3",
	"8.4",
	"9",
	"10",
	"11",
	"12"
	};
	
	private static String htmlParams;
	
	public static String genHtmlParams()
	{
		if (htmlParams == null)
		{
		
			StringBuffer s = new StringBuffer();
			for (int i=1; i<HTML_FILE_NAMES.length; i++)
			{
				//s.append("&amp;");
				s.append("&");
				s.append(HTML_FILE_NAMES[i] + "=" + i);
				s.append("&");
				//s.append("&amp;");
				s.append(i + "=" + NUMBERING[i]);
			}
			htmlParams = s.toString();
		}
		
		//Log.d("jhavatar", "numberingParams = " + htmlParams);
		return htmlParams;
	}
	
	
	
	public static CharSequence getPageTitle(int position, Activity activity) {
		Locale l = Locale.getDefault();
		return NUMBERING[position] + " " + activity.getString(TITLE_IDS[position]).toUpperCase(l).trim();
	}
	
	public static CharSequence getIndentedPageTitle(int position, Activity activity) {
		String number = NUMBERING[position];
		String indentation = (number.indexOf(".") >= 0) ? "\u00A0\u00A0" : ""; 
		return indentation + number + " " + activity.getString(TITLE_IDS[position]);
	}
	
	

}
