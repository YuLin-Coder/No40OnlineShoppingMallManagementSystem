<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>


</head>
<body>
<div class="container header">
	<%-- <div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="艺美商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div> --%>
	<%@ include file="/html/header.html" %>
	<%@ include file="menu.jsp" %>
	
</div>	<div class="container cart">
		<c:if test="${not empty cart.cartItems }">
		<div class="span24">
			<div class="step step1">
				购物车信息
			</div>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
						<c:forEach var="cartItem" items="${cart.cartItems }">
						<tr>
							<td width="60">
								<img src="${pageContext.request.contextPath}/${cartItem.product.image}"/>
							</td>
							<td>
								<a target="_blank">${cartItem.product.pname}</a>
							</td>
							<td>
								￥${cartItem.product.shopPrice}
							</td>
							<td class="quantity" width="60">
								${cartItem.count}
							</td>
							<td width="140">
								<span class="subtotal">￥${cartItem.subtotal}</span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart_removeCart?pid=${cartItem.product.pid}" class="delete">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint">${cart.total}</em>
					商品金额: <strong id="effectivePrice">￥${cart.total}元</strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/cart_clearCart" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/order_saveOrder" id="submit" class="submit">提交订单</a>
				</div>
				
		</div>
		</c:if>
		<c:if test="${empty cart.cartItems }">
			<div class="span24">
				<div class="step step1">
					<h2>亲!您还没有购物!请先去购物!</h2>
				</div>
			</div>
		</c:if>
	</div>
	
	<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2015-2025 从戎源码网 版权所有</div>
	</div>
</div>
</body></html>