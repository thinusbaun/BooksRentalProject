/**
 * Created by michal on 04.11.15.
 */
$(document).ready(function () {
    $('a.close-admin-message').click(function (event) {
        var id = event.target.id.split('-')[event.target.id.split('-').length - 1];
        $.post("/listAdminMessages", {'closeMessageId': id});
    });
});