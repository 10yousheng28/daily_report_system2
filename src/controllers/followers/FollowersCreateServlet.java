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

            Follow f = new Follow();

            //フォローする人（つまりログインしている人）のidデータ保存
            f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            //フォローされる人のidデータ保存(FollowersNewServletでリクエストスコープにセットしたidを取得)
            f.setFollowed_employee((Employee)request.getAttribute("fId"));

            //「フォローした日」のデータ保存
            Date followed_at = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("followed_at");
            if(rd_str != null && !rd_str.equals("")){
                followed_at = Date.valueOf(request.getParameter("followed_at"));
            }
            f.setFollowed_at(followed_at);

            //フォローされている人の日報更新時刻(Reportのupdated_atと一致させたい)(保留)
            //f.setReport_updated_at((Report)request.getSession().getAttribute;

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            //「フォロー完了」のフラッシュメッセージ(リダイレクト先の日報一覧で表示される)
            request.getSession().setAttribute("flush", "フォローしました。");
            em.close();

            //フォロー後、「日報　一覧」のページへリダイレクト
            response.sendRedirect("/reports/index");
    }
    }
}
