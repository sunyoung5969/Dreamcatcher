function left_selectmenu(design) {

	var path = "";

	if (design == "RING")
		path = "Form_RightMenu_Ring";
	else if (design == "STRING")
		path = "Form_RightMenu_String";
	else if (design == "FEATHER")
		path = "Form_RightMenu_Feather";
	else if (design == "DECO")
		path = "Form_RightMenu_Deco";

	var form = document.createElement("form");
	form.setAttribute("action", "MainForm.jsp");

	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "rightpage");
	input.setAttribute("value", path);
	form.appenChild(form);
	
	form.submit();
}

