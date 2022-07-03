function copytable(el) {
    var urlField = document.getElementById(el)
    var range = document.createRange()
    range.selectNode(urlField)
    window.getSelection().addRange(range)
    document.execCommand('copy')
    window.getSelection().removeAllRanges()
}