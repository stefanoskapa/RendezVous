document.addEventListener('DOMContentLoaded', function () {
    $("#alert").hide();
    document.getElementById('calendar').innerHTML = "Loading Calendar Data";
    getCalendarDataAndDrawCalendar();

    function getCalendarDataAndDrawCalendar() {
        var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
        var calendarData;
        var comp_id = $("#comp-id").val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                calendarData = JSON.parse(this.responseText);

                if (calendarData.businessHours.length == 0) {
                    calendarData.businessHours = [{daysOfWeek: 1, startTime: "00:00:00", endTime: "00:00:00"}]
                }
                console.log("RecievedfromServer>>>> ");
                console.log(calendarData);
                drawCalendar(calendarData);

            }
        };
        xhttp.open("GET", full + "/rendezvous/api/v1/client/company/" + comp_id + "/availability", true);
        xhttp.send();
    }

    function drawCalendar(calendarData) {
        var calendarEl = document.getElementById('calendar');
        calendarEl.innerHTML = "";
        var calendar = new FullCalendar.Calendar(calendarEl, {
            businessHours: calendarData.businessHours,
//                slotMinTime: x.slotMinTime,
//                slotMaxTime: x.slotMaxTime,
            events: calendarData.blockDates,
            // themeSystem: 'bootstrap',
            initialView: 'timeGridWeek',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: ''
            },
            firstDay: 1,
            allDaySlot: false,
            slotDuration: '01:00:00',
            // scrollTime: '07:00:00',
            expandRows: true,
            // contentHeight: 1000,
            // displayEventTime: false,
            selectConstraint: "businessHours",
            select: function (info) {
                $("#hdate").val(info.start);
                $(".modal-title").text("Confirmation");
                $(".modal-body p").html(
                        "Are you sure?" +
                        "<br/>" +
                        info.start
                        );
                $('#myModal').modal('show');
            },
            selectable: true,
            // eventContent: { html: '<p>Blocked</p>' },
            eventBackgroundColor: 'gray',
            eventBorderColor: 'gray',
            eventTextColor: 'white',
            timeZone: 'Europe/Athens',
            // eventDisplay: 'block',
            eventContent: function (arg) {
                return arg.event.title;
            },
        });
        calendar.render();
    }

    $("#submitDateToServer").click(function () {
        $('html, body').css("cursor", "wait");
        var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

        $.ajax(full + "/rendezvous/api/v1/client/request-app",
                {type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                            {
                                "companyId": $("#comp-id").val(),
                                "appointmentTimestamp": new Date($("#hdate").val())
                            }
                    ),
                    success: function (data, status, xhr) {   // success callback function
                        $('html, body').css("cursor", "auto");
                        $('#alert').removeClass("alert-warning");
                        $('#alert').addClass("alert-success");

                        $('#alert').show();
                        $('#alert').html("Your appointment has been successfully created")
                        getCalendarDataAndDrawCalendar();
                    },
                    error: function (jqXhr, textStatus, errorMessage) { // error callback 
                        $('html, body').css("cursor", "auto");
                        $('#alert').removeClass("alert-success");
                        $('#alert').addClass("alert-warning");

                        $('#alert').show();
                        $('#alert').html("Your appointment request could not be completed. Refresh the page and try again")
                    }
                });
    });
});