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
<section class="home-slider owl-carousel">
    <div class="slider-item" style="background-image:url(img/bg_1.jpg);">
        <div class="overlay"></div>
        <div class="container">
            <div class="row no-gutters slider-text align-items-center justify-content-center"
                 data-scrollax-parent="true">
                <div class="col-md-8 text-center ftco-animate">
                    <h1 class="mb-4">Что-то <span>Хорошее</span></h1>
                    <p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">Узнать больше</a></p>
                </div>
            </div>
        </div>
    </div>

    <div class="slider-item" style="background-image:url(img/bg_2.jpg);">
        <div class="overlay"></div>
        <div class="container">
            <div class="row no-gutters slider-text align-items-center justify-content-center"
                 data-scrollax-parent="true">
                <div class="col-md-8 text-center ftco-animate">
                    <h1 class="mb-4">Какие-то<span> Ребята</span></h1>
                    <p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">Узнать больше</a></p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-services ftco-no-pb">
    <div class="container-wrap">
        <div class="row no-gutters">
            <div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-primary">
                <div class="media block-6 d-block text-center">
                    <div class="icon d-flex justify-content-center align-items-center">
                        <span class="flaticon-teacher"></span>
                    </div>
                    <div class="media-body p-2 mt-3">
                        <h3 class="heading">Лучшие преподаватели</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porttitor non mauris ac
                            laoreet. Aenean consequat</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-tertiary">
                <div class="media block-6 d-block text-center">
                    <div class="icon d-flex justify-content-center align-items-center">
                        <span class="flaticon-reading"></span>
                    </div>
                    <div class="media-body p-2 mt-3">
                        <h3 class="heading">Целеустремленные студенты</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porttitor non mauris ac
                            laoreet. Aenean consequat</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-fifth">
                <div class="media block-6 d-block text-center">
                    <div class="icon d-flex justify-content-center align-items-center">
                        <span class="flaticon-books"></span>
                    </div>
                    <div class="media-body p-2 mt-3">
                        <h3 class="heading">Качественное обучение</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porttitor non mauris ac
                            laoreet. Aenean consequat</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-quarternary">
                <div class="media block-6 d-block text-center">
                    <div class="icon d-flex justify-content-center align-items-center">
                        <span class="flaticon-diploma"></span>
                    </div>
                    <div class="media-body p-2 mt-3">
                        <h3 class="heading">Портфолио</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porttitor non mauris ac
                            laoreet. Aenean consequat</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<#include "parts/footer.ftlh">
</body>
</html>