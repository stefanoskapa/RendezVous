<%-- 
    Document   : business_hours
    Created on : Mar 8, 2021, 3:36:25 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    </head>
    <body>
        <!--URL: company/business-hours-->    
        company working hours <br/>

    <c:if test="${IncorrectWorkingHours != null}">
        <div>
            ${IncorrectWorkingHours}
        </div>
    </c:if>
        
    <form:form modelAttribute="weekHours" action="${pageContext.request.contextPath}/company/business-hours" 
               method="post" >
        <fieldset>
            <legend>Monday </legend>
            <form:label path="week['1'].startTime">Opening time</form:label>
            <form:input path="week['1'].startTime" type="time" />
            <br/>
            <form:label path="week['1'].closeTime">Closing time</form:label>
            <form:input path="week['1'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay1" ><label for="closedAllDay1">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Tuesday </legend>
            <form:label path="week['2'].startTime">Opening time</form:label>
            <form:input path="week['2'].startTime" type="time" />
            <br/>
            <form:label path="week['2'].closeTime">Closing time</form:label>
            <form:input path="week['2'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay2" ><label for="closedAllDay2">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Wednesday </legend>
            <form:label path="week['3'].startTime">Opening time</form:label>
            <form:input path="week['3'].startTime" type="time" />
            <br/>
            <form:label path="week['3'].closeTime">Closing time</form:label>
            <form:input path="week['3'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay3" ><label for="closedAllDay3">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Thursday </legend>
            <form:label path="week['4'].startTime">Opening time</form:label>
            <form:input path="week['4'].startTime" type="time" />
            <br/>
            <form:label path="week['4'].closeTime">Closing time</form:label>
            <form:input path="week['4'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay4" ><label for="closedAllDay4">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Friday </legend>
            <form:label path="week['5'].startTime">Opening time</form:label>
            <form:input path="week['5'].startTime" type="time" />
            <br/>
            <form:label path="week['5'].closeTime">Closing time</form:label>
            <form:input path="week['5'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay5" ><label for="closedAllDay5">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Saturday </legend>
            <form:label path="week['6'].startTime">Opening time</form:label>
            <form:input path="week['6'].startTime" type="time" />
            <br/>
            <form:label path="week['6'].closeTime">Closing time</form:label>
            <form:input path="week['6'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay6" ><label for="closedAllDay6">Closed all Day</label>
        </fieldset>
        <fieldset>
            <legend>Sunday  </legend>
            <form:label path="week['0'].startTime">Opening time</form:label>
            <form:input path="week['0'].startTime" type="time" />
            <br/>
            <form:label path="week['0'].closeTime">Closing time</form:label>
            <form:input path="week['0'].closeTime" type="time"/>
            <br/>
            <input type="checkbox" id="closedAllDay7" ><label for="closedAllDay7">Closed all Day</label>
        </fieldset>
        <input type="submit" value="Submit">

    </form:form>

    <script>
        $(document).ready(function () {
            $("input[type='time']").each(function () {
                if ($(this).val() == "") {
                    $(this).prop("readonly", true);
                    $(this).siblings("input[type='checkbox']").prop('checked', true);
                }
            });
        });

        $("input[type='checkbox']").change(function () {
            if (this.checked) {
                $(this).siblings("input[type='time']").prop("readonly", true).val(null);
            } else {
                $(this).siblings("input[type='time']").prop("readonly", false).val("00:00");
            }
        });
    </script>

</body>
</html>
