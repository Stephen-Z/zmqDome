import org.zeromq.ZMQ;

public class zeromqClientDemo{
	public static void main(String[] args){
		ZMQ.Context context = ZMQ.context(1);

		System.out.println("connecting to server\n");
		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.connect("tcp://localhost:5556");

		for(int i=0;i<10;i++){
			String requestStr="Hello";
			System.out.println("sending Hello ==>" + i);
			requester.send(requestStr.getBytes(),0);

			byte[] reply = requester.recv(0);
			System.out.println("Received " + new String(reply)+ " "+"==>"+i);
		}
		requester.close();
		context.term();
	}
}