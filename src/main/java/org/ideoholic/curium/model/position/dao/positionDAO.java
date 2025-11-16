package org.ideoholic.curium.model.position.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.repositories.PositionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class positionDAO {

	private final PositionRepository positionRepo;

	public Position create(Position position) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			// session.save(position);
			position = positionRepo.save(position);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
		}
		return position;
	}

	public List<Position> readListOfObjects(int branchId) {

		List<Position> results = new ArrayList<Position>();
		try {
			// results = (List<Position>) session.createQuery("From Position where branchid = "+branchId).list();
			results = positionRepo.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	public void deleteMultiple(List<Integer> ids) {
		try {
			// Query query = session.createQuery("delete from Position where positionid IN (:ids)");
			// query.setParameterList("ids", ids);
			positionRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

}
