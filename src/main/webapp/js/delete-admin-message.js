/**
 * Created by michal on 04.11.15.
 */
$(document).ready(function () {
    $('a.removeMessage').click(function (event) {
        var id = event.target.id;
        $.post("/listAdminMessages", {'deleteMessageId': id});
        var rowId = '#' + id + '-row';
        $(rowId).remove();
    });
});