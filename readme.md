# PCIP PoC

The goal of this PoC is to split the PCIP business logic out of STIG Orchestration.

I'm currently just using Spring Integration to get the UC_PACKAGe_CONFIRMATION Message via JMS (ActiveMQ) and calling a transformer and handler to apply the logic from the relevant projects.

Of course it should be simple to switch it to consume the message via an HTTP post removing the need for a broker.

We are still having an issue where it looks like the error responses possible from calling a CWS service are now held in an array, so a successful response returns an empty array which cannot be parsed.

This needs a fix in the w001 client anyway.

To run on LT0 set -Djavax.net.ssl.trustStore=client.ts -Djavax.net.ssl.keyStore=client.ks -Djavax.net.ssl.trustStorePassword=wBCGIwjPB1 -Djavax.net.ssl.keyStorePassword=wBCGIwjPB1
 where the client.ks and client.ts files come from:

 Keystore: http://repository.int.kn/repository/installers/swift/security/client/client.ks
 
 Truststore:  http://repository.int.kn/repository/installers/swift/security/client/client.ts
 
 To deploy we should be able to create a Daemon job in the JDA console.
 
 Check the TODOs in the code:
 * Some clean up
 * ~~get the UUID from the message or generate~~
 * Handle exceptions in the integration flow.
 * More tests - CLOSE_SHIPMENT, HTTP, sad flows :*( 
 * Fix w001 client for array of errors returned.
 
 Thoughts:
 * Auditing - how? Use of wiretap?
 