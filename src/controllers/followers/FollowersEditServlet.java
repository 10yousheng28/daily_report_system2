package controllers.followers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class FollowersEditServlet
 */
@WebServlet("/followers/edit")
public class FollowersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, (Integer)request.getSession().getAttribute("followed_employee")); //セッションスコープから取得
System.out.println(e.getId()); //確認用
System.out.println(e.getName());
        em.close();
        System.out.println("editerror1");
        request.setAttribute("followed_employee", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("followed_employee_id", e.getId());
        System.out.println("editerror2");

        //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/followers/edit.jsp"); //テスト用
        //rd.forward(request, response);

        response.sendRedirect(request.getContextPath() +"/followers/destroy");
        }

}
