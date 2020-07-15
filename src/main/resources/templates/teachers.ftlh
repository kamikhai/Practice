<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<#include "parts/header.ftlh">
<div id="colorlib-page">
    <div class="container">
        <div class="row">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="text-center">
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li ><a href="/profile">Группы</a></li>
                        <li class="colorlib-active"><a href="/teachers">Преподаватели</a></li>
                        <li ><a href="/registration">Регистрация</a></li>
                        <li ><a href="/job_profile">Профили работы</a></li>
                        <li ><a href="/tags">Теги</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="overlay"></div>
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5">
                                <div class="desc" style="text-align: center">
                                    <h1 class="big-letter center">Все преподаватели</h1>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">

                                                <section class="ftco-section" style="padding: 0 0 ! important">
                                                    <div class="container">
                                                        <div class="row">
                                                            <#list teacherList as teacher>
                                                                <div class="col-md-12 course d-lg-flex ftco-animate" style="margin-top: 20px; ">
                                                                    <div class="img" style="background-image: url(${teacher.user.photoPath});"></div>
                                                                    <div class="text bg-light p-4">
                                                                        <h3><a href="#">${teacher.fullname}</a></h3>
                                                                        <p>${teacher.position}</p>
                                                                    </div>
                                                                </div>
                                                            </#list>
                                                        </div>
                                                    </div>
                                                </section>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div><!-- END COLORLIB-MAIN -->
</div><!-- END COLORLIB-PAGE -->
<#include "parts/footer.ftlh">
</body>
</html>