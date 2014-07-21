var register= function() { 
    issueTrackerService().register().done(function(data){
        if(data.succes){
            window.location.replace("/login");
        }
        else
            $.each(data.errors, function(k, v) {    
                $("#" + k + "Error").append(v);
            });
    });
};
$(document).ready(function() {
    var submitButton=$("#submitButton");
    submitButton.on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();
        submitButton.attr("disabled", "disabled");
        $('#inputRetypePassword').val("");
        register();     
    });
    
    //VALIDARI
    submitButton.attr("disabled", "disabled");
    $("#inputRetypePassword").on('input', function() {
        var error=$("#passwordMatchError");
        if($('#inputPassword').val()!==$('#inputRetypePassword').val()){
            submitButton.attr("disabled", "disabled");
            error.attr("class","text-warning visible");
        } else {
            submitButton.removeAttr("disabled");
            error.attr("class","hidden");
        }
    });
});


