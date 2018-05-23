<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>主页</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
</head>
<body>
<!--导航栏部分-->
<%@include file="/common/header-nav.jsp" %>
<!-- 中间内容 -->
<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">

    <div class="carousel slide" id="myCarousel">
        <ol class="carousel-indicators">
            <li data-slide-to="0" data-target="#myCarousel" class="active">
            </li>
            <li data-slide-to="1" data-target="#myCarousel">
            </li>
            <li data-slide-to="2" data-target="#myCarousel">
            </li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img alt="" src="/images/feichuan.jpg" />
                <div class="carousel-caption">
                    <h4>
                        宇宙飞船
                    </h4>
                    <p>
                        宇宙飞船（英语名为 space craft,spaceship），是一种运送航天员、货物到达太空并安全返回的航天器。宇宙飞船可分为一次性使用与可重复使用两种类型。用运载火箭把飞船送入地球卫星轨道运行，然后再入大气层。飞船上除有一般人造卫星基本系统设备外，还有生命维持系统、重返地球的再入系统，回收登陆系统等。
                    </p>
                </div>
            </div>
            <div class="item">
                <img alt="" src="/images/gangtiexia.jpg" />
                <div class="carousel-caption">
                    <h4>
                        钢铁侠的战衣
                    </h4>
                    <p>
                        钢铁侠战衣是钢铁侠（托尼·史塔克）的战斗装甲。钢铁侠拥有赋予他超人力量，超人耐力，飞行能力与多种武器的动力装甲。此盔甲是由托尼·史塔克设计并大多数时间都是由他穿戴的。史塔克是位美国亿万富翁与军火制造商，以他独特的生活方式与其聪明才智及天才发明家闻名。
                    </p>
                </div>
            </div>
            <div class="item">
                <img alt="" src="/images/chuansongmen.jpg" />
                <div class="carousel-caption">
                    <h4>
                        传送门
                    </h4>
                    <p>
                        传送门，是对门的广义延伸，这个门连接的不是里外的空间，而是连接整个三维乃至多维空间。通过传送门，可以将人或物在一定时间内传送到特定或指定的地点或空间，是一种极其快捷的传输工具。目前传送门还只是个概念，只能在电影、动画及游戏中实现。
                    </p>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>




    <div class="jumbotron">
        <h1 align="center" valign="middle">
            欢迎来到购物商城
        </h1>




    </div>
        </div>
    </div>
</div>
<!-- /container -->
<!-- 尾部 -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
</body>
</html>