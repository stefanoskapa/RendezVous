document.addEventListener('DOMContentLoaded', function () {
    var calendarData;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            calendarData = JSON.parse(this.responseText);
            drawCalendar(calendarData);
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/client/dates", true);
    xhttp.send();

    function drawCalendar(calendarData) {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'timeGridWeek',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
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
                $(".modal-title").text(info.event.title);
                $(".modal-body p").html(
                        info.event.start.toLocaleTimeString() +
                        " - " +
                        info.event.end.toLocaleTimeString() +
                        "<br/><br/>" +
                        info.event.extendedProps.addr_str +
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


});