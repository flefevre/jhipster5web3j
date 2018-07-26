jhipsterweb3j-app is a jhipster applciation that will acts as a webfront, with a database
ethereum will contain ethereum layer

#Start the apps

##Start Ethereum

'''
cd ethereum/ethereum-docker
docker-compose up -d
'''


##Start Kafka

'''
cd jhipster5web3j-app
docker-compose -f src/main/docker/kafka.yml up -d
'''

##Start Web

'''
cd jhipster5web3j-app
./mvnw
'''

#Connect to

'''
http://localhost:3000/
http://localhost:8080/#/
'''
docker-compose scale eth=10
docker exec -it ethereum-docker_eth_2 geth attach ipc://root/.ethereum/devchain/geth.ipc