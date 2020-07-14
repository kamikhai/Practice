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
<section class="ftco-section ftco-consult ftco-no-pt ftco-no-pb" style="background-image: url(img/bg_3.jpg); "
         data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 py-5 px-md-5 bg-primary" style="margin-top: 100px; margin-bottom: 100px">
                <div class="heading-section heading-section-white ftco-animate mb-5">
                    <h2 class="mb-4">Войти</h2>
                    <p>Если у вас нет почты и пароля, обратитесь в деканат</p>
                </div>
                <form class="appointment-form ftco-animate" method="post">
                    <div class="d-md-flex">
                        <input type="email" name="email" class="form-control" placeholder="Почта" >
                    </div>
                    <div class="d-md-flex">
                        <input type="password" name="password" class="form-control" placeholder="Пароль">
                    </div>
                    <input type="hidden" name="_csrf" value="{_csrf.token}">
                    <div class="d-md-flex" style="margin-top: 20px;">
                        <div class="form-group">
                            <input type="submit" value="Войти" class="btn btn-secondary py-3 px-4">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<#include "parts/footer.ftlh">
</body>
</html>