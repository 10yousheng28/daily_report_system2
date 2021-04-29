<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
    <c:if test="${flush != null}">
    <div id="flush_success">
        <c:out value="${flush}"></c:out>
    </div>
    </c:if>
        <h3>【${followed_employee.name}の日報  一覧】</h3>
        <table id="f_report_list">
            <tbody>
                <tr>
                    <th class="f_report_name">氏名</th>
                    <th class="f_report_date">日付</th>
                    <th class="f_report_title">タイトル</th>
                    <th class="f_report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${f_reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="f_report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="f_report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="f_report_title">${report.title}</td>
                        <td class="f_report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                    </c:forEach>
            </tbody>
        </table>

             <div id="pagination">
            (全 ${f_reports_count} 件) <br />
            <c:forEach var="i" begin="1" end="${((f_reports_count - 1) / 15) + 1}" step="1">
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
        <br />
        <c:if test="${sessionScope.login_employee.id != employee.id}">
        <p><a href="#" onclick="confirmDestroy();">フォロー解除する</a></p>
                            <form method="POST" name="fd" action="<c:url value='/followers/destroy?id=${followed_employee.id}' />">
                               <input type="hidden" name="_token" value="${_token}" />

                            </form>
                            <script>
                            function confirmDestroy() {
                                if(confirm("フォロー解除します。よろしいでしょうか？")) {
                                    document.forms["fd"].submit();
                        }
                    }
                            </script>
                </c:if>

    </c:param>
</c:import>