<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Reference Bootstrap files -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Search</title>
        <style>
            th, td {
                border: 1px solid black;
            }

            table {
                border-collapse: collapse;
                border: 1px solid black;
            }

            body {
                display: grid;
                min-height: 100vh;
                height: 100%;
                grid-template-rows: auto 1fr auto;
            }

            main{
                background-color: #2f3438;
            }
        </style>
    </head>
    <body>
        <header>
            <%--    <jsp:include page="navbars/loginNavbar.jsp"/>--%>
        </header>

        <main>
            <div class="container">
                <div class="row ">
                    <div class="col-12 p-5 mx-auto text-white text-center">
                        <h1>Find the company you are interested in closing an appointment</h1>
                    </div>
                </div>

                <div class="row py-5 px-2 bg-white rounded shadow">
                    
                    <div class="col-12 col-md-2 px-1">
                        <select class="form-control custom-select my-2" id="category">
                            <option selected="selected" value="All">All Categories</option>
                        </select>
                    </div>

                    <div class="col-12 col-md-8 px-1">
                        <input type="text" id="searchbar" class="form-control my-2" placeholder="Search for..." >
                    </div>

                    <div class="col-12 col-md-2 px-1 d-flex justify-content-center">
                        <input type="button" class="btn btn-success rounded w-75 h-100" value="search" id="srcBtn"/>
                    </div>

                </div>



                <div class="row">
                    <div class="col-12 p-5 mx-auto text-white text-center">
                        <p id="matches"></p>
                        <table id="resultTable"></table>
                    </div>
                </div>

            </div>

        </main>

        <footer>
            <jsp:include page="../navbars/footer.jsp"/>
        </footer>
    </body>
    <script>
        var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');

        window.onload = getCategories;

        function getCategories() {

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let categories = JSON.parse(this.responseText);
                    categories.sort();
                    let catSelect = document.getElementById("category");
                    for (let i = 0; i < categories.length; i++) {
                        let opt = document.createElement("option");
                        opt.value = categories[i];
                        opt.appendChild(document.createTextNode(categories[i]));
                        catSelect.appendChild(opt);
                    }
                }
            };
            xhttp.open("GET", full + "/api/v1/client/categories");
            xhttp.send();
        }

        function compare(a, b) {
            if (a.displayName < b.displayName) {
                return -1;
            }
            if (a.displayName > b.displayName) {
                return 1;
            }
            return 0;
        }

        document.getElementById("searchbar").addEventListener("keyup", function (event) {
            if (event.keyCode === 13) {
                event.preventDefault();
                document.getElementById("srcBtn").click();
            }
        });

        document.getElementById("srcBtn").addEventListener("click", function () {
            $('*').css("cursor", "wait");

            let searchTerm = document.getElementById("searchbar").value;
            let resultTable = document.getElementById("resultTable");
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    $('*').css("cursor", "auto");
                    let searchResults = JSON.parse(this.responseText);

                    searchResults.sort(compare); //sorting based on display name

                    document.getElementById("matches").innerHTML = searchResults.length + " matches found";
                    if (searchResults.length > 0) {
                        document.getElementById("resultTable").innerHTML =
                                "<tr><th>Company name</th><th>Address</th>" +
                                "<th>City</th><th>Telephone</th><th></th></tr>";
                    } else {
                        document.getElementById("resultTable").innerHTML = "";
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
                        cell4.innerHTML = "<a href='" + full + "/client/date-select?companyId=" + searchResults[i].id + "'>CHECK</a>";

                    }
                }
            };
            xhttp.open("GET", full + "/api/v1/client/comp-search?searchTerm=" + searchTerm + "&category=" + document.getElementById("category").value, true);
            xhttp.send();
        })



    </script>
</html>
