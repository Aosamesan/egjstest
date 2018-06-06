<#ftl encoding="UTF-8" />
<#include "control.ftl" />
<!DOCTYPE html>
<html>
<head>
    <title>egjs Test : ${COLLECTION.path}</title>
    <@printCommonScript />
    <link rel="stylesheet" href="/static/css/view.css">
    <script src="/static/js/view.js"></script>
</head>
<body>
    <div class="view-container" style="opacity: 0;">
        <div id="image-container"></div>
        <a href="#" class="layer-top page-btn btn-prev"></a>
        <a href="#" class="layer-top page-btn btn-next"></a>
        <div class="layer-top image-indicator">
            <div class="indicator-wrapper">
                <span class="current-page">1</span> / <span class="total-page">0</span>
            </div>
            <a href="/" class="btn-back"></a>
        </div>
    </div>
    <div class="load-indicator">
        <div class="load-indicator-rotate">
            <div class="indicator">
                <div class="rect">
                    <svg height="100%" width="100%">
                    <path stroke="salmon" stroke-width="3" fill="salmon" d="
M 50.000 80.000
L 79.389 90.451
L 78.532 59.271
L 97.553 34.549
L 67.634 25.729
L 50.000 0.000
L 32.366 25.729
L 2.447 34.549
L 21.468 59.271
L 20.611 90.451
L 50.000 80.000"
/>
                    </svg>
                </div>
                <div class="indicator-description">
                    <h3>Loading... <em class="current-image">0</em><em>/</em><em class="total-image">0</em></h3>
                </div>
            </div>
        </div>
    </div>
    <template id="image-item">
        <div class="image-panel">
            <div class="image-wrapper">
                <img class="image" src="{{path}}" onload="imageOnLoad();"/>
            </div>
        </div>
    </template>
    <script>
        jQuery(document).ready(function() {
           loadCollection(jQuery, ${COLLECTION.collectionNumber});
        });
    </script>
</body>
</html>
