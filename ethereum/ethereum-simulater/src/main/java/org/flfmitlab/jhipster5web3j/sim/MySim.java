package org.flfmitlab.jhipster5web3j.sim;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.flfmitlab.jhipster5web3j.sma.MySmartContract;
import org.flfmitlab.jhipster5web3j.sma.SmartContractUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionObject;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

public class MySim {

	private final static Logger log = LoggerFactory.getLogger(MySim.class);


	public static void main(String[] args) {
		Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials("node01account","/volatile/home/fl218080/github/jhipster5web3j/ethereum/ethereum-docker/files/keystore/UTC--2017-06-05T15-25-01.575856240Z--d0f98f1ea8406c9ddb222144de6f8a9e464941da");
		} catch (IOException | CipherException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		log.info("Asking for mycontract");
		SmartContractUtil mySmartContractUtil = new SmartContractUtil(web3j,credentials);
		MySmartContract mySmartContract = mySmartContractUtil.getMySmartContract();

		log.info("mySmartContract binary "+mySmartContract.getContractBinary());
		log.info("mySmartContract address "+mySmartContract.getContractAddress());
		log.info("mySmartContract netaddress"+mySmartContract.getDeployedAddress("456719"));
		

		int count=0;
		Date date;
		String data =new String();
		TransactionReceipt tr;
		while(true) {
			count++;
			date = new Date();
			try {
				data = "my data: "+count + " " + date.getTime();
				log.info("Calling the smart contract with: "+data);
				tr = mySmartContract.registerTx(data).send();
				log.info("Call done: "+data +"\t"+tr.getBlockNumberRaw()+"\t"+tr.getContractAddress()+"\t"+tr.getFrom());
				
			} catch (Exception e1) {
				log.error("e1: "+e1.getMessage());
				e1.printStackTrace();
			}
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				log.error("e: "+e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
