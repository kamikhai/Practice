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
                url: "/user_registration",
                type: "POST",
                dataType: "html",
                data: $("#user").serialize(),
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
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
<#include "parts/header.ftlh">
<div id="snackbar" style="z-index: 1002"></div>

<div id="colorlib-page">
    <div class="container">
        <div class="row">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="text-center">
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="/profile">Группы</a></li>
                        <li><a href="/teachers">Преподаватели</a></li>
                        <li class="colorlib-active"><a href="/registration">Регистрация</a></li>
                        <li><a href="/job_profile">Профили работы</a></li>
                        <li><a href="/tags">Теги</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="overlay"></div>
                        <div class="d-flex align-items-center  justify-content-center ">
                            <div class="author-info text p-3 p-md-5" style="width: 60% !important;">
                                <div class="desc" style="text-align: center;  ">
                                    <section class="ftco-section ftco-consult ftco-no-pt ftco-no-pb"
                                             data-stellar-background-ratio="0.5">
                                        <div class="container">
                                            <div class="row justify-content-center">
                                                <div class="col-md-12 py-12 px-md-12 bg-primary"
                                                     style="margin-top: 20px; margin-bottom: 20px;">
                                                    <!-- Tab links -->
                                                    <div class="tab" style="margin-top: 15px">
                                                        <button class="tablinks" onclick="openTab(event, 'User')"
                                                                id="defaultOpen">Новый пользователь
                                                        </button>

                                                        <script>
                                                            // Get the element with id="defaultOpen" and click on it
                                                        </script>
                                                        <button class="tablinks" onclick="openTab(event, 'Group')">Новая
                                                            группа
                                                        </button>
                                                    </div>


                                                    <!-- Tab content -->
                                                    <div id="User" class="tabcontent">
                                                        <form id="user" class="appointment-form ftco-animate"
                                                              style="margin-top: 30px">
                                                            <div class="d-md-flex">
                                                                <input type="email" class="form-control"
                                                                       placeholder="Почта" name="email">
                                                            </div>
                                                            <div class="d-md-flex">
                                                                <input type="text" class="form-control"
                                                                       placeholder="ФИО" name="name">
                                                            </div>
                                                            <div class="d-md-flex">
                                                                <input type="password" class="form-control"
                                                                       placeholder="Пароль" name="password">
                                                            </div>
                                                            <div class="d-md-flex">
                                                                <select name="role" id="role" class="form-control">
                                                                    <option value="" style="color: #1eaaf1" selected
                                                                            disabled>Выберите роль
                                                                    </option>
                                                                    <option value="ADMIN" style="color: #1eaaf1">
                                                                        Администратор
                                                                    </option>
                                                                    <option value="TEACHER" style="color: #1eaaf1">
                                                                        Преподаватель
                                                                    </option>
                                                                </select>
                                                            </div>
                                                            <div class="d-md-flex" style="margin-top: 20px;">
                                                                <div class="form-group">
                                                                    <input type="button" onclick="sendAjaxForm()"
                                                                           value="Добавить"
                                                                           class="btn btn-secondary py-3 px-4">
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>

                                                    <div id="Group" class="tabcontent">
                                                        <form id="group" method="post" action="/group_registration"
                                                              class="appointment-form ftco-animate"
                                                              style="margin-top: 30px" enctype="multipart/form-data">
                                                            <div class="d-md-flex">
                                                                <input type="text" class="form-control"
                                                                       placeholder="Группа" name="group">
                                                            </div>
                                                            <input type="hidden" name="_csrf" value="${_csrf.token}">

                                                            <div class="example-2" style="margin-top: 20px">
                                                                <div class="d-md-flex">
                                                                    <input type="file" name="file" id="file"
                                                                           class="input-file" accept=".xlsx">
                                                                    <label for="file"
                                                                           class="btn btn-tertiary js-labelFile">
                                                                        <i class="icon fa fa-check"></i>
                                                                        <span class="js-fileName">Загрузить список группы</span>
                                                                    </label>
                                                                </div>
                                                            </div>

                                                            <div style="margin-top: 20px;">
                                                                <div class="form-group">
                                                                    <input type="submit" value="Добавить"
                                                                           class="btn btn-secondary py-3 px-4">
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
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