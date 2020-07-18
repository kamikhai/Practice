<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<script>
	function addPhoto() {
		var formData = new FormData();
		var files = ($('#file'))[0]['files'];
		[].forEach.call(files, function (file, i, files) {
			formData.append("file", file);
		});
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "/profile/photo",
			data: formData,
			processData: false,
			contentType: false
		})
			.done(function (response) {
				alert(response);
				location.reload();
			})
			.fail(function () {
				alert('Error')
			});
	}

	function updatePosition(text) {
		$.ajax({
			url: "/api/teacher/position?text=" + text,
			method: "POST",
			headers: {
				"Authorization": "${token}"
			},
			dataType: "json",
			contentType: "application/json",
			complete: function (response) {
				document.getElementById('position1').style.display = 'block';
				document.getElementById('position2').style.display = 'none';
				document.getElementById('position1').innerText = text;
			},
			error: function (response) {
				alert(response.responseText);
			}
		});
	}

	function updateInformation(text) {
		$.ajax({
			url: "/api/teacher/information?text=" + text,
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


	function updateLink(text) {
		$.ajax({
			url: "/api/teacher/link?text=" + text,
			method: "POST",
			headers: {
				"Authorization": "${token}"
			},
			dataType: "json",
			contentType: "application/json",
			complete: function (response) {
				document.getElementById('link1').style.display = 'block';
				document.getElementById('link2').style.display = 'none';
				document.getElementById('link1').href = text;
			},
			error: function (response) {
				alert(response.responseText);
			}
		});
	}

    function sendAjaxForm() {
        var e = document.getElementById("group");
        var value = e.options[e.selectedIndex].value;
        console.log(value);
        var data = {group: value}
        $.ajax({
            url: "/api/admin/teacher/${profileInfo.id}",
            type: "POST",
            data: JSON.stringify(data),
            headers: {
                "Authorization": "${token}"
            },
            contentType: "application/json",
            dataType: "json",
            complete: function (response) {
                alert("Группа успешно добавлена");
                location.reload();
            },
            error: function (response) {
            }
        });
    }
</script>
<body>
<div id="envelope" class="envelope">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 25px; ">
                <a class="close-btn" style="margin-left: 97%" href="javascript:void(0)"
                   onclick="document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'">Закрыть</a>
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Добавить группу</h2>
                </div>
                <form method="post" action="/profile" class="appointment-form ftco-animate" id="new_competence">
                    <div class="d-md-flex">
                        <select name="group" id="group" class="form-control">
                            <option value="" style="color: #1eaaf1" selected disabled>Выберите группу</option>
                            <#list groups as group>
                                <option value="${group.id}" style="color: #1eaaf1">${group.numeric}</option>
                            </#list>
                        </select>
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
                        <li class="colorlib-active"><a href="/profile">Обо мне</a></li>
                        <li><a href="/my_students/${id}">Мои студенты</a></li>
                    </ul>
                </nav>
            </aside>
            <div id="colorlib-main">
                <section class="ftco-section-no-padding bg-light">
                    <div class="hero-wrap" style="background-color: #fff ! important">
                        <div class="d-flex align-items-center ">
                            <div class="author-info text p-3 p-md-5"
                                 style="background-color: rgba(30,170,241,0.3); color: rgba(0,0,0,0.3); width: 100%">
                                <div class="desc">
                                    <h1 class="big-letter">${profileInfo.fullname}</h1>
                                    <hr style="height:1px;border-width:0; width: 70%; color:#1eaaf1;background-color:gray;">
                                    <div style="margin-top: 15px">
                                        <div class="d-md-flex">
                                            <h2>Должность</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('position2').style.display='block';document.getElementById('position1').style.display='none'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <p class="mb-4" id="position1">${profileInfo.position}</p>
                                        <div class="mb-4" style="display: none" id="position2">
                                            <form method="post">
                                        <textarea name="" cols="30" rows="6" class="form-control" id="positionForm"
                                                  style="background-color: rgba(219,235,243,0.78) !important; margin-bottom: 20px">${profileInfo.position}</textarea>
                                                <input type="button" value="Обновить" class="btn btn-secondary" onclick="updatePosition($('#positionForm').val())">
                                            </form>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Дополнительная информация</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('info2').style.display='block';document.getElementById('info1').style.display='none'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <p id="info1">${profileInfo.information}</p>
                                        <div class="mb-4" style="display: none" id="info2">
                                            <form method="post">
                                        <textarea name="" cols="30" rows="6" class="form-control" id="informationForm"
                                                  style="background-color: rgba(219,235,243,0.78) !important; margin-bottom: 20px">${profileInfo.information}</textarea>
                                                <input type="button" value="Обновить" class="btn btn-secondary" onclick="updateInformation($('#informationForm').val())">
                                            </form>
                                        </div>
                                        <div class="d-md-flex">
                                            <h2>Ответственный за группы</h2>
                                            <#if isAdmin == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <ul class="ftco-social mt-3">
                                            <#list profileInfo.groups as group>
                                                <li class="ftco-animate"><a href="#">${group.numeric}</a></li>
                                            </#list>
                                        </ul>
                                        <div class="d-md-flex">
                                            <h2>Свяжитесь со мной</h2>
                                            <#if isOwnProfile == true>
                                                <a href="javascript:void(0)"
                                                   onclick="document.getElementById('link2').style.display='block';document.getElementById('link1').style.display='none'"><span
                                                            class="icon-pencil" style="margin-left: 5px"></span></a>
                                            </#if>
                                        </div>
                                        <ul class="ftco-social mt-1" id="link1">
                                            <li class="ftco-animate"><a href="${profileInfo.link}"><span
                                                            class="icon-chrome"></span></a>
                                            </li>
                                        </ul>
                                        <div class="mb-4" style="display: none" id="link2">
                                            <form method="post">
                                                <input type="text" class="form-control"
                                                       value="${profileInfo.link}"
                                                       style="margin-bottom: 20px; background-color: rgba(219,235,243,0.78) !important;" id="linkForm">
                                                <input type="button" value="Обновить" class="btn btn-secondary" onclick="updateLink($('#linkForm').val())">
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
<div id="fade" class="black-overlay"></div>

</body>
</html>