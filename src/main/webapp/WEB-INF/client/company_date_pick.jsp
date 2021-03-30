<%-- 
    Document   : company_date_pick
    Created on : Mar 8, 2021, 10:12:42 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Availability</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
        <link href='${pageContext.request.contextPath}/calendar/lib/main.css' rel='stylesheet' />
        <script src='${pageContext.request.contextPath}/calendar/lib/main.js'></script>
        <script src="${pageContext.request.contextPath}/calendar/calendar-date-pick.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
         <link rel="stylesheet" href="/chat/styles.css">
        <style>
            .fc-timegrid-slot:hover {
                cursor: pointer;
            }
            #calendar .fc-non-business {
                background: #706b6b56;
            }
        </style>
    </head>
    <body>
        <h1>Pick a date for your appointment</h1>
        <h2>${comp_name}</h2>
        <div class="alert alert-dismissible fade show" role="alert" id="alert">
        </div>

        <input type="text" id="comp-id" value="${comp_id}" hidden  />

        <div id='calendar'></div>

        <!-- Modal content-->
        <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Modal title</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                    </div>

                    <div class="modal-body">
                        <p>One fine body&hellip;</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="cancelModalBtn" data-dismiss="modal">Cancel</button>
                        <form>
                            <input type="text" hidden id="hdate" value="">
                            <button type="button" class="btn btn-primary" id="submitDateToServer" data-dismiss="modal">
                                Confirm
                            </button>
                        </form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <!-- Chat starts here -->

        <div id='whatsapp-chat' class='hide'>
            <div class='header-chat'>
                <div class='head-home'><h3>Hello!</h3>
                    <p>Click one of your established conversations</p></div>
                <div class='get-new hide'><div id='get-label'></div><div id='get-nama'></div></div></div>
            <div class='home-chat'>
                <!-- Active conversations -->                
                </div>
            <div class='start-chat hide'>
                <div id="msgframe" style="overflow-y: scroll; height:300px;">
                    <!-- messages go here -->
                </div>
                <div class='blanter-msg'><textarea id='chat-input' placeholder='Write a response' maxlength='120' row='1'></textarea>
                    <a href='javascript:void;' id='send-it'>Send</a></div></div>
            <div id='get-number'></div><a class='close-chat' href='javascript:void'>×</a>
        </div>
        <a class='blantershow-chat' href='javascript:void' title='Show Chat'>Ask us a question!</a>
        <script src="${pageContext.request.contextPath}/chat/chat.js"></script>
        <!-- Chat ends here -->

    </body>
</html>
