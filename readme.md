# PCIP PoC

The goal of this PoC is to split the PCIP business logic out of STIG Orchestration.

I'm currently just using Spring Integration to get the UC_PACKAGe_CONFIRMATION Message via JMS (ActiveMQ) and calling a transformer and handler to apply the logic from the relevant projects.

Of course it should be simple to switch it to consume the message via an HTTP post removing the need for a broker.

We are still having an issue where it looks like the error responses possible from calling a CWS service are now held in an array, so a successful response returns an empty array which cannot be parsed.

This no longer calls the API client but calls direct to the MOCA

To run on LTx set -Djavax.net.ssl.trustStore=client.ts -Djavax.net.ssl.keyStore=client.ks -Djavax.net.ssl.trustStorePassword=wBCGIwjPB1 -Djavax.net.ssl.keyStorePassword=wBCGIwjPB1 -Dwms.api.url=https://server:8010
 where server is the LTx instance you wish to run on, and the client.ks and client.ts files come from:

 Keystore: http://repository.int.kn/repository/installers/swift/security/client/client.ks
 
 Truststore:  http://repository.int.kn/repository/installers/swift/security/client/client.ts
 
 To deploy we should be able to create a Daemon job in the JDA console.
 
#Steps to manually deploy as a Daemon in JDA
 
##Vagrant changes

In Vagrant file, expose an additional port (8025 in this example). You'll probably have to halt and restart the vagrant image once the changes are done. This is purely for testing purposes.
 
##Copy Jar
 
 1. Build the jar file (e.g. pcip-integration-0.0.1.jar)
 2. Copy the jar file to vagrant. Easiest way is to copy it to ~/Documents/dev/swift/vagrant/serversetup_alpha
 3. Login to vagrant via ssh
 4. Execute the command: sudo mv /vagrant/pcip-integration-0.0.1.jar /apps/jda/swiftapp/les/knlib/
 5. Execute the command: sudo chown wmd:wmd /apps/jda/swiftapp/les/knlib/pcip-integration-0.0.1.jar
 
##Security Setup

 1. Login to Vagrant via SSH
 2. Execute: 'sudo cp /apps/jda/swiftweb/rpweb/settings/keystore /apps/jda/swiftapp/les/knlib/keystore'
 3. Execute: 'sudo chown wmd:wmd /apps/jda/swiftapp/les/knlib/keystore'
 4. Execute: 'sudo wget -o /apps/jda/swiftapp/les/knlib/client.ts http://repository.int.kn/repository/installers/swift/security/client/client.ts'
 5. Execute: 'sudo wget -o /apps/jda/swiftapp/les/knlib/client.ks http://repository.int.kn/repository/installers/swift/security/client/client.ks'
 6. Execute: 'sudo chown wmd:wmd /apps/jda/swiftapp/les/knlib/client.ks'
 7. Execute: 'sudo chown wmd:wmd /apps/jda/swiftapp/les/knlib/client.ts'
 
 
##Daemon Setup

Note on -D system values. Anything with a '-' in the property name can't be passed in under the env section, hence its passed directly to the process
Also, this is just a POC - probably need to create a prod profile with defaults that ops can override

Note on localhost. Note that in the config below localhost isn't used. This is because the requests are https, and localhost hasn't been added as a trusted SAN. One for ops.

