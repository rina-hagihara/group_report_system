<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.CUS_CODE.getValue()}">顧客番号</label><br />
<input type="text" name="${AttributeConst.CUS_CODE.getValue()}" value="${customer.code}" />
<br /><br />

<label for="${AttributeConst.CUS_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}" value="${customer.name}" />
<br /><br />

<label for="${AttributeConst.CUS_TEL.getValue()}">電話番号</label><br />
<input type="text" name="${AttributeConst.CUS_TEL.getValue()}" />
<br /><br />

<label for="${AttributeConst.CUS_ADDRESS.getValue()}">住所</label><br />
<input type="text" name="${AttributeConst.CUS_ADDRESS.getValue()}" />
<br /><br />

<fmt:parseDate value="${cutomer.moveDay}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="moveDay" type="date" />
<label for="${AttributeConst.CUS_MOVE_DAY.getValue()}">引っ越し日</label><br />
<input type="date" name="${AttributeConst.CUS_MOVE_DAY.getValue()}" value="<fmt:formatDate value='${moveDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="${AttributeConst.CUS_PAY_FLAG.getValue()}">支払い状況</label><br />
<select name="${AttributeConst.CUS_PAY_FLAG.getValue()}">
    <option value="${AttributeConst.PAY_TRUE.getIntegerValue()}">支払い済み</option>
    <option value="${AttributeConst.PAY_FALSE.getIntegerValue()}">未払い</option>
</select>
<br /><br />
<input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>