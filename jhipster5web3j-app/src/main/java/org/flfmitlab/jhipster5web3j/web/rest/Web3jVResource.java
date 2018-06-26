package org.flfmitlab.jhipster5web3j.web.rest;

import java.io.IOException;

import org.flfmitlab.jhipster5web3j.service.Web3jVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web3 controller
 */
@RestController
@RequestMapping("/api/web3jv")
public class Web3jVResource {

	private final Logger log = LoggerFactory.getLogger(Web3jVResource.class);

	private final Web3jVService web3jService;

	public Web3jVResource(Web3jVService web3jService) {
		this.web3jService = web3jService;
	}

	/**
	 * GET getWeb3jVersion
	 */
	@GetMapping("/get-web-3-j-version")
	public String getWeb3jVersion() {
		String version = new String("no version");
		try {
			version = web3jService.getWeb3jVersion();
		} catch (IOException e) {
			version = e.getMessage();
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return version;
	}

}
