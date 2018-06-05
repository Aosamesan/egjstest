function loadCollection($, collectionNumber) {
    var template = $("#image-item").html().trim();
    var container = $("#image-container");
    const DELAY = 120;

    $.ajax("/api/collection/" + collectionNumber, {
        method: "GET",
        success: function(data) {
            var collection = data["collection"];

            for(var idx in collection) {
                var img = collection[idx];
                var render = templateRenderer(template, {"path" : img});
                container.append(render);
            }

            var flicking = new eg.Flicking("#image-container", {
                duration: DELAY
            });

            container.closest(".view-container")
                .find(".btn-prev")
                .on("click", function(e) {
                    e.preventDefault();
                    flicking.prev();
                })
                .end()
                .find(".btn-next")
                .on("click", function(e) {
                    e.preventDefault();
                    flicking.next();
                });

            $(document).on("keyup", function(e) {
                if (e.which === 37) {
                    flicking.prev();
                } else if (e.which === 39) {
                    flicking.next();
                }
            })
        }
    });
}

