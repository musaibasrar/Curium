



function numbersandalphabets(evt)
         
            {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if ( charCode != 8 && (charCode < 65 || charCode > 90)   && (charCode < 48 || charCode > 57) && (charCode < 97 || charCode > 122) )
            return false;

            return true;
            }

 function floatingpointnumbers(evt)
            {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if ((charCode != 46 && charCode != 8 )&& (charCode < 48 || charCode > 57)  )
            return false;

            return true;
            }