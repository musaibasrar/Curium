// JScript File
var attempts = 0;  
var timerHandle = setTimeout("checkIfHilighterLoaded()", 200);  
function checkIfHilighterLoaded()
{    
    try
    {      
        dp.SyntaxHighlighter.ClipboardSwf = '/Frontend/SyntaxHighlighter/Scripts/clipboard.swf';            
        dp.SyntaxHighlighter.HighlightAll('code');              
        clearTimeout(timerHandle);    
    }    
    catch(e)
    {      
        clearTimeout(timerHandle);      
        if(attempts < 25)
        {        
            timerHandle = setTimeout("checkIfHilighterLoaded()", 200);
        }      
        attempts++;    
    } 
    //alert('1');
}



