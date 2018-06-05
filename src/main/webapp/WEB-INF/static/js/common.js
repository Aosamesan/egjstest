function templateRenderer(template, param) {
    return template.replace(/\{\{([^}]+)\}\}/g, function() {
        var replaceTarget = arguments[1];
        return param[replaceTarget];
    }).trim();
}