The code must run in SSL mode

 1. Navigate to https://appserver.vagrant:8010/console
 2. Under Tasks, click on Daemon and then add
 3. Set TASK ID and TASK NAME to 'PCIP'
 4. Set command to: 'java -Dserver.ssl.key-alias=appserver.vagrant -Dserver.ssl.key-store=$LESDIR/knlib/keystore -Dserver.ssl.key-store-password=vagrant -Dserver.ssl.key.store-type=jks -jar $LESDIR/knlib/pcip-integration-0.0.1.jar'
 5. Tracking Enabled to ON
 6. Start-in DIR to '$LESDIR/knlib'
 7. Tracefile: '$LESDIR/log/pcip.log'
 8. Set the following props
 	'esb.url=http://GBW02269.nw.win.int.kn:9098/mockKNCL01/ws' - this is the stub
 	'javax.net.ssl.keyStore=$LESDIR/knlib/client.ks'
 	'javax.net.ssl.keyStorePassword=wBCGIwjPB1'
 	'javax.net.ssl.trustStore=$LESDIR/les/knlib/client.ts'
 	'javax.net.ssl.trustStorePassword=$LESDIR/les/knlib/client.ts'
 	'server.port=8025'
 	'service.useInternalStores=false'
 	'wms.api.url=https://appserver.vagrant:8010'
 	'wms.jms.broker=ssl://appserver.vagrant:8017' (Note that I'm using SSL enabled activemq - change this to tcp:// if non-ssl)
 	
##Testing

You can then send a PCIP request to https://appserver.vagrant:8025/pcip using Postman

Sample request

'
<UC_PACKAGE_CONFIRMATION_IFD>
<CTRL_SEG>
  <TRNNAM>UC_PACKAGE_CONFIRMATION</TRNNAM> 
  <TRNVER>2017_0110</TRNVER> 
  <UUID>8d2761e8-b42a-483d-b3a7-98e81a24b579</UUID> 
  <WH_ID>LT1</WH_ID> 
  <CLIENT_ID>CLIE01</CLIENT_ID> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  <DEVCOD /> 
  <SRCLOC>RDT001</SRCLOC> 
  <STOLOC /> 
  <DSTLOC>OUTLANE-53</DSTLOC> 
  <PRTADR>DEFAULT</PRTADR> 
  <PKG_LOCATION /> 
  <REPRINT /> 
  <ROUTE_ID>64</ROUTE_ID> 
<SHIPMENT_SEG>
  <SHIP_ID>SID0001950</SHIP_ID> 
  <HOST_SHIP_ID /> 
  <SHIP_CLIENT_ID /> 
  <SHIP_DOC_NUM /> 
  <SHIP_TRACK_NUM /> 
  <FRGHT_CHRG /> 
  <FRTCOD /> 
  <EARLY_DEV_DT>20170101000100+0000</EARLY_DEV_DT> 
  <EARLY_SHP_DT>20170101000100+0000</EARLY_SHP_DT> 
  <LATE_DEV_DT>20181231235900+0000</LATE_DEV_DT> 
  <LATE_SHP_DT>20181231235900+0000</LATE_SHP_DT> 
  <FRGHT_RATE>0.0000</FRGHT_RATE> 
  <SRVLVL>SL-LT</SRVLVL> 
  <SRC_HOST /> 
  <SRC_PORT /> 
  <WH_ID>LT1</WH_ID> 
  <AESITN /> 
  <AES_TYP /> 
  <AES_ACPT_DTE /> 
  <FTSRNUM /> 
  <EXPORT_TYP /> 
  <SHIP_CONT_TEL /> 
  <SHIP_CONT_NAME /> 
  <BOLA_NUM /> 
  <CARCOD>CARRIER2</CARCOD> 
<WAREHOUSE_ADDRESS_SEG>
  <ADR_ID>A000000002</ADR_ID> 
  <HOST_EXT_ID>A000000002</HOST_EXT_ID> 
  <ADRNAM>WH_ADRNAM----------2---------3---------4</ADRNAM> 
  <ADRLN1>WH_ADRLN1----------2---------3---------4</ADRLN1> 
  <ADRLN2>WH_ADRLN2----------2---------3---------4</ADRLN2> 
  <ADRLN3>WH_ADRLN3----------2---------3---------4</ADRLN3> 
  <ADRCTY>WH_ADRCTY----------2---------3</ADRCTY> 
  <ADRSTC>ADRSTC---1---------2</ADRSTC> 
  <ADRPSZ>WH_ADRPSZ----------2</ADRPSZ> 
  <CTRY_NAME>GBR</CTRY_NAME> 
  <RGNCOD /> 
  <PHNNUM>WH_PHNNUM----------2</PHNNUM> 
  <FAXNUM>WH_FAXNUM----------2</FAXNUM> 
  <RSAFLG>0</RSAFLG> 
  <TEMP_FLG>0</TEMP_FLG> 
  <LAST_NAME>WH_LAST_NAME-------2---------3</LAST_NAME> 
  <FIRST_NAME>WH_FIRST_NAME------2---------3</FIRST_NAME> 
  <HONORIFIC>WH_HONORIFIC-------2</HONORIFIC> 
  <ADR_DISTRICT /> 
  <WEB_ADR>WH_WEB_ADR---------2---------3---------4---------5---------6---------7</WEB_ADR> 
  <EMAIL_ADR>WH_EMAIL_ADR--------2---------3---------4---------5---------6---------7</EMAIL_ADR> 
  <PAGNUM /> 
  <LOCALE_ID>EN-GB</LOCALE_ID> 
  <ATTN_NAME>WH_ATTN_NAME-------2---------3---------4</ATTN_NAME> 
  <ATTN_TEL>WH_ATTN_TEL--------2</ATTN_TEL> 
  <CONT_NAME>WH_CONT_NAME-------2---------3---------4</CONT_NAME> 
  <CONT_TEL>WH_CONT_TEL--------2</CONT_TEL> 
  <CONT_TITLE>WH_CONT_TITLE------2</CONT_TITLE> 
  <PO_BOX_FLG>0</PO_BOX_FLG> 
  <SHIP_PHNNUM /> 
  <SHIP_FAXNUM /> 
  <SHIP_WEB_ADR /> 
  <SHIP_EMAIL_ADR /> 
  <SHIP_CONT_NAME /> 
  <SHIP_CONT_TITLE /> 
  <SHIP_CONT_TEL /> 
  <SHIP_ATTN_NAME>steve</SHIP_ATTN_NAME> 
  <SHIP_ATTN_PHNNUM /> 
  <ADRTYP /> 
  <TIM_ZON_CD>Europe/London</TIM_ZON_CD> 
  <UC_HOST_ADDRESS_ID /> 
  <UC_VAT_NUMBER /> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  </WAREHOUSE_ADDRESS_SEG>
<CARRIER_ADDRESS_SEG>
  <ADR_ID>A000000264</ADR_ID> 
  <HOST_EXT_ID>A000000264</HOST_EXT_ID> 
  <ADRNAM>CARRIER 2 NAME</ADRNAM> 
  <ADRLN1>Carrier 2 Address Line 1</ADRLN1> 
  <ADRLN2>Carrier 2 Street Name</ADRLN2> 
  <ADRLN3 /> 
  <ADRCTY>Carrier 2 Name</ADRCTY> 
  <ADRSTC /> 
  <ADRPSZ>NG15 0DT</ADRPSZ> 
  <CTRY_NAME>GBR</CTRY_NAME> 
  <RGNCOD /> 
  <PHNNUM /> 
  <FAXNUM /> 
  <RSAFLG>0</RSAFLG> 
  <TEMP_FLG>0</TEMP_FLG> 
  <LAST_NAME /> 
  <FIRST_NAME /> 
  <HONORIFIC /> 
  <ADR_DISTRICT /> 
  <WEB_ADR /> 
  <EMAIL_ADR /> 
  <PAGNUM /> 
  <LOCALE_ID>EN-GB</LOCALE_ID> 
  <ATTN_NAME /> 
  <ATTN_TEL /> 
  <CONT_NAME /> 
  <CONT_TEL /> 
  <CONT_TITLE /> 
  <PO_BOX_FLG>0</PO_BOX_FLG> 
  <SHIP_PHNNUM /> 
  <SHIP_FAXNUM /> 
  <SHIP_WEB_ADR /> 
  <SHIP_EMAIL_ADR /> 
  <SHIP_CONT_NAME /> 
  <SHIP_CONT_TITLE /> 
  <SHIP_CONT_TEL /> 
  <SHIP_ATTN_NAME /> 
  <SHIP_ATTN_PHNNUM /> 
  <ADRTYP /> 
  <TIM_ZON_CD /> 
  <UC_HOST_ADDRESS_ID /> 
  <UC_VAT_NUMBER /> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  </CARRIER_ADDRESS_SEG>
<SHIPMENT_ORDER>
  <SHIP_ID>SID0001950</SHIP_ID> 
  <HOST_SHIP_ID /> 
  <CLIENT_ID>CLIE01</CLIENT_ID> 
  <STCUST>C000000017</STCUST> 
  <BTCUST>C000000017</BTCUST> 
  <RTCUST>C000000017</RTCUST> 
  <ORDNUM>RK-PRESTAGE-01</ORDNUM> 
  <CPONUM /> 
  <ORDTYP>PPB</ORDTYP> 
  <DLVNUM /> 
  <DLVSEQ>0</DLVSEQ> 
  <CPOTYP /> 
  <CPODTE>20190313121000+0000</CPODTE> 
  <PAYTRM /> 
  <DLV_CONTACT /> 
  <RUSH_FLG>0</RUSH_FLG> 
  <CSTMS_ORDTYP /> 
  <SRV_TYPE /> 
<SHIPMENT_LINE_SEG>
  <SHIP_ID>SID0001950</SHIP_ID> 
  <SHIP_LINE_ID>SLN000017E</SHIP_LINE_ID> 
  <ORD_CLIENT_ID>CLIE01</ORD_CLIENT_ID> 
  <ORDNUM>RK-PRESTAGE-01</ORDNUM> 
  <ORDLIN>1</ORDLIN> 
  <ORDSLN>0</ORDSLN> 
  <SALES_ORD_NUM /> 
  <SALES_ORDLIN /> 
  <PART_NUM>VP61029791</PART_NUM> 
  <ORDERED_LINE_QTY>0000000008</ORDERED_LINE_QTY> 
  <SHIPMENT_LINE_QTY>0000000008</SHIPMENT_LINE_QTY> 
  <SHP_QTY>0000000000</SHP_QTY> 
  <INVSTS_PRG>A</INVSTS_PRG> 
  <INVSTS>A</INVSTS> 
  <STKUOM>EA</STKUOM> 
  <HSTACC>A</HSTACC> 
  <NON_ALC_FLG>0</NON_ALC_FLG> 
  <DISTRO_ID /> 
  <SOURCE_DISTRO_ID /> 
  <REQUESTED_SUPNUM /> 
  <REQUESTED_MANDTE /> 
  <REQUESTED_EXPIRE_DTE /> 
  <ORG_DISTRO_ID /> 
  <REQUESTED_SUP_LOTNUM /> 
  <REQUESTED_INV_ATTR_STR1 /> 
  <REQUESTED_INV_ATTR_STR2 /> 
  <REQUESTED_INV_ATTR_STR3 /> 
  <REQUESTED_INV_ATTR_STR4 /> 
  <REQUESTED_INV_ATTR_STR5 /> 
  <REQUESTED_INV_ATTR_STR6 /> 
  <REQUESTED_INV_ATTR_STR7 /> 
  <REQUESTED_INV_ATTR_STR8 /> 
  <REQUESTED_INV_ATTR_STR9 /> 
  <REQUESTED_INV_ATTR_STR10 /> 
  <REQUESTED_INV_ATTR_STR11 /> 
  <REQUESTED_INV_ATTR_STR12 /> 
  <REQUESTED_INV_ATTR_STR13 /> 
  <REQUESTED_INV_ATTR_STR14 /> 
  <REQUESTED_INV_ATTR_STR15 /> 
  <REQUESTED_INV_ATTR_STR16 /> 
  <REQUESTED_INV_ATTR_STR17 /> 
  <REQUESTED_INV_ATTR_STR18 /> 
  <REQUESTED_INV_ATTR_INT1 /> 
  <REQUESTED_INV_ATTR_INT2 /> 
  <REQUESTED_INV_ATTR_INT3 /> 
  <REQUESTED_INV_ATTR_INT4 /> 
  <REQUESTED_INV_ATTR_INT5 /> 
  <REQUESTED_INV_ATTR_FLT1 /> 
  <REQUESTED_INV_ATTR_FLT2 /> 
  <REQUESTED_INV_ATTR_FLT3 /> 
  <REQUESTED_INV_ATTR_DTE1 /> 
  <REQUESTED_INV_ATTR_DTE2 /> 
  <ORDLIN_CHG_REACOD /> 
  <REACOD_CMNT /> 
  <PRT_CLIENT_ID>CLIE01</PRT_CLIENT_ID> 
  <CSTPRT /> 
  <UNT_PRICE /> 
  <CRNCY_CODE>USD</CRNCY_CODE> 
  <REQUESTED_LOTNUM /> 
  <PARFLG>0</PARFLG> 
  </SHIPMENT_LINE_SEG>
<CLIENT_ADDRESS_SEG>
  <ADR_ID>A000000031</ADR_ID> 
  <HOST_EXT_ID>A000000031</HOST_EXT_ID> 
  <ADRNAM>Client 001</ADRNAM> 
  <ADRLN1>Address Line 1</ADRLN1> 
  <ADRLN2>Street Name</ADRLN2> 
  <ADRLN3>Address Line 3</ADRLN3> 
  <ADRCTY>City</ADRCTY> 
  <ADRSTC>Any Old Thing</ADRSTC> 
  <ADRPSZ>P0STALCODE</ADRPSZ> 
  <CTRY_NAME>GBR</CTRY_NAME> 
  <RGNCOD>Region</RGNCOD> 
  <PHNNUM>0111111111</PHNNUM> 
  <FAXNUM>1111111111</FAXNUM> 
  <RSAFLG>1</RSAFLG> 
  <TEMP_FLG>0</TEMP_FLG> 
  <LAST_NAME>LastName</LAST_NAME> 
  <FIRST_NAME>FirstName</FIRST_NAME> 
  <HONORIFIC>Honorific</HONORIFIC> 
  <ADR_DISTRICT>AddressDistrict</ADR_DISTRICT> 
  <WEB_ADR>http://client01.co.uk</WEB_ADR> 
  <EMAIL_ADR>client01@kn.com</EMAIL_ADR> 
  <PAGNUM /> 
  <LOCALE_ID /> 
  <ATTN_NAME>RecAttName</ATTN_NAME> 
  <ATTN_TEL /> 
  <CONT_NAME>ReceivingContactName</CONT_NAME> 
  <CONT_TEL>2111111111</CONT_TEL> 
  <CONT_TITLE>RCT</CONT_TITLE> 
  <PO_BOX_FLG>1</PO_BOX_FLG> 
  <SHIP_PHNNUM>0222222222</SHIP_PHNNUM> 
  <SHIP_FAXNUM>1222222222</SHIP_FAXNUM> 
  <SHIP_WEB_ADR>shipping.kn.com</SHIP_WEB_ADR> 
  <SHIP_EMAIL_ADR>shipping@client01.com</SHIP_EMAIL_ADR> 
  <SHIP_CONT_NAME>ShippingContactNames</SHIP_CONT_NAME> 
  <SHIP_CONT_TITLE>ShippingContactTitle</SHIP_CONT_TITLE> 
  <SHIP_CONT_TEL>2222222222</SHIP_CONT_TEL> 
  <SHIP_ATTN_NAME>3222222222</SHIP_ATTN_NAME> 
  <SHIP_ATTN_PHNNUM /> 
  <ADRTYP /> 
  <TIM_ZON_CD>UTC</TIM_ZON_CD> 
  <UC_HOST_ADDRESS_ID /> 
  <UC_VAT_NUMBER /> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  </CLIENT_ADDRESS_SEG>
<ST_CUST_SEG>
  <ADR_ID>A000000118</ADR_ID> 
  <HOST_EXT_ID>C000000017</HOST_EXT_ID> 
  <ADRNAM>CUSTOMER1</ADRNAM> 
  <ADRLN1>Customer 1 Line 1</ADRLN1> 
  <ADRLN2>Customer 1 Street Name</ADRLN2> 
  <ADRLN3>Customer 1 Line 3</ADRLN3> 
  <ADRCTY>Customer 1 City</ADRCTY> 
  <ADRSTC /> 
  <ADRPSZ>CU10NN</ADRPSZ> 
  <CTRY_NAME>GBR</CTRY_NAME> 
  <RGNCOD /> 
  <PHNNUM /> 
  <FAXNUM>9222222222</FAXNUM> 
  <RSAFLG>0</RSAFLG> 
  <TEMP_FLG>1</TEMP_FLG> 
  <LAST_NAME /> 
  <FIRST_NAME /> 
  <HONORIFIC /> 
  <ADR_DISTRICT /> 
  <WEB_ADR /> 
  <EMAIL_ADR>shipto@kn.com</EMAIL_ADR> 
  <PAGNUM /> 
  <LOCALE_ID>EN-GB</LOCALE_ID> 
  <ATTN_NAME /> 
  <ATTN_TEL /> 
  <CONT_NAME>ShipToName</CONT_NAME> 
  <CONT_TEL>0111111111</CONT_TEL> 
  <CONT_TITLE /> 
  <PO_BOX_FLG>0</PO_BOX_FLG> 
  <SHIP_PHNNUM /> 
  <SHIP_FAXNUM /> 
  <SHIP_WEB_ADR /> 
  <SHIP_EMAIL_ADR /> 
  <SHIP_CONT_NAME /> 
  <SHIP_CONT_TITLE /> 
  <SHIP_CONT_TEL /> 
  <SHIP_ATTN_NAME /> 
  <SHIP_ATTN_PHNNUM /> 
  <ADRTYP>CST</ADRTYP> 
  <TIM_ZON_CD>Europe/London</TIM_ZON_CD> 
  <UC_HOST_ADDRESS_ID>address01</UC_HOST_ADDRESS_ID> 
  <UC_VAT_NUMBER>1234567890</UC_VAT_NUMBER> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  </ST_CUST_SEG>
  </SHIPMENT_ORDER>
<SHIPMENT_UNIT_SUMMARY>
  <SHIP_ID>SID0001950</SHIP_ID> 
  <NUMLPN>1</NUMLPN> 
  <SHIP_GRSWGT>31400</SHIP_GRSWGT> 
  <SHIP_NETWGT>6400</SHIP_NETWGT> 
  <SHIP_VOL>43200</SHIP_VOL> 
<SHIPMENT_UNIT_DETAIL>
  <UC_PKG_ID>PKG00000000000052</UC_PKG_ID> 
  <UC_SEQNO>1</UC_SEQNO> 
  <UC_PKGTYPE>PAL</UC_PKGTYPE> 
  <UC_PKGHGT>4.5</UC_PKGHGT> 
  <UC_PKGLEN>120</UC_PKGLEN> 
  <UC_PKGWDT>80</UC_PKGWDT> 
  <UC_DIMUOM>CM</UC_DIMUOM> 
  <UC_PKGVOL>43200</UC_PKGVOL> 
  <UC_VOLUOM>CM3</UC_VOLUOM> 
  <UC_NETWGT>6400</UC_NETWGT> 
  <UC_GRSWGT>31400</UC_GRSWGT> 
  <UC_WGTUOM>G</UC_WGTUOM> 
  <UC_ITEM>VP61029791</UC_ITEM> 
  <UC_CONTENTS>General Goods</UC_CONTENTS> 
  <UC_ORDER>RK-PRESTAGE-01</UC_ORDER> 
  <UC_TRAKNM /> 
  <UC_PKGCODE>L000000000NP</UC_PKGCODE> 
  </SHIPMENT_UNIT_DETAIL>
  </SHIPMENT_UNIT_SUMMARY>
<RT_CUST_SEG>
  <ADR_ID>A000000118</ADR_ID> 
  <HOST_EXT_ID>C000000017</HOST_EXT_ID> 
  <ADRNAM>CUSTOMER1</ADRNAM> 
  <ADRLN1>Customer 1 Line 1</ADRLN1> 
  <ADRLN2>Customer 1 Street Name</ADRLN2> 
  <ADRLN3>Customer 1 Line 3</ADRLN3> 
  <ADRCTY>Customer 1 City</ADRCTY> 
  <ADRSTC /> 
  <ADRPSZ>CU10NN</ADRPSZ> 
  <CTRY_NAME>GBR</CTRY_NAME> 
  <RGNCOD /> 
  <PHNNUM /> 
  <FAXNUM>9222222222</FAXNUM> 
  <RSAFLG>0</RSAFLG> 
  <TEMP_FLG>1</TEMP_FLG> 
  <LAST_NAME /> 
  <FIRST_NAME /> 
  <HONORIFIC /> 
  <ADR_DISTRICT /> 
  <WEB_ADR /> 
  <EMAIL_ADR>shipto@kn.com</EMAIL_ADR> 
  <PAGNUM /> 
  <LOCALE_ID>EN-GB</LOCALE_ID> 
  <ATTN_NAME /> 
  <ATTN_TEL /> 
  <CONT_NAME>ShipToName</CONT_NAME> 
  <CONT_TEL>0111111111</CONT_TEL> 
  <CONT_TITLE /> 
  <PO_BOX_FLG>0</PO_BOX_FLG> 
  <SHIP_PHNNUM /> 
  <SHIP_FAXNUM /> 
  <SHIP_WEB_ADR /> 
  <SHIP_EMAIL_ADR /> 
  <SHIP_CONT_NAME /> 
  <SHIP_CONT_TITLE /> 
  <SHIP_CONT_TEL /> 
  <SHIP_ATTN_NAME /> 
  <SHIP_ATTN_PHNNUM /> 
  <ADRTYP>CST</ADRTYP> 
  <TIM_ZON_CD>Europe/London</TIM_ZON_CD> 
  <UC_HOST_ADDRESS_ID>address01</UC_HOST_ADDRESS_ID> 
  <UC_VAT_NUMBER>1234567890</UC_VAT_NUMBER> 
  <ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME> 
  </RT_CUST_SEG>
  </SHIPMENT_SEG>
  </CTRL_SEG>
  </UC_PACKAGE_CONFIRMATION_IFD>
'
 
 Check the TODOs in the code:
 * Some clean up
 * ~~get the UUID from the message or generate~~
 * Handle exceptions in the integration flow.
 * More tests - CLOSE_SHIPMENT, HTTP, sad flows :*( 
 
 Thoughts:
 * Auditing - how? Use of wiretap?
 