document.addEventListener('DOMContentLoaded', function () {
    var calendarData;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            calendarData = JSON.parse(this.responseText);
            drawCalendar(calendarData);
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/company/dates", true);
    xhttp.send();

    function drawCalendar(calendarData) {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            // themeSystem: 'bootstrap',
            initialView: 'timeGridWeek',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            firstDay: 1,
            allDaySlot: false,
            slotDuration: '01:00:00',
            // scrollTime: '07:00:00',
            expandRows: true,
            contentHeight: 1000,
            displayEventTime: false,
            eventClick: function (info) {
                $(".modal-title").text(info.event.title);
                $(".modal-body p").html(
                        info.event.start.toLocaleTimeString() +
                        " - " +
                        info.event.end.toLocaleTimeString() +
                        "<br/><br/>Tel.:" +
                        info.event.extendedProps.tel
                        );

                $('#myModal').modal('show');
            },
            // selectable: true,
            businessHours: {
                // days of week. an array of zero-based day of week integers (0=Sunday)
                daysOfWeek: [1, 2, 3, 4, 5], // Monday - Thursday

                startTime: '08:00', // a start time (10am in this example)
                endTime: '18:00', // an end time (6pm in this example)
            },
//            slotMinTime: "08:00:00", //na to allazoume dinamika me vasi to orario
//            slotMaxTime: "18:00:00", //na to allazoume dinamika me vasi to orario
            events: calendarData
        });

        calendar.render();
    }
});
