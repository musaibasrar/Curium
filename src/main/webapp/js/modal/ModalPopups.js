/****************************************************************
 *                                                              *
 * ModalPopups                                                  *
 * -----------                                                  *
 *                                                              *
 * This script offers you a collection of basic modal popups.   *
 *                                                              *
 * Requirements:                                                * 
 * DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" *
 *                                                              *
 * Version 0.1 (initial version)                                *
 * Copyright (c) 2008 Jan Stolk                                 *
 *                                                              *
 * Website: http://www.modalpopups.com                          *
 * E-Mail: stolk_jan@hotmail.com                                *
 *                                                              *
 *                                                              *
 * This library is free software; you can redistribute          *
 * it and/or modify it under the terms of the GNU               *
 * Lesser General Public License as published by the            *
 * Free Software Foundation; either version 3 of the            *
 * License, or (at your option) any later version.              *
 *                                                              *
 * This library is distributed in the hope that it will         *
 * be useful, but WITHOUT ANY WARRANTY; without even the        *
 * implied warranty of MERCHANTABILITY or FITNESS FOR A         *
 * PARTICULAR PURPOSE. See the GNU Lesser General Public        *
 * License for more details.                                    *
 *                                                              *
 * You should have received a copy of the GNU Lesser            *
 * General Public License along with this library;              *
 * Inc., 59 Temple Place, Suite 330, Boston,                    *
 * MA 02111-1307 USA                                            *
 *                                                              *
 ****************************************************************/

/****************************************************************
 *                                                              *
 *  Next release ideas:                                         *
 *                                                              *
 *  maxWidth and maxHeight                                      *
 *      to limit autosize of popup                              *
 *                                                              *
 *  Improve window.onresize                                     *
 *      change is to addHandler etc... to be able to have more  *
 *      than one window or more than one oneesize active        *
 *                                                              *
 ****************************************************************/

var ModalPopupsDefaults = {
    shadow: true,
    shadowSize: 5,
    shadowColor: "#333333",
    backgroundColor: "#CCCCCC",
    borderColor: "#999999",
    titleBackColor: "#C1D2E7",
    titleFontColor: "#15428B",
    popupBackColor: "#C7D6E9",
    popupFontColor: "black",
    footerBackColor: "#C1D2E7",
    footerFontColor: "#15428B",
    okButtonText: "OK",
    yesButtonText: "Yes",
    noButtonText: "No",
    cancelButtonText: "Cancel",
    fontFamily: "Verdana,Arial",
    fontSize: "9pt"
}

