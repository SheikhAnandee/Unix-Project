package chat_client_and_server;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class client_frame extends javax.swing.JFrame {

    // Define logger instances
    private static final Logger auditLogger = Logger.getLogger("audit");
    private static final Logger errorLogger = Logger.getLogger("error");
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    private client_frame(String username) {
        initComponents();
        // Initialize the loggers
        initLoggers();
        // Start the thread for receiving messages
        ListenThread();
        this.username = username;
    }

    //--------------------------//
    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    //--------------------------//
    public void userAdd(String data) {
        users.add(data);
    }

    //--------------------------//
    public void userRemove(String data) {
        ta_chat.append(data + " is now offline.\n");
    }

    //--------------------------//
    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList) {
            //users.append(token + "\n");
        }
    }

    //--------------------------//
    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    public void Disconnect() {
        try {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch (IOException ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }

    public client_frame() {
        initComponents();
        // Initialize the loggers
        initLoggers();
        // Start the thread for receiving messages
        ListenThread();
    }

    private void initLoggers() {
        // Configure handlers and formatters for loggers
        try {
            FileHandler auditFileHandler = new FileHandler("audit_log_client.txt", true);
            FileHandler errorFileHandler = new FileHandler("error_log_client.txt", true);

            SimpleFormatter formatter = new SimpleFormatter();
            auditFileHandler.setFormatter(formatter);
            errorFileHandler.setFormatter(formatter);

            auditLogger.addHandler(auditFileHandler);
            errorLogger.addHandler(errorFileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //--------------------------//
    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    // Ensure that the data array has at least 3 elements before accessing them
                    if (data.length >= 3) {
                        if (data[2].equals(chat)) {
                            ta_chat.append(data[0] + ": " + data[1] + "\n");
                            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                        } else if (data[2].equals(connect)) {
                            ta_chat.removeAll();
                            userAdd(data[0]);
                        } else if (data[2].equals(disconnect)) {
                            userRemove(data[0]);
                        } else if (data[2].equals(done)) {
                            //users.setText("");
                            writeUsers();
                            users.clear();
                        }
                    } else {
                        // Handle the case where the data array doesn't have enough elements
                        System.err.println("Received incomplete data: " + stream);
                    }
                }
            } catch (IOException ignored) {
                // Handle exceptions if necessary
            }
        }
    }

    //--------------------------//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        jButton1.setText("New client");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_newInstanceActionPerformed(evt);
            }
        });

        jLabel1.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_disconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_username)
                    .addComponent(b_connect)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_disconnect)
                    .addComponent(jButton1)
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_chat)
                    .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed

    }//GEN-LAST:event_tf_usernameActionPerformed


    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        username = tf_username.getText();
        String password = tf_password.getText();

        File f = null;
        FileInputStream fis = null;
        DataInputStream dis = null;

        boolean validUser = false;
        try {
            f = new File("user_database.ser");
            if (!f.exists()) {
                System.out.println("The login file doesn't exist");
            } else {
                fis = new FileInputStream(f);
                dis = new DataInputStream(fis);

                while (dis.available() > 0) {  // Check if there's more data to read
                    String storedUsername = dis.readUTF();
                    String storedPassword = dis.readUTF();

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        validUser = true;
                        if (!isConnected) {
                            sock = new Socket(address, port);

                            try {
                                // Initialize the reader here
                                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                            } catch (IOException ex) {
                                Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            writer = new PrintWriter(sock.getOutputStream());
                            writer.println(username + ":has connected.:Connect");
                            writer.flush();
                            isConnected = true;
                            tf_username.setEditable(false);

                            // Create and start the IncomingReader thread
                            Thread incomingReaderThread = new Thread(new IncomingReader());
                            incomingReaderThread.start();
                        }

                        // Log the connection
                        auditLogger.info(username + " has connected.");
                    }
                    else
                    {
                        ta_chat.append("Invalid login! Try Again. \n");
                    }
                }
            }
        } catch (IOException e) {
            ta_chat.append("Cannot Connect! Try Again. \n");
            tf_username.setEditable(true);

            // Log the error
            errorLogger.severe("Cannot Connect: " + e.getMessage());

        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void b_newInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_newInstanceActionPerformed
        // Generate a unique identifier for the new instance (e.g., based on current time)
        String newInstanceUsername = "User" + System.currentTimeMillis();

        // Create a new instance of the client application with the unique username
        client_frame newInstance = new client_frame(newInstanceUsername);

        // Show the new instance's UI
        newInstance.setVisible(true);
    }//GEN-LAST:event_b_newInstanceActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new client_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
