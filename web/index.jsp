<%--
  Created by IntelliJ IDEA.
  User: RareMa
  Date: 2018/6/6
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/PurchaseAction_purchse">
    数量：<input type="text" name="purchases[0].purchaseitemCount"/>
    留言：<input type="text" name="purchases[0].purchaseitemMsg"/><br>
    商品id：<input type="text" name="purchases[0].productId"/>

    数量：<input type="text" name="purchases[1].purchaseitemCount"/>
    留言：<input type="text" name="purchases[1].purchaseitemMsg"/><br>
    商品id：<input type="text" name="purchases[1].productId"/>

    用户id：<input type="text" name="user.id"/>

    收货地址表id<input type="text" name="purchaseaddress.id"/>
    <input type="submit" value="提交"/>
</form>
<%--<form action="${pageContext.request.contextPath}/PurchaseaddressAction_save">--%>
    <%--数量：<input type="text" name="user.id"/>--%>
    <%--留言：<input type="text" name="puraddressCity"/><br>--%>
    <%--<input type="submit" value="提交"/>--%>
</form>
</body>
</html>
