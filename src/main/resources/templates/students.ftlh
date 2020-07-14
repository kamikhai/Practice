<#-- @ftlvariable name="profiles" type="java.util.List<ru.itis.practice.models.JobProfile>" -->
<#-- @ftlvariable name="tags" type="java.util.List<ru.itis.practice.models.Tag>" -->
<#-- @ftlvariable name="students" type="java.util.List<ru.itis.practice.dto.StudentInfoDto>" -->
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
<section class="hero-wrap hero-wrap-2" style="background-image: url('img/bg_4.jpg');">
<#--    <div class="overlay"></div>-->
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <h1 class="mb-2 bread">Наши студенты</h1>
            </div>
        </div>
    </div>
</section>

<!-- ##### Shop Grid Area Start ##### -->
<section class="shop_grid_area section-padding-80" style="background-color: #fff">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 col-lg-3">
                <div class="shop_sidebar_area" style="margin-top: 20px">

                    <!-- ##### Single Widget ##### -->
                    <div class="widget catagory mb-50">
                        <!-- Widget Title -->
                        <h6 class="widget-title mb-30">Фильтры</h6>

                        <!--  Catagories  -->
                        <div class="catagories-menu" style="margin-top: 20px">
                            <ul id="menu-content2" class="menu-content">
                                <!-- Single Item -->
                                <li>
                                    <a data-toggle="collapse" href="#profiles">профиль работы</a>
                                    <ul id="profiles" class="sub-menu collapse">
                                        <#list profiles as profile>
                                            <li>
                                                <a href="?p=${profile.id}">${profile.title}</a>
                                            </li>
                                        </#list>
                                    </ul>
                                </li>
                                <!-- Single Item -->
                                <li>
                                    <a data-toggle="collapse" href="#tags">компетенции</a>
                                    <ul id="tags" class="sub-menu collapse">
                                        <#list tags as tag>
                                            <li>
                                                <a href="?t=${tag.id}">${tag.name}</a>
                                            </li>
                                        </#list>
                                    </ul>
                                </li>
                                <li>
                                    <a data-toggle="collapse" href="#courses">курс</a>
                                    <ul id="courses" class="sub-menu collapse">
                                        <li><a>1 (бакалавриат)</a></li>
                                        <li><a>2 (бакалавриат)</a></li>
                                        <li><a>3 (бакалавриат)</a></li>
                                        <li><a>4 (бакалавриат)</a></li>
                                        <li><a>1 (магистратура)</a></li>
                                        <li><a>2 (магистратура)</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-8 col-lg-9">
                <div class="shop_grid_product_area">
                    <div class="row">
                        <section class="ftco-section" style="padding: 0 0 ! important">
                            <div class="container">
                                <div class="row">
                                    <#list students as student>
                                        <div id="student-${student.id}" class="col-md-12 course d-lg-flex ftco-animate" style="margin-top: 20px; ">
                                            <div class="img" style="background-image: url(${student.imgUrl!"img/empty_user.jpg"});"></div>
                                            <div class="text bg-light p-4">
                                                <h3><a href="/profile/${student.id}">${student.fullName}</a></h3>
                                                <p class="subheading">2̈ͥ͌͒̄ͮ͏̷̩͓̦̰̼̱̬̗̯̘̲̠͓͖̞̳͠ͅ ̶̝͍̮̠̯̻͈̱̱̯̠̬̃̊ͯͣ̅̓͌̄͋̒̾͊͂ͤ͑̉́̚̕͞к͖̥͕̥ͧͩͮͣ̔͗̅́͘͞͡у̢͎͙̬͓̙̜̭̟̥̥͔̌͗̉̏͑́̒ͣ̕рͭͪ͛̓̒̏̑͂̽͏̷͕̬̝̟̙̥͈͓̠͇̕с̵͎̭͍̟̙̦̇̿̀̋ͩ̌̀̚͞͞</p>
                                                <p>${student.description}</p>
                                                <#list student.tags as tag>
                                                    <input type="submit" value="${tag}" class="btn btn-secondary but">
                                                </#list>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <!-- Pagination (not done yet) -->
                <!--nav aria-label="navigation">
                    <ul class="pagination mt-50 mb-70">
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                    </ul>
                </nav-->
            </div>
        </div>
    </div>
</section>

<#include "parts/footer.ftlh">
</body>
</html>