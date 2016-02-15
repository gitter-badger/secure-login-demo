package org.terasoluna.securelogin.domain.service.authenticationevent;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.securelogin.domain.model.FailedAuthentication;
import org.terasoluna.securelogin.domain.model.SuccessfulAuthentication;
import org.terasoluna.securelogin.domain.repository.authenticationevent.FailedAuthenticationRepository;
import org.terasoluna.securelogin.domain.repository.authenticationevent.SuccessfulAuthenticationRepository;

@Service
@Transactional
public class AuthenticationEventSharedServiceImpl implements
		AuthenticationEventSharedService {

	@Inject
	FailedAuthenticationRepository failedAuthenticationRepository;

	@Inject
	SuccessfulAuthenticationRepository successAuthenticationRepository;

	@Transactional(readOnly = true)
	@Override
	public List<SuccessfulAuthentication> findLatestSuccessEvents(
			String username, int count) {
		return successAuthenticationRepository.findLatest(username, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<FailedAuthentication> findLatestFailureEvents(
			String username, int count) {
		return failedAuthenticationRepository.findLatest(username, count);
	}

	@Override
	public int authenticationSuccess(SuccessfulAuthentication event) {
		deleteFailureEventByUsername(event.getUsername());
		return successAuthenticationRepository.create(event);
	}

	@Override
	public int authenticationFailure(FailedAuthentication event) {
		return failedAuthenticationRepository.create(event);
	}

	@Override
	public int deleteFailureEventByUsername(String username) {
		return failedAuthenticationRepository.deleteByUsername(username);
	}

}
