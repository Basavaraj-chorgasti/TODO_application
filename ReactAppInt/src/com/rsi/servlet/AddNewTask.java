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

import com.rsi.dao.DAOAddTask;
import com.rsi.dao.DAOLogin;

/**
 * Servlet implementation class AddNewTask
 */
@WebServlet("/AddNewTask")
public class AddNewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewTask() {
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

			String taskname = jsonObject.getString("taskname");
			String taskd = jsonObject.getString("taskd");
			
			int uid = Integer.parseInt(jsonObject.getString("uid"));
			
			
			   
			System.out.println(taskd);
			System.out.println(taskname);

			String result = DAOAddTask.addtask(taskname,taskd,uid);
		
			JSONObject json = new JSONObject();
			json.putOnce("result", result);

			//response.setHeader("Access-Control-Allow-Origin", "*");
			//response.addHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(json.toString());

		} catch (JSONException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

