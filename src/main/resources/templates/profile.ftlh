<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script>
        function confirm(id) {
            $.ajax({
                url: "/api/student/competence?student=" + id,
                method: "POST",
                headers: {
                    "Authorization": "${token}"
                },
                complete: function (response) {
                    location.reload();
                }
            });
        }
    </script>
    <title>Document</title>
    <script>
        function updateDescription(text) {
            $.ajax({
                url: "/api/student/about?text=" + text,
                method: "POST",
                headers: {
                    "Authorization": "${token}"
                },
                dataType: "json",
                contentType: "application/json",
                complete: function (response) {
                    document.getElementById('info1').style.display = 'block';
                    document.getElementById('info2').style.display = 'none';
                    document.getElementById('info1').innerText = text;
                },
                error: function (response) {
                    alert(response.responseText);
                }
            });

        }

        var first = true;

        function addTag() {
            var e = document.getElementById("tags");
            var value = e.options[e.selectedIndex].value;
            console.log(result)
            if (first) {
                document.getElementById("tags_div").innerText = '';
                first = false;
            }
            document.getElementById("result").value += value + ' ';
            $('#tags_div').first().append('<div style="color: #fff; margin-right: 5px; margin-top: 5px; background-color: #35b7f1; padding: 5px; text-align: center" class="col-md-3">#' + value + '</div >')
            $('#tags').prop('selectedIndex',0);
        }

        function sendAjaxForm() {
            $.ajax({
                url:     "/profile",
                type:     "POST",
                dataType: "html",
                data: $("#new_competence").serialize(),
                complete: function(response) {
                    alert("Компетенция успешно добавлена");
                    location.reload();
                },
                error: function(response) {
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
                <form method="post" action="/profile" class="appointment-form ftco-animate" id="new_competence">
                    <div class="d-md-flex">
                        <input name="123" type="text" class="form-control" placeholder="Введите текст">
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
                        </div>
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="button" onclick="sendAjaxForm()" value="Добавить" class="btn btn-secondary py-3 px-4">
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
                <form action="#" class="appointment-form ftco-animate">
                    <div class="example-2" style="margin-top: 20px">
                        <div class="d-md-flex">
                            <input type="file" name="file" id="file" class="input-file" accept="image/jpeg,image/png">
                            <label for="file" class="btn btn-tertiary js-labelFile">
                                <i class="icon fa fa-check"></i>
                                <span class="js-fileName">Загрузить файл</span>
                            </label>
                        </div>
                    </div>
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="button" onclick="sendForm()" value="Добавить" class="btn btn-secondary py-3 px-4">
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
                                             style="background-image: url(${profileInfo.photoPath});"><div
                                class="iconupload" style="width: 100%; text-align: right"><a href="javascript:void(0)"
                                                                                             onclick="document.getElementById('envelope2').style.display='block';document.getElementById('fade').style.display='block'"><span
                                        class="icon-file_upload"
                                        style="color: white; margin-right: 5px"></span></a></div></span>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li class="colorlib-active"><a href="/profile">Обо мне</a></li>
                        <li><a href="/portfolio">Портфолио</a></li>
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
                                    <h3 class="" style="color: #1eaaf1">${profileInfo.jobProfileTitle}</h3>
                                    <span class="subheading">группа ${profileInfo.groupNumeric}</span>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <div class="d-md-flex">
                                            <h2>Обо мне</h2>
                                            <a href="javascript:void(0)"
                                               onclick="document.getElementById('info2').style.display='block';document.getElementById('info1').style.display='none'"><span
                                                        class="icon-pencil" style="margin-left: 5px"></span></a>
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
                                            <a href="javascript:void(0)"
                                               onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><span
                                                        class="icon-pencil" style="margin-left: 5px"></span></a>
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
                                            <a href="javascript:void(0)"
                                               onclick="document.getElementById('experience2').style.display='block';document.getElementById('experience1').style.display='none'"><span
                                                        class="icon-pencil" style="margin-left: 5px"></span></a>
                                        </div>
                                        <p class="mb-4" id="experience1">Нет опыта</p>
                                        <div class="mb-4" style="display: none" id="experience2">
                                            <form method="post">
                                        <textarea name="" cols="30" rows="4" class="form-control"
                                                  style="background-color: rgba(219,235,243,0.78) !important; margin-bottom: 20px">Нет опыта</textarea>
                                                <input type="button" class="btn btn-secondary">
                                            </form>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Свяжитесь со мной</h2>
                                            <a href="javascript:void(0)"
                                               onclick="document.getElementById('link2').style.display='block';document.getElementById('link1').style.display='none'"><span
                                                        class="icon-pencil" style="margin-left: 5px"></span></a>
                                        </div>
                                        <ul class="ftco-social mt-3" id="link1">
                                            <li class="ftco-animate"><a href="https://vk.com/khai_kam"><span
                                                            class="icon-chrome"></span></a>
                                            </li>
                                        </ul>
                                        <div class="mb-4" style="display: none" id="link2">
                                            <form method="post">
                                                <input type="text" class="form-control" value="https://vk.com/khai_kam"
                                                       style="margin-bottom: 20px; background-color: rgba(219,235,243,0.78) !important;">
                                                <input type="button" class="btn btn-secondary">
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
    </div><!-- END COLORLIB-MAIN -->
</div><!-- END COLORLIB-PAGE -->
<#include "parts/footer.ftlh">
<div id="fade" class="black-overlay"></div>

</body>
</html>