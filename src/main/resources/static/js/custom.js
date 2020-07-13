function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");

    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");

    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";

    evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click();

(function () {
    "use strict";

    $(".input-file").each(function () {
        var $input = $(this),
            $label = $input.next(".js-labelFile"),
            labelVal = $label.html();

        $input.on("change", function (element) {
            var fileName = "";
            if (element.target.value) fileName = element.target.value.split("\\").pop();
            fileName ? $label.addClass("has-file").find(".js-fileName").html(fileName) : $label.removeClass("has-file").html(labelVal);
        });
    });
})();
