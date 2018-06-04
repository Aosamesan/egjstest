(function($) {
    $(document).ready(function(e) {
        var InfiniteGrid = eg.InfiniteGrid;
        var GridLayout = InfiniteGrid.GridLayout;

        var ig = new InfiniteGrid("#image-index", {
            horizontal: false
        });

        ig.setLayout(GridLayout, {
            margin: 15,
            align: "center"
        });

        $("#image-index").animate({
            "height" : "100%"
        }, 300);
    });
})(jQuery);