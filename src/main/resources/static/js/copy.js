function copytable(el, idMessage, status) {
    var urlField = document.getElementById(el);
    var range = document.createRange();
    range.selectNode(urlField);
    window.getSelection().addRange(range);
    document.execCommand('copy');
    window.getSelection().removeAllRanges();

    setMessage(idMessage, status);
}




function setMessage(idmsg, status){
//    var span = document.getElementById(idmsg);
//    span.innerHTML = "таблиця скопійована в буфер";
    document.getElementById(idmsg).disabled=status;
//console.log(idmsg);
//console.log(1);
//window.alert(idmsg);
//window.alert("таблиця скопійована");

//    span.textContent="таблиця скопійована в буфер";
//    span.innerText = "таблиця скопійована в буфер"
//    span.outerText = "таблиця скопійована в буфер"
//    span.style.color = "#FF0000"
//txt=document.createTextNode("таблиця скопійована в буфер")
//    span.appendChild(txt)

}