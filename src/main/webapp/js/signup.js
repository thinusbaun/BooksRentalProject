/**
 * Created by michal on 26.10.15.
 */
jQuery(function () {
    var signUpSuccessfull = $("body").attr("data-signupsuccessfull");
    if (signUpSuccessfull == "false") {
        $("#logindiv").addClass("has-error");
        $("#loginexists").removeClass("hidden");
    }
})
jQuery($('#signupform').on('input', function () {

    console.log(imieInput);
    if (imieInput.value.length == 0) {
        $('#imieEmpty').removeClass('hidden');
    } else {
        $('#imieEmpty').addClass('hidden');
    }

    if (imieInput.value.length > 44) {
        $('#imieTooLong').removeClass('hidden');
    } else {
        $('#imieTooLong').addClass('hidden');
    }

    var nazwiskoInput = $('#nazwiskoInput')[0]
    if (nazwiskoInput.value.length == 0) {
        $('#nazwiskoEmpty').removeClass('hidden');
    } else {
        $('#nazwiskoEmpty').addClass('hidden');
    }

    if (nazwiskoInput.value.length > 44) {
        $('#nazwiskoTooLong').removeClass('hidden');
    } else {
        $('#nazwiskoTooLong').addClass('hidden');
    }

    var loginInput = $('#loginInput')[0]
    if (loginInput.value.length == 0) {
        $('#loginEmpty').removeClass('hidden');
    } else {
        $('#loginEmpty').addClass('hidden');
    }

    if (loginInput.value.length > 44) {
        $('#loginTooLong').removeClass('hidden');
    } else {
        $('#loginTooLong').addClass('hidden');
    }

    var hasloInput = $('#hasloInput')[0]
    if (hasloInput.value.length == 0) {
        $('#hasloEmpty').removeClass('hidden');
    } else {
        $('#hasloEmpty').addClass('hidden');
    }

    if (hasloInput.value.length > 44) {
        $('#hasloTooLong').removeClass('hidden');
    } else {
        $('#hasloTooLong').addClass('hidden');
    }

    var emailInput = $('#emailInput')[0]
    if (emailInput.value.length == 0) {
        $('#emailEmpty').removeClass('hidden');
    } else {
        $('#emailEmpty').addClass('hidden');
    }

    if (emailInput.value.length > 44) {
        $('#emailTooLong').removeClass('hidden');
    } else {
        $('#emailTooLong').addClass('hidden');
    }
    var pattern = new RegExp('[a-zA-Z\.+%-]+@[a-zA-Z+%-]+\.[a-zA-Z]+');
    if (!pattern.test(emailInput.value)) {
        $('#emailDoesNotMatch').removeClass('hidden');
    } else {
        $('#emailDoesNotMatch').addClass('hidden');
    }
}))