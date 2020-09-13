package com.rsi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.rsi.dao.DAOLogin;
import com.rsi.dao.Sendemail;

/**
 * Servlet implementation class EMAIL
 */
@WebServlet("/EMAIL")
public class EMAIL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EMAIL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			
		
			JSONObject jsonObject = getJsonObject(req, response);

			int m = (int) (Math.random()*10000);
			String result = ""+m;
			
			String email = jsonObject.getString("email");
			String SenderEmail = "";
			String password ="";
			Sendemail.sendemail(email," your otp is : "+result,SenderEmail,password);
			
		
			System.out.println(email);
		
			

			
			
		
			JSONObject json = new JSONObject();
			json.putOnce("result", result);

			//response.setHeader("Access-Control-Allow-Origin", "*");
			//response.addHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(json.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public static JSONObject getJsonObject(HttpServletRequest request, HttpServletResponse response) {

		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;

		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
			jsonObject = new JSONObject(sb.toString());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		return jsonObject;
	}

}
