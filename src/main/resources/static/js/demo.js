$(document).ready(function() {
    // The plugin shows the treecontrol after the
    // collapse, expand and toggle events are hooked up
    // Just hide the links.
    $('#treecontrol a').hide();

    // On click of your custom links, button, etc
    // Trigger the appropriate treecontrol click
    $('#expandAll').click(function() {        
        $('#treecontrol a:eq(1)').click();    
    });
    
    $('#collapseAll').click(function() {
        $('#treecontrol a:eq(0)').click();        
    });

    // Render the treeview per usual.    
	$("#treeview").treeview({
		control: "#treecontrol",
		persist: "cookie",
		cookieId: "treeview-black"
	});
});