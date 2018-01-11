
function getMonthStr(month) {
	
	switch (month) {
		
		case "01": return "jan";
		break;
		
		case "02": return "feb";
		break;
		
		case "03": return "mar";
		break;
		
		case "04": return "apr";
		break;
		
		case "05": return "may";
		break;
		
		case "06": return "jun";
		break;
		
		case "07": return "jul";
		break;
		
		case "08": return "aug";
		break;
		
		case "09": return "sep";
		break;
		
		case "10": return "oct";
		break;
		
		case "11": return "nov";
		break;
		
		case "12": return "dec";
		break;
                
                default: return "you entered: " + month;
	}
	
}

function getMonthNum(month) {
	
	switch (month) {
		
		case "jan": return "01";
		break;
		
		case "feb": return "02";
		break;
		
		case "mar": return "03";
		break;
		
		case "apr": return "04";
		break;
		
		case "may": return "05";
		break;
		
		case "jun": return "06";
		break;
		
		case "jul": return "07";
		break;
		
		case "aug": return "08";
		break;
		
		case "sep": return "09";
		break;
		
		case "oct": return "10";
		break;
		
		case "nov": return "11";
		break;
		
		case "dec": return "12";
		break;
                
                default: return "you entered: " + month;
	}
	
}