$(document).ready(function () {
    var searchEntries = Cookies.getJSON('searchEntries');
    $('#autocomplete').autocomplete(
        {
            source: searchEntries
        }
    )
});
function addSearchEntryToCookie() {
    var searchEntries = Cookies.getJSON('searchEntries');
    if (typeof searchEntries != 'undefined') {
        searchEntries.append($('#autocomplete').val());
    } else {
        searchEntries = [$('#autocomplete').val()];
    }
    Cookies.set('searchEntries', searchEntries);
}