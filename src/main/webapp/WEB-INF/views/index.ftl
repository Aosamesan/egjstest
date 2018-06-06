<#ftl encoding="UTF-8" />
<#include "control.ftl" />
<!DOCTYPE html>
<html>
<head>
    <title>eg.js Test</title>
    <@printCommonScript />
    <link href="/static/css/main.css" rel="stylesheet" type="text/css" />
    <script src="/static/js/main.js"></script>
</head>
<body>
    <div id="main-container">
        <div id="image-index" class="image-index">
        </div>
    </div>
    <nav class="fixed-bottom">
        <h1>Foo</h1>
    </nav>
    <template id="image-item-template" is="auto-binding">
        <div class="image-wrapper">
            <img class="image" src="{{representativeImage}}" alt="{{path}}">
            <a href="/view/{{collectionNumber}}" class="anchor-wrapper" title="{{path}}"></a>
        </div>
    </template>
</body>
</html>