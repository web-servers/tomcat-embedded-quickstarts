# Introduction

This project utilizes Red Hat's JWS distribution of the Apache Tomcat 8.0.36 artifacts with tomcat-vault 1.1.3.Final. It also assumes that [PR 29](https://github.com/picketbox/tomcat-vault/pull/29) will be accepted, otherwise it won't work :X

# Prerequisites

For this project to work, you'll first need to create a vault for tomcat to use on your system. To get setup, you need to install and setup a vault per the instructions in the vault project [here](https://github.com/picketbox/tomcat-vault/blob/master/INSTALL). After you do that, then you can run the following commands in the project directory to mimic my initial setup:

    # keytool -genseckey -keystore src/main/resources/vault.keystore -alias my_vault  -storetype jceks -keyalg AES -keysize 128 -storepass my_password123 -keypass my_password123 -validity 730
    # vault.sh --keystore src/main/resources/vault.keystore --keystore-password my_password123 --alias my_vault --vault-block my_block --attribute manager_password --sec-attr P@SSW0#D --enc-dir src/main/resources/vault/ --iteration 44 --salt 1234abcd

After you run those two commands, you'll need to copy the output into src/main/resources/vault.properties, then you're all set!

# Launch

After the vault is setup, then you can compile the servlet code and start the container:

     # mvn clean compile exec:java

# Test

As a test, you can start the container as mentioned above and look for the following line which shows that "VAULT::my_block::manager_password::" was retrevied from the vault and "P@SSW0#D" is printed instead:

    Vault entry from block 'my_block' and 'attribute' manager_password: P@SSWO#D
