document.addEventListener('DOMContentLoaded', function () {
    $("#alert").hide();
    $("#confirmDeleteDate").hide();

    var calendarData;
    var filteredCalendarData;
    var calendar;
    var defDate;
    var initialView;
    var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

    drawCalendar();

    function drawCalendar() {
        getData(function () {
            defDate = new Date();

            initialView = 'timeGridWeek';
            defDate = new Date();

            initializeCalendar();
            $("#loading-container").hide();
            $("#calendar-container").fadeIn("slow");
            calendar.render();
        });
    }

    function getData(afterLoading) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                calendarData = JSON.parse(this.responseText);
                filteredCalendarData = calendarData;
                afterLoading();
            }
        };
        xhttp.open("GET", full + "/api/v1/client/dates", true);
        xhttp.send();
    }

    function initializeCalendar() {
        var calendarEl = document.getElementById('calendar');
        calendarEl.innerHTML = "";
        calendar = new FullCalendar.Calendar(calendarEl, {
            initialDate: defDate,
            initialView: $(window).width() < 765 ? 'timeGridDay' : initialView,
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: $(window).width() < 765 ? '' : 'dayGridMonth,timeGridWeek'
            },
            firstDay: 1,
            allDaySlot: false,
            slotDuration: '01:00:00',
//             scrollTime: '07:00:00',
            // slotLabelInterval: '03:00',
            expandRows: true,
            contentHeight: 1500,
            displayEventTime: false,
            timeZone: 'Europe/Athens',
            eventClick: function (info) {
                let startTime = new Date(info.event.start);
                startTime.setHours(startTime.getHours() + (startTime.getTimezoneOffset() / 60));
                let endTime = new Date(info.event.end);
                endTime.setHours(endTime.getHours() + (endTime.getTimezoneOffset() / 60));

                $("#hdate").val(info.event.start);

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
                $("#deleteDate").show();
                $("#confirmDeleteDate").hide();

                $('#myModal').modal('show');
            },
            eventContent: function (arg) {
                return {html: '<div class="row h-100"><p class="col-sm-12 my-auto text-center text-wrap">' + arg.event.title + '</p></div>'}
            },
            events: filteredCalendarData
        });
    }


    $(window).on("orientationchange", function (event) {
        setTimeout(function () {
            initializeCalendar();
            calendar.render();
        }, 200);
    });

    $("#search-by-name").keyup(filterEvents);
    $("#search-by-category").change(filterEvents);

    function filterEvents() {
        var searchText = $("#search-by-name").val();
        var searchCat = $("#search-by-category").val();
        filteredCalendarData = [];
        calendarData.forEach(element => {
            if (element.title.toLowerCase().includes(searchText.toLowerCase())) {
                if (searchCat == -1) {
                    filteredCalendarData.push(element);
                } else if (element.extendedProps.category == searchCat) {
                    filteredCalendarData.push(element);
                }
            }
        });
        defDate = calendar.currentData.currentDate
        initialView = calendar.currentData.currentViewType;

        initializeCalendar();
        calendar.render();
    }


    $("#deleteDate").click(function () {
        $(".modal-title").text("Appointment Deletion");
        $(".modal-body p").html("Are you sure?");
        $("#deleteDate").hide();
        $("#confirmDeleteDate").show();
    });

    $("#confirmDeleteDate").click(function () {
        $('html, body').css("cursor", "wait");
        var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

        $.ajax(full + "/api/v1/client/delete-app",
                {type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(new Date($("#hdate").val())),
                    success: function (data, status, xhr) {   // success callback function
                        $('html, body').css("cursor", "auto");
                        $('#alert').removeClass("alert-warning");
                        $('#alert').addClass("alert-success");

                        $('#alert').show();
                        $('#alert').html("Your appointment has been deleted");
                        
                        getData(function () {
                            defDate = calendar.currentData.currentDate

                            initializeCalendar();
                            $("#loading-container").hide();
                            $("#calendar-container").fadeIn("slow");
                            calendar.render();
                        });
                    },
                    error: function (jqXhr, textStatus, errorMessage) { // error callback 
                        $('html, body').css("cursor", "auto");
                        $('#alert').removeClass("alert-success");
                        $('#alert').addClass("alert-warning");

                        $('#alert').show();
                        $('#alert').html("Wrong request");
                    }
                });

        setTimeout(() => {
            $("#deleteDate").show();
        }, 500)
        setTimeout(() => {
            $("#confirmDeleteDate").hide()
        }, 500)
    })

});