var ModalPopups = {
    Init: function() {
        //No init required, yet
    },
    
    SetDefaults: function(parameters) {
        parameters = parameters || {};
        ModalPopupsDefaults.shadow = parameters.shadow != null ? parameters.shadow : ModalPopupsDefaults.shadow;
        ModalPopupsDefaults.shadowSize = parameters.shadowSize != null ? parameters.shadowSize : ModalPopupsDefaults.shadowSize;
        ModalPopupsDefaults.shadowColor = parameters.shadowColor != null ? parameters.shadowColor : ModalPopupsDefaults.shadowColor;
        ModalPopupsDefaults.backgroundColor = parameters.backgroundColor != null ? parameters.backgroundColor : ModalPopupsDefaults.backgroundColor;
        ModalPopupsDefaults.borderColor = parameters.borderColor != null ? parameters.borderColor : ModalPopupsDefaults.borderColor;
        ModalPopupsDefaults.okButtonText = parameters.okButtonText != null ? parameters.okButtonText : ModalPopupsDefaults.okButtonText;
        ModalPopupsDefaults.yesButtonText = parameters.yesButtonText != null ? parameters.yesButtonText : ModalPopupsDefaults.yesButtonText;
        ModalPopupsDefaults.noButtonText = parameters.noButtonText != null ? parameters.noButtonText : ModalPopupsDefaults.noButtonText;
        ModalPopupsDefaults.cancelButtonText = parameters.cancelButtonText != null ? parameters.cancelButtonText : ModalPopupsDefaults.cancelButtonText;
        ModalPopupsDefaults.titleBackColor = parameters.titleBackColor != null ? parameters.titleBackColor : ModalPopupsDefaults.titleBackColor;
        ModalPopupsDefaults.titleFontColor = parameters.titleFontColor != null ? parameters.titleFontColor : ModalPopupsDefaults.titleFontColor;
        ModalPopupsDefaults.popupBackColor = parameters.popupBackColor != null ? parameters.popupBackColor : ModalPopupsDefaults.popupBackColor;
        ModalPopupsDefaults.popupFontColor = parameters.popupFontColor != null ? parameters.popupFontColor : ModalPopupsDefaults.popupFontColor;
        ModalPopupsDefaults.footerBackColor = parameters.footerBackColor != null ? parameters.footerBackColor : ModalPopupsDefaults.footerBackColor;
        ModalPopupsDefaults.footerFontColor = parameters.footerFontColor != null ? parameters.footerFontColor : ModalPopupsDefaults.footerFontColor;
        ModalPopupsDefaults.fontFamily = parameters.fontFamily != null ? parameters.fontFamily : ModalPopupsDefaults.fontFamily;
        ModalPopupsDefaults.fontSize = parameters.fontSize != null ? parameters.fontSize : ModalPopupsDefaults.fontSize;
    },

    Alert: function(id, title, message, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "Alert";

        //'Alert' specific parameters
        parameters.buttons = "ok";
        parameters.okButtonText = parameters.okButtonText != null ? parameters.okButtonText : ModalPopupsDefaults.okButtonText;

        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, message, parameters);
        var oPopupBody = myLayers[4];
        
        //'Alert' specific setup of Body
        oPopupBody.innerHTML = message;

        //Style all layers        
        ModalPopups._styleAllLayers(id, parameters, myLayers);
    },

    Confirm: function(id, title, question, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "Confirm";

        //'Confirm' specific parameters
        parameters.buttons = "yes,no";
        parameters.yesButtonText = parameters.yesButtonText != null ? parameters.yesButtonText : ModalPopupsDefaults.yesButtonText;
        parameters.noButtonText = parameters.noButtonText != null ? parameters.noButtonText : ModalPopupsDefaults.noButtonText;

        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, question, parameters);
        var oPopupBody = myLayers[4];
        
        //'Confirm' specific setup of Body
        oPopupBody.innerHTML = question;
        
        //Style all layers   
        ModalPopups._styleAllLayers(id, parameters, myLayers);
    },
    
    YesNoCancel: function(id, title, question, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "YesNoCancel";

        //'Confirm' specific parameters
        parameters.buttons = "yes,no,cancel";
        parameters.yesButtonText = parameters.yesButtonText != null ? parameters.yesButtonText : ModalPopupsDefaults.yesButtonText;
        parameters.noButtonText = parameters.noButtonText != null ? parameters.noButtonText : ModalPopupsDefaults.noButtonText;
        parameters.cancelButtonText = parameters.cancelButtonText != null ? parameters.cancelButtonText : ModalPopupsDefaults.cancelButtonText;

        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, question, parameters);
        var oPopupBody = myLayers[4];
        
        //'Confirm' specific setup of Body
        oPopupBody.innerHTML = question;
        
        //Style all layers   
        ModalPopups._styleAllLayers(id, parameters, myLayers);
    },
    
    Prompt: function(id, title, question, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "Prompt";
        
        //'Prompt' specific parameters
        parameters.buttons = "ok,cancel";
        parameters.okButtonText = parameters.okButtonText != null ? parameters.okButtonText : "OK";
        parameters.cancelButtonText = parameters.cancelButtonText != null ? parameters.cancelButtonText : "Cancel";
        
        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, question, parameters);
        var oPopupBody = myLayers[4];
        
        var txtStyle = "";
        if(parameters.width != null)
            txtStyle = "width:95%;";

        //'Prompt' specific setup of Body
        var txtHtml = question + "<br/>";
        txtHtml += "<input type=text id='" + id + "_promptInput' value='' " + 
            "style='border: solid 1px #859DBE; "  + txtStyle + "'>";

        oPopupBody.innerHTML = txtHtml;
        
        //Style all layers   
        ModalPopups._styleAllLayers(id, parameters, myLayers);

        //Focus input box        
        ModalPopupsSupport.findControl(id+"_promptInput").focus();
    },
    
    GetPromptInput: function(id) {
        var promptValue = ModalPopupsSupport.findControl(id+"_promptInput");
        return promptValue;
    },
    
    GetPromptResult: function(id) {
        var promptValue = ModalPopupsSupport.findControl(id+"_promptInput");
        return promptValue;
    },
    
    GetCustomControl: function(id) {
        return ModalPopupsSupport.findControl(id);
    },
    
    Indicator: function(id, title, message, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "Indicator";

        //'Indicator' specific parameters
        if(parameters.buttons == null)
            parameters.buttons = "";

        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, message, parameters);
        var oPopupBody = myLayers[4];
        
        //'Indicator' specific setup of Body
        oPopupBody.innerHTML = message;

        //Style all layers        
        ModalPopups._styleAllLayers(id, parameters, myLayers);
    },

    //Custom modal popup. parameters.buttons is a mandatory parameter
    Custom: function(id, title, contents, parameters) {
        //Get parameters
        parameters = parameters || {};
        if(!title) title = "Custom";
        
        if(parameters.buttons == null)
        {
            alert("buttons is a required parameter. ie: buttons: 'yes,no' or buttons: 'ok'.\nPossible buttons are yes, no, ok, cancel");
            return;
        }

        //Create layers
        var myLayers = ModalPopups._createAllLayers(id, title, contents, parameters);
        var oPopupBody = myLayers[4];
        
        //'Custom' specific setup of Body
        oPopupBody.innerHTML = contents;

        //Style all layers        
        ModalPopups._styleAllLayers(id, parameters, myLayers);
    },

    //Cancel/Close modal popup    
    Close: function(id) {
        window.onresize = null;
        window.onscroll = null;
    
        //try
        //{
            document.body.removeChild(ModalPopupsSupport.findControl(id+"_background"));
            document.body.removeChild(ModalPopupsSupport.findControl(id+"_popup"));
            document.body.removeChild(ModalPopupsSupport.findControl(id+"_shadow"));
        //}
        //catch
        //{
        //}
    },

    //Cancel/Close modal popup    
    Cancel: function(id) {
        ModalPopups.Close(id);
    },
    
     //Support variable to put each layer on top, increases everytime a modal popup is created
    _zIndex: 10000,
    
     //Support function to create all layers
    _createAllLayers: function(id, title, message, parameters) {
        //Create all 6 layers for; BackGround, Popup, Shadow, PopupTitle, PopupBody, PopupFooter
        var oBackground = ModalPopupsSupport.makeLayer(id+'_background', true, null);        // 0
        var oPopup = ModalPopupsSupport.makeLayer(id+'_popup', true, null);                  // 1
        var oShadow = ModalPopupsSupport.makeLayer(id+'_shadow', true, null);                // 2
        var oPopupTitle = ModalPopupsSupport.makeLayer(id+'_popupTitle', true, oPopup);      // 3
        var oPopupBody = ModalPopupsSupport.makeLayer(id+'_popupBody', true, oPopup);        // 4
        var oPopupFooter = ModalPopupsSupport.makeLayer(id+'_popupFooter', true, oPopup);    // 5
        
        //Set default values for button related parameters; OK, Yes, No, Cancel
        var okButtonText = parameters.okButtonText != null ? parameters.okButtonText : ModalPopupsDefaults.okButtonText;
        var yesButtonText = parameters.yesButtonText != null ? parameters.yesButtonText : ModalPopupsDefaults.yesButtonText;
        var noButtonText = parameters.noButtonText != null ? parameters.noButtonText : ModalPopupsDefaults.noButtonText;
        var cancelButtonText = parameters.cancelButtonText != null ? parameters.cancelButtonText : ModalPopupsDefaults.cancelButtonText;
        var onOk = parameters.onOk != null ? parameters.onOk : "ModalPopups.Close(\"" + id + "\");";
        var onYes = parameters.onYes != null ? parameters.onYes : "ModalPopups.Close(\"" + id + "\");";
        var onNo = parameters.onNo != null ? parameters.onNo : "ModalPopups.Close(\"" + id + "\");";
        var onCancel = parameters.onCancel != null ? parameters.onCancel : "ModalPopups.Close(\"" + id + "\");";
        
        //Create popup 'title' layer
        oPopupTitle.innerHTML = "<table cellpadding='0' cellspacing='0' style='border: 0;' height='100%'>" +
            "<tr><td valign='middle'><b>" + title + "</b></td></tr>" + 
            "</table>" ;
        
        //Create popup 'footer' layer
        oPopupFooter.innerHTML = "";
            
        //Split buttons parameter and create buttons; OK, Yes, No, Cancel
        parameters.fontFamily = parameters.fontFamily != null ? parameters.fontFamily : ModalPopupsDefaults.fontFamily;
        var bt = parameters.buttons.split(',');
        for(x in bt) {
            if(bt[x] == "ok")
                oPopupFooter.innerHTML += "<input name='" + id + "_okButton' id='" + id + "_okButton' type=button value='" + okButtonText + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + onOk + "'/>";
            if(bt[x] == "yes")
                oPopupFooter.innerHTML += "<input name='" + id + "_yesButton' id='" + id + "_yesButton' type=button value='" + yesButtonText + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + onYes + "'/>";
            if(bt[x] == "no")
                oPopupFooter.innerHTML += "<input name='" + id + "_noButton' id='" + id + "_noButton' type=button value='" + noButtonText + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + onNo + "'/>";
            if(bt[x] == "cancel")
                oPopupFooter.innerHTML += "<input name='" + id + "_cancelButton' id='" + id + "_cancelButton' type=button value='" + cancelButtonText + "' style='font-family:Verdana,Arial; font-size:8pt; border: solid 1px #859DBE; background-color: white; width:75px; height:20px; margin-right: 5px; margin-left: 5px;' onclick='" + onCancel + "'/>";
        }
        
        //Create popup 'body' layer, is done in; Alert, Confirm, YesNoCancel, Prompt and Custom functions.
        var allLayers = new Array(oBackground, oPopup, oShadow, oPopupTitle, oPopupBody, oPopupFooter);
        
        if(parameters.autoClose != null )
            setTimeout('ModalPopups.Close(\"'+id+'\");', parameters.autoClose);

        return allLayers;
    },
    
     //Support function to style and position all layers
    _styleAllLayers: function(id, parameters, allLayers) {
        var myLayers = allLayers;
        var oBackground = myLayers[0];
        var oPopup = myLayers[1];
        var oShadow = myLayers[2];
        var oPopupTitle = myLayers[3];
        var oPopupBody = myLayers[4];
        var oPopupFooter = myLayers[5];
        
        ModalPopups._zIndex += 3;
        var zIndex = ModalPopups._zIndex;

        //Get Css parameters for borderColor. 
        parameters.borderColor = parameters.borderColor != null ? parameters.borderColor : ModalPopupsDefaults.borderColor;  // #859DBE

        //Default css for; oBackground, oPopup and oShadow layers
        //Position elements excluded (except for background); top, left, width, height. 
        //They will be calculated by contents of oPopup, or set by the parameters.
		var cssBackground = "display:inline; position:absolute; z-index: " + (zIndex) + "; left:0px; top:0px; width:100%; height:100%; filter:alpha(opacity=70); opacity:0.7;";
        if(ModalPopupsSupport.isOlderIE()) {
	        var viewport = ModalPopupsSupport.getViewportDimensions();
        	cssBackground = "display:inline; position:absolute; z-index: 10; left:0px; top:0px; width:" + viewport.width + "px; height:" + viewport.height + "px; filter:alpha(opacity=70); opacity:0.7; overflow:hidden;";
        }
        var cssShadow = "display:inline; position:absolute; z-index: " + (zIndex+1) + ";"; 
        var cssPopup = "display:inline; position:absolute; z-index: " + (zIndex+2) + "; background-color:white; color:black; border:solid 1px " + parameters.borderColor + "; padding:1px;"; // background-color:#EEF1F2

        //Get Css parameters for oBackGround layer. 
        parameters.backgroundColor = parameters.backgroundColor != null ? parameters.backgroundColor : ModalPopupsDefaults.backgroundColor;
        cssBackground += " background-color:" + parameters.backgroundColor + ";";

        //Css for oPopup content layers. (oPopupTitle, oPopupBody, oPopupFooter)
        parameters.fontFamily = parameters.fontFamily != null ? parameters.fontFamily : ModalPopupsDefaults.fontFamily;
        parameters.fontSize = parameters.fontSize != null ? parameters.fontSize : ModalPopupsDefaults.fontSize;
        var cssPopupTitle = "position: absolute; font-family:" + parameters.fontFamily + "; font-size:" + parameters.fontSize + "; padding: 5px; text-align:left;";
        var cssPopupBody = "position: absolute; font-family:" + parameters.fontFamily + "; font-size:" + parameters.fontSize + "; padding: 5px; text-align:left;";
        var cssPopupFooter = "position: absolute; font-family:" + parameters.fontFamily + "; font-size:" + parameters.fontSize + "; padding: 5px; text-align:center;";

        //First style the contents of the oPopup layer. (oPopupTitle, oPopupBody, oPopupFooter)
        //When this is done we can calculate the height and width of the oPopup contents.
        if(ModalPopupsSupport.isIE) {
            oPopupTitle.style.cssText = cssPopupTitle;
            oPopupBody.style.cssText = cssPopupBody; 
            oPopupFooter.style.cssText = cssPopupFooter; 
        }
        else { 
            oPopupTitle.setAttribute("style", cssPopupTitle);
            oPopupBody.setAttribute("style", cssPopupBody);
            oPopupFooter.setAttribute("style", cssPopupFooter);
        } 

        //Get css color related parameters for; oPopup, oPopupTitle, oPopupBody, oPopupFooter.
        parameters.titleBackColor = parameters.titleBackColor != null ? parameters.titleBackColor : ModalPopupsDefaults.titleBackColor;
        parameters.titleFontColor = parameters.titleFontColor != null ? parameters.titleFontColor : ModalPopupsDefaults.titleFontColor;
        parameters.popupBackColor = parameters.popupBackColor != null ? parameters.popupBackColor : ModalPopupsDefaults.popupBackColor;
        parameters.popupFontColor = parameters.popupFontColor != null ? parameters.popupFontColor : ModalPopupsDefaults.popupFontColor;
        parameters.footerBackColor = parameters.footerBackColor != null ? parameters.footerBackColor : ModalPopupsDefaults.footerBackColor;
        parameters.footerFontColor = parameters.footerFontColor != null ? parameters.footerFontColor : ModalPopupsDefaults.footerFontColor;
        cssPopupTitle += " background-color:" + parameters.titleBackColor + ";";
        cssPopupTitle += " color:" + parameters.titleFontColor + ";";
        cssPopupBody += " background-color:" + parameters.popupBackColor + ";";
        cssPopupBody += " color:" + parameters.popupFontColor + ";";
        cssPopupFooter += " background-color:" + parameters.footerBackColor + ";";
        cssPopupFooter += " color:" + parameters.footerFontColor + ";";

        //Calculate maxWidth of the 3 layers in oPopup. (oPopupTitle,oPopupBody,oPopupFooter)
        var calcMaxWidth = 0;
        if(ModalPopupsSupport.getLayerWidth(oPopupTitle.id) > calcMaxWidth) 
            calcMaxWidth = ModalPopupsSupport.getLayerWidth(oPopupTitle.id);
        if(ModalPopupsSupport.getLayerWidth(oPopupBody.id) > calcMaxWidth)
            calcMaxWidth = ModalPopupsSupport.getLayerWidth(oPopupBody.id);  
        if(ModalPopupsSupport.getLayerWidth(oPopupFooter.id) > calcMaxWidth)
            calcMaxWidth = ModalPopupsSupport.getLayerWidth(oPopupFooter.id);   
                        
        //Calculate total height of the 3 layers in oPopup. (oPopupTitle+oPopupBody+oPopupFooter)
        var calcTotalHeight = ModalPopupsSupport.getLayerHeight(oPopupTitle.id) + ModalPopupsSupport.getLayerHeight(oPopupBody.id) + ModalPopupsSupport.getLayerHeight(oPopupFooter.id);    
        
        parameters.width = parameters.width != null ? parameters.width : (calcMaxWidth + 4); // Add 4px for; padding: 1px and border: 1px;
        parameters.height = parameters.height != null ? parameters.height : calcTotalHeight; // Set height as height of; oPopupTitle + oPopupBody + oPopupFooter
        
        //Eerst hoogte oPopupBody aanpassen indien parameters.height is meegegeven
        var newBodyHeight = ModalPopupsSupport.getLayerHeight(oPopupBody.id)
        if(parameters.height > calcTotalHeight) {
            // Sub 10px for; padding: 5px;
            newBodyHeight = parameters.height - ModalPopupsSupport.getLayerHeight(oPopupTitle.id) - ModalPopupsSupport.getLayerHeight(oPopupFooter.id);
            cssPopupBody += " height:" + newBodyHeight + "px;";
            calcTotalHeight = ModalPopupsSupport.getLayerHeight(oPopupTitle.id) + newBodyHeight + ModalPopupsSupport.getLayerHeight(oPopupFooter.id);  
        }
        
        cssPopupTitle += " top:1px;";
        cssPopupBody += " top:" + ModalPopupsSupport.getLayerHeight(oPopupTitle.id) + "px;";
        cssPopupFooter += " top:" + (ModalPopupsSupport.getLayerHeight(oPopupTitle.id) + (newBodyHeight) /*ModalPopupsSupport.getLayerHeight(oPopupBody.id)*/) + "px;";
        cssPopupTitle += " width:" + (parameters.width - 10) + "px;"; // Sub 10px for; padding-left+right: 5px;
        cssPopupBody += " width:" + (parameters.width - 10) + "px;"; // Sub 10px for-left+right; padding: 5px;
        cssPopupFooter += " width:" + (parameters.width - 10) + "px;"; // Sub 10px for-left+right; padding: 5px;
        
         //Get browser width and height
        var frameWidth = ModalPopupsSupport.getFrameWidth();
        var frameHeight = ModalPopupsSupport.getFrameHeight();
        
        if(parameters.height < calcTotalHeight)
            parameters.height = calcTotalHeight;
        
        //Get parameters for oPopup layer.
        parameters.top = parameters.top != null ? parameters.top : ((frameHeight/2) - (parameters.height/2));
        parameters.left = parameters.left != null ? parameters.left : ((frameWidth/2) - (parameters.width/2));

        //Set modal popup position
        //cssPopup += " top:" + parameters.top + "px;";
        //cssPopup += " left:" + parameters.left + "px;";
        
        cssPopupTitle += " left:1px;";
        cssPopupBody += " left:1px;";
        cssPopupFooter += " left:1px;";
        
        if(parameters.width) 
            cssPopup += " width:" + parameters.width + "px;";
        else
            cssPopup += " width:" + parameters.maxWidth + "px;";
            
        if(parameters.height) 
            cssPopup += " height:" + (parameters.height-1) + "px;";
        else
            cssPopup += " height:" + (calcTotalHeight-1) + "px;";
        
        //First style the contents of the oPopup layer. (oPopupTitle, oPopupBody, oPopupFooter)
        //When this is done we can calculate the height and width of the oPopup contents.
        if(ModalPopupsSupport.isIE) {
            oPopupTitle.style.cssText = cssPopupTitle;
            oPopupBody.style.cssText = cssPopupBody; 
            oPopupFooter.style.cssText = cssPopupFooter; 
        }
        else { 
            oPopupTitle.setAttribute("style", cssPopupTitle);
            oPopupBody.setAttribute("style", cssPopupBody);
            oPopupFooter.setAttribute("style", cssPopupFooter);
        }   

        //Setup shadow layer
        parameters.shadow = parameters.shadow != null ? parameters.shadow : ModalPopupsDefaults.shadow;
        parameters.shadowSize = parameters.shadowSize != null ? parameters.shadowSize : ModalPopupsDefaults.shadowSize;
        if(parameters.shadow) {
            //Get parameters for oShadow layer.
            parameters.shadowSize = parameters.shadowSize != null ? parameters.shadowSize : ModalPopupsDefaults.shadowSize;
            parameters.shadowColor = parameters.shadowColor != null ? parameters.shadowColor : ModalPopupsDefaults.shadowColor;
            cssShadow += "background-color:" + parameters.shadowColor + ";"; 
        
            //cssShadow += " top:" + (parameters.top + parameters.shadowSize) + "px;";
            //cssShadow += " left:" + (parameters.left + parameters.shadowSize) + "px;";
            if(parameters.width) 
                cssShadow += " width:" + parameters.width + "px;";
            else
                cssShadow += " width:" + maxWidth + "px;";
            if(parameters.height) 
                cssShadow += " height:" + (parameters.height-1) + "px;";
            else
                cssShadow += " height:" + (calcTotalHeight) + "px;";
            
        }
        else {
            cssShadow += " display:none;";
        }
        
        //Secondly style the contents of the main layers. (oBackGround, oPopup, oShadow)
        if(ModalPopupsSupport.isIE) {
            oPopup.style.cssText = cssPopup; 
            oShadow.style.cssText = cssShadow; 
            oBackground.style.cssText = cssBackground; 
        }
        else {
            oPopup.setAttribute("style", cssPopup);
            oShadow.setAttribute("style", cssShadow);
            oBackground.setAttribute("style", cssBackground);
        }
        
        if(!ModalPopupsSupport.isOlderIE()) {
	        ModalPopupsSupport.centerElement(document.getElementById(id+'_background'), 0, true);
		}
		else {
			var viewport = ModalPopupsSupport.getViewportDimensions();
			oBackground.innerHTML = "<div><iframe style='z-index:-1; position:absolute; top:0;left:0 display:none; display/**/:block; position:absolute; filter:mask(); width:" + viewport.width + "px; height:" + viewport.height + "px;' id='corr_bug_ie' src='../common/imgLay/spinner.gif'></iframe></div>";
		}
        ModalPopupsSupport.centerElement(document.getElementById(id+'_popup'), 0, false);
        if(parameters.shadow)
            ModalPopupsSupport.centerElement(document.getElementById(id+'_shadow'), parameters.shadowSize, false);
        
        //Load file?
        parameters.loadTextFile = parameters.loadTextFile != null ? parameters.loadTextFile : "";
        if(parameters.loadTextFile != "")
            ModalPopups._loadTextFile(id, parameters, allLayers, parameters.loadTextFile);
            
//        parameters.autoClose = parameters.autoClose != null ? parameters.autoClose : 0;
//        if(!parameters.autoClose)
//        {
        window.onresize = function() {
            ModalPopupsSupport.centerElement(document.getElementById(id+'_background'), 0, true);
            ModalPopupsSupport.centerElement(document.getElementById(id+'_popup'), 0, false);
            if(parameters.shadow) {
                ModalPopupsSupport.centerElement(document.getElementById(id+'_shadow'), parameters.shadowSize, false);
                }
            }
            
        window.onscroll = function() {
            ModalPopupsSupport.centerElement(document.getElementById(id+'_background'), 0, true);
            ModalPopupsSupport.centerElement(document.getElementById(id+'_popup'), 0, false);
            if(parameters.shadow) {
                ModalPopupsSupport.centerElement(document.getElementById(id+'_shadow'), parameters.shadowSize, false);
                }
        }

        //}
     },
     
     //Support function to load text file via AJAX call
     _loadTextFile: function(id, parameters, allLayers, filename)
     {
        var objXml = ModalPopupsSupport.getXmlHttp(); 
        objXml.open("GET", filename, true);
        objXml.onreadystatechange=function() 
        {
            if (objXml.readyState==4) 
            {
                var txt = objXml.responseText.replace("\r\n","<br>").replace("\n\r","<br>").replace("\n","<br>").replace("\r","<br>");
                var html = "<div style='overflow-y: scroll; position:absolute; " + 
                    "top:5px; left:5px; height:" + (parameters.height - 65) + "px; " + 
                    "width:" + (parameters.width - 10) + "px;'>";
                html += txt;
                html += "</div>";
                ModalPopups.GetCustomControl(id+"_popupBody").innerHTML = html;
                parameters.loadTextFile = "";
                ModalPopups._styleAllLayers(id, parameters, allLayers);
            }
        }
        objXml.send(null);
    }
};

