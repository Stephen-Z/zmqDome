import org.zeromq.ZMQ;

public class zeromqServerDemo{
	public static void main(String [] args) throws Exception{
		//System.out.println("zermq package is ready\n");
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:5555");

		while(!Thread.currentThread().isInterrupted()){
			byte[] request=responder.recv(0);
			System.out.println("Received Hello....");

			Thread.sleep(1000);

			String reply="World";
			responder.send(reply.getBytes(),0);
		}
		responder.close();
		context.term();
	}
}