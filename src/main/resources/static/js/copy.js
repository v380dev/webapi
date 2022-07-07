function copyTable(idTable, idMessage) {
    var urlField = document.getElementById(idTable);
    var range = document.createRange();
    range.selectNode(urlField);
    window.getSelection().addRange(range);
    document.execCommand('copy');
    window.getSelection().removeAllRanges();

    showMessage(idMessage);
}

function showMessage(idMessage){
 var x = document.getElementById(idMessage);
  x.className = "show";
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
  }