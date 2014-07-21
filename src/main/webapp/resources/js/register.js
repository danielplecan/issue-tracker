function passwordsMatch() {
    return $('#inputPassword').val() === $('#inputRetypePassword').val();
}

function createRegisterData(){
    var serialized = $("#registerForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item){
        user[item.name] = item.value;
    });
    
    return user;
}

$(document).ready(function() {
    var submitButton = $("#submitButton");
    
    submitButton.on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();
        
        if(passwordsMatch()) {
            $("#passwordMatchError").attr("class", "hidden");
            
            var registerData = createRegisterData();
            
            issueTrackerService.register(registerData).done(function(data) {
                if (data.succes) {
                    window.location.replace("/login");
                }
                else {
                   $.each(data.errors, function(key, value) {
                        $("#" + key + "Error").append(value);
                    }); 
                }
            });
        } else {
            $("#passwordMatchError").attr("class", "text-warning visible");
        }
    });
});