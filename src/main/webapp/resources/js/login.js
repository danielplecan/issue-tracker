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
               function() {
                   $("#loginError").removeClass("hidden");
                   currentButton.removeAttr("disabled");
               });
    });
});

