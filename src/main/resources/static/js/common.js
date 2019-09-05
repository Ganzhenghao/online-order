var search_btn = document.getElementById("search_btn");
var search = document.getElementById("search")
search_btn.onclick = function() {
    if (search.value != undefined && search.value != null && search.value != "") {
        window.location.href = "/user/search/" + search.value + "/1/10";
    }
}