(function($) {
	"use strict";
	
	$(".monetario").priceFormat({
	    prefix: "R$ ",
	    centsSeparator: ",",
	    thousandsSeparator: ".",
	    clearPrefix: true
	});
})(jQuery);
