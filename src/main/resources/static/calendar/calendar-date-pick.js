document.addEventListener('DOMContentLoaded', function () {
    var calendarData;

    var comp_id = $("#comp-id").val();

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            calendarData = JSON.parse(this.responseText);

            console.log(calendarData);
            console.log(calendarData.businessHours);
            console.log(calendarData.blockDates);
            drawCalendar(calendarData);
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/client/company/" + comp_id + "/availability", true);
    xhttp.send();

    function drawCalendar(calendarData) {
        var calendarEl = document.getElementById('calendar');
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
            // dateClick: function (info) {
            // alert('Clicked on: ' + info.dateStr);
            // alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
            // alert('Current view: ' + info.view.type);
            // change the day's background color just for fun
            // info.dayEl.style.backgroundColor = 'red';
            // },
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
            // eventDisplay: 'block',
            eventContent: function (arg) {
                return arg.event.title;
            },
        });

        calendar.render();
    }

    $("#submitDateToServer").click(function () {
        //send to server the date(and the company)
        console.log("ajax to server");
        console.log($("#hdate").val());

        $.ajax("http://localhost:8080/rendezvous/api/v1/client/company/" + comp_id + "/date",
                {   type: 'POST', 
                    //todo send comp-id and datetime of the date
                    success: function (data, status, xhr) {   // success callback function
                        alert("ok");
//                        $('p').append(data.firstName + ' ' + data.middleName + ' ' + data.lastName);
                    },
                    error: function (jqXhr, textStatus, errorMessage) { // error callback 
                        alert("error");
//                        $('p').append('Error: ' + errorMessage);
                    }

                });

    });
});