$(document).ready(function () {
    $("input[type='time']").each(function () {
        if ($(this).val() == "") {
            $(this).prop("readonly", true);
            $(this).closest(".row").find("input[type='checkbox']").prop('checked', true);
        }
    });

    $("input[type='checkbox']").change(function () {
        if (this.checked) {
            $(this).closest(".row").find("input[type='time']").prop("readonly", true).val(null);
        } else {
            $(this).closest(".row").find("input[type='time']").prop("readonly", false).val("00:00");
        }
    });

});