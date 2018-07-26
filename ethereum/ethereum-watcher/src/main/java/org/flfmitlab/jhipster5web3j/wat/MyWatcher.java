package org.flfmitlab.jhipster5web3j.wat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.flfmitlab.jhipster5web3j.sma.MySmartContract;
import org.flfmitlab.jhipster5web3j.sma.SmartContractUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import rx.Subscription;

public class MyWatcher {
	private final static Logger log = LoggerFactory.getLogger(MyWatcher.class);



	public static void main(String[] args) {
		//Getting the Contract
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

		log.info("mySmartContract "+mySmartContract.getContractBinary());
		
		
//		EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
//		        DefaultBlockParameterName.LATEST, mySmartContract.getContractAddress());
//		web3j.ethLogObservable(filter);
		
		
		//Basic subscription
		Subscription subscription = web3j.transactionObservable().subscribe(tx -> {
			log.info("basic subscription tx: "+ getData(tx) );	
		});
		
		Subscription subscribe = mySmartContract.eventTxEventObservable(DefaultBlockParameterName.EARLIEST,
		        DefaultBlockParameterName.LATEST).subscribe()
				;
				//.eValueQualifiedEventObservable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);

//		Subscription subscribe = eAddQualifiedHourlySampleInvokedEventObservable.subscribe(
//				event -> {
//					logger.info("Block"+event.blocknumber.intValue());
//					
//				}, throwable -> {
//					logger.error("Handle error event: "+ throwable.getMessage()); 
//	    }, () -> {
//	    	logger.error("Handle on complete event");  
//	    });
		
		
		try {
			log.info("waiting");
			TimeUnit.SECONDS.sleep(40);	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		subscription.unsubscribe();
		System.exit(0);
	}

	public static String getData(Transaction tx) {
		String data = new String();
		data+="\ntx.getBlockHash(): "+tx.getBlockHash();
		data+="\ntx.getBlockNumber(): "+tx.getBlockNumber();
		data+="\ntx.getBlockNumberRaw(): "+tx.getBlockNumberRaw();
		data+="\ntx.getCreates(): "+tx.getCreates();
		data+="\ntx.getFrom(): "+tx.getFrom();
		data+="\ntx.getGas(): "+tx.getGas();
		data+="\ntx.getGasPrice(): "+tx.getGasPrice();
		data+="\ntx.getGasPriceRaw(): "+tx.getGasPriceRaw();
		data+="\ntx.getHash(): "+tx.getHash();
		data+="\ntx.getInput(): "+tx.getInput() ;
		data+="\ntx.getNonce(): "+tx.getNonce();
		data+="\ntx.getNonceRaw(): "+tx.getNonceRaw();
		data+="\ntx.getPublicKey(): "+tx.getPublicKey();
		data+="\ntx.getR(): "+tx.getR();
		data+="\ntx.getRaw(): "+tx.getRaw();
		data+="\ntx.getTo(): "+tx.getTo();
		data+="\ntx.getTransactionIndex(): "+tx.getTransactionIndex();
		data+="\ntx.getTransactionIndexRaw(): "+tx.getTransactionIndexRaw();
		data+="\ntx.getV(): "+tx.getV();
		data+="\ntx.getValue(): "+tx.getValue();
		data+="\ntx.getValueRaw(): "+tx.getValueRaw();
		
		String inputData = tx.getInput();
        String method = inputData.substring(0,10);
        System.out.println(method);
        String to = inputData.substring(10,74);
        String value = inputData.substring(74);
        Method refMethod;
		try {
			refMethod = TypeDecoder.class.getDeclaredMethod("decode",String.class,int.class,Class.class);
			refMethod.setAccessible(true);
	        Address address = (Address)refMethod.invoke(null,to,0,Address.class);
	        System.out.println("xx="+address.toString());
	        Uint256 amount = (Uint256) refMethod.invoke(null,value,0,Uint256.class);
	        System.out.println("xx="+amount.getValue());
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String hexString = value;    
		byte[] bytes;
		try {
			bytes = Hex.decodeHex(hexString.toCharArray());
			System.out.println("yyyyyyyyyy\n"+new String(bytes, "UTF-8"));
		       
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return data;
	}

}
