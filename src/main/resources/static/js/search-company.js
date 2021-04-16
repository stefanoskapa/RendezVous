$(document).ready(function () {
    var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');   
  
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
        $('main *').css("cursor", "wait");
        let searchTerm = document.getElementById("searchbar").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                $('main *').css("cursor", "auto");
                let searchResults = JSON.parse(this.responseText);
                searchResults.sort(compare); //sorting based on display name

                $("#results-area").show();
                $("#results-number").text(searchResults.length + " results found")

                var resultsData = [];
                for (let i = 0; i < searchResults.length; i++) {
                    let card = '<div class="row justify-content-center"><div class="col-11 mycard my-1 rounded bg-white">' +
                            '<div class="mycard-img"><img src="https://eu.ui-avatars.com/api/?name='+searchResults[i].displayName+'" alt="No-logo-available" /></div>' +
                            '<div class="mycard-title"><p><b>' + searchResults[i].displayName + '</b></p></div>' +
                            '<div class="mycard-telephone"><p class="m-0">Tel: <a href="tel:' + searchResults[i].tel + '">' + searchResults[i].tel + '</a></p></div > ' +
                            '<div class="mycard-address"><p class="m-0">' + searchResults[i].addrStr + ' ' + searchResults[i].addrNo + ', ' + searchResults[i].addrCity + '</p></div>' +
                            '<div class="mycard-link"><a class="btn btn-primary rounded-pill" href="' + full + '/client/date-select?companyId=' + searchResults[i].id + '">Book appointment</a></div>' +
                            '</div></div>';
                    resultsData.push(card);

                }
                var container = $('#pagination');
                container.pagination({
                    dataSource: resultsData,
                    totalNumber: 50,
                    pageSize: 3,
                    showPageNumbers: true,
                    showPrevious: true,
                    showNext: true,
                    showFirstOnEllipsisShow: true,
                    showLastOnEllipsisShow: true,
                    className: 'paginationjs-theme-blue paginationjs-big',
                    callback: function (response, pagination) {
                        var dataHtml = '';
                        $.each(response, function (index, item) {
                            dataHtml += item;
                        });

                        $('#results').html(dataHtml);
                    }
                })
            }
        };
        xhttp.open("GET", full + "/api/v1/client/comp-search?searchTerm=" + searchTerm + "&category=" + document.getElementById("category").value+"&city=" +document.getElementById("city").value , true);
        xhttp.send();
    })

  $("#searchInfo").popover({
        content: "Your search can be based on the company name or the telephone number",
        trigger: 'hover'
    });

   
});



