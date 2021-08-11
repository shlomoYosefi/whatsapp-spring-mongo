
var xhttp = new XMLHttpRequest();

function routerFunctions(method, url, callBack) {
    xhttp.open(method, url, true);
    xhttp.send();
    console.log("flkgfklfklfkhlf");

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("request");
            if (callBack) {
                callBack(xhttp.responseText)
            }
        }
    };

}






