package org.terasoluna.securelogin.domain.service.passwordhistory;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.terasoluna.securelogin.domain.model.PasswordHistory;
import org.terasoluna.securelogin.domain.repository.passwordhistory.PasswordHistoryRepository;

@Service
@Transactional
public class PasswordHistorySharedServiceImpl implements
		PasswordHistorySharedService {

	@Inject
	PasswordHistoryRepository passwordHistoryRepository;

	public int insert(PasswordHistory history) {
		return passwordHistoryRepository.insert(history);
	}

	@Transactional(readOnly = true)
	public List<PasswordHistory> findHistoriesByUseFrom(String username,
			DateTime useFrom) {
		return passwordHistoryRepository.findByUseFrom(username, useFrom);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PasswordHistory> findLatestHistories(String username, int limit) {
		return passwordHistoryRepository.findLatestHistories(username, limit);
	}

}
