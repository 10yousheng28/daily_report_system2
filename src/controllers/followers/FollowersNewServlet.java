package controllers.followers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowersNewServlet
 */
@WebServlet("/followers/new")
public class FollowersNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("follow",new Follow());

        //クエリパラメータから日報作成者のidを取得
        Follow fId = em.find(Follow.class, Integer.parseInt(request.getParameter("Id")));
        //取得した日報作成者のidをリクエストスコープにセット
        request.setAttribute("fId", fId);


        //新たに入力するためのフォームなどは必要ないので、FollowersCreateServletへリダイレクト
        response.sendRedirect("/followers/create");
    }

}
