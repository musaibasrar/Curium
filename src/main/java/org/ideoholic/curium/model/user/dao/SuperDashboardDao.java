package org.ideoholic.curium.model.user.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SuperDashboardDao {

    Session session = null;
    Session.Transaction transaction = null;

    private static final Logger logger = LogManager.getLogger(SuperDashboardDao.class);

    public SuperDashboardDao() {
        session = HibernateUtil.openCurrentSession();
    }

    public List<Object[]> listBranches() {
        return listRows(
                "select idbranch, branchname from branch order by branchname",
                null,
                null
        );
    }

    public List<Object[]> fetchStudentCountsByBranch(List<Integer> branchIds, String academicYear, String classFilter, String sectionFilter) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);
        String studentWhere = buildStudentWhereClause(params, classFilter, sectionFilter);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select s.branchid, count(s.sid) " +
                        "from student s where s.branchid in (:branchIds) " +
                        studentWhere +
                        " group by s.branchid",
                params,
                listParams
        );
    }

    public List<Object[]> fetchTeacherCountsByBranch(List<Integer> branchIds) {
        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select t.branchid, count(t.tid) from teacher t where t.branchid in (:branchIds) and t.currentemployee = 1 group by t.branchid",
                null,
                listParams
        );
    }

    public List<Object[]> fetchFeeTotalsByBranch(List<Integer> branchIds, String academicYear, String classFilter, String sectionFilter, Integer feeCategoryId) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);
        String studentWhere = buildStudentWhereClause(params, classFilter, sectionFilter);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        StringBuilder feesSql = new StringBuilder();
        feesSql.append("select f.branchid, ")
                .append("sum(coalesce(f.feesamount,0)-coalesce(f.concession,0)-coalesce(f.waiveoff,0)) as totalfees,")
                .append("sum(coalesce(f.feespaid,0)) as paidfees,")
                .append("sum(coalesce(f.feesamount,0)-coalesce(f.feespaid,0)-coalesce(f.concession,0)-coalesce(f.waiveoff,0)) as duefees ")
                .append("from fee_studentfeesstructure f ")
                .append("where f.branchid in (:branchIds) and f.academicyear = :academicYear ")
                .append("and f.sid in (select s.sid from student s where s.branchid in (:branchIds) ")
                .append(studentWhere)
                .append(") ");

        if (feeCategoryId != null) {
            feesSql.append(" and f.idfeescategory = :feeCategoryId ");
            params.put("feeCategoryId", feeCategoryId);
        }

        feesSql.append(" group by f.branchid");
        return listRows(feesSql.toString(), params, listParams);
    }

    public List<Object[]> fetchMonthlyCollectionByBranch(List<Integer> branchIds, Date startDate, Date endDate) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select r.branchid, sum(coalesce(r.totalamount,0)) from fee_receiptinfo r " +
                        "where r.cancelreceipt = 0 and r.branchid in (:branchIds) and r.date between :startDate and :endDate group by r.branchid",
                params,
                listParams
        );
    }

    public List<Object[]> fetchAttendanceByBranch(List<Integer> branchIds, Date fromDate, Date toDate, String academicYear) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("fromDate", fromDate);
        params.put("toDate", toDate);
        params.put("academicYear", academicYear);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select a.branchid, a.attendancestatus, count(a.attendanceid) " +
                        "from att_studentdailyattendance a where a.branchid in (:branchIds) " +
                        "and a.date between :fromDate and :toDate and a.academicyear = :academicYear " +
                        "group by a.branchid, a.attendancestatus",
                params,
                listParams
        );
    }

    public List<Object[]> fetchResultByBranch(List<Integer> branchIds, String academicYear, Integer examId) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        StringBuilder resultSql = new StringBuilder();
        resultSql.append("select m.branchid, avg(m.marksobtained) from marks m where m.branchid in (:branchIds) and m.academicyear = :academicYear");
        if (examId != null) {
            resultSql.append(" and m.examid = :examId");
            params.put("examId", examId);
        }
        resultSql.append(" group by m.branchid");

        return listRows(resultSql.toString(), params, listParams);
    }

    public Object countExamsConducted(List<Integer> branchIds, String academicYear, Date toDate, String examName) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);
        params.put("toDate", toDate);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        StringBuilder examCountSql = new StringBuilder();
        examCountSql.append("select count(*) from examschedule es where es.branchid in (:branchIds) and es.academicyear = :academicYear and es.date <= :toDate");
        if (examName != null && !examName.trim().isEmpty()) {
            examCountSql.append(" and es.examname = :examName");
            params.put("examName", examName.trim());
        }
        return singleValue(examCountSql.toString(), params, listParams);
    }

    public List<Object[]> fetchStudentDimensions(List<Integer> branchIds, String academicYear, String classFilter, String sectionFilter) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);
        String studentWhere = buildStudentWhereClause(params, classFilter, sectionFilter);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select s.branchid, s.classstudying, s.gender, s.socialcategory from student s where s.branchid in (:branchIds) " +
                        studentWhere,
                params,
                listParams
        );
    }

    public List<Object[]> fetchMonthlyReceiptRows(List<Integer> branchIds, Date trendStart, Date trendEnd) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("trendStart", trendStart);
        params.put("trendEnd", trendEnd);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select r.branchid, r.date, coalesce(r.totalamount,0) from fee_receiptinfo r where r.cancelreceipt = 0 and r.branchid in (:branchIds) " +
                        "and r.date between :trendStart and :trendEnd",
                params,
                listParams
        );
    }

    public List<Object[]> fetchFeeCategoryAnalysis(List<Integer> branchIds, String academicYear) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select coalesce(fc.feescategoryname,'Others') as categoryName, " +
                        "sum(coalesce(f.feesamount,0)-coalesce(f.concession,0)-coalesce(f.waiveoff,0)) as totalfees, " +
                        "sum(coalesce(f.feespaid,0)) as paidfees " +
                        "from fee_studentfeesstructure f left join fee_feescategory fc on fc.idfeescategory = f.idfeescategory " +
                        "where f.branchid in (:branchIds) and f.academicyear = :academicYear " +
                        "group by fc.feescategoryname",
                params,
                listParams
        );
    }

    public List<Object[]> fetchExamPerformance(List<Integer> branchIds, String academicYear) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("academicYear", academicYear);

        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select coalesce(e.examname,'Exam'), avg(m.marksobtained) from marks m " +
                        "left join exams e on e.exid = m.examid and e.branchid = m.branchid " +
                        "where m.branchid in (:branchIds) and m.academicyear = :academicYear group by e.examname",
                params,
                listParams
        );
    }

    public List<Object[]> fetchExamOptions(List<Integer> branchIds) {
        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select e.exid, e.examname from exams e where e.branchid in (:branchIds) order by e.examname",
                null,
                listParams
        );
    }

    public List<Object[]> fetchFeeCategoryOptions(List<Integer> branchIds) {
        Map<String, List<?>> listParams = new LinkedHashMap<String, List<?>>();
        listParams.put("branchIds", branchIds);

        return listRows(
                "select fc.idfeescategory, fc.feescategoryname from fee_feescategory fc where fc.branchid in (:branchIds) order by fc.feescategoryname",
                null,
                listParams
        );
    }

    public List<Object[]> listRows(String sql, Map<String, Object> params, Map<String, List<?>> listParams) {
        List<Object[]> rows = new ArrayList<Object[]>();
        try {
            logger.debug("SuperDashboard SQL(listRows): {}", sql);
            logger.debug("SuperDashboard SQL params: {} | listParams: {}", params, listParams);
            session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            bindParameters(query, params, listParams);
            rows = query.list();
            logger.debug("SuperDashboard SQL rows returned: {}", rows == null ? 0 : rows.size());
            transaction.commit();
        } catch (Exception hibernateException) {
            transaction.rollback();
            logger.error(hibernateException);
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return rows;
    }

    public Object singleValue(String sql, Map<String, Object> params, Map<String, List<?>> listParams) {
        Object value = null;
        try {
            logger.debug("SuperDashboard SQL(singleValue): {}", sql);
            logger.debug("SuperDashboard SQL params: {} | listParams: {}", params, listParams);
            session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            bindParameters(query, params, listParams);
            value = query.uniqueResult();
            logger.debug("SuperDashboard SQL single value result: {}", value);
            transaction.commit();
        } catch (Exception hibernateException) {
            transaction.rollback();
            logger.error(hibernateException);
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return value;
    }

    public List<Object[]> listRowsByHql(String hql, Map<String, Object> params, Map<String, List<?>> listParams) {
        List<Object[]> rows = new ArrayList<Object[]>();
        try {
            session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            bindParameters(query, params, listParams);
            rows = query.list();
            transaction.commit();
        } catch (Exception hibernateException) {
            transaction.rollback();
            logger.error(hibernateException);
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return rows;
    }

    private void bindParameters(Query query, Map<String, Object> params, Map<String, List<?>> listParams) {
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                try {
                    query.setParameter(entry.getKey(), entry.getValue());
                } catch (IllegalArgumentException ignored) {
                    // Parameter is not referenced by this query; ignore safely.
                }
            }
        }
        if (listParams != null) {
            for (Map.Entry<String, List<?>> entry : listParams.entrySet()) {
                try {
                    query.setParameterList(entry.getKey(), entry.getValue());
                } catch (IllegalArgumentException ignored) {
                    // Parameter is not referenced by this query; ignore safely.
                }
            }
        }
    }

    private String buildStudentWhereClause(Map<String, Object> params, String classFilter, String sectionFilter) {
        String classValue = safeTrim(classFilter);
        String sectionValue = safeTrim(sectionFilter);

        StringBuilder studentWhere = new StringBuilder();
        studentWhere.append(" and (s.promotedyear = :academicYear or s.yearofadmission = :academicYear)");
        studentWhere.append(" and s.archive = 0 and s.passedout = 0 and s.droppedout = 0 and s.leftout = 0 ");

        if (!classValue.isEmpty() && !sectionValue.isEmpty()) {
            studentWhere.append(" and s.classstudying like :classSectionFilter ");
            params.put("classSectionFilter", classValue + "--" + sectionValue + "%");
        } else if (!classValue.isEmpty()) {
            studentWhere.append(" and s.classstudying like :classFilter ");
            params.put("classFilter", classValue + "--%");
        } else if (!sectionValue.isEmpty()) {
            studentWhere.append(" and s.classstudying like :sectionFilter ");
            params.put("sectionFilter", "%--" + sectionValue + "%");
        }

        return studentWhere.toString();
    }

    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}
