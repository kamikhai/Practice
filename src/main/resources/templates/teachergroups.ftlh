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
                <h1 id="colorlib-logo"><a href="index.html"><span class="img"
                                                                  style="background-image: url(${photo});"></span></a>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <#if isOwnProfile>
                            <li><a href="/profile">Обо мне</a></li>
                        <#else>
                            <li><a href="/profile/${id}">Обо мне</a></li>
                        </#if>
                        <li class="colorlib-active"><a href="/my_students/${id}">Мои студенты</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light" style="height: 100%">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="overlay" ></div>
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5"  style="width: 100%">
                                <div class="desc" style="text-align: center">
                                    <h1 class="big-letter center">Мои группы</h1>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">
                                            <div class="row">
                                                <section class="ftco-section" style="padding: 0 0 ! important; width: 100%">
                                                    <div class="container">
                                                        <div class="row">
                                                            <#list groups as group>
                                                                <div class="col-md-6 course d-lg-flex ftco-animate"
                                                                     style="margin-top: 20px; ">
                                                                    <div class="text bg-light p-4">
                                                                        <h3><a href="/students_group?g=${group.id}">${group.numeric}</a></h3>
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
    </div>
</div>
<#include "parts/footer.ftlh">
</body>
</html>