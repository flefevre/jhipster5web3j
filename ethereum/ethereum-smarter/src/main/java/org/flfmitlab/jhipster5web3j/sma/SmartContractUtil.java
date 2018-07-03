package org.flfmitlab.jhipster5web3j.sma;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

public final class SmartContractUtil {

	private final Logger log = LoggerFactory.getLogger(SmartContractUtil.class);

	public final static String MYCONTRACTADDRESSES= "/volatile/home/fl218080/github/jhipster5web3j/ethereum/ethereum-simulater/target/contractAddresses.properties";

	public static String MYSMARTCONTRACT_KEY=new String("mysmartcontract");

	private Web3j web3j;

	private final Credentials credentials;
	public static BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
	public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

	private MySmartContract mySmartContract;
	private Properties prop;

	public SmartContractUtil(Web3j web3j, Credentials credentials) {
		this.prop = new Properties();
		this.credentials = credentials;
		this.web3j = web3j;

		Web3ClientVersion web3ClientVersion;
		try {
			web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			log.info("clientVersion: "+clientVersion);
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		initializeContracts();
	}

	public ZonedDateTime parseDateTime(BigInteger date) {
		log.debug("parsing date: "+date);
		//Convert String to LocalDateTime
		LocalDateTime ldt = LocalDateTime.parse(""+date, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

		//Paris, 2016 Apr-Oct = DST, UTC+2, other months UTC+1
		//UTC+2
		ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Europe/Paris"));

		return parisDateTime;
	}

	public LocalDate parseDate(BigInteger date) {
		log.debug("parsing date: "+date);
		//Convert String to LocalDateTime
		LocalDate ldt = LocalDate.parse(""+date, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return ldt;
	}


	private void initializeContracts() {

		InputStream input = null;

		try {
			log.info("Looking for previous loqded contractsn at "+MYCONTRACTADDRESSES);
			input = new FileInputStream(MYCONTRACTADDRESSES);
			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			log.info(MYCONTRACTADDRESSES+" not found, so deploy new Contracts");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//Try to load from property value
		if(prop.getProperty(MYSMARTCONTRACT_KEY)!=null) {
			log.info("Loading MySmartContract contract: "+MYSMARTCONTRACT_KEY + " from address: "+prop.getProperty(MYSMARTCONTRACT_KEY));
			mySmartContract = MySmartContract.load(prop.getProperty(MYSMARTCONTRACT_KEY), web3j, credentials, GAS_PRICE, GAS_LIMIT);
		}
		//If property value is false, no binary, so deploy the contract
		if(mySmartContract==null || mySmartContract.getContractBinary()==null) {
			try {
				log.info("Deploying the contract: "+web3j +"\t" + credentials);
				mySmartContract = MySmartContract.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send();
				if(mySmartContract!=null) {
					log.info("Deployed cfActionNotarization SmartContract: \nBinary:{} \nAddress: {}", mySmartContract.getContractBinary(), mySmartContract.getContractAddress());
					prop.setProperty(MYSMARTCONTRACT_KEY, mySmartContract.getContractAddress());	
					storeProperty();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void storeProperty() {
		OutputStream output = null;
		try {
			output = new FileOutputStream(MYCONTRACTADDRESSES);
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public MySmartContract getMySmartContract() {
		mySmartContract = MySmartContract.load(prop.getProperty(MYSMARTCONTRACT_KEY), web3j, credentials, GAS_PRICE, GAS_LIMIT);
		return mySmartContract;
	}

}
