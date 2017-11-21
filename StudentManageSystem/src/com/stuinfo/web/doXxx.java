package com.stuinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stuinfo.dao.UserDao;
import com.stuinfo.model.User;
import com.stuinfo.util.DataBaseUtil;

public class doXxx extends HttpServlet {
	DataBaseUtil dbUtil=new DataBaseUtil();
	UserDao userDao=new UserDao();

	/**
	 * Constructor of the object.
	 */
	public doXxx() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("pwd");
		//����ǰ̨��¼������û���������
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		if(userName.equals("")){
			request.setAttribute("error", "�û���ʶΪ�գ����������룡��");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		if(password.equals("")){
			request.setAttribute("error", "����Ϊ�գ����������룡");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		User user=new User(userName,password);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "�û������������");
				// ��������ת
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				//��ȡsession
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
				// �ͻ�����ת
				response.sendRedirect("checkLogin.jsp");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
