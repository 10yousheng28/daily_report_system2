<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">


        <h2>フォロー リスト</h2>
        <table id="follow_list">
            <tbody>
                <tr>
                    <th class="follow_name">氏名</th>
                    <th class="follow_date">フォローした日</th>
                    <th class="follow_action1">操作1</th>
                    <th class="follow_action2">操作2</th>
                </tr>
                <c:forEach var="follower" items="${followers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_name"><c:out value="${follow.employee.name}" /></td>
                        <td class="follow_date"><fmt:formatDate value='${follow.followed_at}' pattern='yyyy-MM-dd' /></td>
                        <td class="follow_action1"><a href="<c:url value='/followers/show?id=${follow.employee.followed_employee}' />">日報を見る</a></td>
                        <td class="follow_action2"><a href="#" onclick="confirmDestroy();">フォロー解除する</a>
                            <form method="POST" action="<c:url value='/followers/destroy' />">
                               <input type="hidden" name="_token" value="${_token}" />
                            </form>
                            <script>
                            function confirmDestroy() {
                                if(confirm("フォロー解除しますか？")) {
                                    document.forms[1].submit();
                        }
                    }
                            </script>
                            </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:param>
        </c:import>