package paymaya.packager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class BuildISOMessage {
	InputStream input = null;
	
	public BuildISOMessage() {
		// TODO Auto-generated constructor stub
		try {
			//input = new FileInputStream("/GenericPackager.xml");
			input = this.getClass().getResourceAsStream("/GenericPackager.xml");
			
			GenericPackager packager = new GenericPackager(input);
			//
			// Create ISO Message
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.setMTI("0200");
			isoMsg.set(3, "201234");
			isoMsg.set(4, "10000");
			isoMsg.set(7, "110722180");
			isoMsg.set(11, "123456");
			isoMsg.set(44, "A5DFGR");
			isoMsg.set(105, "ABCDEFGHIJ 1234567890");
	 
			// print the DE list
			logISOMsg(isoMsg);
	 
			// Get and print the output result
			byte[] data = isoMsg.pack();
			System.out.println("RESULT : " + new String(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) throws IOException, ISOException {
		
		new BuildISOMessage();
	}
 
	private static void logISOMsg(ISOMsg msg) {
		System.out.println("----ISO MESSAGE-----");
		try {
			System.out.println("  MTI : " + msg.getMTI());
			for(int i=1;i<=msg.getMaxField();i++) {
				if(msg.hasField(i)) {
					System.out.println("Field-"+i+": "+msg.getString(i));
				}
			}

		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}
 
	}
}
