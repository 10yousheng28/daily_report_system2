package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesShowServlet
 */
@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee"); //ログインしている従業員のID取得
        System.out.println(login_employee.getId()); //確認用
        System.out.println(login_employee.getName());

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id"))); //従業員のidを取得
        System.out.println("フォローしようとしてる従業員の名前:" + e.getName());
        System.out.println("フォローしようとしてる従業員のID:" + e.getId());

        long checkFollowersDatabase = (long) em.createNamedQuery("getTheFollowerCount", Long.class) //両Idが一致するデータがいくつあるか確認
                .setParameter("employee_id",login_employee)
                .setParameter("followed_employee_id", e)
                .getSingleResult();
        System.out.println("一致するデータは全" + checkFollowersDatabase + "件");

        em.close();
        request.setAttribute("_token", request.getSession().getId()); //_tokenをリクエストにセット
        request.setAttribute("employee", e);
        request.setAttribute("checkFollowersDatabase", checkFollowersDatabase);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

    }

