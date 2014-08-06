function passwordsMatch() {
    return $('#inputPassword').val() === $('#inputRetypePassword').val();
}

$(document).ready(
     $('#submitPassword').click(function(event){
        event.preventDefault();
        if (passwordsMatch()) {
            issueTrackerService.changePassword($('#inputPassword').val()).done(function(){
                window.location.replace("/login");
            });
        }
    }),
    $('#inputPassword').on('blur', function() {
        var password = $(this).val();
        setTimeout(function() {
            if (!registerValidationService.validatePassword(password)) {
                $('#mainPasswordError').text('Password must contain between 5 and 20 characters.');
            } else {
                $('#mainPasswordError').text('');
            }
            
            var password2 = $('#inputRetypePassword').val();
            if (!registerValidationService.passwordsMatch(password, password2)) {
                $('#passwordError').text('Passwords don\'t match.');
            } else {
                $('#passwordError').text('');
            }
        }, 150);
    }),
     $('#inputRetypePassword').on('input', function() {
        var password1 = $(this).val();
        var password2 = $('#inputPassword').val();

        setTimeout(function() {
            if (!registerValidationService.passwordsMatch(password1, password2)) {
                $('#passwordError').text('Passwords don\'t match.');
            } else {
                $('#passwordError').text('');
            }
        }, 150);
    })
);


