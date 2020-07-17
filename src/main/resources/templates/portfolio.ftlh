<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
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
            $('#tags_div').first().append('<div onclick="delete_tag('+i+')" id="' + i + '" style="color: #fff; margin-right: 5px; margin-top: 5px; background-color: #35b7f1; padding: 5px; text-align: center" class="col-md-3">#' + value + '</div >')
            $('#tags').prop('selectedIndex', 0);
            i++;
        }

        function sendAjaxForm() {
            document.getElementById("result").value += result;
            $.ajax({
                url: "/portfolio",
                type: "POST",
                dataType: "html",
                data: $("#new_project").serialize(),
                complete: function (response) {
                    location.reload();
                },
                error: function (response) {
                }
            });
        }

        function delete_tag(i) {
            var str = document.getElementById(i).innerText.substr(1);
            console.log(str);
            result = result.replace(str + ' ', '');
            document.getElementById(i).remove();
            if ( $('#tags_div').is(':empty')){
                $('#tags_div').first().append('<span style="color: #fff; margin-right: 10px">Здесь будут расположены выбранные вами теги</span>\n' +
                    '                            <span style="color: #fff; margin-right: 10px">Нажмите на тег, если хотите его удалить</span>')
                first = true;
            }
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
                    <h2 class="mb-4">Добавить новую запись</h2>
                </div>
                <form method="post" class="appointment-form ftco-animate" id="new_project">
                    <div class="d-md-flex">
                        <input id="title" name="title" type="text" class="form-control" placeholder="Заголовок">
                    </div>
                    <div class="d-md-flex">
                        <textarea name="description" id="description" cols="30" rows="4" class="form-control" placeholder="Описание"
                                  style="background-color: rgba(30,170,241,0.78) !important;"></textarea>
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
<#include "parts/header.ftlh">


<div id="colorlib-page">
    <div class="container">
        <div class="row">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="text-center">
                <h1 id="colorlib-logo"><a href="index.html"><span class="img"
                                                                  style="background-image: url(${userInfo.photoPath});"></span></a>
                </h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="/profile/${userInfo.id}">Обо мне</a></li>
                        <li class="colorlib-active"><a href="/portfolio/${userInfo.id}">Портфолио</a></li>
                    </ul>
                </nav>
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="overlay"></div>
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5" style="width: 100%">
                                <div class="desc">
                                    <div style="text-align: center">
                                        <h1 class="big-letter center">Мои портфолио</h1>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">

                                                <section class="ftco-section" style="padding: 0 0 ! important; width: 100%">
                                                    <div class="container">
                                                        <div class="row">
                                                            <#if userInfo.isOwnProfile == true>
                                                                <div style="text-align: center; width: 100%">
                                                                    <input type="submit" value="Добавить"
                                                                           class="btn btn-secondary py-3 px-4 show-btn"
                                                                           href="javascript:void(0)"
                                                                           onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"
                                                                           style="margin-top: 20px; margin-bottom: 20px; width: 90%">
                                                                </div>
                                                            </#if>
                                                            <#list projects>
                                                                <#items as proj>
                                                                    <div class="col-md-12 course d-lg-flex ftco-animate">
                                                                        <div class="text bg-light p-4" style="width: 100%">
                                                                            <h3><a href="/project/${proj.projectId}">${proj.title}</a></h3>
                                                                                <#list proj.tags>
                                                                                    <div class="mb-4">
                                                                                        <#items as tag>
                                                                                            <span style="color: #000; margin-right: 10px">#${tag.name}</span>
                                                                                        </#items>
                                                                                    </div>
                                                                                </#list>
                                                                        </div>
                                                                    </div>
                                                                </#items>
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
<div id="fade" class="black-overlay"></div>

<#include "parts/footer.ftlh">
</body>
</html>