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
        function sendAjaxForm() {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/api/admin/tags",
                type: "POST",
                dataType: "html",
                headers: {
                    "Authorization": "${token}"
                },
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                data: $("#new_tag").serialize(),
                success: function (response) {
                    var x = document.getElementById("snackbar");
                    x.innerText = response;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                    setTimeout(function () {
                        location.reload();
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

        function deleteTag(id) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/api/admin/tags/"+id,
                type: "DELETE",
                headers: {
                    "Authorization": "${token}"
                },
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                success: function (response) {
                    var x = document.getElementById("snackbar");
                    x.innerText = response;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                    setTimeout(function () {
                        location.reload();
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
<body>
<div id="envelope" class="envelope">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 25px; ">
                <a class="close-btn" style="margin-left: 97%" href="javascript:void(0)"
                   onclick="document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'">Закрыть</a>
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Добавить тег</h2>
                </div>
                <form method="post" action="/profile" class="appointment-form ftco-animate" id="new_tag">
                    <div class="d-md-flex">
                        <input name="tag" type="text" class="form-control" placeholder="Введите тег">
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="button" onclick="sendAjaxForm()" value="Добавить"
                                   class="btn btn-secondary py-3 px-4">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "parts/header.ftlh">
<div id="snackbar" style="z-index: 1002"></div>
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
                        <li ><a href="/job_profile">Профили работы</a></li>
                        <li class="colorlib-active"><a href="/tags">Теги</a></li>
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
                                    <h1 class="big-letter center">Все теги</h1>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">
                                                <section class="ftco-section" style="padding: 0 0 ! important">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div style="text-align: center; width: 100%">
                                                                <input type="submit" value="Добавить" class="btn btn-secondary py-3 px-4 show-btn"
                                                                       href="javascript:void(0)" onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"
                                                                       style="margin-top: 20px; margin-bottom: 20px; width: 90%">
                                                            </div>
                                                            <#list tags as tag>
                                                                <div class="col-md-4 course d-lg-flex ftco-animate" style="margin-top: 20px; ">
                                                                    <div class="text bg-light p-4 ">
                                                                        <h3 onclick="deleteTag(${tag.id})"><a href="#">${tag.name}</a></h3>
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
<div id="fade" class="black-overlay"></div>

</body>
</html>