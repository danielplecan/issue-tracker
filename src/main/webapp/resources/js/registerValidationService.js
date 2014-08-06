var registerValidationService = (function() {
    var self = {};

    self.validateUsername = function(username) {
        var trimedUsername = username.trim();
        if (trimedUsername.length < 5 || trimedUsername.length > 20) {
            return false;
        }
        return true;
    };

    self.validateAlphanumeric = function(text) {
        var re = /^[a-z0-9]+$/i;
        return re.test(text.trim());
    };

    self.validateName = function(name) {
        var trimedName = name.trim();
        if (trimedName.length < 5 || trimedName.length > 60) {
            return false;
        }
        return true;
    };

    self.validateEmail = function(email) {
        var trimedEmail = email.trim();
        var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\w*$/;
        return reg.test(trimedEmail);
    };

    self.validatePassword = function(password) {
        var trimedPassword = password.trim();
        if (trimedPassword.length < 5 || trimedPassword.length > 20)
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
