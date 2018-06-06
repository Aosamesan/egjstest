function templateRenderer(template, param) {
    return template.replace(/\{\{([^}]+)\}\}/g, function() {
        var replaceTarget = arguments[1];
        return param[replaceTarget];
    }).trim();
}

function templateListRender(template, list) {
    var result = [];

    for (var idx in list) {
        var item = list[idx];
        result.push(templateRenderer(template, item));
    }

    return result;
}

function getItems(template, key, ig) {
    $.ajax("/api/collection", {
        method: "GET",
        success: function(data) {
            var result = data["result"];
            var render = templateListRender(template, result);
            ig.append(render, key);
        }
    });
}

(function($) {

    $(document).ready(function(e) {
        var template = document.getElementById("image-item-template").innerHTML;
        var InfiniteGrid = eg.InfiniteGrid;
        var Layout = InfiniteGrid.GridLayout;

        var ig = new InfiniteGrid("#image-index", {
            horizontal: false
        });

        ig.setLayout(Layout, {
            margin: 5,
            align: "center"
        });

        ig.on({
            append: function(e) {
                getItems(template, e.groupKey + 1, ig);
            }
        });

        getItems(template, 0, ig);

    });
})(jQuery);