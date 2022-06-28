<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_MIS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>


<label for="${AttributeConst.MIS_TITLE.getValue()}">案件名</label><br />
<input type="text" name="${AttributeConst.MIS_TITLE.getValue()}" value="${mission.title}" />
<br /><br />

<label for="${AttributeConst.MIS_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.MIS_CONTENT.getValue()}" rows="10" cols="50">${mission.content}</textarea>
<br /><br />

<fmt:parseDate value="${mission.visitStart}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="visitStart" type="date" />
<label for="${AttributeConst.MIS_VISIT_START.getValue()}">訪問予定日時</label><br />
<input type="date" name="${AttributeConst.MIS_VISIT_START.getValue()}" value="<fmt:formatDate value='${visitStart}' pattern='yyyy-MM-dd' />" />
<br /><br />

<fmt:parseDate value="${mission.visitFinish}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="visitFinish" type="date" />
<label for="${AttributeConst.MIS_VISIT_FINISH.getValue()}">訪問完了日時</label><br />
<input type="date" name="${AttributeConst.MIS_VISIT_FINISH.getValue()}" value="<fmt:formatDate value='${visitFinish}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="${AttributeConst.MIS_TRACK_SIZE.getValue()}">トラックサイズ</label><br />
<input type="text" name="${AttributeConst.MIS_TRACK_SIZE.getValue()}" value="${mission.trackSize}" />
<br /><br />

<fmt:parseDate value="${mission.moveStart}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="moveStart" type="date" />
<label for="${AttributeConst.MIS_MOVE_START.getValue()}">引っ越し予定日時</label><br />
<input type="date" name="${AttributeConst.MIS_MOVE_START.getValue()}" value="<fmt:formatDate value='${moveStart}' pattern='yyyy-MM-dd' />" />
<br /><br />

<fmt:parseDate value="${mission.moveFinish}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="moveFinish" type="date" />
<label for="${AttributeConst.MIS_MOVE_FINISH.getValue()}">引っ越し完了日時</label><br />
<input type="date" name="${AttributeConst.MIS_MOVE_FINISH.getValue()}" value="<fmt:formatDate value='${moveFinish}' pattern='yyyy-MM-dd' />" />
<br /><br />

<fmt:parseDate value="${mission.payDue}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="payDue" type="date" />
<label for="${AttributeConst.MIS_PAY_DUE.getValue()}">支払予定日時</label><br />
<input type="date" name="${AttributeConst.MIS_PAY_DUE.getValue()}" value="<fmt:formatDate value='${payDue}' pattern='yyyy-MM-dd' />" />
<br /><br />

<br /><br />
<input type="hidden" name="${AttributeConst.MIS_ID.getValue()}" value="${mission.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>