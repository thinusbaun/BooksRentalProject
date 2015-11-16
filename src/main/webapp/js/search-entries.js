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
        searchEntries.push($('#autocomplete').val());
    } else {
        searchEntries = [$('#autocomplete').val()];
    }
    Cookies.set('searchEntries', searchEntries, {expires: 3650});
}