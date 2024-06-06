import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Chatapp2 extends Frame implements ActionListener,Runnable {
    TextField textfield;
    TextArea textarea;
    Button btn;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread thread;
    Label heading;
    Chatapp2(){
        heading=new Label("HOPE");
        heading.setBounds(200,20,50,50);
        textarea=new TextArea();
        textarea.setBounds(20,70,450,320);
        textfield =new TextField();
        textfield.setBounds(20,400,370,50);
        btn=new Button("SEND");
        btn.setBounds(400,400,70,50);
        btn.addActionListener(this);
        thread=new Thread(this);
        thread.setDaemon(true);
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
            socket=new Socket("localhost",9999);
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());

        }catch(Exception e){}

        add(heading);
        add(textarea);
        add(textfield);
        add(btn);
        setTitle("HOPE");
        setLayout(null);
        setSize(500,500);
        setVisible(true);


    }
    @Override
    public void run() {
        while(true){
            try{
                String msg=dataInputStream.readUTF();
                textarea.append("FAITH: "+msg+"\n");
            }catch (Exception e){

            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        String msg=textfield.getText();
        textarea.append("HOPE: "+msg+"\n");
        textfield.setText("");
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }catch (Exception ex){}
    }
    public static void main(String[] args) {
        new Chatapp2();
    }


}

