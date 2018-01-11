
//single selection
//this function will display the calandar window as a pop up in the center of the screen


function openCalendar() {
	
    var calWidth,calHeight;
    calWidth = 205;
    calHeight = 180;

    cFlag = 1;
    var childWin = open('calendar.htm','','width = '+ calWidth +',height= '+ calHeight + ',top = '+ (screen.height/2 - calHeight/2) +', left= '+ (screen.width/2 - calWidth/2) + '');
}

var flag = 0

function showCalendar(tableID) {
	
    tableID.style.display = "block";
}

function hideCalendar(tableID) {
	
    tableID.style.display = "none";
}

/*******************
      UTILITY FUNCTIONS
    ********************/

// day of week of month's first day

function getFirstDay(theYear, theMonth)
{
    var firstDate = new Date(theYear,theMonth,1);
    return firstDate.getDay();
}
// number of days in the month
      
function getMonthLen(theYear, theMonth) 
{
    var oneHour = 1000 * 60 * 60;
    var oneDay = oneHour * 24;
    var thisMonth = new Date(theYear, theMonth, 1);
    var nextMonth = new Date(theYear, theMonth + 1, 1);
    var len = Math.ceil((nextMonth.getTime() -
        thisMonth.getTime() - oneHour)/oneDay);
    return len;
}
	  
// create array of English month names
var theMonths = ["January","February","March","April","May","June",
"July","August","September","October","November","December"];
// return IE4+ or W3C DOM reference for an ID
      
function getObject(obj)
{
    var theObj;
    if (document.all)
    {
        if (typeof obj == "string")
        {
            return document.all(obj);
        } else
{
            return obj.style;
        }
    }
    if (document.getElementById) {
        if (typeof obj == "string") {
            return document.getElementById(obj);
        } else {
            return obj.style;
        }
    }
    return null;
}
      
/************************
      DRAW CALENDAR CONTENTS
      *************************/

// clear and re-populate table based on form's selections
function populateTable(form) {
		
    var theMonth = form.chooseMonth.selectedIndex;
    var theYear = parseInt(form.chooseYear.options[form.chooseYear.selectedIndex].value);

    // initialize date-dependent variables
    var firstDay = getFirstDay(theYear, theMonth);
    var howMany = getMonthLen(theYear, theMonth);
         
    // fill in month/year in table header
    getObject("tableHeader").innerHTML = theMonths[theMonth] +
    " " + theYear;
    //getObject("tableHeader").style.color = "#FFFFFF";	//printing header
    //getObject("tableHeader").style.background = "#003366";
    //getObject("tableHeader").style.borderStyle = "outset";

    getObject("tableHeader").style.color = "#000000";	//printing header
    getObject("tableHeader").style.background = "e6effe";
    getObject("tableHeader").style.borderStyle = "outset";
    




         
    // initialize vars for table creation
    var dayCounter = 1;
    var TBody = getObject("tableBody");
    // clear any existing rows
    TBody.style.background = "#e6effe";
		 
    while (TBody.rows.length > 0) {
        TBody.deleteRow(0);
    }
		 		 
    //clearing cells
    var rCount = 1;
		 
    while (rCount <= 42) {
			 
        var clearCell = getObject("c" + rCount);
        //clearCell.style.background = "#00667C";
        clearCell.style.color = "#FFFFFF";
        clearCell.style.fontWeight = "normal";
        clearCell.innerText = "";
        rCount++;
    }
		 
    var newR, newC;
    var done=false;
		 	
					
    var j = firstDay + 1;			//with reference to week day;
			
    //chaning cursor type of non value cells
    for(disableCells = 1; disableCells < j; disableCells++) {
				
        //alert(disableCells);
        var vCell = getObject("c" + disableCells);
        vCell.innerHTML = "&nbsp";
        vCell.style.cursor = "default";
    }
			
    while (!done) {
			 
        // create new row at end
        newR = TBody.insertRow(TBody.rows.length);
						
        for (var i = 0; i < 7; i++) {
            // create new cell at end of row
            newC = newR.insertCell(newR.cells.length);
            if (TBody.rows.length == 1 && i < firstDay) {
                // no content for boxes before first day
                newC.innerHTML = "";
                newC.style.disabled = true;
                continue;
            }
        
            if (dayCounter == howMany) {
                // no more rows after this one
                done = true;
            }
            // plug in date (or empty for boxes after last day)
            var cDay = new Date();
            var cMonth = new Date();
            var cYear = new Date();
			   
            var iday = (dayCounter <= howMany) ? //month delimiter
            dayCounter++ : "";
				  
            if (iday != "") {
								
                if (cDay.getDate() == iday && cMonth.getMonth() == theMonth && cYear.getFullYear() == theYear) {
					
                    buttonStr = "<button id='buttonDay + iday +' onClick='insertDate(" + iday + ",buttonDay)' style='background-color:#CCCCCC; width:40px; border-style:none; color:red; font-weight:bold; cursor:pointer'>" + iday + "</button>"
                    var obj = getObject("c" + j);
                    obj.style.backgroundColor = "Orange";
                    obj.style.color = "Black";
                    obj.style.fontWeight = "bold";
                    obj.innerText = iday;
                }
                else {
                    //buttonStr = "<button id='buttonDay + iday +' onClick='insertDate(" + iday + ")' style='background-color:#999999; width:40px; border-style:none; cursor:pointer'>" + iday + "</button>"
                    buttonStr = "<button id='buttonDay + iday +' onClick='insertDate(" + iday + ")' style=' width:40px; border-style:none; cursor:pointer'>" + iday + "</button>"
                    var obj = getObject("c" + j);
                    obj.style.backgroundColor = "#e6effe";
                    obj.style.borderStyle = "outset";
                    obj.style.color = "Black";
                    obj.innerText = iday;
                }
                newC.innerHTML = (buttonStr); //printing numbers
                j++;
            }
        }
    }
    var endCells = j;
    while(endCells <= 42) {

        var cObj = getObject("c" + endCells);
        cObj.innerHTML = "&nbsp";
        cObj.style.cursor = "pointer";
        endCells++;
    }
}

     	  
/*******************
      INITIALIZATIONS
      ********************/
  
