package com.model.branch.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.branch.dto.Branch;
import com.model.branch.dto.Districts;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class BranchDAO {

	
    Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public BranchDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Branch> readListOfObjects() {
		
		List<Branch> results = new ArrayList<Branch>();
        try {
            transaction = session.beginTransaction();
            results = (List<Branch>) session.createQuery("From Branch order by centercode ASC").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
	}

    public List<Districts> readListOfObjectsDistrict() {
        
        List<Districts> results = new ArrayList<Districts>();
        try {
            transaction = session.beginTransaction();
            results = (List<Districts>) session.createQuery("From Districts").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
    }

    public boolean create(Districts district) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(district);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return result;
        }
    }

    public boolean updateMultiple(List<Districts> districtList) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Districts districts : districtList) {
                session.update(districts);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
}

    public boolean deleteMultiple(List ids) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Districts where iddistrict IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
}

    public boolean deleteMultipleCenters(List ids) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Branch where idbranch IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }

    public boolean updateMultipleCenters(List<Branch> branchList) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Branch branch : branchList) {
                session.update(branch);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }

    @SuppressWarnings("finally")
    public boolean addBranches(Branch branch) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(branch);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return result;
        }
    }

    public Branch getBranch(String centerCode) {
        
        Branch results = new Branch();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("From Branch where centercode='" + centerCode + "'").setCacheable(true).setCacheRegion("commonregion");
            results = (Branch) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
}

    public Branch getBranch(int branchId) {
        Branch results = new Branch();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("From Branch where idbranch='" + branchId + "'").setCacheable(true).setCacheRegion("commonregion");
            results = (Branch) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
    }

    public List<Branch> readListOfObjects(int branchId) {
        
        List<Branch> results = new ArrayList<Branch>();
try {
    transaction = session.beginTransaction();
    results = (List<Branch>) session.createQuery("From Branch where idbranch ="+branchId+" order by centercode ASC").setCacheable(true).setCacheRegion("commonregion").list();
    transaction.commit();
} catch (HibernateException hibernateException) {transaction.rollback();
    hibernateException.printStackTrace();
} finally {
    HibernateUtil.closeSession();
    return results;
}
}

    public boolean checkDistrict(Districts district) {
        
                List<Districts> distList = new ArrayList<Districts>();
                
        try {
            transaction = session.beginTransaction();
            distList = (List<Districts>) session.createQuery("From Districts where districtname ='"+district.getDistrictname()+"'").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        if(distList.size()>0) {
            return true;
        }
        return false;
    }

    public boolean checkBranch(Branch branch) {
        
            List<Branch> branchList = new ArrayList<Branch>();
                    
            try {
                transaction = session.beginTransaction();
                branchList = (List<Branch>) session.createQuery("From Branch where centercode ='"+branch.getCentercode()+"'").list();
                transaction.commit();
            } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
            } finally {
                HibernateUtil.closeSession();
            }
            if(branchList.size()>0) {
                return true;
            }
            return false;
            }

    public List<Districts> getDistrict(String districtcode) {
        
              List<Districts> distList = new ArrayList<Districts>();
                        
                try {
                    transaction = session.beginTransaction();
                    distList = (List<Districts>) session.createQuery("From Districts where districtcode = '"+districtcode+"'").list();
                    transaction.commit();
                } catch (HibernateException hibernateException) {transaction.rollback();
                    hibernateException.printStackTrace();
                } finally {
                    HibernateUtil.closeSession();
                }
                
                return distList;
    }

    public Branch getDistrictName(String centerCode) {
        
        Branch dist = new Branch();
                  
          try {
              //if
              transaction = session.beginTransaction();
              Query query = session.createQuery("From Branch where centercode = '"+centerCode+"'").setCacheable(true).setCacheRegion("commonregion");
              dist = (Branch) query.uniqueResult();
              transaction.commit();
          } catch (HibernateException hibernateException) {transaction.rollback();
              hibernateException.printStackTrace();
          } finally {
              HibernateUtil.closeSession();
          }
          
          return dist;
}

	public List<Branch> getBranchListByState(String state) {
        
		List<Branch> branch = new ArrayList<Branch>();
                  
          try {
              //if
              transaction = session.beginTransaction();
              Query query = session.createQuery("From Branch where state = '"+state+"'");
              branch = query.getResultList();
              transaction.commit();
          } catch (HibernateException hibernateException) {transaction.rollback();
              hibernateException.printStackTrace();
          } finally {
              HibernateUtil.closeSession();
          }
          
          return branch;
}
    }