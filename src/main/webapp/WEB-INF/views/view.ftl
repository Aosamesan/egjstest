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
    <div class="view-container">
        <div id="image-container"></div>
        <a href="#" class="page-btn btn-prev"></a>
        <a href="#" class="page-btn btn-next"></a>
    </div>
    <template id="image-item">
        <div class="image-panel">
            <div class="image-wrapper">
                <img class="image" src="{{path}}"/>
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
