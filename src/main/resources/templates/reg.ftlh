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
<section class="ftco-section ftco-consult ftco-no-pt ftco-no-pb" style="background-color: rgba(30,170,241,0.26)"
         data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 100px; margin-bottom: 100px">

                <div class="tab">
                    <button class="tablinks" onclick="openTab(event, 'User')" id="defaultOpen">Новый пользователь</button>
                    <button class="tablinks" onclick="openTab(event, 'Group')">Новая группа</button>
                </div>

                <!-- Tab content -->
                <div id="User" class="tabcontent">
                    <form method="post" action="/user_registration" class="appointment-form ftco-animate" style="margin-top: 30px">
                        <div class="d-md-flex">
                            <input type="email" class="form-control" placeholder="Почта" name="email">
                        </div>
                        <div class="d-md-flex">
                            <input type="text" class="form-control" placeholder="ФИО" name="name">
                        </div>
                        <div class="d-md-flex">
                            <input type="password" class="form-control" placeholder="Пароль" name="password">
                        </div>
                        <div class="d-md-flex">
                            <select name="role" id="role" class="form-control">
                                <option value="" style="color: #1eaaf1" selected disabled>Выберите роль</option>
                                <option value="ADMIN" style="color: #1eaaf1">Администратор</option>
                                <option value="TEACHER" style="color: #1eaaf1">Преподаватель</option>
                            </select>
                        </div>
                        <div class="d-md-flex" style="margin-top: 20px;">
                            <div class="form-group">
                                <input type="submit" value="Добавить" class="btn btn-secondary py-3 px-4">
                            </div>
                        </div>
                    </form>
                </div>

                <div id="Group" class="tabcontent">
                    <form method="post" action="/group_registration" class="appointment-form ftco-animate" style="margin-top: 30px" enctype="multipart/form-data">
                        <div class="d-md-flex">
                            <input type="text" class="form-control" placeholder="Группа" name="group">
                        </div>
                        <div class="d-md-flex">
                            <select name="course" class="form-control">
                                <option value="" style="color: #1eaaf1" selected disabled>Выберите курс</option>
                                <option value="1" style="color: #1eaaf1">1 (бакалавриат)</option>
                                <option value="2" style="color: #1eaaf1">2 (бакалавриат)</option>
                                <option value="3" style="color: #1eaaf1">3 (бакалавриат)</option>
                                <option value="4" style="color: #1eaaf1">4 (бакалавриат)</option>
                                <option value="5" style="color: #1eaaf1">1 (магистратура)</option>
                                <option value="6" style="color: #1eaaf1">2 (магистратура)</option>
                            </select>
                        </div>
                        <div class="d-md-flex">
                            <select name="teacher" class="form-control">
                                <option value="" style="color: #1eaaf1" selected disabled>Выберите куратора</option>
                                <option value="1" style="color: #1eaaf1">Выберите куратора</option>
                            </select>
                        </div>
                        <div class="example-2" style="margin-top: 20px">
                            <div class="d-md-flex">
                                <input type="file" name="file" id="file" class="input-file" accept=".xlsx">
                                <label for="file" class="btn btn-tertiary js-labelFile">
                                    <i class="icon fa fa-check"></i>
                                    <span class="js-fileName">Загрузить список группы</span>
                                </label>
                            </div>
                        </div>
                        <div style="margin-top: 20px;">
                            <div class="form-group">
                                <input type="submit" value="Добавить" class="btn btn-secondary py-3 px-4">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<#include "parts/footer.ftlh">
</body>
</html>