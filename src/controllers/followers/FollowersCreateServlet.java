package controllers.followers;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class FollowersCreateServlet
 */
@WebServlet("/followers/create")
public class FollowersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            //Followのインスタンス生成
            Follow f = new Follow();

            f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Employee followed_employee = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
            f.setFollowed_employee(followed_employee);

            Date followed_at = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("followed_at");
            if(rd_str != null && !rd_str.equals("")){
                followed_at = Date.valueOf(request.getParameter("followed_at"));
            }
            f.setFollowed_at(followed_at);

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();

            em.close();

            //「フォロー完了」のフラッシュメッセージ(リダイレクトで表示される)
            request.getSession().setAttribute("flush", followed_employee.getName() +"をフォローしました。");

            //フォロー後、「フォローリスト」のページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/employees/index");
        }
    }
}
