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

<div id="envelope" class="envelope">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 25px; ">
                <a class="close-btn" style="margin-left: 97%" href="javascript:void(0)"
                   onclick="document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'">Закрыть</a>
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Добавить новую запись</h2>
                </div>
                <form action="#" class="appointment-form ftco-animate">
                    <div class="d-md-flex">
                        <input type="text" class="form-control" placeholder="Заголовок">
                    </div>
                    <div class="d-md-flex">
                        <textarea name="" id="" cols="30" rows="4" class="form-control" placeholder="Описание"
                                  style="background-color: rgba(30,170,241,0.78) !important;"></textarea>
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="submit" value="Добавить" class="btn btn-secondary py-3 px-4">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "parts/header.ftlh">


<div id="colorlib-page">
    <div class="container">
        <div class="row">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="text-center">
                <h1 id="colorlib-logo"><a href="index.html"><span class="img"
                                                                  style="background-image: url(/img/student-2.jpg);"></span></a>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="/profile">Обо мне</a></li>
                        <li class="colorlib-active"><a href="/portfolio">Портфолио</a></li>
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
                                    <div style="text-align: center">
                                        <h1 class="big-letter center">Все группы</h1>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">

                                                <section class="ftco-section" style="padding: 0 0 ! important">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div style="text-align: center; width: 100%">
                                                                <input type="submit" value="Добавить"
                                                                       class="btn btn-secondary py-3 px-4 show-btn"
                                                                       href="javascript:void(0)"
                                                                       onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"
                                                                       style="margin-top: 20px; margin-bottom: 20px; width: 90%">
                                                            </div>
                                                            <div class="col-md-12 course d-lg-flex ftco-animate"
                                                                 style="margin-top: 20px; ">
                                                                <div class="text bg-light p-4">
                                                                    <h3><a href="#">Хакатон</a></h3>
                                                                    <p>Lorem ipsum dolor sit amet, consectetur
                                                                        adipiscing elit. Fusce lacinia
                                                                        lectus sit amet pretium fermentum...</p>
                                                                    <span class="icon-file-photo-o file-icon"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 course d-lg-flex ftco-animate">
                                                                <div class="text bg-light p-4">
                                                                    <h3><a href="#">Сайт ProSALE</a></h3>
                                                                    <p>Lorem ipsum dolor sit amet, consectetur
                                                                        adipiscing elit. Fusce lacinia
                                                                        lectus sit amet pretium fermentum...</p>
                                                                    <span class="icon-link file-icon"></span>
                                                                    <span class="icon-file-video-o file-icon"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 course d-lg-flex ftco-animate">
                                                                <div class="text bg-light p-4">
                                                                    <h3><a href="#">Стажировка где-нибудь</a></h3>
                                                                    <p>Lorem ipsum dolor sit amet, consectetur
                                                                        adipiscing elit. Fusce lacinia
                                                                        lectus sit amet pretium fermentum...</p>
                                                                    <span class="icon-file-photo-o file-icon"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 course d-lg-flex ftco-animate">
                                                                <div class="text bg-light p-4">
                                                                    <h3><a href="#">Ещё что-нибудь</a></h3>
                                                                    <p>Lorem ipsum dolor sit amet, consectetur
                                                                        adipiscing elit. Fusce lacinia
                                                                        lectus sit amet pretium fermentum...</p>
                                                                    <span class="icon-file-photo-o file-icon"></span>
                                                                    <span class="icon-link file-icon"></span>
                                                                    <span class="icon-file-video-o file-icon"></span>
                                                                </div>
                                                            </div>
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
<div id="fade" class="black-overlay"></div>

<#include "parts/footer.ftlh">
</body>
</html>