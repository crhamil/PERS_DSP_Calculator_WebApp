/** 
 * Définit le sélecteur d'une colonne DataTable, qui permet de filtrer selon la valeur de la colonne
 * @param {Object} c une colonne d'un tableau DataTable
 * <p>Source principale : https://datatables.net/</p>
 */
function setColumnSearchSelector(c) {
	let column = c;
    // Create a set (array with distinct elements= for distinct values
    let values = new Set();
    // Collect all unique values (split if cell has multiple lines)
    column.data().each(function (d) {
		// Cleaning DataTables' column's cell HTML
		d.split(",").forEach(v => {
		var str = v.replace(/(<([^>]+)>)/gi, "").trim();
			if (str) values.add(str);
		});
    });
	// Create select element
	let select = document.createElement("select");
	select.className = select.className = "column-select";
	select.add(new Option($(column.footer()).text(), ""));
	column.footer().replaceChildren(select);
	// Apply listener for user change in value
	select.addEventListener("change", function () {
		// On doit enlever les accents des valeurs à comparer, sinon la recherche DataTable retournera systématiquement faux
		var value_safe = select.value.normalize("NFD").replace(/[\u0300-\u036f]/g, ""); /* Sépare les accents de leur lettre, ex : â = a^, puis les supprime */
		value_safe = RegExp.escape(value_safe); /* Normalise les accents */
		var expr = select.value == "" ? "" : new RegExp(`(^|,\\s*)${value_safe}(\\s*,|$)`, "i");
		column.search(expr, {exact: true}).draw();
	});
	
	// Add list of options
	Array.from(values).sort().forEach(v => select.add(new Option(v)));
}