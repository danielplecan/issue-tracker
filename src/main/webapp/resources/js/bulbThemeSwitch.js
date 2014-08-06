$(document).ready( function() { 
$('#lightBulbPic').click(function(){
    var themeLink;
    themeLink=$('#bootstrapTheme').attr('href');
    var words = themeLink.split('/');
    console.log(words[4]);
    issueTrackerService.changeTheme((words[4]+1)%3);
});
});