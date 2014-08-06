
function clearForm(form) {
    $(form).find('input').each(function(index, element) {
        $(element).val('');
    });
    $(form).find('.errors').each(function(index, element) {
        $(element).text('');
    });
}

//FUNCTION FOR VALIDATION OF THE WHOLE FORM
function validateRegisterForm() {
    var username = $('#inputUsername').val().trim();
    var name = $('#inputName').val().trim();
    var email = $('#inputEmail').val().trim();
    var password1 = $('#inputPassword').val().trim();
    var password2 = $('#inputRetypePassword').val().trim();
    var service = registerValidationService;
    var hasErrors = false;


    if (!service.validateUsername(username)) {
        hasErrors = true;
    }
    if (!service.validateName(name)) {
        $('#nameError').text('Name must contain between 5 and 60 characters');
        hasErrors = true;
    }
    if (!service.validateUsername(username)) {
        $('#usernameError').append('Username must contain between 5 and 20 characters.\n');
        hasErrors = true;
    }
    if (!service.validateAlphanumeric(username)) {
        $('#usernameError').append('Only alphanumeric characters are accepted.');
        hasErrors = true;
    }
    if (!service.validateEmail(email)) {
        $('#emailError').text('Not a well-formed email adress.');
        hasErrors = true;
    }
    if (!service.validatePassword(password1)) {
        $('#mainPasswordError').text('Password must contain between 5 and 20 characters.');
        hasErrors = true;
    }
    if (!service.passwordsMatch(password1, password2)) {
        $('#passwordError').text('Passwords don\'t match.');
        hasErrors = true;
    }
    return !hasErrors;
}

//ATTACH TOOLTIPS TO AN ELEMENT AND IT'S DESCENDANTS
function attachTooltips(element) {
    $(function() {
        $(element).tooltip({
            position: {
                my: "left center",
                at: "right+10 center",
                using: function(position, feedback) {
                    $(this).css(position);
                    $("<div>")
                            .addClass("arrow")
                            .addClass(feedback.vertical)
                            .addClass(feedback.horizontal)
                            .appendTo(this);
                }
            }
        });
    });
}
function createRegisterData() {
    var serialized = $("#registerForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item) {
        user[item.name] = item.value.trim();
    });

    return user;
}

$(document).ready(function() {
    //ATTACH TOOLTIPS
    attachTooltips($('#registerForm'));
    //CLEAR THE FORM FIELDS WHEN LOADING THE PAGE
    clearForm($('#registerForm'));

    var submitButton = $("#submitButton");

    submitButton.on("click", function(event) {
        event.preventDefault();
        $('.errors').empty();

        if (validateRegisterForm()) {
            var registerData = createRegisterData();

            issueTrackerService.register(registerData).done(function(data) {
                if (data.success) {
                    window.location.replace("/registerMessage");
                }
                else {
                    $.each(data.errors, function(key, value) {
                        $("#" + key + "Error").append(value);
                    });
                }
            });
        }
    });

    //EVENT HANDLERS FOR ACTIONS ON THE FORM ELEMENTS
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
            if (username.length !== 0) {
                issueTrackerService.checkUsernameExistance(username).done(function(data) {
                    if (data.success) {
                        var previousErros = $('#usernameError').text();
                        $('#usernameError').text(previousErros +
                                '\nUsername is already taken.');
                    }
                });
            } else {
                $('#usernameError').text('');
            }
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
    });

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
    });


    $('#inputName').on('focus', function() {
        $('#nameError').text('');
    });

    $('#inputUsername').on('focus', function() {
        $('#usernameError').text('');
    });

    $('#inputEmail').on('focus', function() {
        $('#emailError').text('');
    });

    $('#inputPassword').on('focus', function() {
        $('#mainPasswordError').text('');
    });

    $('#inputRetypePassword').on('focus', function() {
        $('#passwordError').text('');
    });

});