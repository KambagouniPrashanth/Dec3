package com.example.dec3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class OrderDetails {
    public TableView<Product> orderTable;


    //Product product=new Product();

    public Pane getAllOrders() {
        TableColumn oid = new TableColumn("oid");
        oid.setCellValueFactory(new PropertyValueFactory<>("oid"));

        TableColumn pid = new TableColumn("pid");
        pid.setCellValueFactory(new PropertyValueFactory<>("pid"));


        TableColumn cid = new TableColumn("cid");
        cid.setCellValueFactory(new PropertyValueFactory<>("cid"));



        orderTable = new TableView<>();
        final ObservableList<Order> data= FXCollections.observableArrayList();
        /*data.add(new Order(1,2,3,));
        data.add(new Order(2,4,5,));*/
        orderTable=new TableView<>();

        ObservableList items=Order.getAllOrders();



        orderTable.setItems(items);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        orderTable.getColumns().addAll(oid,pid,cid);
        orderTable.setMinSize(Dec3.height,Dec3.width);








        Pane tablePane=new Pane();
        tablePane.getChildren().add(orderTable);
        return tablePane;

    }
}
