package com.rsi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.rsi.dao.UpdateTaskDao;


/**
 * Servlet implementation class UpdateTask
 */
@WebServlet("/UpdateTask")
public class UpdateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			
		
			JSONObject jsonObject = getJsonObject(request, response);
			int id =Integer.parseInt( jsonObject.getString("id"));
			String taskname = jsonObject.getString("taskname");
			String taskd = jsonObject.getString("taskd");
		
			int uid = Integer.parseInt(jsonObject.getString("uid"));
			System.out.println(id); 
			System.out.println(taskname);
			System.out.println(taskd);

			String result = UpdateTaskDao.updateTaskDao(id, taskname, taskd, uid);
		
			JSONObject json = new JSONObject();
			json.putOnce("result", result);

			response.getWriter().write(json.toString());

		} catch (JSONException | ClassNotFoundException | SQLException e) {
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
			System.out.println("Update user"+jsonObject);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		return jsonObject;
	}

}
