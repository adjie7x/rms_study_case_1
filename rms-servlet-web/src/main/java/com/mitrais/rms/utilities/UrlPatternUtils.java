package com.mitrais.rms.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

public class UrlPatternUtils {

	private static boolean hasUrlPattern(ServletContext servletContext,
			String urlPattern) {

		Map<String, ? extends ServletRegistration> map = servletContext
				.getServletRegistrations();

		for (String servletName : map.keySet()) {
			ServletRegistration sr = map.get(servletName);

			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}

		}
		return false;
	}

	// servletPath:
	// ==> /spath
	// ==> /spath/*
	// ==> *.ext
	// ==> /
	public static String getUrlPattern(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();

		String urlPattern = null;
		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
			return urlPattern;
		}
		urlPattern = servletPath;

		boolean has = hasUrlPattern(servletContext, urlPattern);
		if (has) {
			return urlPattern;
		}
		int i = servletPath.lastIndexOf('.');
		if (i != -1) {
			String ext = servletPath.substring(i + 1);
			urlPattern = "*." + ext;
			has = hasUrlPattern(servletContext, urlPattern);

			if (has) {
				return urlPattern;
			}
		}
		return "/";
	}
	
//	public static void main(String[] args) {
//		Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
//		// Configure For "EMPLOYEE" Role.
//        List<String> urlPatterns1 = new ArrayList<String>();
// 
//        urlPatterns1.add("/userInfo");
//        urlPatterns1.add("/employeeTask");
// 
//        mapConfig.put("EMPLOYEE", urlPatterns1);
// 
//        // Configure For "MANAGER" Role.
//        List<String> urlPatterns2 = new ArrayList<String>();
// 
//        urlPatterns2.add("/userInfo");
//        urlPatterns2.add("/managerTask");
// 
//        mapConfig.put("MANAGER", urlPatterns2);
//        
//        
//        Set<String> testSet = mapConfig.keySet();
//        
//        for (String string : testSet) {
//			System.out.println(string);
//		}
//	}

}
