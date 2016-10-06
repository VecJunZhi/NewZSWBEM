function IsNumeric(sText)

{
    var ValidChars = "0123456789.";
    var IsNumber = true;
    var Char;


    for (i = 0; i < sText.length && IsNumber == true; i++) {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1) {
            IsNumber = false;
        }
    }
    return IsNumber;

};


function   a(string_value) 
{ 
	var   type= /^[1-9]+[0-9]*]*$/; 
	var   re   =   new   RegExp(type); 
	if(string_value.match(re)==null) 
	{ 
		
		return false;
	}
	else
	{
		if(string_value<100){
			this.value=1;
			return false;
		}
		else
		{
		return true;
		}
	}
};
function calcProdSubTotal() {

    var prodSubTotal = 0;

    $(".row-total-input").each(function () {

        var valString = $(this).text() || 0;

        prodSubTotal += parseFloat(valString);

    });
	prodSubTotal=prodSubTotal.toFixed(2);
    $("#product-subtotal").text(prodSubTotal);

};


$(function () {

    $('.num-pallets-input').live("blur",function () {

        var $this = $(this);

        var numPallets = $this.val();
        var multiplier = $this
            .parent().parent()
            .find(".price-per-pallet").val();

        if ((IsNumeric(numPallets)) && (numPallets != '') && parseInt(numPallets)>=1) {

            var rowTotal = numPallets * multiplier;
			rowTotal = rowTotal.toFixed(2);

            $this
                .css("background-color", "white")
                .parent().parent()
                .find("td.row-total span")
                .text(rowTotal);

        } else {
			numPallets=$this.val("");//ÖµÎª¿Õ
			rowTotal = 0;
            $this.css("background-color", "#ffdcdc");
			
			$this
                .parent().parent()
                .find("td.row-total span")
                .text(rowTotal);

        };

        calcProdSubTotal();

    });

});
$(function () {

    $('.price-per-pallet').live("blur",function () {

        var $this = $(this);

        var pricePallets = $this.val();
        var number = $this
            .parent().parent()
            .find(".num-pallets-input").val();

        if ((IsNumeric(pricePallets)) && (pricePallets != '') ) {

            var rowTotal = pricePallets * number;
			rowTotal = rowTotal.toFixed(2);

            $this
              //  .css("background-color", "white")
                .parent().parent()
                .find("td.row-total span")
                .text(rowTotal);

        } else {

           // $this.css("background-color", "#ffdcdc");

        };

        calcProdSubTotal();

    });

});