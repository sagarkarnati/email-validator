package com.vidya.tools.email.mxrecords;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MXRecordFetcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(MXRecordFetcher.class);

	public List<String> fetch(String hostName) {
		List<String> mxRecordList = new ArrayList<String>();
		try {
			
			// Perform a DNS lookup for MX records in the domain
			Hashtable env = new Hashtable();
			env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
			
			DirContext ictx = new InitialDirContext(env);
			Attributes attrs = ictx.getAttributes(hostName, new String[] { "MX" });
			Attribute attr = attrs.get("MX");

			// if we don't have an MX record, try the machine itself
			if ((attr == null) || (attr.size() == 0)) {
				attrs = ictx.getAttributes(hostName, new String[] { "A" });
				attr = attrs.get("A");
				if (attr == null) {
					LOGGER.info("No match for name '{}'",hostName);

					return mxRecordList;
				}
			}
			// Huzzah! we have machines to try. Return them as an array list
			// NOTE: We SHOULD take the preference into account to be absolutely
			// correct. This is left as an exercise for anyone who cares.
			NamingEnumeration en = attr.getAll();

			while (en.hasMore()) {
				String mailhost;
				String x = (String) en.next();
				String f[] = x.split(" ");
				// THE fix *************
				if (f.length == 1) {
					mailhost = f[0];
				}
				else if (f[1].endsWith(".")) {
					mailhost = f[1].substring(0, (f[1].length() - 1));
				}
				else {
					mailhost = f[1];
				}
				// THE fix *************
				mxRecordList.add(mailhost);
			}			
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return mxRecordList;
	}
}