// create dynamic list of year choices
function fillYears() {
    var today = new Date();
    var curYear = new Date();
    var thisYear = today.getFullYear();
    var yearChooser = document.dateChooser.chooseYear;
    // for (i = thisYear; i < thisYear + 10; i++)
    //  {
    for (var i=1900;i<2200;i++)
    {

        //document.write("<option value="+i+">"+i+"</option>");
        yearChooser.options[yearChooser.options.length] = new Option(i, i);
    }
    setCurrMonth(today);
    setCurrYear(curYear);
}
	  
// set month choice to current month
function setCurrMonth(today) {
		
    document.dateChooser.chooseMonth.selectedIndex = today.getMonth();
}
	  
// set year choice to current year
function setCurrYear(curYear) {

    document.dateChooser.chooseYear.selectedIndex = (curYear.getFullYear() - 1900);

}
	  
var dateSel = new Array(1);

//displays data in the text field
function insertDate(str,buttonDay) {
		
    var sMonth;
	
    switch (document.dateChooser.chooseMonth.value) {
		
        case "January":
            sMonth = "01";
            break;
		
        case "February":
            sMonth = "02";
            break;
		
        case "March":
            sMonth = "03";
            break;
		
        case "April":
            sMonth = "04";
            break;
		
        case "May":
            sMonth = "05";
            break;
		
        case "June":
            sMonth = "06";
            break;
		
        case "July":
            sMonth = "07";
            break;
		
        case "August":
            sMonth = "08";
            break;
		
        case "September":
            sMonth = "09";
            break;
		
        case "October":
            sMonth = "10";
            break;
		
        case "November":
            sMonth = "11";
            break;
		
        case "December":
            sMonth = "12";
            break;
		
    }
		
    str = document.dateChooser.chooseYear.value + "/" + sMonth + "/" + str
    //opener.document.form1.dateField.innerText = str;
    var dField = document.getElementById('dateField');
    dField.innerText = str;
		
    if(dateSel[0] == undefined) {
        dateSel[0] = str;
    //alert(dateSel[0]);
    }
    else {
        dateSel[1] = str;
    //alert(dateSel[1] + ' ' + dateSel[0]);
    }
		
//hideCalendar(calendarTable);
//self.close();
//hideCalendar(dateChooser);
}
	

