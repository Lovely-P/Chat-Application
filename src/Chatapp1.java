import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Chatapp1 extends Frame implements ActionListener,Runnable {
    TextField textfield;
    TextArea textarea;
    Button btn;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread thread;
    Label heading;
    Chatapp1(){
        heading=new Label("FAITH");
        heading.setBounds(200,20,50,50);
        textfield =new TextField();
        textfield.setBounds(20,400,370,50);
        textarea=new TextArea();
        textarea.setBounds(20,70,450,320);
        btn=new Button("SEND");
        btn.setBounds(400,400,70,50);
        btn.addActionListener(this);
        thread=new Thread(this);
        thread.start();
        textarea.setBackground(new Color(245, 245, 220));
        textarea.setForeground(new Color(139, 69, 19));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        try {
            serverSocket = new ServerSocket(9999);
            socket=serverSocket.accept();
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());

        }catch(Exception e){}

        add(heading);

        add(textarea);
        add(textfield);
        add(btn);
        setTitle("FAITH");
        setLayout(null);
        setSize(500,500);
        setVisible(true);


    }
    @Override
    public void run() {
        while(true){
            try{
                String msg=dataInputStream.readUTF();
                textarea.append("HOPE: "+msg+"\n");
            }catch (Exception e){

            }}

    }
    public void actionPerformed(ActionEvent e) {
        String msg=textfield.getText();
        textarea.append("FAITH: "+msg+"\n");
        textfield.setText("");
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }catch (Exception ex){}
    }
    public static void main(String[] args) {
        new Chatapp1();
    }


}
