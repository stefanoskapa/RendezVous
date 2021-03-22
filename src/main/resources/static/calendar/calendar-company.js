document.addEventListener('DOMContentLoaded', function () {
    var calendarData;
    
    document.getElementById('calendar').innerHTML = "Loading Calendar Data";
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            calendarData = JSON.parse(this.responseText);
            if (calendarData.businessHours.length==0) {
                calendarData.businessHours = [{daysOfWeek: 1, startTime: "00:00:00", endTime: "00:00:00"}]
            }
            drawCalendar(calendarData);
            console.log(calendarData.businessHours);
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/company/dates", true);
    xhttp.send();

    function drawCalendar(calendarData) {
        var calendarEl = document.getElementById('calendar');
        calendarEl.innerHTML = "";
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
            timeZone: 'Europe/Athens',
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
            businessHours: calendarData.businessHours,
//            slotMinTime: "08:00:00", //na to allazoume dinamika me vasi to orario
//            slotMaxTime: "18:00:00", //na to allazoume dinamika me vasi to orario
            events: calendarData.events
        });

        calendar.render();
    }
});
