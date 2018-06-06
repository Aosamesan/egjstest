var LOAD_OBJECT = {
    current: 0,
    target: 0,
    setTarget: function(target) {
        this.target = target;
        $(".load-indicator")
            .find(".total-image")
            .text(target)
            .end()
            .find(".current-image")
            .text(0);
        $("html,body").css({
            overflow: "hidden",
            height: "90vh"
        })
    },
    count: function() {
        this.current += 1;

        if (this.current % 5 === 0) {
            loadRect();
        }

        $(".load-indicator")
            .find(".current-image")
            .text(this.current);
        if (this.current >= this.target) {
            $("html,body").css({
                overflow: "auto",
                height: "auto"
            })
            $(".load-indicator")
                .find(".rect")
                .finish()
                .end()
                .hide();
            $(".view-container")
                .animate({
                    opacity: 1
                });
        }
    }
};

function loadRect() {
    $(".load-indicator").find(".rect")
        .animate({
            transform: "rotate(360deg)"
        }, 150);
}

function loadCollection($, collectionNumber) {
    var template = $("#image-item").html().trim();
    var container = $("#image-container");
    const DELAY = 120;

    $.ajax("/api/collection/" + collectionNumber, {
        method: "GET",
        success: function(data) {
            var $imageIndicator = $(".image-indicator");
            var collection = data["collection"];
            var path = data["path"];

            LOAD_OBJECT.setTarget(collection.length);

            $imageIndicator.find(".total-page")
                .text(collection.length);
            document.title = path;
            for(var idx in collection) {
                var img = collection[idx];
                var render = templateRenderer(template, {"path" : img});
                container.append(render);
            }

            var flicking = new eg.Flicking("#image-container", {
                duration: DELAY,
                circular: true,
                previewPadding: [50, 50]
            }).on({
                flickEnd: function(e) {
                    $imageIndicator.find(".current-page")
                        .text(e.no + 1);
                }
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

function imageOnLoad() {
    LOAD_OBJECT.count();
}
