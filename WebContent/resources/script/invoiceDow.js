/**
 * 
 */


window.onload = function(){	
	document.getElementById("download").addEventListener("click" ,() =>{
		    const invoice = this.document.getElementById("invoice");
            console.log(invoice);
            console.log(window);
            var number = this.document.getElementById("track").textContent;
 
			var opt = { 
                margin: 0.2,                
				filename: 'fatturaOrder#'+number+'.pdf',
                image: { type: 'jpeg', quality: 0.80 },
                html2canvas: { scale: 2 }, 
                jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
            };
            html2pdf().from(invoice).set(opt).save(); 
        }) 
}









