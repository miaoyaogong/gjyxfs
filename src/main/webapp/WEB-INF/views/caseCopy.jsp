<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Classic - Contact Page</title>
    <!--
    Classic Template
    http://www.cssmoban.com/tm-488-classic
    -->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="http://fonts.useso.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="${contextPath}/static/css/templatemo-style.css">                                   <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<%@ include file="./include/head.jsp"%>

<div class="tm-contact-img-container">

</div>

<section class="tm-section">
    <div class="container-fluid">
        <div class="row">
            <div class="row tm-margin-t-small">
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-2.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #2</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Read More</a>
                    </div>

                </div>

                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-2.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #2</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Read More</a>
                    </div>

                </div>

                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-3.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #3</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Detail</a>
                    </div>

                </div>
            </div>
            <div class="row tm-margin-t-small">
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-1.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #1</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Detail</a>
                    </div>

                </div>

                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-2.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #2</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Read More</a>
                    </div>

                </div>

                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">

                    <div class="tm-content-box">
                        <img src="${contextPath}/static/img/tm-img-310x180-3.jpg" alt="Image" class="tm-margin-b-30 img-fluid">
                        <h4 class="tm-margin-b-20 tm-gold-text">Lorem ipsum dolor #3</h4>
                        <p class="tm-margin-b-20">Aenean cursus tellus mauris, quis
                            consequat mauris dapibus id. Donec
                            scelerisque porttitor pharetra</p>
                        <a href="#" class="tm-btn text-uppercase">Detail</a>
                    </div>

                </div>
            </div>
        </div>

    </div>
</section>

<%@ include file="./include/footer.jsp"%>

</body>
</html>