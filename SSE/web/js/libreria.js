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