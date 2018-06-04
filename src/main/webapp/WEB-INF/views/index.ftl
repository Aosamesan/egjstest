<#include "control.ftl" />
<!DOCTYPE html>
<html>
<head>
    <title>eg.js Test</title>
    <meta charset="UTF-8" />
    <@printUpperScript />
</head>
<body>
    <div id="main-container">
        <div id="image-index" class="image-index">
            <#list COLLECTIONS as collection>
                <div class="item-wrapper">
                    <div class="image-wrapper">
                        <img class="image" src="${collection.collection?first}" alt="${collection.path}">
                    </div>
                    <#--<div class="mask"></div>-->
                </div>
            </#list>
        </div>
    </div>
</body>
</html>