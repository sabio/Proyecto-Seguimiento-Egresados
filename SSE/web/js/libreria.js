function Trim(str){
	// Remove leading spaces and carriage returns
	while ((str.substring(0,1) == ' ') || (str.substring(0,1) == '\n') || (str.substring(0,1) == '\r'))
	{
		str = str.substring(1,str.length);
	}
	
	// Remove trailing spaces and carriage returns
	
	while ((str.substring(str.length-1,str.length) == ' ') || (str.substring(str.length-1,str.length) == '\n') || (str.substring(str.length-1,str.length) == '\r'))
	{
		str = str.substring(0,str.length-1);
	}
	
	var contX = 0;
	var charCode = '';
	var strTemp = '';
	
	for (contX = 0; contX < str.length; contX++){
		charCode = str.charCodeAt(contX);
		if (charCode != 160){
			strTemp += str.charAt(contX);
		}
	}
	return str;
}


//Funcion javascript que te regresa en dias la diferencia de dos fechas
//Fechas en formato DD/MM/YYYY
function diasDiferenciaEntreDosFechas(fechaInicio,fechaFin){


   var datosSplitados = fechaInicio.split("/");
   var miFecha1 = new Date( datosSplitados[2], datosSplitados[1]-1, datosSplitados[0] );
   datosSplitados = fechaFin.split("/");
   var miFecha2 = new Date( datosSplitados[2], datosSplitados[1]-1, datosSplitados[0] );

   //Resta fechas y redondea
        //   Math.floor((fecha1.getTime()-fecha2.getTime())/(3600000*24))
   var diferencia = miFecha2.getTime() - miFecha1.getTime()
   var dias = diferencia / (3600000*24)
   //var segundos = Math.floor(diferencia / 1000)

   return dias;
}