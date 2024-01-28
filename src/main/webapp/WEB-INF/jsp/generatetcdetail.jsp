
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate TC Detail</title>
        <link rel="stylesheet" href="/shatabdi/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/shatabdi/css/validation/jquery.ketchup.css">

        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
        <script src="/shatabdi/js/datePicker/ui/jquery.ui.core.js"></script>
        <script src="/shatabdi/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="/shatabdi/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script src="/shatabdi/js/datePicker/ui/jquery.ui.tabs.js"></script>
        <script src="/shatabdi/js/datePicker/ui/sliderAccess.js"></script>
        <script src="/shatabdi/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery.ui.button.js"></script>
        <link rel="stylesheet" href="/shatabdi/css/datePicker/demos.css">





        <style type="text/css">

            .headerSearch{
                font-size: 18px;
                font-weight: bold;

            }

            .myclass{
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                width: auto;
                height: auto;
                color: black;
                text-transform:capitalize;
            }
            <!--
            .divCSS{
                overflow:  scroll;
                height: 100%;
                width: 100%;
            }

            .fiedlSet {
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-width: 1;
                width: 100%;
                color: #000000;
                font-size: 16px;
                font-weight: bold;
                font-variant: normal;
                font-stretch: wider;
                background-color: #e2ebf3;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
            }
            .legendCSS {
                color: #666666;
            }
            .tableCSS {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0px;
                top: 0px;
            }
            .textAreaCSS {
                height: auto;
                width: auto;
            }
            .textField {
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;


            }
            .alignRight {
                font-family: Tahoma;
                font-size: 12px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            
            .alignRightYear {
                font-family: ariel;
                font-size: 14px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignRightHeader {
								font-family: Tahoma;
								font-weight: bold;
								color: #5E87B0;
							    font-size: 16px;
            }

            .alignRightHead {
                font-family: Tahoma;
                font-size: 12px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;


                font-weight: bold;
            }



            .alignRightMultiple {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bolder;
                text-align: right;
                vertical-align: middle;
                font-style: normal;
                color: #325F6D;
            }
            .alignCentreMultiple {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bolder;
                text-align: center;
                vertical-align: middle;
                font-style: normal;
                color: #000000;
            }
            .autoAdjust {
                height: auto;
                width: auto;
            }
            .radioSpanCSS {
                font-size: 12px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: left;
                vertical-align: middle;
            }
            .radioCSS {
                background-position: left center;
            }
            .spanText {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bold;
                color: #000000;
            }
            .emptyFieldSet {
                border-top-color: #FA7676;
                border-right-color: #FA7676;
                border-bottom-color: #FA7676;
                border-left-color: #FA7676;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-width: thin;
                border-right-width: thin;
                border-bottom-width: thin;
                border-left-width: thin;
                background-image: url(images/close.JPG);
                background-repeat: repeat-y;
                background-attachment: scroll;
                background-position: right;
                width: auto;
                height: auto;
                display: inline;
            }
            .style1 {
                font-family: Tahoma;
                font-size: 14px;
            }
            .style2 {
                color: #666666;
                font-family: Tahoma;
                font-size: 14px;
            }
            .style4 {
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                vertical-align: middle;
                color: #325f6d;
            }



            -->

            .alignRight1 {
                font-family: Tahoma;
                font-size: 15px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .mandatoryClass {
    font-family: Tahoma;
    font-size: 11px;
    color: red;
    font-style: normal;
    text-transform: capitalize;
    
    text-align: right;
    vertical-align: middle;
    font-weight: bold;
}
        </style>

        <script type="text/javascript" src="/shatabdi/js/datetimepicker_css.js"></script>

        <script type="text/javascript">
        
           
            $(function() {

        		$("#tabs").tabs();
        		$("#save").button().click(function() {
        			generateDetail();
        		});
        		$("#effect").hide();

        	});

            function generateDetail() {

                var form1 = document.getElementById("form1");
                form1.action = "/shatabdi/DocumentsProcess/tcDetail";
               
                form1.submit();
                
            }
        </script>

    </head>
    
   
    <body onload="hide()"><form id="form1"  >
            

                    <div align="center">
                    <br/><br/>
                    <p class="alignRightHeader">Generate TC Detail</p>
                    
                    
                    </div>
                            
                                <table width="100%" >
                                    <tr>

                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">

                                            <button id="save" >Submit</button>

                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <!-- <button id="update" >Update</button> -->

                                        </td>


                                    </tr>
                                </table>

        </form>
        
    </body>
</html>