var ModalPopupsSupport = {
    isIE: function() {
        return (window.ActiveXObject) ? true : false;
    },

    isOlderIE: function() {
		var ver = -1; // Return value assumes failure.
		if (navigator.appName == 'Microsoft Internet Explorer') {
			var ua = navigator.userAgent;
			var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
			if (re.exec(ua) != null) {
				ver = parseFloat( RegExp.$1 );
		    }
		}
		if ( ver > -1 && ver < 7.0 ) {
			return true;
		} else {
			return false;
		}
    },
    
    makeLayer : function(id,layerVisible,layerParent) {
        var container = document.createElement("div");
        container.id = id;
        
        if(layerParent)
            layerParent.appendChild(container);
        else
            document.body.appendChild(container);
        
        return container;
    },
    
    deleteLayer: function(id) {
        var del = findLayer(id);
        if(del) 
            document.body.removeChild(del);
    },
    
    findLayer: function(id) {
        return document.all ? document.all[id] : document.getElementById(id);
    },
        
    findControl: function(id, parent) {
        if(parent == null)
        {  
            return document.all ? document.all[id] : document.getElementById(id);
        }
        else
        {
            return document.all ? document.all[id] : document.getElementById(id);
        }
    },
    
    getLayerHeight: function(id) {
        if (document.all) {
            gh = document.getElementById(id).offsetHeight;  
        }
        else {
            gh = document.getElementById(id).offsetHeight;  //-5;
        }
        return gh;
    },
    
    getLayerWidth: function(id) {
        gw = document.getElementById(id).offsetWidth;
        return gw;
    },
    
    getViewportDimensions: function() {
        var intH = 0, intW = 0;
        
        if(self.innerHeight) {
           intH = window.innerHeight;
           intW = window.innerWidth;
        } 
        else {
            if(document.documentElement && document.documentElement.clientHeight) {
                intH = document.documentElement.clientHeight;
                intW = document.documentElement.clientWidth;
            }
            else {
                if(document.body) {
                    intH = document.body.clientHeight;
                    intW = document.body.clientWidth;
                }
            }
        }

        return {
            height: parseInt(intH, 10),
            width: parseInt(intW, 10)
        };
    },
    
    getScrollXY: function() {
        var scrOfX = 0, scrOfY = 0;
        if( typeof( window.pageYOffset ) == 'number' ) {
            //Netscape compliant
            scrOfY = window.pageYOffset;
            scrOfX = window.pageXOffset;
        } else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
            //DOM compliant
            scrOfY = document.body.scrollTop;
            scrOfX = document.body.scrollLeft;
        } else if( document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
            //IE6 standards compliant mode
            scrOfY = document.documentElement.scrollTop;
            scrOfX = document.documentElement.scrollLeft;
        }
        return [ scrOfX, scrOfY ];
        },
    
    centerElement: function(elem,add,noleft) {
        var viewport = ModalPopupsSupport.getViewportDimensions();
        var left = (viewport.width == 0) ? 50 : parseInt((viewport.width - elem.offsetWidth) / 2, 10);
        var top = (viewport.height == 0) ? 50 : parseInt((viewport.height - elem.offsetHeight) / 2, 10);
        var scroll = ModalPopupsSupport.getScrollXY();
        //alert(scroll[1]);

        if(!noleft) {
            elem.style.left = (left + add) + 'px';
        }
        elem.style.top = (top + add + scroll[1]) + 'px';

        viewport, left, top, elem = null;    
    },
    
    readFile: function(filename, intoElement) {
        var xmlHttp = getXmlHttp();
        var file = filename+"?r="+Math.random();
        xmlHttp.open("GET", file, true);
        xmlHttp.onreadystatechange=function() 
        {
            if (xmlHttp.readyState==4) 
            {
                intoElement.innerHTML = xmlHttp.responseText;
            }
        }
        xmlHttp.send(null);
    },
        
    getFrameWidth: function() {
        var frameWidth = document.documentElement.clientWidth;
        if (self.innerWidth) // Als de browser deze manier van aanroepen hanteerd
        {
            frameWidth = self.innerWidth; // Haal de frame-width op
        }
        else if (document.documentElement && document.documentElement.clientWidth)  // Als de browser deze manier van aanroepen hanteerd
        {
            frameWidth = document.documentElement.clientWidth; // Haal de frame-width op
        }
        else if (document.body)  // Als de browser deze manier van aanroepen hanteerd
        {
            frameWidth = document.body.clientWidth; // Haal de frame-width op
        }
        else return;
        return frameWidth;
    },
    
    getFrameHeight: function() {
        var frameHeight = document.documentElement.clientHeight;
        if (self.innerWidth) // Als de browser deze manier van aanroepen hanteerd
        {
            frameHeight = self.innerHeight; // Haal de frame-height op
        }
        else if (document.documentElement && document.documentElement.clientWidth)  // Als de browser deze manier van aanroepen hanteerd
        {
            frameHeight = document.documentElement.clientHeight; // Haal de frame-height op
        }
        else if (document.body)  // Als de browser deze manier van aanroepen hanteerd
        {
            frameHeight = document.body.clientHeight; // Haal de frame-height op
        }
        else return;
        return frameHeight;
    },
    
    getXmlHttp: function()
    {
        var xmlHttp;
        try
        {  // Firefox, Opera 8.0+, Safari  
            xmlHttp=new XMLHttpRequest();  
        }
        catch (e)
        {  // Internet Explorer  
            try
            {    
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");    
            }
            catch (e)
            {    
                try
                {      
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");      
                }
                catch (e)
                {      
                    alert("Your browser does not support AJAX!");      
                    return false;      
                }    
            }  
        }  
        return xmlHttp;
    }
    
    

};




