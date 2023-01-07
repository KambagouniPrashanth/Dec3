package com.example.dec3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
/*user interface control we write here
* here we created one emaillabel,emailtextfield,passwlabel,passtextField*/
public class Dec3 extends Application {

    Pane bodyPane=new Pane();
    Login login=new Login();
    public static final int height=600,width=700,upperLine=50;
    Label messagelabel,loginLabel;

    ProductDetails productDetails=new ProductDetails();
    boolean loggedIn=false;

    Button orderButton,orderDetails;
    private GridPane headerBar()
    {
        GridPane gridPane=new GridPane();
        gridPane.setPrefSize(width,upperLine-5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);


        TextField searchText=new TextField();
        searchText.setMinWidth(300);

        loginLabel=new Label("Please login!");

        Button loginButton=new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn==false)
                {
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());

                }

            }
        });


        Button searchButton=new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                //bodyPane.getChildren().add(productDetails.getAllProducts());
                String search=searchText.getText();
                bodyPane.getChildren().add(productDetails.getProductByName(search));
            }
        });

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(loginLabel,2,0);
        gridPane.add(loginButton,3,0);




        return gridPane;

    }

    private GridPane footerBar()
    {

       orderDetails=new Button("OrderDetails");
       orderDetails.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
              /* if(loggedIn==false){
                   bodyPane.getChildren().clear();
                   bodyPane.getChildren().add(loginPage());
               }
               else {
                   Product order=orderDetails.getAllOrders();
                   if(order!=null){
                       String email=loginLabel.getText();
                       email=email.substring(8);
                       if(Order.placeSingleOrder(order,email)) {
                           System.out.println("Order Placed");
                       }
                       else{
                           System.out.println("Order Failed");
                       }
                   }
*/

               }
       });


       orderButton=new Button("Buy");
       orderButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if(loggedIn==false){
                   bodyPane.getChildren().clear();
                   bodyPane.getChildren().add(loginPage());
               }
               else {
                   Product product=productDetails.getSelectedProduct();
                   if(product!=null){
                       String email=loginLabel.getText();
                       email=email.substring(8);
                       if(Order.placeSingleOrder(product,email)) {
                           System.out.println("Order Placed");
                       }
                       else{
                           System.out.println("Order Failed");
                       }
                   }



               }

           }
       });
       GridPane gridPane=new GridPane();
       gridPane.add(orderButton,0,0);
       gridPane.add(orderDetails,1,0);
       gridPane.setMinWidth(width);
       gridPane.setTranslateY(height+10);
       return gridPane;

    }


    private GridPane loginPage() {
        Label emaillabel = new Label("Email");
        Label passwordlabel = new Label("Password");
        messagelabel = new Label("I am message");

        TextField emailField = new TextField();
         emailField.setPromptText("Enter Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginButton = new Button("Login");

        GridPane gridPane=new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.setAlignment(Pos.CENTER);





        gridPane.add(emaillabel,0,0);//second is row first is col
        gridPane.add(emailField,1,0);
        gridPane.add(passwordlabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(messagelabel,1,3);




        gridPane.add(loginButton,0,3);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailField.getText();
                String password=passwordField.getText();
                if(login.customerLogin(email,password))
                {
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                    loginLabel.setText("Welcome "+email);
                    loggedIn=true;
                    messagelabel.setText("Successfully Login");
                }
                else
                {
                    messagelabel.setText("Invalid User");
                }
                //messagelabel.setText("email is "+email+" has password "+password);

            }
        });
        return gridPane;
    }

    Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(width,height+upperLine);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setMinSize(width,height);
        bodyPane.setPrefSize(width,height);

       bodyPane.getChildren().add(productDetails.getAllProducts());


        root.getChildren().addAll(
                headerBar(),

                //loginPage(),
                bodyPane,
                footerBar()
        );
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("  Welcome to SupplyChain!  ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}