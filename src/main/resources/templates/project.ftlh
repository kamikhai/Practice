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
<#--                        <div class="overlay" style="z-index: 1"></div>-->
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5" style="background-color: rgba(30,170,241,0.3); color: rgba(0,0,0,0.3)">
                                <div class="desc">
                                    <h1 class="big-letter">Мой сайт</h1>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <h2>Описание</h2>
                                        <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            Maecenas sed pretium tellus. In condimentum venenatis sapien, et porttitor
                                            nibh consectetur id. Vestibulum tincidunt leo id felis volutpat, consequat
                                            posuere metus gravida. Sed blandit ante a erat cursus, at cursus libero.</p>
                                        <h2>Теги</h2>
                                        <div class="mb-4">
                                            <span style="color: #000; margin-right: 10px">#Java</span></li>
                                            <span style="color: #000; margin-right: 10px">#Spring</span>
                                        </div>
                                        <h2>Видео</h2>
                                        <div style="margin-bottom: 20px">
                                            <iframe width="720" height="450"
                                                    src="https://www.youtube.com/embed/4NW4WKNpb7I" frameborder="1"
                                                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                                                    allowfullscreen></iframe>
                                        </div>
                                        <h2>Фото</h2>
                                        <div class="slider" style="margin-bottom: 20px">
                                            <div class="slider__wrapper">
                                                <div class="slider__item">
                                                    <div style="height: 500px; background: #deeef6;"><img
                                                                src="https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg" style="height: 100%; width: auto">
                                                    </div>
                                                </div>
                                                <div class="slider__item">
                                                    <div style="height: 500px;  background: #deeef6;"><img
                                                                src="https://i.pinimg.com/originals/a0/77/39/a07739aeb372debef2eb4166f63a5974.jpg" style="height: 100%; width: auto">
                                                    </div>
                                                </div>
                                                <div class="slider__item">
                                                    <div style="height: 500px; background: #deeef6;"><img
                                                                src="https://n1s1.hsmedia.ru/94/71/68/947168a6debcf7ab3499d0bd54257d87/620x462_1_ec4ca08c3f84afa3e477f3a01f9e0c97@665x495_0xac120003_4522973461562655669.jpg" style="height: 100%; width: auto">
                                                    </div>
                                                </div>
                                            </div>
                                            <a class="slider__control slider__control_left" href="#" role="button"></a>
                                            <a class="slider__control slider__control_right slider__control_show"
                                               href="#" role="button"></a>
                                        </div>
                                        <h2>Ссылки</h2>
                                        <ul class="">
                                            <li class="ftco-animate"><span class="icon-link"
                                                                           style="margin-right: 15px; color: #1eaaf1"></span><a
                                                        href="https://github.com/kamikhai/Practice">https://github.com/kamikhai/Practice</a>
                                            </li>
                                            <li class="ftco-animate"><span class="icon-link"
                                                                           style="margin-right: 15px; color: #1eaaf1"></span><a
                                                        href="https://vk.com/khai_kam">https://vk.com/khai_kam</a></li>
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
<div id="fade" class="black-overlay"></div>

<#include "parts/footer.ftlh">

</body>
</html>