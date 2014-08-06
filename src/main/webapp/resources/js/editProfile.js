function passwordsMatch() {
    return $('#inputNewPassword').val()!==""?$('#inputNewPassword').val() === $('#inputRetypePassword').val():true;
}

function createEditData(){
    var serialized = $("#editForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item){
        user[item.name] = item.value;
    });
    
    return user;
}

$(document).ready(function() {
    var submitButton = $("#submitButton");
    var cancelButton = $("#cancelButton");
    
    submitButton.click(function(event) {
        event.preventDefault();
        $(".errors").empty();
        
        if(passwordsMatch()) {
            $("#passwordMatchError").attr("class", "hidden");
            
            var editData = createEditData();
            
            issueTrackerService.edit(editData).done(function(data) {
                if (data.success) {
                    window.location.replace("/profile/"+data.username);
                }
                else {
                   $.each(data.errors, function(key, value) {
                        $("#" + key + "Error").append(value);
                    }) 
                };
            });
        }
    });
    
    
    cancelButton.click( function(event) {
        event.preventDefault();
        window.location.replace("/settings");
    });
    
    $('#inputUsername').on('blur', function() {
        var username = $(this).val();
        var hasErrors = false;
        setTimeout(function() {
            $('#usernameError').text('');
            if (!registerValidationService.validateUsername(username)) {
                $('#usernameError').append('Username must contain between 5 and 20 characters.\n    ');
                hasErrors = true;
            } else {
                $('#usernameError').text('');
            }
            if (!registerValidationService.validateAlphanumeric(username)) {
                $('#usernameError').append('Only alphanumeric characters are accepted.');
                hasErrors = true;
            }
            if (hasErrors)
                return;
            if (username.length === 0) 
                $('#usernameError').text('');
        }, 150);
    });
    $('#inputName').on('blur', function() {
        var name = $(this).val();
        setTimeout(function() {
            if (!registerValidationService.validateName(name)) {
                $('#nameError').text('Name must contain between 5 and 60 characters');
            } else {
                $('#nameError').text('');
            }
        }, 150);
    });

    $('#inputEmail').on('blur', function() {
        var email = $(this).val();
        setTimeout(function() {
            if (!registerValidationService.validateEmail(email)) {
                $('#emailError').text('Not a well-formed email adress.');
            } else {
                $('#emailError').text('');
            }
        }, 150);
    });
    $('#inputNewPassword').on("blur", function() {
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
    });
     $('#inputRetypePassword').on("input", function() {
        var password1 = $(this).val();
        var password2 = $('#inputNewPassword').val();

        setTimeout(function() {
            if (!registerValidationService.passwordsMatch(password1, password2)) {
                $('#passwordError').text('Passwords don\'t match.');
            } else {
                $('#passwordError').text('');
            }
        }, 150);
    });
    
});


