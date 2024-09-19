package org.ideoholic.curium.model.student.dto;

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * https://mapstruct.org/
 * https://marketplace.eclipse.org/content/mapstruct-eclipse-plugin
 * https://mapstruct.org/documentation/ide-support/
 * https://stackoverflow.com/questions/45518161/how-to-get-eclipse-to-generate-mapstruct-mappers-using-gradle
 * @author Ideoholic
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = DateUtil.class)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    static String createConClassStudying(CreateStudentDto createStudentDto) {
        String conClassStudying = "";
        String addClass = DataUtil.emptyString(createStudentDto.getAddclass());
        if (!addClass.equalsIgnoreCase("")) {
            conClassStudying = addClass + "--";
        }
        String addSec = DataUtil.emptyString(createStudentDto.getAddsec());
        if (!addSec.equalsIgnoreCase("")) {
            conClassStudying = conClassStudying + addSec;
        }
        return conClassStudying;
    }

    static String createClassadmittedin(CreateStudentDto createStudentDto) {
        String conClassAdmittedIn = "";
        String addClassE = DataUtil.emptyString(createStudentDto.getAddclass());
        if (!conClassAdmittedIn.equalsIgnoreCase("")) {
            conClassAdmittedIn = addClassE + "--";
        }
        String addSecE = DataUtil.emptyString(createStudentDto.getAddsec());
        if (!addSecE.equalsIgnoreCase("")) {
            conClassAdmittedIn = conClassAdmittedIn + addSecE;
        }
        return conClassAdmittedIn;
    }

    @Mapping(target = "classstudying", expression = "java(StudentMapper.createConClassStudying(studentDto))")
    @Mapping(target = "classadmittedin", expression = "java(StudentMapper.createClassadmittedin(studentDto))")
    @Mapping(target = "dateofbirth", expression = "java(DateUtil.indiandateParser(studentDto.getDateofbirth()))")
    @Mapping(target = "stdlaststudied", source = "lastclass")
    @Mapping(target = "schoollastattended", source = "lastschool")
    @Mapping(target = "admissionnumber", source = "admnno")
    @Mapping(target = "admissiondate", expression = "java(DateUtil.indiandateParser(studentDto.getDateofadmission()))")
    @Mapping(target = "createddate", expression = "java(DateUtil.indiandateParser(studentDto.getCreateddate()))")
    @Mapping(target = "mothertongue", source = "motherT")
    @Mapping(target = "crecorddate", expression = "java(DateUtil.indiandateParser(studentDto.getDateofcr()))")
    @Mapping(target = "placeofbirth", source = "place")
    @Mapping(target = "nooftc", source = "tcno")
    @Mapping(target = "dateoftc", expression = "java(DateUtil.indiandateParser(studentDto.getDateoftc()))")
    @Mapping(target = "subsequentprogress", source = "progress")
    @Mapping(target = "dateleaving", expression = "java(DateUtil.indiandateParser(studentDto.getDateofleaving()))")
    @Mapping(target = "reasonleaving", source = "reasonforleaving")
    @Mapping(target = "datetcissued", expression = "java(DateUtil.indiandateParser(studentDto.getDateoftcissued()))")
    @Mapping(target = "guardiandetails", source = "guardian")
    @Mapping(target = "instructionmediumlastschool", source = "mediumofinstructionlastschool")
    @Mapping(target = "yearofadmission", source = "yearofadmission")
    @Mapping(target = "promotedyear", source = "promotedyear")
    Student mapStudent(CreateStudentDto studentDto);

    @Mapping(target = "addresspermanent", source = "permanentaddress")
    @Mapping(target = "addresstemporary", source = "temporaryaddress")
    @Mapping(target = "remarks", source = "remarksadditional")
    Parents mapParent(CreateStudentDto studentDto);

    @Mapping(target = "exampassedappearance", source = "pep")
    @Mapping(target = "exampassedyear", source = "passedyear")
    @Mapping(target = "exampassedregno", source = "regno")
    @Mapping(target = "exampassedresultwithclass", source = "resultclass")
    @Mapping(target = "secondlanguage", source = "xsecondlanguage")
    @Mapping(target = "aggregatemarkssslc", source = "aggmarks")
    @Mapping(target = "sslcmediuminstruction", source = "xmediuminstruction")
    @Mapping(target = "pumediuminstruction", source = "PUmediuminstruction")
    Pudetails mapPudetails(CreateStudentDto studentDto);

    @Mapping(target = "exampassedappearance", source = "pepdc")
    @Mapping(target = "exampassedyear", source = "passedyeardc")
    @Mapping(target = "exampassedregno", source = "regnodc")
    @Mapping(target = "exampassedresultwithclass", source = "resultclassdc")
    @Mapping(target = "pumediuminstruction", source = "mediumofinstructiondc")
    @Mapping(target = "subjectsqualifingexampartone", source = "qpartone")
    @Mapping(target = "subjectsqualifingexamparttwo", source = "qparttwo")
    @Mapping(target = "subjectsdegreecoursepartone", source = "ppartone")
    @Mapping(target = "subjectsdegreecourseparttwo", source = "pparttwo")
    @Mapping(target = "transfercertificate", source = "originaltc")
    @Mapping(target = "proficiencysports", source = "games")
    @Mapping(target = "areyouemployee", source = "employer")
    Degreedetails mapDegreedetails(CreateStudentDto studentDto);
    
    Classsec mapClassec(CreateStudentDto createStudentDto);


    static String createConClassStudyings(StudentDto studentDto) {
        String conClassStudying = "";
        String addClass = DataUtil.emptyString(studentDto.getAddclass());
        if (!addClass.equalsIgnoreCase("")) {
            conClassStudying = addClass + "--";
        }
        String addSec = DataUtil.emptyString(studentDto.getAddsec());
        if (!addSec.equalsIgnoreCase("")) {
            conClassStudying = conClassStudying + addSec;
        }
        return conClassStudying;
    }

    static String createClassadmittedina(StudentDto studentDto) {
        String conClassAdmittedIn = "";
        String addClassE = DataUtil.emptyString(studentDto.getAddclass());
        if (!conClassAdmittedIn.equalsIgnoreCase("")) {
            conClassAdmittedIn = addClassE + "--";
        }
        String addSecE = DataUtil.emptyString(studentDto.getAddsec());
        if (!addSecE.equalsIgnoreCase("")) {
            conClassAdmittedIn = conClassAdmittedIn + addSecE;
        }
        return conClassAdmittedIn;
    }

    @Mapping(target = "dateofbirth", expression = "java(DateUtil.indiandateParser(studentDto.getDateofbirth()))")
    Student mapStudent(StudentDto studentDto);
    Parents mapParent(StudentDto studentDto);
    Pudetails mapPudetails(StudentDto studentDto);
    Degreedetails mapDegreedetails(StudentDto studentDto);
}
