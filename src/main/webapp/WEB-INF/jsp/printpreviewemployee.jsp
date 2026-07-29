<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Staff ID</title>

<!-- <link rel="stylesheet" href="style.css"> -->
<style>
/* ===============================
   RESET
================================= */

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{

    background:#dcdcdc;

    display:flex;

    justify-content:left;

    height:100vh;

    font-family:Arial, Helvetica, sans-serif;
    

}


/* ===============================
        CARD
================================= */

.idCard{

    width:210px;

    height:322px;

    background:#ffffff;

    position:relative;

    overflow:hidden;

    border-radius:12px;

    border:1px solid #cfcfcf;

    box-shadow:0 3px 10px rgba(0,0,0,.25);
    
    margin-left:10px;

}


/* ===============================
      SVG
================================= */

.headerSVG{

    position:absolute;

    left:0;

    top:0;

    width:210px;

    height:110px;

    z-index:1;

}

.footerSVG{

    position:absolute;

    left:0;

    bottom:0;

    width:210px;

    height:95px;

    z-index:1;

}


/* ===============================
      SCHOOL
================================= */

.school{

    position:absolute;

    width:100%;

    top:8px;

    left:0;

    text-align:center;

    z-index:20;

}

.schoolName{

    color:#ffffff;

    font-size:17px;

    font-weight:900;

    letter-spacing:.5px;

    line-height:18px;

    text-transform:uppercase;

}

.address{

    margin-top:5px;

    color:#d9ad64;

    font-size:7px;

    font-weight:bold;

}

.phone{

    margin-top:4px;

    color:#ffffff;

    font-size:8px;

    font-weight:bold;

}


/* ===============================
      LOGO
================================= */

.logo{

    position:absolute;

    left:10px;

    top:48px;

    width:38px;

    height:38px;

    background:#ffffff;

    border-radius:50%;

    border:1px solid #d4b06d;

    display:flex;

    justify-content:center;

    align-items:center;

    z-index:30;

}

.logo img{

    width:31px;

    height:31px;

    border-radius:50%;

}


/* ===============================
      PHOTO
================================= */

.photoOuter{

    position:absolute;

    top:74px;

    left:50%;

    transform:translateX(-50%);

    width:118px;

    height:118px;

    background:#d73e36;

    border-radius:50%;

    padding:3px;

    z-index:40;

}

.photoBlack{

    width:100%;

    height:100%;

    background:#2f2f2f;

    border-radius:50%;

    padding:3px;

}

.photo{

    width:100%;

    height:100%;

    background:#ffffff;

    border-radius:50%;

    overflow:hidden;

}

.photo img{

    width:100%;

    height:100%;

    object-fit:cover;

}


/* ===============================
      DETAILS
================================= */

.content{

    position:absolute;

    top:198px;

    width:100%;

    text-align:center;

    z-index:20;

}

.staffName{

    color:#d63f35;

    font-size:14px;

    font-weight:bold;

    letter-spacing:1px;

    text-transform:uppercase;

}

.designation{

    margin-top:6px;

    color:#4b4ba1;

    font-size:9px;

    font-weight:bold;

    letter-spacing:.5px;

}

.mobile{

    margin-top:10px;

    font-size:10px;

    color:#222;

    font-family:"Times New Roman", serif;

}


/* ===============================
      FOOTER
================================= */

.footer{

    position:absolute;

    bottom:7px;

    width:100%;

    text-align:center;

    color:#ffffff;

    font-size:9px;

    font-weight:bold;

    letter-spacing:1px;

    z-index:30;

}


/* ===============================
      PRINT
================================= */

@media print{

body{

    background:white;

}

.idCard{

    box-shadow:none;

    margin:0;

    width:210px;

    height:322px;

}

}



</style>
<style>
 .print-btn{
            text-align: center;
            margin: 10px;
        }

        @media print{
            .print-btn{
                display: none;
            }
        }

</style>

</head>

<body>
 <c:set var="iInitial" value="${iInitial}"/>
         <c:set var="limit" value="1"/>
                        
          <c:forEach begin="1" end="${endValue}">
                        <%!                        
                            int i = 1;
                        %>
			<c:if test="${limit < iInitial}">	
<div class="idCard">

<!-------------------------
TOP SVG
-------------------------->

<svg class="headerSVG"
viewBox="0 0 210 110"
preserveAspectRatio="none">

<path
fill="#222429"
d="
M0,0
H210
V68

C190 78
170 95
145 98

C120 101
88 92
58 84

C33 77
15 70
0 58

Z"/>

<!-- White Wave -->

<path
fill="white"
d="
M0 64

C25 76
55 88
82 92

C116 97
148 89
178 72

C191 65
201 58
210 52

V110

H0

Z"/>

<!-- Green Shape -->

<path

fill="#4BA443"

d="
M210 28

C188 34
170 46
156 66

C171 72
188 72
210 65

Z"/>

</svg>

<!-------------------------
LOGO
-------------------------->

<div class="logo">

<img src="/hira/images/hira.png" />

</div>

<!-------------------------
HEADER
-------------------------->

<div class="school">

<div class="schoolName">

HIRA PUBLIC SCHOOL

</div>

<div class="address">

Arehalli, Belur Taluk, Hassan Dist.

</div>

<div class="phone">

Cell : 8277201635

</div>

</div>

<!-------------------------
PHOTO
-------------------------->

<div class="photoOuter">

<div class="photoBlack">

<div class="photo">

<img src="data:image;base64,<%= request.getSession().getAttribute("employeephoto" + i + "")%>"  alt="Photo" />

</div>

</div>

</div>

<!-------------------------
DETAILS
-------------------------->

<div class="content">

<div class="staffName">

<%= request.getSession().getAttribute("teachername" + i + "")%>

</div>

<div class="designation">

<%= request.getSession().getAttribute("designation" + i + "")%>

</div>

<div class="mobile">

Cell : <%= request.getSession().getAttribute("contactnumber" + i + "")%>

</div>

</div>

<!-------------------------
BOTTOM SVG
-------------------------->

<svg class="footerSVG"

viewBox="0 0 210 95"

preserveAspectRatio="none">

<!-- black -->

<path

fill="#25262C"

d="

M0 95

L0 54

C10 63

26 73

55 80

L55 95

Z"/>

<!-- green -->

<path

fill="#4AA33E"

d="

M52 95

C62 55

96 40

132 50

C160 58

178 70

188 95

Z"/>

<!-- footer -->

<rect

x="0"

y="73"

width="210"

height="22"

fill="#4D5937"/>

</svg>

<div class="footer">

DISE : 29230426413

</div>

</div>
 </c:if>
   <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach>
                    <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>
                      
                       <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>
</body>

</html>