<%-- 
    Document   : business_hours
    Created on : Mar 8, 2021, 3:36:25 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--URL: company/business-hours-->    
        company working hours <br/>

        <!--${weekHours.week['day1'].startTime}-->
        <!--${weekHours.week['day1'].closeTime}-->

        <form:form modelAttribute="weekHours" >
            <fieldset>
                <legend>Monday </legend>
                <form:label path="week['day1'].startTime">Opening time</form:label>
                <form:input path="week['day1'].startTime" type="time" />
                <br/>
                <form:label path="week['day1'].closeTime">Closing time</form:label>
                <form:input path="week['day1'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Tuesday </legend>
                <form:label path="week['day2'].startTime">Opening time</form:label>
                <form:input path="week['day2'].startTime" type="time" />
                <br/>
                <form:label path="week['day2'].closeTime">Closing time</form:label>
                <form:input path="week['day2'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Wednesday </legend>
                <form:label path="week['day3'].startTime">Opening time</form:label>
                <form:input path="week['day3'].startTime" type="time" />
                <br/>
                <form:label path="week['day3'].closeTime">Closing time</form:label>
                <form:input path="week['day3'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Thursday </legend>
                <form:label path="week['day4'].startTime">Opening time</form:label>
                <form:input path="week['day4'].startTime" type="time" />
                <br/>
                <form:label path="week['day4'].closeTime">Closing time</form:label>
                <form:input path="week['day4'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Friday </legend>
                <form:label path="week['day5'].startTime">Opening time</form:label>
                <form:input path="week['day5'].startTime" type="time" />
                <br/>
                <form:label path="week['day5'].closeTime">Closing time</form:label>
                <form:input path="week['day5'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Saturday </legend>
                <form:label path="week['day6'].startTime">Opening time</form:label>
                <form:input path="week['day6'].startTime" type="time" />
                <br/>
                <form:label path="week['day6'].closeTime">Closing time</form:label>
                <form:input path="week['day6'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <fieldset>
                <legend>Sunday  </legend>
                <form:label path="week['day7'].startTime">Opening time</form:label>
                <form:input path="week['day7'].startTime" type="time" />
                <br/>
                <form:label path="week['day7'].closeTime">Closing time</form:label>
                <form:input path="week['day7'].closeTime" type="time"/>
                <br/>
                <input type="checkbox" id="closedAllDay" ><label for="closedAllDay">Closed all Day</label>
            </fieldset>
            <input type="submit" value="Submit">

        </form:form>

    </body>
</html>
