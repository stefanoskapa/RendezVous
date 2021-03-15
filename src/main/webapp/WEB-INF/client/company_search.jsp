<%-- 
    Document   : company_search
    Created on : Mar 8, 2021, 3:26:13 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            th, td {
  border: 1px solid black;
}
table {
  border-collapse: collapse;
  border: 1px solid black;
}
        </style>
    </head>
    <body>
        <!--URL: client/comp-select-->
        Company search page
        <!--forma anazitisis etairias-->
        <!--anazitisi me vasi to category-->
        <!--anazitisi me vasi to onoma/tilefono-->
        <!--o client epilegei etairia, kai me mia forma xtypao to back :POST client/comp-select, stelnontas to id tis eterias-->
        <br><br>
        <form>           
            <input type="text" name="searchTerm" id="searchbar"/>
            <input type="button" value="search" onclick="getResults();"/>
        </form>
        <br><br>
        <div>
            <table id="resultTable">
                <tr>
                <th>Company name</th>
                <th>Address</th>
                <th>City</th>
                <th>Telephone</th>
                <th></th>
                </tr>
                
            </table>
        </div>
        <script>
    function getResults() {
       
        let searchTerm = document.getElementById("searchbar").value;
        let resultTable = document.getElementById("resultTable");
        
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            searchResults = JSON.parse(this.responseText);
            for (let i=0; i<searchResults.length;i++) {
                var row = resultTable.insertRow(-1);
                cell0 = row.insertCell(0);
                cell1 = row.insertCell(1);
                cell2 = row.insertCell(2);
                cell3 = row.insertCell(3);
                cell4 = row.insertCell(4);
                cell0.innerHTML = searchResults[i].displayName;
                cell1.innerHTML = searchResults[i].addrStr + " " + searchResults[i].addrNo;
                cell2.innerHTML = searchResults[i].addrCity;
                cell3.innerHTML = searchResults[i].tel;
                cell4.innerHTML = "<a href='http://localhost:8080/rendezvous/client/date-select?companyId=" + searchResults[i].id + "'>CHECK</a>";
                
            }         
        }
    };
    xhttp.open("GET", "http://localhost:8080/rendezvous/client/comp-search?searchTerm="+searchTerm, true);
    xhttp.send();
}

        </script>
        
    </body>
</html>
