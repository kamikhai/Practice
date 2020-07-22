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
        function confirm(id) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url:
                    "/api/student/competence?c=" + id,
                method: "POST",
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
                error: function (data) {
                    var x = document.getElementById("snackbar");
                    x.innerText = data.responseText;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                }
            })
            ;
        }
    </script>
    <title>Document</title>
    <script>
        function updateDescription(text) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/api/student/about?text=" + text,
                method: "POST",
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
                    x.innerText = response.responseText;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                }
            });
        }

        function updateWorkExperience(text) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/api/student/experience?text=" + text,
                method: "POST",
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
                    x.innerText = response.responseText;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                }
            });
        }

        function updateLink(text) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: "/api/student/link?text=" + text,
                method: "POST",
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
                    x.innerText = response.responseText;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                }
            });
        }

        var first = true;
        var i = 1;
        var result = '';

        function addTag() {
            var e = document.getElementById("tags");
            var value = e.options[e.selectedIndex].value;
            console.log(result);
            if (first) {
                document.getElementById("tags_div").innerText = '';
                first = false;
            }
            // document.getElementById("result").value += value + ' ';
            result += value + ' ';
            console.log(result);
            $('#tags_div').first().append('<div onclick="delete_tag(' + i + ')" id="' + i + '" style="color: #fff; margin-right: 5px; margin-top: 5px; background-color: #35b7f1; padding: 5px; text-align: center" class="col-md-3">#' + value + '</div >')
            $('#tags').prop('selectedIndex', 0);
            i++;
        }

        function sendAjaxForm() {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            document.getElementById("result").value += result;
            $.ajax({
                url: "/profile/competence",
                type: "POST",
                dataType: "html",
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                data: $("#new_competence").serialize(),
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

        function delete_tag(i) {
            var str = document.getElementById(i).innerText.substr(1);
            console.log(str);
            result = result.replace(str + ' ', '');
            document.getElementById(i).remove();
            if ($('#tags_div').is(':empty')) {
                $('#tags_div').first().append('<span style="color: #fff; margin-right: 10px">Здесь будут расположены выбранные вами теги</span>\n' +
                    '                            <span style="color: #fff; margin-right: 10px">Нажмите на тег, если хотите его удалить</span>')
                first = true;
            }
        }

        function addPhoto() {
            var formData = new FormData();
            var files = ($('#file'))[0]['files'];
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/profile/photo",
                data: formData,
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    var x = document.getElementById("snackbar");
                    x.innerText = response;
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                })
                .fail(function () {
                    var x = document.getElementById("snackbar");
                    x.innerText = "Ошибка загрузки";
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                });
        }

        function sendJobProfile() {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var e = document.getElementById("job");
            var value = e.options[e.selectedIndex].value;
            console.log(value);
            var data = {id: value}
            $.ajax({
                url: "/api/student/jobprofile",
                type: "POST",
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                data: JSON.stringify(data),
                headers: {
                    "Authorization": "${token}"
                },
                contentType: "application/json",
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
                    <h2 class="mb-4">Добавить компетенцию</h2>
                </div>
                <form method="post" action="/profile/competence" class="appointment-form ftco-animate"
                      id="new_competence">
                    <div class="d-md-flex">
                        <input name="competenceName" type="text" class="form-control" placeholder="Введите текст">
                    </div>

                    <div class="d-md-flex">
                        <select name="tag" id="tags" class="form-control" onchange="addTag()">
                            <option value="" style="color: #1eaaf1" selected disabled>Выберите тег</option>
                            <#list tags as tag>
                                <option value="${tag.name}" style="color: #1eaaf1">${tag.name}</option>
                            </#list>
                        </select>
                    </div>
                    <input id="result" hidden name="result">
                    <div class=" justify-content-center">
                        <div class="row justify-content-center" id="tags_div"
                             style="margin-top: 30px; margin-bottom: 10px; padding-top: 10px; padding-bottom: 10px; ">
                            <span style="color: #fff; margin-right: 10px">Здесь будут расположены выбранные вами теги</span>
                            <span style="color: #fff; margin-right: 10px">Нажмите на тег, если хотите его удалить</span>
                        </div>
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
<div id="envelope2" class="envelope">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 25px; ">
                <a class="close-btn" style="margin-left: 97%" href="javascript:void(0)"
                   onclick="document.getElementById('envelope2').style.display='none';document.getElementById('fade').style.display='none'">Закрыть</a>
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Изменить фото</h2>
                </div>
                <form method="post" class="appointment-form ftco-animate">
                    <div class="example-2" style="margin-top: 20px">
                        <div class="d-md-flex">
                            <input type="file" name="file" id="file" class="input-file" accept=".jpeg, .jpg">
                            <label for="file" class="btn btn-tertiary js-labelFile">
                                <i class="icon fa fa-check"></i>
                                <span class="js-fileName">Загрузить новое фото</span>
                            </label>
                        </div>
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="button" onclick="addPhoto()" value="Добавить"
                                   class="btn btn-secondary py-3 px-4">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="envelope3" class="envelope">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 25px; ">
                <a class="close-btn" style="margin-left: 97%" href="javascript:void(0)"
                   onclick="document.getElementById('envelope3').style.display='none';document.getElementById('fade').style.display='none'">Закрыть</a>
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Выбрать профиль работы</h2>
                </div>
                <form method="post" class="appointment-form ftco-animate" id="new_job">
                    <div class="d-md-flex">
                        <select name="group" id="job" class="form-control">
                            <option value="" style="color: #1eaaf1" selected disabled>Выберите профиль работы</option>
                            <#list jobs as job>
                                <option value="${job.id}" style="color: #1eaaf1">${job.title}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="button" onclick="sendJobProfile()" value="Добавить"
                                   class="btn btn-secondary py-3 px-4">
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
                <h1 id="colorlib-logo"><span class="img"
                                             style="background-image: url(${profileInfo.photoPath});">
                                                                    <#if isOwnProfile == true>

                                                                        <div
                                                                                class="iconupload"
                                                                                style="width: 100%; text-align: right"><a
                                                                                    href="javascript:void(0)"
                                                                                    onclick="document.getElementById('envelope2').style.display='block';document.getElementById('fade').style.display='block'"><span
                                                                                        class="icon-file_upload"
                                                                                        style="color: white; margin-right: 5px"></span></a></div>
                                                                    </#if>
                    </span>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <#if isOwnProfile>
                            <li class="colorlib-active"><a href="/profile">Обо мне</a></li>
                        <#else>
                            <li class="colorlib-active"><a href="/profile/${id}">Обо мне</a></li>
                        </#if>
                        <li><a href="/portfolio/${id}">Портфолио</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5"
                                 style="background-color: rgba(30,170,241,0.3); color: rgba(0,0,0,0.3); width: 100%">
                                <div class="desc">
                                    <h1 class="big-letter">${profileInfo.fullName}</h1>
                                    <div class="d-md-flex">
                                        <h3 class=""
                                            style="color: #1eaaf1">${profileInfo.jobProfileTitle!"Профиль работы не выбран"}</h3>
                                        <#if isOwnProfile == true>
                                            <a href="javascript:void(0)"
                                               onclick="document.getElementById('envelope3').style.display='block';document.getElementById('fade').style.display='block'"><span
                                                        class="icon-pencil" style="margin-left: 5px"></span></a>
                                        </#if>
                                    </div>
                                    <span class="subheading">группа ${profileInfo.groupNumeric}</span>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <div class="d-md-flex">
                                            <h2>Обо мне</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('info2').style.display='block';document.getElementById('info1').style.display='none'">
                                                    <span class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <p class="mb-4" id="info1">${profileInfo.description}</p>
                                        <div class="mb-4" style="display: none" id="info2">
                                            <form method="post">
                                        <textarea name="" cols="30" rows="6" class="form-control" id="aboutForm"
                                                  style="background-color: rgba(219,235,243,0.78) !important; margin-bottom: 20px">${profileInfo.description}</textarea>
                                                <input type="button" value="Обновить"
                                                       onclick="updateDescription($('#aboutForm').val())"
                                                       class="btn btn-secondary">
                                            </form>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Компетенции</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <div class="mb-4">
                                                <#list profileInfo.competenceList>
                                                    <ul>
                                                        <#items as comp>
                                                            <li><input type="button" value="${comp.description}"
                                                                       style="margin-bottom: 10px; margin-right: 10px"
                                                                       <#if comp.confirmed == false>class="btn btn-secondary disabled butd"
                                                                        <#if user! == 'TEACHER'> onclick = "confirm(${comp.id})"</#if>
                                                                        <#else> class="btn btn-secondary disabled but"
                                                                            onclick="window.location.href = '/profile/${comp.confirmedBy.user.id}'"
                                                                        </#if>
                                                                >
                                                                <#if comp.tags??>
                                                                    <#list comp.tags as tag>
                                                                        <span style="color: #000; margin-right: 10px">#${tag.name}</span>
                                                                    </#list>
                                                                </#if>
                                                            </li>
                                                        </#items>
                                                    </ul>
                                                </#list>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Опыт работы</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('experience2').style.display='block';document.getElementById('experience1').style.display='none'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <p class="mb-4"
                                           id="experience1">${profileInfo.workExperience!'Нет информации'}</p>
                                        <div class="mb-4" style="display: none" id="experience2">
                                            <form method="post">
                                        <textarea name="" cols="30" rows="4" class="form-control"
                                                  id="workExperienceForm"
                                                  style="background-color: rgba(219,235,243,0.78) !important; margin-bottom: 20px">${profileInfo.workExperience!'Нет информации'}</textarea>
                                                <input type="button" value="Обновить"
                                                       onclick="updateWorkExperience($('#workExperienceForm').val())"
                                                       class="btn btn-secondary">
                                            </form>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Свяжитесь со мной</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('link2').style.display='block';document.getElementById('link1').style.display='none'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <ul class="ftco-social mt-3" id="link1">
                                            <#if profileInfo.link??>
                                                <li class="ftco-animate"><a id="link"
                                                                            href="${profileInfo.link!'#'}"><span
                                                                class="icon-chrome"></span></a>
                                                </li>
                                            <#else>
                                                <li class="ftco-animate">Способ связи не указан</li>
                                            </#if>
                                        </ul>
                                        <div class="mb-4" style="display: none" id="link2">
                                            <form method="post">
                                                <input type="text" class="form-control" id="linkForm"
                                                       value="${profileInfo.link!''}"
                                                       style="margin-bottom: 20px; background-color: rgba(219,235,243,0.78) !important;">
                                                <input type="button" value="Обновить"
                                                       onclick="updateLink($('#linkForm').val())"
                                                       class="btn btn-secondary">
                                            </form>
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
<script>
    (function () {

        'use strict';

        $('.input-file').each(function () {
            var $input = $(this),
                $label = $input.next('.js-labelFile'),
                labelVal = $label.html();

            $input.on('change', function (element) {
                var fileName = '';
                if (element.target.value) fileName = element.target.value.split('\\').pop();
                fileName ? $label.addClass('has-file').find('.js-fileName').html(fileName) : $label.removeClass('has-file').html(labelVal);
            });
        });

    })();
</script>
<div id="fade" class="black-overlay"></div>
<div id="snackbar" style="z-index: 1002"></div>

</body>
</html>