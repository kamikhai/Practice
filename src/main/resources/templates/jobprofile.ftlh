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
                        <li ><a href="/teachers">Преподаватели</a></li>
                        <li ><a href="/registration">Регистрация</a></li>
                        <li class="colorlib-active"><a href="/job_profile">Профили работы</a></li>
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
                                    <h1 class="big-letter center">Все профили работы</h1>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">
                                                <section class="ftco-section" style="padding: 0 0 ! important">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div style="text-align: center; width: 100%">
                                                                <input type="submit" value="Добавить" class="btn btn-secondary py-3 px-4 show-btn"
                                                                       href="javascript:void(0)"
                                                                       style="margin-top: 20px; margin-bottom: 20px; width: 90%">
                                                            </div>
                                                            <#list jobs as job>
                                                                <div class="col-md-6 course d-lg-flex ftco-animate" style="margin-top: 20px; ">
                                                                    <div class="text bg-light p-4">
                                                                        <h3><a href="#">${job.title}</a></h3>
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