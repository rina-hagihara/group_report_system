<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actMis" value="${ForwardConst.ACT_MIS.getValue()}" />
<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commAsgn" value="${ForwardConst.CMD_ASSIGN.getValue()}" />
<c:set var="commRgst" value="${ForwardConst.CMD_REGISTER.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${mission.id} の案件情報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>案件</th>
                    <td><c:out value="${mission.title}" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><c:out value="${mission.content}" /></td>
                </tr>
                <tr>
                    <th>訪問予定日時</th>
                    <fmt:parseDate value="${mission.visitStart}" pattern="yyyy-MM-dd'T'HH:mm" var="visitStart" type="date" />
                    <td><fmt:formatDate value="${visitStart}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>訪問完了日時</th>
                    <fmt:parseDate value="${mission.visitFinish}" pattern="yyyy-MM-dd'T'HH:mm" var="visitFinish" type="date" />
                    <td><fmt:formatDate value="${visitFinish}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>トラックサイズ</th>
                    <td><c:out value="${mission.trackSize}" /></td>
                </tr>
                                <tr>
                    <th>引っ越し予定日時</th>
                    <fmt:parseDate value="${mission.moveStart}" pattern="yyyy-MM-dd'T'HH:mm" var="moveStart" type="date" />
                    <td><fmt:formatDate value="${moveStart}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>引っ越し完了日時</th>
                    <fmt:parseDate value="${mission.moveFinish}" pattern="yyyy-MM-dd'T'HH:mm" var="moveFinish" type="date" />
                    <td><fmt:formatDate value="${moveFinish}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>支払い日時</th>
                    <fmt:parseDate value="${mission.payDue}" pattern="yyyy-MM-dd'T'HH:mm" var="payDue" type="date" />
                    <td><fmt:formatDate value="${payDue}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${mission.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${mission.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actMis}&command=${commEdit}&id=${mission.id}' />">この案件情報を編集する</a>
        </p>

        <h3>id : ${mission.id} 案件の担当従業員</h3>

        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>従業員名</th>
                    <th>操作</th>
                </tr>
                    <c:forEach var="mission_employee" items="${mission_employees}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${mission_employee.employee.code}" /></td>
                        <td><c:out value="${mission_employee.employee.name}" /></td>
                        <td>
                            <a href="<c:url value='?action=${actEmp}&command=${commShow}&id=${mission_employee.employee.id}' />">詳細を見る</a>
                        </td>
                    </tr>
                    </c:forEach>
            </tbody>
        </table>

         <p>
            <a href="<c:url value='?action=${actMis}&command=${commAsgn}&id=${mission.id}' />">この案件に従業員をアサイン</a>
        </p>

        <h3>id : ${mission.id} 案件の顧客情報</h3>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>顧客番号</th>
                    <th>氏名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="mission_customer" items="${mission_customers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${mission_customer.customer.code}" /></td>
                        <td><c:out value="${mission_customer.customer.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='?action=${actCus}&command=${commShow}&id=${mission_customer.customer.id}' />">詳細を見る</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>
            <a href="<c:url value='?action=${actMis}&command=${commRgst}&id=${mission.id}' />">この案件に顧客を登録</a>
        </p>




        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}&id=${mission.id}' />">この案件の日報一覧</a>
        </p>
        <p>
            <a href="<c:url value='?action=${actMis}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>