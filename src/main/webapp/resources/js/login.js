$(document).ready(function() {
    $("#loginButton").click(function(event) {
        event.preventDefault();

        var currentButton = $(this);
        currentButton.attr("disabled", "disabled");

        $("#loginError").addClass("hidden");

        var loginData = {};
        loginData = {
            username: $("#inputUsername").val(),
            password: $("#inputPassword").val()
        };

        issueTrackerService.login(loginData).then(
                function() {
                    currentButton.removeAttr("disabled");
                    window.location.replace("/");

                },
                function(response) {
                    switch (response.responseText) {
                        case "credential":
                            $("#loginErrorCredential").removeClass("hidden");
                            $("#loginErrorActivation").addClass("hidden");
                            break;
                        case "activation":
                            $("#loginErrorCredential").addClass("hidden");
                            $("#loginErrorActivation").removeClass("hidden");
                            break;
                    }
                    currentButton.removeAttr("disabled");
                });
    });
});

