package controllers.followers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowersDestroyServlet
 */
@WebServlet("/followers/destroy")
public class FollowersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getAttribute("followed_employee")));
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee"); //ログインしている従業員のID取得
        System.out.println(login_employee.getId()); //確認用
        System.out.println(login_employee.getName());

        Employee followed_employee = em.find(Employee.class, (Integer)request.getSession().getAttribute("followed_employee")); //フォロー解除される従業員のID取得
        System.out.println(followed_employee.getId()); //確認用
        System.out.println(followed_employee.getName());

        //Follow followed_employee = em.find(Follow.class, request.getSession().getAttribute("followed_employee"));

        Follow f = (Follow) em.createNamedQuery("getMyFollowerUnfollowed", Follow.class)
                .setParameter("employee_id",login_employee)
                .setParameter("followed_employee_id", followed_employee)
                .getSingleResult();
        System.out.println("両者IDの一致を確認"); //確認用

        em.getTransaction().begin();
        em.remove(f); //データ削除
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", followed_employee.getName() + "をフォロー解除しました");
        em.close();
        System.out.println("emを終了しました");

        //セッションスコープ上の不要になったfollowed_employeeのデータを削除
        request.getSession().removeAttribute("followed_employee");
        System.out.println("セッションスコープ上のデータを削除しました");

        //followers/indexのページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/followers/index");

    }

}
