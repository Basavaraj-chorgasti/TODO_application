package com.rsi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.rsi.bean.user;
import com.rsi.dao.GetAllUserDao;

/**
 * Servlet implementation class GetAllUser
 */
@WebServlet("/GetAllUser")
public class GetAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUser() {
    	System.out.println("Inside GetAllUser Constructor...");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			
		
			List<user> result = GetAllUserDao.GetAllUserDao();
		
					
			 Map<String, Object> mapObject = new HashMap<String, Object>();
			 mapObject.put("result", result);
			
		    //response.addHeader("Access-Control-Allow-Origin", "*");
			 includeJSONResponse(mapObject, request, response);

			

		} catch ( ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
	}
	public String getJSONFromObject(Object object) {
		String jsonData = null;
		
		 ObjectMapper writeMapper = new ObjectMapper();
		 
		    
		try {
			
		    jsonData = writeMapper.writeValueAsString(object);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in  getJSONFromObject ");
		}
		return jsonData;
	}

	
	public String getValidJsonFromObject(Map<String, Object> allObjectMap) {
		if (null == allObjectMap || allObjectMap.isEmpty()) {
		    
			allObjectMap = new HashMap<String, Object>();
			
		}
		return getJSONFromObject(allObjectMap);
	}
	
	public void includeJSONResponse(Map<String, Object> jsonMap, HttpServletRequest request, HttpServletResponse response) {
		String jsonString = getValidJsonFromObject(jsonMap);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			System.out.println("before write :: " + response.isCommitted());
			response.getWriter().write(jsonString);
			
			
			System.out.println("jsonString :: " + jsonString);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in  includeJSONResponse");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
