function getContrastYIQ(hexcolor){
	var r = parseInt(hexcolor.substr(0,2),16);
	var g = parseInt(hexcolor.substr(2,2),16);
	var b = parseInt(hexcolor.substr(4,2),16);
	var yiq = ((r*299)+(g*587)+(b*114))/1000;
	return (yiq >= 128) ? 'black' : 'white';
}

$(document).ready(function() {
    $('#buttonCreateIssue').click(function() {
        var currentButton = $(this);
        currentButton.attr('disabled', 'disabled');
        issueTrackerService.createIssue($('#textArea1').val(), $('#textArea2').val()).done(function(data) {
            if (data.success) {
                window.location.replace(data.url);
            }

            currentButton.removeAttr('disabled');
        });
    });
    
    $('#labelSelector').delegate('.list-item-text', 'click', function(){
        var labelColorToSet;
        var circleColorToSet;
        $(this).toggleClass('selectedLabel');
        if($(this).hasClass('selectedLabel')) {
            labelColorToSet =  $(this).attr('data-color');
            $(this).css('color', getContrastYIQ(labelColorToSet));
        } else {
            labelColorToSet =  'white';
            $(this).css('color', 'black');
        }      
        $(this).css('background-color', labelColorToSet);
    });
});


