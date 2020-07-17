<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        function deleteGroup(group) {
            $.ajax({
                url: "/api/admin/group/${group.id}",
                method: "DELETE",
                headers: {
                    "Authorization": "${token}"
                },
                complete: function (response) {
                    alert("Группа успешно удалена");
                    location = "/profile";
                }
            });
        }
    </script>
</head>
<body>
<#include "parts/header.ftlh">
<div id="colorlib-page">
    <div class="container">
        <div class="row">
            <div id="colorlib-mai">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5"
                                 style="background-color: rgba(30,170,241,0.3); color: rgba(0,0,0,0.3); width: 100%">
                                <div class="desc">
                                    <div class="d-md-flex justify-content-center" style="text-align: center">
                                        <h1 class="big-letter">${group.numeric}</h1>
                                        <#if admin>
                                            <a onclick="deleteGroup()"><span class="icon-delete"></span></a>
                                        </#if>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12">
                                        <div class="shop_grid_product_area">

                                            <div class="row">

                                                <section class="ftco-section" style="padding: 0 0 ! important">
                                                    <div class="container">
                                                        <div class="row">
                                                            <#list students as student>
                                                                <div id="student-${student.id}"
                                                                     class="col-md-12 course d-lg-flex ftco-animate"
                                                                     style="margin-top: 20px; ">
                                                                    <div class="img"
                                                                         style="background-image: url(${student.imgUrl!"img/empty_user.jpg"});"></div>
                                                                    <div class="text bg-light p-4">
                                                                        <h3>
                                                                            <a href="/profile/${student.id}">${student.fullName}</a>
                                                                        </h3>
                                                                        <p id="student-${student.id}-course"
                                                                           class="subheading"></p>
                                                                        <script>
                                                                            document.getElementById("student-${student.id}-course").innerHTML = make_zalgo("Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn");
                                                                        </script>
                                                                        <p>${student.description}</p>
                                                                        <#list student.tags as tag>
                                                                            <input type="submit" value="${tag}"
                                                                                   class="btn btn-secondary but">
                                                                        </#list>
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
</body>
</html>