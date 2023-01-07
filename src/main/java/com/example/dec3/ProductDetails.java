package com.example.dec3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
/*in the product details tableview is created and method form the product class is called*/
public class ProductDetails {

    public TableView<Product> productTable;


    //Product product=new Product();

    public Pane getAllProducts()
    {
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable=new TableView<>();

        final ObservableList<Product> data= FXCollections.observableArrayList();
        data.add(new Product(1,"Lenovo",9090));
        data.add(new Product(2,"Mac",8900));

        ObservableList items= Product.getAllProducts();



        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);//this we delete extracolumns
        productTable.getColumns().addAll(id,name,price);//adding the id,name,price to product table
        productTable.setMinSize(Dec3.width,Dec3.height);

        Pane tablePane=new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;
    }

    public Pane getProductByName(String searchName)
    {
        TableColumn id=new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        productTable=new TableView<>();

        ObservableList items= Product.getProductsByName(searchName);



        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(Dec3.height,Dec3.width);

        Pane tablePane=new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Product getSelectedProduct()
    {
        if(productTable==null)
        {
            System.out.println("Table Obejct Not Found");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            Product selectedProduct=productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId()+" "+selectedProduct.getName()+" "+selectedProduct.getPrice());
            return selectedProduct;
        }
        else {
            System.out.println("Please Select");
            return null;
        }
    }
}
