<#-- @ftlvariable name="selected_tags" type="java.util.List<java.lang.Long>" -->
<#-- @ftlvariable name="selected_prof" type="java.util.List<java.lang.Long>" -->
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
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <h1 class="mb-2 bread">Наши студенты</h1>
            </div>
        </div>
    </div>
</section>

<section class="shop_grid_area section-padding-80" style="background-color: #fff">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 col-lg-3">
                <div class="shop_sidebar_area" style="margin-top: 20px">

                    <script>
                        const selected = {
                            tags: [<#list selected_tags as id>${id},</#list>],
                            profiles: [<#list selected_prof as id>${id},</#list>],
                        };

                        function doFilter() {
                            let query = "?";

                            for (let i = 0; i < selected.profiles.length; i++) {
                                query += "p=" + selected.profiles[i] + "&";
                            }
                            for (let i = 0; i < selected.tags.length; i++) {
                                query += "t=" + selected.tags[i] + "&";
                            }

                            window.location.search = query;
                        }

                        const data = {
                            tags: [<#list tags as tag>{
                                id: ${tag.id},
                                name: "${tag.name}",
                            },</#list>],
                            profiles: [<#list profiles as profile>{
                                id: ${profile.id},
                                name: "${profile.title}",
                            },</#list>],
                        }

                        function removeItem(id, list) {
                            const index = list.indexOf(id);
                            if (index > -1) {
                                list.splice(index, 1);
                                displaySelected();
                            }
                        }

                        function removeTag(id) {
                            removeItem(id, selected.tags);
                        }

                        function removeProfile(id) {
                            removeItem(id, selected.profiles);
                        }

                        function display(element, selected, data, f) {
                            element.innerHTML = "";

                            for (let i = 0; i < selected.length; i++) {
                                const item = data.find(e => e.id === selected[i]);
                                if (typeof item === "object") {
                                    element.innerHTML += "<li><a href='javascript:" + f.name + "(" + item.id + ")'>" + item.name + "</a></li>"
                                }
                            }
                        }

                        function displaySelected() {
                            display(document.getElementById("selected-profiles"), selected.profiles, data.profiles, removeProfile);
                            display(document.getElementById("selected-tags"), selected.tags, data.tags, removeTag);
                        }

                        function addItem(id, selected, data) {
                            if (!selected.includes(id)) {
                                const item = data.find(element => element.id === id);
                                if (typeof item === "object") {
                                    selected.push(id);
                                    displaySelected();
                                }
                            }
                        }

                        function addTag(id) {
                            addItem(id, selected.tags, data.tags);
                        }

                        function addProfile(id) {
                            addItem(id, selected.profiles, data.profiles);
                        }
                    </script>

                    <div class="widget catagory mb-50">
                        <h4 class="widget-title mb-30">Выбранные фильтры</h4>
                        <div class="catagories-menu">
                            <h6>Профили работы</h6>
                            <ul id="selected-profiles" class="sub-menu" style="margin-bottom: 0"></ul>
                        </div>
                        <div class="catagories-menu">
                            <h6>Тэги</h6>
                            <ul id="selected-tags" class="sub-menu" style="margin-bottom: 0"></ul>
                        </div>
                        <button class="btn btn-primary btn-sm" onclick="doFilter()">Применить фильтр</button>

                        <div>
                            <h6 class="widget-title mb-30">Добавить фильтры</h6>
                            <div class="catagories-menu" style="margin-top: 20px">
                                <ul id="menu-content2" class="menu-content">
                                    <li>
                                        <a data-toggle="collapse" href="#profiles">профиль работы</a>
                                        <ul id="profiles" class="sub-menu collapse">
                                            <#list profiles as profile>
                                                <li>
                                                    <a href="javascript:addProfile(${profile.id});">
                                                        ${profile.title}
                                                    </a>
                                                </li>
                                            </#list>
                                        </ul>
                                    </li>
                                    <li>
                                        <a data-toggle="collapse" href="#tags">тэги</a>
                                        <ul id="tags" class="sub-menu collapse">
                                            <#list tags as tag>
                                                <li>
                                                    <a href="javascript:addTag(${tag.id});">
                                                        ${tag.name}
                                                    </a>
                                                </li>
                                            </#list>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
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
                                        <div id="student-${student.id}" class="col-12 d-flex flex-column course ftco-animate" style="margin-top: 20px;">
                                            <div class="d-flex" style="min-height: 200px; max-height: 200px;">
                                                <div class="img" style="background-image: url(${student.imgUrl!"img/empty_user.jpg"});"></div>
                                                <div class="text bg-light p-4">
                                                    <h4><a href="/profile/${student.id}">${student.fullName}</a></h4>
                                                    <p style="min-height: 115px; max-height: 115px; overflow-y: auto">
                                                        ${student.description}
                                                    </p>
                                                </div>
                                            </div>
                                            <#list student.tags>
                                                <div class="ml-auto">
                                                 <#items as tag>
                                                    <span class="badge badge-pill badge-primary badge-tag" style="padding: 5px 7px;">${tag}</span>
                                                 </#items>
                                                </div>
                                            </#list>
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
</section>
<script>displaySelected();</script>
<#include "parts/footer.ftlh">
</body>
</html>