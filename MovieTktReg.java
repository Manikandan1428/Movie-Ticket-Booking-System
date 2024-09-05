import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;


import com.toedter.calendar.JDateChooser;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;








/**
 * MovieTktReg
 */
public class MovieTktReg {
    
 public static void main(String[] args) {
    
   
    final String dbUrl = "jdbc:mysql://localhost/movieTicketBooking?serverTimezone=UTC";
    final String user = "root";
    final String pwdd ="1328";
    
    

    JFrame f = new JFrame("MOVIE TICKET BOOKING SYSTEM");
    f.getContentPane().setBackground(Color.BLACK);
    
    JLabel l1,l2,l3,l4;
    JTextField tf1;
    JButton b1,b2;
    JPasswordField p1;

    

    l1 = new JLabel("LOGIN");
    l1.setForeground(Color.decode("#DAF7A6"));
    l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
    l1.setBounds(200, 30, 400,30);

    l2 = new JLabel("Registered Email");
    l2.setForeground(Color.WHITE);
    l2.setBounds(80,100 , 150, 30);

    l3 = new JLabel("Enter Password");
    l3.setForeground(Color.WHITE);
    l3.setBounds(80,200 , 150, 30);

    l4 = new JLabel("Don't have an account? -->");
    l4.setForeground(Color.WHITE);
    l4.setBounds(200,300,170,30);

    tf1 = new JTextField();
    tf1.setBounds(250,100,200,30);
    
    p1 = new JPasswordField();
    p1.setBounds(250,200,200,30);
    
    
    b1 = new JButton("LOGIN");
    b1.setBounds(50,300,100,30);
    b1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){

            f.dispose();
            
            String email = tf1.getText();
            String passw =new String(p1.getPassword());
            
            String sqlEmail = "";
            String sqlPwd = "";
            String sqluserType = "";
            String sqlusernm="";

            try {
                Connection lgnCn = DriverManager.getConnection(dbUrl, user, pwdd);
                Statement stmt1 = null;
                stmt1 = lgnCn.createStatement();
                

                String lgnQry = "select Email,pwd,userType,name from user where Email='"+email+"'";
                
                
                ResultSet loginDeets =  stmt1.executeQuery(lgnQry);
                
                while (loginDeets.next()) {
                    sqlEmail = loginDeets.getString("Email");
                    sqlPwd = loginDeets.getString("pwd");
                    sqluserType = loginDeets.getString("userType");
                    sqlusernm=loginDeets.getString("name");
                    
                }
                
                

            } catch (Exception ex) {
                System.out.println(ex);
            }
            final String userEmail=sqlEmail;
            String finalSqlusernm = sqlusernm;
            //this is for user login
            JComboBox cb1,cb2;
            String[] citySelected={"0"};
            String cit[] = {"Bangalore","Mumbai","Chennai","Madurai","Hyderabad","Delhi"};
            if(email.equals(sqlEmail) && passw.equals(sqlPwd) && sqluserType.equals("User") ){
                JOptionPane.showMessageDialog(f,"Login success");

                JFrame sc = new JFrame("SELECT CITY");
                sc.getContentPane().setBackground(Color.black);
                JLabel city,lang;
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLACK);
                
                JButton hp;

                city = new JLabel("Select City");
                city.setForeground(Color.WHITE);
                city.setBounds(50,50,100,30);

                lang = new JLabel("Movie Langauage");
                lang.setForeground(Color.WHITE);
                lang.setBounds(50,100,150,30);

                
                cb1 = new JComboBox(cit);
                // cb1.setForeground(Color.WHITE);

                String lan[] = {"Tamil","English","Hindi","Telegu","kannada","Malayalam"};
                cb2 = new JComboBox(lan);
               
                // cb2.setForeground(Color.WHITE);
                
                hp = new JButton("Next");
               // hp.setForeground(Color.WHITE);
                hp.setBounds(150,50,100,30);
                hp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        sc.dispose();
                        JFrame hpf = new JFrame("Home Page");
                        hpf.getContentPane().setBackground(Color.black);
                        JLabel hompag,inv,brandName;
                        JButton showButton;


                        
                       
                        
                        hompag = new JLabel("Enjoy your Movie with popcorn not family ;)");
                        hompag.setBounds(600,30,300,30);
                        hompag.setForeground(Color.WHITE);
                        hompag.setFont(new Font("Times New Roman", Font.BOLD, 16));
                
                        inv = new JLabel("WELCOME!");
                        inv.setBounds(700,0,200,30);
                        inv.setForeground(Color.WHITE);
                        inv.setFont(new Font("Times New Roman", Font.BOLD, 20));

                        String date[]={"0"};
                        JDateChooser dateChooser = new JDateChooser();
                        dateChooser.setDateFormatString("dd-MM-yyyy");
                        dateChooser.setDate(Calendar.getInstance().getTime());
                        dateChooser.setBounds(1300, 20, 200, 30);
                        hpf.add(dateChooser);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        date[0] = dateFormat.format(dateChooser.getDate());
                        
                        dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
                            
                                @Override
                                public void propertyChange(PropertyChangeEvent evt) {
                                    Date selectedDate = (Date) evt.getNewValue(); // Get the new date
                                    String finDa = new SimpleDateFormat("dd-MM-yyyy").format(selectedDate);
                                    // Use finalDate as needed
                                    date[0]=finDa;
                                }
                                
                            });
                            
                        
                        
                       
                        brandName=new JLabel(new ImageIcon("C:\\Users\\Manikandan\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-01-26 020915.png"));
                        brandName.setBounds(0,40,400,200);

                        JButton support = new JButton("Help");
                        support.setBounds(0, 0, 100, 30);
                        support.setForeground(Color.BLACK);
                        hpf.add(support);

                        support.addActionListener(supp->{
                            
                            JFrame contactDetails=new JFrame("Contact details");
                    contactDetails.getContentPane().setBackground(Color.BLACK);
                    try(
                        Connection conn = DriverManager.getConnection(dbUrl, user, pwdd);
                        PreparedStatement prepstmt = conn.prepareStatement("select admin_email,admin_ContactNumber from adminContact")){
                            ResultSet res=prepstmt.executeQuery();
                            

                            int yadNum=80;
                            
                            int yadEmailName=50;
                            while(res.next()){
                                String adminEmailll=res.getString("admin_email");
                                String cntNum=res.getString("admin_ContactNumber");
                                JLabel emailLbl=new JLabel("Email: ");
                                emailLbl.setBounds(50, yadEmailName, 100, 30);
                                emailLbl.setForeground(Color.WHITE);
                                emailLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(emailLbl);

                                JLabel emailDesc=new JLabel(adminEmailll);
                                emailDesc.setBounds(170, yadEmailName, 270, 30);
                                emailDesc.setForeground(Color.WHITE);
                                emailDesc.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(emailDesc);

                                JLabel numLbl=new JLabel("Phone: ");
                                numLbl.setBounds(50, yadNum, 100, 30);
                                numLbl.setForeground(Color.WHITE);
                                numLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(numLbl);

                                JLabel numb=new JLabel(cntNum);
                                numb.setBounds(170, yadNum, 200, 30);
                                numb.setForeground(Color.WHITE);
                                numb.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(numb);
                                yadEmailName=yadNum+30;
                                yadNum=yadEmailName+30;

                            }
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }

                    contactDetails.setSize(500, 350);
                    contactDetails.setLayout(null);
                    contactDetails.setVisible(true);
                    contactDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        });

                        JButton bookingHistory = new JButton("Booking History");
                        bookingHistory.setBounds(1300,60,160,20);
                        bookingHistory.setForeground(Color.black);
                        hpf.add(bookingHistory);
                        bookingHistory.addActionListener(bh->{
                        
                            
                                // SQL query to fetch the user's booking history
                                String sql = "SELECT movie_name, theater_name, show_date, booking_time, number_of_tickets FROM bookinghistory WHERE user_email = ?";
                            
                                
                                    
                                    try (Connection conn = DriverManager.getConnection(dbUrl, user, pwdd);
                                         PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            
                                        // Set the user email parameter
                                        pstmt.setString(1, userEmail);
                            
                                        // Execute the query
                                        ResultSet resultSet = pstmt.executeQuery();
                            
                                        // Create a new JFrame to display the booking history
                                        JFrame bookingHistoryFrame = new JFrame("Booking History");
                                        bookingHistoryFrame.getContentPane().setBackground(Color.black);
                                        bookingHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        bookingHistoryFrame.setSize(600, 400);
                                        bookingHistoryFrame.setLocationRelativeTo(null);
                            
                                        // Create a JPanel to hold the booking history
                                        JPanel bookingHistoryPanel = new JPanel();
                                        bookingHistoryPanel.setLayout(new BoxLayout(bookingHistoryPanel, BoxLayout.Y_AXIS));
                                        bookingHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                            
                                        // Iterate through the booking history and add each booking to the panel
                                        while (resultSet.next()) {
                                            String movieName = resultSet.getString("movie_name");
                                            String theaterName = resultSet.getString("theater_name");
                                            String showDate = resultSet.getString("show_date");
                                            String bookingTime = resultSet.getString("booking_time");
                                            int numOfTickets = resultSet.getInt("number_of_tickets");
                                        
                                            // Create a JPanel for each booking
                                            JPanel bookingPanel = new JPanel();
                                            
                                            bookingPanel.setLayout(new GridLayout(5, 1, 10, 10));
                                            bookingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                            bookingPanel.setBackground(Color.DARK_GRAY);

                                            bookingPanel.add(new JLabel("Movie: " + movieName)).setForeground(Color.WHITE);
                                            bookingPanel.add(new JLabel("Theater: " + theaterName)).setForeground(Color.WHITE);
                                            bookingPanel.add(new JLabel("Show Date: " + showDate)).setForeground(Color.WHITE);
                                            bookingPanel.add(new JLabel("Booking Time: " + bookingTime)).setForeground(Color.WHITE);
                                            bookingPanel.add(new JLabel("Tickets: " + numOfTickets)).setForeground(Color.WHITE);
                                        
                                            bookingHistoryPanel.add(bookingPanel);
                                            bookingHistoryPanel.add(Box.createVerticalStrut(10));
                                            bookingHistoryPanel.setBackground(Color.black);
                                            
                                        }
                                        
                                        // Add the booking history panel to the frame and make it visible
                                        bookingHistoryFrame.add(new JScrollPane(bookingHistoryPanel), BorderLayout.CENTER);
                                        bookingHistoryFrame.setVisible(true);
                                        
                            
                                    }
                                    catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                           
                        });
                        
                        final int[] s={0};
                        cb1.setBounds(1100,40,100,20);
                        cb1.setForeground(Color.black);
                       
                        String selectedCity = (String) cb1.getSelectedItem();
                        try {
                            Connection thrCn = DriverManager.getConnection(dbUrl, user, pwdd);
                            Statement thrsmt = null;
                            thrsmt = thrCn.createStatement();
                            
                            String thrQry = "SELECT * FROM theatre WHERE tcity='" + selectedCity + "'";
                            
                            ResultSet resultSet =  thrsmt.executeQuery(thrQry);
                            
                            int jp=200;
                            int thrLbl=200;
                            while (resultSet.next()) {
                                s[0]++;
                                String theaterName = resultSet.getString("tname").toUpperCase();
                                String showTimes = resultSet.getString("tshow");
                                String movies=resultSet.getString("tmovies");
                
                                JPanel theaterPanel = new JPanel();
                                theaterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                                theaterPanel.setBounds(400, jp,1100, 30);
                                jp+=50;
                                
                                JLabel theaterLabel = new JLabel(theaterName);
                                theaterLabel.setBounds(100,thrLbl,400,50);
                                theaterLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
                                theaterLabel.setForeground(Color.WHITE);
                                thrLbl+=50;
                                hpf.add(theaterLabel);
                                //theaterPanel.add(theaterLabel);
                                int x=400;
                               
                                String[] showTimesArray = showTimes.split(",");
                                String [] movieArray = movies.split(",");
                                int j=0;
                                for (int i=0;i<showTimesArray.length;i++) {
                                    
                                    if(j==movieArray.length){
                                        j=0;

                                    }

                                    hpf.revalidate();
                                    hpf.repaint();
                                  // String finalDate=date[0];

                                    showButton = new JButton(showTimesArray[i]+" -> "+movieArray[j]);
                                    String mov = movieArray[j];
                                    String finalTime=showTimesArray[i];
                                    j++;
                                    showButton.setForeground(Color.black);
                                    showButton.setBounds(x,300,200,30);
                                    x+=200;
                                    
                                    showButton.setFont(new Font("Times New Roman", Font.BOLD, 20    ));
                                    
                                   

                                    showButton.addActionListener(new ActionListener() {
                                        
                                        public void actionPerformed(ActionEvent e){
                                            
                                            hpf.dispose();
                                            JFrame movieName = new JFrame("REVIEW DETAILS");
                                            movieName.getContentPane().setBackground(Color.black);

                                            JLabel usernamLabel = new JLabel("Name : "+finalSqlusernm);
                                            usernamLabel.setBounds(10,10,300,30);
                                            usernamLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            usernamLabel.setForeground(Color.WHITE);
                                            movieName.add(usernamLabel);

                                            JLabel movnamLabel = new JLabel("Movie : "+mov);
                                            movnamLabel.setBounds(10,50,300,30);
                                            movnamLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            movnamLabel.setForeground(Color.WHITE);
                                            movieName.add(movnamLabel); 
                                           
                                            JLabel dateLabel = new JLabel("Date : "+date[0]);
                                            dateLabel.setBounds(10,90,300,30);
                                            dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            dateLabel.setForeground(Color.WHITE);
                                            movieName.add(dateLabel);

                                            
                                            
                                            JLabel timeLabel = new JLabel("Time : "+finalTime);
                                            timeLabel.setBounds(10,130,300,30);
                                            timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            timeLabel.setForeground(Color.WHITE);
                                            movieName.add(timeLabel);

                                            JLabel thrnamLabel = new JLabel("Theater : "+theaterName);
                                            thrnamLabel.setBounds(10,170,300,30);
                                            thrnamLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            thrnamLabel.setForeground(Color.WHITE);
                                            movieName.add(thrnamLabel);


                                            JLabel location = new JLabel("Location : "+(String) cb1.getSelectedItem());
                                            location.setBounds(10,210,200,20);
                                            location.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            location.setForeground(Color.WHITE);
                                            movieName.add(location);

                                            JLabel langLabel = new JLabel("Language : "+(String) cb2.getSelectedItem());
                                            langLabel.setBounds(10,250,200,20);
                                            langLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                            langLabel.setForeground(Color.WHITE);
                                            movieName.add(langLabel);                                            

                                            
                                            String [] finSeats = new String[100];
                                            int [] a = new int[1];
                                            a[0]=0;
                                            JButton seatSelection = new JButton("Select seat");
                                            seatSelection.setBounds(100,300,100,30);
                                            seatSelection.setForeground(Color.black);
                                            movieName.add(seatSelection);
                                            seatSelection.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e){
                                                    movieName.dispose();

                                                    JFrame seat=new JFrame("SEATS");
                                                    seat.getContentPane().setBackground(Color.BLACK);
    
                                                    int x=0,y=0,hor=1,ver=1,seatno=1;
                                                    char ch='A';
                                                    for (int k=0;k<700;k++) {
                                                        if (hor==6) {
                                                           JLabel space = new JLabel("        STEPS");
                                                            space.setBounds(x,y,100,20);
                                                            space.setForeground(Color.WHITE);
                                                            seat.add(space);
                                                            x+=100;
                                                            hor++;
                                                            continue;
                                                            
                                                            
                                                        }

                                                        if(hor>11){
                                                            hor=1;
                                                            seatno=1;
                                                            x=0;
                                                            y+=50;
                                                            ver++;
                                                            ch++;
                                                            if (ver==11) {
                                                                break;
                                                            }
                                                        }
                                                        
                                                        JButton mySeat = new JButton(String.valueOf(ch)+""+seatno);
                                                        mySeat.setBounds(x,y,60,20);
                                                        mySeat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                                                        x+=60;
                                                        hor++;
                                                        seatno++;
                                                        String [] selectedSeats = new String[100];
                                                       
                                                        
                                                        String str=""; 
                                                        mySeat.setForeground(Color.BLACK);
                                                        mySeat.addActionListener(new ActionListener() {
                                                            
                                                            
                                                            public void actionPerformed(ActionEvent e) {
                                                                
                                                                
                                                                int b=0;
                                                                // Toggle seat color when clicked
                                                                if (mySeat.getForeground().equals(Color.BLACK)) {
                                                                    mySeat.setForeground(Color.RED); // Change to selected color

                                                                    
                                                                    finSeats[a[0]] = mySeat.getText(); // Copy the selected seat data into finSeats array
                                                                    a[0]=a[0]+1; 
                                                                    b=a[0];                                                                  
                                                                
                                                                } else {
                                                                    mySeat.setForeground(Color.BLACK); // Change back to default color
                                                                    for (int i = 0; i < b; i++) {
                                                                        if (finSeats[i].equals(mySeat.getText())) {
                                                                            // Shift remaining elements to fill the gap
                                                                            for (int j = i; j < b- 1; j++) {
                                                                                finSeats[j] = finSeats[j + 1];
                                                                            }
                                                                            finSeats[b - 1] = null; // Clear the last element
                                                                            b--; // Decrement index as one seat is deselected
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                                
                                                            }
                                                        });
                                                        
                                                       seat.add(mySeat);

                                                    
                                                        
                                                    }
                                                    
                                                    

                                                    JButton next = new JButton("Next");
                                                    next.setBounds(50,520,150,30);
                                                    next.setForeground(Color.BLACK);
                                                    next.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));

                                                    next.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e){
                                                            seat.dispose();

                                                            
                                                            
                                                            JFrame payment = new JFrame("Payment");
                                                            payment.getContentPane().setBackground(Color.BLACK);
                                                            

                                                            JLabel selSeats = new JLabel("Selected seats: ");
                                                            selSeats.setBounds(10,20,100,30);
                                                            selSeats.setForeground(Color.WHITE);
                                                            selSeats.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                            payment.add(selSeats);

                                                            int t=120,u=20,numOfSeats=0,perTktPrice=198;
                                                            
                                                            for (int i = 0; i < a[0]; i++) {
                                                               
                                                                    JLabel seleSeats;
                                                                    if (i==a[0]-1) {
                                                                       seleSeats = new JLabel(finSeats[i]);
                                                                    }
                                                                    else{
                                                                        seleSeats=new JLabel(finSeats[i]+" ,");
                                                                    }
                                                                        seleSeats.setBounds(t, u, 40, 30);
                                                                        numOfSeats+=1;
                                                                        t += 40;
                                                                        if(t>350){
                                                                            u=u+20;
                                                                            t=120;
                                                                        }
                                                                        seleSeats.setForeground(Color.WHITE);
                                                                        seleSeats.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                                        payment.add(seleSeats);

                                                                        
                                                                
                                                            }   
                                                            final int[]popcornCount={0};
                                                            final int[]cokeCount={0};
                                                            
                                                            JLabel popcorn = new JLabel("Popcorn");
                                                            popcorn.setBounds(10,150,100,30);
                                                            popcorn.setForeground(Color.WHITE);
                                                            popcorn.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                            payment.add(popcorn);

                                                            JLabel cokeC = new JLabel("Total: "+cokeCount[0]);
                                                            cokeC.setBounds(250,200,100,30);
                                                            cokeC.setForeground(Color.WHITE);
                                                            cokeC.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                            payment.add(cokeC);

                                                            JLabel popC = new JLabel("Total: "+popcornCount[0]);
                                                            popC.setBounds(250,150,100,30);
                                                            popC.setForeground(Color.WHITE);
                                                            popC.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                            payment.add(popC);

                                                            JButton popcornAdd=new JButton("+");
                                                            popcornAdd.setBounds(180,150,50,20);
                                                            popcornAdd.setForeground(Color.BLACK);
                                                            popcornAdd.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                            payment.add(popcornAdd);
                                                            
                                                            

                                                            popcornAdd.addActionListener(new ActionListener() {
                                                                
                                                                public void actionPerformed(ActionEvent e){
                                                                   popcornCount[0]++;
                                                                   popC.setText("Total: "+popcornCount[0]); 
                                                                   payment.revalidate();
                                                                   payment.repaint();
                                                                }
                                                               
                                                            });

                                                            

                                                            JButton popcornDec=new JButton("-");
                                                            popcornDec.setBounds(120,150,40,20);
                                                            popcornDec.setForeground(Color.BLACK);
                                                            popcornDec.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                            payment.add(popcornDec);
                                                            

                                                            popcornDec.addActionListener(new ActionListener() {
                                                                
                                                                public void actionPerformed(ActionEvent e){
                                                                    if(popcornCount[0]>0){
                                                                        popcornCount[0]--; 
                                                                        popC.setText("Total: "+popcornCount[0]);
                                                                    }
                                                                    payment.revalidate();
                                                                    payment.repaint();
                                                                }
                                                               
                                                            });

                                                            JButton cokeAdd=new JButton("+");
                                                            cokeAdd.setBounds(180,200,50,20);
                                                            cokeAdd.setForeground(Color.BLACK);
                                                            cokeAdd.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                            payment.add(cokeAdd);
                                                            

                                                            cokeAdd.addActionListener(new ActionListener() {
                                                                
                                                                public void actionPerformed(ActionEvent e){
                                                                   cokeCount[0]++; 
                                                                   cokeC.setText("Total: "+cokeCount[0]);
                                                                   payment.revalidate();
                                                                   payment.repaint();
                                                                }
                                                                
                                                            });

                                                            JButton cokeDec=new JButton("-");
                                                            cokeDec.setBounds(120,200,40,20);
                                                            cokeDec.setForeground(Color.BLACK);
                                                            cokeDec.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                            payment.add(cokeDec);
                                                            

                                                            cokeDec.addActionListener(new ActionListener() {
                                                                
                                                                public void actionPerformed(ActionEvent e){
                                                                    if(cokeCount[0]>0){
                                                                        cokeCount[0]--; 
                                                                        cokeC.setText("Total: "+cokeCount[0]);
                                                                    }
                                                                    payment.revalidate();
                                                                    payment.repaint();
                                                                }
                                                               
                                                            });

                                                            JLabel coke = new JLabel("Coke");
                                                            coke.setBounds(10,200,100,30);
                                                            coke.setForeground(Color.WHITE);
                                                            coke.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                            payment.add(coke);


                                                            

                                                            int numOfTkt=numOfSeats;
                                                            
                                                            JLabel totAmt = new JLabel("Total Amount: "+(numOfTkt*perTktPrice));
                                                            totAmt.setBounds(190,250,300,20);
                                                            totAmt.setForeground(Color.WHITE);
                                                            totAmt.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                            payment.add(totAmt);

                                                            JButton done=new JButton("Done");
                                                            done.setBounds(20,250,100,20);
                                                            done.setForeground(Color.BLACK);
                                                            done.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
                                                            payment.add(done);
                                                            

                                                            done.addActionListener(new ActionListener() {
                                                                
                                                                public void actionPerformed(ActionEvent e){
                                                                    
                                                                    int tamt=(numOfTkt*perTktPrice)+(popcornCount[0] *120)+(cokeCount[0] *50);
                                                                    totAmt.setText("Total Amount: "+tamt);

                                                                    
                                                            JButton amtPay = new JButton("PAY");
                                                            amtPay.setBounds(250,300,80,20);
                                                            amtPay.setForeground(Color.BLACK);
                                                            amtPay.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                            payment.add(amtPay);

                                                            payment.revalidate();
                                                            payment.repaint();
                                                            
                                                            amtPay.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent e){
                                                                    payment.dispose();

                                                                    JFrame endFrame = new JFrame("Booking");
                                                                    endFrame.getContentPane().setBackground(Color.BLACK);

                                                                    JLabel endMsg = new JLabel("<html>Movie: "+mov+"<br>"+"Show: "+date[0]+" "+finalTime+"<br>"+"Number of Tkts: "+numOfTkt+"</html>");
                                                                    endMsg.setBounds(20,10,290,70);
                                                                    endMsg.setForeground(Color.WHITE);
                                                                    endMsg.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                                    endFrame.add(endMsg);

                                                                    JLabel qr =new JLabel();
                                                                    qr.setBounds(20, 120, 120, 120);
                                                                    endFrame.add(qr);

                                                                    try {
                                                                        String qrContent = "Theatre: " + theaterName+"\nMovie: " + mov + "\nShow: " + date[0]+" "+finalTime + "\nNumber of Tkts: " + numOfTkt+"\nPopcorn: "+popcornCount[0]+"\nCoke: "+cokeCount[0];
                                                                        BufferedImage qrCodeImage = generateQRCodeImage(qrContent);
                                                                        qr.setIcon(new ImageIcon(qrCodeImage));
                                                                    } catch (WriterException | IOException ex) {
                                                                        ex.printStackTrace();
                                                                        JOptionPane.showMessageDialog(endFrame, "Error generating QR code", "Error", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                    
                                                                    JLabel endMsg1 = new JLabel("<html>"+"Your booking is confirmed!"+"<br>"+"Thanks for visiting BlockMySeat :)"+"</html>");
                                                                    endMsg1.setBounds(20,80,290,40);
                                                                    endMsg1.setForeground(Color.WHITE);
                                                                    endMsg1.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                                    endFrame.add(endMsg1);

                                                                    
                                                                       
                                                                    
                                                                        String bookingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                                                        String sql = "INSERT INTO bookingHistory (user_email, movie_name, theater_name, show_date, booking_time, number_of_tickets) VALUES (?, ?, ?, ?, ?, ?)";
                                                                    
                                                                        try (Connection conn = DriverManager.getConnection(dbUrl, user, pwdd);
                                                                             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                                                                    
                                                                            pstmt.setString(1, userEmail);
                                                                            pstmt.setString(2, mov);
                                                                            pstmt.setString(3, theaterName);
                                                                            pstmt.setString(4, date[0]+" "+finalTime);
                                                                            pstmt.setString(5, bookingTime);
                                                                            pstmt.setInt(6, numOfTkt);
                                                                    
                                                                            pstmt.executeUpdate();
                                                                            System.out.println("Booking details added to database successfully.");
                                                                    
                                                                        } catch (SQLException ex) {
                                                                            ex.printStackTrace();
                                                                            JOptionPane.showMessageDialog(endFrame, "Error saving booking details", "Error", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    
                                                                        // Your existing code...
                                                                    

                                                                    endFrame.setBounds(400,300,300,350);
                                                                    endFrame.setLayout(null);
                                                                    endFrame.setVisible(true);
                                                                    endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                                   

                                                                }
                                                                });
                                                                   
                                                             

                                                            
                                                            }
                                                               
                                                        });
                                                                                                                      

                                                            payment.setBounds(300,200,400,400);
                                                            payment.setLayout(null);
                                                            payment.setVisible(true);
                                                            payment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                                       

                                                        }
                                                        private BufferedImage generateQRCodeImage(String text) throws WriterException, IOException {
                                                            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                                                            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 100, 100);
                                                    
                                                            BufferedImage qrCodeImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                                                            Graphics2D graphics = qrCodeImage.createGraphics();
                                                            graphics.setColor(Color.WHITE);
                                                            graphics.fillRect(0, 0, 100, 100);
                                                            graphics.setColor(Color.BLACK);
                                                    
                                                            for (int x = 0; x < 100; x++) {
                                                                for (int y = 0; y < 100; y++) {
                                                                    if (bitMatrix.get(x, y)) {
                                                                        graphics.fillRect(x, y, 1, 1);
                                                                    }
                                                                }
                                                            }
                                                            graphics.dispose();
                                                            return qrCodeImage;
                                                        }
                                                    
                                                        
                                                        
                                                    });
                                                    
                                                    seat.add(next);                                                   

                                                    JLabel entry = new JLabel("Entry/Exit-->");
                                                    entry.setBounds(550,520,200,30);
                                                    entry.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                                    entry.setForeground(Color.white);
                                                    seat.add(entry);

                                                    
                                                    JButton screen = new JButton("Hey, leech! Blood is here.");
                                                    screen.setBounds(100,600,500,30);
                                                    screen.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
                                                    seat.add(screen);

                                                    seat.setBounds(300,200,700, 700);
                                                    seat.setLayout(null);
                                                    seat.setVisible(true);
                                                    seat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                   
                                                }
                                            });                                            

                                            movieName.setBounds(400,200,350,500);
                                            // (1550, 970);
                                            movieName.setLayout(null);
                                            movieName.setVisible(true);
                                            movieName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                    }    
                                    });
                
                                    // Add action listener if needed
                
                                    theaterPanel.add(showButton);
                                }
                
                                hpf.add(theaterPanel);
                            }
                
                            thrCn.close();
                            }
                            
                         catch (Exception ex) {
                            System.out.println(ex);
                        }
                       
                        hpf.add(hompag); hpf.add(inv);hpf.add(brandName);hpf.add(cb1);
                        hpf.setLayout(new GridLayout(s[0],1));
                        hpf.setSize(1550, 970);
                        hpf.setLayout(null);
                        hpf.setVisible(true);
                        hpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
           
                
                panel.add(city);
                panel.add(cb1);
                
                panel.add(lang);
                panel.add(cb2);
                

                sc.add(hp);

                sc.add(panel);

                sc.setBounds(500,100,450,150);
                sc.setVisible(true);
                
                sc.setLayout(null);
                sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            
            else if (email.equals(sqlEmail) && passw.equals(sqlPwd) && sqluserType.equals("Admin")) {
                JOptionPane.showMessageDialog(f,"Hello admin");
                
                
                JFrame adminHome = new JFrame("Home");
                adminHome.getContentPane().setBackground(Color.BLACK);

                JLabel adminName = new JLabel("Welcome "+sqlusernm+"!");
                adminName.setBounds(450,10,500,30);
                adminName.setFont(new Font("Times New Roman", Font.BOLD, 20));
                adminName.setForeground(Color.white);
                adminHome.add(adminName);

                JButton adminTheatre = new JButton("Theatre");
                adminTheatre.setBounds(0,50,100,20);
                adminTheatre.setFont(new Font("Times New Roman", Font.BOLD, 20));
                adminHome.add(adminTheatre);
                adminTheatre.addActionListener(eg->{
                    adminHome.dispose();
                    JFrame theatreList=new JFrame("Theatre");
                    theatreList.getContentPane().setBackground(Color.BLACK);
                    JComboBox cbb1;
                    cbb1 = new JComboBox(cit);
                    cbb1.setBounds(0, 0, 100, 30);
                    theatreList.add(cbb1);
                    cbb1.addActionListener(eh->{
                        
                        
                    citySelected[0]=(String)cbb1.getSelectedItem();
                    for (Component comp : theatreList.getContentPane().getComponents()) {
                        if (!(comp instanceof JComboBox)) {
                            theatreList.remove(comp);
                        }
                    }
                    try (
                        Connection thrCn = DriverManager.getConnection(dbUrl, user, pwdd)){
                        Statement thrsmt = null;
                        thrsmt = thrCn.createStatement();
                        theatreList.revalidate();
                        theatreList.repaint();
                        String thrQry = "SELECT tname FROM theatre WHERE tcity='"+citySelected[0]+"'";
                        
                        ResultSet resultSet =  thrsmt.executeQuery(thrQry);
                        
                        
                        int yPosition = 50;
                        while (resultSet.next()) {
                            String theaterName = resultSet.getString("tname").toUpperCase();
                            JLabel thrLbl = new JLabel(theaterName);
                            thrLbl.setForeground(Color.WHITE);
                            thrLbl.setBounds(50, yPosition, 300, 30); // Adjust as needed
                            theatreList.add(thrLbl);
                            yPosition += 40;
                            theatreList.revalidate();
                            theatreList.repaint();
                        }

                        JLabel enterMovieName = new JLabel("Enter the theatre name to update");
                        enterMovieName.setBounds(100, 0, 250, 30);
                        enterMovieName.setFont(new Font("Times New Roman", Font.BOLD, 15));
                        enterMovieName.setForeground(Color.WHITE);
                        theatreList.add(enterMovieName);


                        JTextField theatreName=new JTextField();
                        theatreName.setBounds(350, 0, 200, 30);
                        theatreList.add(theatreName);

                        

                        JButton DeleteMovie =new JButton("Delete");
                        DeleteMovie.setBounds(600,0,150,30);
                        DeleteMovie.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        theatreList.add(DeleteMovie);
                        
                        DeleteMovie.addActionListener(add->{
                            if(!(theatreName.getText().trim().isEmpty())){
                            String movName=theatreName.getText();
                            
                            try(
                                Connection addCn = DriverManager.getConnection(dbUrl, user, pwdd);
                                PreparedStatement stmt = addCn.prepareStatement("DELETE FROM THEATRE WHERE tname='"+movName+"'"+"and tcity='"+citySelected[0]+"'")){
                                    int rowsAffected = stmt.executeUpdate();
                                    if (rowsAffected > 0) {
                                        JOptionPane.showMessageDialog(null, "Deleted successfully");
                                        theatreList.dispose();                                   }
                                theatreList.revalidate();
                                theatreList.repaint();
                                }
                            
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter the theatre name to delete");
                            }
                        });

                        JButton adminMovie = new JButton("Movies");
                        adminMovie.setBounds(830,00,100,30);
                        adminMovie.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        theatreList.add(adminMovie);

                        adminMovie.addActionListener(am->{
                            if(!(theatreName.getText().trim().isEmpty())){
                                theatreList.dispose();
                                JFrame movieEdit = new JFrame("Movie Updation Corner");
                                movieEdit.getContentPane().setBackground(Color.BLACK);


                                try(
                                Connection addCn = DriverManager.getConnection(dbUrl, user, pwdd)){
                                Statement stmt = addCn.createStatement();
                                String theName=theatreName.getText();
                                String qry="select tmovies from theatre where tcity='"+citySelected[0]+"' and tname='"+theName+"'";
                                    ResultSet resSet =  stmt.executeQuery(qry);
                                    int yPos = 50;
                                    while (resSet.next()) {
                                        String[] movieName = resSet.getString("tmovies").toUpperCase().split(",");
                                        for(String movies:movieName){
                                        JLabel movieLbl = new JLabel(movies);
                                        movieLbl.setForeground(Color.WHITE);
                                        movieLbl.setBounds(50, yPos, 200, 30); // Adjust as needed
                                        movieEdit.add(movieLbl);
                                        yPos += 40;
                                        }
                                        
                                    }
                                    movieEdit.revalidate();
                                    movieEdit.repaint();
                                }
                                    
                            catch(Exception ex){
                                ex.printStackTrace();
                            }

                            JLabel enterMovName=new JLabel("Enter Movie");
                            enterMovName.setBounds(300, 50, 200, 30);
                            enterMovName.setFont(new Font("Times New Roman", Font.BOLD, 20));
                            enterMovName.setForeground(Color.WHITE);
                            movieEdit.add(enterMovName);

                            JTextField enterMovField=new JTextField();
                            enterMovField.setBounds(500, 50, 200, 30);
                            movieEdit.add(enterMovField);

                            JButton add =new JButton("ADD");
                            add.setBounds(720, 50, 120, 30);
                            add.setFont(new Font("Times New Roman", Font.BOLD, 15));
                            movieEdit.add(add);

                            add.addActionListener(addd->{
                                String mvNameFieldText=enterMovField.getText();
                                String thrName=theatreName.getText();
                                try(Connection con = DriverManager.getConnection(dbUrl, user, pwdd);
                                    PreparedStatement updMovstmt=con.prepareStatement("update theatre set tmovies=Concat(tmovies,',"+mvNameFieldText+"') where tcity='"+citySelected[0]+"' and tname='"+thrName+"'")){
                                    updMovstmt.executeUpdate();    
                                    }
                                    catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            });
                            JButton delete =new JButton("Delete");
                            delete.setBounds(720, 80, 120, 30);
                            delete.setFont(new Font("Times New Roman", Font.BOLD, 15));
                            movieEdit.add(delete);

                            delete.addActionListener(ad->{
                                String mvNameFieldText=enterMovField.getText();
                                String thrName=theatreName.getText();
                                try(Connection con = DriverManager.getConnection(dbUrl, user, pwdd);
                                    PreparedStatement updMovstmt=con.prepareStatement("UPDATE theatre SET tmovies = TRIM(BOTH ',' FROM REPLACE(CONCAT(',', tmovies, ','), ?, ',')) " +"WHERE tcity ='"+citySelected[0]+"' AND tname ='"+thrName+"'")){
                                        updMovstmt.setString(1,","+ mvNameFieldText+",");
                                        updMovstmt.executeUpdate();    
                                    }
                                    catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            });
                            JButton update =new JButton("Update");
                            update.setBounds(720, 110, 120, 30);
                            update.setFont(new Font("Times New Roman", Font.BOLD, 15));
                            movieEdit.add(update);

                            update.addActionListener(upd->{
                                
                                JTextField enterNewMovField=new JTextField();
                                enterNewMovField.setBounds(500, 80, 200, 30);
                                movieEdit.add(enterNewMovField);
                                movieEdit.revalidate();
                                movieEdit.repaint();

                                String mvNameFieldText=enterMovField.getText();
                               
                                String thrName=theatreName.getText();
                                update.addActionListener(upd1->{
                                    String mvNewNameFieldText=enterNewMovField.getText();
                                    try(Connection con = DriverManager.getConnection(dbUrl, user, pwdd);
                                    PreparedStatement updMovstmt=con.prepareStatement("UPDATE theatre SET tmovies = replace(tmovies,'"+mvNameFieldText+"','"+mvNewNameFieldText+"')WHERE tcity ='"+citySelected[0]+"' AND tname ='"+thrName+"'")){
                                        
                                        updMovstmt.executeUpdate();    
                                    }
                                    catch(Exception ex){
                                    ex.printStackTrace();
                                }
                                });
                               
                            });
                            
                                movieEdit.setBounds(200, 0, 900, 600);
                                movieEdit.setLayout(null);
                                movieEdit.setVisible(true);
                                movieEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter the theatre name to update movie");
                            }
                        });

                        JButton addButton=new JButton("Add");
                        addButton.setBounds(750, 0, 80, 30);
                        addButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        theatreList.add(addButton);

                        addButton.addActionListener(add->{
                            if(!(theatreName.getText().trim().isEmpty())){
                            String theName=theatreName.getText();
                            theatreList.dispose();

                            JFrame addMovieFrame=new JFrame("ADD Movies Corner");
                            addMovieFrame.getContentPane().setBackground(Color.BLACK);

                            JLabel addMovieList = new JLabel("Movie names(use , for items)");
                            addMovieList.setBounds(0, 0, 200, 20);
                            addMovieList.setForeground(Color.WHITE);
                            addMovieFrame.add(addMovieList);

                            JTextField addMovieName=new JTextField();
                            addMovieName.setBounds(220, 0, 420, 30);
                            addMovieFrame.add(addMovieName);

                            

                            JLabel addShow = new JLabel("Show times(use , for items)");
                            addShow.setBounds(0, 50, 200, 20);
                            addShow.setForeground(Color.WHITE);
                            addMovieFrame.add(addShow);

                            JTextField addShowTime=new JTextField();
                            addShowTime.setBounds(200, 50, 420, 30);
                            addMovieFrame.add(addShowTime);

                            

                            JButton ok = new JButton("OK");
                            ok.setBounds(250, 80, 80, 20);
                            addMovieFrame.add(ok);
                            ok.addActionListener(okk->{
                                String movieList=addMovieName.getText();
                                String showList=addShowTime.getText();
                                try(
                                Connection addCn = DriverManager.getConnection(dbUrl, user, pwdd);
                                PreparedStatement stmt = addCn.prepareStatement("INSERT into theatre (tname,tcity,tmovies,tshow) VALUES (?,?,?,?)")){
                                    stmt.setString(1,theName);
                                    stmt.setString(2, citySelected[0]);
                                    stmt.setString(3, movieList);
                                    stmt.setString(4, showList);

                                    int rowsAffected = stmt.executeUpdate();
                                    if (rowsAffected > 0) {
                                        JOptionPane.showMessageDialog(null, "Added successfully");
                                        addMovieFrame.dispose(); 
                                    }
                                addMovieFrame.revalidate();
                                addMovieFrame.repaint();
                                }
                            
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        
                            });

                           
                            

                            addMovieFrame.setBounds(300, 200, 800, 300);
                            addMovieFrame.setLayout(null);
                            addMovieFrame.setVisible(true);
                            addMovieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Enter the theatre name to add");
                        }

                        
                        });
                    
                           
                        
                    }
                    catch (Exception a){
                        System.out.println(a);
                    }
                
                });
                theatreList.setBounds(0, 0, 1200, 600);
                theatreList.setLayout(null);
                theatreList.setVisible(true);
                theatreList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                });
                
            
                



                JButton adminContact = new JButton("Contact Us");
                adminContact.setBounds(100,50,160,20);
                adminContact.setFont(new Font("Times New Roman", Font.BOLD, 20));
                adminHome.add(adminContact);

                adminContact.addActionListener(aca->{
                    adminHome.dispose();
                    JFrame contactDetails=new JFrame("Contact details");
                    contactDetails.getContentPane().setBackground(Color.BLACK);
                    try(
                        Connection conn = DriverManager.getConnection(dbUrl, user, pwdd);
                        PreparedStatement prepstmt = conn.prepareStatement("select admin_email,admin_ContactNumber from adminContact")){
                            ResultSet res=prepstmt.executeQuery();
                            

                            int yadNum=80;
                            
                            int yadEmailName=50;
                            while(res.next()){
                                String adminEmailll=res.getString("admin_email");
                                String cntNum=res.getString("admin_ContactNumber");
                                JLabel emailLbl=new JLabel("Email: ");
                                emailLbl.setBounds(50, yadEmailName, 100, 30);
                                emailLbl.setForeground(Color.WHITE);
                                emailLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(emailLbl);

                                JLabel emailDesc=new JLabel(adminEmailll);
                                emailDesc.setBounds(170, yadEmailName, 270, 30);
                                emailDesc.setForeground(Color.WHITE);
                                emailDesc.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(emailDesc);

                                JLabel numLbl=new JLabel("Phone: ");
                                numLbl.setBounds(50, yadNum, 100, 30);
                                numLbl.setForeground(Color.WHITE);
                                numLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(numLbl);

                                JLabel numb=new JLabel(cntNum);
                                numb.setBounds(170, yadNum, 200, 30);
                                numb.setForeground(Color.WHITE);
                                numb.setFont(new Font("Times New Roman", Font.BOLD, 20));
                                contactDetails.add(numb);
                                yadEmailName=yadNum+30;
                                yadNum=yadEmailName+30;

                            }
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }

                    contactDetails.setSize(500, 350);
                    contactDetails.setLayout(null);
                    contactDetails.setVisible(true);
                    contactDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                });

                JButton adminBookingHistory = new JButton("Booking History");
                adminBookingHistory.setBounds(260,50,200,20);
                adminBookingHistory.setFont(new Font("Times New Roman", Font.BOLD, 20));
                adminHome.add(adminBookingHistory);
                adminBookingHistory.addActionListener(ef->{
                    try(Connection connect = DriverManager.getConnection(dbUrl, user, pwdd);
                    PreparedStatement stmt = connect.prepareStatement("select user_email,movie_name,theater_name,show_date,booking_time,number_of_tickets from bookinghistory")){
                    ResultSet resultSet=stmt.executeQuery();
                    adminHome.dispose();

                    JFrame bookingHistoryFrame=new JFrame("Booking history");
                    bookingHistoryFrame.getContentPane().setBackground(Color.BLACK);

                    JPanel booking = new JPanel();
                    booking.setLayout(new BoxLayout(booking, BoxLayout.Y_AXIS));
                    booking.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    booking.setBackground(Color.BLACK);

                        while (resultSet.next()) {
                            String userEmaill = resultSet.getString("user_email");
                            String movieName = resultSet.getString("movie_name");
                            String theaterName = resultSet.getString("theater_name");
                            String showDate = resultSet.getString("show_date");
                            String bookingTime = resultSet.getString("booking_time");
                            int numOfTickets = resultSet.getInt("number_of_tickets");
                        
                            // Create a JPanel for each booking
                            JPanel bookingPanel = new JPanel(new GridLayout(6, 1, 10, 10));
                            bookingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            bookingPanel.setBackground(Color.DARK_GRAY);

                            bookingPanel.add(new JLabel("User email: "+userEmaill)).setForeground(Color.WHITE);
                            bookingPanel.add(new JLabel("Movie: " + movieName)).setForeground(Color.WHITE);
                            bookingPanel.add(new JLabel("Theater: " + theaterName)).setForeground(Color.WHITE);
                            bookingPanel.add(new JLabel("Show Date: " + showDate)).setForeground(Color.WHITE);
                            bookingPanel.add(new JLabel("Booking Time: " + bookingTime)).setForeground(Color.WHITE);
                            bookingPanel.add(new JLabel("Tickets: " + numOfTickets)).setForeground(Color.WHITE);
                        
                            booking.add(bookingPanel);
                            booking.add(Box.createVerticalStrut(10));
                            booking.setBackground(Color.BLACK);
                            
                        }
                        
                        // Add the booking history panel to the frame and make it visible
                    bookingHistoryFrame.add(new JScrollPane(booking), BorderLayout.CENTER);
                    bookingHistoryFrame.setVisible(true);
                    bookingHistoryFrame.setSize(1200, 900);
                    bookingHistoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                } 
            });
                
             
            
                ImageIcon originalIcon = new ImageIcon("C:\\Users\\Manikandan\\OneDrive\\Documents\\MovieTicketReg\\lib\\images.jpeg");
                Image resizedImage = originalIcon.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JLabel adminHomejpg = new JLabel(resizedIcon);
                adminHomejpg.setBounds(0, 70, 1200, 600);
                adminHome.add(adminHomejpg);

                adminHome.setBounds(100,100,1200,800);
                adminHome.setLayout(null);
                adminHome.setVisible(true);
                adminHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            
            else{
                JOptionPane.showMessageDialog(f,"Invalid User I'D or Password");
            }
        }
    });

    b2 = new JButton("SIGN UP");
    b2.setBounds(370,300,100,30);
    b2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            f.dispose();
            JFrame s = new JFrame("SIGN IN PAGE");
            JLabel l1,l2,l3,l4,l5;
            JTextField tf1,tf2,tf3;
            JButton b1;
            JPasswordField p1,p2;
            JComboBox<String> cb1;

            String [] usertype = {"User","Admin"};
            Boolean []b={true};
            
        
            cb1 = new JComboBox(usertype);
            cb1.setBounds(20, 50, 100, 30);

            JLabel secretKeyLabel = new JLabel("Enter key:");
            secretKeyLabel.setBounds(140, 50, 100, 30);
    
            // Create the JTextField for the secret key, but don't add it to the frame yet
            JPasswordField secretKeyField = new JPasswordField();
            secretKeyField.setBounds(260, 50, 120, 30);

            JButton enter= new JButton("!");
            enter.setBounds(400, 50, 070, 030);
           
            // Add the ActionListener to the JComboBox
            cb1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (cb1.getSelectedItem().equals("Admin")) {
                        
                        s.add(secretKeyLabel);
                        s.add(secretKeyField);
                        s.add(enter);
                        
                    } else {
                        // Remove the secret key components if "User" is selected
                        s.remove(secretKeyLabel);
                        s.remove(secretKeyField);
                        s.remove(enter);
                    }
                    // Refresh the frame to show/hide the secret key components
                    s.revalidate();
                    s.repaint();
                    
                }
            });
            
            enter.addActionListener(ef->{
                if (cb1.getSelectedItem().equals("Admin")) {
                    String secretKey = new String(secretKeyField.getPassword());
                    if (secretKey.equals("HiAmudu")) {
                        JOptionPane.showMessageDialog(null, "Admin access granted");
                        b[0]=true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid admin key");
                        b[0]=false;
                    }
                }
            });
            
                if (cb1.getSelectedItem().equals("Admin")) {
                    String secretKey = new String(secretKeyField.getPassword());
                    if (secretKey.equals("HiAmudu")) {
                        JOptionPane.showMessageDialog(null, "Admin access granted");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid admin key");
                        
                    }
                }
            
            
            l1 = new JLabel("NAME");
            l1.setBounds(80,100,100,30);

            l2 = new JLabel("MOB NO");
            l2.setBounds(80,200,100,30);

            l3 = new JLabel("EMAIL");
            l3.setBounds(80,300,100,30);

            l4 = new JLabel("CREATE PASSWORD");
            l4.setBounds(80,400,170,30);

            l5 = new JLabel("CONFIRM PASSWORD");
            l5.setBounds(80,500,170,30);

            tf1 = new JTextField();
            tf1.setBounds(250,100,200,30);

            tf2 = new JTextField();
            tf2.setBounds(250,200,200,30);
           
            tf3 = new JTextField();
            tf3.setBounds(250,300,200,30);

            p1 = new JPasswordField();
            p1.setBounds(250,400,200,30);

            p2 = new JPasswordField();
            p2.setBounds(250,500,200,30);

            b1 = new JButton("REGISTER");
            b1.setBounds(150,600,100,30);
         

            //code for registering into the application (only sql is left for now)
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String passw1 =new String(p1.getPassword());
                    String passw2 =new String(p2.getPassword());
                    String mob=tf2.getText();
                    String name  = tf1.getText();
                    String email = tf3.getText();
                    String userType = ""+cb1.getItemAt(cb1.getSelectedIndex());
                    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+.[a-zA-Z.-]+$";

                    //mobile validity checking
                    int mobValidcount=0;
                    for (int i = 0; i <mob.length() ; i++) {
                            if(mob.charAt(i)=='1' ||mob.charAt(i)=='2' ||mob.charAt(i)=='3' ||mob.charAt(i)=='4' ||mob.charAt(i)=='5' ||mob.charAt(i)=='6' ||mob.charAt(i)=='7' ||mob.charAt(i)=='8' ||mob.charAt(i)=='9' ||mob.charAt(i)=='0'){
                               mobValidcount++;
                            }
                    }
                    if(mob.length()==10){
                        if (mobValidcount != 10) {
                            JOptionPane.showMessageDialog(s,"Mobile number should be a number");
                        }
                    }
                    else{
                         JOptionPane.showMessageDialog(s,"Enter 10 digit mobile number");
                    }
                    //pwd validity checking
                    if(!passw1.equals(passw2)){
                        JOptionPane.showMessageDialog(s,"Password Mismatch");
                    }

                    //name validity checking
                    if (name.length()==0) {
                        JOptionPane.showMessageDialog(s,"Enter your name");
                    }

                    //email validity checking
                    if (email.length()==0) {
                        JOptionPane.showMessageDialog(s,"Enter your email");
                    }
                    if(!email.matches(regex) && email.length()!=0){
                        JOptionPane.showMessageDialog(s,"Invalid email");
                    }
                    

                    //if everything is crct, adding it to the sql and successfully registering
                    if(passw1.equals(passw2) && mobValidcount==10 && (name.length()>=1) && (email.length()>=1 && email.matches(regex)) && (b[0]==true)){
                        //here goes sql
                        
                        try {
                            Connection regcn = DriverManager.getConnection(dbUrl, user, pwdd);
                            Statement stmt = null;
                            stmt = regcn.createStatement();

                            String regqry = "insert into user(name,mobno,Email,userType,pwd) values('"+name+"',"+mob+",'"+email+"','"+userType+"','"+passw1+"')";
                            stmt.executeUpdate(regqry);

                            JOptionPane.showMessageDialog(s,"Successfully Registered");
                        
                        } 
                    catch (Exception en) {
                            System.out.println(en);
                        }
                        
                        
                    }
                
                    
                    
                }
            
            });
            
            s.add(cb1);
            s.add(l1);s.add(l2);s.add(l3);s.add(l4);s.add(l5);
            s.add(tf1);s.add(tf2);s.add(tf3);s.add(p1);s.add(p2);
            s.add(b1);

            s.setBounds(500,100,500,700);
            s.setLayout(null);
            s.setVisible(true);
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        }
    
    });
    
    f.add(l1);
    f.add(l2);
    f.add(l3);
    f.add(l4);
    f.add(tf1);
    f.add(p1);
    f.add(b1);
    f.add(b2);
    

    
    f.setBounds(500,100,500,500);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

 
    
 
    
}
