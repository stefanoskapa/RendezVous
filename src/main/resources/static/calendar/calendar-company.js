document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        // themeSystem: 'bootstrap',
        initialView: 'timeGridWeek',
        // initialDate: '2021-02-07',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        firstDay: 1,
        allDaySlot: false,
        slotDuration: '01:00:00',
        // scrollTime: '07:00:00',
        // slotLabelInterval: '03:00',
        expandRows: true,
        contentHeight: 1000,
        displayEventTime: false,
        // dateClick: function (info) {
        // alert('Clicked on: ' + info.dateStr);
        // alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
        // alert('Current view: ' + info.view.type);
        // change the day's background color just for fun
        // info.dayEl.style.backgroundColor = 'red';
        // },
        eventClick: function (info) {
            $(".modal-title").text(info.event.title);
            $(".modal-body p").html(
                    info.event.start +
                    "<br/> to <br/>" +
                    info.event.end +
                    info.event.extendedProps.tel +
                    "<br/>" +
                    info.event.extendedProps.email +
                    "<br/>" +
                    info.event.extendedProps.notes
                    );

            $('#myModal').modal('show');

            // alert('Event: ' + info.event.id);
            // alert('Event: ' + info.event.extendedProps.notes);

            // alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
            // alert('View: ' + info.view.type);

            // change the border color just for fun
            // info.el.style.borderColor = 'red';
        },
        // selectable: true,
        businessHours: {
            // days of week. an array of zero-based day of week integers (0=Sunday)
            daysOfWeek: [1, 2, 3, 4, 5], // Monday - Thursday

            startTime: '08:00', // a start time (10am in this example)
            endTime: '18:00', // an end time (6pm in this example)
        },
        slotMinTime: "08:00:00", //na to allazoume dinamika me vasi to orario
        slotMaxTime: "18:00:00", //na to allazoume dinamika me vasi to orario
        events: [
            {
                title: 'Πιπος',
                id: '222',
                start: '2021-03-03T10:00:00',
                end: '2021-03-03T11:00:00',
                extendedProps: {
                    notes: 'note about user',
                    tel: "210 11 22 333",
                    email: "a@b.com"
                }
            },
            {
                title: "Πιπης",
                id: '333',
                start: '2021-03-03T11:00:00',
                end: '2021-03-03T12:00:00',
                extendedProps: {
                    notes: 'note about user',
                    tel: "210 11 22 333",
                    email: "b@b.com"
                }
            },
            {
                title: 'Maria Maraki',
                start: '2021-03-04T15:00:00',
                end: '2021-03-04T16:00:00',
                extendedProps: {
                    notes: 'note about user',
                    tel: "210 11 22 333",
                    email: "c@b.com"
                }
            }
        ]
    });

    calendar.render();
});