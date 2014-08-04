var registerValidationService = (function() {
    var self = {};

    self.validateUsername = function(username) {
        var trimedUsername = username.trim();
        if (trimedUsername.length > 0 && trimedUsername.length < 5 || trimedUsername.length > 20) {
            return false;
        }
        return true;
    };

    self.validateName = function(name) {
        var trimedName = name.trim();
        if (trimedName.length > 0 && trimedName.length < 5 || trimedName.length > 60) {
            return false;
        }
        return true;
    };

    self.validateEmail = function(email) {
        var trimedEmail = email.trim();
        if (trimedEmail.length === 0) {
            return true;
        }
        var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\w*$/;
        return reg.test(email);
    };

    self.validatePassword = function(password) {
        var trimedPassword = password.trim();
        if (trimedPassword.length > 0 && trimedPassword.length < 5 || trimedPassword.length > 20)
            return false;
        return true;
    };

    self.passwordsMatch = function(password1, password2) {
        if (password1.localeCompare(password2) === 0) {
            return true;
        } else {
            return false;
        }
    };

    return self;
})();


function passwordsMatch() {
    return $('#inputPassword').val() === $('#inputRetypePassword').val();
}

function createRegisterData() {
    var serialized = $("#registerForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item) {
        user[item.name] = item.value;
    });

    return user;
}

$(document).ready(function() {
    var submitButton = $("#submitButton");

    submitButton.on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();

        if (passwordsMatch()) {
//            $("#passwordMatchError").attr("class", "hidden");
            $('#passwordError').text('');

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
        } else {
//            $("#passwordMatchError").attr("class", "text-warning visible");
            $('#passwordError').text('Passwords don\'t match');
        }
    });


    //AUGUSTIN
    $('#inputName').on('input', function() {
        var name = $(this).val();
        if (!registerValidationService.validateName(name)) {
            $('#nameError').text('Name must contain between 5 and 60 characters');
        } else {
            $('#nameError').text('');
        }
    });

    $('#inputUsername').on('input', function() {
        var username = $(this).val();
        if (!registerValidationService.validateUsername(username)) {
            $('#usernameError').text('Username must contain between 5 and 20 characters');
        } else {
            $('#usernameError').text('');
        }
    });

    $('#inputEmail').on('input', function() {
        var email = $(this).val();
        if (!registerValidationService.validateEmail(email)) {
            $('#emailError').text('Not a well-formed email adress.');
        } else {
            $('#emailError').text('');
        }
    });

    $('#inputPassword').on('input', function() {
        var password = $(this).val();
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
    });

    $('#inputRetypePassword').on('input', function() {
        var password1 = $(this).val();
        var password2 = $('#inputPassword').val();
        if (!registerValidationService.passwordsMatch(password1, password2)) {
            $('#passwordError').text('Passwords don\'t match.');
        } else {
            $('#passwordError').text('');
        }
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