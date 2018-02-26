<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="tm-header">
    <div class="container-fluid">
        <div class="tm-header-inner">
            <a href="#" class="navbar-brand tm-site-name">${siteName}</a>

            <!-- navbar -->
            <nav class="navbar tm-main-nav">

                <button class="navbar-toggler hidden-md-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                    &#9776;
                </button>

                <div class="collapse navbar-toggleable-sm" id="tmNavbar">
                    <ul class="nav navbar-nav">
                        <c:choose>
                            <c:when test="${ menu == 'index'}" >
                                <li class="nav-item active">
                                    <a href="/" class="nav-link">首页</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="/" class="nav-link">首页</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${ menu == 'service'}" >
                                <li class="nav-item active">
                                    <a href="/service" class="nav-link">服务</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="/service" class="nav-link">服务</a>
                                </li>
                            </c:otherwise>
                        </c:choose>


                        <c:choose>
                            <c:when test="${ menu == 'case'}" >
                                <li class="nav-item active">
                                    <a href="/case" class="nav-link">案例</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="/case" class="nav-link">案例</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${ menu == 'treasure'}" >
                                <li class="nav-item active">
                                    <a href="/treasure" class="nav-link">吉祥物</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="/treasure" class="nav-link">吉祥物</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${ menu == 'about'}" >
                                <li class="nav-item active">
                                    <a href="/about" class="nav-link">关于我们</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="/about" class="nav-link">关于我们</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>

            </nav>

        </div>
    </div>
</div>