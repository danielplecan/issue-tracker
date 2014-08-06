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
        else{
            $('#passwordError').text('Passwords don\'t match');
        }
    })
);


