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
            <aside id="colorlib-aside" role="complementary" class="text-center" >
                <h1 id="colorlib-logo"><a href="index.html"><span class="img"
                                                                  style="background-image: url(img/student-2.jpg);"></span></a>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li class="colorlib-active"><a href="index.html">Обо мне</a></li>
                        <li><a href="collection.html">Портфолио</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="overlay"></div>
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5">
                                <div class="desc">
                                    <h1 class="big-letter">Хайруллина Камилла Альбертовна</h1>
                                    <h3 class="" style="color: #1eaaf1">Web-разработчик</h3>
                                    <span class="subheading">2 курс, группа 11-802</span>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <h2>Обо мне</h2>
                                        <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sed pretium tellus. In condimentum venenatis sapien, et porttitor nibh consectetur id. Vestibulum tincidunt leo id felis volutpat, consequat posuere metus gravida. Sed blandit ante a erat cursus, at cursus libero.</p>
                                        <h2 >Компетенции</h2>
                                        <div class="mb-4">
                                            <input type="submit" value="Java" class="btn btn-secondary but" style="background-color: #1eaaf1">
                                            <input type="submit" value="Spring Boot" class="btn btn-secondary disabled but">
                                            <input type="submit" value="Jsoup" class="btn btn-secondary disabled but ">
                                        </div>
                                        <h2 >Опыт работы</h2>
                                        <p class="mb-4">Нет опыта</p>
                                        <h2 >Свяжитесь со мной</h2>
                                        <ul class="ftco-social mt-3" >
                                            <li class="ftco-animate"><a href="#"><span class="icon-vk"></span></a>
                                            </li>
                                            <li class="ftco-animate"><a href="#"><span class="icon-chrome"></span></a>
                                            </li>
                                        </ul>
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