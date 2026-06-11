<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style type="text/css">
<!--
.mark{
    border:1px solid black;
    border-collapse: collapse;
}


</style>
<style>
 .rightside{
        float:right;
        }
</style>
<style>
@page {
    size: A4;
    margin: 10mm;
}
    .page {
    width: 190mm;
    min-height: 277mm;
    border: 1px solid black;
    padding: 10px;
    box-sizing: border-box;
    margin: auto; 
}

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
	<script type="text/javascript" src="/roshan/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/roshan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Character Certificate</title>
        <script type="text/javascript">
            // window.onload = function(){
            //	 window.print();
            // }
        </script>
</head>

<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form id="form1" method="post" class="bodymargin">
        <div class="page">
            <table width="100%">
                <tr>
                    <td style="text-align: left;"></td>
                    <td style="text-align: center;">ACADEMIC PERFORMANCE (2025 – 2026)</td>
                    <td style="text-align: right;"></td>
                </tr>
                 <tr>
                    <td style="text-align: left;">Admission NO:  </td>
                    <td style="text-align: center;">Name: Amal Ahmed Geabel AL Saeedi  (2025 – 2026)</td>
                    <td style="text-align: right;">Grade: LKG </td>
                </tr>
                 <tr>
                    <td style="text-align: left;"></td>
                    <td style="text-align: center;">ANNUAL REPORT CARD</td>
                    <td style="text-align: right;"></td>
                </tr>
            </table>
            <table width="100%" class="mark">
                <tr>
                    <td class="mark">SCHOLASTIC <br>
AREAS</td>
                    <td class="mark" colspan="2">EVALUATION 1 </td>
                    <td class="mark" colspan="2">EVALUATION 2</td>
                    <td class="mark" colspan="2">EVALUATION 3</td>
                </tr>
                <tr>
                    <td class="mark">Subject</td>
                    <td class="mark">Oral</td>
                    <td class="mark">written</td>
                    <td class="mark">Oral</td>
                    <td class="mark">written</td>
                    <td class="mark">Oral</td>
                    <td class="mark">written</td>
                </tr>
                 <tr>
                    <td class="mark">ENGLISH </td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark"></td>
                    <td class="mark"></td>
                </tr>
                 <tr>
                    <td class="mark">MATHEMATICS</td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark"></td>
                    <td class="mark"></td>
                </tr>
                 <tr>
                    <td class="mark">SCIENCE</td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark"></td>
                    <td class="mark"></td>
                </tr>
                 <tr>
                    <td class="mark">ISLAMIC STUDIES</td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark"></td>
                    <td class="mark"></td>
                </tr>
                 <tr>
                    <td class="mark">ART & CRAFT</td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark"></td>
                    <td class="mark"></td>
                </tr>
            </table>
            <h5>PERSONALITY DEVELOPMENT </h5>
             <table width="100%" class="mark">
                <tr>
                    <td class="mark">Personal and Social Traits</td>
                    <td class="mark" >EVALUATION 1 </td>
                    <td class="mark" >EVALUATION 2</td>
                    <td class="mark" >EVALUATION 3</td>
                </tr>
                <tr>
                    <td class="mark">Courteousness </td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Confidence  </td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Care of belongings </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr>
                 <tr>
                    <td class="mark">Neatness</td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Regularity and Punctuality </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Sharing and caring </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr> 
                <tr>
                    <td class="mark">Respect for other’s Property </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr>
                <tr>
                    <td class="mark">Self-control </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr>
                <tr>
                    <td class="mark">Specific Achievement  </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr>
            </table>
             <h5  style="margin: 4px;">&emsp; </h5>
             <table width="100%" class="mark">
                <tr>
                    <td class="mark">CO-SCHOLASTIC AREAS</td>
                    <td class="mark" >EVALUATION 1 </td>
                    <td class="mark" >EVALUATION 2</td>
                    <td class="mark" >EVALUATION 3</td>
                </tr>
                <tr>
                    <td class="mark">Games </td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Work Education  </td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Discipline </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    
                </tr>
                 <tr>
                    <td class="mark">Health/Physical Education</td>
                     <td class="mark">height / weight</td>
                    <td class="mark">height / weight</td>
                    <td class="mark">height / weight</td>
                   
                </tr>
                 <tr>
                    <td class="mark">Attendance </td>
                     <td class="mark">A1</td>
                    <td class="mark">A1</td>
                    <td class="mark">A1</td>
                   
                </tr>
             </table>
             <p style="margin-top: 0px;font-size: 11px;text-align: left;">REMARKS: All over good in all Aspects </p>
             <p style="font-size: 11px;text-align: left;">RESULT: PASSED </p>
             <p style="margin-bottom: 0px;font-size: 11px;text-align: left;">DATE: 30-October 2025 CLASS TEACHER'S SIGNATURE_________ PARENT’S SIGNATURE__________</p>
              <table class="mark">
                <tr>
                    <td class="mark">
                        <table>
                            <tr>
                                <td style="text-align: left;">The School Principal </td>
                                <td></td>
                                <td style="text-align: right;"> المدرسةم</td>
                            </tr>
                            <tr>
                                <td style="text-align: left;">Name </td>
                                 <td>Ms. SOHA ALBISAIS </td>
                                <td style="text-align: right;"> : 

