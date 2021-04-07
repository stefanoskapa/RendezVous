$("span").css({"display": "none"});

$( "span[id$=errors]" ).each(function() {
  let msg = $(this).html().replace("<br>", ", ");
  $(this).parent().popover({
        content: msg
    }).popover('show');
});