function calNext(dateChooser) {
	
    if(document.dateChooser.chooseMonth.selectedIndex < 11) {

        document.dateChooser.chooseMonth.selectedIndex++ ;
        populateTable(dateChooser);
    }
    else {
        document.dateChooser.chooseYear.selectedIndex++ ;
        document.dateChooser.chooseMonth.selectedIndex = 0;
        populateTable(dateChooser);
    }
	
}

function calPre(dateChooser) {
    //document.dateChooser.chooseYear.selectedIndex = (document.dateChooser.chooseYear.value - 100) + 1;
    if(document.dateChooser.chooseMonth.selectedIndex > 0) {

        document.dateChooser.chooseMonth.selectedIndex-- ;
        populateTable(dateChooser);
    }
    else {
        document.dateChooser.chooseYear.selectedIndex-- ;
        document.dateChooser.chooseMonth.selectedIndex = 11;
        populateTable(dateChooser);
    }
	
}

/*
=======================================================================================================
extra features
=======================================================================================================
*/


var objDate = new Date();

function getCellValue(cellID) {

    if (cellID.innerText != " " ) {
		
        for(i=1; i<43; i++) {
			
            var c = getObject( 'c' + i);

            if (c.style.background == "#CCFFCC") c.style.background = "#009393";

        }
        cellID.style.background = "#CCFFCC";
        opener.fName.value =  addZero(cellID.innerText) + "-" + getMonthStr(dateChooser.chooseMonth.value) + "-" + dateChooser.chooseYear.value;

        if(opener.fName.name == "dateOfBirth") vd_AgeCalculation(dateChooser.chooseMonth.value,cellID.innerText,dateChooser.chooseYear.value);
			
        if(opener.fName.id == "enqCalDate") {
            opener.form1.action ='EstateplusController?mo=enquiry&todo=dispViewDateWise';
            opener.form1.submit();
        }
        else if(opener.fName.name == "rDay") {
            opener.rptForm.action ='EstateplusController?mo=property&todo=ReportsSaleDay';
            opener.rptForm.submit();
        // alert('ready for day wise');
        }
        else if(opener.fName.name == "renDay") {
            opener.rptForm.action ='EstateplusController?mo=property&todo=ReportsRentDay';
            opener.rptForm.submit();
        }
                        
        else if(opener.fName.name == "leaseDay") {
            opener.rptForm.action ='EstateplusController?mo=property&todo=ReportsLeaDay';
            opener.rptForm.submit();
        }
                        
        else if(opener.fName.name == "vieDay") {
            opener.rptForm.action ='EstateplusController?mo=property&todo=ReportsViewDatewise';
            opener.rptForm.submit();
        }
        self.close();
    }
}


function getCalDate(form) {

    var calDate = null;
    form = document.getElementById(form);

    for(i=1; i<43; i++) {
		
        cellID = "c" + i;
        cellID = document.getElementById(cellID);
        calDate = addZero(cellID.innerText) + '-' + addZero(getMonthStr(form.chooseMonth.value)) + '-' + ((form.chooseYear.value).substr(2,2));

    //if(cellID.innerText != " ") alert(calDate);
    }
		
}

function vd_AgeCalculation(mm,dd,yyyy)
{
    var byr=parseInt(yyyy);
    var bmo=parseInt(mm);
    var bday=parseInt(dd);


    var now = new Date();
    var tday=now.getDate();
    var tmo=(now.getMonth()+1);
    var tyr=(now.getFullYear());
    var form1=opener.form1;

    if(tmo>bmo || (tmo==bmo && tday>=bday))
    {
        form1.age.value=(tyr-byr);
        if((tyr-byr)<10 ||(tyr-byr)>100)
        {
		
            form1.age.value="";
            form1.dateOfBirth.value="";
            alert("age should not be more than 100 or less than 10");
        }
				
    }
    else
    {
        form1.age.value=(tyr-byr)-1;
        if((form1.age.value<10 || form1.age.value>100))
        {
			
            form1.age.value="";
            form1.dateOfBirth.value="";
            alert("age should not be more than 100 or less than 10");
        }
    }
}


function addZero(dateValue) {

    if(dateValue <= 9) return ("0" + dateValue);
    else return dateValue;
}
