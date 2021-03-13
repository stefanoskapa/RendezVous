document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
//         themeSystem: 'vader',
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
                    "<br/><br/>" +
                    info.event.extendedProps.address +
                    "<br/>" +
                    info.event.extendedProps.tel +
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
        eventContent: function (arg) {
            let msg = arg.event.title
            return msg
        },
        events: [
            {
                title: 'Vodafone',
                id: '111',
                start: '2021-03-03T00:00:00',
                end: '2021-03-03T01:00:00',
                extendedProps: {
                    notes: 'my personal note voda',
                    tel: "210 11 22 333",
                    address: "ki  ki 5"
                }
            },
            {
                title: 'Vodafone',
                id: '222',
                start: '2021-03-03T10:00:00',
                end: '2021-03-03T11:00:00',
                extendedProps: {
                    notes: 'my personal note 2',
                    tel: "210 11 22 333",
                    address: "kou kou 5"
                }
            },
            {
                title: "Οδοντιατριο dr. Dontakias",
                id: '333',
                start: '2021-03-03T11:00:00',
                end: '2021-03-03T12:00:00',
                extendedProps: {
                    notes: 'my personal note 2',
                    tel: "210 11 22 333",
                    address: "fou fou 5"
                }
            },
            {
                title: 'Κουρειο "Η χρυση Ισιωτικη"',
                start: '2021-03-04T15:00:00',
                end: '2021-03-04T16:00:00',
                extendedProps: {
                    notes: 'my personal note 3',
                    tel: "210 11 22 333",
                    address: "Kolokotrooni 5"
                }
            }
        ]
    });

    calendar.render();
});