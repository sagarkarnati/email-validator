package com.vidya.tools.email.validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMTPValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMTPValidator.class);
	
	@Value("${app.verifierDomain}")
	private String verifierDomain;
	
	@Value("${app.verifierAddress}")
	private String verifierAddress;
	
	public boolean isValid(String mxRecord, String emailToValidate) {
		
		Socket skt = null;
		BufferedReader rdr = null;
		BufferedWriter wtr = null;
		try {
			int res;
			skt = new Socket(mxRecord, 25);
			rdr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			wtr = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));

			res = hear(rdr);
			if (res != 220) {
				LOGGER.debug("Invalid header");
				return false;
			}
			say(wtr, "HELO " + verifierDomain);

			res = hear(rdr);
			if (res != 250) {
				LOGGER.debug("Not ESMTP");
				return false;
			}

			// validate the sender address
			say(wtr, "MAIL FROM: <" + verifierAddress + ">");
			res = hear(rdr);
			if (res != 250) {
				LOGGER.debug("Sender rejected");
				return false;
			}

			say(wtr, "RCPT TO: <" + emailToValidate + ">");
			res = hear(rdr);

			// be polite
			say(wtr, "RSET");
			hear(rdr);
			say(wtr, "QUIT");
			hear(rdr);
			if (res != 250) {
				LOGGER.debug("Address is not valid!");
				return false;
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(rdr);		
			IOUtils.closeQuietly(wtr);
			IOUtils.closeQuietly(skt);
		}
		return true;
	}

	private static int hear(BufferedReader in) throws IOException {
		String line;
		int res = 0;

		while ((line = in.readLine()) != null) {
			String pfx = line.substring(0, 3);
			try {
				res = Integer.parseInt(pfx);
			} catch (Exception ex) {
				res = -1;
			}
			if (line.charAt(3) != '-')
				break;
		}

		return res;
	}

	private static void say(BufferedWriter wr, String text) throws IOException {
		wr.write(text + "\r\n");
		wr.flush();
	}

}