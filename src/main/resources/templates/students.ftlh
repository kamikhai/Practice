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
<#--    <div class="overlay"></div>-->
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <h1 class="mb-2 bread">Наши студенты</h1>
            </div>
        </div>
    </div>
</section>

<!-- ##### Shop Grid Area Start ##### -->
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
                    </script>

                    <!-- ##### Single Widget ##### -->
                    <div class="widget catagory mb-50">
                        <!-- Widget Title -->
                        <h6 class="widget-title mb-30">Фильтры</h6>

                        <!--  Catagories  -->
                        <div class="catagories-menu" style="margin-top: 20px">
                            <ul id="menu-content2" class="menu-content">
                                <!-- Single Item -->
                                <li>
                                    <a data-toggle="collapse" href="#profiles">профиль работы</a>
                                    <ul id="profiles" class="sub-menu collapse">
                                        <#list profiles as profile>
                                            <li>
                                                <a href="?p=${profile.id}">${profile.title}</a>
                                            </li>
                                        </#list>
                                    </ul>
                                </li>
                                <!-- Single Item -->
                                <li>
                                    <a data-toggle="collapse" href="#tags">тэги</a>
                                    <ul id="tags" class="sub-menu collapse">
                                        <#list tags as tag>
                                            <li>
                                                <a href="?t=${tag.id}">${tag.name}</a>
                                            </li>
                                        </#list>
                                    </ul>
                                </li>
                                <!--li>
                                    <a data-toggle="collapse" href="#courses">курс</a>
                                    <ul id="courses" class="sub-menu collapse">
                                        <li><a>1 (бакалавриат)</a></li>
                                        <li><a>2 (бакалавриат)</a></li>
                                        <li><a>3 (бакалавриат)</a></li>
                                        <li><a>4 (бакалавриат)</a></li>
                                        <li><a>1 (магистратура)</a></li>
                                        <li><a>2 (магистратура)</a></li>
                                    </ul>
                                </li-->
                            </ul>
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
                                    <script>
                                        // ZALGO text script by tchouky
                                        // https://eeemo.net/

                                        const zalgo_up = [
                                            '\u030d', '\u030e', '\u0304', '\u0305',
                                            '\u033f', '\u0311', '\u0306', '\u0310',
                                            '\u0352', '\u0357', '\u0351', '\u0307',
                                            '\u0308', '\u030a', '\u0342', '\u0343',
                                            '\u0344', '\u034a', '\u034b', '\u034c',
                                            '\u0303', '\u0302', '\u030c', '\u0350',
                                            '\u0300', '\u0301', '\u030b', '\u030f',
                                            '\u0312', '\u0313', '\u0314', '\u033d',
                                            '\u0309', '\u0363', '\u0364', '\u0365',
                                            '\u0366', '\u0367', '\u0368', '\u0369',
                                            '\u036a', '\u036b', '\u036c', '\u036d',
                                            '\u036e', '\u036f', '\u033e', '\u035b',
                                            '\u0346', '\u031a'
                                        ];

                                        const zalgo_down = [
                                            '\u0316', '\u0317', '\u0318', '\u0319',
                                            '\u031c', '\u031d', '\u031e', '\u031f',
                                            '\u0320', '\u0324', '\u0325', '\u0326',
                                            '\u0329', '\u032a', '\u032b', '\u032c',
                                            '\u032d', '\u032e', '\u032f', '\u0330',
                                            '\u0331', '\u0332', '\u0333', '\u0339',
                                            '\u033a', '\u033b', '\u033c', '\u0345',
                                            '\u0347', '\u0348', '\u0349', '\u034d',
                                            '\u034e', '\u0353', '\u0354', '\u0355',
                                            '\u0356', '\u0359', '\u035a', '\u0323'
                                        ];

                                        const zalgo_mid = [
                                            '\u0315', '\u031b', '\u0340', '\u0341',
                                            '\u0358', '\u0321', '\u0322', '\u0327',
                                            '\u0328', '\u0334', '\u0335', '\u0336',
                                            '\u034f', '\u035c', '\u035d', '\u035e',
                                            '\u035f', '\u0360', '\u0362', '\u0338',
                                            '\u0337', '\u0361', '\u0489'
                                        ];

                                        //gets an int between 0 and max
                                        function rand(max) {
                                            return Math.floor(Math.random() * max);
                                        }

                                        //gets a random char from a zalgo char table
                                        function rand_zalgo(array) {
                                            const index = Math.floor(Math.random() * array.length);
                                            return array[index];
                                        }

                                        //lookup char to know if its a zalgo char or not
                                        function is_zalgo_char(c) {
                                            for(let i = 0; i < zalgo_up.length; i++)
                                                if(c === zalgo_up[i])
                                                    return true;

                                            for(let i = 0; i < zalgo_down.length; i++)
                                                if(c === zalgo_down[i])
                                                    return true;

                                            for(let i = 0; i < zalgo_mid.length; i++)
                                                if(c === zalgo_mid[i])
                                                    return true;

                                            return false;
                                        }

                                        // main shit
                                        function make_zalgo(text) {
                                            let newText = '';

                                            for(let i = 0; i < text.length; i++) {
                                                if(is_zalgo_char(text.substr(i, 1)))
                                                    continue;

                                                const num_up = rand(8);
                                                const num_mid = rand(2);
                                                const num_down = rand(8);

                                                //add the normal character
                                                newText += text.substr(i, 1);

                                                for(let j = 0; j < num_up; j++)
                                                    newText += rand_zalgo(zalgo_up);

                                                for(let j = 0; j < num_mid; j++)
                                                    newText += rand_zalgo(zalgo_mid);

                                                for(let j = 0; j < num_down; j++)
                                                    newText += rand_zalgo(zalgo_down);
                                            }

                                            return newText;
                                        }
                                    </script>
                                    <#list students as student>
                                        <div id="student-${student.id}" class="col-md-12 course d-lg-flex ftco-animate" style="margin-top: 20px; ">
                                            <div class="img" style="background-image: url(${student.imgUrl!"img/empty_user.jpg"});"></div>
                                            <div class="text bg-light p-4">
                                                <h3><a href="/profile/${student.id}">${student.fullName}</a></h3>
                                                <p id="student-${student.id}-course" class="subheading"></p>
                                                <script>
                                                    document.getElementById("student-${student.id}-course").innerHTML = make_zalgo("Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn");
                                                </script>
                                                <p>${student.description}</p>
                                                <#list student.tags as tag>
                                                    <span class="badge badge-pill badge-primary badge-tag" style="padding: 5px 7px;">${tag}</span>
                                                </#list>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <!-- Pagination (not done yet) -->
                <!--nav aria-label="navigation">
                    <ul class="pagination mt-50 mb-70">
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                    </ul>
                </nav-->
            </div>
        </div>
    </div>
</section>

<#include "parts/footer.ftlh">
</body>
</html>