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
        <!--<link rel="stylesheet" href="/css/search.css">-->
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

            #results-area{
                display: none;
            }

            .mycard {
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                transition: 0.3s;
                width: 90%;
                display: grid;
                grid-template-columns: 100px auto 200px;
                grid-template-rows: 1fr 1fr 1fr;
                gap: 3px 4px;
                grid-template-areas:
                    "img title link"
                    "img telephone link"
                    "img address link";
            }

            .mycard:hover {
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            }

            .mycard-img{
                grid-area: img;
                display: grid;
                align-items: center;
                justify-items: center;
            }
            .mycard-img img{
                width: 100px;
            }

            .mycard-title{
                grid-area: title;
            }

            .mycard-telephone{
                grid-area: telephone;
            }

            .mycard-adress{
                grid-area: address;
            }

            .mycard-link{
                grid-area: link;
                display: grid;
                align-items: center;
                justify-items: center;
            }

            @media only screen and (max-width: 800px) {
                .mycard-img{
                    display: none;
                }

                .mycard {
                    width: 90%;
                    grid-template-columns: 1fr;
                    grid-template-rows: 1fr 1fr 1fr;
                    gap: 3px 4px;
                    grid-template-areas:
                        "title"
                        "telephone"
                        "address"
                        "link";
                    padding: 10px 0;
                }
            }


        </style>
        <title>Search</title>
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



                <div class="row py-5 px-2 my-3 bg-white rounded shadow" id="results-area">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <p class="text-center" id="results-number"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="container" id="results">
                            </div>
                        </div>
                    </div>
                </div>
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
//            let resultTable = document.getElementById("resultTable");
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                $('*').css("cursor", "auto");
                let searchResults = JSON.parse(this.responseText);

                searchResults.sort(compare); //sorting based on display name
                
                $("#results-area").show();
                $("#results-number").text(searchResults.length+" results found")
                
                document.querySelector("#results").innerHTML = "";
                for (let i = 0; i < searchResults.length; i++) {
                    let card = '<div class="row justify-content-center"><div class="col-11 mycard my-1 rounded">' +
                            '<div class="mycard-img"><img src="/img/No_image_available.svg" alt="No-logo-available" /></div>' +
                            '<div class="mycard-title"><p>' + searchResults[i].displayName + '</p></div>' +
                            '<div class="mycard-telephone"><p>Tel:' + searchResults[i].tel + '</p></div>' +
                            '<div class="mycard-address"><p>' + searchResults[i].addrStr + ' ' + searchResults[i].addrNo + ', ' + searchResults[i].addrCity + '</p></div>' +
                            '<div class="mycard-link"><a class="btn btn-primary rounded-pill" href="' + full + '/client/date-select?companyId=' + searchResults[i].id + '">Close your appointment</a></div>' +
                            '</div></div>';

                    document.querySelector("#results").innerHTML += card;
                }
            }
        };
        xhttp.open("GET", full + "/api/v1/client/comp-search?searchTerm=" + searchTerm + "&category=" + document.getElementById("category").value, true);
        xhttp.send();
    })



</script>
</html>
