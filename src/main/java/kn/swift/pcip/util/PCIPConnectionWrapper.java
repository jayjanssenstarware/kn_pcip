package kn.swift.pcip.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redprairie.moca.MocaArgument;
import com.redprairie.moca.MocaException;
import com.redprairie.moca.MocaResults;
import com.redprairie.moca.client.ConnectionUtils;
import com.redprairie.moca.client.MocaConnection;

import kn.swift.pcip.configuration.properties.WmsProperties;

@Component
public class PCIPConnectionWrapper {

	private WmsProperties wmsProps;

	public PCIPConnectionWrapper(@Autowired WmsProperties wmsProps) {
		this.wmsProps = wmsProps;
	}
//TODO This should be connection pooled for efficiency
	public MocaResults executeCommand(String command, MocaArgument... params) throws MocaException {
		String url = wmsProps.getApi().getUrl() + "/service";
		try (MocaConnection conn = ConnectionUtils.createConnection(url, null)) {
			ConnectionUtils.login(conn, wmsProps.getApi().getUser(), wmsProps.getApi().getPassword());
			return conn.executeCommandWithArgs(command, params);
		}
	}

}
