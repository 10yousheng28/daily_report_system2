package controllers.followers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowersShowServlet
 */
@WebServlet("/followers/show")
public class FollowersShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee followed_employee = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
System.out.println(followed_employee.getName());
        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        System.out.println(page);
        List<Report> f_reports = em.createNamedQuery("getFollowersReports", Report.class)
                .setParameter("followed_employee", followed_employee)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();
        em.close();
System.out.println(f_reports.size() + "件");
        request.setAttribute("f_reports", f_reports);
        //request.setAttribute("reports_count", reports_count);  (あとで加える)
        request.setAttribute("page", page);
        System.out.println("error2");
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        System.out.println("error3");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/followers/show.jsp");
        rd.forward(request, response);
    }

}
