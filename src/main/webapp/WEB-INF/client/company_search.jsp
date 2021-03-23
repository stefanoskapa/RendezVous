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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
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
    <body onload="getCategories();">
        <!--URL: client/comp-select-->
        <h2>Company search page</h2>
        <br><br>
        <form>           
            <input type="text" name="searchTerm" id="searchbar"/>
            <select id="category">
                <option selected="selected" value="All">All Categories</option>
            </select>
            <input type="button" value="search" onclick="getResults();"/>
        </form>
        <br>
        <p id="matches"></p>
        <table id="resultTable"></table>
        <script>
            function getCategories() {
                
                var xhttp = new XMLHttpRequest();
                 xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {                   
                        let categories = JSON.parse(this.responseText);
                        let catSelect = document.getElementById("category");                  
                        for (let i = 0; i < categories.length; i++) {                               
                                    let opt = document.createElement("option");
                                     opt.value= categories[i];                                    
                                     opt.appendChild(document.createTextNode(categories[i]));
                                    catSelect.appendChild(opt);
                        }
                    } 
                };
                xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/client/categories");
                xhttp.send();              
            }
            
            function getResults() {
                $('*').css("cursor", "wait");

                let searchTerm = document.getElementById("searchbar").value;
                let resultTable = document.getElementById("resultTable");
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        $('*').css("cursor", "auto");
                        let searchResults = JSON.parse(this.responseText);
                        document.getElementById("matches").innerHTML = searchResults.length + " matches found";
                        if (searchResults.length > 0) {
                            document.getElementById("resultTable").innerHTML =
                                    "<tr><th>Company name</th><th>Address</th>" +
                                    "<th>City</th><th>Telephone</th><th></th></tr>";
                        } else {
                            document.getElementById("resultTable").innerHTML="";
                        }
                        for (let i = 0; i < searchResults.length; i++) {
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
                xhttp.open("GET", "http://localhost:8080/rendezvous/api/v1/client/comp-search?searchTerm=" + searchTerm +"&category="+document.getElementById("category").value, true);
                xhttp.send();
            }

        </script>

    </body>
</html>
