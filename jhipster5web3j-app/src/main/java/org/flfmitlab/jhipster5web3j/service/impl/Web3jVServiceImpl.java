package org.flfmitlab.jhipster5web3j.service.impl;

import java.io.IOException;

import org.flfmitlab.jhipster5web3j.service.Web3jVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

@Service
@Transactional
public class Web3jVServiceImpl implements Web3jVService {

	private final Logger log = LoggerFactory.getLogger(Web3jVServiceImpl.class);
	
	@Autowired
	private Web3j web3j;

	public String getWeb3jVersion() throws IOException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		String v = web3ClientVersion.getWeb3ClientVersion();
		log.debug("Calling web3j service version: {}",v);
		return v;
	}
}
