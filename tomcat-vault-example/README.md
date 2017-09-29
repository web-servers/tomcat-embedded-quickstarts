# Introduction

This project utilizes Apache Tomcat 8.5.23 with tomcat-vault 1.1.3.Final (actually 1.1.4.Final once it's released).

**Note:** As the versions above don't exist, this quickstart doesn't work at the moment. Once the versions above are released, then it'll work (I tested with dev distributions).

# Prerequisites

For this project to work, you'll first need to create a vault for tomcat to use on your system. To get setup, you need to install and setup a vault per the instructions in the vault project [here](https://github.com/picketbox/tomcat-vault/blob/master/INSTALL). After you install tomcat-vault, you can run the following commands from the project directory to mimic my initial setup and prevent any code changes:

    # Create a keystore for the vault
    $ keytool -genseckey -keystore src/main/resources/vault.keystore -alias my_vault  -storetype jceks -keyalg AES -keysize 128 -storepass my_password123 -keypass my_password123 -validity 730
    # Create a vault.properties file from the initialized vault
    $ tomcat-vault.sh --keystore src/main/resources/vault.keystore --keystore-password my_password123 --alias my_vault --enc-dir src/main/resources/vault/ --iteration 44 --salt 1234abcd -g src/main/resources/vault.properties
    # Add a secured attribute to the vault
    $ tomcat-vault.sh --keystore src/main/resources/vault.keystore --keystore-password my_password123 --alias my_vault --enc-dir src/main/resources/vault/ --iteration 120 --salt 1234abcd --vault-block my_block --attribute manager_password --sec-attr P@SSW0#D

After you run those three commands you're all set to start tomcat with tomcat-vault!

# Launch

After the vault is setup, then you can compile the servlet code and start the container:

     # mvn clean compile exec:java

# Test

As a test, you can start the container as mentioned above and look for the following line in the output which shows that "VAULT::my_block::manager_password::" was retrieved from the vault and "P@SSW0#D" is printed instead:

    Vault entry from block 'my_block' and 'attribute' manager_password: P@SSWO#D
