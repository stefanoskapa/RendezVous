$(document).ready(function () {
    AOS.init({disable: 'mobile'});
    $('[data-bs-hover-animate]')
            .mouseenter(function () {
                var elem = $(this);
                elem.addClass('animated ' + elem.attr('data-bs-hover-animate'))
            })
            .mouseleave(function () {
                var elem = $(this);
                elem.removeClass('animated ' + elem.attr('data-bs-hover-animate'))
            });


    $("#calendar-logo, #navbar-brand1, #prem-logo").hover(
            function () {
                $("#calendar-logo").css("color", "#52a5bb");
                $("#navbar-brand1").css("color", "#52a5bb");
            },
            function () {
                $("#calendar-logo").css("color", "white");
                $("#navbar-brand1").css("color", "white");
            });
});