اسم
</td>
                            </tr>
                             <tr>
                                <td style="text-align: left;">Date </td>
                                 <td></td>
                                <td style="text-align: right;">التاريخ</td>
                            </tr>
                             <tr>
                                <td style="text-align: left;">Signature </td>
                                 <td></td>
                                <td style="text-align: right;"> التوقيع</td>
                            </tr>
                        </table>
                    </td>
                    <td class="mark">
                          <table>
                            <tr>
                                <td style="text-align: left;">The School Principal </td>
                                <td></td>
                                <td style="text-align: right;"> المدرسةم</td>
                            </tr>
                            <tr>
                                <td style="text-align: left;">Name </td>
                                 <td>Ms. SOHA ALBISAIS </td>
                                <td style="text-align: right;"> :  اسم</td>
                            </tr>
                             <tr>
                                <td style="text-align: left;">Date </td>
                                 <td></td>
                                <td style="text-align: right;">التاريخ
</td>
                            </tr>
                             <tr>
                                <td style="text-align: left;">Signature </td>
                                 <td></td>
                                <td style="text-align: right;"> التوقيع</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="mark">Attesting (Authentication)
                        <br>&nbsp;<br>&nbsp;<br>
                    </td>
                    <td class="mark">School Stamp
                        <br>&nbsp;<br>&nbsp;<br> </td>
                </tr>
              </table> 
              <table width="100%">
                <tr>
                    <td>&nbsp;</td>
                </tr>
              </table>
              <table style="float: left;">
                <tr>
                    <td>GRADING SCALE </td>
                </tr>
                <tr>
                    <td>SCHOLASTIC AREAS </td>
                </tr>
              </table> 
              <table class="mark">

                <tr>
                    <td class="mark">MARKS RANGE</td>
                     <td class="mark">GRADE</td>
                </tr>
                <tr>
                    <td class="mark">90-100</td>
                     <td class="mark">A1</td>
                </tr>
                <tr>
                    <td class="mark">75-89</td>
                     <td class="mark">A</td>
                </tr>
                <tr>
                    <td class="mark">56-74</td>
                     <td class="mark">B</td>
                </tr>
                 <tr>
                    <td class="mark">35 - 55 </td>
                     <td class="mark">C</td>
                </tr>
                 <tr>
                    <td class="mark">35 & below </td>
                     <td class="mark">D</td>
                </tr>
              </table>
		
        </div>
        <!-- cover page -->
         <div class="page">
            <table width="100%">
               <tr><td><br></td></tr>
                <tr><td><br></td></tr>
                <tr>
                    <td style="text-align: left;">Kingdom of saudi arabia</td>
                    <td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
                    <td style="text-align: right;">المملكة العربية السعودية</td>
                </tr>
                <tr>
                    <td style="text-align: left;">Ministry of Education (280) </td>
                    <td></td>
                    <td style="text-align: right;">وزارة التعليم (٢٨٠)</td>
                </tr>
                <tr>
                    <td style="text-align: left;">General Directorate of Education in Jeddah</td>
                    <td></td>
                    <td style="text-align: right;">الإدارة العامة للتعليم بمحافظة جدة</td>
                </tr>
                <tr>
                    <td style="text-align: left;">Private Education Office </td>
                    <td></td>
                    <td style="text-align: right;">مكتب التعليم الخاص</td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td style="text-align: left;">DAR AL MAJD INTERNATIONAL SCHOOL</td>
                    <td></td>
                    <td style="text-align: right;">مدرسة دار المجد العالمية</td>
                </tr>
                <tr>
                    <td style="text-align: left;"> JEDDAH,KSA </td>
                    <td></td>
                    <td style="text-align: right;">جدة، المملكة العربية السعودية</td>
                </tr>
                <tr>
                    <td style="text-align: left;">License No.520-0867 </td>
                    <td></td>
                    <td style="text-align: right;">رقم الترخيص: 520-0867</td>
                </tr>
                 <tr>
                    <td style="text-align: left;">Authorized Curriculum: INDIAN.</td>
                    <td></td>
                    <td style="text-align: right;">المنهج المعتمد: الهندي</td>
                </tr>
                 <tr>
                    <td style="text-align: left;">Accredited by: CBSE </td>
                    <td></td>
                    <td style="text-align: right;">جهة الاعتماد: CBSE</td>
                </tr>
            </table>
            <table width="100%">
                <tr>
                    <td style="text-align: left;"><img src="daralmajd.png" width="100px" height="100px"/></td>
                    <td style="text-align: center;">ACHIEVEMENT RECORD FOR GRADE- LKG2 <br>
                    Academic Year 2025 – 2026</td>
                    <td style="text-align: right;"><img src="student.png" width="100px" height="100px"/></td>
                </tr>
                 <tr><td><br></td></tr>
               <tr><td><br></td></tr>
            </table>
            <hr>
            <table width="100%">
              
               <tr><td><br></td></tr>
               <tr><td><br></td></tr>
             <tr>
                <td style="text-align: left;">Student's Name</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.name}"/></td>
                <td style="text-align: right;">  اسم الطالب / الطالبة</td>
             </tr>
             <tr>
                <td style="text-align: left;">Admission Number</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.admissionnumber}"/></td>
                <td style="text-align: right;">رقم القبول</td>
             </tr>
             <tr>
                <td style="text-align: left;">Class </td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.classstudying}"/></td>
                <td style="text-align: right;">الصف</td>
             </tr>
             <tr>
                <td style="text-align: left;">Nationality</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.nationality}"/> </td>
                <td style="text-align: right;">الجنسية</td>
             </tr>
             <tr>
                <td style="text-align: left;">Date of Birth</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.dateofbirth}"/> </td>
                <td style="text-align: right;">تاريخ الميلاد</td>
             </tr>
             <tr>
                <td style="text-align: left;">Place of Birth</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.placeofbirth}"/></td>
                <td style="text-align: right;">مكان الميلاد</td>
             </tr>
             <tr>
                <td style="text-align: left;">Name of Father</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.fathersname}"/></td>
                <td style="text-align: right;">اسم الأب</td>
             </tr>
             <tr>
                <td style="text-align: left;">Name of Mother </td>
                <td style="text-align: center;"><c:out value="${Parents.parents.mothersname}"/></td>
                <td style="text-align: right;">اسم الأم</td>
             </tr>
             <tr>
                <td style="text-align: left;">Contact Number</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.contactnumber}"/></td>
                <td style="text-align: right;">رقم الجوال</td>
             </tr>
             <tr>
                <td style="text-align: left;">Email Address</td>
                <td style="text-align: center;"> <c:out value="${Parents.parents.email}"/> </td>
                <td style="text-align: right;">البريد الإلكتروني</td>
             </tr>
             <tr>
                <td style="text-align: left;">ID / Iqama No</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.studentexternalid}"/> </td>
                <td style="text-align: right;">رقم الهوية / الإقامة</td>
             </tr>
             <tr>
                <td style="text-align: left;">Passport No</td>
                <td style="text-align: center;"> </td>
                <td style="text-align: right;">رقم جواز السفر</td>
             </tr>
             <tr>
                <td style="text-align: left;">Date of Admission</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.admissiondate}"/></td>
                <td style="text-align: right;">تاريخ الالتحاق</td>
             </tr>
             <tr>
                <td style="text-align: left;">Date of Leaving</td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.dateleaving}"/></td>
                <td style="text-align: right;">تاريخ ترك المدرسة</td>
             </tr>
             <tr>
                <td style="text-align: left;">Previous School </td>
                <td style="text-align: center;"><c:out value="${Parents.parents.student.schoollastattended}"/></td>
                <td style="text-align: right;">المدرسة السابقة</td>
             </tr>
             <tr>
                <td style="text-align: left;">Date Issued (G - H) </td>
                <td style="text-align: center;"></td>
                <td style="text-align: right;">تاريخ إصدار الشهادة (هـ - م)</td>
             </tr>
             <tr>
                <td style="text-align: left;">No. of weeks</td>
                <td style="text-align: center;"></td>
                <td style="text-align: right;">عدد الأسابيع</td>
             </tr>
             <tr>
                <td style="text-align: left;">No. of Terms </td>
                <td style="text-align: center;"></td>
                <td style="text-align: right;">عدد الفصول الدراسية</td>
             </tr>
            </table>
            <hr>
<table width="100%">
  
   <tr>
      <td style="text-align: right;">
         صندوق بريد 4448 - جدة 22335 - المملكة العربية السعودية
الهاتف: +966126332334
البريد الإلكتروني: [info@daralmajdschool.com]

      </td>
   </tr>
    <tr>
      <td>P. O. Box 4448- Jeddah-22335 Saudi Arabia Phone:+966126332334 E-mail: info@daralmajdschool.com</td>
   </tr>

</table>
         </div>
        <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>
       
	</form>
</body>
</html>
