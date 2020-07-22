<!doctype html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        function deleteProject() {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/project/${info.projectId}",
                method: "POST",
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                data: {"userId": ${info.userId}},
                success: function (response) {
                    var x = document.getElementById("snackbar");
                    x.innerText = response;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                    setTimeout(function () {
                        location = "/portfolio/${info.userId}";
                    }, 1500);
                },
                error: function (response) {
                    var x = document.getElementById("snackbar");
                    x.innerText = response;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                }
            });
        }
    </script>
</head>
<body onload="unescape()">

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
                                                                  style="background-image: url(${info.photoPath});"></span></a>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="/profile/${info.userId}">Обо мне</a></li>
                        <li class="colorlib-active"><a href="/portfolio/${info.userId}">Портфолио</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light" style="height: 100%">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <#--                        <div class="overlay" style="z-index: 1"></div>-->
                        <div class="d-flex align-items-center " style="height: 100%">
                            <div class="author-info text p-3 p-md-5"
                                 style="width: 100%; height: 100%;background-color: rgba(30,170,241,0.3); color: rgba(0,0,0,0.3)">
                                <div class="desc">
                                    <div class="d-md-flex">
                                        <h1 class="big-letter">${info.title}</h1>
                                        <#if isAccessible == true>
                                            <a onclick="deleteProject()"><span class="icon-delete"></span></a>
                                        </#if>
                                    </div>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <h2>Описание</h2>
                                        <p class="mb-4">${info.description?no_esc}</p>
                                        <h2>Теги</h2>
                                        <#list info.tags>
                                            <div class="mb-4">
                                                <#items as tag>
                                                    <span style="color: #000; margin-right: 10px">#${tag.name}</span>
                                                </#items>
                                            </div>
                                        </#list>
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
<div id="snackbar" style="z-index: 1002"></div>

</body>
</html>