function passwordsMatch() {
    return $('#inputPassword').val() === $('#inputRetypePassword').val();
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
    
    submitButton.on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();
        
        if(passwordsMatch()) {
            $("#passwordMatchError").attr("class", "hidden");
            
            var editData = createEditData();
            
            issueTrackerService.edit(editData).done(function(data) {
                if (data.success) {
                    window.location.replace("/profile");
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


