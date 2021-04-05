document.addEventListener('DOMContentLoaded', function () {
    var calendarData;
    var calendar;
    var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

    document.getElementById('calendar').innerHTML = "Loading Calendar Data";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            calendarData = JSON.parse(this.responseText);
            drawCalendar();
        }
    };
    xhttp.open("GET", full + "/api/v1/client/dates", true);
    xhttp.send();

    function drawCalendar() {
        var calendarEl = document.getElementById('calendar');
        calendarEl.innerHTML = "";
        calendar = new FullCalendar.Calendar(calendarEl, {
//            initialView: 'timeGridWeek',
            initialView: $(window).width() < 765 ? 'timeGridDay' : 'timeGridWeek',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: $(window).width() < 765 ? '' : 'dayGridMonth,timeGridWeek',
            },
            firstDay: 1,
            allDaySlot: false,
            slotDuration: '01:00:00',
//             scrollTime: '07:00:00',
            // slotLabelInterval: '03:00',
            expandRows: true,
            contentHeight: 1000,
            displayEventTime: false,
            timeZone: 'Europe/Athens',
            eventClick: function (info) {
                let startTime = new Date(info.event.start);
                startTime.setHours(startTime.getHours() + (startTime.getTimezoneOffset() / 60));
                let endTime = new Date(info.event.end);
                endTime.setHours(endTime.getHours() + (endTime.getTimezoneOffset() / 60));

                $(".modal-title").text(info.event.title);
                $(".modal-body p").html(
                        startTime.toLocaleTimeString() +
                        " - " +
                        endTime.toLocaleTimeString() +
                        "<br/><br/>" +
                        info.event.extendedProps.addr_str +
                        " " +
                        info.event.extendedProps.addr_no +
                        "<br/>" +
                        info.event.extendedProps.addr_city +
                        "<br/>" +
                        info.event.extendedProps.tel
                        );

                $('#myModal').modal('show');
            },
            eventContent: function (arg) {
                let msg = arg.event.title
                return msg
            },
            events: calendarData
        });

        calendar.render();
    }


    $(window).on("orientationchange", function (event) {
        setTimeout(drawCalendar, 100);
    });
});