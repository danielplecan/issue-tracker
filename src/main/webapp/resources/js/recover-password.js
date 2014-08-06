$("#submitButton").click(function(event) {
    event.preventDefault();
    issueTrackerService.recoverPassword($('#inputUsername').val()).done(function(data) {
        if (data.success)
            window.location.replace("/recovery-message");
        else
            $('#usernameError').text("This username dosen\'t exist");
    });
});



