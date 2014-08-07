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