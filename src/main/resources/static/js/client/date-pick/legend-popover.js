$("document").ready(function () {
    $("#legendInfo").popover({
        html: true,
        content: '<div class="container">' +
                '<div class="row">' +
                '<div class="col-1 px-0 d-flex align-items-center">' +
                '<div id="slot-available" class="legend-box mr-1"></div>' +
                '</div>' +
                '<div class="col-11 px-0">' +
                '<p class="my-1 my-md-0">Available for booking</p>' +
                '</div>' +
                '</div>' +
                '<div class="row">' +
                '<div class="col-1 px-0 d-flex align-items-center">' +
                '<div id="slot-existing-with-comp" class="legend-box mr-1"></div>' +
                '</div>' +
                '<div class="col-11 px-0">' +
                '<p class="my-1 my-md-0">Appointment with the company already booked</p>' +
                '</div>' +
                '</div>' +
                '<div class="row">' +
                '<div class="col-1 px-0 d-flex align-items-center">' +
                '<div id="slot-existing-with-other-comp" class="legend-box mr-1"></div>' +
                '</div>' +
                '<div class="col-11 px-0">' +
                '<p class="my-1 my-md-0">Appointment with another company booked</p>' +
                '</div>' +
                '</div>' +
                '<div class="row">' +
                '<div class="col-1 px-0 d-flex align-items-center">' +
                '<div id="slot-unavailable" class="legend-box mr-1"></div>' +
                '</div>' +
                '<div class="col-11 px-0">' +
                '<p class="my-1 my-md-0">Company is unavailable on the specific slot</p>' +
                '</div>' +
                '</div>' +
                '<div class="row">' +
                '<div class="col-1 px-0 d-flex align-items-center">' +
                '<div id="slot-of-working-hours" class="legend-box mr-1"></div>' +
                '</div>' +
                '<div class="col-11 px-0">' +
                '<p class="my-1 my-md-0">Slot belongs outside company`s opening hours</p>' +
                '</div>' +
                '</div>' +
                '</div>',
        trigger: 'hover'
    });
});