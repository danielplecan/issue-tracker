$("#submitButton").click(function(event) {
    event.preventDefault();
    issueTrackerService.recoverPassword($('#inputUsernameEmail').val()).done(function(data) {
        if (data.success)
            window.location.replace("/recovery-message");
        else
            $('#usernameEmailError').text("There is no user with the specified username or email");
    });
});



