
var winChild=null;
var fName = null;

function openWin(pName,winWidth,winHeight,fieldName) {

fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

	if(winChild == null) {

                winChild = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
               // winChild.focus();
	}
       else if(winChild.closed){winChild = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');}
           else if(!winChild.closed){winChild.focus();}
             else winChild.focus();

}
//**********NEWLY ADDED FOR 'APPOINTMENT'-seeker/owner BY Girija******************************************
//*********openWin1() For Seeker Button****************************************************************

//************************NEWLY MODIFIED*******************************

var winChild1=null;
var fName = null;

function openWin1(pName,winWidth,winHeight,fieldName) {

fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

if(winChild1 == null){
       // alert("WELCOME 2 SEEKER");
        winChild1 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
                    // winChild1 = null;

   }
else if( winChild1.closed && form1.seekerName.value==""){
                    // alert("Afetr closed....seekerName z null...");
                     winChild1 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');

   }

else if( winChild1.closed && form1.seekerName.value!="" ){
                    // alert("Afetr closed....seekerName zn't null....");
                     winChild1 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
   }

    else if( form1.seekerName.value=="" ){ //alert("SEEKER Popup...");
                                             winChild1.focus();}

    else { winChild1.focus(); }


}
//------------END1-----------------------------------------------------------------------------

//***********openWin2()NEWLY ADDED For Owner Button************************************

var winChild2=null;
var fName = null;

function openWin2(pName,winWidth,winHeight,fieldName) {

fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

if(winChild2 == null){
        //alert("WELCOME 2 SEEKER");
        winChild2 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');


   }
else if( winChild2.closed && form1.seekerName.value==""){
                     //alert("Afetr closed....Owner z null...");
                     winChild2 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');

   }

else if( winChild2.closed && form1.ownerName.value!="" ){
                    // alert("Afetr closed....Owner zn't null....");
                     winChild2 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
   }

    else if( form1.ownerName.value=="" ){
 winChild2 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
// alert("Owner Popup...");
           winChild2.focus();}
    else { winChild2.focus(); }

}
 //---------------------END2------------------------------------------------------------------------






//*****************FOR SCHEDULER seeker's history/owner's history*************************************
//------------------START OF openWin3()-------------------------------------------------------------
var winChild3=null;
var fName = null;

function openWin3(pName,winWidth,winHeight,fieldName) {

              fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

 if(winChild3 == null) {
                         winChild3=document.getElementById("seekHist");         //To accept 'id=seekHist' from menu-seeker's History
		    winChild3 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
                  }
     else if(winChild3==""){winChild3.focus();}
              else  winChild3.focus();
}
//-----------------------END of openWindow3()--------------------------------------------------

var winChild4=null;
var fName = null;

function openWin4(pName,winWidth,winHeight,fieldName) {

              fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

 if(winChild4 == null) {
                        winChild4=document.getElementById("ownHist");        //To accept 'id=ownHist' from menu-owner's History
		        winChild4 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
                  }
     else if(winChild4==""){winChild4.focus();}
            else  winChild4.focus();
}

//--------------------END OF openWin4()----------------------------------------------------------




//*********************NEWLY ADDED FOR PropertyAdd.jsp:-For "title=select Contact" POPUP Window BY GIRIJA...****************

var winChild5=null;
var fName = null;

function openWin5(pName,winWidth,winHeight,fieldName) {

              fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

 if(winChild5 == null) {
                        winChild5=document.getElementById("contPopup");        //To accept 'id=seekHist' from menu-owner's History
		        winChild5= window.open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
                  }
   else if(winChild5.closed){
                         winChild5=document.getElementById("contPopup");        //To accept 'id=seekHist' from menu-owner's History
		         winChild5= window.open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
                       }
     else if(winChild5==""){winChild5.focus();}
            else  winChild5.focus();
}

//-----------------------------------------END----------------------------------------------------------------
//**************************NEW ADDED openWin6()for Yearly & openWin7() for Between Dates BY SHAZ******************************************************************************


var winChild6=null;
var fName = null;

function openWin6(pName,winWidth,winHeight,fieldName) {

fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

	if(winChild6 == null) {

                winChild6 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
               // winChild.focus();
	}
       else if(winChild6.closed){winChild6 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');}
           else if(!winChild6.closed){winChild6.focus();}
             else winChild6.focus();

}

//----------------------------------------END6------------------------------------


var winChild7=null;
var fName = null;

function openWin7(pName,winWidth,winHeight,fieldName) {

fName=fieldName;

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

	if(winChild7 == null) {

                winChild7 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');
               // winChild.focus();
	}
       else if(winChild7.closed){winChild7 = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',resizable=1,status=1,toolbar=no,scrollbar = 1');}
           else if(!winChild7.closed){winChild7.focus();}
             else winChild7.focus();

}
//--------------------------------------END7-----------------------------------------------------------



//******************************************************************************************************

var winChild = null;

    function openIndexPage(pName,winWidth,winHeight) {

	X = (screen.width/2) - (winWidth/2);
	Y = (screen.height/2) - (winHeight/2);

	if(winChild == null) {

                winChild = open(pName,'','width = '+ winWidth +',height = '+ winHeight +',left = '+ X +',top = '+ Y +',toolbar=no,scrollbar = 1,status=1,scrollbars=1,resizable=1');

	}
	else winChild.focus();

        if(!winChild) alert('please disable your popup blocker and try again...');
    }
