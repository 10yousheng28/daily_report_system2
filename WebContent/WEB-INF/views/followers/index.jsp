<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>フォロー リスト</h2>
        <table id="follow_list">
            <tbody>
                <tr>
                    <th class="follow_name">氏名</th>
                    <th class="follow_date">フォローした日</th>
                    <th class="follow_action">操作</th>

                </tr>
                <c:forEach var="follower" items="${followers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_name"><c:out value="${follower.followed_employee.name}" /></td>
                        <td class="follow_date"><fmt:formatDate value='${follower.followed_at}' pattern='yyyy-MM-dd' /></td>
                        <td class="follow_action"><a href="<c:url value='/followers/show?id=${follower.followed_employee.id}' />">日報を見る</a></td>


                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （ 現在 ${followers_count} 人フォローしています）<br />
            <c:forEach var="i" begin="1" end="${((followers_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        </c:param>
        </c:import>
